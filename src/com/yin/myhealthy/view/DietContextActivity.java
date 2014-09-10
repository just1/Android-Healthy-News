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
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

import com.google.gson.Gson;
import com.loopj.android.image.SmartImageView;
import com.yin.myhealthy.R;
import com.yin.myhealthy.bean.DietContextBean;
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// �������ر�����,������setContentView()ǰ����
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		setContentView(R.layout.activity_context_diet);

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

	// ���ð�������
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

		// ��������
		AsyncHttpClientUtil.RequestAPI(news_url, null, handler);
	}

	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {

			String jsonStr = (String) msg.obj;

			// ʵ�ʷ�������ᱨ��ָ���쳣
			if (jsonStr != null) {
				if (!jsonStr.isEmpty()) {
					AnalyJSONToList(jsonStr);
				}
			}
		}
	};

	private void AnalyJSONToList(String jsonStr) {
		JSONObject obj;
		String someJsonStr = null;
		try {

			// ��JSONObject��ȡָ���ε�JSON����
			obj = new JSONObject(jsonStr);
			someJsonStr = (String) obj.get("yi18").toString();

			// ��GSON�������л���������Ӧ��ʵ����
			Gson gson = new Gson();
			DietContextBean bean = gson.fromJson(someJsonStr,
					DietContextBean.class);

			// ����json�����ݸ���ҳ��
			reflashViewFromNet(bean);

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	// ����json�����ݸ���ҳ��
	private void reflashViewFromNet(DietContextBean bean) {
		this.bean = bean;

		tv_name.setText(bean.getName());

		// �ݲ���ʾ��Ч
		// tv_menu.setText(tv_menu.getText() + bean.getMenu());
		tv_bar.setText(tv_bar.getText() + bean.getBar());

		// ��Ϊ��Щ��ϸ������û��ͼƬ��
		if (bean.getImg() != null) {
			// �ݲ���ʾͼƬ
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
		// �ر�sso��Ȩ
		oks.disableSSOWhenAuthorize();

		// ����ʱNotification��ͼ�������
		oks.setNotification(R.drawable.icon_1, getString(R.string.app_name));
		// title���⣬ӡ��ʼǡ����䡢��Ϣ��΢�š���������QQ�ռ�ʹ��
		oks.setTitle(bean.getName());
		// titleUrl�Ǳ�����������ӣ�������������QQ�ռ�ʹ��
		// oks.setTitleUrl("http://sharesdk.cn");
		// text�Ƿ����ı�������ƽ̨����Ҫ����ֶ�
		// ���в���ȥ�ո�

		oks.setText(bean.getName()
				+ '\n'
				+ StringUtil.DelEmptyLine(Html.fromHtml(bean.getDetailText())
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
