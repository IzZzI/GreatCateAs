package cn.zhouzy.greatcate.common.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import cn.zhouzy.greatcate.common.constant.Constant;

@SuppressLint("CommitPrefEdits")
public class SharedPreferencesUtil
{
	private SharedPreferences mSharedPreferences;
	private Editor mEditor;

	public SharedPreferencesUtil(Context context)
	{
		mSharedPreferences = context.getSharedPreferences(Constant.USER_INFO, Context.MODE_PRIVATE);
		mEditor = mSharedPreferences.edit();
	}

	public void putString(String key, String value)
	{
		mEditor.putString(key, value);
		mEditor.commit();
	};

	public String getString(String key)
	{
		return mSharedPreferences.getString(key, "");
	}

	public void putInt (String key,int  value)
	{
		mEditor.putInt(key,value);
		mEditor.commit();
	}
	public  int getInt(String key)
	{
		return  mSharedPreferences.getInt(key,0);
	}


}
