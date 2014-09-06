package com.yin.myhealthy.view;

import java.io.Serializable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.myhealthy.R;
import com.yin.myhealthy.MainActivity;
import com.yin.myhealthy.bean.ContextBean;

public class SplashActivity extends Activity {

	private TextView tv_splash;
	private static final int SPLASH_FINISH = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// 设置隐藏标题栏,必须在setContentView()前调用
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.activity_splash);

		initView();

	}

	private void initView() {
		tv_splash = (TextView) findViewById(R.id.tv_splash_login);
		tv_splash.setText("健康资讯通");

		setAllAnimation();
		finishAnimation();
	}

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			Intent startIntent = new Intent(SplashActivity.this,
					MainActivity.class);
			startActivity(startIntent);
			SplashActivity.this.finish();
			
			//TestStartContextActivity();
		};
	};

	private void finishAnimation() {
		Message msg = new Message();
		msg.what = SPLASH_FINISH;
		handler.sendMessageDelayed(msg, 1400);
	}

	private void setAllAnimation() {
		Animation set = AnimationUtils.loadAnimation(this, R.anim.lauch_set);
		tv_splash.startAnimation(set);
	}

	
	private void TestStartContextActivity(){
		Intent startIntent = new Intent(SplashActivity.this,
				ContextActivity.class);

		
		startActivity(startIntent);
		SplashActivity.this.finish();
		
	}
	
}
