package cn.zhouzy.greatcate.contract;

import java.util.List;

import cn.zhouzy.greatcate.common.callback.CommonCallback;
import cn.zhouzy.greatcate.entity.CollectEntity;

public interface CollectContract
{
	interface ICollectPresenter
	{

		void refreshCollectList(String username, int pageNo);

		void getMoreCollectList(String username, int pageNo);

		void getDetails(String id);

	}

	interface ICollectModel
	{

		void getCollectList(String username, int pageNo, CommonCallback callback);

		void getDetails(String id, CommonCallback callback);

	}

	interface ICollectView
	{
		void onRefreshSuccessed(List<CollectEntity> mCollectList);

		void onRefreshErrored(String errorMsg);

		void onGetMoreSuccessed(List<CollectEntity> mCollectList);

		void onGetMoreErrored(String errorMsg);

		void onGetDetailsSuccessed(Object result);

		void onGetDetailsErrored(String errorMsg);

	}
}
