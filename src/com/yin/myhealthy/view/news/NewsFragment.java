package com.yin.myhealthy.view.news;

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
import com.yin.myhealthy.controller.NewsController;
import com.yin.myhealthy.view.ChildViewPager;
import com.yin.myhealthy.view.manager.TopBarManager;

//������Ѷҳ��
public class NewsFragment extends Fragment {

	private ChildViewPager mViewPager;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_news, container, false);

		mViewPager = (ChildViewPager) view.findViewById(R.id.child_vp_news);

		final List<Fragment> listViews = new ArrayList<Fragment>();

		listViews.add(new NewsSingleFragment(GlobalDate.API_NEWS_LIST, "1",
				new NewsController()));
		listViews.add(new NewsSingleFragment(GlobalDate.API_NEWS_LIST, "2",
				new NewsController()));
		listViews.add(new NewsSingleFragment(GlobalDate.API_NEWS_LIST, "3",
				new NewsController()));
		listViews.add(new NewsSingleFragment(GlobalDate.API_NEWS_LIST, "4",
				new NewsController()));
		listViews.add(new NewsSingleFragment(GlobalDate.API_NEWS_LIST, "5",
				new NewsController()));
		listViews.add(new NewsSingleFragment(GlobalDate.API_NEWS_LIST, "6",
				new NewsController()));
		listViews.add(new NewsSingleFragment(GlobalDate.API_NEWS_LIST, "7",
				new NewsController()));

		mViewPager.setAdapter(new MyFragmentPagerAdapter(
				getChildFragmentManager(), listViews));

		// ����viewpager�����������
		TopBarManager.getInstance().setNewsVp(mViewPager);
		// �����л�ҳ��ļ���
		mViewPager.setOnPageChangeListener(TopBarManager.getInstance());

		return view;
	}

}
