package com.yin.myhealthy;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;

import com.example.myhealthy.R;
import com.yin.myhealthy.bean.healthyknow.HealKnowCategoryBean;
import com.yin.myhealthy.utils.GsonTools;
import com.yin.myhealthy.utils.HttpClientUtil;
import com.yin.myhealthy.utils.StreamTools;

public class MainActivity extends Activity {

	/**
	 * 服务器地址
	 */
	String LOTTERY_URI = "http://api.yi18.net/news/newsclass";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		
		new Thread(){
			public void run() {
				String jsonString = null;
				String xml = "123";
				
				HttpClientUtil util = new HttpClientUtil();
				InputStream is = util.sendXml(LOTTERY_URI, xml);
				try {
					jsonString = StreamTools.readFromStream(is);
				} catch (IOException e) {
					e.printStackTrace();
				}				
				
				//从assers文件夹里面获取json字符串用于测试
//				InputStream assetIs;
//				String testJsonStr = null;
//				try {
//					assetIs = getAssets().open("test.xml");
//					testJsonStr = StreamTools.readFromStream(assetIs);
//					System.out.println(testJsonStr);
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}  
	            
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
					System.out.println(((HealKnowCategoryBean)list.get(2)).getName());
				}
						
			};
		}.start();
		
	}

}
