package com.yin.myhealthy.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import com.example.myhealthy.R;
import com.loopj.android.image.SmartImageView;
import com.yin.myhealthy.bean.news.NewsBean;

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
		Intent intent = getIntent();

		NewsBean bean = (NewsBean) intent.getSerializableExtra("bean");
		tv_title.setText(bean.getTitle());
		tv_time.setText(bean.getTime());
		tv_tag.setText(bean.getTag());
		
		//img_context.setImageUrl(bean.get);
		
		tv_context.setText(bean.getMessage());
		
	}

}
