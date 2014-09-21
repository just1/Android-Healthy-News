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

import com.yin.myhealthy.GlobalDate;
import com.yin.myhealthy.R;
import com.yin.myhealthy.adapter.PullDownListViewAdapter;
import com.yin.myhealthy.view.PullDownListView;
import com.yin.myhealthy.view.PullDownListView.OnRefreshListener;

public abstract class BaseListViewFragment extends Fragment{

	protected Context context = null;
	protected LinearLayout ll;
	protected PullDownListView lv;
	protected PullDownListViewAdapter adapter;
	protected String apiUrl = null;
	protected BaseListController controller;
	protected String id;	// ����id��

	
	public BaseListViewFragment(String url, String id) {
		this.id = id;
		apiUrl = url;
	}
	
	
	@Override
	public void onAttach(Activity activity) {
	    super.onAttach(activity);  
	    context = activity;  
	}
	
	public void setController(BaseListController ctrl){
		controller = ctrl;
	}
	
	// ��ʼ��ListView
	private void initView() {
		lv = new PullDownListView(context);
		ll.addView(lv);

		adapter = new PullDownListViewAdapter(controller.getTitleList(),
				controller.getImgList(), context);
		lv.setAdapter(adapter);
		lv.setMoreViewBtnListener(moreBtnListener);

		// ����ˢ��
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

		// ����listview��item����
		setLvItemClickListener();
	}
	
	
	// �����ݷ�����ʱ�ص�
	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if (msg.what == GlobalDate.GET_DATA_SUCCESS) {

				// ����ListView����
				adapter.notifyDataSetChanged();
			}
		}
	};
	

	abstract public void setLvItemClickListener();
	
	// "���ظ���"���¼�����
	protected int clickCount = 1; // ��¼���µĴ���
	OnClickListener moreBtnListener = new OnClickListener() {

		@Override
		public void onClick(View v) {

			clickCount++; // �ְ���һ��
			controller.getListData(apiUrl, clickCount, id, handler);
		}
	};
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_base_lv, container,
				false);

		ll = (LinearLayout) view.findViewById(R.id.ll_frag_healthyknow);

		// ��ʼ��ListView
		initView();
		// �������磬��ȡ����֪ʶ�����б�
		controller.getListData(apiUrl, clickCount, id, handler);
		
		return view;
	}
}
