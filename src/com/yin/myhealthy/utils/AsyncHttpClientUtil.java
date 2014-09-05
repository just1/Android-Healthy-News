package com.yin.myhealthy.utils;

import java.util.List;

import org.apache.http.Header;

import android.os.Handler;
import android.os.Message;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;
import com.yin.myhealthy.GlobalDate;
import com.yin.myhealthy.bean.KeyValuesBean;

public class AsyncHttpClientUtil {

	//自己写一个Handler来接收数据即可，不用说开一个线程
	public static void GetXml(String url,  RequestParams params,final Handler handler){
		 AsyncHttpClient client = new AsyncHttpClient();  
         RequestHandle handle = client.get(url, params, new AsyncHttpResponseHandler(){

        	 
        	@Override
 			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
        		
        		if(handler != null){
        			Message msg = new Message();
            		msg.obj = new String(responseBody);
            		handler.sendMessage(msg);
        		}
        		
        		//System.out.println(new String(responseBody));
 			}
        	 
			@Override
			public void onFailure(int arg0, Header[] arg1, byte[] arg2,
					Throwable arg3) {

				if(handler != null){
        			Message msg = new Message();
            		msg.obj = null;
            		handler.sendMessage(msg);
        		}
			}
         });
	}
	
	
	
	//直接用来访问API
	public static void RequestAPI(String apiUrl,List<KeyValuesBean> list,Handler handler){
		String urlStr = apiUrl;
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
}
