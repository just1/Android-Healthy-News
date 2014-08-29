package com.yin.myhealthy.bean;

import java.util.List;

import com.yin.myhealthy.bean.healthyknow.HealKnowCategoryBean;

public class SendStatusBean<T> {

	public boolean getSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public List<HealKnowCategoryBean> getYi18() {
		return yi18;
	}
	public void setYi18(List<HealKnowCategoryBean> yi18) {
		this.yi18 = yi18;
	}
	private boolean success;
	private List<HealKnowCategoryBean> yi18; 
}
