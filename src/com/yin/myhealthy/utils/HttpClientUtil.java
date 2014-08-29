package com.yin.myhealthy.utils;

import java.io.InputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;


public class HttpClientUtil {
		
	/**
	 * �����ip
	 */
	private static final String PROXY="";
	/**
	 * ����Ķ˿�
	 */
	private static final int PORT=0;
	
	private static final String ENCONDING="UTF-8";
	
	private HttpClient client;

	private HttpPost post;
	private HttpGet get;

	public HttpClientUtil() {
		client = new DefaultHttpClient();
		// �ж��Ƿ���Ҫ���ô�����Ϣ
		if (StringUtils.isNotBlank(PROXY)) {
			// ���ô�����Ϣ
			HttpHost host = new HttpHost(PROXY, PORT);
			client.getParams()
					.setParameter(ConnRoutePNames.DEFAULT_PROXY, host);
		}
	}

	/**
	 * ��ָ�������ӷ���xml�ļ�
	 * 
	 * @param uri
	 * @param xml
	 */
	public InputStream sendXml(String uri, String xml) {
		post = new HttpPost(uri);

		try {
			StringEntity entity = new StringEntity(xml, ENCONDING);
			post.setEntity(entity);

			HttpResponse response = client.execute(post);

			// 200
			if (response.getStatusLine().getStatusCode() == 200) {
				return response.getEntity().getContent();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}
}
