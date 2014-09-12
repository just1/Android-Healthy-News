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


public abstract class BaseListViewFragment extends Fragment{

	public Context context = null;
	private LinearLayout ll;
	protected PullDownListView lv;
	private PullDownListViewAdapter adapter;
	protected List<String> titleList = new ArrayList<String>();
	protected List<String> imgList = new ArrayList<String>();
	protected List<String> idList = new ArrayList<String>();
	
	protected String apiUrl = null;

	
	public BaseListViewFragment(String url) {
		apiUrl = url;
	}
	
	
	@Override
	public void onAttach(Activity activity) {
	    super.onAttach(activity);  
	    context = activity;  
	}
	
	// ��ʼ��ListView
	private void initView() {
		lv = new PullDownListView(context);
		ll.addView(lv);

		adapter = new PullDownListViewAdapter(titleList, imgList, context);
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
		
		//����listview��item����
		setLvItemClickListener();
	}

	abstract public void setLvItemClickListener();

	// �������磬��������
	protected  abstract void getListViewDate();
	
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
	protected Handler listDataHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			String jsonStr = (String) msg.obj;

			//ʵ�ʷ�������ᱨ��ָ���쳣
			if(jsonStr != null){
				if(!jsonStr.isEmpty()){
					AnalyJSONToList(jsonStr);
				}
			}
			
			// ����ListView����
			adapter.notifyDataSetChanged();
		}
	};

	//����Json���ݱ���б�
	protected abstract void AnalyJSONToList(String jsonStr);

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_base_lv, container,
				false);

		ll = (LinearLayout) view.findViewById(R.id.ll_frag_healthyknow);

		// ��ʼ��ListView
		initView();
		// �������磬��ȡ����֪ʶ�����б�
		getListViewDate();
		
		return view;
	}
}
