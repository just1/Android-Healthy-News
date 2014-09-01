package com.yin.myhealthy.adapter;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class ChildViewPagerAdapter extends PagerAdapter {

    private List<View> mListViews;

    public ChildViewPagerAdapter(List<View> listViews) {
        mListViews = listViews;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // TODO Auto-generated method stub
        container.addView(mListViews.get(position), 0);// 添加页卡
        return mListViews.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // TODO Auto-generated method stub
        container.removeView(mListViews.get(position));
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mListViews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        // TODO Auto-generated method stub
        return view == object;
    }

}
