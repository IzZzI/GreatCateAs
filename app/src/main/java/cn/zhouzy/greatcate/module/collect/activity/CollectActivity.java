package cn.zhouzy.greatcate.module.collect.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.zhouzy.greatcate.R;
import cn.zhouzy.greatcate.base.BaseActivity;
import cn.zhouzy.greatcate.common.constant.Constant;
import cn.zhouzy.greatcate.common.utils.ToastUtil;
import cn.zhouzy.greatcate.common.view.pullview.CommPullToRefreshView;
import cn.zhouzy.greatcate.common.view.pullview.CommPullToRefreshView.OnFooterLoadListener;
import cn.zhouzy.greatcate.common.view.pullview.CommPullToRefreshView.OnHeaderRefreshListener;
import cn.zhouzy.greatcate.contract.CollectContract;
import cn.zhouzy.greatcate.contract.CollectContract.ICollectView;
import cn.zhouzy.greatcate.entity.CollectEntity;
import cn.zhouzy.greatcate.entity.Data;
import cn.zhouzy.greatcate.entity.User;
import cn.zhouzy.greatcate.module.collect.adapter.CollectListAdapter;
import cn.zhouzy.greatcate.module.collect.presenter.CollectPresenter;
import cn.zhouzy.greatcate.module.main.activity.CateDetailsActivity;

public class CollectActivity extends BaseActivity implements ICollectView
{
	@Bind(R.id.tv_title)
	TextView mTitleTextView;
	@Bind(R.id.lv_collect_list)
	ListView mCollectListListView;
	@Bind(R.id.rl_title_include_container)
	RelativeLayout mTitleContainer;
	@Bind(R.id.refreshview_collect_pulltorefreshview)
	CommPullToRefreshView mCommPullToRefreshView;
	private CollectListAdapter mCollectListAdapter;
	private List<CollectEntity> mCollectList;
	private CollectContract.ICollectPresenter mCollectPresenter;
	private String mUsername;
	/**
	 * 分页页数
	 */
	private int pageNo;

	@Override
	protected void onCreate(Bundle arg0)
	{
		super.onCreate(arg0);
		setContentView(R.layout.activity_collect);
		initView();
		initData();
		initListener();
	}

	private void initListener()
	{
		mCommPullToRefreshView.setOnHeaderRefreshListener(new OnHeaderRefreshListener()
		{

			@Override
			public void onHeaderRefresh(CommPullToRefreshView view)
			{
				mCollectPresenter.refreshCollectList(mUsername, 0);
			}
		});
		mCommPullToRefreshView.setOnFooterLoadListener(new OnFooterLoadListener()
		{

			@Override
			public void onFooterLoad(CommPullToRefreshView view)
			{
				pageNo++;
				mCollectPresenter.getMoreCollectList(mUsername, pageNo);
			}
		});
		mCollectListListView.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				CollectEntity mCollectEntity = mCollectList.get(position);
				if (mCollectEntity != null)
				{
					String mId = mCollectEntity.getId();
					if (mId != null)
					{
						mCollectPresenter.getDetails(mId);
					}
				}
			}
		});
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		mTitleContainer.getBackground().setAlpha(255);
	}

	private void initData()
	{
		mCollectPresenter = new CollectPresenter(this);
		User mUser = BmobUser.getCurrentUser(User.class);
		if (mUser != null)
		{
			mUsername = mUser.getUsername();
			if (mUsername != null)
			{
				mCollectPresenter.refreshCollectList(mUsername, pageNo);
			} else
			{
				mUsername = "";
			}
		}
	}

	private void initView()
	{
		mTitleTextView.setText(getResources().getString(R.string.collect));
		mTitleContainer.getBackground().setAlpha(255);
		mCollectList = new ArrayList<>();
		mCollectListAdapter = new CollectListAdapter(this, mCollectList);
		mCollectListListView.setAdapter(mCollectListAdapter);
		mCommPullToRefreshView.setLoadMoreEnable(true);

	}

	@OnClick(
	{ R.id.btn_back })
	void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.btn_back:
			finish();
			break;

		default:
			break;
		}
	}

	@Override
	public void onRefreshSuccessed(List<CollectEntity> mCollectList)
	{
		pageNo = 0;
		this.mCollectList.clear();
		this.mCollectList.addAll(mCollectList);
		mCollectListAdapter.notifyDataSetChanged();
		mCommPullToRefreshView.onHeaderRefreshFinish();
	}

	@Override
	public void onRefreshErrored(String errorMsg)
	{
		ToastUtil.showToast(this, errorMsg);
		mCommPullToRefreshView.onHeaderRefreshFinish();
	}

	@Override
	public void onGetMoreSuccessed(List<CollectEntity> mCollectList)
	{
		this.mCollectList.addAll(mCollectList);
		mCollectListAdapter.notifyDataSetChanged();
		mCommPullToRefreshView.onFooterLoadFinish();
	}

	@Override
	public void onGetMoreErrored(String errorMsg)
	{
		pageNo--;
		ToastUtil.showToast(this, errorMsg);
		mCommPullToRefreshView.onFooterLoadFinish();
	}

	@Override
	public void onGetDetailsSuccessed(Object result)
	{
		if (result != null)
		{
			List<Data> mDataList = (List<Data>) result;
			if (mDataList != null && mDataList.size() > 0)
			{
				Data mData = mDataList.get(0);
				if (mData != null)
				{
					Intent in = new Intent(this, CateDetailsActivity.class);
					in.putExtra(Constant.EXTRA_KEY_CATE, mData);
					startActivity(in);
				}
			}
		}
	}

	@Override
	public void onGetDetailsErrored(String errorMsg)
	{
		ToastUtil.showToast(this, errorMsg);
	}

}
