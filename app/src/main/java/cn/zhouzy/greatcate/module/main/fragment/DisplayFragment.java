package cn.zhouzy.greatcate.module.main.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.OnClick;
import cn.zhouzy.greatcate.R;
import cn.zhouzy.greatcate.base.BaseFragment;
import cn.zhouzy.greatcate.common.constant.Constant;
import cn.zhouzy.greatcate.common.utils.DialogUtil;
import cn.zhouzy.greatcate.common.utils.ToastUtil;
import cn.zhouzy.greatcate.common.view.ExpandGridViewToScrollView;
import cn.zhouzy.greatcate.contract.DisplayContract;
import cn.zhouzy.greatcate.entity.Data;
import cn.zhouzy.greatcate.module.main.activity.CateDetailsActivity;
import cn.zhouzy.greatcate.module.main.activity.SearchActivity;
import cn.zhouzy.greatcate.module.main.adapter.CategoryGridViewAdapter;
import cn.zhouzy.greatcate.module.main.adapter.DisplayCateListViewAdapter;
import cn.zhouzy.greatcate.module.main.presenter.DisplayPresenter;

public class DisplayFragment extends BaseFragment implements DisplayContract.IDisplayView
{

	@Bind(R.id.lv_display_recipe)
	ListView mCateListView;
	@Bind(R.id.ll_display_title_controller)
	LinearLayout mTitleControllerLinearLayout;
	@Bind(R.id.tv_display_title)
	TextView mTitleTextView;
	private DisplayCateListViewAdapter mCateAdapter;
	private List<Data> mCateList;
	private DisplayPresenter mDisplayPresenter;
	private int mCid = 1;
	private int mPn = 1;
	private int mRn = 30;
	// 头部分类GridView
	private ExpandGridViewToScrollView mHeaderCategoryGridView;
	// 头部图片
	private ImageView mHeaderBgImageView;
	private boolean isCreateView;
	private boolean isLoadData;
	private View mHeadView;
	private CategoryGridViewAdapter mCategoryGridViewAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		setContentView(inflater, container, R.layout.fragment_display);
		initView();
		initListener();
		isCreateView = true;
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	public static DisplayFragment newInstance()
	{
		return new DisplayFragment();
	}

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser)
	{
		super.setUserVisibleHint(isVisibleToUser);
		if (isVisibleToUser && isCreateView) // 对用户可见并且经过了onCreateView生命周期后才加载数据
		{
			if (!isLoadData)
			{
				lazyLoad(); // 延迟加载
			} else
			{
				refresh();
			}
		}
	}

	@Override
	public void onResume()
	{
		super.onResume();
		changeTitleStatus();
	}

	// 刷新
	private void refresh()
	{

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
		if (getUserVisibleHint()) // ViewPager第一个Item
		{
			lazyLoad();
		}
	}

	private void lazyLoad()
	{
		initData();
		isLoadData = true;
	}

	private void initData()
	{
		mDisplayPresenter = new DisplayPresenter(this);
		DialogUtil.showLoadDialog(getActivity(), R.mipmap.xsearch_loading,
				getResources().getString(R.string.loading));
		mDisplayPresenter.getCateList(mCid, mPn + "", mRn + "");
	}

	private void initView()
	{
		// 设置GridView适配器
		mCateList = new ArrayList<>();
		mCateAdapter = new DisplayCateListViewAdapter(mCateList, getActivity());
		mCateListView.setAdapter(mCateAdapter);
		// 初始化HeadView并添加到GridView头部
		mHeadView = LayoutInflater.from(getActivity()).inflate(R.layout.include_display_head_view, null, false);
		// 取得头部分类GridView
		mHeaderCategoryGridView = (ExpandGridViewToScrollView) mHeadView
				.findViewById(R.id.gv_include_display_head_view_category);
		mCategoryGridViewAdapter = new CategoryGridViewAdapter(getActivity());
		mHeaderCategoryGridView.setAdapter(mCategoryGridViewAdapter);
		// 取得头部背景图片
		mHeaderBgImageView = (ImageView) mHeadView.findViewById(R.id.iv_include_display_head_view_bgimageview);
		mCateListView.addHeaderView(mHeadView);
	}

	private void initListener()
	{
		mCateListView.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				// 添加了headView
				if (position < 1)
				{
					return;
				}
				Intent in = new Intent(getActivity(), CateDetailsActivity.class);
				in.putExtra(Constant.EXTRA_KEY_CATE, mCateList.get(position - 1));
				startActivity(in);
			}
		});

		mCateListView.setOnScrollListener(new OnScrollListener()
		{

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState)
			{

			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount)
			{
				changeTitleStatus();
			}
		});
		mHeaderCategoryGridView.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				mCid = position + 1;
				if (mCategoryGridViewAdapter != null)
				{
					String mTitle = mCategoryGridViewAdapter.getTitle(position);
					if (mTitle != null)
					{
						mTitleTextView.setText(mTitle);
					}
				}
				DialogUtil.showLoadDialog(getActivity(), R.mipmap.xsearch_loading,
						getResources().getString(R.string.loading));
				mDisplayPresenter.getCateList(mCid, mPn + "", mRn + "");

			}
		});

	}

	// 修改标题栏透明度
	private void changeTitleStatus()
	{
		// 头部滑动距离
		float scrollDistance = 0;
		int mHeaderImageViewHeight = mHeaderBgImageView.getHeight();
		int mTitleHeight = mTitleControllerLinearLayout.getHeight();
		View v = mCateListView.getChildAt(0);
		// 判断是headView或item headView用的RelativeLayout布局，item使用的LinearLayout布局
		if (v instanceof RelativeLayout)
		{
			scrollDistance = -v.getTop();

		} else
		{
			scrollDistance = mHeaderImageViewHeight - mTitleHeight; // 减去标题栏高度
		}
		if (scrollDistance >= mHeaderImageViewHeight - mTitleHeight)
		{
			scrollDistance = mHeaderImageViewHeight - mTitleHeight;
		}
		int alpha;
		if (mHeaderImageViewHeight == 0)
		{
			alpha = 0;
		} else
		{
			alpha = (int) ((scrollDistance / (mHeaderImageViewHeight - mTitleHeight)) * 255);
		}
		mTitleControllerLinearLayout.getBackground().setAlpha(alpha);
	}

	@OnClick(
			{R.id.btn_display_search})
	void OnClick(View v)
	{
		switch (v.getId())
		{
			case R.id.btn_display_search:
				Intent in = new Intent(getActivity(), SearchActivity.class);
				startActivity(in);
				break;

			default:
				break;
		}
	}

	@Override
	public void setCateList(List<Data> cateList)
	{
		mCateList.clear();
		mCateList.addAll(cateList);
		mCateAdapter.notifyDataSetChanged();
		DialogUtil.removeDialog(getActivity());
	}

	@Override
	public void onGetDataFailed(String error)
	{
		ToastUtil.showToast(getActivity(), error);
		DialogUtil.removeDialog(getActivity());
	}
}
