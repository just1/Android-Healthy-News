package com.yin.myhealthy.engine;

import java.util.List;

import android.os.Handler;

import com.yin.myhealthy.GlobalDate;
import com.yin.myhealthy.bean.KeyValuesBean;
import com.yin.myhealthy.utils.AsyncHttpClientUtil;

public class HealthyKnowEngine {
	
	//获取健康知识分类列表
	public void GetHealKnowCategoryList(Handler handler){
		AsyncHttpClientUtil.GetXml(GlobalDate.API_HEAYKNOW_CATE_LIST, null, handler);
		
	}
	
	
	/*
	 * 获取健康信息知识列表
	 * 请求参数（以下都不是必须的）：
	 * page		int		请求页数，默认是1
	 * limit	int		每页返回的条数，默认是20
	 * type		string	排序方式 ：id：最新时间；count：访问最多，默认是id，按最新时间。
	 * id		long	id：这里是指知识分类的ID 默认为 null ，也就是全部。
	 */
	public void GetHealthyKnowList(List<KeyValuesBean> list,Handler handler){
		String urlStr = GlobalDate.API_HEAYKNOW_LIST;
		
		//如果参数列表非null并且非空
		if((list != null) && (!list.isEmpty()) ){
			urlStr += "?";
		
			//遍历链表，获得并设置参数名和参数值
			for(int i=0;i<list.size();i++){
				urlStr += list.get(i).getKey() + "=" + list.get(i).getValues();
				if(i != (list.size()-1)){
					urlStr += "&";
				}
			}
		
		}
		
		System.out.println("urlStr:"+urlStr);
		
		AsyncHttpClientUtil.GetXml(urlStr, null, handler);
	}
	
	
	
	//获取健康知识详情列表
	public void GetHealthyKnowBean(int id,Handler handler){
		
		AsyncHttpClientUtil.GetXml(GlobalDate.API_HEAYKNOW_MORE, null, handler);
	}
	
	
}
	

