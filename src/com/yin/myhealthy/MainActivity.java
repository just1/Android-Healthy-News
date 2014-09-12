package com.yin.myhealthy;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.Window;
import android.widget.TabHost.TabSpec;
import cn.sharesdk.framework.ShareSDK;

import com.yin.myhealthy.view.diet.DietFragment;
import com.yin.myhealthy.view.knowledge.KnowledgeFragment;
import com.yin.myhealthy.view.manager.TopBarManager;
import com.yin.myhealthy.view.medicine.MedicineFragment;
import com.yin.myhealthy.view.news.NewsFragment;

public class MainActivity extends FragmentActivity {
	// 定义一个布局
	private LayoutInflater layoutInflater;

	/**
	 * fragmentTabHost
	 */
	private FragmentTabHost mTabHost;

	/**
	 * 定义数组来存放按钮图片
	 */
	private int mImageViewArray[] = { R.drawable.buttombar_healthyknow,
			R.drawable.buttombar_diet, R.drawable.buttombar_medicine,
			R.drawable.buttombar_knowledge };

	/**
	 * 定义数组来存放Fragment界面
	 */
	 private Class fragmentArray[] = { NewsFragment.class,
	 DietFragment.class, MedicineFragment.class, KnowledgeFragment.class };

	/**
	 * Tab选项卡的文字
	 */
	private String mTextviewArray[] = { "资讯", "饮食", "药箱", "百科" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// 设置隐藏标题栏,必须在setContentView()前调用
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		//初始化shareSDK
		ShareSDK.initSDK(this);

		initView();

	}
	
	
	private void initView(){
		// 实例化布局对象
		layoutInflater = LayoutInflater.from(this);

		// 实例化TabHost对象，得到TabHost
		mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
		
		
		
		TopBarManager.getInstance().initView(MainActivity.this);
		
		//绑定顶部导航管理器TopManager
		mTabHost.setOnTabChangedListener(TopBarManager.getInstance());
		
		
		// 得到fragment的个数
		int count = fragmentArray.length;

		for (int i = 0; i < count; i++) {
			// 为每一个Tab按钮设置图标、文字和内容
			TabSpec tabSpec = mTabHost.newTabSpec(mTextviewArray[i])
					.setIndicator(mTextviewArray[i],
							getResources().getDrawable(mImageViewArray[i]));
			// 将Tab按钮添加进Tab选项卡中
			mTabHost.addTab(tabSpec, fragmentArray[i], null);
			// 设置Tab按钮的背景
			// mTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.selector_tab_background);
		}
	}

}
