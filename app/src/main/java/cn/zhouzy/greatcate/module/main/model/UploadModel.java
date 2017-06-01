package cn.zhouzy.greatcate.module.main.model;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadBatchListener;
import cn.zhouzy.greatcate.common.callback.CommonProgressCallback;
import cn.zhouzy.greatcate.common.utils.DateUtil;
import cn.zhouzy.greatcate.contract.UploadContract;
import cn.zhouzy.greatcate.entity.ImageBean;
import cn.zhouzy.greatcate.entity.Post;

/**
 * Created by 正义 on 2017/5/3.
 */

public class UploadModel implements UploadContract.IUploadModel
{
	@Override
	public void upload(final String userName, final String contentTxt,String headPortrait ,List<ImageBean> dataList, final CommonProgressCallback callback)
	{
		if (dataList != null && dataList.size() > 0)
		{
			final String[] imageArray = new String[dataList.size()];
			for (int i = 0; i < dataList.size(); i++)
			{
				imageArray[i] = dataList.get(i).getImageShowPickPath();
			}
			Bmob.uploadBatch(imageArray, new UploadBatchListener()
			{
				@Override
				public void onSuccess(List<BmobFile> list, List<String> urls)
				{
					if (urls.size() == imageArray.length)
					{
						Post mPost = new Post();
						mPost.setImgUrls(urls);
						mPost.setAuthor(userName);
						mPost.setTime(DateUtil.getCurTimeString());
						mPost.setContent(contentTxt);
						mPost.save(new SaveListener<String>()
						{


							@Override
							public void done(String objectId, BmobException e)
							{
								if (e == null)
								{
									callback.onSuccess("");
								} else
								{
									callback.onFail(e.getMessage());
								}
							}
						});

					}
				}

				@Override
				public void onProgress(int curIndex, int curPercent, int total, int totalPercent)
				{
					callback.onProgress(totalPercent);
				}

				@Override
				public void onError(int i, String s)
				{
					callback.onFail(s);
				}
			});
		} else
		{
			Post mPost = new Post();
			mPost.setAuthor(userName);
			mPost.setTime(DateUtil.getCurTimeString());
			mPost.setContent(contentTxt);
			mPost.save(new SaveListener<String>()
			{


				@Override
				public void done(String objectId, BmobException e)
				{
					if (e == null)
					{
						callback.onSuccess("");
					} else
					{
						callback.onFail(e.getMessage());
					}
				}
			});
		}
	}
}
