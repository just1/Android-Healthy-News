package com.yin.myhealthy.view;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myhealthy.R;
import com.yin.myhealthy.adapter.ChildViewPagerAdapter;

//ÒûÊ³Ò³Ãæ
public class DietFragment extends Fragment {

    private Activity mActivity;
    private LayoutInflater mLayoutInflater;
    private ChildViewPager mViewPager;

    private int[] mLayouts = new int[] { R.layout.guide_page_layout_one,
            R.layout.guide_page_layout_two, R.layout.guide_page_layout_three,
            R.layout.guide_page_layout_four };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
    }
	
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_diet, container, false);

		mViewPager = (ChildViewPager) view.findViewById(R.id.child_vp);
        List<View> listViews = new ArrayList<View>();
        for (int i = 0; i < mLayouts.length; i++) {
            listViews.add(inflater.inflate(mLayouts[i], null));
        }
        mViewPager.setAdapter(new ChildViewPagerAdapter(listViews));
		
		return view;
	}
	
	
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        // TODO Auto-generated method stub
        mActivity = activity;
        super.onAttach(activity);
    }
	

}
