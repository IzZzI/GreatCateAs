package cn.zhouzy.greatcate.module.main.presenter;

import java.util.List;

import cn.zhouzy.greatcate.common.callback.CommonProgressCallback;
import cn.zhouzy.greatcate.contract.UploadContract;
import cn.zhouzy.greatcate.entity.ImageBean;
import cn.zhouzy.greatcate.module.main.model.UploadModel;

/**
 * Created by 正义 on 2017/5/5.
 */

public class UploadPresenter implements UploadContract.IUploadPresenter
{
	private UploadContract.IUploadModel mUploadModel;
	private UploadContract.IUploadView mUploadView;

	public UploadPresenter(UploadContract.IUploadView mUploadView)
	{
		this.mUploadView = mUploadView;
		this.mUploadModel = new UploadModel();
	}

	@Override
	public void upload(String userName, String contentTxt, String headPortrait, List<ImageBean> dataList)
	{
		mUploadModel.upload(userName, contentTxt, headPortrait, dataList, new CommonProgressCallback()
		{
			@Override
			public void onSuccess(Object success)
			{
				mUploadView.onUploadSuccessed();
			}

			@Override
			public void onFail(Object fail)
			{
				mUploadView.onUploadErrored((String) fail);
			}

			@Override
			public void onProgress(int progress)
			{
				mUploadView.onUploadProgress(progress);
			}
		});
	}
}
