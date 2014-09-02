package com.yin.myhealthy.view;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myhealthy.R;
import com.yin.myhealthy.adapter.MainFragmentPagerAdapter;

//Ò©Æ·Ò³Ãæ
public class MedicineFragment extends Fragment {

	private ChildViewPager mViewPager;
	private Context context;
	private FragmentActivity activity;

	public MedicineFragment(Context context,FragmentActivity activity) {
		super();
		this.context = context;
		this.activity = activity;
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_medicine, container,
				false);

		mViewPager = (ChildViewPager) view.findViewById(R.id.child_vp);
		List<Fragment> listViews = new ArrayList<Fragment>();

		listViews.add(new TestFragment1(context));
		
		mViewPager.setAdapter(new MainFragmentPagerAdapter(activity.getSupportFragmentManager(),
				listViews));
		
		
		
		return view;
	}

}
