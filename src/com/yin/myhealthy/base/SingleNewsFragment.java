package com.yin.myhealthy.base;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yin.myhealthy.GlobalDate;
import com.yin.myhealthy.bean.KeyValuesBean;
import com.yin.myhealthy.bean.news.NewsListBean;
import com.yin.myhealthy.utils.AsyncHttpClientUtil;

public class SingleNewsFragment extends BaseListViewFragment{

	//��������id��
	private String id;
	
	public SingleNewsFragment(String url,String id) {
		super(url);
		this.id = id;
	}

	@Override
	protected void AnalyJSONToList(String jsonStr) {
		JSONObject obj;
		String healthyKnowListStr = null;
	    List<NewsListBean> newsListBeanList = null;
		try {

			// ��JSONObject��ȡָ���ε�JSON����
			obj = new JSONObject(jsonStr);
			healthyKnowListStr = (String) obj.getJSONArray("yi18")
					.toString();

			// ��GSON�������л���������Ӧ��ʵ����
			newsListBeanList = new Gson().fromJson(healthyKnowListStr,
					new TypeToken<List<NewsListBean>>() {
					}.getType());

		} catch (JSONException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < newsListBeanList.size(); i++) {
			titleList.add(newsListBeanList.get(i).getTitle());
			
			/*
			 * ͼƬ��ַʾ����
			 * http://www.yi18.net/img/news/20140905132030_697.jpg
			 * 
			 * */
			imgList.add(GlobalDate.WEB_ADDRESS + newsListBeanList.get(i).getImg());
		}			
	}

	@Override
	protected void getListViewDate() {
		// �������磬��ȡ����֪ʶ�����б�
		List<KeyValuesBean> list = new ArrayList<KeyValuesBean>();

		// ��Ҫ���ص�ҳ��
		
		list.add(new KeyValuesBean("page", String.valueOf(clickCount)));

		list.add(new KeyValuesBean("limit", "10"));
		list.add(new KeyValuesBean("type", "id"));
		list.add(new KeyValuesBean("id", id));

		AsyncHttpClientUtil.RequestAPI(apiUrl, list, handler);		
	}

}
