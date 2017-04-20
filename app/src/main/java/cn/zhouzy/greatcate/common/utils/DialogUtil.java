package cn.zhouzy.greatcate.common.utils;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import cn.zhouzy.greatcate.base.BaseDialogFragment;
import cn.zhouzy.greatcate.common.fragment.LoadDialogFragment;

public class DialogUtil
{
	/** dialog 标记 */
	public static String dialogTag = "dialog";

	/**
	 * 显示dialogFragment
	 * 
	 * @param cancelable
	 *            是否可点dialog外取消
	 * @param contentView
	 *            自定义view
	 * @param leftButtonText
	 *            左边按钮文本
	 * @param lefeButtonClickListener
	 *            左边按钮监听 不设置则隐藏按钮
	 * @param rightButtonText
	 *            右边按钮文本
	 * @param rightButtonClickListener
	 *            右边按钮监听不设置则隐藏按钮
	 * @param titleTxt
	 *            标题
	 * @return
	 */
	public static BaseDialogFragment showBaseDialog(Context context, boolean cancelable, String leftButtonText,
	        String rightButtonText, OnClickListener rightButtonClickListener, String titleTxt, String hintMsg)
	{
		BaseDialogFragment newFragment = new BaseDialogFragment(cancelable, leftButtonText, rightButtonText,
		        rightButtonClickListener, titleTxt, hintMsg);
		// Create and show the dialog.
		FragmentActivity activity = (FragmentActivity) context;
		FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
		ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
		newFragment.show(ft, dialogTag);
		return newFragment;
	}

	/**
	 * 显示dialogFragment
	 * 
	 * @param cancelable
	 *            是否可点dialog外取消
	 * @param contentView
	 *            自定义view
	 * @param lefeButtonClickListener
	 *            左边按钮监听 (取消按钮) 不设置则隐藏按钮
	 * @param rightButtonClickListener
	 *            右边按钮监听（确定按钮） 不设置则隐藏按钮
	 * @param titleTxt
	 *            标题
	 * @return
	 */
	public static BaseDialogFragment showBaseDialog(Context context, boolean cancelable,
	        OnClickListener rightButtonClickListener, String titleTxt, String hintMsg)
	{
		BaseDialogFragment newFragment = new BaseDialogFragment(cancelable, null, null, rightButtonClickListener,
		        titleTxt, hintMsg);
		// Create and show the dialog.
		FragmentActivity activity = (FragmentActivity) context;
		FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
		ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
		newFragment.show(ft, dialogTag);
		return newFragment;
	}

	/**
	 * 描述：移除Fragment.
	 * 
	 * @param context
	 *            the context
	 */
	public static void removeDialog(final Context context)
	{
		try
		{
			FragmentActivity activity = (FragmentActivity) context;
			FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
			// 指定一个系统转场动画
			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
			Fragment prev = activity.getFragmentManager().findFragmentByTag(dialogTag);
			if (prev != null)
			{
				ft.remove(prev);
			}
			ft.addToBackStack(null);
			ft.commit();
		} catch (Exception e)
		{
			// 可能有Activity已经被销毁的异常
			e.printStackTrace();
		}
	}

	/**
	 * 描述：显示加载框.
	 * @param context the context
	 * @param indeterminateDrawable
	 * @param message the message
	 * @param style
	 */
	public static LoadDialogFragment showLoadDialog(Context context, int indeterminateDrawable, String message)
	{
		FragmentActivity activity = (FragmentActivity) context;
		LoadDialogFragment newFragment = LoadDialogFragment.newInstance(DialogFragment.STYLE_NO_TITLE,
		        android.R.style.Theme_Holo_Light_Dialog);
		newFragment.setIndeterminateDrawable(indeterminateDrawable);
		newFragment.setMessage(message);
		FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
		// 指定一个系统转场动画   
		ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
		newFragment.show(ft, dialogTag);
		return newFragment;
	}

	/**
	 * 描述：移除Fragment和View
	 * 
	 * @param view
	 */
	public static void removeDialog(View view)
	{
		removeDialog(view.getContext());
		ViewParent parent = view.getParent();
		if (parent != null)
		{
			if (parent instanceof ViewGroup)
			{
				((ViewGroup) parent).removeView(view);
			}
		}
	}

}
