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
import com.yin.myhealthy.bean.news.NewsListBean;
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
					
					//��JSONObject��ȡָ���ε�JSON����
					obj = new JSONObject(jsonStr);
					healthyKnowListStr = (String) obj.getJSONArray("yi18").toString();
					
					//��GSON�������л���������Ӧ��ʵ����
					list = new Gson().fromJson(healthyKnowListStr,
							new TypeToken<List<NewsListBean>>() {}.getType());
					
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				//���������List
				for(int i=0;i<list.size();i++){
					String str = null;
					str = "title"+list.get(i).getTitle();
					str += "author" + list.get(i).getAuthor();
					System.out.println(str);
				}
				
				//ƽʱ�ø�List������UI����
			}
			
		};
		
		
		/*
		 * ��ȡ������Ϣ֪ʶ�б�
		 * ������������¶����Ǳ���ģ���
		 * page		int		����ҳ����Ĭ����1
		 * limit	int		ÿҳ���ص�������Ĭ����20
		 * type		string	����ʽ ��id������ʱ�䣻count��������࣬Ĭ����id��������ʱ�䡣
		 * id		long	id��������ָ֪ʶ�����ID Ĭ��Ϊ null ��Ҳ����ȫ����
		 */
		//�����������
		List<KeyValuesBean> list = new ArrayList<KeyValuesBean>();
		list.add(new KeyValuesBean("page", "20"));
		list.add(new KeyValuesBean("limit", "5"));
		list.add(new KeyValuesBean("type", "id"));
		
		AsyncHttpClientUtil.RequestAPI(GlobalDate.API_NEWS_LIST, list, handler);	
		
	}
	
}
