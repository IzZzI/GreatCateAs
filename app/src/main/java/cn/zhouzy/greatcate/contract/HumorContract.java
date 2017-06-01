package cn.zhouzy.greatcate.contract;

import java.util.List;

import cn.zhouzy.greatcate.common.callback.CommonCallback;
import cn.zhouzy.greatcate.entity.Post;

/**
 * Created by 正义 on 2017/5/7.
 */

public interface HumorContract
{
	interface IHumorPresenter
	{
		void getAllHumorList(int pageNo, int pageSize);

		void reqLikeOrUnLike(String userName, String postId);
	}

	interface IHumorModel
	{
		void getAllHumorList(int pageNo, int pageSize, CommonCallback callback);

		void reqLikeOrUnLike(String userName, String postId, CommonCallback callback);
	}

	interface IHumorView
	{
		void onGetAllHumorListSuccessed(List<Post> postList);

		void onGetAllHumorListErrored(String errorMsg);
		void onLikeSuccessed();
		void onUnLikeSuccessed();
		void onLikeOrUnLikeErrorred(String errorMsg);


	}

}
