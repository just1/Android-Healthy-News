package com.yin.myhealthy.view.medicine;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yin.myhealthy.GlobalDate;
import com.yin.myhealthy.R;
import com.yin.myhealthy.adapter.MyFragmentPagerAdapter;
import com.yin.myhealthy.controller.MedicineController;
import com.yin.myhealthy.view.ChildViewPager;
import com.yin.myhealthy.view.manager.TopBarManager;

//药品页面
public class MedicineFragment extends Fragment {

	private ChildViewPager mViewPager;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_medicine, container,
				false);

		mViewPager = (ChildViewPager) view.findViewById(R.id.child_vp_medicine);

		final List<Fragment> listViews = new ArrayList<Fragment>();

		listViews.add(new MedicineSingleFragment(GlobalDate.API_MEDICINE_LIST,
				"1", new MedicineController()));
		listViews.add(new MedicineSingleFragment(GlobalDate.API_MEDICINE_LIST,
				"2", new MedicineController()));
		listViews.add(new MedicineSingleFragment(GlobalDate.API_MEDICINE_LIST,
				"3", new MedicineController()));
		listViews.add(new MedicineSingleFragment(GlobalDate.API_MEDICINE_LIST,
				"4", new MedicineController()));
		listViews.add(new MedicineSingleFragment(GlobalDate.API_MEDICINE_LIST,
				"5", new MedicineController()));
		listViews.add(new MedicineSingleFragment(GlobalDate.API_MEDICINE_LIST,
				"6", new MedicineController()));
		listViews.add(new MedicineSingleFragment(GlobalDate.API_MEDICINE_LIST,
				"7", new MedicineController()));

		mViewPager.setAdapter(new MyFragmentPagerAdapter(
				getChildFragmentManager(), listViews));

		// 传递viewpager到标题管理器
		TopBarManager.getInstance().setMedicineVp(mViewPager);
		// 设置切换页面的监听
		mViewPager.setOnPageChangeListener(TopBarManager.getInstance());

		return view;
	}

}
