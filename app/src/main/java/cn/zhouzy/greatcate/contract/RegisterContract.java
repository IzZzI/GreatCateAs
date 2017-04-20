package cn.zhouzy.greatcate.contract;

import cn.zhouzy.greatcate.common.callback.CommonCallback;

public interface RegisterContract
{
	interface IRegisterPresenter
	{
		void register(String username, String password, String email);
	}

	interface IRegisterModel
	{
		void register(String username, String password, String email, CommonCallback callback);
	}

	interface IRegisterView
	{
		void onRegisterSuccessed();

		void onRegisterFailed(String errorMsg);
	}

}
