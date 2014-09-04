package com.yin.myhealthy.view;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yin.myhealthy.base.BaseListViewFragment;
import com.yin.myhealthy.bean.KeyValuesBean;
import com.yin.myhealthy.bean.healthyknow.HealthyKnowListBean;
import com.yin.myhealthy.engine.HealthyKnowEngine;

//健康资讯页面
public class HealthyKnowFragment extends BaseListViewFragment {

	public HealthyKnowFragment(Context context) {
		super(context);
	}
	
	public HealthyKnowFragment() {
		super();
	}

	@Override
	protected void getListViewDate() {
		// 请求网络，获取健康知识分类列表
		List<KeyValuesBean> list = new ArrayList<KeyValuesBean>();

		// 需要返回的页号
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

			// 用JSONObject获取指定段的JSON内容
			obj = new JSONObject(jsonStr);
			healthyKnowListStr = (String) obj.getJSONArray("yi18").toString();

			// 用GSON来反序列化，生成相应的实体类
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
