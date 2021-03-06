package com.yin.myhealthy.base;

import java.util.ArrayList;
import java.util.List;

import android.os.Handler;
import android.os.Message;

import com.yin.myhealthy.GlobalDate;
import com.yin.myhealthy.bean.KeyValuesBean;
import com.yin.myhealthy.utils.AsyncHttpClientUtil;

public abstract class BaseListController {

	/*
	 * 以下是对于DataList页的数据处理
	 */
	protected List<String> titleList = new ArrayList<String>();
	protected List<String> imgList = new ArrayList<String>();
	protected List<String> idList = new ArrayList<String>();

	protected Handler returnListHandler;

	public List<String> getTitleList() {
		return titleList;
	}

	public List<String> getImgList() {
		return imgList;
	}

	public List<String> getIdList() {
		return idList;
	}

	// 用来获取健康信息列表的数据，并显示到ListView中
	protected Handler listDataHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			String jsonStr = (String) msg.obj;
			// Message returnMsg = new Message();

			// 实际发现这里会报空指针异常
			if (jsonStr != null) {
				if (!jsonStr.isEmpty()) {
					AnalyListJSONToList(jsonStr);
					returnListHandler
							.sendEmptyMessage(GlobalDate.GET_DATA_SUCCESS);
				} else {
					returnListHandler
							.sendEmptyMessage(GlobalDate.GET_DATA_FAIL);
				}
			} else {
				returnListHandler.sendEmptyMessage(GlobalDate.GET_DATA_FAIL);
			}

			// 更新ListView数据
			// adapter.notifyDataSetChanged();
		}
	};

	public void getListData(String apiUrl, int pageNum, String id,
			Handler handler) {
		// 先判断缓存是否有

		// 再判断本地是有有

		// 最后再请求网络

		returnListHandler = handler; // 用于当网络请求好了，回来通知界面

		// 请求网络，获取健康知识分类列表
		List<KeyValuesBean> list = new ArrayList<KeyValuesBean>();

		// 需要返回的页号

		list.add(new KeyValuesBean("page", String.valueOf(pageNum)));

		list.add(new KeyValuesBean("limit", "20"));
		list.add(new KeyValuesBean("type", "id"));
		list.add(new KeyValuesBean("id", id));

		AsyncHttpClientUtil.RequestAPI(apiUrl, list, listDataHandler);

	}

	abstract public void AnalyListJSONToList(String jsonStr);

	/*
	 * { JSONObject obj; String someJsonStr = null; List<DietListBean>
	 * dietListBeanList = null; try {
	 * 
	 * // 用JSONObject获取指定段的JSON内容 obj = new JSONObject(jsonStr); someJsonStr =
	 * (String) obj.getJSONArray("yi18").toString();
	 * 
	 * // 用GSON来反序列化，生成相应的实体类 dietListBeanList = new
	 * Gson().fromJson(someJsonStr, new TypeToken<List<DietListBean>>() {
	 * }.getType());
	 * 
	 * } catch (JSONException e) { e.printStackTrace(); }
	 * 
	 * // 判断是否全部都加载完毕 if (dietListBeanList.size() == 0) {
	 * System.out.println("数据全部加载完"); // Toast.makeText(context, "数据全部加载完",
	 * 0).show();
	 * 
	 * return; }
	 * 
	 * for (int i = 0; i < dietListBeanList.size(); i++) {
	 * 
	 * titleList.add(dietListBeanList.get(i).getName());
	 * idList.add(String.valueOf(dietListBeanList.get(i).getId()));
	 * 
	 * 
	 * // 图片地址示例： http://www.yi18.net/img/news/20140905132030_697.jpg
	 * 
	 * imgList.add(GlobalDate.WEB_ADDRESS + dietListBeanList.get(i).getImg()); }
	 * }
	 */

	/*
	 * 以下是对于Context页的数据处理
	 */
	protected Handler returnBeanHandler;
	protected Message returnBeanMsg;

	public void getBeanData(String apiUrl, Handler handler) {
		returnBeanHandler = handler;

		// 请求网络
		AsyncHttpClientUtil.RequestAPI(apiUrl, null, beanHandler);
	}

	Handler beanHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {

			String jsonStr = (String) msg.obj;

			// 实际发现这里会报空指针异常
			if (jsonStr != null) {
				if (!jsonStr.isEmpty()) {
					Object obj = AnalyDataJSONToBean(jsonStr);
					if (obj != null) {
						Message returnBeanMsg = new Message();
						returnBeanMsg.obj = obj;
						returnBeanMsg.what = GlobalDate.GET_DATA_SUCCESS;
						returnBeanHandler.sendMessage(returnBeanMsg);
					} else {
						returnBeanHandler
								.sendEmptyMessage(GlobalDate.GET_DATA_FAIL);
					}

				} else {
					returnBeanHandler
							.sendEmptyMessage(GlobalDate.GET_DATA_FAIL);
				}
			} else {
				returnBeanHandler.sendEmptyMessage(GlobalDate.GET_DATA_FAIL);
			}

		}
	};

	abstract public Object AnalyDataJSONToBean(String jsonStr);
	/*
	 * { JSONObject obj; String someJsonStr = null; try { //
	 * 用JSONObject获取指定段的JSON内容 obj = new JSONObject(jsonStr); someJsonStr =
	 * (String) obj.get("yi18").toString();
	 * 
	 * // 用GSON来反序列化，生成相应的实体类 Gson gson = new Gson(); DietContextBean bean =
	 * gson.fromJson(someJsonStr, DietContextBean.class);
	 * 
	 * // 根据json的数据更新页面 //reflashViewFromNet(bean); returnBeanMsg = new
	 * Message(); returnBeanMsg.obj = bean;
	 * returnBeanHandler.sendMessage(returnBeanMsg);
	 * 
	 * } catch (JSONException e) { e.printStackTrace(); } }
	 */

}
