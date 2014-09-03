package com.yin.myhealthy.view;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yin.myhealthy.adapter.PullDownListViewAdapter;
import com.yin.myhealthy.bean.KeyValuesBean;
import com.yin.myhealthy.bean.healthyknow.HealthyKnowListBean;
import com.yin.myhealthy.engine.HealthyKnowEngine;

//�Զ������������ݵ�ListView
public class AutoGetDateListView extends PullDownListView {

	public Context context = null;
	//private PullDownListView lv;
	private PullDownListViewAdapter adapter;
	protected List<String> titleList = new ArrayList<String>();
	protected List<String> imgList = new ArrayList<String>();
	private String apiUrl = "http://www.yi18.net/";

	public AutoGetDateListView(Context context) {
		super(context);
		this.context = context;

		// ��ʼ��ListView
		initView();
		// �������磬��ȡ����֪ʶ�����б�
		getListViewDate();
	}

	public AutoGetDateListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;

		// ��ʼ��ListView
		initView();
		// �������磬��ȡ����֪ʶ�����б�
		getListViewDate();
	}

	// ��ʼ��ListView
	private void initView() {
		//lv = new PullDownListView(context);
		//ll.addView(lv);

		adapter = new PullDownListViewAdapter(titleList, imgList, context);
		this.setAdapter(adapter);
		this.setMoreViewBtnListener(moreBtnListener);

		// ����ˢ��
		this.setonRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh() {
				new AsyncTask<Void, Void, Void>() {
					protected Void doInBackground(Void... params) {
						try {
							Thread.sleep(500);
							titleList.removeAll(null);
							imgList.removeAll(null);
							getListViewDate();
							// Looper.prepare();
							// Toast.makeText(
							// getActivity().getApplicationContext(),
							// "������30������", 0).show();
							// Looper.loop();
						} catch (Exception e) {
							e.printStackTrace();
						}
						return null;
					}

					@Override
					protected void onPostExecute(Void result) {
						adapter.notifyDataSetChanged();
						onRefreshComplete();
					}
				}.execute(null, null, null);
			}
		});

	}

	// �������磬��������
	protected void getListViewDate() {
		// �������磬��ȡ����֪ʶ�����б�
		List<KeyValuesBean> list = new ArrayList<KeyValuesBean>();

		// ��Ҫ���ص�ҳ��
		list.add(new KeyValuesBean("page", String.valueOf(clickCount)));

		list.add(new KeyValuesBean("limit", "30"));
		list.add(new KeyValuesBean("type", "id"));

		HealthyKnowEngine hke = new HealthyKnowEngine();
		hke.GetHealthyKnowList(list, handler);
	}

	// "���ظ���"���¼�����
	protected int clickCount = 1; // ��¼���µĴ���
	OnClickListener moreBtnListener = new OnClickListener() {

		@Override
		public void onClick(View v) {

			clickCount++; // �ְ���һ��

			getListViewDate();
		}
	};

	// ������ȡ������Ϣ�б�����ݣ�����ʾ��ListView��
	protected Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			String jsonStr = (String) msg.obj;

			AnalyJSONToList(jsonStr);

			// ����ListView����
			adapter.notifyDataSetChanged();
		}
	};

	// ����Json���ݱ���б�
	protected void AnalyJSONToList(String jsonStr) {
		JSONObject obj;
		String healthyKnowListStr = null;
		List<HealthyKnowListBean> HKLBeanList = null;
		try {

			// ��JSONObject��ȡָ���ε�JSON����
			obj = new JSONObject(jsonStr);
			healthyKnowListStr = (String) obj.getJSONArray("yi18").toString();

			// ��GSON�������л���������Ӧ��ʵ����
			HKLBeanList = new Gson().fromJson(healthyKnowListStr,
					new TypeToken<List<HealthyKnowListBean>>() {
					}.getType());

		} catch (JSONException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < HKLBeanList.size(); i++) {
			titleList.add(HKLBeanList.get(i).getTitle());

			imgList.add(apiUrl + HKLBeanList.get(i).getImg());
		}
	}

}
