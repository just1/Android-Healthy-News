package com.yin.myhealthy.controller;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yin.myhealthy.GlobalDate;
import com.yin.myhealthy.base.BaseListController;
import com.yin.myhealthy.bean.DietContextBean;
import com.yin.myhealthy.bean.DietListBean;
import com.yin.myhealthy.utils.AsyncHttpClientUtil;

//饮食相关的逻辑类


public class DietController extends BaseListController{

	/*
	 * 饮食类列表数据逻辑类
	 */
	
	//以后要想办法解决,传递DietListBean类作为参数
	public void AnalyListJSONToList(String jsonStr) {
		JSONObject obj;
		String someJsonStr = null;
		List<DietListBean> listBeanList = null;
		try {

			// 用JSONObject获取指定段的JSON内容
			obj = new JSONObject(jsonStr);
			someJsonStr = (String) obj.getJSONArray("yi18").toString();

			// 用GSON来反序列化，生成相应的实体类
			listBeanList = new Gson().fromJson(someJsonStr,
					new TypeToken<List<DietListBean>>() {
					}.getType());

		} catch (JSONException e) {
			e.printStackTrace();
		}

		// 判断是否全部都加载完毕
		if (listBeanList.size() == 0) {
			System.out.println("数据全部加载完");
			// Toast.makeText(context, "数据全部加载完", 0).show();

			return;
		}

		for (int i = 0; i < listBeanList.size(); i++) {

			titleList.add(listBeanList.get(i).getName());
			idList.add(String.valueOf(listBeanList.get(i).getId()));

			/*
			 * 图片地址示例： http://www.yi18.net/img/news/20140905132030_697.jpg
			 */
			imgList.add(GlobalDate.WEB_ADDRESS
					+ listBeanList.get(i).getImg());
		}
	}

	
	/*
	 * 饮食类单个详情逻辑类
	 */
	
	private void AnalyDataJSONToBean(String jsonStr) {
		JSONObject obj;
		String someJsonStr = null;
		try {
			// 用JSONObject获取指定段的JSON内容
			obj = new JSONObject(jsonStr);
			someJsonStr = (String) obj.get("yi18").toString();

			// 用GSON来反序列化，生成相应的实体类
			Gson gson = new Gson();
			DietContextBean bean = gson.fromJson(someJsonStr,
					DietContextBean.class);

			// 根据json的数据更新页面
			//reflashViewFromNet(bean);
			returnBeanMsg = new Message();
			returnBeanMsg.obj = bean;
			returnBeanHandler.sendMessage(returnBeanMsg);

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}
