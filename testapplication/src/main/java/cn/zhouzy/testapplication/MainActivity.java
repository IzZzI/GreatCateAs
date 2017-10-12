package cn.zhouzy.testapplication;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{

	private TextView mTextView;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.e("test","onCreate");
		mTextView =(TextView) findViewById(R.id.textview);
		mTextView.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent in = new Intent(MainActivity.this, SecondActivity.class);
				startActivity(in);
			}
		});
	}
	@Override
	protected void onStart()
	{
		super.onStart();
		Log.e("test","onStart");
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		Log.e("test","onResume");
	}

	@Override
	protected void onPause()
	{
		super.onPause();
		Log.e("test","onPause");
	}

	@Override
	protected void onStop()
	{
		super.onStop();
		Log.e("test","onStop");
	}

	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		Log.e("test","onDestroy");
	}

	@Override
	protected void onRestart()
	{
		super.onRestart();
		Log.e("test","onRestart");
	}
}
