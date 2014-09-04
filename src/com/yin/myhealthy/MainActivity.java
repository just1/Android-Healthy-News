package com.yin.myhealthy;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.Window;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;

import com.example.myhealthy.R;
import com.yin.myhealthy.view.DietFragment;
import com.yin.myhealthy.view.HealthyKnowFragment;
import com.yin.myhealthy.view.KnowledgeFragment;
import com.yin.myhealthy.view.MedicineFragment;
import com.yin.myhealthy.view.manager.TopBarManager;

public class MainActivity extends FragmentActivity {
	// ����һ������
	private LayoutInflater layoutInflater;

	/**
	 * fragmentTabHost
	 */
	private FragmentTabHost mTabHost;

	/**
	 * ������������Ű�ťͼƬ
	 */
	private int mImageViewArray[] = { R.drawable.buttombar_healthyknow,
			R.drawable.buttombar_diet, R.drawable.buttombar_medicine,
			R.drawable.buttombar_knowledge };

	/**
	 * �������������Fragment����
	 */
	 private Class fragmentArray[] = { HealthyKnowFragment.class,
	 DietFragment.class, MedicineFragment.class, KnowledgeFragment.class };

	/**
	 * Tabѡ�������
	 */
	private String mTextviewArray[] = { "��Ѷ", "��ʳ", "ҩƷ", "�ٿ�" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// �������ر�����,������setContentView()ǰ����
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		initView();

	}
	
	
	private void initView(){
		// ʵ�������ֶ���
		layoutInflater = LayoutInflater.from(this);

		// ʵ����TabHost���󣬵õ�TabHost
		mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
		
		
		
		TopBarManager.getInstance().initView(MainActivity.this);
		
		//�󶨶�������������TopManager
		mTabHost.setOnTabChangedListener(TopBarManager.getInstance());
		
		
		// �õ�fragment�ĸ���
		int count = fragmentArray.length;

		for (int i = 0; i < count; i++) {
			// Ϊÿһ��Tab��ť����ͼ�ꡢ���ֺ�����
			TabSpec tabSpec = mTabHost.newTabSpec(mTextviewArray[i])
					.setIndicator(mTextviewArray[i],
							getResources().getDrawable(mImageViewArray[i]));
			// ��Tab��ť��ӽ�Tabѡ���
			mTabHost.addTab(tabSpec, fragmentArray[i], null);
			// ����Tab��ť�ı���
			// mTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.selector_tab_background);
		}
	}

}
