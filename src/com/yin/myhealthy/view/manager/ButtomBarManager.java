package com.yin.myhealthy.view.manager;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.example.myhealthy.R;

public class ButtomBarManager {
//	// 单例模式
//	// 1.定义静态变量
//	private static ButtomBarManager instance = null;
//
//	// 2.构造函数私有化
//	private ButtomBarManager() {
//	}
//
//	// 3.定义共有方法获取TopBarManager对象
//	public static ButtomBarManager getInstance() {
//		if (instance == null) {
//			instance = new ButtomBarManager();
//		}
//		return instance;
//	}
//
//	// 初始化按钮控件
//	private ImageButton imbtn_healthyknow;
//	private ImageButton imbtn_diet;
//	private ImageButton imbtn_medicine;
//	private ImageButton imbtn_knowledge;
//
//	private ViewPager vp;
//
//	public void init(Activity activity) {
//
//		imbtn_healthyknow = (ImageButton) activity
//				.findViewById(R.id.imgbtn_bottombar_healthyknow);
//		imbtn_diet = (ImageButton) activity
//				.findViewById(R.id.imgbtn_bottombar_diet);
//		imbtn_medicine = (ImageButton) activity
//				.findViewById(R.id.imgbtn_bottombar_medicine);
//		imbtn_knowledge = (ImageButton) activity
//				.findViewById(R.id.imgbtn_bottombar_knowledge);
//
//		vp = (ViewPager) activity.findViewById(R.id.vp);
//
//		setListener();
//
//	}
//
//	// 监听按钮
//	private void setListener() {
//		imbtn_healthyknow.setOnClickListener(new OnClickListener() {
//			public void onClick(View v) {
//				vp.setCurrentItem(0);
//
//			}
//		});
//
//		imbtn_diet.setOnClickListener(new OnClickListener() {
//			public void onClick(View v) {
//				vp.setCurrentItem(1);
//			}
//		});
//
//		imbtn_medicine.setOnClickListener(new OnClickListener() {
//			public void onClick(View v) {
//				vp.setCurrentItem(2);
//			}
//		});
//
//		imbtn_knowledge.setOnClickListener(new OnClickListener() {
//			public void onClick(View v) {
//				vp.setCurrentItem(3);
//			}
//		});
//
//	}
}
