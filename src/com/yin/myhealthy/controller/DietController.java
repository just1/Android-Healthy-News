package com.yin.myhealthy.controller;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Message;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yin.myhealthy.GlobalDate;
import com.yin.myhealthy.base.BaseListController;
import com.yin.myhealthy.bean.DietContextBean;
import com.yin.myhealthy.bean.DietListBean;

//��ʳ��ص��߼���


public class DietController extends BaseListController{

	/*
	 * ��ʳ���б������߼���
	 */
	
	//�Ժ�Ҫ��취���,����DietListBean����Ϊ����
	public void AnalyListJSONToList(String jsonStr) {
		JSONObject obj;
		String someJsonStr = null;
		List<DietListBean> listBeanList = null;
		try {

			// ��JSONObject��ȡָ���ε�JSON����
			obj = new JSONObject(jsonStr);
			someJsonStr = (String) obj.getJSONArray("yi18").toString();

			// ��GSON�������л���������Ӧ��ʵ����
			listBeanList = new Gson().fromJson(someJsonStr,
					new TypeToken<List<DietListBean>>() {
					}.getType());

		} catch (JSONException e) {
			e.printStackTrace();
		}

		// �ж��Ƿ�ȫ�����������
		if (listBeanList.size() == 0) {
			System.out.println("����ȫ��������");
			// Toast.makeText(context, "����ȫ��������", 0).show();

			return;
		}

		for (int i = 0; i < listBeanList.size(); i++) {

			titleList.add(listBeanList.get(i).getName());
			idList.add(String.valueOf(listBeanList.get(i).getId()));

			/*
			 * ͼƬ��ַʾ���� http://www.yi18.net/img/news/20140905132030_697.jpg
			 */
			imgList.add(GlobalDate.WEB_ADDRESS
					+ listBeanList.get(i).getImg());
		}
	}

	
	/*
	 * ��ʳ�൥�������߼���
	 */
	
	public Object AnalyDataJSONToBean(String jsonStr) {
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

			return bean;

		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
