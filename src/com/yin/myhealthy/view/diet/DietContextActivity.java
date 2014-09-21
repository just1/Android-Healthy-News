package com.yin.myhealthy.view.diet;

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
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

import com.google.gson.Gson;
import com.loopj.android.image.SmartImageView;
import com.yin.myhealthy.R;
import com.yin.myhealthy.bean.DietContextBean;
import com.yin.myhealthy.controller.DietController;
import com.yin.myhealthy.utils.AsyncHttpClientUtil;
import com.yin.myhealthy.utils.StringUtil;

public class DietContextActivity extends Activity {

	private ImageButton ib_back;
	private ImageButton ib_more;

	private TextView tv_name;
	private TextView tv_menu;
	private TextView tv_bar;
	private SmartImageView img_context;
	private TextView tv_context;
	private DietContextBean bean;
	
	private DietController controller;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// 设置隐藏标题栏,必须在setContentView()前调用
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_context_diet);
		
		controller = new DietController();
		
		initView();
		initData();

	}

	private void initView() {
		ib_back = (ImageButton) findViewById(R.id.ib_context_back);
		ib_more = (ImageButton) findViewById(R.id.ib_context_more);

		tv_name = (TextView) findViewById(R.id.tv_context_name);
		tv_menu = (TextView) findViewById(R.id.tv_context_menu);
		tv_bar = (TextView) findViewById(R.id.tv_context_bar);
		img_context = (SmartImageView) findViewById(R.id.siv_context_img);
		tv_context = (TextView) findViewById(R.id.tv_context_contextmsg);

		setListend();
	}

	// 设置按键监听
	private void setListend() {
		ib_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				DietContextActivity.this.finish();
			}
		});

		ib_more.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showShare();
			}
		});
	}

	private void initData() {

		Intent intent = getIntent();
		String news_url = (String) intent.getSerializableExtra("url");

		// 请求网络
		//AsyncHttpClientUtil.RequestAPI(news_url, null, handler);
		controller.getBeanData(news_url,handler);
	}

	Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			DietContextBean bean = (DietContextBean) msg.obj;
			reflashViewFromNet(bean);
		}
		
	};

	// 根据json的数据更新页面
	private void reflashViewFromNet(DietContextBean bean) {
		this.bean = bean;

		tv_name.setText(bean.getName());

		// 暂不显示疗效
		// tv_menu.setText(tv_menu.getText() + bean.getMenu());
		tv_bar.setText(tv_bar.getText() + bean.getBar());

		// 因为有些详细内容是没有图片的
		if (bean.getImg() != null) {
			// 暂不显示图片
			// img_context.setImageUrl(GlobalDate.WEB_ADDRESS + bean.getImg());
		} else {
			img_context.setVisibility(View.GONE);
		}

		tv_context.setText(StringUtil.DelEmptyLine(Html
				.fromHtml(bean.getDetailText()).toString().trim()));
	}

	private void showShare() {
		if (bean == null) {
			return;
		}

		ShareSDK.initSDK(this);
		OnekeyShare oks = new OnekeyShare();
		// 关闭sso授权
		oks.disableSSOWhenAuthorize();

		// 分享时Notification的图标和文字
		oks.setNotification(R.drawable.icon_1, getString(R.string.app_name));
		// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
		oks.setTitle(bean.getName());

		// 换行并且去空格
		oks.setText(bean.getName()
				+ '\n'
				+ StringUtil.DelEmptyLine(Html.fromHtml(bean.getDetailText())
						.toString().trim()));

		// 启动分享GUI
		oks.show(this);
	}

}
