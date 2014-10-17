package com.yin.myhealthy.base;

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
import android.widget.TextView;

import com.yin.myhealthy.GlobalDate;
import com.yin.myhealthy.R;
import com.yin.myhealthy.adapter.PullDownListViewAdapter;
import com.yin.myhealthy.view.PullDownListView;
import com.yin.myhealthy.view.PullDownListView.OnRefreshListener;

public abstract class BaseListViewFragment extends Fragment {

	protected Context context = null;
	protected LinearLayout ll;
	protected PullDownListView lv;
	protected PullDownListViewAdapter adapter;
	protected String apiUrl = null;
	protected BaseListController controller;
	protected String id; // 类别的id号

	protected TextView tv_loading;
	protected boolean isHaveData = false; // 是否已经有数据了

	public BaseListViewFragment(String url, String id) {
		this.id = id;
		apiUrl = url;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		context = activity;
	}

	public void setController(BaseListController ctrl) {
		controller = ctrl;
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

	private void updataViewVisible() {
		if (isHaveData) {
			lv.setVisibility(View.VISIBLE);
			tv_loading.setVisibility(View.GONE);
		} else {
			lv.setVisibility(View.GONE); // 设置不显示先，直到有数据再来显示
			tv_loading.setVisibility(View.VISIBLE);
		}

	}

	private void initData() {
		// 请求网络，获取健康知识分类列表
		controller.getListData(apiUrl, clickCount, id, handler);
	}

	// 当数据返回来时回调
	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if (msg.what == GlobalDate.GET_DATA_SUCCESS) {

				isHaveData = true;
				updataViewVisible();

				// 更新ListView数据
				adapter.notifyDataSetChanged();
			}
		}
	};

	abstract public void setLvItemClickListener();

	// "加载更多"的事件处理
	protected int clickCount = 1; // 记录按下的次数
	OnClickListener moreBtnListener = new OnClickListener() {

		@Override
		public void onClick(View v) {

			clickCount++; // 又按下一次
			controller.getListData(apiUrl, clickCount, id, handler);
		}
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_base_lv, container,
				false);

		ll = (LinearLayout) view.findViewById(R.id.ll_frag);
		tv_loading = (TextView) view.findViewById(R.id.tv_loading);

		// 初始化ListView
		initView();
		updataViewVisible();

		// initData();

		return view;
	}

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		// TODO Auto-generated method stub
		if (isVisibleToUser) { // 如果可见

			if (controller.getTitleList().size() > 0) { // 如果已经有数据，则不用访问网络
				isHaveData = true;
				updataViewVisible();
			} else { // 没有数据才访问网络
				initData(); // 加载数据
			}

		} else { // 如果不可见，不执行操作

		}

		super.setUserVisibleHint(isVisibleToUser);
	}
}
