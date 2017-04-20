package cn.zhouzy.greatcate.module.login.model;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.zhouzy.greatcate.common.callback.CommonCallback;
import cn.zhouzy.greatcate.contract.RegisterContract;
import cn.zhouzy.greatcate.entity.User;

public class RegisterModel implements RegisterContract.IRegisterModel
{

	@Override
	public void register(String username, String password, String email, final CommonCallback callback)
	{
		User mBmobUser = new User();
		mBmobUser.setUsername(username);
		mBmobUser.setPassword(password);
		if (!"".equals(email))
		{
			mBmobUser.setEmail(email);
		}
		mBmobUser.signUp(new SaveListener<User>()
		{

			@Override
			public void done(User arg0, BmobException arg1)
			{
				if (arg1 == null)
				{
					callback.onSuccess(null);
				} else
				{
					callback.onFail(arg1.getMessage());
				}

			}
		});
	}

}
