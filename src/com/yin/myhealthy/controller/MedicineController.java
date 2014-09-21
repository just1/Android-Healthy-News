package com.yin.myhealthy.controller;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Message;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yin.myhealthy.GlobalDate;
import com.yin.myhealthy.base.BaseListController;
import com.yin.myhealthy.bean.MedicineContextBean;
import com.yin.myhealthy.bean.MedicineListBean;

//��ʳ��ص��߼���
public class MedicineController extends BaseListController {

	// �Ժ�Ҫ��취���,����DietListBean����Ϊ����
	public void AnalyListJSONToList(String jsonStr) {
		JSONObject obj;
		String someJsonStr = null;
		List<MedicineListBean> listBeanList = null;
		try {

			// ��JSONObject��ȡָ���ε�JSON����
			obj = new JSONObject(jsonStr);
			someJsonStr = (String) obj.getJSONArray("yi18").toString();

			// ��GSON�������л���������Ӧ��ʵ����
			listBeanList = new Gson().fromJson(someJsonStr,
					new TypeToken<List<MedicineListBean>>() {
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
					+ listBeanList.get(i).getImage());
		}
	}

	/*
	 * ���������߼���
	 */
	private void AnalyDataJSONToBean(String jsonStr) {
		JSONObject obj;
		String someJsonStr = null;
		try {
			// ��JSONObject��ȡָ���ε�JSON����
			obj = new JSONObject(jsonStr);
			someJsonStr = (String) obj.get("yi18").toString();

			// ��GSON�������л���������Ӧ��ʵ����
			Gson gson = new Gson();
			MedicineContextBean bean = gson.fromJson(someJsonStr,
					MedicineContextBean.class);

			// ����json�����ݸ���ҳ��
			// reflashViewFromNet(bean);
			returnBeanMsg = new Message();
			returnBeanMsg.obj = bean;
			returnBeanHandler.sendMessage(returnBeanMsg);

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}
