package cn.zhouzy.greatcate.entity;

import com.yzs.imageshowpickerview.ImageShowPickerBean;

/**
 * Created by 正义 on 2017/5/3.
 */

public class ImageBean extends ImageShowPickerBean
{

	private String mPath;
	private int mResId;
	private String mUrl;

	public ImageBean(String mPath, int mResId, String mUrl)
	{
		this.mPath = mPath;
		this.mUrl = mUrl;
		this.mResId = mResId;
	}


	@Override
	public String setImageShowPickerUrl()
	{
		return mUrl;
	}

	@Override
	public int setImageShowPickerDelRes()
	{
		return mResId;
	}

	@Override
	public String setImageShowPickerPath()
	{
		return mPath;
	}
}
