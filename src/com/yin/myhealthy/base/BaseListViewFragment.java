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

import com.example.myhealthy.R;
import com.yin.myhealthy.adapter.PullDownListViewAdapter;
import com.yin.myhealthy.view.PullDownListView;
import com.yin.myhealthy.view.PullDownListView.OnRefreshListener;


public abstract class BaseListViewFragment extends Fragment{

	public Context context = null;
	private LinearLayout ll;
	private PullDownListView lv;
	private PullDownListViewAdapter adapter;
	protected List<String> titleList = new ArrayList<String>();
	protected List<String> imgList = new ArrayList<String>();
	private String apiUrl = "http://www.yi18.net/";

	
	public BaseListViewFragment() {
		super();
	}
	
	public BaseListViewFragment(Context context) {
		super();
		this.context  = context;
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
							getListViewDate();
//							Looper.prepare();
//							Toast.makeText(
//									getActivity().getApplicationContext(),
//									"������30������", 0).show();
//							Looper.loop();
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

	}

	// �������磬��������
	protected abstract void getListViewDate();
//	{
//		// �������磬��ȡ����֪ʶ�����б�
//		List<KeyValuesBean> list = new ArrayList<KeyValuesBean>();
//
//		// ��Ҫ���ص�ҳ��
//		list.add(new KeyValuesBean("page", String.valueOf(clickCount)));
//
//		list.add(new KeyValuesBean("limit", "30"));
//		list.add(new KeyValuesBean("type", "id"));
//
//		HealthyKnowEngine hke = new HealthyKnowEngine();
//		hke.GetHealthyKnowList(list, handler);
//	}

	
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
	protected Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			String jsonStr = (String) msg.obj;

			AnalyJSONToList(jsonStr);

			// ����ListView����
			adapter.notifyDataSetChanged();
		}
	};

	//����Json���ݱ���б�
	protected abstract void AnalyJSONToList(String jsonStr);
//	{
//		JSONObject obj;
//		String healthyKnowListStr = null;
//	    List<HealthyKnowListBean> HKLBeanList = null;
//		try {
//
//			// ��JSONObject��ȡָ���ε�JSON����
//			obj = new JSONObject(jsonStr);
//			healthyKnowListStr = (String) obj.getJSONArray("yi18")
//					.toString();
//
//			// ��GSON�������л���������Ӧ��ʵ����
//			HKLBeanList = new Gson().fromJson(healthyKnowListStr,
//					new TypeToken<List<HealthyKnowListBean>>() {
//					}.getType());
//
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//
//		for (int i = 0; i < HKLBeanList.size(); i++) {
//			titleList.add(HKLBeanList.get(i).getTitle());
//
//			imgList.add(apiUrl + HKLBeanList.get(i).getImg());
//		}
//	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_healthyknow, container,
				false);

		ll = (LinearLayout) view.findViewById(R.id.ll_frag_healthyknow);

		// ��ʼ��ListView
		initView();
		// �������磬��ȡ����֪ʶ�����б�
		getListViewDate();
		
		return view;
	}
}
