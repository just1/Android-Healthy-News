package com.yin.myhealthy.adapter;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
	// fragmentÊý×é
	List<Fragment> fragList;

	public  MyFragmentPagerAdapter(FragmentManager fm,List<Fragment> fragmentList) {
		super(fm);

		this.fragList = fragmentList;
	}

	@Override
	public Fragment getItem(int arg0) {
		if (fragList != null) {
			return fragList.get(arg0);
		}
		return null;
	}

	@Override
	public int getCount() {
		if (fragList != null) {
			return fragList.size();
		}
		return 0;
	}

}
