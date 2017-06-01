package cn.zhouzy.greatcate.module.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import cn.zhouzy.greatcate.R;
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
	private List<Post> mDataList;
	private Context mContext;
	private  HumorContract.IHumorPresenter mHumorPresenter;


	public HumorListAdapter(List<Post> mDataList, Context mContext)
	{
		if (mDataList == null)
		{
			mDataList = new ArrayList<>();
		}
		this.mDataList = mDataList;
		this.mContext = mContext;
	}
	public void setPresenter(HumorContract.IHumorPresenter mHumorPresenter)
	{
		this.mHumorPresenter = mHumorPresenter;
	}


	@Override
	public HumorViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
	{
		View mItemView = LayoutInflater.from(mContext).inflate(R.layout.item_humor_layout, parent, false);
		HumorViewHolder mHumorViewHolder = new HumorViewHolder(mItemView);
		return mHumorViewHolder;
	}

	@Override
	public void onBindViewHolder(HumorViewHolder holder, int position)
	{
		Post mPost = mDataList.get(position);
		if (mPost != null)
		{
			String mTime = mPost.getTime();
			String mAuthor = mPost.getAuthor();
			String mContent = mPost.getContent();
			List<String> mImgUrl = mPost.getImgUrls();
			String mHeadPortraitUrl = mPost.getAuthorHeadPortrait();
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
				ImageLoader.getInstance().displayImage(mHeadPortraitUrl, holder.mHeadPortraitCircleImageView);
			}
			if (mImgUrl != null && mImgUrl.size() > 0)
			{
				HumorPicAdapter humorPicAdapter = new HumorPicAdapter(mImgUrl, mContext);
				holder.mImageGridView.setAdapter(humorPicAdapter);
			}

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
		ImageView mCommentImageView;

		public HumorViewHolder(View itemView)
		{
			super(itemView);
			mHeadPortraitCircleImageView = (CircleImageView) itemView.findViewById(R.id.civ_item_humor_layout_head_portrait);
			mContentTextView = (TextView) itemView.findViewById(R.id.tv_item_humor_layout_content);
			mNameTextView = (TextView) itemView.findViewById(R.id.tv_item_humor_layout_name);
			mTimeTextView = (TextView) itemView.findViewById(R.id.tv_item_humor_layout_time);
			mImageGridView = (ExpandGridViewToScrollView) itemView.findViewById(R.id.gv_item_humor_pic);
			mLikeImageView = (ImageView) itemView.findViewById(R.id.iv_item_humor_like);
			mCommentImageView = (ImageView) itemView.findViewById(R.id.iv_item_humor_comment);

		}
	}

}
