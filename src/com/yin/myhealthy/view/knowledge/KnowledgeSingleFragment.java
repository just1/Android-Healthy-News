package com.yin.myhealthy.view.knowledge;

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
import com.yin.myhealthy.bean.KnowledgeListBean;
import com.yin.myhealthy.utils.AsyncHttpClientUtil;
import com.yin.myhealthy.view.medicine.MedicineContextActivity;

public class KnowledgeSingleFragment extends BaseListViewFragment {

	public KnowledgeSingleFragment(String url, String id) {
		super(url,id);
	}
	
	public KnowledgeSingleFragment(String url, String id,BaseListController ctrl) {
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
				String knowledge_id = controller.getIdList().get(position-1);
				String url = GlobalDate.API_KNOWLEDGE_MORE + knowledge_id;

				Intent startIntent = new Intent(getActivity(),
						KnowledgeContextActivity.class);
				startIntent.putExtra("url", url);
				startActivity(startIntent);
			}

		});

	}

}
