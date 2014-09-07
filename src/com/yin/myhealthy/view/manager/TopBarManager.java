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

	/*
	 * ��Ӧ�б���: "��ҵҪ��", // id=1 "ҽ������", // id=2 "������ʿ", // id=3 "ҩƷ����", // id=4
	 * "ʳƷ����", // id=5 "����ȵ�", // id=6 "������Ѷ" // id=7
	 */
	private int rb_news_id_arry[] = { R.id.rb_topbar_news_1,
			R.id.rb_topbar_news_2, R.id.rb_topbar_news_3,
			R.id.rb_topbar_news_4, R.id.rb_topbar_news_5,
			R.id.rb_topbar_news_6, R.id.rb_topbar_news_7, };

	private List<RadioButton> rb_news_list = new ArrayList<RadioButton>();

	// ��ʳ����

	/*
	 * ���� id:1 ���� id:2 �·� id:3 ������Ѫ�� id:4 ��Ŀ id:5 ���� id:6 ��Ƣ id:7 ��θ id:8 ���
	 * id:9 ���� id:10 �� id:11 ǿ�� id:12 ׳�� id:13 ���ջ��� id:14 ͨѪ id:15 ������� id:16
	 * ����ֹʹ id:17 �������� id:18 �ְ����� id:19 ���� id:20 �������� id:21 ��˥������ id:22 ������Ч
	 * id:23 �������� id:24 ��������� id:25 ���� id:26 ������� id:27 ��ͷʹͷ�� id:28 ���� id:29
	 */

	private ViewPager vp_diet;
	private RadioGroup rg_diet;
	private LinearLayout ll_diet;

	private int rb_diet_id_arry[] = { R.id.rb_topbar_diet_1,
			R.id.rb_topbar_diet_2, R.id.rb_topbar_diet_3,
			R.id.rb_topbar_diet_4, R.id.rb_topbar_diet_5,
			R.id.rb_topbar_diet_6, R.id.rb_topbar_diet_7, };

	private List<RadioButton> rb_diet_list = new ArrayList<RadioButton>();

	// ҩ�䷽��
	/*
	 * �Բ���ҩid:1 ����˼������id:2 ѪҺ������id:3 ˮ����ʼ����id:4 ����������id:5 ��ʪ���߿�id:6 ������id:7
	 * ��/����id:8 �ڷ���ʧ��id:9 ����id:10 �ε�����ҩid:11 ����Ѫ��id:12 ά���ؼ�Ӫ����id:13 ������ҩid:14
	 * ������ҩid:15 �п���ҩid:16 ��ͥ����id:17 ����ϵͳ��id:18 �����ҩid:19 ��θ��ҩid:20 Ƥ����ҩid:21
	 * ��ð����id:22 ������ҩid:203 ����id:201
	 */

	private ViewPager vp_medicine;
	private RadioGroup rg_medicine;
	private LinearLayout ll_medicine;

	private int rb_medicine_id_arry[] = { R.id.rb_topbar_medicine_1,
			R.id.rb_topbar_medicine_2, R.id.rb_topbar_medicine_3,
			R.id.rb_topbar_medicine_4, R.id.rb_topbar_medicine_5,
			R.id.rb_topbar_medicine_6, R.id.rb_topbar_medicine_7, };

	private List<RadioButton> rb_medicine_list = new ArrayList<RadioButton>();

	// �ٿƷ���
	/*
	 * ���˽���id:1 ���ӽ���id:2 ������ʳid:3 ���Խ���id:4 Ů�Ա���id:5 ��Ӥ�ֲ�id:6 ˽������id:7 ��������id:8
	 * ���｡��id:9 �ļ�����id:10 ��������id:11 ҽ�ƻ���id:12 �������id:13
	 */

	private ViewPager vp_knowledge;
	private RadioGroup rg_knowledge;
	private LinearLayout ll_knowledge;

	private int rb_knowledge_id_arry[] = { R.id.rb_topbar_knowledge_1,
			R.id.rb_topbar_knowledge_2, R.id.rb_topbar_knowledge_3,
			R.id.rb_topbar_knowledge_4, R.id.rb_topbar_knowledge_5,
			R.id.rb_topbar_knowledge_6, R.id.rb_topbar_knowledge_7, };

	private List<RadioButton> rb_knowledge_list = new ArrayList<RadioButton>();

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

		for (int i = 0; i < rb_news_id_arry.length; i++) {
			rb_news_list.add((RadioButton) activity
					.findViewById(rb_news_id_arry[i]));
		}

		// ��ʳҳ��
		ll_diet = (LinearLayout) activity.findViewById(R.id.ll_topbar_diet);
		rg_diet = (RadioGroup) activity.findViewById(R.id.rg_topbar_diet);
		ll_diet.setVisibility(View.GONE); // ���ò��ɼ�

		for (int i = 0; i < rb_diet_id_arry.length; i++) {
			rb_diet_list.add((RadioButton) activity
					.findViewById(rb_diet_id_arry[i]));
		}

		// ҩ��ҳ��
		ll_medicine = (LinearLayout) activity
				.findViewById(R.id.ll_topbar_medicine);
		rg_medicine = (RadioGroup) activity
				.findViewById(R.id.rg_topbar_medicine);
		ll_medicine.setVisibility(View.GONE); // ���ò��ɼ�

		for (int i = 0; i < rb_medicine_id_arry.length; i++) {
			rb_medicine_list.add((RadioButton) activity
					.findViewById(rb_medicine_id_arry[i]));
		}

		// �ٿ�ҳ��
		ll_knowledge = (LinearLayout) activity
				.findViewById(R.id.ll_topbar_knowledge);
		rg_knowledge = (RadioGroup) activity
				.findViewById(R.id.rg_topbar_knowledge);
		ll_knowledge.setVisibility(View.GONE); // ���ò��ɼ�

		for (int i = 0; i < rb_knowledge_id_arry.length; i++) {
			rb_knowledge_list.add((RadioButton) activity
					.findViewById(rb_knowledge_id_arry[i]));
		}

		setListener(); // ���ü���
		reflashView(); // ˢ��ҳ��

	}

	// ���ü���
	private void setListener() {
		rg_news.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				for (int i = 0; i < rb_news_id_arry.length; i++) {
					if (checkedId == rb_news_id_arry[i]) {
						vp_news.setCurrentItem(i);
					}
				}
			}
		});

		rg_diet.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				for (int i = 0; i < rb_diet_id_arry.length; i++) {
					if (checkedId == rb_diet_id_arry[i]) {
						vp_diet.setCurrentItem(i);
					}
				}
			}
		});

		rg_medicine.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				for (int i = 0; i < rb_medicine_id_arry.length; i++) {
					if (checkedId == rb_medicine_id_arry[i]) {
						vp_medicine.setCurrentItem(i);
					}
				}
			}
		});

		rg_knowledge.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				for (int i = 0; i < rb_knowledge_id_arry.length; i++) {
					if (checkedId == rb_knowledge_id_arry[i]) {
						vp_knowledge.setCurrentItem(i);
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
			ll_diet.setVisibility(View.GONE);
			ll_medicine.setVisibility(View.GONE);
			ll_knowledge.setVisibility(View.GONE);

			ll_news.setVisibility(View.VISIBLE);
			break;

		case DIET:
			ll_news.setVisibility(View.GONE);
			ll_medicine.setVisibility(View.GONE);
			ll_knowledge.setVisibility(View.GONE);

			ll_diet.setVisibility(View.VISIBLE);
			break;

		case MEDICINE:
			ll_news.setVisibility(View.GONE);
			ll_diet.setVisibility(View.GONE);
			ll_knowledge.setVisibility(View.GONE);

			ll_medicine.setVisibility(View.VISIBLE);
			break;

		case KNOWLEDGE:
			ll_news.setVisibility(View.GONE);
			ll_diet.setVisibility(View.GONE);
			ll_medicine.setVisibility(View.GONE);

			ll_knowledge.setVisibility(View.VISIBLE);
			break;

		}
	}

	// �Եײ��˵�tabHost���м���
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

	// ͨ��handler
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {

			int id = (Integer) msg.obj;

			/*
			 * ����ֱ�Ӵ�radiogroup���������radiobuttonѡ�У��ᵼ��viewpager��ͣ���л�
			 * ������Ϊ��radiogroup��������check���ᵼ��radiobutton��click���ظ����úܶ��
			 * radiobutton�ĵ���¼�����viewpager�л�������viewpager�ͻ᲻ͣ�л�
			 * 
			 * ���������ֱ�ӵ��ö�Ӧ��radiobutton��setChecked
			 */

			if (currentPage == NEWS) {
				// rg_news.check(rb_news_arry[id]);
				
				//��ֹviewpager�����ҳ���radiogroup���ʱ�����
				if (rb_news_list.size() > id) {		
					rb_news_list.get(id).setChecked(true);
				}
			}

			if (currentPage == DIET) {
				if (rb_diet_list.size() > id) {
					rb_diet_list.get(id).setChecked(true);
				}
			}

			if (currentPage == MEDICINE) {
				if (rb_medicine_list.size() > id) {
					rb_medicine_list.get(id).setChecked(true);
				}
			}

			if (currentPage == KNOWLEDGE) {
				if (rb_knowledge_list.size() > id) {
					rb_knowledge_list.get(id).setChecked(true);
				}
			}
		};
	};

	@Override
	public void onPageSelected(int id) {
		// ����ֱ�Ӹ�RadioGroup�ᵼ�»���������,���Բ���message+handler
		// if (currentPage == NEWS) {
		// rg_news.check(rb_news_arry[id]);
		// }

		Message msg = new Message();
		msg.obj = id;
		handler.sendMessage(msg);
	}

}
