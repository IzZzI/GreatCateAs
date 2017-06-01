package cn.zhouzy.greatcate.module.main.presenter;

import java.util.List;

import cn.zhouzy.greatcate.common.callback.CommonCallback;
import cn.zhouzy.greatcate.contract.HumorContract;
import cn.zhouzy.greatcate.entity.Post;
import cn.zhouzy.greatcate.module.main.model.HumorModel;

/**
 * Created by 正义 on 2017/5/7.
 */

public class HumorPresenter implements HumorContract.IHumorPresenter
{
	private HumorContract.IHumorModel mHumorModel;
	private HumorContract.IHumorView mHumorView;

	public HumorPresenter(HumorContract.IHumorView mHumorView)
	{
		this.mHumorView = mHumorView;
		this.mHumorModel = new HumorModel();
	}

	@Override
	public void getAllHumorList(int pageNo, int pageSize)
	{
		mHumorModel.getAllHumorList(pageNo, pageSize, new CommonCallback()
		{
			@Override
			public void onSuccess(Object success)
			{
				mHumorView.onGetAllHumorListSuccessed((List<Post>) success);
			}

			@Override
			public void onFail(Object fail)
			{
				mHumorView.onGetAllHumorListErrored((String) fail);
			}
		});
	}

	@Override
	public void reqLikeOrUnLike(String userName, String postId)
	{
		mHumorModel.reqLikeOrUnLike(userName, postId, new CommonCallback()
		{
			@Override
			public void onSuccess(Object success)
			{

			}

			@Override
			public void onFail(Object fail)
			{

			}
		});
	}
}
