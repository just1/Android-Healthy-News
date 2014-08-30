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
	
	
	//用来获取健康信息列表的数据，并显示到ListView中
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
				
				//用JSONObject获取指定段的JSON内容
				obj = new JSONObject(jsonStr);
				healthyKnowListStr = (String) obj.getJSONArray("yi18").toString();
				
				//用GSON来反序列化，生成相应的实体类
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
							titleList.add("刷新后添加的内容");
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
		
		
		//请求网络，获取健康知识分类列表
		List<KeyValuesBean> list = new ArrayList<KeyValuesBean>();
		list.add(new KeyValuesBean("page", "20"));
		list.add(new KeyValuesBean("limit", "5"));
		list.add(new KeyValuesBean("type", "id"));
		
		HealthyKnowEngine hke = new HealthyKnowEngine();
		
		hke.GetHealthyKnowList(list,handler);
		
		
//		lv = (PullDownListView) findViewById(R.id.pd_lv);
//		list = new ArrayList<String>();
//		list.add("loonggg");
//		list.add("我们都是开发者");
//		list.add("我们都是开发者");
//		list.add("我们都是开发者");
//		list.add("我们都是开发者");
//		list.add("我们都是开发者");
//		list.add("我们都是开发者");
//		list.add("我们都是开发者");
//		list.add("我们都是开发者");
//		list.add("我们都是开发者");
//		list.add("我们都是开发者");
//		list.add("我们都是开发者");
//		list.add("我们都是开发者");
//		list.add("我们都是开发者");
//		list.add("我们都是开发者");
//		list.add("我们都是开发者");
//		list.add("我们都是开发者");
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
//						list.add("刷新后添加的内容");
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
