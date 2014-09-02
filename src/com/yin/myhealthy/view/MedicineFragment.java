package com.yin.myhealthy.view;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myhealthy.R;
import com.yin.myhealthy.adapter.ChildViewPagerAdapter;

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

		mViewPager = (ChildViewPager) view.findViewById(R.id.child_vp_medicine);
		final List<Fragment> listViews = new ArrayList<Fragment>();

		listViews.add(new TestFragment1(context));
		//listViews.add(new TestFragment2(context));
		
		
		mViewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
			
			@Override
			public Fragment getItem(int arg0) {
				if (listViews != null) {
					return listViews.get(arg0);
				}
				return null;
			}

			@Override
			public int getCount() {
				if (listViews != null) {
					return listViews.size();
				}
				return 0;
			}
		});
		
		
//		mViewPager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
//
//             @Override
//             public Fragment getItem(final int position) {
//                 return listViews.get(position);
//                 
//             }
//
//			@Override
//			public int getCount() {
//				// TODO Auto-generated method stub
//				return listViews.size();
//			}
//		 });
		
		
		
		return view;
	}

}
