package cn.zhouzy.greatcate.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;

/**
 * Created by gdxw on 2017/3/8.
 */

public abstract class BaseFragment extends Fragment {

    private View parentView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return parentView;
    }

    public  void setContentView(LayoutInflater inflater, ViewGroup container,int resId){
        parentView = inflater.inflate(resId, container, false);
        ButterKnife.bind(this,parentView);
    }


}
