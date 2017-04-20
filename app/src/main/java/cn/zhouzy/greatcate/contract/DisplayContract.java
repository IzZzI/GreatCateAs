package cn.zhouzy.greatcate.contract;

import cn.zhouzy.greatcate.common.callback.CommonCallback;
import cn.zhouzy.greatcate.entity.Data;

import java.util.List;

/**
 * Created by gdxw on 2017/3/8.
 */

public interface DisplayContract
{
	interface IDisplayPresenter
	{
		void getCateList(int cid, String pn, String rn);
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
	}

	interface IDisplayView
	{
		void setCateList(List<Data> cateList);

		void onGetDataFailed(String error);
	}

}
