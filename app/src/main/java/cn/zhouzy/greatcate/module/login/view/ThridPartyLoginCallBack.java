package cn.zhouzy.greatcate.module.login.view;

public interface ThridPartyLoginCallBack
{
	void onSuccessed();

	void onError(String errorMsg);

	void onCancle();
}
