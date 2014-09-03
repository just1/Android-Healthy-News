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
import com.yin.myhealthy.adapter.ChildViewPagerAdapter;


//°Ù¿ÆÒ³Ãæ
public class KnowledgeFragment extends Fragment {
	
    private int[] mLayouts = new int[] { R.layout.guide_page_layout_one,
            R.layout.guide_page_layout_two, R.layout.guide_page_layout_three,
            R.layout.guide_page_layout_four };
    
    private ChildViewPager mViewPager;
	private Context context;
	
	
	public KnowledgeFragment(Context context) {
		super();
		this.context = context;
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_knowledge, container, false);
		
		mViewPager = (ChildViewPager) view.findViewById(R.id.child_vp);
        List<View> listViews = new ArrayList<View>();
        listViews.add(new AutoGetDateListView(context));
        listViews.add(new AutoGetDateListView(context));
//        for (int i = 0; i < mLayouts.length; i++) {
//        	
//            listViews.add(inflater.inflate(mLayouts[i], null));
//        }
        mViewPager.setAdapter(new ChildViewPagerAdapter(listViews));
		
		return view;
	}
	
}
