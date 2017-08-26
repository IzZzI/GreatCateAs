package cn.zhouzy.greatcate.module.setting.activity;

import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.zhouzy.greatcate.R;
import cn.zhouzy.greatcate.base.BaseActivity;
import cn.zhouzy.greatcate.common.constant.Constant;
import cn.zhouzy.greatcate.common.utils.StrUtil;
import cn.zhouzy.greatcate.common.utils.ToastUtil;
import cn.zhouzy.greatcate.common.view.CircleImageView;
import cn.zhouzy.greatcate.entity.User;
import cn.zhouzy.greatcate.module.login.activity.LoginActivity;
import cn.zhouzy.greatcate.module.main.activity.AboutActivity;
import cn.zhouzy.greatcate.module.main.activity.UserInfoActivity;

public class SettingActivity extends BaseActivity
{
	@Bind(R.id.tv_title)
	TextView mTitleTextView;
	@Bind(R.id.rl_title_include_container)
	RelativeLayout mTitleContainerRelativeLayout;
	@Bind(R.id.civ_setting_head_portrait)
	CircleImageView mHeadPortraitCircleImageView;
	@Bind(R.id.btn_setting_login_out)
	Button mLoginOutButton;
	private User mUser;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		initView();
		initData();
	}

	private void initData()
	{
		mUser = BmobUser.getCurrentUser(User.class);
		if (mUser != null)
		{
			mLoginOutButton.setVisibility(View.VISIBLE);
			String mHeadProtraitUrl = mUser.getIcon();
			if (!StrUtil.strIsNullOrEmpty(mHeadProtraitUrl))
			{
				ImageLoader.getInstance().displayImage(mHeadProtraitUrl,
						mHeadPortraitCircleImageView);
			} else
			{
				mHeadPortraitCircleImageView.setImageResource(R.mipmap.head_portrait);
			}
		} else
		{
			mLoginOutButton.setVisibility(View.GONE);
		}
	}

	private void initView()
	{
		mTitleTextView.setText(getResources().getString(R.string.setting));
		mTitleContainerRelativeLayout.getBackground().setAlpha(255);
	}

	@OnClick(
	{ R.id.btn_back, R.id.rl_setting_userinfo, R.id.rl_setting_about, R.id.rl_setting_clear_cache,
			R.id.rl_setting_check_for_updates,
			 R.id.btn_setting_login_out })
	void OnClick(View v)
	{
		switch (v.getId())
		{
		case R.id.btn_back:
			setResult(RESULT_OK);
			finish();
			break;
		case R.id.rl_setting_userinfo:
			toUserInfo();
			break;
		case R.id.rl_setting_about:
			toAbout();
			break;
		case R.id.rl_setting_clear_cache:
			clearCache();
			break;
		case R.id.rl_setting_check_for_updates:
			checkForUpdates();
			break;
		case R.id.btn_setting_login_out:
			loginOut();
			break;
		default:
			break;
		}
	}

	private void loginOut()
	{
		BmobUser.logOut();
		refresh();
		ToastUtil.showToast(this, getResources().getString(R.string.login_out_successed));
	}

	private void refresh()
	{
		mUser = BmobUser.getCurrentUser(User.class);
		if (mUser != null)
		{
			mLoginOutButton.setVisibility(View.VISIBLE);
			String mHeadProtraitUrl = mUser.getIcon();
			if (!StrUtil.strIsNullOrEmpty(mHeadProtraitUrl))
			{
				ImageLoader.getInstance().displayImage(mHeadProtraitUrl,
						mHeadPortraitCircleImageView);
			} else
			{
				mHeadPortraitCircleImageView.setImageResource(R.mipmap.head_portrait);
			}
		} else
		{
			mLoginOutButton.setVisibility(View.GONE);
			mHeadPortraitCircleImageView.setImageResource(R.mipmap.head_portrait);
		}
	}


	private void checkForUpdates()
	{
		ToastUtil.showToast(this, getResources().getString(R.string.latest_version));
	}

	private void feedBack()
	{
		ToastUtil.showToast(this, getResources().getString(R.string.feedback));
	}

	private void clearCache()
	{
		ToastUtil.showToast(this, "清理成功,清理了518KB缓存");

	}

	private void toAbout()
	{
		Intent in = new Intent(this, AboutActivity.class);
		startActivity(in);
		overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
	}

	private void toUserInfo()
	{
		Intent in = new Intent();
		if (mUser != null)
		{
			// 已登录直接起收藏界面
			in.setClass(this, UserInfoActivity.class);
			startActivity(in);
		} else
		{
			// 未登录起登录界面
			in.setClass(this, LoginActivity.class);
			startActivityForResult(in, Constant.REQUESTCODE_LOGIN);
			overridePendingTransition(R.anim.anim_open, R.anim.anim_alpha);
		}

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if (keyCode == KeyEvent.KEYCODE_BACK)
		{
			setResult(RESULT_OK);
			finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK && (requestCode == Constant.REQUESTCODE_LOGIN
				|| requestCode == Constant.REQUESTCODE_USERINFO
				|| requestCode == Constant.REQUESTCODE_SETTING))
		{
			refresh();
		}
	}

}
