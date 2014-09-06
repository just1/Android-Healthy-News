package com.yin.myhealthy.view.manager;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost.OnTabChangeListener;

import com.example.myhealthy.R;

public class TopBarManager implements OnTabChangeListener, OnPageChangeListener {

	private final int CHANGE_TOP_BAR = 1;
	
	/**
	 * Tab选项卡的文字
	 */
	private String mTextviewArray[] = { "资讯", "饮食", "药品", "百科" };

	/*
	 * 导航条对应的文字
	 */

	// 定义常量表示当前处于哪个页面,常量与标题名的顺序必须一致
	private final int NEWS = 0;
	private final int DIET = 1;
	private final int MEDICINE = 2;
	private final int KNOWLEDGE = 3;

	// 当前页面号
	private int currentPage = NEWS;

	private Activity activity;

	/*
	 * 各个页面处理
	 */

	// 新闻页面方面
	private ViewPager vp_news;
	private RadioGroup rg_news;
	private LinearLayout ll_news;

	private String NewsCategoryList[] = { "企业要闻", // id=1
			"医疗新闻", // id=2
			"生活贴士", // id=3
			"药品新闻", // id=4
			"食品新闻", // id=5
			"社会热点", // id=6
			"疾病快讯" // id=7
	};

	private int rb_news_arry[] = { R.id.rb_topbar_news_1,
			R.id.rb_topbar_news_2, R.id.rb_topbar_news_3,
			R.id.rb_topbar_news_4, R.id.rb_topbar_news_5,
			R.id.rb_topbar_news_6, R.id.rb_topbar_news_7, };
	
	private List<RadioButton> rbList = new ArrayList<RadioButton>();
	
	

	// 饮食方面
	private ViewPager vp_diet;
	private RadioGroup rg_diet;
	private LinearLayout ll_diet;

	// 药箱方面
	private ViewPager vp_medicine;
	private RadioGroup rg_medicine;
	private LinearLayout ll_medicine;

	// 百科方面
	private ViewPager vp_knowledge;
	private RadioGroup rg_knowledge;
	private LinearLayout ll_knowledge;

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
	 */
	public void setNewsVp(ViewPager vp) {
		this.vp_news = vp;
	}

	public void setDietVp(ViewPager vp) {
		this.vp_diet = vp;
	}

	public void setMedicineVp(ViewPager vp) {
		this.vp_medicine = vp;
	}

	public void setKnoweledgeVp(ViewPager vp) {
		this.vp_knowledge = vp;
	}

	// 初始化按钮控件
	public void initView(Activity activity) {
		this.activity = activity;

		// 新闻页面
		ll_news = (LinearLayout) activity.findViewById(R.id.ll_topbar_news);
		rg_news = (RadioGroup) activity.findViewById(R.id.rg_topbar_news);
		ll_news.setVisibility(View.GONE); // 设置不可见

		for(int i=0;i<rb_news_arry.length;i++){
			rbList.add((RadioButton) activity.findViewById(rb_news_arry[i]));
		}
		
		
		
		setListener(); // 设置监听
		reflashView(); // 刷新页面

	}

	// 设置监听
	private void setListener() {
		rg_news.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				for (int i = 0; i < rb_news_arry.length; i++) {
					if (checkedId == rb_news_arry[i]) {
						vp_news.setCurrentItem(i);
					}
				}
			}
		});

	}


	private void reflashView() {
		// 清楚所有已有
		// ll_news.removeAllViews();

		// 根据当前页面号来设置标题
		switch (currentPage) {
		case NEWS:
			ll_news.setVisibility(View.VISIBLE);

			break;

		case DIET:
			break;

		case MEDICINE:
			ll_medicine.setVisibility(View.VISIBLE);

			break;
		case KNOWLEDGE:
			break;

		}
	}

	@Override
	public void onTabChanged(String tabId) {
		// 如果页面设置当前页面号
		for (int i = 0; i < mTextviewArray.length; i++) {
			if (tabId == mTextviewArray[i]) {
				currentPage = i;
				Log.d("onTabChanged", "当前页号： " + String.valueOf(i));
			}
		}

		// 刷新标题栏
		reflashView();
	}

	@Override
	public void onPageScrollStateChanged(int id) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	
	//通过handler
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			
			if(msg.what == CHANGE_TOP_BAR){
				int id = (Integer) msg.obj;
				
				if (currentPage == NEWS) {
					
					//
					/*
					 * 假如直接从radiogroup设置里面的radiobutton选中，会导致viewpager不停地切换
					 * 这是因为从radiogroup里面设置check，会导致radiobutton的click被重复调用很多次
					 * radiobutton的点击事件能让viewpager切换，所以viewpager就会不停切换
					 * 
					 * 解决方法：直接调用对应的radiobutton的setChecked
					 * */
					//rg_news.check(rb_news_arry[id]);
					rbList.get(id).setChecked(true);
				}
			}
		};
	};

	
	
	@Override
	public void onPageSelected(int id) {
		//假如直接改RadioGroup会导致滑动不流畅,所以采用message+handler
//		if (currentPage == NEWS) {
//			rg_news.check(rb_news_arry[id]);
//		}
		
		Message msg = new Message();
		msg.what = CHANGE_TOP_BAR;
		msg.obj = id;
		handler.sendMessage(msg);
	}
		
	
}
