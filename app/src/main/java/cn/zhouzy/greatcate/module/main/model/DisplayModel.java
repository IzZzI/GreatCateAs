package cn.zhouzy.greatcate.module.main.model;

import cn.zhouzy.greatcate.api.DisplayApi;
import cn.zhouzy.greatcate.common.callback.CommonCallback;
import cn.zhouzy.greatcate.common.constant.Constant;
import cn.zhouzy.greatcate.common.mannager.RetrofitMannager;
import cn.zhouzy.greatcate.contract.DisplayContract;
import cn.zhouzy.greatcate.entity.Root;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by gdxw on 2017/3/8.
 */

public class DisplayModel implements DisplayContract.IDisplayModel
{

	@Override
	public void getCateList(int cid, String pn, String rn, final CommonCallback callback)
	{
		RetrofitMannager.getInstance().create(DisplayApi.class).getCateList(cid, pn, rn,
				Constant.KEY).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Subscriber<Root>()
				{
					@Override
					public void onCompleted()
					{

					}

					@Override
					public void onError(Throwable e)
					{
						callback.onFail(e.getMessage());
					}

					@Override
					public void onNext(Root root)
					{
						if (root != null && root.getResult() != null)
						{

							callback.onSuccess(root.getResult().getData());

						}
					}
				});
	}
}
