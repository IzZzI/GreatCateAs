package cn.zhouzy.greatcate.module.main.presenter;

import java.util.List;

import cn.zhouzy.greatcate.common.callback.CommonCallback;
import cn.zhouzy.greatcate.contract.SearchContract;
import cn.zhouzy.greatcate.contract.SearchContract.ISearchView;
import cn.zhouzy.greatcate.entity.Data;
import cn.zhouzy.greatcate.module.main.model.SearchModel;

public class SearchPresenter implements SearchContract.ISearchPresenter
{
	private SearchContract.ISearchModel mSearchModel;
	private ISearchView mSearchView;

	public SearchPresenter(ISearchView mSearchView)
	{
		super();
		this.mSearchView = mSearchView;
		this.mSearchModel = new SearchModel();
	}

	@Override
	public void searchByMenu(String name, String pn, String rn)
	{
		mSearchModel.searchByMenu(name, pn, rn, new CommonCallback()
		{

			@SuppressWarnings("unchecked")
			@Override
			public void onSuccess(Object success)
			{
				mSearchView.setSearchResultList((List<Data>) success);
			}

			@Override
			public void onFail(Object fail)
			{
				mSearchView.onSearchError((String) fail);
			}
		});
	}

}
