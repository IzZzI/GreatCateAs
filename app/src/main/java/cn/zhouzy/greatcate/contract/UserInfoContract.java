package cn.zhouzy.greatcate.contract;

import cn.zhouzy.greatcate.common.callback.CommonCallback;

public interface UserInfoContract
{
	interface IUserInfoPresenter
	{
		void modifyNickname(String nickname, String objectId);

		void modifyProfile(String profile, String objectId);

		void modifyHeadProtrait(String headprotraitPath, String objectId, String nowIcon);

	}

	interface IUserInfoModel
	{
		void modifyNickname(String nickname, String objectId, CommonCallback callback);

		void modifyProfile(String profile, String objectId, CommonCallback callback);

		void modifyHeadProtrait(String headProtraitPath, String objectId, String nowIcon, CommonCallback callback);
	}

	interface IUserInfoView
	{
		void onModifySuccessed();

		void onModifyErrored(String errorMsg);
	}
}
