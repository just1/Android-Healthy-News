package com.yin.myhealthy.view.manager;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.example.myhealthy.R;


public class TopBarManager {
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

	// 初始化按钮控件
	private ImageButton imbtn_sing;
	private ImageButton imbtn_songlist;
	private ImageButton imbtn_lic;
	private ViewPager vp;

	public void init(Activity activity) {

		imbtn_sing = (ImageButton) activity
				.findViewById(R.id.imbtn_topbar_sing);
		imbtn_songlist = (ImageButton) activity
				.findViewById(R.id.imbtn_topbar_songlist);
		imbtn_lic = (ImageButton) activity.findViewById(R.id.imbtn_topbar_lic);
		vp = (ViewPager) activity.findViewById(R.id.vp);

		setListener();

	}

	// 监听按钮
	private void setListener() {
		imbtn_sing.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				vp.setCurrentItem(0);

			}
		});

		imbtn_songlist.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				vp.setCurrentItem(1);
			}
		});

		imbtn_lic.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				vp.setCurrentItem(2);
			}
		});

	}
}
