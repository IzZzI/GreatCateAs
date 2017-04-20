package cn.zhouzy.greatcate.module.main.adapter;

import java.util.ArrayList;
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
import cn.zhouzy.greatcate.common.utils.StrUtil;
import cn.zhouzy.greatcate.entity.Data;

/**
 * Created by gdxw on 2017/3/9.
 */

public class DisplayCateListViewAdapter extends BaseAdapter
{
	private List<Data> mCateList;
	private Context mContext;
	private ImageLoader mImageLoader;
	private DisplayImageOptions mImageOptions;

	public DisplayCateListViewAdapter(List<Data> mCateList, Context context)
	{
		if (mCateList == null)
		{
			mCateList = new ArrayList<>();
		}
		this.mCateList = mCateList;
		this.mContext = context;
		this.mImageLoader = ImageLoader.getInstance();
		this.mImageOptions = new DisplayImageOptions.Builder().cacheInMemory(true).bitmapConfig(
				Bitmap.Config.RGB_565).build();
	}

	@Override
	public int getCount()
	{
		return mCateList == null ? 0 : mCateList.size();
	}

	@Override
	public Object getItem(int position)
	{
		return mCateList.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(int position, View contentView, ViewGroup viewGroup)
	{
		if (contentView == null)
		{
			contentView = LayoutInflater.from(mContext).inflate(R.layout.item_display_cate,
					viewGroup, false);
		}
		final ImageView ivAlbums = BaseViewHolder.get(contentView, R.id.iv_item_cate_album);
		TextView tvTitle = BaseViewHolder.get(contentView, R.id.tv_item_cate_name);
		TextView tvIntro = BaseViewHolder.get(contentView, R.id.tv_item_cate_intro);
		Data cate = mCateList.get(position);
		if (cate != null)
		{
			String title = cate.getTitle();
			String tagArray = cate.getTags();
			String imtro = cate.getImtro();
			List<String> albums = cate.getAlbums();

			if (!StrUtil.strIsNullOrEmpty(title))
			{
				tvTitle.setText(title);

			}
			if (!StrUtil.strIsNullOrEmpty(imtro))
			{
				tvIntro.setText(imtro);
			}
			if (!StrUtil.strIsNullOrEmpty(tagArray))
			{
				String[] tags = tagArray.split(";");
				StringBuffer tag = new StringBuffer();
				int tagMaxNum = 3;
				if (tags.length < 3)
				{
					tagMaxNum = tags.length;
				}
				for (int i = 0; i < tagMaxNum; i++)
				{
					if (i == tagMaxNum)
					{
						tag.append(tags[i]);
					} else
					{
						tag.append(tags[i] + ";");
					}
				}
			}
			if (albums != null)
			{
				String imgUrl = albums.get(0);
				if (!StrUtil.strIsNullOrEmpty(imgUrl))
				{
					mImageLoader.displayImage(imgUrl, ivAlbums, mImageOptions);
				}
			}

		}

		return contentView;
	}
}
