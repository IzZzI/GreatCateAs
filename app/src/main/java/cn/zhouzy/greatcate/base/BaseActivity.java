package cn.zhouzy.greatcate.base;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import butterknife.ButterKnife;

/**
 * Created by zhouzy on 2017/3/8.
 */

public class BaseActivity extends FragmentActivity
{

	@Override
	public void setContentView(int layoutResID)
	{
		super.setContentView(layoutResID);
		ButterKnife.bind(this);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
	}

}
