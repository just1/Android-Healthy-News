package com.yin.myhealthy.view;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yin.myhealthy.base.BaseListViewFragment;
import com.yin.myhealthy.bean.KeyValuesBean;
import com.yin.myhealthy.bean.healthyknow.HealthyKnowListBean;
import com.yin.myhealthy.engine.HealthyKnowEngine;

import android.content.Context;
import android.support.v4.app.Fragment;

//������Ѷҳ��
public class TestFragment2 extends BaseListViewFragment {

	public TestFragment2(Context context) {
		super(context);
	}

	@Override
	protected void getListViewDate() {
		// �������磬��ȡ����֪ʶ�����б�
		List<KeyValuesBean> list = new ArrayList<KeyValuesBean>();

		// ��Ҫ���ص�ҳ��
		list.add(new KeyValuesBean("page", String.valueOf(clickCount)));

		list.add(new KeyValuesBean("limit", "30"));
		list.add(new KeyValuesBean("type", "id"));

		HealthyKnowEngine hke = new HealthyKnowEngine();
		hke.GetHealthyKnowList(list, handler);
	}

	@Override
	protected void AnalyJSONToList(String jsonStr) {
		JSONObject obj;
		String healthyKnowListStr = null;
		List<HealthyKnowListBean> HKLBeanList = null;
		try {

			// ��JSONObject��ȡָ���ε�JSON����
			obj = new JSONObject(jsonStr);
			healthyKnowListStr = (String) obj.getJSONArray("yi18").toString();

			// ��GSON�������л���������Ӧ��ʵ����
			HKLBeanList = new Gson().fromJson(healthyKnowListStr,
					new TypeToken<List<HealthyKnowListBean>>() {
					}.getType());

		} catch (JSONException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < HKLBeanList.size(); i++) {
			titleList.add(HKLBeanList.get(i).getTitle());

			imgList.add("http://www.yi18.net/" + HKLBeanList.get(i).getImg());
		}
	}
}