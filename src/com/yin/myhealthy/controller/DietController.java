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

//饮食相关的逻辑类
public class DietController {

	/*
	 * 饮食类列表数据逻辑类
	 */
	private List<String> titleList = new ArrayList<String>();
	private List<String> imgList = new ArrayList<String>();
	private List<String> idList = new ArrayList<String>();

	private Handler returnListHandler;

	// 用来获取健康信息列表的数据，并显示到ListView中
	protected Handler listDataHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			String jsonStr = (String) msg.obj;
			// Message returnMsg = new Message();

			// 实际发现这里会报空指针异常
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

			// 更新ListView数据
			// adapter.notifyDataSetChanged();
		}
	};

	public void getDietListData(String apiUrl, int pageNum, String id,
			Handler handler) {
		returnListHandler = handler; // 用于当网络请求好了，回来通知界面

		// 请求网络，获取健康知识分类列表
		List<KeyValuesBean> list = new ArrayList<KeyValuesBean>();

		// 需要返回的页号

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

			// 用JSONObject获取指定段的JSON内容
			obj = new JSONObject(jsonStr);
			someJsonStr = (String) obj.getJSONArray("yi18").toString();

			// 用GSON来反序列化，生成相应的实体类
			dietListBeanList = new Gson().fromJson(someJsonStr,
					new TypeToken<List<DietListBean>>() {
					}.getType());

		} catch (JSONException e) {
			e.printStackTrace();
		}

		// 判断是否全部都加载完毕
		if (dietListBeanList.size() == 0) {
			System.out.println("数据全部加载完");
			// Toast.makeText(context, "数据全部加载完", 0).show();

			return;
		}

		for (int i = 0; i < dietListBeanList.size(); i++) {

			titleList.add(dietListBeanList.get(i).getName());
			idList.add(String.valueOf(dietListBeanList.get(i).getId()));

			/*
			 * 图片地址示例： http://www.yi18.net/img/news/20140905132030_697.jpg
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
	 * 饮食类单个详情逻辑类
	 */
	private Handler returnBeanHandler;
	private Message returnBeanMsg;
	
	
	public void getDietBeanData(String apiUrl,Handler handler) {
		returnBeanHandler = handler;
		
		// 请求网络
		AsyncHttpClientUtil.RequestAPI(apiUrl, null, beanHandler);
	}
	
	Handler beanHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {

			String jsonStr = (String) msg.obj;

			// 实际发现这里会报空指针异常
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
