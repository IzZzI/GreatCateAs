package cn.zhouzy.greatcate.module.collect.adapter;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuAdapter;

import java.util.List;

import cn.zhouzy.greatcate.R;
import cn.zhouzy.greatcate.base.BaseApplication;
import cn.zhouzy.greatcate.entity.CollectEntity;

/**
 * Created by 正义 on 2017/4/19.
 */

public class CollectListAdapter extends SwipeMenuAdapter<CollectListAdapter.DefaultViewHolder>
{

	private List<CollectEntity> mDataList;
	private OnItemClickListener mOnItemClickListener;
	private DisplayImageOptions options;

	public CollectListAdapter(List mDataList)
	{
		this.mDataList = mDataList;
		this.options = BaseApplication.getOptions();
	}


	@Override
	public int getItemCount()
	{
		return mDataList == null ? 0 : mDataList.size();
	}

	@Override
	public View onCreateContentView(ViewGroup parent, int viewType)
	{
		return LayoutInflater.from(parent.getContext()).inflate(R.layout.item_collect_list, parent, false);
	}


	@Override
	public CollectListAdapter.DefaultViewHolder onCompatCreateViewHolder(View realContentView, int viewType)
	{
		DefaultViewHolder viewHolder = new DefaultViewHolder(realContentView);
		viewHolder.mOnItemClickListener = mOnItemClickListener;
		return viewHolder;
	}

	@Override
	public void onBindViewHolder(CollectListAdapter.DefaultViewHolder holder, int position)
	{
		CollectEntity mCollectEntity = mDataList.get(position);
		if (mCollectEntity != null)
		{
			final String mAblumsUrl = mCollectEntity.getAlbums();
			String mTitle = mCollectEntity.getTitle();
			String mIntro = mCollectEntity.getIntro();
			String mTime = mCollectEntity.getCollectTime();


			if (mAblumsUrl != null)
			{
				holder.mAblumsImageView.setTag(mAblumsUrl);

				ImageLoader.getInstance().displayImage(mAblumsUrl, holder.mAblumsImageView, options,new ImageLoadingListener()
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
						if (mAblumsUrl .equals(view.getTag())){
							((ImageView)view).setImageBitmap(loadedImage);
						}
					}

					@Override
					public void onLoadingCancelled(String imageUri, View view)
					{

					}
				});
			}
			if (mTitle != null)
			{
				holder.mTitleTextView.setText(mTitle);
			}
			if (mIntro != null)
			{
				holder.mIntroTextView.setText(mIntro);
			}
			if (mTime != null)
			{
				holder.mTimeTextView.setText(mTime);
			}

		}
	}

	public void setOnItemClickListener(OnItemClickListener onItemClickListener)
	{
		this.mOnItemClickListener = onItemClickListener;

	}

	static class DefaultViewHolder extends RecyclerView.ViewHolder
	{

		OnItemClickListener mOnItemClickListener;
		TextView mTimeTextView;
		ImageView mAblumsImageView;
		TextView mTitleTextView;
		TextView mIntroTextView;

		public DefaultViewHolder(View itemView)
		{
			super(itemView);
			RelativeLayout mContainer = (RelativeLayout) itemView.findViewById(R.id.rl_item_collect_list_container);
			mTimeTextView = (TextView) itemView.findViewById(R.id.tv_item_collect_list_time);
			mAblumsImageView = (ImageView) itemView.findViewById(R.id.iv_item_collect_list_albums);
			mTitleTextView = (TextView) itemView.findViewById(R.id.tv_item_collect_list_name);
			mIntroTextView = (TextView) itemView.findViewById(R.id.tv_item_collect_list_intro);
			mContainer.setOnClickListener(new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					if (mOnItemClickListener != null)
					{
						mOnItemClickListener.onItemClick(getAdapterPosition());
					}
				}
			});

		}


	}

	public interface OnItemClickListener
	{
		void onItemClick(int position);

	}

}
