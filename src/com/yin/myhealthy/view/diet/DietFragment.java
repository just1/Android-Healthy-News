package com.yin.myhealthy.view.diet;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yin.myhealthy.GlobalDate;
import com.yin.myhealthy.R;
import com.yin.myhealthy.adapter.MyFragmentPagerAdapter;
import com.yin.myhealthy.controller.DietController;
import com.yin.myhealthy.view.ChildViewPager;
import com.yin.myhealthy.view.manager.TopBarManager;

//��ʳҳ��
public class DietFragment extends Fragment {

	private ChildViewPager mViewPager;
	private Context context;

	@Override
	public void onStart() {
		super.onStart();

		// Ӧ�����������getActivity()�Ų��᷵��Ϊnull������Ӧ���ڹ��캯���������
		this.context = getActivity();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_diet, container, false);

		mViewPager = (ChildViewPager) view.findViewById(R.id.child_vp_diet);

		final List<Fragment> listViews = new ArrayList<Fragment>();

		listViews.add(new DietSingleFragment(GlobalDate.API_DIET_MENU_LIST,
				"1", new DietController()));
		listViews.add(new DietSingleFragment(GlobalDate.API_DIET_MENU_LIST,
				"2", new DietController()));
		listViews.add(new DietSingleFragment(GlobalDate.API_DIET_MENU_LIST,
				"3", new DietController()));
		listViews.add(new DietSingleFragment(GlobalDate.API_DIET_MENU_LIST,
				"4", new DietController()));
		listViews.add(new DietSingleFragment(GlobalDate.API_DIET_MENU_LIST,
				"5", new DietController()));
		listViews.add(new DietSingleFragment(GlobalDate.API_DIET_MENU_LIST,
				"6", new DietController()));
		listViews.add(new DietSingleFragment(GlobalDate.API_DIET_MENU_LIST,
				"7", new DietController()));

		mViewPager.setAdapter(new MyFragmentPagerAdapter(
				getChildFragmentManager(), listViews));

		// ����viewpager�����������
		TopBarManager.getInstance().setDietVp(mViewPager);
		// �����л�ҳ��ļ���
		mViewPager.setOnPageChangeListener(TopBarManager.getInstance());

		return view;
	}
}
