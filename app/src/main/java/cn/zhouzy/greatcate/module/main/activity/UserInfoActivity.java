package cn.zhouzy.greatcate.module.main.activity;

import java.io.File;

import com.nostra13.universalimageloader.core.ImageLoader;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.zhouzy.greatcate.R;
import cn.zhouzy.greatcate.base.BaseActivity;
import cn.zhouzy.greatcate.base.BaseDialogFragment;
import cn.zhouzy.greatcate.common.constant.Constant;
import cn.zhouzy.greatcate.common.utils.DialogUtil;
import cn.zhouzy.greatcate.common.utils.FileUtil;
import cn.zhouzy.greatcate.common.utils.StrUtil;
import cn.zhouzy.greatcate.common.utils.ToastUtil;
import cn.zhouzy.greatcate.common.view.CircleImageView;
import cn.zhouzy.greatcate.common.view.pullview.CommPullToRefreshView;
import cn.zhouzy.greatcate.common.view.pullview.CommPullToRefreshView.OnHeaderRefreshListener;
import cn.zhouzy.greatcate.contract.UserInfoContract;
import cn.zhouzy.greatcate.entity.User;
import cn.zhouzy.greatcate.module.main.presenter.UserInfoPresenter;

public class UserInfoActivity extends BaseActivity implements UserInfoContract.IUserInfoView
{
	@Bind(R.id.tv_title)
	TextView mTitleTextView;
	@Bind(R.id.rl_title_include_container)
	RelativeLayout mTitleContainerRelativeLayout;
	@Bind(R.id.tv_user_info_account)
	TextView mAccountTextView;
	@Bind(R.id.tv_user_info_nickname)
	TextView mNicknameTextView;
	@Bind(R.id.tv_user_info_profile)
	TextView mProfileTextView;
	@Bind(R.id.civ_user_info_head_portrait)
	CircleImageView mHeadPortraitCircleImageView;
	@Bind(R.id.tv_user_info_email)
	TextView mEmailTextView;
	@Bind(R.id.refreshview_user_info_pulltorefreshview)
	CommPullToRefreshView mCommPullToRefreshView;
	private User mUser;
	private UserInfoContract.IUserInfoPresenter mUserInfoPresenter;
	private BaseDialogFragment mDialog;
	private Uri mHeadProtraitUri;

	@Override
	protected void onCreate(Bundle arg0)
	{
		super.onCreate(arg0);
		setContentView(R.layout.activity_user_info);
		initView();
		initData();
		initListener();
	}

	private void initListener()
	{
		mCommPullToRefreshView.setOnHeaderRefreshListener(new OnHeaderRefreshListener()
		{
			@Override
			public void onHeaderRefresh(CommPullToRefreshView view)
			{
				mCommPullToRefreshView.onHeaderRefreshFinish();
			}
		});
	}

	private void initData()
	{
		mUser = BmobUser.getCurrentUser(User.class);
		if (mUser != null)
		{
			String mAccount = mUser.getUsername();
			String mHeadPortrait = mUser.getIcon();
			String mProfile = mUser.getProfile();
			String mNickname = mUser.getNickName();
			String mEmail = mUser.getEmail();
			if (!StrUtil.strIsNullOrEmpty(mAccount))
			{
				mAccountTextView.setText(mAccount);
			}

			if (!StrUtil.strIsNullOrEmpty(mProfile))
			{
				mProfileTextView.setText(mProfile);
			}

			if (!StrUtil.strIsNullOrEmpty(mNickname))
			{
				mNicknameTextView.setText(mNickname);
			}
			if (!StrUtil.strIsNullOrEmpty(mHeadPortrait))
			{
				ImageLoader.getInstance().displayImage(mHeadPortrait, mHeadPortraitCircleImageView);
			}
			if (!StrUtil.strIsNullOrEmpty(mEmail))
			{
				mEmailTextView.setText(mEmail);
			}

		}
	}

	private void refresh()
	{
		mUser = BmobUser.getCurrentUser(User.class);
		if (mUser != null)
		{
			String mAccount = mUser.getUsername();
			String mHeadPortrait = mUser.getIcon();
			String mProfile = mUser.getProfile();
			String mNickname = mUser.getNickName();
			String mEmail = mUser.getEmail();
			if (!StrUtil.strIsNullOrEmpty(mAccount))
			{
				mAccountTextView.setText(mAccount);
			} else
			{
				mAccountTextView.setText("");
			}

			if (!StrUtil.strIsNullOrEmpty(mProfile))
			{
				mProfileTextView.setText(mProfile);
			} else
			{
				mProfileTextView.setText(getResources().getString(R.string.profile_defualt));
			}

			if (!StrUtil.strIsNullOrEmpty(mNickname))
			{
				mNicknameTextView.setText(mNickname);
			} else
			{
				mNicknameTextView.setText("");
			}

			if (!StrUtil.strIsNullOrEmpty(mHeadPortrait))
			{
				ImageLoader.getInstance().displayImage(mHeadPortrait, mHeadPortraitCircleImageView);
			} else
			{
				mHeadPortraitCircleImageView.setImageResource(R.mipmap.head_portrait);
			}
			if (!StrUtil.strIsNullOrEmpty(mEmail))
			{
				mEmailTextView.setText(mEmail);
			} else
			{
				mEmailTextView.setText("");
			}

		}
	}

	private void initView()
	{
		mTitleTextView.setText(getResources().getString(R.string.user_info));
		mTitleContainerRelativeLayout.getBackground().setAlpha(255);
		mUserInfoPresenter = new UserInfoPresenter(this);
	}

