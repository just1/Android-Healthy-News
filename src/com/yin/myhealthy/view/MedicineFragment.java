package com.yin.myhealthy.view;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myhealthy.R;
import com.yin.myhealthy.view.manager.TopBarManager;

//ҩƷҳ��
public class MedicineFragment extends Fragment {

	private ChildViewPager mViewPager;
	private Context context;
	
	
	public MedicineFragment() {
		super();
		
		if(getActivity() == null){
			
			Log.d("����������������","����   getActivity() is null");
			System.out.println("getActivity() is null");
		}
		else{
			
			Log.d("����������������","��Ȼû�г���");
			System.out.println("��Ȼû�г���");
			this.context = getActivity();
		}
		
	}
	
	@Override
	public void onAttach(Activity activity) {
	    super.onAttach(activity);  
	    context = activity;  
	}

	public MedicineFragment(Context context) {
		super();
		this.context = context;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_medicine, container,
				false);

		mViewPager = (ChildViewPager) view.findViewById(R.id.child_vp_medicine);
		
		//����viewpager�����������
		TopBarManager.getInstance().setMedicineVp(mViewPager);
		//�����л�ҳ��ļ���
		mViewPager.setOnPageChangeListener(TopBarManager.getInstance());
		
		final List<Fragment> listViews = new ArrayList<Fragment>();

		listViews.add(new TestFragment1(context));
		listViews.add(new TestFragment2(context));
		
		
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
		
		
		return view;
	}

}
