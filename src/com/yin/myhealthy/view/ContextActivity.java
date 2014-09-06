package com.yin.myhealthy.view;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.myhealthy.R;
import com.google.gson.Gson;
import com.loopj.android.image.SmartImageView;
import com.yin.myhealthy.bean.ContextBean;
import com.yin.myhealthy.utils.AsyncHttpClientUtil;

public class ContextActivity extends Activity {

	private ImageButton ib_back;
	private ImageButton ib_more;
	
	private TextView tv_title;
	private TextView tv_time;
	private TextView tv_tag;
	private SmartImageView img_context;
	private TextView tv_context;

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
		ib_back = (ImageButton) findViewById(R.id.ib_context_back);
		ib_more = (ImageButton) findViewById(R.id.ib_context_more);
		
		tv_title = (TextView) findViewById(R.id.tv_context_title);
		tv_time = (TextView) findViewById(R.id.tv_context_time);
		tv_tag = (TextView) findViewById(R.id.tv_context_tag);
		img_context = (SmartImageView) findViewById(R.id.siv_context_img);
		tv_context = (TextView) findViewById(R.id.tv_context_contextmsg);
		
		setListend();
	}
	
	//设置按键监听
	private void setListend(){
		ib_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ContextActivity.this.finish();
			}
		});
		
		ib_more.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	private void initData() {
		
		Intent intent = getIntent();
		String news_url =  (String) intent.getSerializableExtra("url");
		
		//请求网络
		AsyncHttpClientUtil.RequestAPI(news_url, null, handler);
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
		String someJsonStr = null;
		try {

			// 用JSONObject获取指定段的JSON内容
			obj = new JSONObject(jsonStr);
			someJsonStr = (String) obj.get("yi18").toString();

			// 用GSON来反序列化，生成相应的实体类
			Gson gson = new Gson();
			ContextBean bean = gson.fromJson(someJsonStr, ContextBean.class);

			//根据json的数据更新页面
			reflashViewFromNet(bean);
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	//根据json的数据更新页面
	private void reflashViewFromNet(ContextBean bean){
		tv_title.setText(bean.getTitle());
		tv_time.setText(bean.getTime());
		tv_tag.setText(bean.getTag());
		
		//因为有些详细内容是没有图片的
		if(bean.getImg() != null){
			img_context.setImageUrl(bean.getImg());
		}else{
			img_context.setVisibility(View.GONE);
		}
		
		//
		tv_context.setText(Html.fromHtml(bean.getMessage()));
	}
	

}
