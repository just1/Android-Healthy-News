package com.yin.myhealthy.engine;

import java.util.List;

import android.os.Handler;

import com.yin.myhealthy.GlobalDate;
import com.yin.myhealthy.bean.KeyValuesBean;
import com.yin.myhealthy.utils.AsyncHttpClientUtil;

public class HealthyKnowEngine {
	
	//��ȡ����֪ʶ�����б�
	public void GetHealKnowCategoryList(Handler handler){
		AsyncHttpClientUtil.GetXml(GlobalDate.API_HEAYKNOW_CATE_LIST, null, handler);
	}
	
	
	/*
	 * ��ȡ������Ϣ֪ʶ�б�
	 * ������������¶����Ǳ���ģ���
	 * page		int		����ҳ����Ĭ����1
	 * limit	int		ÿҳ���ص�������Ĭ����20
	 * type		string	����ʽ ��id������ʱ�䣻count��������࣬Ĭ����id��������ʱ�䡣
	 * id		long	id��������ָ֪ʶ�����ID Ĭ��Ϊ null ��Ҳ����ȫ����
	 */
	public void GetHealthyKnowList(List<KeyValuesBean> list,Handler handler){
		String urlStr = GlobalDate.API_HEAYKNOW_LIST;
		
		//��������б��null���ҷǿ�
		if((list != null) && (!list.isEmpty()) ){
			urlStr += "?";
		
			//����������ò����ò������Ͳ���ֵ
			for(int i=0;i<list.size();i++){
				urlStr += list.get(i).getKey() + "=" + list.get(i).getValues();
				if(i != (list.size()-1)){
					urlStr += "&";
				}
			}
		
		}
		
		System.out.println("urlStr:"+urlStr);
		
		AsyncHttpClientUtil.GetXml(urlStr, null, handler);
	}
	
	
	
	
	/*
	 * ��ȡ����֪ʶ����
	 * �������
	 * id(����)		long		����֪ʶ����ID���
	 */
	public void GetHealthyKnowBean(String id,Handler handler){
		String urlStr = GlobalDate.API_HEAYKNOW_MORE;
		if(id != null){
			urlStr += "?id="+id;
		}
		
		AsyncHttpClientUtil.GetXml(urlStr, null, handler);
	}
	
	
}
	

