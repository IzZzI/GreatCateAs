package cn.zhouzy.greatcate.module.login.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.OnClick;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;
import cn.zhouzy.greatcate.R;
import cn.zhouzy.greatcate.base.BaseActivity;
import cn.zhouzy.greatcate.common.constant.Constant;
import cn.zhouzy.greatcate.common.utils.DialogUtil;
import cn.zhouzy.greatcate.common.utils.SharedPreferencesUtil;
import cn.zhouzy.greatcate.common.utils.StrUtil;
import cn.zhouzy.greatcate.common.utils.ToastUtil;
import cn.zhouzy.greatcate.common.view.AutoCleanEditText;
import cn.zhouzy.greatcate.common.view.ProcessButton;
import cn.zhouzy.greatcate.common.view.ProgressGenerator;
import cn.zhouzy.greatcate.contract.LoginContract;
import cn.zhouzy.greatcate.module.login.presenter.LoginPresenter;

public class LoginActivity extends BaseActivity implements LoginContract.ILoginVew
{
	@Bind(R.id.et_login_username)
	AutoCleanEditText mUsernameEditText;
	@Bind(R.id.et_login_password)
	AutoCleanEditText mPasswordEditText;
	@Bind(R.id.btn_login_login)
	ProcessButton mLoginButton;
	@Bind(R.id.tv_title)
	TextView mTitleTextView;
	@Bind(R.id.rl_title_include_container)
	RelativeLayout mTitleContainerRelativeLayout;
	private static final int THIRD_PARTY_LOGIN_QQ = 0;
	private static final int THIRD_PARTY_LOGIN_WECHAT = 1;
	private static final int THIRD_PARTY_LOGIN_SINAWEIBO = 2;
	private LoginContract.ILoginPresenter mLoginPresenter;
	private ProgressGenerator mProgressGenerator;
	private Handler mHandler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initView();
		initData();
	}

	private void initView()
	{
		mTitleTextView.setText(getResources().getString(R.string.login));
		mTitleContainerRelativeLayout.getBackground().setAlpha(255);
		mProgressGenerator = new ProgressGenerator(mLoginButton);
	}

	private void initData()
	{
		mLoginPresenter = new LoginPresenter(this);
		SharedPreferencesUtil sp = new SharedPreferencesUtil(this);
		String mUsername = sp.getString(Constant.SP_KEY_USERNAME);
		String mPassword = sp.getString(Constant.SP_KEY_PASSWORD);
		if (!StrUtil.strIsNullOrEmpty(mUsername))
		{
			mUsernameEditText.setMessage(mUsername);
			mPasswordEditText.requestFocus();
			if (!StrUtil.strIsNullOrEmpty(mPassword))
			{
				mPasswordEditText.setMessage(mPassword);
			}
		}

	}

	@OnClick(
	{ R.id.ll_login_third_party_qq, R.id.ll_login_third_party_wechat, R.id.ll_login_third_party_sinaweibo,
	        R.id.btn_login_register, R.id.btn_login_login, R.id.btn_back })
	void OnClick(View v)
	{
		switch (v.getId())
		{
		case R.id.ll_login_third_party_qq:
			thirdPartyLogin(THIRD_PARTY_LOGIN_QQ);
			break;
		case R.id.ll_login_third_party_wechat:
			thirdPartyLogin(THIRD_PARTY_LOGIN_WECHAT);
			break;
		case R.id.ll_login_third_party_sinaweibo:
			thirdPartyLogin(THIRD_PARTY_LOGIN_SINAWEIBO);
			break;
		case R.id.btn_login_register:
			register();
			break;
		case R.id.btn_login_login:
			login();
			break;
		case R.id.btn_back:
			finish();
			overridePendingTransition(R.anim.anim_alpha, R.anim.anim_close);
			break;

		default:
			break;
		}
	}

	private void login()
	{
		if (mProgressGenerator.isRunning())
		{
			return;
		}
		final String mUsername = mUsernameEditText.getText().toString().trim();
		final String mPassword = mPasswordEditText.getText().toString().trim();
		if ("".equals(mUsername))
		{
			ToastUtil.showToast(this, getResources().getString(R.string.username_is_empty));
		} else if ("".equals(mPassword))
		{
			ToastUtil.showToast(this, getResources().getString(R.string.password_is_empty));
		} else
		{
			SharedPreferencesUtil sp = new SharedPreferencesUtil(this);
			sp.putString(Constant.SP_KEY_USERNAME, mUsername);
			sp.putString(Constant.SP_KEY_PASSWORD, mPassword);
			mProgressGenerator.start();
			mHandler.postDelayed(new Runnable()
			{

				@Override
				public void run()
				{
					mLoginPresenter.login(mUsername, mPassword);
				}
			}, 700);

		}

	}

	private void register()
	{
		// 注册成功应该直接进入主页 登录界面应该销毁
		Intent in = new Intent(this, RegisterActivity.class);
		startActivityForResult(in, Constant.REQUESTCODE_REGISTER);
	}

	private void thirdPartyLogin(int thirdPartyLoginFlag)
	{
		String name = "";
		switch (thirdPartyLoginFlag)
		{
		case THIRD_PARTY_LOGIN_QQ:
			name = QQ.NAME;
			break;
		case THIRD_PARTY_LOGIN_WECHAT:
			name = Wechat.NAME;
			break;
		case THIRD_PARTY_LOGIN_SINAWEIBO:
			name = SinaWeibo.NAME;
			break;
		default:
			break;
		}
		DialogUtil.showLoadDialog(this, R.mipmap.xsearch_loading, getResources().getString(R.string.loading));
		mLoginPresenter.thirdPartyLogin(name);

	}

	@Override
	public void onLoginSuccessed(Object object)
	{
		DialogUtil.removeDialog(this);
		ToastUtil.showToast(this, getResources().getString(R.string.login_success));
		mProgressGenerator.onSuccessed();
		setResult(RESULT_OK);
		finish();
		overridePendingTransition(R.anim.anim_alpha, R.anim.anim_close);
	}

	@Override
	public void onLoginFailed(String errorMsg)
	{
		DialogUtil.removeDialog(this);
		mProgressGenerator.onError();
		ToastUtil.showToast(this, errorMsg);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK && requestCode == Constant.REQUESTCODE_REGISTER)
		{
			setResult(RESULT_OK);
			finish();
			overridePendingTransition(R.anim.anim_alpha, R.anim.anim_close);
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if (keyCode == KeyEvent.KEYCODE_BACK)
		{
			finish();
			overridePendingTransition(R.anim.anim_alpha, R.anim.anim_close);
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onThirdPartyLoginCancle()
	{
		DialogUtil.removeDialog(this);
	}

}
