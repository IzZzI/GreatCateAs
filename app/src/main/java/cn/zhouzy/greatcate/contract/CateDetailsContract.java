package cn.zhouzy.greatcate.contract;

import cn.zhouzy.greatcate.common.callback.CommonCallback;

public interface CateDetailsContract
{
	interface ICateDetailsPresenter
	{
		void collect(String username, String id, String ablums, String title, String intro);

		void unCollect(String id);

		void isCollected(String id);
	}

	interface ICateDetailsModel
	{
		void collect(String username, String id, String ablums, String title, String intro,
					 CommonCallback callback);

		void unCollect(String id, CommonCallback callback);

		void isCollected(String id, CommonCallback callback);
	}

	interface ICateDetailsView
	{
		void onCollectSuccessed();

		void onCollectErrored(String errorMsg);

		void onUnCollectSuccessed();

		void onUnCollectErrored(String errorMsg);

		void setIsCollectFlag(boolean isCollect);

		void showToast(String msg);
	}
}
