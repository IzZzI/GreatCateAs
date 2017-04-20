package cn.zhouzy.greatcate.common.mannager;

import cn.zhouzy.greatcate.common.constant.Constant;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by gdxw on 2017/3/8.
 */

public class RetrofitMannager
{

    private static Retrofit retrofit;

    public static Retrofit getInstance()
    {
        if (retrofit == null)
        {
            retrofit = new Retrofit.Builder().baseUrl(Constant.BaseUrl).addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
        }

        return retrofit;

    }

}
