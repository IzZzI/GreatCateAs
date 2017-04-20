package cn.zhouzy.greatcate.module.login.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.OnClick;
import cn.zhouzy.greatcate.R;
import cn.zhouzy.greatcate.base.BaseActivity;
import cn.zhouzy.greatcate.common.utils.StrUtil;
import cn.zhouzy.greatcate.common.utils.ToastUtil;
import cn.zhouzy.greatcate.contract.RegisterContract;
import cn.zhouzy.greatcate.module.login.presenter.RegisterPresenter;

public class RegisterActivity extends BaseActivity implements RegisterContract.IRegisterView
{
	@Bind(R.id.btn_register_register)
	Button mRegisterButton;
	@Bind(R.id.et_register_username)
	EditText mUserNameEditText;
	@Bind(R.id.et_register_password)
	EditText mPasswordEditText;
	@Bind(R.id.et_register_password_sure)
	EditText mSurePasswordEditText;
	@Bind(R.id.et_register_email)
	EditText mEmailEditText;
	@Bind(R.id.tv_title)
	TextView mTitleTextView;
	@Bind(R.id.rl_title_include_container)
	RelativeLayout mTitleContainerRelativeLayout;
	private RegisterContract.IRegisterPresenter mRegisterPresenter;

	@Override
	protected void onCreate(Bundle arg0)
	{
		super.onCreate(arg0);
		setContentView(R.layout.activity_register);
		initView();
		initData();
		initListener();
	}

	private void initData()
	{
		mRegisterPresenter = new RegisterPresenter(this);

	}

	private void initView()
	{
		mTitleTextView.setText(getResources().getString(R.string.register));
		mTitleContainerRelativeLayout.getBackground().setAlpha(255);
	}

	private void initListener()
	{

	}

	@OnClick(
	{ R.id.btn_register_register, R.id.btn_back })
	void OnClick(View v)
	{
		switch (v.getId())
		{
		case R.id.btn_back:
			finish();
			break;
		case R.id.btn_register_register:
			register();
			break;
		default:
			break;
		}

	}

	private void register()
	{
		String mUsername = mUserNameEditText.getText().toString().trim();
		if ("".equals(mUsername))
		{
			ToastUtil.showToast(this, getResources().getString(R.string.username_is_empty));
			return;
		}
		String mPassword = mPasswordEditText.getText().toString().trim();
		if ("".equals(mPassword))
		{
			ToastUtil.showToast(this, getResources().getString(R.string.password_is_empty));
			return;
		}
		String mSurePassword = mSurePasswordEditText.getText().toString().trim();
		if (!mPassword.equals(mSurePassword))
		{
			ToastUtil.showToast(this, getResources().getString(R.string.password_diffier));
			return;
		}
		String mEmail = mEmailEditText.getText().toString().trim();
		if (!"".equals(mEmail))
		{
			if (!StrUtil.isEmail(mEmail))
			{
				ToastUtil.showToast(this, getResources().getString(R.string.email_illegal));
				return;
			}
		}

		mRegisterPresenter.register(mUsername, mPassword, mEmail);

	}

	@Override
	public void onRegisterSuccessed()
	{
		ToastUtil.showToast(this, getResources().getString(R.string.register_successed));
		setResult(RESULT_OK);
		finish();
	}

	@Override
	public void onRegisterFailed(String errorMsg)
	{
		ToastUtil.showToast(this, errorMsg);
	}

}
