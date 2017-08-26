package cn.zhouzy.greatcate.module.collect.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yanzhenjie.recyclerview.swipe.Closeable;
import com.yanzhenjie.recyclerview.swipe.OnSwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.zhouzy.greatcate.R;
import cn.zhouzy.greatcate.base.BaseActivity;
import cn.zhouzy.greatcate.common.constant.Constant;
import cn.zhouzy.greatcate.common.utils.ToastUtil;
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
	SwipeMenuRecyclerView mCollectListListView;
	@Bind(R.id.rl_title_include_container)
	RelativeLayout mTitleContainer;
	@Bind(R.id.swipe_refresh_layout_collect)
	SwipeRefreshLayout mSwipeRefreshLayout;
	private CollectListAdapter mCollectListAdapter;
	private List<CollectEntity> mCollectList;
	private CollectContract.ICollectPresenter mCollectPresenter;
	private String mUsername;
	/**
	 * 分页页数
	 */
	private int pageNo;

	private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator()
	{
		@Override
		public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType)
		{


			// 设置菜单方向为竖型的。
			swipeRightMenu.setOrientation(SwipeMenu.VERTICAL);
			SwipeMenuItem deleteItem = new SwipeMenuItem(CollectActivity.this)
					.setBackgroundDrawable(R.mipmap.bg_delete_btn)
					.setText("删除") // 文字，还可以设置文字颜色，大小等。。
					.setTextColor(getResources().getColor(R.color.white))
					.setWidth(166)
					.setHeight(0).setWeight(1);// 设置高度为0。
			// 设置高度的Weight。
			swipeRightMenu.addMenuItem(deleteItem);// 添加一个按钮到右侧侧菜单。
		}
	};

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
		mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
		{
			@Override
			public void onRefresh()
			{
				mCollectPresenter.refreshCollectList(mUsername, 0);
			}
		});
		mCollectListAdapter.setOnItemClickListener(new CollectListAdapter.OnItemClickListener()
		{
			@Override
			public void onItemClick(int position)
			{
				mCollectPresenter.getDetails(mCollectList.get(position).getId());
			}
		});

		mCollectListListView.addOnScrollListener(new RecyclerView.OnScrollListener()
		{
			@Override
			public void onScrolled(RecyclerView recyclerView, int dx, int dy)
			{
				if (!recyclerView.canScrollVertically(1))
				{
					if (mCollectList != null && mCollectList.size() > 0)
					{
						pageNo++;
						mCollectPresenter.getMoreCollectList(mUsername, pageNo);
					}
					// 手指不能向上滑动了
					// TODO 这里有个注意的地方，如果你刚进来时没有数据，但是设置了适配器，这个时候就会触发加载更多，需要开发者判断下是否有数据，如果有数据才去加载更多。
				}
			}
		});
		mCollectListListView.setSwipeMenuItemClickListener(new OnSwipeMenuItemClickListener()
		{
			@Override
			public void onItemClick(Closeable closeable, int adapterPosition, int menuPosition, int direction)
			{
				mCollectList.remove(adapterPosition);
				mCollectListAdapter.notifyItemRemoved(adapterPosition);
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
		mCollectListListView.setLayoutManager(new LinearLayoutManager(this));
		mCollectListListView.setSwipeMenuCreator(swipeMenuCreator);
		mCollectList = new ArrayList<>();
		mCollectListAdapter = new CollectListAdapter(mCollectList);
		mCollectListListView.setAdapter(mCollectListAdapter);

	}

	@OnClick(
			{R.id.btn_back})
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
		mSwipeRefreshLayout.setRefreshing(false);
		pageNo = 0;
		this.mCollectList.clear();
		this.mCollectList.addAll(mCollectList);
		mCollectListAdapter.notifyDataSetChanged();

	}

	@Override
	public void onRefreshErrored(String errorMsg)
	{
		mSwipeRefreshLayout.setRefreshing(false);
		ToastUtil.showToast(this, errorMsg);

	}

	@Override
	public void onGetMoreSuccessed(List<CollectEntity> mCollectList)
	{
		this.mCollectList.addAll(mCollectList);
		mCollectListAdapter.notifyDataSetChanged();
	}

	@Override
	public void onGetMoreErrored(String errorMsg)
	{
		pageNo--;
		ToastUtil.showToast(this, errorMsg);
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
