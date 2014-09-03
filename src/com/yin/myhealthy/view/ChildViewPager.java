package com.yin.myhealthy.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class ChildViewPager extends ViewPager {
	private float mDX, mDY, mLX, mLY;
	int mLastAct = -1;
	boolean mIntercept = false;
	
	public ChildViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ChildViewPager(Context context) {
		super(context);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			mDX = mDY = 0f;
			mLX = ev.getX();
			mLY = ev.getY();

			break;

		case MotionEvent.ACTION_MOVE:
			final float curX = ev.getX();
			final float curY = ev.getY();
			mDX += Math.abs(curX - mLX);
			mDY += Math.abs(curY - mLY);
			mLX = curX;
			mLY = curY;

			if (mIntercept && mLastAct == MotionEvent.ACTION_MOVE) {
				return false;
			}

			//��������һ�������ô���������������
			//Ŀ����������Ƕ�׵�ListView�ܹ��໥��Ӱ��
			if(mDX-mDY > 5){
				return true;
			}
			
			
			if (mDX > mDY) {
				mIntercept = true;
				mLastAct = MotionEvent.ACTION_MOVE;
				return false;
			}

		}
		mLastAct = ev.getAction();
		mIntercept = false;
		return super.onInterceptTouchEvent(ev);
	}

	@Override
	public boolean onTouchEvent(MotionEvent arg0) {
		if (arg0.getAction() == MotionEvent.ACTION_DOWN) {
			// �˾������Ϊ��֪ͨ���ĸ�ViewPager���ڽ��е��Ǳ��ؼ��Ĳ�������Ҫ���ҵĲ������и���
			getParent().requestDisallowInterceptTouchEvent(true);
		}

		if (arg0.getAction() == MotionEvent.ACTION_MOVE) {
			// �˾������Ϊ��֪ͨ���ĸ�ViewPager���ڽ��е��Ǳ��ؼ��Ĳ�������Ҫ���ҵĲ������и���
			getParent().requestDisallowInterceptTouchEvent(true);
		}

		return super.onTouchEvent(arg0);
	}

}