package cn.zhouzy.greatcate.contract;

import cn.zhouzy.greatcate.common.callback.CommonCallback;
import cn.zhouzy.greatcate.entity.Data;

import java.util.List;

/**
 * Created by zhouzy on 2017/3/8.
 */

public interface DisplayContract
{
	interface IDisplayPresenter
	{
		void getCateList(int cid, String pn, String rn);
		void getMoreCateList(int cid, String pn, String rn);

	}

	interface IDisplayModel
	{
		/**
		 * * 按菜单id分类获取数据
		 *
		 * @param cid
		 * @param pn
		 *            pn 页数
		 * @param rn
		 *            rn 返回数据条数
		 */
		void getCateList(int cid, String pn, String rn, CommonCallback callback);
		void getMoreCateList(int cid, String pn, String rn, CommonCallback callback);
	}

	interface IDisplayView
	{
		//刷新或者第一次加载
		void setCateList(List<Data> cateList);
		//刷新或者第一次加载失败
		void onGetDataFailed(String error);
		//加载成功
		void onGetMoreSuccess(List<Data> cateList);
		//加载失败
		void  onGetMoreFail(String errorMsg);
	}

}
