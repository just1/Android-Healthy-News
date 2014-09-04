package com.yin.myhealthy;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.example.myhealthy.R;
import com.yin.myhealthy.view.DietFragment;
import com.yin.myhealthy.view.HealthyKnowFragment;
import com.yin.myhealthy.view.KnowledgeFragment;
import com.yin.myhealthy.view.MedicineFragment;

public class MainActivity extends FragmentActivity {

	// ʹ��Viewpager��Ϊ��������
	// private NoSlipViewPager vp;
	// List<Fragment> fragmentList = new ArrayList<Fragment>();
	// MainFragmentPagerAdapter adapter;

	// ʹ��FragmentTabHost��Ϊ��������
	// private RadioGroup rgs;
	// public List<Fragment> fragmentList = new ArrayList<Fragment>();

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
	private String mTextviewArray[] = { "��ѡ", "����", "����", "����" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// �������ر�����,������setContentView()ǰ����
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		// ʹ��Viewpager��Ϊ��������
		// vp = (NoSlipViewPager) findViewById(R.id.vp);
		//
		// fragmentList.add(new HealthyKnowFragment(MainActivity.this));
		// fragmentList.add(new DietFragment());
		// fragmentList.add(new MedicineFragment(MainActivity.this));
		// fragmentList.add(new KnowledgeFragment(MainActivity.this));
		//
		// adapter = new MainFragmentPagerAdapter(getSupportFragmentManager(),
		// fragmentList);
		// vp.setAdapter(adapter);
		//
		// TopBarManager.getInstance().init(this);
		// ButtomBarManager.getInstance().init(this);

		// ʹ��FragmentTabHost��Ϊ��������
		// fragmentList.add(new HealthyKnowFragment(MainActivity.this));
		// fragmentList.add(new DietFragment());
		// fragmentList.add(new MedicineFragment(MainActivity.this));
		// fragmentList.add(new KnowledgeFragment(MainActivity.this));
		//
		// rgs = (RadioGroup) findViewById(R.id.tabs_rg);
		//
		// FragmentTabAdapter tabAdapter = new FragmentTabAdapter(this,
		// fragmentList, R.id.tab_content, rgs);
		// tabAdapter.setOnRgsExtraCheckedChangedListener(new
		// FragmentTabAdapter.OnRgsExtraCheckedChangedListener(){
		// @Override
		// public void OnRgsExtraCheckedChanged(RadioGroup radioGroup, int
		// checkedId, int index) {
		// System.out.println("Extra---- " + index + " checked!!! ");
		// }
		// });

		// ʵ�������ֶ���
		layoutInflater = LayoutInflater.from(this);

		// ʵ����TabHost���󣬵õ�TabHost
		mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

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

	private View getTabItemView(int index) {
		View view = layoutInflater.inflate(R.layout.tab_item_view, null);

		ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
		imageView.setImageResource(mImageViewArray[index]);

		TextView textView = (TextView) view.findViewById(R.id.textview);
		textView.setText(mTextviewArray[index]);

		return view;
	}

}
