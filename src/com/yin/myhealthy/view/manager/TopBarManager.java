package com.yin.myhealthy.view.manager;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TabHost.OnTabChangeListener;

import com.example.myhealthy.R;


public class TopBarManager implements OnTabChangeListener,OnPageChangeListener{
	
	/**
	 * Tabѡ�������
	 */
	private String mTextviewArray[] = { "��Ѷ", "��ʳ", "ҩƷ", "�ٿ�" };
	
	//���峣����ʾ��ǰ�����ĸ�ҳ��,�������������˳�����һ��
	private final int HEALTHY_KNOW = 0;
	private final int DIET = 1;
	private final int MEDICINE = 2;
	private final int KNOWLEDGE = 3;
	
	//��ǰҳ���
	private int currentPage = HEALTHY_KNOW;
	
	private Activity activity;
	
	
	//ҳ�洦��
	//��Ŷ����Ĳ���
	private LinearLayout ll;
	
	//ҩƷҳ���viewpager
	private ViewPager vp_medicine;
	private String medicine_tab[] = { "��Ѷ", "��ʳ"};
	
	
	
	// ����ģʽ
	// 1.���徲̬����
	private static TopBarManager instance = null;

	// 2.���캯��˽�л�
	private TopBarManager() {
	}

	// 3.���干�з�����ȡTopBarManager����
	public static TopBarManager getInstance() {
		if (instance == null) {
			instance = new TopBarManager();
		}
		return instance;
	}

	
	/*
	 * ��ʼ������Viewpager
	 * */
	public void setMedicineVp(ViewPager vp){
		this.vp_medicine = vp;
	}
	
	
	
	
	// ��ʼ����ť�ؼ�
	public void initView(Activity activity) {
		this.activity = activity;

		ll = (LinearLayout) activity.findViewById(R.id.ll_topbar);
		
		if(activity == null){
			Log.d("topBar", "activity is null");
		}else{
			Log.d("topBar", "activity is not null");
		}
		
		
		if(ll == null){
			Log.d("topBar", "ll is null");
		}else{
			Log.d("topBar", "ll is not null");
		}
		
		reflashView();
	}

	private int vpItemNum;
	private void reflashView(){
		//�����������
		ll.removeAllViews();
		
		//���ݵ�ǰҳ��������ñ���
		switch(currentPage){
		case HEALTHY_KNOW:
			break;
			
		case DIET:
			break;
			
		case MEDICINE:
			for(vpItemNum=0;vpItemNum<2;vpItemNum++){
				Button btn = new Button(activity);
				btn.setText(medicine_tab[vpItemNum]);
				btn.setWidth(1);
				btn.setHeight(50);
				btn.setWidth(50);
				btn.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						vp_medicine.setCurrentItem(vpItemNum+1);
						
					}
				});
				
				
				ll.addView(btn);
				
			}
			
			break;
		case KNOWLEDGE:
			break;
			
		}
	}


	@Override
	public void onTabChanged(String tabId) {
		//���ҳ�����õ�ǰҳ���
		for(int i=0;i<mTextviewArray.length;i++){
			if(tabId == mTextviewArray[i]){
				currentPage = i;
				Log.d("onTabChanged", "��ǰҳ�ţ� "+ String.valueOf(i));
			}
		}
		
		//ˢ�±�����
		reflashView();
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub
		
	}
}
