package cn.zhouzy.greatcate.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.zhouzy.greatcate.R;
import cn.zhouzy.greatcate.common.view.AutoCleanEditText;

@SuppressLint("InflateParams")
public class BaseDialog extends Dialog
{
	private BaseDialog(Context context, int theme)
	{
		super(context, theme);
	}

	public static class Builder
	{

		private Activity mContext;

		TextView mTitleTextView;

		Button mCancleButton;

		Button mConfirmButton;

		RelativeLayout mBtnLayout;

		private View mRootView;
		private String mCancleButtonTxt;
		private String mConfirmButtonTxt;;
		private String mTitleTxt;
		private AutoCleanEditText mAutoCleanEditText;

		private OnClickListener onConfirmListener;

		private BaseDialog mDialog;

		private boolean isCancleable = true;

		float ratio;

		DisplayMetrics dm;

		private int CANCLE_BUTTON_VISIBILITY = View.VISIBLE;

		private int CONFIRM_BUTTON_VISIBILITY = View.VISIBLE;

		private String mHintText;

		public Builder(Activity context)
		{
			this.mContext = context;

		}

		public void setTitleTxt(String titleTxt)
		{
			this.mTitleTxt = titleTxt;
		}

		public void setLeftButtonText(String txt)
		{
			this.mCancleButtonTxt = txt;
		}

		public void setRightButtonClickListener(String txt,
				OnClickListener listener)
		{
			mConfirmButtonTxt = txt;
			onConfirmListener = listener;
			CONFIRM_BUTTON_VISIBILITY = View.VISIBLE;
		}

		public void setEditTextHint(String hint)
		{
			this.mHintText = hint;
		}

		public String getEditTextMsg()
		{
			return mAutoCleanEditText.getText().toString().trim();
		}

		public void setCancelable(boolean cancelable)
		{
			isCancleable = cancelable;
		}

		public BaseDialog create()
		{
			initDialog();
			initContent();
			return mDialog;
		}

		private void initContent()
		{
			if (mTitleTxt != null)
			{
				mTitleTextView.setText(mTitleTxt);
			}
			if (mCancleButtonTxt != null)
			{
				mCancleButton.setText(mCancleButtonTxt);
			}
			if (mConfirmButtonTxt != null)
			{
				mConfirmButton.setText(mConfirmButtonTxt);
			}
			if (mHintText != null)
			{
				mAutoCleanEditText.setMessage(mHintText);
			}
			mCancleButton.setVisibility(CANCLE_BUTTON_VISIBILITY);
			mConfirmButton.setVisibility(CONFIRM_BUTTON_VISIBILITY);

			if (CANCLE_BUTTON_VISIBILITY == View.GONE && CONFIRM_BUTTON_VISIBILITY == View.GONE)
			{
				mBtnLayout.setVisibility(View.GONE);
			}
			mCancleButton.setOnClickListener(new View.OnClickListener()
			{

				@Override
				public void onClick(View v)
				{
					mDialog.dismiss();
				}
			});
			mConfirmButton.setOnClickListener(new View.OnClickListener()
			{

				@Override
				public void onClick(View v)
				{
					if (onConfirmListener != null)
					{
						onConfirmListener.onClick(mDialog, DialogInterface.BUTTON_NEGATIVE);
					}
					mDialog.dismiss();
				}
			});
		}

		private void initDialog()
		{
			mDialog = new BaseDialog(mContext, R.style.DialogStyleBottom);
			mDialog.setCanceledOnTouchOutside(isCancleable);
			Window dialogWindow = mDialog.getWindow();
			WindowManager.LayoutParams lp = dialogWindow.getAttributes();
			dm = new DisplayMetrics();
			dm = mContext.getResources().getDisplayMetrics();
			lp.width = dm.widthPixels; // 宽度
			lp.height = dm.heightPixels; // 高度
			ratio = (float) 1920 / dm.heightPixels;
			dialogWindow.setAttributes(lp);
			mRootView = mContext.getLayoutInflater().inflate(R.layout.dialog_base, null);
			mDialog.setContentView(mRootView);
			mTitleTextView = (TextView) mRootView.findViewById(R.id.tv_dialog_base_title);
			mCancleButton = (Button) mRootView.findViewById(R.id.btn_dialog_base_cancle);
			mConfirmButton = (Button) mRootView.findViewById(R.id.btn_dialog_base_confirm);
			mAutoCleanEditText = (AutoCleanEditText) mRootView.findViewById(
					R.id.et_dialog_base_input);

		}
	}

}
