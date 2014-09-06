package com.yin.myhealthy.view;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.example.myhealthy.R;
import com.google.gson.Gson;
import com.loopj.android.image.SmartImageView;
import com.yin.myhealthy.bean.news.NewsBean;
import com.yin.myhealthy.bean.news.NewsListBean;
import com.yin.myhealthy.utils.AsyncHttpClientUtil;

public class ContextActivity extends Activity {

	TextView tv_title;
	TextView tv_time;
	TextView tv_tag;
	SmartImageView img_context;
	TextView tv_context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// 设置隐藏标题栏,必须在setContentView()前调用
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		setContentView(R.layout.activity_context);

		initView();
		initData();

	}

	private void initView() {
		tv_title = (TextView) findViewById(R.id.tv_context_title);
		tv_time = (TextView) findViewById(R.id.tv_context_time);
		tv_tag = (TextView) findViewById(R.id.tv_context_tag);
		img_context = (SmartImageView) findViewById(R.id.siv_context_img);
		tv_context = (TextView) findViewById(R.id.tv_context_contextmsg);
	}

	private void initData() {
		
//		Intent intent = getIntent();
//		NewsBean bean = (NewsBean) intent.getSerializableExtra("bean");
//		tv_title.setText(bean.getTitle());
//		tv_time.setText(bean.getTime());
//		tv_tag.setText(bean.getTag());
//		
//		//img_context.setImageUrl(bean.get);
//		
//		tv_context.setText(bean.getMessage());
		
		Intent intent = getIntent();
		String news_url =  (String) intent.getSerializableExtra("url");
		
		//请求网络
		AsyncHttpClientUtil.RequestAPI(news_url, null, handler);
		
		//json解析
		
		//界面显示
	}
	
	Handler handler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			
			String jsonStr = (String) msg.obj;

			//实际发现这里会报空指针异常
			if(jsonStr != null){
				if(!jsonStr.isEmpty()){
					
					
					
					AnalyJSONToList(jsonStr);
				}
			}
		}
	};
	
	private void AnalyJSONToList(String jsonStr) {
		JSONObject obj;
		String healthyKnowListStr = null;
		List<NewsListBean> newsListBeanList = null;
		try {

			// 用JSONObject获取指定段的JSON内容
			obj = new JSONObject(jsonStr);
			
			healthyKnowListStr = (String) obj.get("yi18").toString();

			//测试，乱写
			//tv_context.setText(healthyKnowListStr);
			
			
			
			
			// 用GSON来反序列化，生成相应的实体类
			Gson gson = new Gson();
			NewsBean newsBean = gson.fromJson(healthyKnowListStr, NewsBean.class);

			//根据json的数据更新页面
			reflashViewFromNet(newsBean);
			
		} catch (JSONException e) {
			e.printStackTrace();
		}


	}
	
	private void reflashViewFromNet(NewsBean newsbean){
		tv_title.setText(newsbean.getTitle());
		tv_time.setText(newsbean.getTime());
		tv_tag.setText(newsbean.getTag());
		
		//因为有些详细内容是没有图片的
		if(newsbean.getImg() != null){
			img_context.setImageUrl(newsbean.getImg());
		}else{
			img_context.setVisibility(View.GONE);
		}
		
		//s
		tv_context.setText(Html.fromHtml(newsbean.getMessage()));
	}
	

}
