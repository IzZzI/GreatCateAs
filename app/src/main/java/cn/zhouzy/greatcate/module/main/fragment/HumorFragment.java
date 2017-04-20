package cn.zhouzy.greatcate.module.main.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cn.zhouzy.greatcate.R;
import cn.zhouzy.greatcate.base.BaseFragment;

public class HumorFragment extends BaseFragment
{

	private boolean isCreateView;
	private boolean isLoadData;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		setContentView(inflater, container, R.layout.fragment_humor);
		isCreateView = true;
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	public static HumorFragment newInstance()
	{
		return new HumorFragment();
	}

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser)
	{
		super.setUserVisibleHint(isVisibleToUser);
		if (isVisibleToUser && isCreateView)
		{
			if (isLoadData)
			{
				lazyLoad();
			}
		}

	}

	private void lazyLoad()
	{

	}

}
