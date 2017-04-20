package cn.zhouzy.greatcate.module.main.presenter;

import cn.zhouzy.greatcate.common.callback.CommonCallback;
import cn.zhouzy.greatcate.contract.UserInfoContract;
import cn.zhouzy.greatcate.contract.UserInfoContract.IUserInfoView;
import cn.zhouzy.greatcate.module.main.model.UserInfoModel;

public class UserInfoPresenter implements UserInfoContract.IUserInfoPresenter
{
	private UserInfoContract.IUserInfoModel mUserInfoModel;
	private UserInfoContract.IUserInfoView mUserInfoView;

	public UserInfoPresenter(IUserInfoView mUserInfoView)
	{
		super();
		this.mUserInfoModel = new UserInfoModel();
		this.mUserInfoView = mUserInfoView;
	}

	@Override
	public void modifyNickname(String nickname, String objectId)
	{
		mUserInfoModel.modifyNickname(nickname, objectId, new CommonCallback()
		{

			@Override
			public void onSuccess(Object success)
			{
				mUserInfoView.onModifySuccessed();
			}

			@Override
			public void onFail(Object fail)
			{
				mUserInfoView.onModifyErrored((String) fail);
			}
		});
	}

	@Override
	public void modifyProfile(String profile, String objectId)
	{
		mUserInfoModel.modifyProfile(profile, objectId, new CommonCallback()
		{

			@Override
			public void onSuccess(Object success)
			{
				mUserInfoView.onModifySuccessed();
			}

			@Override
			public void onFail(Object fail)
			{
				mUserInfoView.onModifyErrored((String) fail);
			}
		});
	}

	@Override
	public void modifyHeadProtrait(String headProtraitPath, String objectId, String nowIcon)
	{
		mUserInfoModel.modifyHeadProtrait(headProtraitPath, objectId, nowIcon, new CommonCallback()
		{

			@Override
			public void onSuccess(Object success)
			{
				mUserInfoView.onModifySuccessed();
			}

			@Override
			public void onFail(Object fail)
			{
				mUserInfoView.onModifyErrored((String) fail);
			}
		});
	}

}
