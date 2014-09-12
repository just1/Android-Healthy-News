package com.yin.myhealthy.view.knowledge;

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
import com.yin.myhealthy.bean.KnowledgeContextBean;
import com.yin.myhealthy.utils.AsyncHttpClientUtil;
import com.yin.myhealthy.utils.StringUtil;

public class KnowledgeContextActivity extends Activity {

	private ImageButton ib_back;
	private ImageButton ib_more;

	private TextView tv_title;
	private TextView tv_time;
	private TextView tv_className;
	private SmartImageView img_context;
	private TextView tv_context;
	private KnowledgeContextBean bean;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// �������ر�����,������setContentView()ǰ����
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_context_knowledge);

		initView();
		initData();

	}

	private void initView() {

		ib_back = (ImageButton) findViewById(R.id.ib_context_back);
		ib_more = (ImageButton) findViewById(R.id.ib_context_more);

		tv_title = (TextView) findViewById(R.id.tv_context_title);
		tv_time = (TextView) findViewById(R.id.tv_context_time);
		tv_className = (TextView) findViewById(R.id.tv_context_className);

		img_context = (SmartImageView) findViewById(R.id.siv_context_img);
		tv_context = (TextView) findViewById(R.id.tv_context_contextmsg);

		setListend();
	}

	// ���ð�������
	private void setListend() {
		ib_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				KnowledgeContextActivity.this.finish();
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
		String url = (String) intent.getSerializableExtra("url");

		// ��������
		AsyncHttpClientUtil.RequestAPI(url, null, handler);
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
			KnowledgeContextBean bean = gson.fromJson(someJsonStr,
					KnowledgeContextBean.class);

			// ����json�����ݸ���ҳ��
			reflashViewFromNet(bean);

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	// ����json�����ݸ���ҳ��
	private void reflashViewFromNet(KnowledgeContextBean bean) {
		this.bean = bean;

		tv_title.setText(bean.getTitle());
		tv_time.setText(tv_time.getText() + bean.getTime());
		tv_className.setText(tv_className.getText() + bean.getClassName());

		// ���ﲻ��ʾͼƬ

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
