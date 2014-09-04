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

	// 使用Viewpager作为软件主框架
	// private NoSlipViewPager vp;
	// List<Fragment> fragmentList = new ArrayList<Fragment>();
	// MainFragmentPagerAdapter adapter;

	// 使用FragmentTabHost作为软件主框架
	// private RadioGroup rgs;
	// public List<Fragment> fragmentList = new ArrayList<Fragment>();

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
	 private Class fragmentArray[] = { HealthyKnowFragment.class,
	 DietFragment.class, MedicineFragment.class, KnowledgeFragment.class };

	/**
	 * Tab选项卡的文字
	 */
	private String mTextviewArray[] = { "精选", "排行", "分类", "搜索" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// 设置隐藏标题栏,必须在setContentView()前调用
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		// 使用Viewpager作为软件主框架
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

		// 使用FragmentTabHost作为软件主框架
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

		// 实例化布局对象
		layoutInflater = LayoutInflater.from(this);

		// 实例化TabHost对象，得到TabHost
		mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

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

	private View getTabItemView(int index) {
		View view = layoutInflater.inflate(R.layout.tab_item_view, null);

		ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
		imageView.setImageResource(mImageViewArray[index]);

		TextView textView = (TextView) view.findViewById(R.id.textview);
		textView.setText(mTextviewArray[index]);

		return view;
	}

}
