package cn.zhouzy.greatcate.module.main.model;

import java.io.File;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;
import cn.zhouzy.greatcate.common.callback.CommonCallback;
import cn.zhouzy.greatcate.contract.UserInfoContract;
import cn.zhouzy.greatcate.entity.User;

public class UserInfoModel implements UserInfoContract.IUserInfoModel
{

	@Override
	public void modifyNickname(String nickname, String objectId, final CommonCallback callback)
	{
		User newUser = new User();
		newUser.setNickName(nickname);
		newUser.update(objectId, new UpdateListener()
		{

			@Override
			public void done(BmobException arg0)
			{
				if (arg0 == null)
				{
					callback.onSuccess("修改成功");
				} else
				{
					callback.onFail(arg0.getMessage());
				}
			}
		});
	}

	@Override
	public void modifyProfile(String profile, String objectId, final CommonCallback callback)
	{
		User newUser = new User();
		newUser.setProfile(profile);
		newUser.update(objectId, new UpdateListener()
		{

			@Override
			public void done(BmobException arg0)
			{
				if (arg0 == null)
				{
					callback.onSuccess(null);
				} else
				{
					callback.onFail(arg0.getMessage());
				}
			}
		});

	}

	@Override
	public void modifyHeadProtrait(String headProtraitPath, String objectId, String nowIcon, CommonCallback callback)
	{
		uploadNewHeadProtrait(headProtraitPath, objectId, nowIcon, callback);
	}

	private void uploadNewHeadProtrait(String headProtraitPath, final String objectId, final String nowIcon,
	        final CommonCallback callback)
	{
		final BmobFile mBmobFile = new BmobFile(new File(headProtraitPath));
		mBmobFile.uploadblock(new UploadFileListener()
		{

			@Override
			public void done(BmobException arg0)
			{
				if (arg0 == null)
				{
					String fileUrl = mBmobFile.getFileUrl();
					updateUserInfo(fileUrl, objectId, nowIcon, callback);
				} else
				{
					callback.onFail(arg0.getMessage());
				}
			}

		});
	}

	//更新用户信息
	private void updateUserInfo(String filePath, String objectId, final String nowIcon, final CommonCallback callback)
	{
		User mNewUser = new User();
		mNewUser.setIcon(filePath);
		mNewUser.update(objectId, new UpdateListener()
		{

			@Override
			public void done(BmobException arg0)
			{
				if (arg0 == null)
				{
					callback.onSuccess(null);
					deleteOldHeadProtrait(nowIcon);
				} else
				{
					callback.onFail(arg0.getMessage());
				}
			}
		});
	}

	private void deleteOldHeadProtrait(String icon)
	{
		BmobFile mBmobFile = new BmobFile();
		mBmobFile.setUrl(icon);
		mBmobFile.delete(new UpdateListener()
		{

			@Override
			public void done(BmobException arg0)
			{
				if (arg0 == null)
				{
				} else
				{
				}
			}
		});
	}

}
