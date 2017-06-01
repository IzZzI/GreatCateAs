package cn.zhouzy.greatcate.contract;

import java.util.List;

import cn.zhouzy.greatcate.common.callback.CommonCallback;
import cn.zhouzy.greatcate.common.callback.CommonProgressCallback;
import cn.zhouzy.greatcate.entity.ImageBean;

/**
 * Created by 正义 on 2017/5/3.
 */

public interface UploadContract
{
	interface IUploadPresenter
	{
		/**
		 * 上传
		 *
		 * @param userName   用户名
		 * @param contentTxt 上传文字内容
		 * @param dataList   上传图片集合
		 */
		void upload(String userName, String contentTxt, String headPortrait, List<ImageBean> dataList);
	}

	interface IUploadModel
	{
		/**
		 * 上传
		 *
		 * @param userName   用户名
		 * @param contentTxt 上传文字内容
		 * @param dataList   上传图片集合
		 */
		void upload(String userName, String contentTxt, String headPortrait, List<ImageBean> dataList, CommonProgressCallback callback);
	}

	interface IUploadView
	{
		void onUploadSuccessed();

		void onUploadErrored(String errorMsg);

		void onUploadProgress(int progress);

	}


}
