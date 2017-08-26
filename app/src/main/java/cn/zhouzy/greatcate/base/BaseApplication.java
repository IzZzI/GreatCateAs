package cn.zhouzy.greatcate.base;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import cn.bmob.v3.Bmob;
import cn.sharesdk.framework.ShareSDK;

/**
 * Created by zhouzy on 2017/3/9.
 */

public class BaseApplication extends Application
{
	@Override
	public void onCreate()
	{
		super.onCreate();
		ImageLoaderConfiguration configuration = ImageLoaderConfiguration.createDefault(getApplicationContext());
		ImageLoader.getInstance().init(configuration);
		ShareSDK.initSDK(getApplicationContext());
		Bmob.initialize(getApplicationContext(), "f76cd3c3080057cc0ae9918be21256a1");

	}
}
