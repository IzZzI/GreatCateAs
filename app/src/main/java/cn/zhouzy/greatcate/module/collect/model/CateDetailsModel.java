package cn.zhouzy.greatcate.module.collect.model;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.zhouzy.greatcate.common.callback.CommonCallback;
import cn.zhouzy.greatcate.common.utils.DateUtil;
import cn.zhouzy.greatcate.contract.CateDetailsContract;
import cn.zhouzy.greatcate.entity.CollectEntity;

public class CateDetailsModel implements CateDetailsContract.ICateDetailsModel
{

	@Override
	public void collect(String username, String id, String ablums, String title, String intro,
			final CommonCallback callback)
	{
		CollectEntity mCollectEntity = new CollectEntity();
		mCollectEntity.setAlbums(ablums);
		mCollectEntity.setUsername(username);
		mCollectEntity.setTitle(title);
		mCollectEntity.setIntro(intro);
		mCollectEntity.setId(id);
		mCollectEntity.setCollectTime(DateUtil.getCurTimeString());
		mCollectEntity.save(new SaveListener<String>()
		{

			@Override
			public void done(String arg0, BmobException e)
			{
				if (e == null)
				{
					callback.onSuccess(null);
				} else
				{
					callback.onFail(e.getMessage());
				}

			}
		});
	}

	@Override
	public void unCollect(String id, final CommonCallback callback)
	{

		BmobQuery<CollectEntity> mBmobQuery = new BmobQuery<CollectEntity>();
		mBmobQuery.addWhereEqualTo("id", id);
		mBmobQuery.findObjects(new FindListener<CollectEntity>()
		{

			@Override
			public void done(List<CollectEntity> arg0, BmobException e)
			{
				if (e == null)
				{
					if (arg0 != null && arg0.size() > 0 && arg0.get(0) != null)
					{
						CollectEntity collectEntity = arg0.get(0);
						collectEntity.delete(new UpdateListener()
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
					} else
					{
						callback.onFail("");
					}
				} else
				{
					callback.onFail(e.getMessage());
				}
			}
		});
	}

	@Override
	public void isCollected(String id, final CommonCallback callback)
	{
		BmobQuery<CollectEntity> mBmobQuery = new BmobQuery<CollectEntity>();
		mBmobQuery.addWhereEqualTo("id", id);
		mBmobQuery.findObjects(new FindListener<CollectEntity>()
		{

			@Override
			public void done(List<CollectEntity> arg0, BmobException e)
			{
				if (e == null)
				{
					if (arg0 != null && arg0.size() > 0)
					{
						callback.onSuccess(true);
					} else
					{
						callback.onSuccess(false);
					}
				} else
				{
					callback.onFail(e.getMessage());
				}
			}
		});
	}

}
