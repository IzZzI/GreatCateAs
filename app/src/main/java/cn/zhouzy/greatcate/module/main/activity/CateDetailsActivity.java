package cn.zhouzy.greatcate.module.main.activity;

import java.util.List;

import com.nostra13.universalimageloader.core.ImageLoader;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.zhouzy.greatcate.R;
import cn.zhouzy.greatcate.base.BaseActivity;
import cn.zhouzy.greatcate.common.constant.Constant;
import cn.zhouzy.greatcate.common.utils.StrUtil;
import cn.zhouzy.greatcate.common.utils.ToastUtil;
import cn.zhouzy.greatcate.common.view.CircleImageView;
import cn.zhouzy.greatcate.contract.CateDetailsContract;
import cn.zhouzy.greatcate.contract.CateDetailsContract.ICateDetailsView;
import cn.zhouzy.greatcate.entity.Data;
import cn.zhouzy.greatcate.entity.Steps;
import cn.zhouzy.greatcate.entity.User;
import cn.zhouzy.greatcate.module.collect.presenter.CateDetailsPresenter;
import cn.zhouzy.greatcate.module.login.activity.LoginActivity;
import cn.zhouzy.greatcate.module.main.adapter.CateDetailsStepAdapter;

public class CateDetailsActivity extends BaseActivity implements ICateDetailsView
{
	@Bind(R.id.lv_cate_details_step)
	ListView mStepListView;
	@Bind(R.id.ll_cate_details_title_controller)
	LinearLayout mTitleController;
	@Bind(R.id.ll_cate_details_collect_container)
	LinearLayout mActivityCollectContainerLinaerLayout;
	private CateDetailsStepAdapter mCateDetailsStepAdapter;
	private Data mCateEntity;
	private List<Steps> mCateSteps;
	private TextView mNameLableTextView;
	private TextView mNameTextView;
	private TextView mIntroTextView;
	private ImageView mAlbumsImageView;
	private TextView mIngredientsTextView;
	private TextView mBurdenTextView;
	private LinearLayout mHeaderCollectContainerLinearLayout;
	private CircleImageView mCollectCircleImageView;
	private CateDetailsContract.ICateDetailsPresenter mCateDetailsPresenter;
	private String mTitle = "";
	private String mImTro = "";
	private String imgUrl = "";
	private String mId = "";
	private boolean isCollect = false;
	private User mUser;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		// 状态栏设置为透明
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
		{
			Window window = getWindow();
			window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
					| WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
			window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
					| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
					| View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
			window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

			// 给状态栏设置颜色。我设置透明。
			window.setStatusBarColor(Color.TRANSPARENT);
			window.setNavigationBarColor(Color.TRANSPARENT);
		}
		setContentView(R.layout.activity_cate_details);
		initView();
		initData();
		initListener();

	}

	private void initView()
	{
		// ListView上方的布局单独提出为include 最后添加到ListView头部
		View mHeaderView = getLayoutInflater().inflate(R.layout.include_cate_details_head_view,
				null);

		mNameLableTextView = (TextView) mHeaderView.findViewById(
				R.id.tv_include_cate_details_name_lable);
		mNameTextView = (TextView) mHeaderView.findViewById(R.id.tv_include_cate_details_name);
		mIntroTextView = (TextView) mHeaderView.findViewById(R.id.tv_include_cate_details_intro);
		mIngredientsTextView = (TextView) mHeaderView.findViewById(
				R.id.tv_include_cate_details_ingredients);
		mBurdenTextView = (TextView) mHeaderView.findViewById(R.id.tv_include_cate_details_burden);
		mAlbumsImageView = (ImageView) mHeaderView.findViewById(
				R.id.imageview_include_cate_details_albums);
		mHeaderCollectContainerLinearLayout = (LinearLayout) mHeaderView.findViewById(
				R.id.ll_include_cate_details_collect_cotanier);
		mCollectCircleImageView = (CircleImageView) mHeaderCollectContainerLinearLayout
				.findViewById(R.id.btn_include_cate_details_collect);

		mStepListView.addHeaderView(mHeaderView);
	}

	private void initListener()
	{
		mStepListView.setOnScrollListener(new OnScrollListener()
		{

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState)
			{

			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
					int totalItemCount)
			{
				changeTitleStatus(); // 滑动改变标题栏透明度
			}
		});
		mCollectCircleImageView.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				mCollectCircleImageView.setEnabled(false); // 请求成功前禁用
				if (isCollect)
				{
					// 收藏状态-走取消收藏逻辑
					mCateDetailsPresenter.unCollect(mId);
				} else
				{
					// 未收藏状态-走收藏逻辑
					if (mUser != null)
					{
						String username = mUser.getUsername();
						mCateDetailsPresenter.collect(username, mId, imgUrl, mTitle, mImTro);
					} else
					{
						mCollectCircleImageView.setEnabled(true);
						Intent in = new Intent(CateDetailsActivity.this, LoginActivity.class);
						startActivityForResult(in, Constant.REQUESTCODE_LOGIN);
						overridePendingTransition(R.anim.anim_open, R.anim.anim_alpha);
					}
				}
			}
		});
		;

	}

	@OnClick(
	{ R.id.btn_cate_details_back })
	void OnClick(View v)
	{
		switch (v.getId())
		{
		case R.id.btn_cate_details_back:
			finish();
			break;
		default:
			break;
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK && requestCode == Constant.REQUESTCODE_LOGIN)
		{
			refresh();
		}
	}

	private void refresh()
	{
		mUser = BmobUser.getCurrentUser(User.class);
		if (mUser != null)
		{
			mCollectCircleImageView.setEnabled(false);
			mCateDetailsPresenter.isCollected(mId);
		}
	}

	private void changeTitleStatus()
	{

		View v = mStepListView.getChildAt(0);
		if (v != null)
		{
			float mImageViewSrollY;
			int mImageViewHight = mAlbumsImageView.getHeight();
			int mTitleHight = mTitleController.getHeight();
			if (mImageViewHight == 0)
			{
				int alpha = 0;
				mTitleController.getBackground().setAlpha(alpha);
				return;
			}
			if (v instanceof RelativeLayout) // headView
			{
				mImageViewSrollY = -v.getTop();
				// 改变收藏按钮位置 图片高度减去标题栏加上按钮高度一半即滑动到按钮顶部
				if ((mImageViewSrollY >= (mImageViewHight - mTitleHight - mCollectCircleImageView
						.getHeight() / 2)) && mCollectCircleImageView
								.getParent() == mHeaderCollectContainerLinearLayout)
				{

					mHeaderCollectContainerLinearLayout.removeView(mCollectCircleImageView);
					mActivityCollectContainerLinaerLayout.addView(mCollectCircleImageView);

				} else if ((mImageViewSrollY <= (mImageViewHight - mTitleHight
						- mCollectCircleImageView.getHeight() / 2)) && mCollectCircleImageView
								.getParent() == mActivityCollectContainerLinaerLayout)
				{
					mActivityCollectContainerLinaerLayout.removeView(mCollectCircleImageView);
					mHeaderCollectContainerLinearLayout.addView(mCollectCircleImageView);
				}

			} else // 顶部布局已经滑出界面
			{
				mImageViewSrollY = mImageViewHight - mTitleHight;
			}
			if ((int) mImageViewSrollY >= (mImageViewHight - mTitleHight)) //
			{
				mImageViewSrollY = mImageViewHight - mTitleHight;

			}
			int alpha = (int) ((mImageViewSrollY / (mImageViewHight - mTitleHight)) * 255);
			mTitleController.getBackground().setAlpha(alpha);

		}

	}

	private void initData()
	{

		mCateDetailsPresenter = new CateDetailsPresenter(this);
		Intent in = getIntent();
		mCateEntity = (Data) in.getSerializableExtra(Constant.EXTRA_KEY_CATE);
		if (mCateEntity != null)
		{
			mTitle = mCateEntity.getTitle();
			mImTro = mCateEntity.getImtro();
			mId = mCateEntity.getId();
			String mBurden = mCateEntity.getBurden(); // 食材
			String mIngredients = mCateEntity.getIngredients(); // 佐料
			List<String> albums = mCateEntity.getAlbums();
			mCateSteps = mCateEntity.getSteps();
			// 获取封面图片Url并加载
			if (albums != null)
			{
				imgUrl = albums.get(0);
				if (!StrUtil.strIsNullOrEmpty(imgUrl))
				{
					ImageLoader.getInstance().displayImage(imgUrl, mAlbumsImageView);
				}
			}

			if (!StrUtil.strIsNullOrEmpty(mTitle))
			{
				mNameLableTextView.setText(mTitle);
				mNameTextView.setText(mTitle);
			}
			if (!StrUtil.strIsNullOrEmpty(mImTro))
			{
				mIntroTextView.setText(mImTro);
			}
			if (!StrUtil.strIsNullOrEmpty(mIngredients))
			{
				mIngredientsTextView.setText(mIngredients);
			}
			if (!StrUtil.strIsNullOrEmpty(mBurden))
			{
				mBurdenTextView.setText(mBurden);
			}

			mCateDetailsStepAdapter = new CateDetailsStepAdapter(mCateSteps, this);
			mStepListView.setAdapter(mCateDetailsStepAdapter);
		}
		mUser = BmobUser.getCurrentUser(User.class);
		if (mUser != null)
		{
			mCollectCircleImageView.setEnabled(false);
			mCateDetailsPresenter.isCollected(mId);
		}
	}

	@Override
	public void onCollectSuccessed()
	{
		isCollect = true;
		mCollectCircleImageView.setEnabled(true);
		mCollectCircleImageView.setImageResource(R.mipmap.btn_collect_pressed);
		ToastUtil.showToast(this, getResources().getString(R.string.collect_successed));
	}

	@Override
	public void onCollectErrored(String errorMsg)
	{
		mCollectCircleImageView.setEnabled(true);
		ToastUtil.showToast(this, getResources().getString(R.string.collect_failed));
	}

	@Override
	public void onUnCollectSuccessed()
	{
		isCollect = false;
		mCollectCircleImageView.setEnabled(true);
		mCollectCircleImageView.setImageResource(R.mipmap.btn_collect_nor);
		ToastUtil.showToast(this, getResources().getString(R.string.uncollect_successed));
	}

	@Override
	public void onUnCollectErrored(String errorMsg)
	{
		mCollectCircleImageView.setEnabled(true);
		ToastUtil.showToast(this, errorMsg);
	}

	@Override
	public void setIsCollectFlag(boolean isCollect)
	{
		this.isCollect = isCollect;
		if (isCollect)
		{
			mCollectCircleImageView.setImageResource(R.mipmap.btn_collect_pressed);
		} else
		{
			mCollectCircleImageView.setImageResource(R.mipmap.btn_collect_nor);
		}
		mCollectCircleImageView.setEnabled(true);
	}

	@Override
	public void showToast(String msg)
	{
		ToastUtil.showToast(this, msg);
	}

}
