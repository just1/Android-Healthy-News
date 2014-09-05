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

	//新闻类别的id号
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

			// 用JSONObject获取指定段的JSON内容
			obj = new JSONObject(jsonStr);
			healthyKnowListStr = (String) obj.getJSONArray("yi18")
					.toString();

			// 用GSON来反序列化，生成相应的实体类
			newsListBeanList = new Gson().fromJson(healthyKnowListStr,
					new TypeToken<List<NewsListBean>>() {
					}.getType());

		} catch (JSONException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < newsListBeanList.size(); i++) {
			titleList.add(newsListBeanList.get(i).getTitle());
			
			/*
			 * 图片地址示例：
			 * http://www.yi18.net/img/news/20140905132030_697.jpg
			 * 
			 * */
			imgList.add(GlobalDate.WEB_ADDRESS + newsListBeanList.get(i).getImg());
		}			
	}

	@Override
	protected void getListViewDate() {
		// 请求网络，获取健康知识分类列表
		List<KeyValuesBean> list = new ArrayList<KeyValuesBean>();

		// 需要返回的页号
		
		list.add(new KeyValuesBean("page", String.valueOf(clickCount)));

		list.add(new KeyValuesBean("limit", "10"));
		list.add(new KeyValuesBean("type", "id"));
		list.add(new KeyValuesBean("id", id));

		AsyncHttpClientUtil.RequestAPI(apiUrl, list, handler);		
	}

}
