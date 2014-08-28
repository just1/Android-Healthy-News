package com.yin.myhealthy;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;

import com.example.myhealthy.R;
import com.yin.myhealthy.bean.NewsCategoryBean;
import com.yin.myhealthy.bean.SendStatusBean;
import com.yin.myhealthy.utils.GsonTools;
import com.yin.myhealthy.utils.HttpClientUtil;
import com.yin.myhealthy.utils.StreamTools;

public class MainActivity extends Activity {

	/**
	 * ·þÎñÆ÷µØÖ·
	 */
	String LOTTERY_URI = "http://api.yi18.net/news/newsclass";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		
		new Thread(){
			public void run() {
				String result = null;
				String xml = "123";
				
				HttpClientUtil util = new HttpClientUtil();
				InputStream is = util.sendXml(LOTTERY_URI, xml);
				try {
					result = StreamTools.readFromStream(is);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				
				
				GsonTools gt = new GsonTools();
				
				
				//List<NewsList> list = gt.changeGsonToList(result,NewsList.class);
				

				SendStatusBean t = gt.changeGsonToBean(result,SendStatusBean.class);
				List<NewsCategoryBean> list = t.getYi18();
				
				
				
				System.out.println(result);
				
				if(list.isEmpty()){
					System.out.println("empty");
				}else{
					System.out.println(list.get(2).getName());
				}
				
				
			};
		}.start();
		
		
		
	}
	
	
	class T{
		public boolean getSuccess() {
			return success;
		}
		public void setSuccess(boolean success) {
			this.success = success;
		}
		public List<NewsList> getYi18() {
			return yi18;
		}
		public void setYi18(List<NewsList> yi18) {
			this.yi18 = yi18;
		}
		private boolean success;
		private List<NewsList> yi18; 
	}

	class NewsList{
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		String name;
		int id;
	}

}
