package cn.zhouzy.greatcate.module.login.presenter;

import cn.zhouzy.greatcate.common.callback.CommonCallback;
import cn.zhouzy.greatcate.contract.RegisterContract;
import cn.zhouzy.greatcate.contract.RegisterContract.IRegisterView;
import cn.zhouzy.greatcate.module.login.model.RegisterModel;

public class RegisterPresenter implements RegisterContract.IRegisterPresenter
{
	private RegisterContract.IRegisterModel mRegisterModel;
	private RegisterContract.IRegisterView mRegisterView;

	public RegisterPresenter(IRegisterView mRegisterView)
	{
		super();
		this.mRegisterView = mRegisterView;
		mRegisterModel = new RegisterModel();
	}

	@Override
	public void register(String username, String password, String email)
	{
		mRegisterModel.register(username, password, email, new CommonCallback()
		{

			@Override
			public void onSuccess(Object success)
			{
				mRegisterView.onRegisterSuccessed();
			}

			@Override
			public void onFail(Object fail)
			{
				mRegisterView.onRegisterFailed((String) fail);
			}
		});
	}

}
