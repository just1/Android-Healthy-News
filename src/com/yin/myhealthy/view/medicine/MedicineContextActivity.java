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

		// �������ر�����,������setContentView()ǰ����
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

	// ���ð�������
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

		// ��������
		//AsyncHttpClientUtil.RequestAPI(news_url, null, handler);
		controller = new MedicineController();
		controller.getBeanData(news_url, handler);

	}

	// ��ȡ�������󵽵����ݺ�ص�
	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			if (msg.what == GlobalDate.GET_DATA_SUCCESS) {

				// ��������
				MedicineContextBean bean = (MedicineContextBean) msg.obj;
				if(bean != null){
					reflashViewFromNet(bean);
				}
			}
		}
	};

	// ����json�����ݸ���ҳ��
	private void reflashViewFromNet(MedicineContextBean bean) {
		this.bean = bean;

		tv_name.setText(bean.getName());
		tv_category.setText(tv_category.getText() + bean.getCategoryName());
		tv_tag.setText(tv_tag.getText() + bean.getTag());
		tv_factory.setText(tv_factory.getText() + bean.getFactory());

		// ��Ϊ��Щ��ϸ������û��ͼƬ��
		if (bean.getImage() != null) {
			// ��ʱ����ʾͼƬ
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