	@OnClick(
	{ R.id.rl_user_info_account_container, R.id.rl_user_info_email_container, R.id.rl_user_info_headportrait_container,
	        R.id.rl_user_info_nickname_container, R.id.rl_user_info_profile_container, R.id.rl_user_info_qq_container,
	        R.id.rl_user_info_wechat_container, R.id.rl_user_info_weibo_container, R.id.btn_back })
	void OnClick(View v)
	{
		switch (v.getId())
		{
		case R.id.btn_back:
			setResult(RESULT_OK);
			finish();
			break;
		case R.id.rl_user_info_nickname_container:
			modifyNickName();
			break;
		case R.id.rl_user_info_profile_container:
			modifyProfile();
			break;
		case R.id.rl_user_info_headportrait_container:
			modifyHeadProtrait();
			break;

		default:
			break;
		}
	}

	private void modifyHeadProtrait()
	{
		new AlertDialog.Builder(this).setItems(new String[]
		{ getResources().getString(R.string.by_take_photo), getResources().getString(R.string.by_gallery) },
		        new OnClickListener()
		        {

			        @Override
			        public void onClick(DialogInterface dialog, int which)
			        {
				        switch (which)
				        {
				        // 拍照
				        case 0:
					        Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
					        ContentValues values = new ContentValues();
					        mHeadProtraitUri = getContentResolver().insert(Media.EXTERNAL_CONTENT_URI, values);
					        captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mHeadProtraitUri);
					        startActivityForResult(captureIntent, Constant.REQUEST_CODE_CAPTURE);
					        break;
				        // 从图库中选取
				        case 1:
					        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
					        galleryIntent.setType("image/*");
					        startActivityForResult(galleryIntent, Constant.REQUEST_CODE_GALLERY);
					        break;
				        default:
					        break;
				        }
			        }
		        }).show();
	}

	private void modifyProfile()
	{
		if (mUser != null)
		{
			String hintMsg = mUser.getProfile();
			mDialog = DialogUtil.showBaseDialog(this, false, new OnClickListener()
			{

				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					if (mDialog != null && mUser != null)
					{
						String profile = mDialog.getEditTextMsg();
						String objectId = mUser.getObjectId();
						mUserInfoPresenter.modifyProfile(profile, objectId);
						DialogUtil.showLoadDialog(UserInfoActivity.this, R.mipmap.xsearch_loading, "修改中");
					}

				}
			}, getResources().getString(R.string.modify_profile), hintMsg);
		}
	}

	private void modifyNickName()
	{
		if (mUser != null)
		{
			String hintMsg = mUser.getNickName();
			mDialog = DialogUtil.showBaseDialog(this, false, new OnClickListener()
			{

				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					if (mDialog != null && mUser != null)
					{
						String nickname = mDialog.getEditTextMsg();
						String objectId = mUser.getObjectId();
						DialogUtil.showLoadDialog(UserInfoActivity.this, R.mipmap.xsearch_loading, "修改中");
						mUserInfoPresenter.modifyNickname(nickname, objectId);

					}

				}
			}, getResources().getString(R.string.modify_nickname), hintMsg);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode != RESULT_CANCELED)
		{
			switch (requestCode)
			{
			case Constant.REQUEST_CODE_CAPTURE:
				startPhotoZoom(mHeadProtraitUri);
				break;
			case Constant.REQUEST_CODE_GALLERY:
				if (data != null)
				{
					startPhotoZoom(data.getData());
				} else
				{
					ToastUtil.showToast(this, getResources().getString(R.string.operation_error));
				}
				break;
			case Constant.REQUEST_CODE_CROP:
				saveCropBitmapAndUpLoad(data);
				break;
			default:
				break;
			}

		}
	}

	private void saveCropBitmapAndUpLoad(Intent data)
	{
		if (data != null)
		{
			Bundle extras = data.getExtras();
			if (extras != null)
			{
				Bitmap bitmap = extras.getParcelable("data");
				if (FileUtil.IsExistSdCard())
				{
					String path = Environment.getExternalStorageDirectory() + File.separator + Constant.DIR_GREATCATE
					        + File.separator + Constant.FILE_NAME_HEADPROTRAIT;
					if (FileUtil.writeBitmapToSD(path, bitmap))
					{
						DialogUtil.showLoadDialog(UserInfoActivity.this, R.mipmap.xsearch_loading, "修改中");
						mUserInfoPresenter.modifyHeadProtrait(path, mUser.getObjectId(), mUser.getIcon());
					} else
					{
						ToastUtil.showToast(this, getResources().getString(R.string.operation_error));
					}
				} else
				{
					ToastUtil.showToast(this, getResources().getString(R.string.cannot_find_sd));
				}
			} else
			{
				ToastUtil.showToast(this, getResources().getString(R.string.operation_error));
			}
		} else
		{
			ToastUtil.showToast(this, getResources().getString(R.string.operation_error));
		}
	}

	/**
	 * 裁剪图片方法实现
	 * 
	 * @param uri
	 */
	public void startPhotoZoom(Uri uri)
	{
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		// 设置裁剪
		intent.putExtra("crop", "true");
		// aspectX aspectY 是宽高的比例
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		// outputX outputY 是裁剪图片宽高
		intent.putExtra("outputX", 300);
		intent.putExtra("outputY", 300);
		intent.putExtra("return-data", true);
		startActivityForResult(intent, Constant.REQUEST_CODE_CROP);
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
	public void onModifySuccessed()
	{
		refresh();
		DialogUtil.removeDialog(this);
	}

	@Override
	public void onModifyErrored(String errorMsg)
	{
		ToastUtil.showToast(this, errorMsg);
		DialogUtil.removeDialog(this);
	}

}