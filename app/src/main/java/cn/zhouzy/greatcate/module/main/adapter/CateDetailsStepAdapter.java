package cn.zhouzy.greatcate.module.main.adapter;

import java.util.List;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import cn.zhouzy.greatcate.R;
import cn.zhouzy.greatcate.base.BaseViewHolder;
import cn.zhouzy.greatcate.entity.Steps;

public class CateDetailsStepAdapter extends BaseAdapter
{
	private List<Steps> mCateSteps;
	private Context mContext;
	private ImageLoader mImageLoader;
	private DisplayImageOptions mImageOptions;

	public CateDetailsStepAdapter(List<Steps> steps, Context context)
	{
		super();
		this.mCateSteps = steps;
		this.mContext = context;
		this.mImageLoader = ImageLoader.getInstance();
		this.mImageOptions = new DisplayImageOptions.Builder().cacheInMemory(true).bitmapConfig(
				Bitmap.Config.RGB_565).build();
	}

	@Override
	public int getCount()
	{
		return mCateSteps.size();
	}

	@Override
	public Object getItem(int position)
	{
		return mCateSteps.get(position);
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
			convertView = LayoutInflater.from(mContext).inflate(R.layout.item_lv_step, parent,
					false);
		}
		TextView tvStep = BaseViewHolder.get(convertView, R.id.tv_item_step_info);
		final ImageView ivPic = BaseViewHolder.get(convertView, R.id.iv_item_step_pic);
		Steps cateStep = mCateSteps.get(position);
		if (cateStep != null)
		{
			String imgUrl = cateStep.getImg();
			String stepString = cateStep.getStep();
			if (imgUrl != null)
			{
				ivPic.setVisibility(View.VISIBLE);
				mImageLoader.displayImage(imgUrl, ivPic, mImageOptions);
			} else
			{
				ivPic.setVisibility(View.GONE);
			}

			if (stepString != null)
			{
				tvStep.setText(stepString);
			}
		}

		return convertView;
	}

}
