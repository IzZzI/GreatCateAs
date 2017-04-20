package cn.zhouzy.greatcate.api;

import cn.zhouzy.greatcate.entity.Root;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by gdxw on 2017/3/8.
 */

public interface DisplayApi
{

	@GET("index")
	Observable<Root> getCateList(@Query("cid") int cid, @Query("pn") String pn, @Query("rn") String rn,
								 @Query("key") String key);

	@GET("queryid")
	Observable<Root> getCateDetailsById(@Query("id") int id, @Query("key") String key);

	@GET("query")
	Observable<Root> searchByMenu(@Query("menu") String menu, @Query("pn") String pn, @Query("rn") String rn,
								  @Query("key") String key);
}
