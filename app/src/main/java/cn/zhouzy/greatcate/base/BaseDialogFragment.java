package cn.zhouzy.greatcate.base;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import cn.zhouzy.greatcate.base.BaseDialog.Builder;

public class BaseDialogFragment extends DialogFragment
{

	private boolean cancelable = true;
	private String mCancleButtonText;
	private String mConfirmButtonText;
	private OnClickListener mOnConfirmListener;
	private String mTitleTxt;
	private Builder builder;
	private String mHintMsg;

	public BaseDialogFragment(boolean cancelable, String cancleButtonText, String confirmButtonText,
			OnClickListener confirmOnClickListener, String titleTxt, String hintMsg)
	{
		this.cancelable = cancelable;
		this.mCancleButtonText = cancleButtonText;
		this.mConfirmButtonText = confirmButtonText;
		this.mOnConfirmListener = confirmOnClickListener;
		this.mTitleTxt = titleTxt;
		this.mHintMsg = hintMsg;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState)
	{
		builder = new Builder(getActivity());
		if (mCancleButtonText == null || "".equals(mCancleButtonText))
		{
			mCancleButtonText = "取消";
		}
		builder.setLeftButtonText(mCancleButtonText);
		if (mOnConfirmListener != null)
		{
			if (mConfirmButtonText == null || "".equals(mConfirmButtonText))
			{
				mConfirmButtonText = "确定";
			}
			builder.setRightButtonClickListener(mConfirmButtonText, mOnConfirmListener);
		}
		if (mTitleTxt != null)
		{
			builder.setTitleTxt(mTitleTxt);
		}
		if (mHintMsg != null)
		{
			builder.setEditTextHint(mHintMsg);
		}
		builder.setCancelable(cancelable);
		BaseDialog dialog = builder.create();
		return dialog;
	}

	public String getEditTextMsg()
	{
		if (builder != null)
		{
			return builder.getEditTextMsg();
		}
		return "";
	}

	@Override
	public void onDismiss(DialogInterface dialog)
	{
		super.onDismiss(dialog);
	}

}
