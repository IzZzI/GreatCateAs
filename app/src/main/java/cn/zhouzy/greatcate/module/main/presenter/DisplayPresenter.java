package cn.zhouzy.greatcate.module.main.presenter;

import java.util.List;

import cn.zhouzy.greatcate.common.callback.CommonCallback;
import cn.zhouzy.greatcate.contract.DisplayContract;
import cn.zhouzy.greatcate.entity.Data;
import cn.zhouzy.greatcate.module.main.model.DisplayModel;

/**
 * Created by gdxw on 2017/3/9.
 */

public class DisplayPresenter implements DisplayContract.IDisplayPresenter
{
	private DisplayContract.IDisplayModel mDisplayModel;
	private DisplayContract.IDisplayView mDisplayView;

	public DisplayPresenter(DisplayContract.IDisplayView displayView)
	{
		this.mDisplayView = displayView;
		this.mDisplayModel = new DisplayModel();
	}

	@Override
	public void getCateList(int cid, String pn, String rn)
	{

		CommonCallback callback = new CommonCallback()
		{
			@SuppressWarnings("unchecked")
            @Override
			public void onSuccess(Object success)
			{
				mDisplayView.setCateList((List<Data>) success);
			}

			@Override
			public void onFail(Object fail)
			{
				mDisplayView.onGetDataFailed((String) fail);
			}
		};
		mDisplayModel.getCateList(cid, pn, rn, callback);
	}

	@Override
	public void getMoreCateList(int cid, String pn, String rn)
	{
		CommonCallback callback = new CommonCallback()
		{
			@SuppressWarnings("unchecked")
			@Override
			public void onSuccess(Object success)
			{
				mDisplayView.onGetMoreSuccess((List<Data>) success);
			}

			@Override
			public void onFail(Object fail)
			{
				mDisplayView.onGetMoreFail((String) fail);
			}
		};
		mDisplayModel.getMoreCateList(cid, pn, rn, callback);
	}
}
