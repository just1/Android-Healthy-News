package com.yin.myhealthy.view.news;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yin.myhealthy.GlobalDate;
import com.yin.myhealthy.base.BaseListViewFragment;
import com.yin.myhealthy.base.BaseListViewFragment_BAK;
import com.yin.myhealthy.bean.KeyValuesBean;
import com.yin.myhealthy.bean.NewsListBean;
import com.yin.myhealthy.utils.AsyncHttpClientUtil;

public class NewsSingleFragment extends BaseListViewFragment_BAK {

	// ��������id��
	private String id;

	public NewsSingleFragment(String url, String id) {
		super(url);
		this.id = id;
	}

	@Override
	protected void AnalyJSONToList(String jsonStr) {
		JSONObject obj;
		String someJsonStr = null;
		List<NewsListBean> newsListBeanList = null;
		try {

			// ��JSONObject��ȡָ���ε�JSON����
			obj = new JSONObject(jsonStr);
			someJsonStr = (String) obj.getJSONArray("yi18").toString();

			// ��GSON�������л���������Ӧ��ʵ����
			newsListBeanList = new Gson().fromJson(someJsonStr,
					new TypeToken<List<NewsListBean>>() {
					}.getType());

		} catch (JSONException e) {
			e.printStackTrace();
		}

		
		// �ж��Ƿ�ȫ�����������
		if (newsListBeanList.size() == 0) {
			System.out.println("����ȫ��������");
			Toast.makeText(context, "����ȫ��������", 0).show();

			return;
		}
		
		
		for (int i = 0; i < newsListBeanList.size(); i++) {
			titleList.add(newsListBeanList.get(i).getTitle());
			idList.add(String.valueOf(newsListBeanList.get(i).getId()));

			/*
			 * ͼƬ��ַʾ���� http://www.yi18.net/img/news/20140905132030_697.jpg
			 */
			imgList.add(GlobalDate.WEB_ADDRESS
					+ newsListBeanList.get(i).getImg());
		}
	}

	@Override
	protected void getListViewDate() {
		// �������磬��ȡ����֪ʶ�����б�
		List<KeyValuesBean> list = new ArrayList<KeyValuesBean>();

		// ��Ҫ���ص�ҳ��

		list.add(new KeyValuesBean("page", String.valueOf(clickCount)));

		list.add(new KeyValuesBean("limit", "20"));
		list.add(new KeyValuesBean("type", "id"));
		list.add(new KeyValuesBean("id", id));

		AsyncHttpClientUtil.RequestAPI(apiUrl, list, listDataHandler);
	}

	// ����listView��item�������
	@Override
	public void setLvItemClickListener() {

		// listview���õ�������¼�
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				String news_id = idList.get(position-1);
				String url = GlobalDate.API_NEWS_MORE + news_id;

				Intent startIntent = new Intent(getActivity(),
						NewsContextActivity.class);
				startIntent.putExtra("url", url);
				startActivity(startIntent);
			}

		});

	}

}
