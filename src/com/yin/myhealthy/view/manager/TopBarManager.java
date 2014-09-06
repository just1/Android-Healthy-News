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
	 * Tabѡ�������
	 */
	private String mTextviewArray[] = { "��Ѷ", "��ʳ", "ҩƷ", "�ٿ�" };

	/*
	 * ��������Ӧ������
	 */

	// ���峣����ʾ��ǰ�����ĸ�ҳ��,�������������˳�����һ��
	private final int NEWS = 0;
	private final int DIET = 1;
	private final int MEDICINE = 2;
	private final int KNOWLEDGE = 3;

	// ��ǰҳ���
	private int currentPage = NEWS;

	private Activity activity;

	/*
	 * ����ҳ�洦��
	 */

	// ����ҳ�淽��
	private ViewPager vp_news;
	private RadioGroup rg_news;
	private LinearLayout ll_news;

	private String NewsCategoryList[] = { "��ҵҪ��", // id=1
			"ҽ������", // id=2
			"������ʿ", // id=3
			"ҩƷ����", // id=4
			"ʳƷ����", // id=5
			"����ȵ�", // id=6
			"������Ѷ" // id=7
	};

	private int rb_news_arry[] = { R.id.rb_topbar_news_1,
			R.id.rb_topbar_news_2, R.id.rb_topbar_news_3,
			R.id.rb_topbar_news_4, R.id.rb_topbar_news_5,
			R.id.rb_topbar_news_6, R.id.rb_topbar_news_7, };
	
	private List<RadioButton> rbList = new ArrayList<RadioButton>();
	
	

	// ��ʳ����
	private ViewPager vp_diet;
	private RadioGroup rg_diet;
	private LinearLayout ll_diet;

	// ҩ�䷽��
	private ViewPager vp_medicine;
	private RadioGroup rg_medicine;
	private LinearLayout ll_medicine;

	// �ٿƷ���
	private ViewPager vp_knowledge;
	private RadioGroup rg_knowledge;
	private LinearLayout ll_knowledge;

	// ����ģʽ
	// 1.���徲̬����
	private static TopBarManager instance = null;

	// 2.���캯��˽�л�
	private TopBarManager() {
	}

	// 3.���干�з�����ȡTopBarManager����
	public static TopBarManager getInstance() {
		if (instance == null) {
			instance = new TopBarManager();
		}
		return instance;
	}

	/*
	 * ��ʼ������Viewpager
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

	// ��ʼ����ť�ؼ�
	public void initView(Activity activity) {
		this.activity = activity;

		// ����ҳ��
		ll_news = (LinearLayout) activity.findViewById(R.id.ll_topbar_news);
		rg_news = (RadioGroup) activity.findViewById(R.id.rg_topbar_news);
		ll_news.setVisibility(View.GONE); // ���ò��ɼ�

		for(int i=0;i<rb_news_arry.length;i++){
			rbList.add((RadioButton) activity.findViewById(rb_news_arry[i]));
		}
		
		
		
		setListener(); // ���ü���
		reflashView(); // ˢ��ҳ��

	}

	// ���ü���
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
		// �����������
		// ll_news.removeAllViews();

		// ���ݵ�ǰҳ��������ñ���
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
		// ���ҳ�����õ�ǰҳ���
		for (int i = 0; i < mTextviewArray.length; i++) {
			if (tabId == mTextviewArray[i]) {
				currentPage = i;
				Log.d("onTabChanged", "��ǰҳ�ţ� " + String.valueOf(i));
			}
		}

		// ˢ�±�����
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

	
	//ͨ��handler
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			
			if(msg.what == CHANGE_TOP_BAR){
				int id = (Integer) msg.obj;
				
				/*
				 * ����ֱ�Ӵ�radiogroup���������radiobuttonѡ�У��ᵼ��viewpager��ͣ���л�
				 * ������Ϊ��radiogroup��������check���ᵼ��radiobutton��click���ظ����úܶ��
				 * radiobutton�ĵ���¼�����viewpager�л�������viewpager�ͻ᲻ͣ�л�
				 * 
				 * ���������ֱ�ӵ��ö�Ӧ��radiobutton��setChecked
				 * */
				
				if (currentPage == NEWS) {
					
					
					//rg_news.check(rb_news_arry[id]);
					rbList.get(id).setChecked(true);
				}
			}
		};
	};

	
	
	@Override
	public void onPageSelected(int id) {
		//����ֱ�Ӹ�RadioGroup�ᵼ�»���������,���Բ���message+handler
//		if (currentPage == NEWS) {
//			rg_news.check(rb_news_arry[id]);
//		}
		
		Message msg = new Message();
		msg.what = CHANGE_TOP_BAR;
		msg.obj = id;
		handler.sendMessage(msg);
	}
		
	
}
