package com.yin.myhealthy.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yin.myhealthy.GlobalDate;
import com.yin.myhealthy.bean.DietContextBean;
import com.yin.myhealthy.bean.DietListBean;
import com.yin.myhealthy.bean.KeyValuesBean;
import com.yin.myhealthy.utils.AsyncHttpClientUtil;

//��ʳ��ص��߼���
public class DietController {

	/*
	 * ��ʳ���б������߼���
	 */
	private List<String> titleList = new ArrayList<String>();
	private List<String> imgList = new ArrayList<String>();
	private List<String> idList = new ArrayList<String>();

	private Handler returnListHandler;

	// ������ȡ������Ϣ�б�����ݣ�����ʾ��ListView��
	protected Handler listDataHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			String jsonStr = (String) msg.obj;
			// Message returnMsg = new Message();

			// ʵ�ʷ�������ᱨ��ָ���쳣
			if (jsonStr != null) {
				if (!jsonStr.isEmpty()) {
					AnalyDierListJSONToList(jsonStr);
					returnListHandler
							.sendEmptyMessage(GlobalDate.GET_DIETLIST_DATA_SUCCESS);
				} else {
					returnListHandler
							.sendEmptyMessage(GlobalDate.GET_DIETLIST_DATA_FAIL);
				}
			} else {
				returnListHandler
						.sendEmptyMessage(GlobalDate.GET_DIETLIST_DATA_FAIL);
			}

			// ����ListView����
			// adapter.notifyDataSetChanged();
		}
	};

	public void getDietListData(String apiUrl, int pageNum, String id,
			Handler handler) {
		returnListHandler = handler; // ���ڵ�����������ˣ�����֪ͨ����

		// �������磬��ȡ����֪ʶ�����б�
		List<KeyValuesBean> list = new ArrayList<KeyValuesBean>();

		// ��Ҫ���ص�ҳ��

		list.add(new KeyValuesBean("page", String.valueOf(pageNum)));

		list.add(new KeyValuesBean("limit", "20"));
		list.add(new KeyValuesBean("type", "id"));
		list.add(new KeyValuesBean("id", id));

		AsyncHttpClientUtil.RequestAPI(apiUrl, list, listDataHandler);

	}

	public void AnalyDierListJSONToList(String jsonStr) {
		JSONObject obj;
		String someJsonStr = null;
		List<DietListBean> dietListBeanList = null;
		try {

			// ��JSONObject��ȡָ���ε�JSON����
			obj = new JSONObject(jsonStr);
			someJsonStr = (String) obj.getJSONArray("yi18").toString();

			// ��GSON�������л���������Ӧ��ʵ����
			dietListBeanList = new Gson().fromJson(someJsonStr,
					new TypeToken<List<DietListBean>>() {
					}.getType());

		} catch (JSONException e) {
			e.printStackTrace();
		}

		// �ж��Ƿ�ȫ�����������
		if (dietListBeanList.size() == 0) {
			System.out.println("����ȫ��������");
			// Toast.makeText(context, "����ȫ��������", 0).show();

			return;
		}

		for (int i = 0; i < dietListBeanList.size(); i++) {

			titleList.add(dietListBeanList.get(i).getName());
			idList.add(String.valueOf(dietListBeanList.get(i).getId()));

			/*
			 * ͼƬ��ַʾ���� http://www.yi18.net/img/news/20140905132030_697.jpg
			 */
			imgList.add(GlobalDate.WEB_ADDRESS
					+ dietListBeanList.get(i).getImg());
		}
	}

	public List<String> getTitleList() {
		return titleList;
	}

	public List<String> getImgList() {
		return imgList;
	}

	public List<String> getIdList() {
		return idList;
	}

	
	
	/*
	 * ��ʳ�൥�������߼���
	 */
	private Handler returnBeanHandler;
	private Message returnBeanMsg;
	
	
	public void getDietBeanData(String apiUrl,Handler handler) {
		returnBeanHandler = handler;
		
		// ��������
		AsyncHttpClientUtil.RequestAPI(apiUrl, null, beanHandler);
	}
	
	Handler beanHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {

			String jsonStr = (String) msg.obj;

			// ʵ�ʷ�������ᱨ��ָ���쳣
			if (jsonStr != null) {
				if (!jsonStr.isEmpty()) {
					AnalyDataJSONToBean(jsonStr);
				}
			}
		}
	};
	
	private void AnalyDataJSONToBean(String jsonStr) {
		JSONObject obj;
		String someJsonStr = null;
		try {

			// ��JSONObject��ȡָ���ε�JSON����
			obj = new JSONObject(jsonStr);
			someJsonStr = (String) obj.get("yi18").toString();

			// ��GSON�������л���������Ӧ��ʵ����
			Gson gson = new Gson();
			DietContextBean bean = gson.fromJson(someJsonStr,
					DietContextBean.class);

			// ����json�����ݸ���ҳ��
			//reflashViewFromNet(bean);
			returnBeanMsg = new Message();
			returnBeanMsg.obj = bean;
			returnBeanHandler.sendMessage(returnBeanMsg);
			
			

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}
