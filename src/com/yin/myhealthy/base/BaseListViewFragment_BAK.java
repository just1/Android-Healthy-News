package com.yin.myhealthy.base;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
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

import com.yin.myhealthy.R;
import com.yin.myhealthy.adapter.PullDownListViewAdapter;
import com.yin.myhealthy.view.PullDownListView;
import com.yin.myhealthy.view.PullDownListView.OnRefreshListener;


public abstract class BaseListViewFragment_BAK extends Fragment{

	public Context context = null;
	private LinearLayout ll;
	protected PullDownListView lv;
	private PullDownListViewAdapter adapter;
	protected List<String> titleList = new ArrayList<String>();
	protected List<String> imgList = new ArrayList<String>();
	protected List<String> idList = new ArrayList<String>();
	
	protected String apiUrl = null;

	
	public BaseListViewFragment_BAK(String url) {
		apiUrl = url;
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

		adapter = new PullDownListViewAdapter(titleList, imgList, context);
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
							titleList.removeAll(null);
							imgList.removeAll(null);
							idList.removeAll(null);
							getListViewDate();
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
		
		//设置listview的item监听
		setLvItemClickListener();
	}

	abstract public void setLvItemClickListener();

	// 访问网络，请求数据
	protected  abstract void getListViewDate();
	
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
	protected Handler listDataHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			String jsonStr = (String) msg.obj;

			//实际发现这里会报空指针异常
			if(jsonStr != null){
				if(!jsonStr.isEmpty()){
					AnalyJSONToList(jsonStr);
				}
			}
			
			// 更新ListView数据
			adapter.notifyDataSetChanged();
		}
	};

	//解析Json数据变成列表
	protected abstract void AnalyJSONToList(String jsonStr);

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_base_lv, container,
				false);

		ll = (LinearLayout) view.findViewById(R.id.ll_frag_healthyknow);

		// 初始化ListView
		initView();
		// 请求网络，获取健康知识分类列表
		getListViewDate();
		
		return view;
	}
}
