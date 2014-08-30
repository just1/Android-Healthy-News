package com.yin.myhealthy;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.LinearLayout;

import com.example.myhealthy.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yin.myhealthy.adapter.PullDownListViewAdapter;
import com.yin.myhealthy.bean.KeyValuesBean;
import com.yin.myhealthy.bean.healthyknow.HealthyKnowListBean;
import com.yin.myhealthy.engine.HealthyKnowEngine;
import com.yin.myhealthy.view.PullDownListView;
import com.yin.myhealthy.view.PullDownListView.OnRefreshListener;

public class MainActivity extends Activity {
	
	public Context context = this;
	private LinearLayout ll;
	private PullDownListView lv ;
	private PullDownListViewAdapter adapter;
	
	
	//������ȡ������Ϣ�б�����ݣ�����ʾ��ListView��
	Handler handler = new Handler(){
		
		private List<HealthyKnowListBean> HKLBeanList = null;
		private List<String> titleList = new ArrayList<String>();
		
		@Override
		public void handleMessage(Message msg) {
			String jsonStr =(String) msg.obj;
			
			JSONObject obj;
			String healthyKnowListStr = null;
			lv = new PullDownListView(context);
			ll.addView(lv);
			
			
			try {
				
				//��JSONObject��ȡָ���ε�JSON����
				obj = new JSONObject(jsonStr);
				healthyKnowListStr = (String) obj.getJSONArray("yi18").toString();
				
				//��GSON�������л���������Ӧ��ʵ����
				HKLBeanList = new Gson().fromJson(healthyKnowListStr,
						new TypeToken<List<HealthyKnowListBean>>() {}.getType());
				
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			for(int i=0;i<HKLBeanList.size();i++){
				titleList.add(HKLBeanList.get(i).getTitle());
			}
			
			
			adapter = new PullDownListViewAdapter(titleList,context );
			lv.setAdapter(adapter);
	
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
		
	};
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//TestEngine.TestGetHealthyKnowList();		
		ll = (LinearLayout) findViewById(R.id.activity_main_ll);
		
		
		//�������磬��ȡ����֪ʶ�����б�
		List<KeyValuesBean> list = new ArrayList<KeyValuesBean>();
		list.add(new KeyValuesBean("page", "20"));
		list.add(new KeyValuesBean("limit", "5"));
		list.add(new KeyValuesBean("type", "id"));
		
		HealthyKnowEngine hke = new HealthyKnowEngine();
		
		hke.GetHealthyKnowList(list,handler);
		
		
//		lv = (PullDownListView) findViewById(R.id.pd_lv);
//		list = new ArrayList<String>();
//		list.add("loonggg");
//		list.add("���Ƕ��ǿ�����");
//		list.add("���Ƕ��ǿ�����");
//		list.add("���Ƕ��ǿ�����");
//		list.add("���Ƕ��ǿ�����");
//		list.add("���Ƕ��ǿ�����");
//		list.add("���Ƕ��ǿ�����");
//		list.add("���Ƕ��ǿ�����");
//		list.add("���Ƕ��ǿ�����");
//		list.add("���Ƕ��ǿ�����");
//		list.add("���Ƕ��ǿ�����");
//		list.add("���Ƕ��ǿ�����");
//		list.add("���Ƕ��ǿ�����");
//		list.add("���Ƕ��ǿ�����");
//		list.add("���Ƕ��ǿ�����");
//		list.add("���Ƕ��ǿ�����");
//		list.add("���Ƕ��ǿ�����");
//		adapter = new PullDownListViewAdapter(list, this);
//		lv.setAdapter(adapter);
//
//		lv.setonRefreshListener(new OnRefreshListener() {
//
//			@Override
//			public void onRefresh() {
//				new AsyncTask<Void, Void, Void>() {
//					protected Void doInBackground(Void... params) {
//						try {
//							Thread.sleep(1000);
//						} catch (Exception e) {
//							e.printStackTrace();
//						}
//						list.add("ˢ�º���ӵ�����");
//						return null;
//					}
//
//					@Override
//					protected void onPostExecute(Void result) {
//						adapter.notifyDataSetChanged();
//						lv.onRefreshComplete();
//					}
//				}.execute(null, null, null);
//			}
//		});
	}

}
