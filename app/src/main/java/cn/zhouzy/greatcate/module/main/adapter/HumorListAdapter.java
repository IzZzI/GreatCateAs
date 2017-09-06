package cn.zhouzy.greatcate.module.main.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cn.zhouzy.greatcate.R;
import cn.zhouzy.greatcate.common.utils.SharedPreferencesUtil;
import cn.zhouzy.greatcate.common.utils.StrUtil;
import cn.zhouzy.greatcate.common.view.CircleImageView;
import cn.zhouzy.greatcate.common.view.ExpandGridViewToScrollView;
import cn.zhouzy.greatcate.contract.HumorContract;
import cn.zhouzy.greatcate.entity.Post;

/**
 * Created by 正义 on 2017/5/5.
 */

public class HumorListAdapter extends RecyclerView.Adapter<HumorListAdapter.HumorViewHolder>
{
	private  DisplayImageOptions options;
	private List<Post> mDataList;
	private Context mContext;


	public HumorListAdapter(List<Post> mDataList, Context mContext)
	{
		if (mDataList == null)
		{
			mDataList = new ArrayList<>();
		}
		this.mDataList = mDataList;
		this.mContext = mContext;
		this.options = new DisplayImageOptions.Builder()
				.cacheInMemory(true)
				.cacheOnDisk(true)
				.bitmapConfig(Bitmap.Config.RGB_565)
				.build();
	}


	@Override
	public HumorViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
	{
		View mItemView = LayoutInflater.from(mContext).inflate(R.layout.item_humor_layout, parent, false);
		HumorViewHolder mHumorViewHolder = new HumorViewHolder(mItemView);
		return mHumorViewHolder;
	}

	@Override
	public void onBindViewHolder(final HumorViewHolder holder, final int position)
	{
		Post mPost = mDataList.get(position);
		if (mPost != null)
		{
			String mTime = mPost.getTime();
			String mAuthor = mPost.getAuthor();
			final String mContent = mPost.getContent();
			List<String> mImgUrl = mPost.getImgUrls();
			final String mHeadPortraitUrl = mPost.getAuthorHeadPortrait();
			if (!StrUtil.strIsNullOrEmpty(mTime))
			{
				holder.mTimeTextView.setText(mTime);
			}
			if (!StrUtil.strIsNullOrEmpty(mAuthor))
			{
				holder.mNameTextView.setText(mAuthor);
			}
			if (!StrUtil.strIsNullOrEmpty(mContent))
			{
				holder.mContentTextView.setText(mContent);
			}
			if (!StrUtil.strIsNullOrEmpty(mHeadPortraitUrl))
			{
				ImageLoader.getInstance().displayImage(mHeadPortraitUrl, holder.mHeadPortraitCircleImageView, options,new ImageLoadingListener()
				{
					@Override
					public void onLoadingStarted(String imageUri, View view)
					{
						holder.mHeadPortraitCircleImageView.setTag(mHeadPortraitUrl);
					}

					@Override
					public void onLoadingFailed(String imageUri, View view, FailReason failReason)
					{

					}

					@Override
					public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage)
					{
						if (mHeadPortraitUrl.equals(holder.mHeadPortraitCircleImageView.getTag())){
							 holder.mHeadPortraitCircleImageView.setImageBitmap(loadedImage);
						}
					}

					@Override
					public void onLoadingCancelled(String imageUri, View view)
					{

					}
				});
			} else
			{
				holder.mHeadPortraitCircleImageView.setImageResource(R.mipmap.head_portrait);
			}
			holder.mLikeTextView.setText(new Random().nextInt(20) + "");
			if (mImgUrl != null && mImgUrl.size() > 0)
			{
				HumorPicAdapter humorPicAdapter = new HumorPicAdapter(mImgUrl, mContext);
				holder.mImageGridView.setAdapter(humorPicAdapter);
			}
			holder.mLikeImageView.setOnClickListener(new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					if (holder.isLike == false)
					{
						holder.isLike = true;
						holder.mLikeImageView.setImageResource(R.drawable.like_pre);
						SharedPreferencesUtil sharedPreferencesUtil = new SharedPreferencesUtil(mContext);
						holder.mLikeTextView.setText(Integer.valueOf(holder.mLikeTextView.getText().toString()) + 1 + "");
						sharedPreferencesUtil.putInt(position + "", Integer.valueOf(holder.mLikeTextView.getText().toString()));

					} else
					{
						holder.isLike = false;
						holder.mLikeImageView.setImageResource(R.drawable.like_nor);
						SharedPreferencesUtil sharedPreferencesUtil = new SharedPreferencesUtil(mContext);
						holder.mLikeTextView.setText(Integer.valueOf(holder.mLikeTextView.getText().toString()) - 1 + "");
						sharedPreferencesUtil.putInt(position + "", Integer.valueOf(holder.mLikeTextView.getText().toString()));
					}
				}
			});

		}

	}

	@Override
	public int getItemCount()
	{
		return mDataList == null ? 0 : mDataList.size();
	}

	static class HumorViewHolder extends RecyclerView.ViewHolder
	{
		CircleImageView mHeadPortraitCircleImageView;
		TextView mTimeTextView;
		TextView mContentTextView;
		TextView mNameTextView;
		ExpandGridViewToScrollView mImageGridView;
		ImageView mLikeImageView;
		TextView mLikeTextView;
		boolean isLike;

		public HumorViewHolder(View itemView)
		{
			super(itemView);
			mHeadPortraitCircleImageView = (CircleImageView) itemView.findViewById(R.id.civ_item_humor_layout_head_portrait);
			mContentTextView = (TextView) itemView.findViewById(R.id.tv_item_humor_layout_content);
			mNameTextView = (TextView) itemView.findViewById(R.id.tv_item_humor_layout_name);
			mTimeTextView = (TextView) itemView.findViewById(R.id.tv_item_humor_layout_time);
			mImageGridView = (ExpandGridViewToScrollView) itemView.findViewById(R.id.gv_item_humor_pic);
			mLikeImageView = (ImageView) itemView.findViewById(R.id.iv_item_humor_like);
			mLikeTextView = (TextView) itemView.findViewById(R.id.tv_item_humor_like_num);
		}
	}

}
