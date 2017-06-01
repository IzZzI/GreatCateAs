package cn.zhouzy.greatcate.module.login.model;

import java.util.HashMap;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.SaveListener;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.zhouzy.greatcate.common.callback.CommonCallback;
import cn.zhouzy.greatcate.contract.LoginContract;
import cn.zhouzy.greatcate.entity.User;
import cn.zhouzy.greatcate.module.login.view.ThridPartyLoginCallBack;

public class LoginModel implements LoginContract.ILoginModel
{

	@Override
	public void login(String email, String password, final CommonCallback callback)
	{
		User.loginByAccount(email, password, new LogInListener<User>()
		{

			@Override
			public void done(User user, BmobException arg1)
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

	@Override
	public void thirdPartyLogin(String name, final ThridPartyLoginCallBack callback)
	{
		Platform platform = ShareSDK.getPlatform(name);
		if (platform != null)
		{ // 判定是否完成授权
			if (platform.isAuthValid())
			{
//				PlatformDb platformDb = platform.getDb();
//				if (platformDb != null)
//				{
//					loginByUserId(platformDb, callback);
//				} // platform为null
				platform.removeAccount(true);
			}
			platform.SSOSetting(false);
			platform.setPlatformActionListener(new PlatformActionListener()
			{

				@Override
				public void onError(Platform arg0, int arg1, Throwable arg2)
				{
					callback.onError(arg2.getMessage());
				}

				@Override
				public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2)
				{
					if (arg0 != null)
					{
						PlatformDb platformDb = arg0.getDb();
						if (platformDb != null)
						{
							loginByUserId(platformDb, callback); // 授权成功进行登录或注册
						} // platform为null
					}
				}

				@Override
				public void onCancel(Platform arg0, int arg1)
				{
					callback.onCancle();
				}
			});
			platform.authorize();
			platform.showUser(null);

		}
	}

	/**
	 * 以三方的userId作为用户名登录或注册
	 *
	 * @param platformDb
	 * @param callback
	 */
	private void loginByUserId(final PlatformDb platformDb, final ThridPartyLoginCallBack callback)
	{
		final String userId = platformDb.getUserId();
		final String nickName = platformDb.getUserName();
		final String icon = platformDb.getUserIcon();
		if (userId != null)
		{
			// 查询userId是否存在数据库
			// 存在则跳主页
			// 不存在则走注册流程
			BmobQuery<User> bmobQuery = new BmobQuery<User>();
			bmobQuery.addWhereEqualTo("username", userId);
			bmobQuery.findObjects(new FindListener<User>()
			{

				@Override
				public void done(List<User> users, BmobException e)
				{
					if (e == null)
					{
						if (users != null && users.size() != 0)// 已注册 获取信息跳转到主界面
						{
							User user = users.get(0);
							if (user != null)
							{
								BmobUser.loginByAccount(userId, "123456", new LogInListener<User>()
								{

									@Override
									public void done(User arg0, BmobException arg1)
									{
										if (arg1 == null)
										{
											callback.onSuccessed();
											;
										} else
										{
											callback.onError(arg1.getMessage());
										}
									}
								});
							}

						} else // 未注册
						{
							User user = new User();
							user.setUsername(userId);
							user.setPassword("123456");
							user.setNickName(nickName);
							user.setIcon(icon);
							user.signUp(new SaveListener<User>()
							{

								@Override
								public void done(User arg0, BmobException arg1)
								{
									if (arg1 == null)
									{
										callback.onSuccessed();
									}
								}
							});
						}

					} else
					{
						callback.onError(e.getMessage());
					}
				}
			});

		} // userId为null
	}

}
