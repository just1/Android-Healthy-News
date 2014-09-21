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

//ҩƷҳ��
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

		// ����viewpager�����������
		TopBarManager.getInstance().setMedicineVp(mViewPager);
		// �����л�ҳ��ļ���
		mViewPager.setOnPageChangeListener(TopBarManager.getInstance());

		return view;
	}

}
