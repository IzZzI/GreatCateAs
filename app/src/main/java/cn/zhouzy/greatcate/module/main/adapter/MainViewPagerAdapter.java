package cn.zhouzy.greatcate.module.main.adapter;


import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by gdxw on 2017/3/8.
 */

public class MainViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragmentList;

    public MainViewPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        if (fragmentList == null) {
            fragmentList = new ArrayList<Fragment>();
        }
        this.mFragmentList = fragmentList;
    }


    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}
