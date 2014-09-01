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

//健康资讯页面
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
	
	//初始化ListView
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

	// 记录按下的次数
	private int clickCount = 1;
	OnClickListener moreBtnListener = new OnClickListener() {

		@Override
		public void onClick(View v) {

			clickCount++; // 又按下一次

			// 请求网络，获取健康知识分类列表
			List<KeyValuesBean> list = new ArrayList<KeyValuesBean>();

			// 需要返回的页号
			list.add(new KeyValuesBean("page",String.valueOf(clickCount)));

			list.add(new KeyValuesBean("limit", "30"));
			list.add(new KeyValuesBean("type", "id"));

			HealthyKnowEngine hke = new HealthyKnowEngine();
			hke.GetHealthyKnowList(list, handler);
		}
	};

	// 用来获取健康信息列表的数据，并显示到ListView中
	Handler handler = new Handler() {

		private List<HealthyKnowListBean> HKLBeanList = null;

		@Override
		public void handleMessage(Message msg) {
			String jsonStr = (String) msg.obj;

			JSONObject obj;
			String healthyKnowListStr = null;

			try {

				// 用JSONObject获取指定段的JSON内容
				obj = new JSONObject(jsonStr);
				healthyKnowListStr = (String) obj.getJSONArray("yi18")
						.toString();

				// 用GSON来反序列化，生成相应的实体类
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

			// 更新ListView数据
			adapter.notifyDataSetChanged();
		}
	};

	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_healthyknow, container, false);
		
		ll = (LinearLayout) view.findViewById(R.id.ll_frag_healthyknow);

		// 初始化ListView
		initListView();

		// 请求网络，获取健康知识分类列表
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
