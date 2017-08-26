package cn.zhouzy.greatcate.module.main.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import cn.zhouzy.greatcate.R;
import cn.zhouzy.greatcate.base.BaseFragment;
import cn.zhouzy.greatcate.common.constant.Constant;
import cn.zhouzy.greatcate.common.utils.ToastUtil;
import cn.zhouzy.greatcate.contract.HumorContract;
import cn.zhouzy.greatcate.entity.Post;
import cn.zhouzy.greatcate.module.main.activity.UpLoadActivity;
import cn.zhouzy.greatcate.module.main.adapter.HumorListAdapter;
import cn.zhouzy.greatcate.module.main.presenter.HumorPresenter;

public class HumorFragment extends BaseFragment implements HumorContract.IHumorView
{

	@Bind(R.id.lv_humor_list)
	RecyclerView mHumorRecyclerView;
	@Bind(R.id.rl_humor_title_controller)
	RelativeLayout mTitleLinearLayout;
	@Bind(R.id.swiperefresh_humor)
	SwipeRefreshLayout mSwipeRefreshLayout;
	private boolean isCreateView;
	private boolean isLoadData;
	private HumorListAdapter mHumorListAdapter;
	private List<Post> mDataList;
	private HumorContract.IHumorPresenter mHumorPresenter;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		setContentView(inflater, container, R.layout.fragment_humor);
		isCreateView = true;
		initView();
		initListener();
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	private void initListener()
	{
		mHumorRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
		{
			@Override
			public void onScrolled(RecyclerView recyclerView, int dx, int dy)
			{
				super.onScrolled(recyclerView, dx, dy);
			}
		});
		mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
		{
			@Override
			public void onRefresh()
			{
				mHumorPresenter.getAllHumorList(0, 50);
			}
		});

	}

	private void initView()
	{

		mHumorRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
		dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.divider));
		mHumorRecyclerView.addItemDecoration(dividerItemDecoration);
		mHumorRecyclerView.setItemAnimator(new DefaultItemAnimator());
		mDataList = new ArrayList<>();
		mHumorListAdapter = new HumorListAdapter(mDataList, getActivity());
		mHumorRecyclerView.setAdapter(mHumorListAdapter);
		mHumorPresenter = new HumorPresenter(this);
		mTitleLinearLayout.setBackgroundResource(R.color.black_2c);
		mTitleLinearLayout.getBackground().setAlpha(255);

	}

	public static HumorFragment newInstance()
	{
		return new HumorFragment();
	}

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser)
	{
		super.setUserVisibleHint(isVisibleToUser);
		if (isVisibleToUser && isCreateView)
		{
			if (!isLoadData)
			{
				lazyLoad();
			}
		}

	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
		if (getUserVisibleHint()){
			lazyLoad();
		}
	}

	@OnClick({R.id.btn_humor_upload})
	void OnClick(View v)
	{
		switch (v.getId())
		{
			case R.id.btn_humor_upload:
				Intent in = new Intent(getActivity(), UpLoadActivity.class);
				startActivityForResult(in, Constant.REQUEST_CODE_UPLOAD);
				break;
			default:
				break;
		}


	}

	private void lazyLoad()
	{
		isLoadData = true;
		mHumorPresenter.getAllHumorList(0, 10);
		mTitleLinearLayout.setBackgroundResource(R.color.black_2c);
		mTitleLinearLayout.getBackground().setAlpha(255);


	}

	@Override
	public void onGetAllHumorListSuccessed(List<Post> postList)
	{
		if (postList != null)
		{
			mDataList.clear();
			mDataList.addAll(postList);
			mHumorListAdapter.notifyDataSetChanged();
		}
		mSwipeRefreshLayout.setRefreshing(false);
	}

	@Override
	public void onGetAllHumorListErrored(String errorMsg)
	{
		ToastUtil.showToast(getActivity(), errorMsg);
		mSwipeRefreshLayout.setRefreshing(false);
	}

	@Override
	public void onLikeSuccessed()
	{

	}

	@Override
	public void onUnLikeSuccessed()
	{

	}

	@Override
	public void onLikeOrUnLikeErrorred(String errorMsg)
	{

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == Activity.RESULT_OK && requestCode == Constant.REQUEST_CODE_UPLOAD)
		{

			mHumorPresenter.getAllHumorList(0, 10);
		}
	}
}
