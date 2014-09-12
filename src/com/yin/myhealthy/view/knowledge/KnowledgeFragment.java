package com.yin.myhealthy.view.knowledge;

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
import com.yin.myhealthy.view.ChildViewPager;
import com.yin.myhealthy.view.manager.TopBarManager;


//�ٿ�ҳ��
public class KnowledgeFragment extends Fragment {
	
	private ChildViewPager mViewPager;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_knowledge, container,
				false);

		mViewPager = (ChildViewPager) view.findViewById(R.id.child_vp_knowledge);
		
		final List<Fragment> listViews = new ArrayList<Fragment>();
		

		listViews.add(new KnowledgeSingleFragment(GlobalDate.API_KNOWLEDGE_LIST,"1"));
		listViews.add(new KnowledgeSingleFragment(GlobalDate.API_KNOWLEDGE_LIST,"2"));
		listViews.add(new KnowledgeSingleFragment(GlobalDate.API_KNOWLEDGE_LIST,"3"));
		listViews.add(new KnowledgeSingleFragment(GlobalDate.API_KNOWLEDGE_LIST,"4"));
		listViews.add(new KnowledgeSingleFragment(GlobalDate.API_KNOWLEDGE_LIST,"5"));
		listViews.add(new KnowledgeSingleFragment(GlobalDate.API_KNOWLEDGE_LIST,"6"));
		listViews.add(new KnowledgeSingleFragment(GlobalDate.API_KNOWLEDGE_LIST,"7"));

		
		mViewPager.setAdapter(new MyFragmentPagerAdapter(getChildFragmentManager(), listViews));
		
		
		//����viewpager�����������
		TopBarManager.getInstance().setKnoweledgeVp(mViewPager);
		//�����л�ҳ��ļ���
		mViewPager.setOnPageChangeListener(TopBarManager.getInstance());
		
		return view;
	}
	
}
