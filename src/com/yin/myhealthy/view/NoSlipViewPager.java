package com.yin.myhealthy.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

//���ܻ�����ViewPager
public class NoSlipViewPager extends ViewPager {
	public NoSlipViewPager(Context context) {
		super(context);
	}

	public NoSlipViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	
	//����1��ͨ���޸�scrollTo��������ֹ�������������ǶԴ����¼����д���
	//����ʵ�ʵ�Ч���ᵼ������ҳ����ʾ������
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
	
	
	//����2���Դ����¼����д�����дonTouch�¼������������¼�������false��
	/*
	 * ֮ǰ����Ϊviewpager�ؼ��󶨴����¼�������ontouchlistener��
	 * Ȼ����дontouch�¼���������false��������û��Ч����������������
	 * ���º�ԭ����ontouch�¼�������ͻ
	 * 
	 * */
	@Override
	public boolean onTouchEvent(MotionEvent arg0) {
		return false;
	}
}
