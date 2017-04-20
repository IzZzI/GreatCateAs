package cn.zhouzy.greatcate.module.collect.presenter;

import cn.zhouzy.greatcate.common.callback.CommonCallback;
import cn.zhouzy.greatcate.contract.CateDetailsContract;
import cn.zhouzy.greatcate.contract.CateDetailsContract.ICateDetailsView;
import cn.zhouzy.greatcate.module.collect.model.CateDetailsModel;

public class CateDetailsPresenter implements CateDetailsContract.ICateDetailsPresenter
{
	private CateDetailsContract.ICateDetailsModel mCateDetailsModel;
	private CateDetailsContract.ICateDetailsView mCateDetailsView;

	public CateDetailsPresenter(ICateDetailsView mCateDetailsView)
	{
		super();
		this.mCateDetailsView = mCateDetailsView;
		this.mCateDetailsModel = new CateDetailsModel();
	}

	@Override
	public void collect(String username, String id, String ablums, String title, String intro)
	{
		mCateDetailsModel.collect(username, id, ablums, title, intro, new CommonCallback()
		{

			@Override
			public void onSuccess(Object success)
			{
				mCateDetailsView.onCollectSuccessed();

			}

			@Override
			public void onFail(Object fail)
			{
				mCateDetailsView.onCollectErrored((String) fail);
			}
		});
	}

	@Override
	public void unCollect(String id)
	{
		mCateDetailsModel.unCollect(id, new CommonCallback()
		{

			@Override
			public void onSuccess(Object success)
			{
				mCateDetailsView.onUnCollectSuccessed();
			}

			@Override
			public void onFail(Object fail)
			{
				mCateDetailsView.onUnCollectErrored((String) fail);
			}
		});
	}

	@Override
	public void isCollected(String id)
	{
		mCateDetailsModel.isCollected(id, new CommonCallback()
		{

			@Override
			public void onSuccess(Object success)
			{
				mCateDetailsView.setIsCollectFlag((boolean) success);
			}

			@Override
			public void onFail(Object fail)
			{
				mCateDetailsView.showToast((String) fail);
			}
		});
	}

}
