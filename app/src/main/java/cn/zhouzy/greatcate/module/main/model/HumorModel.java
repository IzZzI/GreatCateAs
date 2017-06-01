package cn.zhouzy.greatcate.module.main.model;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.zhouzy.greatcate.common.callback.CommonCallback;
import cn.zhouzy.greatcate.contract.HumorContract;
import cn.zhouzy.greatcate.entity.Post;

/**
 * Created by 正义 on 2017/5/7.
 */

public class HumorModel implements HumorContract.IHumorModel
{
	@Override
	public void getAllHumorList(int pageNo, int pageSize, final CommonCallback callback)
	{
		BmobQuery<Post> mBmobQuery = new BmobQuery<>();
		mBmobQuery.setLimit(50);
		mBmobQuery.order("time");
		mBmobQuery.findObjects(new FindListener<Post>()
		{
			@Override
			public void done(List<Post> list, BmobException e)
			{
				if (e == null)
				{
					callback.onSuccess(list);
				} else
				{
					callback.onFail(e.getMessage());
				}
			}
		});
	}

	@Override
	public void reqLikeOrUnLike(String userName, String postId, CommonCallback callback)
	{

	}
}
