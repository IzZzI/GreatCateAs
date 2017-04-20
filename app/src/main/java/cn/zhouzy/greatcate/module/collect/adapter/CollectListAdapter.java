package cn.zhouzy.greatcate.module.collect.adapter;

import java.util.ArrayList;
import java.util.List;

import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import cn.zhouzy.greatcate.R;
import cn.zhouzy.greatcate.base.BaseViewHolder;
import cn.zhouzy.greatcate.entity.CollectEntity;

public class CollectListAdapter extends BaseAdapter
{
	private Context mContext;
	private List<CollectEntity> mCollectList;

	public CollectListAdapter(Context mContext, List<CollectEntity> mCollectList)
	{
		super();
		if (mCollectList == null)
		{
			mCollectList = new ArrayList<>();
		}
		this.mContext = mContext;
		this.mCollectList = mCollectList;
	}

	@Override
	public int getCount()
	{
		return mCollectList == null ? 0 : mCollectList.size();
	}

	@Override
	public Object getItem(int position)
	{
		return mCollectList.get(position);
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
			convertView = LayoutInflater.from(mContext).inflate(R.layout.item_collect_list, parent,
					false);
		}
		CollectEntity mCollectEntity = mCollectList.get(position);
		if (mCollectEntity != null)
		{
			ImageView mAblumsImageView = BaseViewHolder.get(convertView,
					R.id.iv_item_collect_list_albums);
			TextView mTitleTextView = BaseViewHolder.get(convertView,
					R.id.tv_item_collect_list_name);
			TextView mIntroTextView = BaseViewHolder.get(convertView,
					R.id.tv_item_collect_list_intro);
			TextView mTimeTextView = BaseViewHolder.get(convertView,
					R.id.tv_item_collect_list_time);
			String mAblumsUrl = mCollectEntity.getAlbums();
			String mTitle = mCollectEntity.getTitle();
			String mIntro = mCollectEntity.getIntro();
			String mTime = mCollectEntity.getCollectTime();
			if (mAblumsUrl != null)
			{
				ImageLoader.getInstance().displayImage(mAblumsUrl, mAblumsImageView);
			}
			if (mTitle != null)
			{
				mTitleTextView.setText(mTitle);
			}
			if (mIntro != null)
			{
				mIntroTextView.setText(mIntro);
			}
			if (mTime != null)
			{
				mTimeTextView.setText(mTime);
			}
		}
		return convertView;
	}

}
