package cn.zhouzy.greatcate.common.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil
{
	private static Toast toast = null;

	public static void showToast(Context mContext, String content)
	{
		if (toast == null)
		{
			toast = Toast.makeText(mContext, content, Toast.LENGTH_SHORT);
		} else
		{
			toast.setText(content);
			toast.setDuration(Toast.LENGTH_SHORT);
		}
		toast.show();
	}

	public static void showToast(Context mContext, int content)
	{
		if (toast == null)
		{
			toast = Toast.makeText(mContext, content + "", Toast.LENGTH_SHORT);
		} else
		{

			toast.setText(content + "");
			toast.setDuration(Toast.LENGTH_SHORT);
		}
		toast.show();
	}

	public static void showToast(final Context mContext, final String content, boolean flag)
	{
		if (flag)
		{
			new Thread(new Runnable()
			{

				@Override
				public void run()
				{
					Toast.makeText(mContext, content, Toast.LENGTH_SHORT).show();
				}
			}).run();
		}
	}
}
