package cn.zhouzy.greatcate.base;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;

import cn.bmob.v3.Bmob;
import cn.sharesdk.framework.ShareSDK;

/**
 * Created by zhouzy on 2017/3/9.
 */

public class BaseApplication extends Application
{

	/**
	 * 图片Options
	 */
	private static  DisplayImageOptions options;

	@Override
	public void onCreate()
	{
		super.onCreate();
		ImageLoaderConfiguration configuration = ImageLoaderConfiguration.createDefault(getApplicationContext());
		ImageLoader.getInstance().init(configuration);
		ShareSDK.initSDK(getApplicationContext());
		Bmob.initialize(getApplicationContext(), "f76cd3c3080057cc0ae9918be21256a1");
		options = new DisplayImageOptions.Builder()
				.cacheInMemory(true)
				.cacheOnDisk(true)
				.bitmapConfig(Bitmap.Config.RGB_565)
				.build();
	}

	public static DisplayImageOptions getOptions()
	{
		return options;
	}

	public static void setOptions(DisplayImageOptions options)
	{
		BaseApplication.options = options;
	}
}
