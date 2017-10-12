package cn.zhouzy.greatcate.module.main.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.ArrayList;
import java.util.List;

import cn.zhouzy.greatcate.R;
import cn.zhouzy.greatcate.base.BaseApplication;
import cn.zhouzy.greatcate.base.BaseViewHolder;
import cn.zhouzy.greatcate.entity.CollectEntity;
import cn.zhouzy.greatcate.entity.Data;

public class SearchResultListAdapter extends BaseAdapter
{
	private DisplayImageOptions options;
	private Context mContext;
	private List<Data> mResultList;


	public SearchResultListAdapter(Context mContext, List<Data> mResultList)
	{
		super();
		if (mResultList == null)
		{
			mResultList = new ArrayList<>();
		}
		this.mContext = mContext;
		this.mResultList = mResultList;
		this.options = BaseApplication.getOptions();
	}

	@Override
	public int getCount()
	{
		return mResultList == null ? 0 : mResultList.size();
	}

	@Override
	public Object getItem(int position)
	{
		return mResultList.get(position);
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
		Data mResultEntity = mResultList.get(position);
		if (mResultEntity != null)
		{
			final ImageView mAblumsImageView = BaseViewHolder.get(convertView,
					R.id.iv_item_collect_list_albums);
			TextView mTitleTextView = BaseViewHolder.get(convertView,
					R.id.tv_item_collect_list_name);
			TextView mIntroTextView = BaseViewHolder.get(convertView,
					R.id.tv_item_collect_list_intro);

			List<String> mAblums = mResultEntity.getAlbums();
			String mTitle = mResultEntity.getTitle();
			String mIntro = mResultEntity.getImtro();
			if (mAblums != null)
			{
				String mAblumsUrl = mAblums.get(0);
				if (mAblumsUrl != null)
				{
					mAblumsImageView.setTag(mAblumsUrl);
					ImageLoader.getInstance().displayImage(mAblumsUrl, mAblumsImageView, options, new ImageLoadingListener()
					{
						@Override
						public void onLoadingStarted(String imageUri, View view)
						{

						}

						@Override
						public void onLoadingFailed(String imageUri, View view, FailReason failReason)
						{

						}

						@Override
						public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage)
						{
							if (imageUri.equals(view.getTag()))
							{
								((ImageView) view).setImageBitmap(loadedImage);
							}
						}

						@Override
						public void onLoadingCancelled(String imageUri, View view)
						{

						}
					});
				}
			}


			if (mTitle != null)
			{
				mTitleTextView.setText(mTitle);
			}
			if (mIntro != null)
			{
				mIntroTextView.setText(mIntro);
			}

		}
		return convertView;
	}

}
