package cn.zhouzy.greatcate.module.collect.model;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.zhouzy.greatcate.api.DisplayApi;
import cn.zhouzy.greatcate.base.BaseSubscriber;
import cn.zhouzy.greatcate.common.callback.CommonCallback;
import cn.zhouzy.greatcate.common.constant.Constant;
import cn.zhouzy.greatcate.common.mannager.RetrofitMannager;
import cn.zhouzy.greatcate.contract.CollectContract;
import cn.zhouzy.greatcate.entity.CollectEntity;
import cn.zhouzy.greatcate.entity.Data;
import cn.zhouzy.greatcate.entity.Root;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CollectModel implements CollectContract.ICollectModel
{

	@Override
	public void getCollectList(String username, int pageNo, final CommonCallback callback)
	{
		BmobQuery<CollectEntity> mBmobQuery = new BmobQuery<>();
		mBmobQuery.addWhereEqualTo("username", username);
		mBmobQuery.setLimit(30);
		mBmobQuery.setSkip(pageNo * 30);
		mBmobQuery.order("-createdAt");
		mBmobQuery.findObjects(new FindListener<CollectEntity>()
		{

			@Override
			public void done(List<CollectEntity> collectList, BmobException e)
			{
				if (e == null)
				{
					if (collectList != null)
					{
						callback.onSuccess(collectList);
					}
				} else
				{
					callback.onFail(e.getMessage());
				}
			}
		});
	}

	@Override
	public void getDetails(String id, final CommonCallback callback)
	{
		RetrofitMannager.getInstance().create(DisplayApi.class).getCateDetailsById(Integer.valueOf(
				id), Constant.KEY).distinctUntilChanged().observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io()).subscribe(new BaseSubscriber<Root>()
				{

					@Override
					public void onNext(Root root)
					{
						if (root != null && root.getResult() != null && root.getResult()
								.getData() != null)
						{
							List<Data> dataList = root.getResult().getData();
							callback.onSuccess(dataList);
						} else
						{
							callback.onFail("");
						}
					}

					@Override
					public void onError(Throwable arg0)
					{
						callback.onFail(arg0.getMessage());
					}
				});
		;
	}

}
