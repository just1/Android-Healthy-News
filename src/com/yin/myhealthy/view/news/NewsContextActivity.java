package com.yin.myhealthy.view.news;

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

import com.loopj.android.image.SmartImageView;
import com.yin.myhealthy.GlobalDate;
import com.yin.myhealthy.R;
import com.yin.myhealthy.bean.MedicineContextBean;
import com.yin.myhealthy.bean.NewsContextBean;
import com.yin.myhealthy.controller.DietController;
import com.yin.myhealthy.controller.NewsController;
import com.yin.myhealthy.utils.AsyncHttpClientUtil;
import com.yin.myhealthy.utils.StringUtil;

public class NewsContextActivity extends Activity {

	private ImageButton ib_back;
	private ImageButton ib_more;

	private TextView tv_title;
	private TextView tv_time;
	private TextView tv_tag;
	private SmartImageView img_context;
	private TextView tv_context;

	private NewsContextBean bean;
	private NewsController controller;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// �������ر�����,������setContentView()ǰ����
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_context_news);

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

	// ���ð�������
	private void setListend() {
		ib_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				NewsContextActivity.this.finish();
			}
		});

		ib_more.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showShare();
			}
		});
	}

	private void initData() {

		Intent intent = getIntent();
		String url = (String) intent.getSerializableExtra("url");

		// ��������
		//AsyncHttpClientUtil.RequestAPI(news_url, null, handler);
		controller = new NewsController();
		controller.getBeanData(url, handler);
	}

	// ��ȡ�������󵽵����ݺ�ص�
	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			if (msg.what == GlobalDate.GET_DATA_SUCCESS) {

				// ��������
				NewsContextBean bean = (NewsContextBean) msg.obj;
				if(bean != null){
					reflashViewFromNet(bean);
				}
			}
		}
	};



	// ����json�����ݸ���ҳ��
	private void reflashViewFromNet(NewsContextBean bean) {
		this.bean = bean;

		tv_title.setText(bean.getTitle());
		tv_time.setText(tv_time.getText() + bean.getTime());
		tv_tag.setText(tv_tag.getText() + bean.getTag());

		// ��Ϊ��Щ��ϸ������û��ͼƬ��
		if (bean.getImg() != null) {
			img_context.setImageUrl(bean.getImg());
		} else {
			img_context.setVisibility(View.GONE);
		}

		//
		tv_context.setText(StringUtil.DelEmptyLine(Html
				.fromHtml(bean.getMessage()).toString().trim()));
	}

	private void showShare() {
		if (bean == null) {
			return;
		}

		ShareSDK.initSDK(this);
		OnekeyShare oks = new OnekeyShare();
		// �ر�sso��Ȩ
		oks.disableSSOWhenAuthorize();

		// ����ʱNotification��ͼ�������
		oks.setNotification(R.drawable.icon_1, getString(R.string.app_name));
		// title���⣬ӡ��ʼǡ����䡢��Ϣ��΢�š���������QQ�ռ�ʹ��
		oks.setTitle(bean.getTitle());
		// titleUrl�Ǳ�����������ӣ�������������QQ�ռ�ʹ��
		// oks.setTitleUrl("http://sharesdk.cn");
		// text�Ƿ����ı�������ƽ̨����Ҫ����ֶ�
		// ���в���ȥ�ո�

		oks.setText(bean.getTitle()
				+ '\n'
				+ StringUtil.DelEmptyLine(Html.fromHtml(bean.getMessage())
						.toString().trim()));
		// imagePath��ͼƬ�ı���·����Linked-In�����ƽ̨��֧�ִ˲���
		// oks.setImagePath("/sdcard/test.jpg");
		// url����΢�ţ��������Ѻ�����Ȧ����ʹ��
		// oks.setUrl("http://sharesdk.cn");
		// comment���Ҷ�������������ۣ�������������QQ�ռ�ʹ��
		// oks.setComment("���ǲ��������ı�");
		// site�Ƿ�������ݵ���վ���ƣ�����QQ�ռ�ʹ��
		// oks.setSite(getString(R.string.app_name));
		// siteUrl�Ƿ�������ݵ���վ��ַ������QQ�ռ�ʹ��
		// oks.setSiteUrl("http://sharesdk.cn");

		// ��������GUI
		oks.show(this);
	}

}
