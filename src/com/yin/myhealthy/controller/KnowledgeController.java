package com.yin.myhealthy.controller;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yin.myhealthy.GlobalDate;
import com.yin.myhealthy.base.BaseListController;
import com.yin.myhealthy.bean.KnowledgeContextBean;
import com.yin.myhealthy.bean.KnowledgeListBean;

//��ʳ��ص��߼���
public class KnowledgeController extends BaseListController {

	// �Ժ�Ҫ��취���,����DietListBean����Ϊ����
	public void AnalyListJSONToList(String jsonStr) {
		JSONObject obj;
		String someJsonStr = null;
		List<KnowledgeListBean> listBeanList = null;
		try {

			// ��JSONObject��ȡָ���ε�JSON����
			obj = new JSONObject(jsonStr);
			someJsonStr = (String) obj.getJSONArray("yi18").toString();

			// ��GSON�������л���������Ӧ��ʵ����
			listBeanList = new Gson().fromJson(someJsonStr,
					new TypeToken<List<KnowledgeListBean>>() {
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

			titleList.add(listBeanList.get(i).getTitle());
			idList.add(String.valueOf(listBeanList.get(i).getId()));

			/*
			 * ͼƬ��ַʾ���� http://www.yi18.net/img/news/20140905132030_697.jpg
			 */
			imgList.add(GlobalDate.WEB_ADDRESS
					+ listBeanList.get(i).getImg());
		}
	}

	/*
	 * ���������߼���
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
			KnowledgeContextBean bean = gson.fromJson(someJsonStr,
					KnowledgeContextBean.class);

			// ����json�����ݸ���ҳ��
			// reflashViewFromNet(bean);
			//returnBeanMsg = new Message();
			//returnBeanMsg.obj = bean;
			//returnBeanHandler.sendMessage(returnBeanMsg);
			return bean;
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
