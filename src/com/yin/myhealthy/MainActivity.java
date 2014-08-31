package com.yin.myhealthy;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Window;
import android.view.WindowManager;

import com.example.myhealthy.R;
import com.yin.myhealthy.adapter.MainFragmentPagerAdapter;
import com.yin.myhealthy.view.DietFragment;
import com.yin.myhealthy.view.HealthyKnowFragment;
import com.yin.myhealthy.view.KnowledgeFragment;
import com.yin.myhealthy.view.MedicineFragment;
import com.yin.myhealthy.view.manager.TopBarManager;

public class MainActivity extends FragmentActivity {


	private ViewPager vp;
	List<Fragment> fragmentList = new ArrayList<Fragment>();
	MainFragmentPagerAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// 设置隐藏标题栏,必须在setContentView()前调用
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);


		setContentView(R.layout.activity_main);

		vp = (ViewPager) findViewById(R.id.vp);

		fragmentList.add(new HealthyKnowFragment(MainActivity.this));
		fragmentList.add(new DietFragment());
		fragmentList.add(new MedicineFragment());
		fragmentList.add(new KnowledgeFragment());

		adapter = new MainFragmentPagerAdapter(getSupportFragmentManager(),
				fragmentList);
		vp.setAdapter(adapter);

		
		TopBarManager.getInstance().init(this);
	}


}
