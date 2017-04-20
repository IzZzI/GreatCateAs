package cn.zhouzy.greatcate.module.collect.presenter;

import java.util.List;

import cn.zhouzy.greatcate.common.callback.CommonCallback;
import cn.zhouzy.greatcate.contract.CollectContract;
import cn.zhouzy.greatcate.contract.CollectContract.ICollectView;
import cn.zhouzy.greatcate.entity.CollectEntity;
import cn.zhouzy.greatcate.module.collect.model.CollectModel;

public class CollectPresenter implements CollectContract.ICollectPresenter
{
	private CollectContract.ICollectModel mCollectModel;
	private CollectContract.ICollectView mCollectView;

	public CollectPresenter(ICollectView mCollectView)
	{
		super();
		this.mCollectView = mCollectView;
		this.mCollectModel = new CollectModel();
	}

	@Override
	public void refreshCollectList(String username, int pageNo)
	{
		mCollectModel.getCollectList(username, pageNo, new CommonCallback()
		{

			@SuppressWarnings("unchecked")
			@Override
			public void onSuccess(Object success)
			{
				mCollectView.onRefreshSuccessed((List<CollectEntity>) success);
			}

			@Override
			public void onFail(Object fail)
			{
				mCollectView.onRefreshErrored((String) fail);
			}
		});
	}

	@Override
	public void getMoreCollectList(String username, int pageNo)
	{
		mCollectModel.getCollectList(username, pageNo, new CommonCallback()
		{

			@SuppressWarnings("unchecked")
			@Override
			public void onSuccess(Object success)
			{
				mCollectView.onGetMoreSuccessed((List<CollectEntity>) success);
			}

			@Override
			public void onFail(Object fail)
			{
				mCollectView.onGetMoreErrored((String) fail);
			}
		});
	}

	@Override
	public void getDetails(String id)
	{
		mCollectModel.getDetails(id, new CommonCallback()
		{

			@Override
			public void onSuccess(Object success)
			{
				mCollectView.onGetDetailsSuccessed(success);
			}

			@Override
			public void onFail(Object fail)
			{
				mCollectView.onGetDetailsErrored((String) fail);
			}
		});
	}

}
