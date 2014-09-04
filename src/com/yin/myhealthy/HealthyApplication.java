package com.yin.myhealthy;

import android.app.Application;
import android.content.Context;

public class HealthyApplication extends Application {

	private Context context;
	
	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}

}
