package com.yin.myhealthy.bean;


//���ڱ���JSON���ʵ�API�Ĳ������Ͳ���ֵ
public class KeyValuesBean {
	public KeyValuesBean(String key, String values) {
		super();
		this.key = key;
		this.values = values;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValues() {
		return values;
	}
	public void setValues(String values) {
		this.values = values;
	}
	
	private String key;
	private String values;
	
	

}
