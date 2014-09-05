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

//药品页面
public class MedicineFragment extends Fragment {

	private ChildViewPager mViewPager;
	private Context context;
	
	
	@Override
	public void onStart() {
		super.onStart();
		
		//应该在这里调用getActivity()才不会返回为null，而不应该在构造函数里面调用
		this.context = getActivity();
	}

	public MedicineFragment() {
		super();
		
		if(getActivity() == null){
			
			Log.d("出错啦啦啦啦啦啦","出错：   getActivity() is null");
			System.out.println("getActivity() is null");
		}
		else{
			
			Log.d("出错啦啦啦啦啦啦","竟然没有出错");
			System.out.println("竟然没有出错");
			this.context = getActivity();
		}
		
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
		
//		//传递viewpager到标题管理器
//		TopBarManager.getInstance().setMedicineVp(mViewPager);
//		//设置切换页面的监听
//		mViewPager.setOnPageChangeListener(TopBarManager.getInstance());
		
		final List<Fragment> listViews = new ArrayList<Fragment>();

		//listViews.add(new TestFragment1(context));
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
		
		
		return view;
	}

}
