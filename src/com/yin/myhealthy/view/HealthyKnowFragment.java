package com.yin.myhealthy.view;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.myhealthy.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yin.myhealthy.MainActivity;
import com.yin.myhealthy.adapter.PullDownListViewAdapter;
import com.yin.myhealthy.bean.KeyValuesBean;
import com.yin.myhealthy.bean.healthyknow.HealthyKnowListBean;
import com.yin.myhealthy.engine.HealthyKnowEngine;
import com.yin.myhealthy.view.PullDownListView.OnRefreshListener;

//������Ѷҳ��
public class HealthyKnowFragment extends Fragment{

	public Context context = null;
	private LinearLayout ll;
	private PullDownListView lv;
	private PullDownListViewAdapter adapter;
	private List<String> titleList = new ArrayList<String>();
	private List<String> imgList = new ArrayList<String>();
	
	
	public HealthyKnowFragment(Context context) {
		super();
		this.context = context;
	}
	
	//��ʼ��ListView
	private void initListView() {
		lv = new PullDownListView(context);
		ll.addView(lv);

		adapter = new PullDownListViewAdapter(titleList, imgList,context);
		lv.setAdapter(adapter);
		lv.setMoreViewBtnListener(moreBtnListener);

		lv.setonRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh() {
				new AsyncTask<Void, Void, Void>() {
					protected Void doInBackground(Void... params) {
						try {
							Thread.sleep(1000);
						} catch (Exception e) {
							e.printStackTrace();
						}
						titleList.add("ˢ�º���ӵ�����");
						return null;
					}

					@Override
					protected void onPostExecute(Void result) {
						adapter.notifyDataSetChanged();
						lv.onRefreshComplete();
					}
				}.execute(null, null, null);
			}
		});

	}

	// ��¼���µĴ���
	private int clickCount = 1;
	OnClickListener moreBtnListener = new OnClickListener() {

		@Override
		public void onClick(View v) {

			clickCount++; // �ְ���һ��

			// �������磬��ȡ����֪ʶ�����б�
			List<KeyValuesBean> list = new ArrayList<KeyValuesBean>();

			// ��Ҫ���ص�ҳ��
			list.add(new KeyValuesBean("page",String.valueOf(clickCount)));

			list.add(new KeyValuesBean("limit", "30"));
			list.add(new KeyValuesBean("type", "id"));

			HealthyKnowEngine hke = new HealthyKnowEngine();
			hke.GetHealthyKnowList(list, handler);
		}
	};

	// ������ȡ������Ϣ�б�����ݣ�����ʾ��ListView��
	Handler handler = new Handler() {

		private List<HealthyKnowListBean> HKLBeanList = null;

		@Override
		public void handleMessage(Message msg) {
			String jsonStr = (String) msg.obj;

			JSONObject obj;
			String healthyKnowListStr = null;

			try {

				// ��JSONObject��ȡָ���ε�JSON����
				obj = new JSONObject(jsonStr);
				healthyKnowListStr = (String) obj.getJSONArray("yi18")
						.toString();

				// ��GSON�������л���������Ӧ��ʵ����
				HKLBeanList = new Gson().fromJson(healthyKnowListStr,
						new TypeToken<List<HealthyKnowListBean>>() {
						}.getType());

			} catch (JSONException e) {
				e.printStackTrace();
			}

			for (int i = 0; i < HKLBeanList.size(); i++) {
				titleList.add(HKLBeanList.get(i).getTitle());
				
				imgList.add("http://www.yi18.net/"+HKLBeanList.get(i).getImg());
			}

			// ����ListView����
			adapter.notifyDataSetChanged();
		}
	};

	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_lic, container, false);
		
		ll = (LinearLayout) view.findViewById(R.id.ll_frag_healthyknow);

		// ��ʼ��ListView
		initListView();

		// �������磬��ȡ����֪ʶ�����б�
		List<KeyValuesBean> list = new ArrayList<KeyValuesBean>();
		list.add(new KeyValuesBean("page", "1"));
		list.add(new KeyValuesBean("limit", "30"));
		list.add(new KeyValuesBean("type", "id"));

		HealthyKnowEngine hke = new HealthyKnowEngine();

		hke.GetHealthyKnowList(list, handler);
		
		
		return view;
	}
	
	
//	@Override
//	public View onCreateView(LayoutInflater inflater, ViewGroup container,
//			Bundle savedInstanceState) {
//		View view = inflater.inflate(R.layout.fragment_lic, container, false);
//		return view;
//	}
	
}
