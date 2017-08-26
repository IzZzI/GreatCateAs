package cn.zhouzy.greatcate.common.view;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

public class AutoCleanEditText extends EditText

{
	private Drawable dRight;
	private Rect rBounds;
	private boolean isCanClean;
	private InnerOnFocusChangeListener onFocusChangeListener;


	public AutoCleanEditText(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
		InitEditText();
	}

	public AutoCleanEditText(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		InitEditText();
	}

	public AutoCleanEditText(Context context)
	{
		super(context);
		InitEditText();
	}

	private void InitEditText()
	{
		setEditTextDrawable();
		addTextChangedListener(new TextWatcher()
		{

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count)
			{

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after)
			{

			}

			@Override
			public void afterTextChanged(Editable s)
			{
				setEditTextDrawable();
			}
		});
		setOnFocusChangeListener(new OnFocusChangeListener()
		{

			@Override
			public void onFocusChange(View v, boolean hasFocus)
			{
				if (!hasFocus)
				{
					setCompoundDrawables(null, null, null, null);
				} else
				{
					setEditTextDrawable();
				}
				if (onFocusChangeListener != null)
				{
					onFocusChangeListener.onFocusChange(v, hasFocus);
				}
			}
		});
	}

	/**
	 * setText()不显示清除按钮
	 *
	 * @param msg
	 */
	public void setMessage(CharSequence msg)
	{
		this.setText(msg);
		setSelection(msg.length());
		setCompoundDrawables(null, null, null, null);
	}

	private void setEditTextDrawable()
	{
		if (getText().toString().length() == 0)
		{
			setCompoundDrawables(null, null, null, null);
		} else
		{
			setCompoundDrawables(null, null, this.dRight, null);
		}
	}

	@Override
	protected void onDetachedFromWindow()
	{
		super.onDetachedFromWindow();
		this.dRight = null;
		this.rBounds = null;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		if ((dRight != null) && (event.getAction() == 1) && isCanClean)
		{
			this.rBounds = this.dRight.getBounds();
			 int i = (int) event.getRawX();//距离屏幕的距离
//			int i = (int) event.getX();// 距离边框的距离
			if (i > getRight() - 3 * rBounds.width())
			{
				setText("");
				event.setAction(MotionEvent.ACTION_CANCEL);
			}
		}
		return super.onTouchEvent(event);
	}

	// 显示右侧图片

	@Override
	public void setCompoundDrawables(Drawable left, Drawable top, Drawable right, Drawable bottom)
	{
		if (right != null)
		{
			dRight = right;
			isCanClean = true;
		} else
		{
			isCanClean = false;
		}
		super.setCompoundDrawables(left, top, right, bottom);
	}

	public interface InnerOnFocusChangeListener
	{
		void onFocusChange(View v, boolean hasFocus);
	}

	public void addInnerOnFocusChangeLister(InnerOnFocusChangeListener onFocusChangeListener)
	{
		this.onFocusChangeListener = onFocusChangeListener;
	}
}
