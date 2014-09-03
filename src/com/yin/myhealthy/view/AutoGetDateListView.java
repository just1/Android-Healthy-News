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

//自动从网上拉数据的ListView
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

		// 初始化ListView
		initView();
		// 请求网络，获取健康知识分类列表
		getListViewDate();
	}

	public AutoGetDateListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;

		// 初始化ListView
		initView();
		// 请求网络，获取健康知识分类列表
		getListViewDate();
	}

	// 初始化ListView
	private void initView() {
		//lv = new PullDownListView(context);
		//ll.addView(lv);

		adapter = new PullDownListViewAdapter(titleList, imgList, context);
		this.setAdapter(adapter);
		this.setMoreViewBtnListener(moreBtnListener);

		// 下拉刷新
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
							// "更新了30条数据", 0).show();
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

	// 访问网络，请求数据
	protected void getListViewDate() {
		// 请求网络，获取健康知识分类列表
		List<KeyValuesBean> list = new ArrayList<KeyValuesBean>();

		// 需要返回的页号
		list.add(new KeyValuesBean("page", String.valueOf(clickCount)));

		list.add(new KeyValuesBean("limit", "30"));
		list.add(new KeyValuesBean("type", "id"));

		HealthyKnowEngine hke = new HealthyKnowEngine();
		hke.GetHealthyKnowList(list, handler);
	}

	// "加载更多"的事件处理
	protected int clickCount = 1; // 记录按下的次数
	OnClickListener moreBtnListener = new OnClickListener() {

		@Override
		public void onClick(View v) {

			clickCount++; // 又按下一次

			getListViewDate();
		}
	};

	// 用来获取健康信息列表的数据，并显示到ListView中
	protected Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			String jsonStr = (String) msg.obj;

			AnalyJSONToList(jsonStr);

			// 更新ListView数据
			adapter.notifyDataSetChanged();
		}
	};

	// 解析Json数据变成列表
	protected void AnalyJSONToList(String jsonStr) {
		JSONObject obj;
		String healthyKnowListStr = null;
		List<HealthyKnowListBean> HKLBeanList = null;
		try {

			// 用JSONObject获取指定段的JSON内容
			obj = new JSONObject(jsonStr);
			healthyKnowListStr = (String) obj.getJSONArray("yi18").toString();

			// 用GSON来反序列化，生成相应的实体类
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
