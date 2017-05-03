package cn.zhouzy.greatcate.module.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import cn.zhouzy.greatcate.R;
import cn.zhouzy.greatcate.base.BaseActivity;
import cn.zhouzy.greatcate.common.constant.Constant;
import cn.zhouzy.greatcate.common.utils.DialogUtil;
import cn.zhouzy.greatcate.common.utils.ToastUtil;
import cn.zhouzy.greatcate.common.view.AutoCleanEditText;
import cn.zhouzy.greatcate.contract.SearchContract;
import cn.zhouzy.greatcate.contract.SearchContract.ISearchView;
import cn.zhouzy.greatcate.entity.Data;
import cn.zhouzy.greatcate.module.main.adapter.SearchResultListAdapter;
import cn.zhouzy.greatcate.module.main.presenter.SearchPresenter;

public class SearchActivity extends BaseActivity implements ISearchView
{
	@Bind(R.id.lv_search_recent)
	ListView mRecentListView;
	@Bind(R.id.lv_search_result)
	ListView mResultListView;
	@Bind(R.id.rl_search_title_include_container)
	RelativeLayout mTitleContainerRelativeLayout;
	@Bind(R.id.et_search_input)
	AutoCleanEditText mSearchInputEditText;
	@Bind(R.id.ll_search_quick_search)
	LinearLayout mQuickSearchLinearLayout;
	private SearchContract.ISearchPresenter mSearchPresenter;
	private SearchResultListAdapter mSearchResultListAdapter;
	private List<Data> mResultList;

	@Override
	protected void onCreate(Bundle arg0)
	{
		super.onCreate(arg0);
		setContentView(R.layout.activity_search);
		initView();
		initListener();
	}

	private void initListener()
	{
		mResultListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				Intent in = new Intent(SearchActivity.this, CateDetailsActivity.class);
				in.putExtra(Constant.EXTRA_KEY_CATE, mResultList.get(position));
				startActivity(in);
			}
		});

	}

	private void initView()
	{
		mTitleContainerRelativeLayout.getBackground().setAlpha(255);
		mSearchPresenter = new SearchPresenter(this);
		mResultList = new ArrayList<>();
		mSearchResultListAdapter = new SearchResultListAdapter(this, mResultList);
		mResultListView.setAdapter(mSearchResultListAdapter);
	}

	@OnClick(
			{R.id.btn_search_back, R.id.tv_search_hot_1, R.id.tv_search_hot_3, R.id.btn_search_query, R.id.tv_search_hot2})
	void OnClick(View v)
	{
		switch (v.getId())
		{
			case R.id.btn_search_back:
				finish();
				break;
			case R.id.btn_search_query:
				query();
				break;
			case R.id.tv_search_hot_1:
				mSearchInputEditText.setText(getResources().getString(R.string.hot_1));
				query();
				break;
			case R.id.tv_search_hot2:
				mSearchInputEditText.setText(getResources().getString(R.string.hot_2));
				query();
				break;
			case R.id.tv_search_hot_3:
				mSearchInputEditText.setText(getResources().getString(R.string.hot_3));
				query();
				break;

			default:
				break;
		}

	}

	private void query()
	{
		String mMenu = mSearchInputEditText.getText().toString().trim();
		if (mMenu != null && !"".equals(mMenu))
		{
			DialogUtil.showLoadDialog(this, R.mipmap.xsearch_loading, "正在查询");
			mSearchPresenter.searchByMenu(mMenu, "1", "30");
		}else
		{
			mQuickSearchLinearLayout.setVisibility(View.GONE);
		}


	}

	@Override
	public void setSearchResultList(List<Data> mDataList)
	{
		DialogUtil.removeDialog(this);
		if (mDataList != null && mDataList.size() > 0)
		{
			mQuickSearchLinearLayout.setVisibility(View.GONE);
			mResultList.clear();
			mResultList.addAll(mDataList);
			mSearchResultListAdapter.notifyDataSetChanged();
		} else
		{
			mQuickSearchLinearLayout.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void onSearchError(String errorMsg)
	{
		DialogUtil.removeDialog(this);
		ToastUtil.showToast(this, errorMsg);
	}
}
