package cn.zhouzy.greatcate.module.main.model;

import java.util.List;

import cn.zhouzy.greatcate.api.DisplayApi;
import cn.zhouzy.greatcate.base.BaseSubscriber;
import cn.zhouzy.greatcate.common.callback.CommonCallback;
import cn.zhouzy.greatcate.common.constant.Constant;
import cn.zhouzy.greatcate.common.mannager.RetrofitMannager;
import cn.zhouzy.greatcate.contract.SearchContract;
import cn.zhouzy.greatcate.entity.Data;
import cn.zhouzy.greatcate.entity.Root;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SearchModel implements SearchContract.ISearchModel
{

	@Override
	public void searchByMenu(String name, String pn, String rn, final CommonCallback callback)
	{
		RetrofitMannager.getInstance().create(DisplayApi.class).searchByMenu(name, pn, rn, Constant.KEY)
		        .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).distinctUntilChanged()
		        .subscribe(new BaseSubscriber<Root>()
		        {
			        public void onNext(Root root)
			        {
				        if (root != null && root.getResult() != null && root.getResult().getData() != null)
				        {
					        List<Data> data = root.getResult().getData();
					        callback.onSuccess(data);
				        } else
				        {
					        callback.onFail(root.getReason());
				        }
			        }

			        @Override
			        public void onError(Throwable arg0)
			        {
				        super.onError(arg0);
				        callback.onFail(arg0.getMessage());
			        }
		        });
	}

}
