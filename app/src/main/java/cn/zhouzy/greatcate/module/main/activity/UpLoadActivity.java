package cn.zhouzy.greatcate.module.main.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.yzs.imageshowpickerview.ImageLoader;
import com.yzs.imageshowpickerview.ImageShowPickerBean;
import com.yzs.imageshowpickerview.ImageShowPickerListener;
import com.yzs.imageshowpickerview.ImageShowPickerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import cn.zhouzy.greatcate.R;
import cn.zhouzy.greatcate.base.BaseActivity;
import cn.zhouzy.greatcate.entity.ImageBean;
import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

/**
 * Created by 正义 on 2017/5/2.
 */

public class UpLoadActivity extends BaseActivity
{
	@Bind(R.id.image_show_picker_view_upload)
	ImageShowPickerView mImageShowPickerView;
	private static final int REQUEST_CODE_IMAGE = 1;

	@Override

	protected void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_upload);
		initView();
	}

	private void initView()
	{
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
}
