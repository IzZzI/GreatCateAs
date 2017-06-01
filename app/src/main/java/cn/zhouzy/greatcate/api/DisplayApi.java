package cn.zhouzy.greatcate.api;

import cn.zhouzy.greatcate.entity.Root;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;


/**
 * 展示界面API
 */
public interface DisplayApi
{
	/**
	 * 获取菜谱列表
	 * @param cid 菜谱ID
	 * @param pn 页码
	 * @param rn 每页数量
	 * @param key
	 * @return
	 */
	@GET("index")
	Observable<Root> getCateList(@Query("cid") int cid, @Query("pn") String pn, @Query("rn") String rn,
								 @Query("key") String key);

	@GET("queryid")
	Observable<Root> getCateDetailsById(@Query("id") int id, @Query("key") String key);

	@GET("query")
	Observable<Root> searchByMenu(@Query("menu") String menu, @Query("pn") String pn, @Query("rn") String rn,
								  @Query("key") String key);
}
