package cn.zhouzy.greatcate.module.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import cn.zhouzy.greatcate.R;
import cn.zhouzy.greatcate.base.BaseViewHolder;

public class CategoryGridViewAdapter extends BaseAdapter
{

	private Context mContext;
	private String[] mCategoryArray =
	{ "家常菜", "快手菜", "创意菜", "素菜", "凉菜", "烘焙", "面食", "汤", "调味料" };
	private int[] mCategoryResArray =
	{ R.mipmap.category_homecook_pressed, R.mipmap.category_quick_pressed,
			R.mipmap.category_creat_pressed, R.mipmap.category_vegetable_pressed,
			R.mipmap.category_clod_dish_pressed, R.mipmap.category_bake_pressed,
			R.mipmap.category_noodle_pressed, R.mipmap.category_soup_pressed,
			R.mipmap.category_seasoner_pressedl };

	public CategoryGridViewAdapter(Context mContext)
	{
		super();
		this.mContext = mContext;

	}

	@Override
	public int getCount()
	{
		return mCategoryArray.length;
	}

	@Override
	public Object getItem(int position)
	{
		return mCategoryArray[position];
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	public String getTitle(int position)
	{
		return mCategoryArray[position];
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		if (convertView == null)
		{
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.item_gv_include_dispaly_head_view_category, parent, false);
		}
		TextView mCategoryTextView = BaseViewHolder.get(convertView,
				R.id.tv_item_include_display_head_view_category);
		ImageView mCategoryImageView = BaseViewHolder.get(convertView,
				R.id.iv_item_include_display_head_view_category);
		mCategoryImageView.setImageResource(mCategoryResArray[position]);
		String category = mCategoryArray[position];
		if (category == null)
		{
			category = "";
		}
		mCategoryTextView.setText(category);

		return convertView;
	}

}
