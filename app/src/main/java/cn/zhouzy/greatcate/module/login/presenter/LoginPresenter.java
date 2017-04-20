package cn.zhouzy.greatcate.module.login.presenter;

import cn.zhouzy.greatcate.common.callback.CommonCallback;
import cn.zhouzy.greatcate.contract.LoginContract;
import cn.zhouzy.greatcate.contract.LoginContract.ILoginVew;
import cn.zhouzy.greatcate.module.login.model.LoginModel;
import cn.zhouzy.greatcate.module.login.view.ThridPartyLoginCallBack;

public class LoginPresenter implements LoginContract.ILoginPresenter
{
	private LoginContract.ILoginModel mLoginModel;
	private LoginContract.ILoginVew mLoginView;

	public LoginPresenter(ILoginVew mLoginView)
	{
		super();
		this.mLoginView = mLoginView;
		this.mLoginModel = new LoginModel();
	}

	@Override
	public void login(String email, String password)
	{
		mLoginModel.login(email, password, new CommonCallback()
		{

			@Override
			public void onSuccess(Object success)
			{
				mLoginView.onLoginSuccessed(success);
			}

			@Override
			public void onFail(Object fail)
			{
				mLoginView.onLoginFailed((String) fail);
			}
		});
	}

	@Override
	public void thirdPartyLogin(String name)
	{
		mLoginModel.thirdPartyLogin(name, new ThridPartyLoginCallBack()
		{

			@Override
			public void onSuccessed()
			{
				mLoginView.onLoginSuccessed(null);
			}

			@Override
			public void onError(String errorMsg)
			{
				mLoginView.onLoginFailed(errorMsg);
			}

			@Override
			public void onCancle()
			{
				mLoginView.onThirdPartyLoginCancle();
			}
		});
	}

}
