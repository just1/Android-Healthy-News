package com.yin.myhealthy.utils;

import org.apache.http.Header;

import android.os.Handler;
import android.os.Message;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;

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
}
