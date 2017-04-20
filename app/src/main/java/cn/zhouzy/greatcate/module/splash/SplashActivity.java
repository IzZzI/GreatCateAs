package cn.zhouzy.greatcate.module.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import cn.zhouzy.greatcate.R;
import cn.zhouzy.greatcate.base.BaseActivity;
import cn.zhouzy.greatcate.module.main.activity.MainActivity;

public class SplashActivity extends BaseActivity
{
	private Handler mHandler = new Handler();

	@Override
	protected void onCreate(Bundle arg0)
	{
		super.onCreate(arg0);
		setContentView(R.layout.activity_splash);
		mHandler.postDelayed(new Runnable()
		{

			@Override
			public void run()
			{
				Intent in = new Intent(SplashActivity.this, MainActivity.class);
				startActivity(in);
				overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
				finish();
			}
		}, 1500);

	}
}
