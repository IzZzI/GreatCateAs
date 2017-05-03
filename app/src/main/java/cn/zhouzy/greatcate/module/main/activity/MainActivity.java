package cn.zhouzy.greatcate.module.main.activity;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import butterknife.Bind;
import cn.zhouzy.greatcate.R;
import cn.zhouzy.greatcate.base.BaseActivity;
import cn.zhouzy.greatcate.common.utils.ToastUtil;
import cn.zhouzy.greatcate.module.main.adapter.MainViewPagerAdapter;
import cn.zhouzy.greatcate.module.main.fragment.DisplayFragment;
import cn.zhouzy.greatcate.module.main.fragment.HumorFragment;
import cn.zhouzy.greatcate.module.main.fragment.MineFragment;

public class MainActivity extends BaseActivity implements OnCheckedChangeListener
{

	@Bind(R.id.viewpager_main_content)
	ViewPager mContentViewPager;
	@Bind(R.id.rg_main_bottom_radiogroup)
	RadioGroup mRadioGroupContent;
	private DisplayFragment mDisplayFragment;
	private MineFragment mMineFragment;
	private HumorFragment mHumorFragment;
	private long mExitTime;
	private MainViewPagerAdapter mMainContentViewPagerAdapter;
	private List<Fragment> mFragmentList;
	private static final int FRAGMENT_CATE = 0;
	private static final int FRAGMENT_HUMOR = 1;
	private static final int FRAGMENT_MINE = 2;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
		{
			Window window = getWindow();
			window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
					| WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
			window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
					| View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
			window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

			// 给状态栏设置颜色。我设置透明。
			window.setStatusBarColor(Color.TRANSPARENT);
			window.setNavigationBarColor(Color.TRANSPARENT);
		}
		setContentView(R.layout.activity_main);
		initData();
		initView();
		initListeners();
	}

	private void initData()
	{
	}

	private void initView()
	{
		mFragmentList = new ArrayList<>();
		mDisplayFragment = DisplayFragment.newInstance();
		mHumorFragment = HumorFragment.newInstance();
		mMineFragment = MineFragment.newInstance();
		mFragmentList.add(mDisplayFragment);
		mFragmentList.add(mHumorFragment);
		mFragmentList.add(mMineFragment);
		mMainContentViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager(),
				mFragmentList);
		mContentViewPager.setAdapter(mMainContentViewPagerAdapter);
		mContentViewPager.setOffscreenPageLimit(3);

	}

	private void initListeners()
	{
		mRadioGroupContent.setOnCheckedChangeListener(this);
	}

	@Override
	protected void onDestroy()
	{
		super.onDestroy();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{

		if (keyCode == KeyEvent.KEYCODE_BACK)
		{
			if ((System.currentTimeMillis() - mExitTime) > 2000)
			{
				ToastUtil.showToast(this, getResources().getString(R.string.exit_click_again));
				mExitTime = System.currentTimeMillis();
			} else
			{
				System.exit(0);
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);

	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId)
	{
		switch (checkedId)
		{
			case R.id.rb_main_cate:
				mContentViewPager.setCurrentItem(FRAGMENT_CATE);
				break;
			case R.id.rb_main_mine:
				mContentViewPager.setCurrentItem(FRAGMENT_MINE);
				break;
			case R.id.rb_main_humor:
				mContentViewPager.setCurrentItem(FRAGMENT_HUMOR);
				break;

			default:
				break;
		}
	}

}
