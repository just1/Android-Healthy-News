package com.yin.myhealthy;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.widget.RadioGroup;

import com.example.myhealthy.R;
import com.yin.myhealthy.adapter.FragmentTabAdapter;
import com.yin.myhealthy.view.DietFragment;
import com.yin.myhealthy.view.HealthyKnowFragment;
import com.yin.myhealthy.view.KnowledgeFragment;
import com.yin.myhealthy.view.MedicineFragment;

public class MainActivity extends FragmentActivity {

	//ʹ��Viewpager��Ϊ��������
//	private NoSlipViewPager vp;
//	List<Fragment> fragmentList = new ArrayList<Fragment>();
//	MainFragmentPagerAdapter adapter;

	//ʹ��FragmentTabHost��Ϊ��������
    private RadioGroup rgs;
    public List<Fragment> fragmentList = new ArrayList<Fragment>();

    public String hello = "hello ";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// �������ر�����,������setContentView()ǰ����
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		
		//ʹ��Viewpager��Ϊ��������
//		vp = (NoSlipViewPager) findViewById(R.id.vp);
//		
//		fragmentList.add(new HealthyKnowFragment(MainActivity.this));
//		fragmentList.add(new DietFragment());
//		fragmentList.add(new MedicineFragment(MainActivity.this));
//		fragmentList.add(new KnowledgeFragment(MainActivity.this));
//
//		adapter = new MainFragmentPagerAdapter(getSupportFragmentManager(),
//				fragmentList);
//		vp.setAdapter(adapter);
//		
//		TopBarManager.getInstance().init(this);
//		ButtomBarManager.getInstance().init(this);
		
		
		//ʹ��FragmentTabHost��Ϊ��������
		fragmentList.add(new HealthyKnowFragment(MainActivity.this));
		fragmentList.add(new DietFragment());
		fragmentList.add(new MedicineFragment(MainActivity.this));
		fragmentList.add(new KnowledgeFragment(MainActivity.this));
		
		rgs = (RadioGroup) findViewById(R.id.tabs_rg);

        FragmentTabAdapter tabAdapter = new FragmentTabAdapter(this, fragmentList, R.id.tab_content, rgs);
        tabAdapter.setOnRgsExtraCheckedChangedListener(new FragmentTabAdapter.OnRgsExtraCheckedChangedListener(){
            @Override
            public void OnRgsExtraCheckedChanged(RadioGroup radioGroup, int checkedId, int index) {
                System.out.println("Extra---- " + index + " checked!!! ");
            }
        });
	}


}
