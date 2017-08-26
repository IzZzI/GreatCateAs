package cn.zhouzy.greatcate.module.main.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yzs.imageshowpickerview.ImageLoader;
import com.yzs.imageshowpickerview.ImageShowPickerBean;
import com.yzs.imageshowpickerview.ImageShowPickerListener;
import com.yzs.imageshowpickerview.ImageShowPickerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.zhouzy.greatcate.R;
import cn.zhouzy.greatcate.base.BaseActivity;
import cn.zhouzy.greatcate.common.utils.ToastUtil;
import cn.zhouzy.greatcate.common.view.AutoCleanEditText;
import cn.zhouzy.greatcate.common.view.CBProgressBar;
import cn.zhouzy.greatcate.contract.UploadContract;
import cn.zhouzy.greatcate.entity.ImageBean;
import cn.zhouzy.greatcate.entity.User;
import cn.zhouzy.greatcate.module.main.presenter.UploadPresenter;
import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

/**
 * Created by 正义 on 2017/5/2.
 */

public class UpLoadActivity extends BaseActivity implements UploadContract.IUploadView
{
	@Bind(R.id.image_show_picker_view_upload)
	ImageShowPickerView mImageShowPickerView;
	@Bind(R.id.et_upload_content)
	AutoCleanEditText mContentEditText;
	@Bind(R.id.progressbar_upload_progress)
	CBProgressBar mProgressBar;
	@Bind(R.id.ll_upload_progress)
	LinearLayout mProgressLinearLayout;

	private static final int REQUEST_CODE_IMAGE = 1;
	private UploadContract.IUploadPresenter mUploadPresenter;
	private User mUser;

	@Override

	protected void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_upload);
		initView();
		initData();
	}

	private void initData()
	{
		mUser = BmobUser.getCurrentUser(User.class);
	}

	private void initView()
	{

		mUploadPresenter = new UploadPresenter(this);
		mProgressBar.setMax(100);
		mImageShowPickerView.setImageLoaderInterface(new ImageLoader()
		{
			@Override
			public void displayImage(Context context, String path, ImageView imageView)
			{
				com.nostra13.universalimageloader.core.ImageLoader.getInstance().displayImage(path, imageView);
			}

			@Override
			public void displayImage(Context context, @DrawableRes Integer resId, ImageView imageView)
			{
				imageView.setImageResource(resId);
			}
		});

		mImageShowPickerView.setPickerListener(new ImageShowPickerListener()
		{
			@Override
			public void addOnClickListener(int remainNum)
			{
				MultiImageSelector.create(UpLoadActivity.this).showCamera(true).count(9 - mImageShowPickerView.getDataList().size()).multi().origin(new ArrayList<String>())
						.start(UpLoadActivity.this, REQUEST_CODE_IMAGE);
			}

			@Override
			public void picOnClickListener(List<ImageShowPickerBean> list, int position, int remainNum)
			{

			}

			@Override
			public void delOnClickListener(int position, int remainNum)
			{

			}
		});
		mImageShowPickerView.show();

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == REQUEST_CODE_IMAGE && resultCode == RESULT_OK)
		{
			List<String> mPathList = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
			List<ImageBean> dataList = new ArrayList<>();
			if (mPathList != null)
			{
				for (int i = 0; i < mPathList.size(); i++)
				{
					ImageBean mImageBean = new ImageBean(mPathList.get(i), 0, "");
					dataList.add(mImageBean);
				}
				mImageShowPickerView.addData(dataList);
			}
		}
	}

	@OnClick({R.id.btn_upload_back, R.id.btn_upload_submit})
	void OnClick(View v)
	{
		switch (v.getId())
		{
			case R.id.btn_upload_back:
				finish();
				break;
			case R.id.btn_upload_submit:
				upload();
				break;
			default:
				break;
		}

	}

	private void upload()
	{
		if (mUser != null)
		{
			String mNickName = mUser.getNickName();
			String mUsername = mUser.getUsername();
			String mHeadPortrait = mUser.getIcon();
			String mContent = mContentEditText.getText().toString().trim();
			List<ImageBean> mImgList = mImageShowPickerView.getDataList();
			mProgressLinearLayout.setVisibility(View.VISIBLE);
			if (mNickName == null ||"".equals(mNickName))
			{
				mUploadPresenter.upload(mUsername, mContent, mHeadPortrait, mImgList);
			}else{
				mUploadPresenter.upload(mNickName, mContent, mHeadPortrait, mImgList);
			}


		}


	}


	@Override
	public void onUploadSuccessed()
	{
		ToastUtil.showToast(this, "成功");
		if (mProgressLinearLayout.getVisibility() == View.VISIBLE)
		{
			mProgressLinearLayout.setVisibility(View.GONE);
		}
		finish();
	}

	@Override
	public void onUploadErrored(String errorMsg)
	{
		ToastUtil.showToast(this, errorMsg);
		if (mProgressLinearLayout.getVisibility() == View.VISIBLE)
		{
			mProgressLinearLayout.setVisibility(View.GONE);
		}
	}

	@Override
	public void onUploadProgress(int progress)
	{

		mProgressBar.setProgress(progress);
	}
}
