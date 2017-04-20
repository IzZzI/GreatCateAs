package cn.zhouzy.greatcate.module.main.activity;

import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import butterknife.Bind;
import butterknife.OnClick;
import cn.zhouzy.greatcate.R;
import cn.zhouzy.greatcate.base.BaseActivity;
import cn.zhouzy.greatcate.common.view.AutoCleanEditText;
import cn.zhouzy.greatcate.contract.SearchContract;
import cn.zhouzy.greatcate.contract.SearchContract.ISearchView;
import cn.zhouzy.greatcate.entity.Data;
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
	private SearchContract.ISearchPresenter mSearchPresenter;

	@Override
	protected void onCreate(Bundle arg0)
	{
		super.onCreate(arg0);
		setContentView(R.layout.activity_search);
		initView();
	}

	private void initView()
	{
		mTitleContainerRelativeLayout.getBackground().setAlpha(255);
		mSearchPresenter = new SearchPresenter(this);
	}

	@OnClick(
	{ R.id.btn_search_back, R.id.tv_search_hot_1, R.id.tv_search_hot_3, R.id.btn_search_query, R.id.tv_search_hot2 })

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

		default:
			break;
		}

	}

	private void query()
	{
		String mMenu = mSearchInputEditText.getText().toString().trim();
		
		
		
	}

	@Override
	public void setSearchResultList(List<Data> mDataList)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSearchError(String errorMsg)
	{
		// TODO Auto-generated method stub
		
	}

}
