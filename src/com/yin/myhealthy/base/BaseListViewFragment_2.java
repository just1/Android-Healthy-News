package com.yin.myhealthy.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;

import com.yin.myhealthy.GlobalDate;
import com.yin.myhealthy.R;
import com.yin.myhealthy.adapter.PullDownListViewAdapter;
import com.yin.myhealthy.controller.DietController;
import com.yin.myhealthy.view.PullDownListView;
import com.yin.myhealthy.view.PullDownListView.OnRefreshListener;
import com.yin.myhealthy.view.diet.DietContextActivity;

public abstract class BaseListViewFragment_2 extends Fragment {

	public Context context = null;
	private LinearLayout ll;
	protected PullDownListView lv;
	private PullDownListViewAdapter adapter;

	protected String apiUrl = null;
	protected String apiMoreUrl = null;
	
	private BaseListController controller;
	// 新闻类别的id号
	private String id;

	public BaseListViewFragment_2(String url, String moreUrl,String id,BaseListController controller) {
		this.apiUrl = url;
		this.apiMoreUrl = moreUrl;
		
		this.id = id;
		
		this.controller = controller;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		context = activity;
	}

	// 初始化ListView
	private void initView() {
		lv = new PullDownListView(context);
		ll.addView(lv);

		adapter = new PullDownListViewAdapter(controller.getTitleList(),
				controller.getImgList(), context);
		lv.setAdapter(adapter);
		lv.setMoreViewBtnListener(moreBtnListener);

		// 下拉刷新
		lv.setonRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh() {
				new AsyncTask<Void, Void, Void>() {
					protected Void doInBackground(Void... params) {
						try {
							Thread.sleep(500);
							controller.getTitleList().removeAll(null);
							controller.getImgList().removeAll(null);
							controller.getIdList().removeAll(null);
							// getListViewDate();
							controller.getListData(apiUrl, clickCount, id,
									handler);
						} catch (Exception e) {
							e.printStackTrace();
						}
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

		// 设置listview的item监听
		setLvItemClickListener();
	}

	// "加载更多"的事件处理
	protected int clickCount = 1; // 记录按下的次数
	OnClickListener moreBtnListener = new OnClickListener() {

		@Override
		public void onClick(View v) {

			clickCount++; // 又按下一次

			// getListViewDate();
			controller.getListData(apiUrl, clickCount, id, handler);
		}
	};

	// 设置listView的item点击处理
	public void setLvItemClickListener() {

		// listview设置点击处理事件
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				String diet_id = controller.getIdList().get(position - 1);
				String url = apiMoreUrl + diet_id;

				Intent startIntent = new Intent(getActivity(),
						DietContextActivity.class);
				startIntent.putExtra("url", url);
				startActivity(startIntent);
			}
		});
	}

	public void initData() {
		controller.getListData(apiUrl, clickCount, id, handler);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_base_lv, container,
				false);

		ll = (LinearLayout) view.findViewById(R.id.ll_frag_healthyknow);
		

		// 初始化ListView
		initView();
		initData();

		return view;
	}

	// 当数据返回来时回调
	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if (msg.what == GlobalDate.GET_DATA_SUCCESS) {

				// 更新ListView数据
				adapter.notifyDataSetChanged();
			}
		}
	};
}
