package com.yin.myhealthy.view;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myhealthy.R;
import com.yin.myhealthy.GlobalDate;
import com.yin.myhealthy.adapter.MyFragmentPagerAdapter;
import com.yin.myhealthy.base.SingleNewsFragment;
import com.yin.myhealthy.view.manager.TopBarManager;

//健康资讯页面
public class NewsFragment extends Fragment {
	


	
	
	private ChildViewPager mViewPager;
	private Context context;
	
	
	@Override
	public void onStart() {
		super.onStart();
		
		//应该在这里调用getActivity()才不会返回为null，而不应该在构造函数里面调用
		this.context = getActivity();
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_news, container,
				false);

		mViewPager = (ChildViewPager) view.findViewById(R.id.child_vp_medicine);
		
		//传递viewpager到标题管理器
		TopBarManager.getInstance().setMedicineVp(mViewPager);
		//设置切换页面的监听
		mViewPager.setOnPageChangeListener(TopBarManager.getInstance());
		
		final List<Fragment> listViews = new ArrayList<Fragment>();
		

		listViews.add(new SingleNewsFragment(GlobalDate.API_NEWS_LIST,"1"));
		listViews.add(new SingleNewsFragment(GlobalDate.API_NEWS_LIST,"2"));
		listViews.add(new SingleNewsFragment(GlobalDate.API_NEWS_LIST,"3"));
		listViews.add(new SingleNewsFragment(GlobalDate.API_NEWS_LIST,"4"));
		listViews.add(new SingleNewsFragment(GlobalDate.API_NEWS_LIST,"5"));
		listViews.add(new SingleNewsFragment(GlobalDate.API_NEWS_LIST,"6"));
		listViews.add(new SingleNewsFragment(GlobalDate.API_NEWS_LIST,"7"));
		
		mViewPager.setAdapter(new MyFragmentPagerAdapter(getChildFragmentManager(), listViews));
		
		
		//传递viewpager到标题管理器
		TopBarManager.getInstance().setNewsVp(mViewPager);
		//设置切换页面的监听
		mViewPager.setOnPageChangeListener(TopBarManager.getInstance());
		
		return view;
	}
	

}
