package com.yin.myhealthy.test;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yin.myhealthy.GlobalDate;
import com.yin.myhealthy.bean.KeyValuesBean;
import com.yin.myhealthy.bean.NewsListBean;
import com.yin.myhealthy.utils.AsyncHttpClientUtil;

public class TestEngine {

	
	public static void TestGetHealthyKnowList(){
		
		Handler handler = new Handler(){

			@Override
			public void handleMessage(Message msg) {
				String jsonStr =(String) msg.obj;
				
				JSONObject obj;
				String healthyKnowListStr = null;
				List<NewsListBean> list = null;
				
				try {
					
					//用JSONObject获取指定段的JSON内容
					obj = new JSONObject(jsonStr);
					healthyKnowListStr = (String) obj.getJSONArray("yi18").toString();
					
					//用GSON来反序列化，生成相应的实体类
					list = new Gson().fromJson(healthyKnowListStr,
							new TypeToken<List<NewsListBean>>() {}.getType());
					
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				//遍历输出该List
				for(int i=0;i<list.size();i++){
					String str = null;
					str = "title"+list.get(i).getTitle();
					str += "author" + list.get(i).getAuthor();
					System.out.println(str);
				}
				
				//平时用该List来更新UI界面
			}
			
		};
		
		
		/*
		 * 获取健康信息知识列表
		 * 请求参数（以下都不是必须的）：
		 * page		int		请求页数，默认是1
		 * limit	int		每页返回的条数，默认是20
		 * type		string	排序方式 ：id：最新时间；count：访问最多，默认是id，按最新时间。
		 * id		long	id：这里是指知识分类的ID 默认为 null ，也就是全部。
		 */
		//设置请求参数
		List<KeyValuesBean> list = new ArrayList<KeyValuesBean>();
		list.add(new KeyValuesBean("page", "20"));
		list.add(new KeyValuesBean("limit", "5"));
		list.add(new KeyValuesBean("type", "id"));
		
		AsyncHttpClientUtil.RequestAPI(GlobalDate.API_NEWS_LIST, list, handler);	
		
	}
	
}
