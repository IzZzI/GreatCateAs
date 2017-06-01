package cn.zhouzy.greatcate.module.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.yzs.imageshowpickerview.ImageShowPickerView;

import java.util.ArrayList;
import java.util.List;

import cn.zhouzy.greatcate.R;
import cn.zhouzy.greatcate.base.BaseViewHolder;

/**
 * Created by 正义 on 2017/5/12.
 */

public class HumorPicAdapter extends BaseAdapter
{
	private List<String> mDataList;
	private Context mContext;

	public HumorPicAdapter(List<String> mDataList, Context mContext)
	{
		if (mDataList == null)
		{
			mDataList = new ArrayList<>();
		}
		this.mDataList = mDataList;
		this.mContext = mContext;

	}

	@Override
	public int getCount()
	{
		return mDataList == null ? 0 : mDataList.size();
	}

	@Override
	public Object getItem(int position)
	{
		return mDataList.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		if (convertView == null)
		{
			convertView = LayoutInflater.from(mContext).inflate(R.layout.item_humor_pic,parent,false);
		}
		ImageView mImageView = BaseViewHolder.get(convertView,R.id.iv_item_humor_pic);
		String mUrl  = mDataList.get(position);
		ImageLoader.getInstance().displayImage(mUrl,mImageView);
		return convertView;
	}
}
