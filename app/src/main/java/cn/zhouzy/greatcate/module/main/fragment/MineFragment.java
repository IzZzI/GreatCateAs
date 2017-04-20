package cn.zhouzy.greatcate.module.main.fragment;

import com.nostra13.universalimageloader.core.ImageLoader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.zhouzy.greatcate.R;
import cn.zhouzy.greatcate.base.BaseFragment;
import cn.zhouzy.greatcate.common.constant.Constant;
import cn.zhouzy.greatcate.common.utils.StrUtil;
import cn.zhouzy.greatcate.common.utils.ToastUtil;
import cn.zhouzy.greatcate.common.view.CircleImageView;
import cn.zhouzy.greatcate.entity.User;
import cn.zhouzy.greatcate.module.collect.activity.CollectActivity;
import cn.zhouzy.greatcate.module.login.activity.LoginActivity;
import cn.zhouzy.greatcate.module.main.activity.UserInfoActivity;
import cn.zhouzy.greatcate.module.setting.activity.SettingActivity;

public class MineFragment extends BaseFragment
{

	@Bind(R.id.tv_mine_name)
	TextView mNickNameTextView;
	@Bind(R.id.civ_mine_head_portrait)
	CircleImageView mHeadPortraitImageView;
	private boolean isCreateView;
	private boolean isLoadData;
	private ImageLoader mImageLoader;
	private User mUser;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		setContentView(inflater, container, R.layout.fragment_mine);
		initView();
		isCreateView = true;
		return super.onCreateView(inflater, container, savedInstanceState);

	}

	private void initView()
	{
		mImageLoader = ImageLoader.getInstance();
	}

	public static MineFragment newInstance()
	{
		MineFragment mMineFragment = new MineFragment();
		return mMineFragment;
	}

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser)
	{
		super.setUserVisibleHint(isVisibleToUser);
		if (isVisibleToUser && isCreateView) // 对用户可见并且经过了onCreateView生命周期后才加载数据
		{
			if (!isLoadData)
			{
				lazyLoad(); // 延迟加载
			} else
			{
			}
		}
	}

	private void refresh()
	{
		mUser = BmobUser.getCurrentUser(User.class);
		if (mUser != null)
		{
			String mNickName = mUser.getNickName();
			String mIcon = mUser.getIcon();
			String mUsername = mUser.getUsername();
			if (!StrUtil.strIsNullOrEmpty(mUsername))
			{
				mNickNameTextView.setText(mUsername);
			}
			if (!StrUtil.strIsNullOrEmpty(mNickName))
			{
				mNickNameTextView.setText(mNickName);
			}
			if (!StrUtil.strIsNullOrEmpty(mIcon))
			{
				mImageLoader.displayImage(mIcon, mHeadPortraitImageView);
			}
		} else
		{
			mHeadPortraitImageView.setImageResource(R.mipmap.head_portrait);
			mNickNameTextView.setText(getResources().getString(R.string.hint_login_or_register));
		}
	}

	// 延迟加载
	private void lazyLoad()
	{
		initData();
		isLoadData = true;
	}

	// 初始化数据
	private void initData()
	{
		mUser = BmobUser.getCurrentUser(User.class);
		if (mUser != null)
		{
			String mNickName = mUser.getNickName();
			String mIcon = mUser.getIcon();
			String mUsername = mUser.getUsername();
			if (!StrUtil.strIsNullOrEmpty(mUsername))
			{
				mNickNameTextView.setText(mUsername);
			}
			if (!StrUtil.strIsNullOrEmpty(mNickName))
			{
				mNickNameTextView.setText(mNickName);
			}
			if (!StrUtil.strIsNullOrEmpty(mIcon))
			{
				mImageLoader.displayImage(mIcon, mHeadPortraitImageView);
			}
		}
	}

	@OnClick(
	{ R.id.rl_mine_collect, R.id.rl_mine_setting, R.id.rl_mine_contact, R.id.rl_mine_info,
	        R.id.ll_mine_head_portrait_controller })
	void OnClick(View v)
	{
		switch (v.getId())
		{
		case R.id.rl_mine_collect:
			toCollect();
			break;
		case R.id.rl_mine_setting:
			toSetting();
			break;
		case R.id.rl_mine_info:
			toUserInfo();
			break;
		case R.id.rl_mine_contact:
			ToastUtil.showToast(getActivity(), getResources().getString(R.string.contact_us));
			break;
		case R.id.ll_mine_head_portrait_controller:
			toUserInfo();
			break;

		default:
			break;
		}
	}

	private void toUserInfo()
	{
		Intent in = new Intent();
		if (mUser != null)
		{
			// 已登录直接起收藏界面
			in.setClass(getActivity(), UserInfoActivity.class);
			startActivityForResult(in, Constant.REQUESTCODE_USERINFO);
		} else
		{
			// 未登录起登录界面
			in.setClass(getActivity(), LoginActivity.class);
			startActivityForResult(in, Constant.REQUESTCODE_LOGIN);
			getActivity().overridePendingTransition(R.anim.anim_open, R.anim.anim_alpha);
		}

	}

	private void toSetting()
	{
		Intent in = new Intent(getActivity(), SettingActivity.class);
		startActivityForResult(in, Constant.REQUESTCODE_SETTING);
	}

	private void toCollect()
	{
		Intent in = new Intent();
		if (mUser != null)
		{
			// 已登录直接起收藏界面
			in.setClass(getActivity(), CollectActivity.class);
			startActivity(in);
		} else
		{
			// 未登录起登录界面
			in.setClass(getActivity(), LoginActivity.class);
			startActivityForResult(in, Constant.REQUESTCODE_LOGIN);
			getActivity().overridePendingTransition(R.anim.anim_open, R.anim.anim_alpha);
		}

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == Activity.RESULT_OK && (requestCode == Constant.REQUESTCODE_LOGIN
		        || requestCode == Constant.REQUESTCODE_SETTING || requestCode == Constant.REQUESTCODE_USERINFO))
		{
			refresh();
		}
	}

}
