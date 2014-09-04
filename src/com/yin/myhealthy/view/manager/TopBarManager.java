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
	 * Tab选项卡的文字
	 */
	private String mTextviewArray[] = { "资讯", "饮食", "药品", "百科" };
	
	//定义常量表示当前处于哪个页面,常量与标题名的顺序必须一致
	private final int HEALTHY_KNOW = 0;
	private final int DIET = 1;
	private final int MEDICINE = 2;
	private final int KNOWLEDGE = 3;
	
	//当前页面号
	private int currentPage = HEALTHY_KNOW;
	
	private Activity activity;
	
	
	//页面处理
	//存放东西的布局
	private LinearLayout ll;
	
	//药品页面的viewpager
	private ViewPager vp_medicine;
	private String medicine_tab[] = { "资讯", "饮食"};
	
	
	
	// 单例模式
	// 1.定义静态变量
	private static TopBarManager instance = null;

	// 2.构造函数私有化
	private TopBarManager() {
	}

	// 3.定义共有方法获取TopBarManager对象
	public static TopBarManager getInstance() {
		if (instance == null) {
			instance = new TopBarManager();
		}
		return instance;
	}

	
	/*
	 * 初始化各个Viewpager
	 * */
	public void setMedicineVp(ViewPager vp){
		this.vp_medicine = vp;
	}
	
	
	
	
	// 初始化按钮控件
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
		//清楚所有已有
		ll.removeAllViews();
		
		//根据当前页面号来设置标题
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
		//如果页面设置当前页面号
		for(int i=0;i<mTextviewArray.length;i++){
			if(tabId == mTextviewArray[i]){
				currentPage = i;
				Log.d("onTabChanged", "当前页号： "+ String.valueOf(i));
			}
		}
		
		//刷新标题栏
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
