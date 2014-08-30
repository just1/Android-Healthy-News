package com.yin.myhealthy.engine;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Handler;
import android.os.Message;
import android.provider.Settings.Global;

import com.yin.myhealthy.GlobalDate;
import com.yin.myhealthy.bean.healthyknow.HealKnowCategoryBean;
import com.yin.myhealthy.bean.healthyknow.HealthyKnowBean;
import com.yin.myhealthy.bean.healthyknow.HealthyKnowListBean;
import com.yin.myhealthy.utils.GsonTools;
import com.yin.myhealthy.utils.HttpClientUtil;
import com.yin.myhealthy.utils.StreamTools;

public class HealthyKnowEngine {
	
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			
			
			}
		}
	
	};
	
	//获取健康知识分类列表
	public List<HealKnowCategoryBean> GetHealKnowCategoryList(){
		
		new Thread(){
			public void run() {
				String jsonString = null;
				String xml = "";
				
				HttpClientUtil util = new HttpClientUtil();
				InputStream is = util.sendXml(GlobalDate.API_HEAYKNOW_CATE_LIST, xml);
				try {
					jsonString = StreamTools.readFromStream(is);
				} catch (IOException e) {
					e.printStackTrace();
				}				
				
				JSONObject obj;
				String healthyKnowStr = null;
				
				List<HealKnowCategoryBean> list = null;
				try {
					obj = new JSONObject(jsonString);
					// 得到服务器的版本信息
					healthyKnowStr = (String) obj.getJSONArray("yi18").toString();
					list = GsonTools.changeGsonToHKCList(healthyKnowStr,HealKnowCategoryBean.class);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				if(list.isEmpty()){
					System.out.println("empty");
				}else{
					System.out.println(list.get(2).getName());
				}
						
			};
		}.start();
		
		
		return null;
	}
	
	
	//获取健康信息知识列表
	public List<HealthyKnowListBean> GetHealthyKnowList(){
		return null;
	}
	
	
	
	//获取健康知识详情列表
	public HealthyKnowBean GetHealthyKnowBean(int id){
		return null;
	}
	
	
}
	

