package com.yin.myhealthy.view;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yin.myhealthy.GlobalDate;
import com.yin.myhealthy.base.BaseListViewFragment;
import com.yin.myhealthy.bean.KeyValuesBean;
import com.yin.myhealthy.bean.NewsListBean;
import com.yin.myhealthy.utils.AsyncHttpClientUtil;

//������Ѷҳ��
public class TestFragment1 extends BaseListViewFragment {

	public TestFragment1() {
		super(GlobalDate.API_HEAYKNOW_LIST);
	}

	@Override
	protected void getListViewDate() {
		// �������磬��ȡ����֪ʶ�����б�
		List<KeyValuesBean> list = new ArrayList<KeyValuesBean>();

		// ��Ҫ���ص�ҳ��
		list.add(new KeyValuesBean("page", "1"));

		list.add(new KeyValuesBean("limit", "10"));
		list.add(new KeyValuesBean("type", "id"));

		AsyncHttpClientUtil.RequestAPI(apiUrl, list, listDataHandler);
	}

	@Override
	protected void AnalyJSONToList(String jsonStr) {
		JSONObject obj;
		String healthyKnowListStr = null;
		List<NewsListBean> HKLBeanList = null;
		try {

			// ��JSONObject��ȡָ���ε�JSON����
			obj = new JSONObject(jsonStr);
			healthyKnowListStr = (String) obj.getJSONArray("yi18").toString();

			// ��GSON�������л���������Ӧ��ʵ����
			HKLBeanList = new Gson().fromJson(healthyKnowListStr,
					new TypeToken<List<NewsListBean>>() {
					}.getType());

		} catch (JSONException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < HKLBeanList.size(); i++) {
			titleList.add(HKLBeanList.get(i).getTitle());

			imgList.add("http://www.yi18.net/" + HKLBeanList.get(i).getImg());
		}
	}

	@Override
	public void setLvItemClickListener() {
		// TODO Auto-generated method stub
		
	}

}
