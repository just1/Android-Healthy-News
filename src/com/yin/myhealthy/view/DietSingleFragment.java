package com.yin.myhealthy.view;

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
import com.yin.myhealthy.bean.DietListBean;
import com.yin.myhealthy.bean.KeyValuesBean;
import com.yin.myhealthy.utils.AsyncHttpClientUtil;

public class DietSingleFragment extends BaseListViewFragment {

	// 新闻类别的id号
	private String id;

	public DietSingleFragment(String url, String id) {
		super(url);
		this.id = id;
	}

	@Override
	protected void AnalyJSONToList(String jsonStr) {
		JSONObject obj;
		String someJsonStr = null;
		List<DietListBean> dietListBeanList = null;
		try {

			// 用JSONObject获取指定段的JSON内容
			obj = new JSONObject(jsonStr);
			someJsonStr = (String) obj.getJSONArray("yi18").toString();

			// 用GSON来反序列化，生成相应的实体类
			dietListBeanList = new Gson().fromJson(someJsonStr,
					new TypeToken<List<DietListBean>>() {
					}.getType());

		} catch (JSONException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < dietListBeanList.size(); i++) {
			
			//判断是否全部都加载完毕
			if(!titleList.isEmpty()){		//如果titleList里面有数据
				//拿titleList里面第一个数据出来进行比较
				//如果相同，则不用继续加载进去，并其他全部加载完了
				if(titleList.get(0) == dietListBeanList.get(i).getName()){
					System.out.println("数据全部加载完");
					
					Toast.makeText(context, "数据全部加载完", 0).show();;
					
					return;
				}
			}
			
			
			titleList.add(dietListBeanList.get(i).getName());
			idList.add(String.valueOf(dietListBeanList.get(i).getId()));

			/*
			 * 图片地址示例： http://www.yi18.net/img/news/20140905132030_697.jpg
			 */
			imgList.add(GlobalDate.WEB_ADDRESS
					+ dietListBeanList.get(i).getImg());
		}
	}

	@Override
	protected void getListViewDate() {
		// 请求网络，获取健康知识分类列表
		List<KeyValuesBean> list = new ArrayList<KeyValuesBean>();

		// 需要返回的页号

		list.add(new KeyValuesBean("page", String.valueOf(clickCount)));

		list.add(new KeyValuesBean("limit", "20"));
		list.add(new KeyValuesBean("type", "id"));
		list.add(new KeyValuesBean("id", id));

		AsyncHttpClientUtil.RequestAPI(apiUrl, list, listDataHandler);
	}

	// 设置listView的item点击处理
	@Override
	public void setLvItemClickListener() {

		// listview设置点击处理事件
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				String diet_id = idList.get(position-1);
				String url = GlobalDate.API_DIET_MORE + diet_id;

				Intent startIntent = new Intent(getActivity(),
						DietContextActivity.class);
				startIntent.putExtra("url", url);
				startActivity(startIntent);
			}

		});

	}

}
