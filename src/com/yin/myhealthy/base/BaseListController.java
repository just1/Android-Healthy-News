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
	 * �����Ƕ���DataListҳ�����ݴ���
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

	// ������ȡ������Ϣ�б�����ݣ�����ʾ��ListView��
	protected Handler listDataHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			String jsonStr = (String) msg.obj;
			// Message returnMsg = new Message();

			// ʵ�ʷ�������ᱨ��ָ���쳣
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

			// ����ListView����
			// adapter.notifyDataSetChanged();
		}
	};

	public void getListData(String apiUrl, int pageNum, String id,
			Handler handler) {
		// ���жϻ����Ƿ���

		// ���жϱ���������

		// �������������

		returnListHandler = handler; // ���ڵ�����������ˣ�����֪ͨ����

		// �������磬��ȡ����֪ʶ�����б�
		List<KeyValuesBean> list = new ArrayList<KeyValuesBean>();

		// ��Ҫ���ص�ҳ��

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
	 * // ��JSONObject��ȡָ���ε�JSON���� obj = new JSONObject(jsonStr); someJsonStr =
	 * (String) obj.getJSONArray("yi18").toString();
	 * 
	 * // ��GSON�������л���������Ӧ��ʵ���� dietListBeanList = new
	 * Gson().fromJson(someJsonStr, new TypeToken<List<DietListBean>>() {
	 * }.getType());
	 * 
	 * } catch (JSONException e) { e.printStackTrace(); }
	 * 
	 * // �ж��Ƿ�ȫ����������� if (dietListBeanList.size() == 0) {
	 * System.out.println("����ȫ��������"); // Toast.makeText(context, "����ȫ��������",
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
	 * // ͼƬ��ַʾ���� http://www.yi18.net/img/news/20140905132030_697.jpg
	 * 
	 * imgList.add(GlobalDate.WEB_ADDRESS + dietListBeanList.get(i).getImg()); }
	 * }
	 */

	/*
	 * �����Ƕ���Contextҳ�����ݴ���
	 */
	protected Handler returnBeanHandler;
	protected Message returnBeanMsg;

	public void getBeanData(String apiUrl, Handler handler) {
		returnBeanHandler = handler;

		// ��������
		AsyncHttpClientUtil.RequestAPI(apiUrl, null, beanHandler);
	}

	Handler beanHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {

			String jsonStr = (String) msg.obj;

			// ʵ�ʷ�������ᱨ��ָ���쳣
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
	 * ��JSONObject��ȡָ���ε�JSON���� obj = new JSONObject(jsonStr); someJsonStr =
	 * (String) obj.get("yi18").toString();
	 * 
	 * // ��GSON�������л���������Ӧ��ʵ���� Gson gson = new Gson(); DietContextBean bean =
	 * gson.fromJson(someJsonStr, DietContextBean.class);
	 * 
	 * // ����json�����ݸ���ҳ�� //reflashViewFromNet(bean); returnBeanMsg = new
	 * Message(); returnBeanMsg.obj = bean;
	 * returnBeanHandler.sendMessage(returnBeanMsg);
	 * 
	 * } catch (JSONException e) { e.printStackTrace(); } }
	 */

}
