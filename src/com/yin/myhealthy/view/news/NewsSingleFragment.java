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
import com.yin.myhealthy.base.BaseListController;
import com.yin.myhealthy.base.BaseListViewFragment;
import com.yin.myhealthy.base.BaseListViewFragment_BAK;
import com.yin.myhealthy.bean.KeyValuesBean;
import com.yin.myhealthy.bean.NewsListBean;
import com.yin.myhealthy.utils.AsyncHttpClientUtil;
import com.yin.myhealthy.view.medicine.MedicineContextActivity;

public class NewsSingleFragment extends BaseListViewFragment {

	public NewsSingleFragment(String url, String id) {
		super(url,id);
	}
	
	public NewsSingleFragment(String url, String id,BaseListController ctrl) {
		super(url,id);
		setController(ctrl);
	}


	// 设置listView的item点击处理
	@Override
	public void setLvItemClickListener() {

		// listview设置点击处理事件
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String news_id = controller.getIdList().get(position-1);
				String url = GlobalDate.API_NEWS_MORE + news_id;

				Intent startIntent = new Intent(getActivity(),
						NewsContextActivity.class);
				startIntent.putExtra("url", url);
				startActivity(startIntent);
			}
		});

	}
	
}
