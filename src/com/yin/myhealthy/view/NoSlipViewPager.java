package com.yin.myhealthy.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

//不能滑动的ViewPager
public class NoSlipViewPager extends ViewPager {
	public NoSlipViewPager(Context context) {
		super(context);
	}

	public NoSlipViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	
	//方法1：通过修改scrollTo函数，禁止它滑动，而不是对触摸事件进行处理
	//不过实际的效果会导致其他页面显示不正常
//	private boolean isCanScroll = true;
//	
//	public void setScanScroll(boolean isCanScroll) {
//		this.isCanScroll = isCanScroll;
//	}
//
//	@Override
//	public void scrollTo(int x, int y) {
//		if (isCanScroll) {
//			super.scrollTo(x, y);
//		}
//	}
	
	
	//方法2：对触摸事件进行处理，重写onTouch事件，并不消费事件（返回false）
	/*
	 * 之前曾经为viewpager控件绑定触摸事件监听器ontouchlistener，
	 * 然后重写ontouch事件，并返回false，不过并没有效果，可能是这样会
	 * 导致和原本的ontouch事件发生冲突
	 * 
	 * */
	@Override
	public boolean onTouchEvent(MotionEvent arg0) {
		return false;
	}
}
