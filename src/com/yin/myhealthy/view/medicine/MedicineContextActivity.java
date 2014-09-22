package com.yin.myhealthy.view.medicine;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

import com.google.gson.Gson;
import com.loopj.android.image.SmartImageView;
import com.yin.myhealthy.GlobalDate;
import com.yin.myhealthy.R;
import com.yin.myhealthy.bean.MedicineContextBean;
import com.yin.myhealthy.bean.NewsContextBean;
import com.yin.myhealthy.controller.MedicineController;
import com.yin.myhealthy.utils.AsyncHttpClientUtil;
import com.yin.myhealthy.utils.StringUtil;

public class MedicineContextActivity extends Activity {

	private ImageButton ib_back;
	private ImageButton ib_more;

	private TextView tv_name;
	private TextView tv_category;
	private TextView tv_tag;
	private TextView tv_factory;
	private SmartImageView img_context;
	private TextView tv_context;

	private MedicineContextBean bean;
	private MedicineController controller;
	
	private final String TAG = "MedicineContextActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// 设置隐藏标题栏,必须在setContentView()前调用
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_context_medicine);

		initView();
		initData();

	}

	private void initView() {

		ib_back = (ImageButton) findViewById(R.id.ib_context_back);
		ib_more = (ImageButton) findViewById(R.id.ib_context_more);

		tv_name = (TextView) findViewById(R.id.tv_context_name);
		tv_category = (TextView) findViewById(R.id.tv_context_category);
		tv_tag = (TextView) findViewById(R.id.tv_context_tag);
		tv_factory = (TextView) findViewById(R.id.tv_context_factory);

		img_context = (SmartImageView) findViewById(R.id.siv_context_img);
		tv_context = (TextView) findViewById(R.id.tv_context_contextmsg);

		setListend();
	}

	// 设置按键监听
	private void setListend() {
		ib_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				MedicineContextActivity.this.finish();
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
		controller = new MedicineController();
		controller.getBeanData(news_url, handler);

	}

	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			if (msg.what == GlobalDate.GET_DATA_SUCCESS) {

				// 更新数据
				MedicineContextBean bean = (MedicineContextBean) msg.obj;
				if(bean == null){
					Log.d(TAG, "MedicineContextBean is null");
				}
				
				if(bean != null){
					reflashViewFromNet(bean);
				}
			}
			
		}
	};



	// 根据json的数据更新页面
	private void reflashViewFromNet(MedicineContextBean bean) {
		this.bean = bean;

		tv_name.setText(bean.getName());
		tv_category.setText(tv_category.getText() + bean.getCategoryName());
		tv_tag.setText(tv_tag.getText() + bean.getTag());
		tv_factory.setText(tv_factory.getText() + bean.getFactory());

		// 因为有些详细内容是没有图片的
		if (bean.getImage() != null) {
			// 暂时不显示图片
			// img_context.setImageUrl(bean.getImage());
		} else {
			img_context.setVisibility(View.GONE);
		}

		tv_context.setText(StringUtil.DelEmptyLine(Html
				.fromHtml(bean.getMessage()).toString().trim()));
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
		// titleUrl是标题的网络链接，仅在人人网和QQ空间使用
		// oks.setTitleUrl("http://sharesdk.cn");
		// text是分享文本，所有平台都需要这个字段
		// 换行并且去空格

		oks.setText(bean.getName()
				+ '\n'
				+ StringUtil.DelEmptyLine(Html.fromHtml(bean.getMessage())
						.toString().trim()));
		// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
		// oks.setImagePath("/sdcard/test.jpg");
		// url仅在微信（包括好友和朋友圈）中使用
		// oks.setUrl("http://sharesdk.cn");
		// comment是我对这条分享的评论，仅在人人网和QQ空间使用
		// oks.setComment("我是测试评论文本");
		// site是分享此内容的网站名称，仅在QQ空间使用
		// oks.setSite(getString(R.string.app_name));
		// siteUrl是分享此内容的网站地址，仅在QQ空间使用
		// oks.setSiteUrl("http://sharesdk.cn");

		// 启动分享GUI
		oks.show(this);
	}

}
