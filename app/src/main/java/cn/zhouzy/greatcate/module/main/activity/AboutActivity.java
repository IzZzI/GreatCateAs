package cn.zhouzy.greatcate.module.main.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.OnClick;
import cn.zhouzy.greatcate.R;
import cn.zhouzy.greatcate.base.BaseActivity;

public class AboutActivity extends BaseActivity
{
	@Bind(R.id.tv_title)
	TextView mTitleTextView;

	@Override
	protected void onCreate(Bundle arg0)
	{
		super.onCreate(arg0);
		setContentView(R.layout.activity_about);
		initView();
	}

	private void initView()
	{
		mTitleTextView.setText(getResources().getString(R.string.about));
	}

	@OnClick(
	{ R.id.btn_back })
	void OnClick(View v)
	{
		switch (v.getId())
		{
		case R.id.btn_back:
			finish();
			overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
			break;

		default:
			break;
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if (keyCode == KeyEvent.KEYCODE_BACK)
		{
			finish();
			overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
