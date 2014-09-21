package com.yin.myhealthy;

public class GlobalDate {

	/*
	 * API接口地址
	 */

	/*
	 * 网站网址
	 */
	public static final String WEB_ADDRESS = "http://www.yi18.net/";

	/*
	 * 资讯
	 */
	// 资讯列表
	public static final String API_NEWS_LIST = "http://api.yi18.net/news/list";

	// 详细资讯内容
	public static final String API_NEWS_MORE = "http://api.yi18.net/news/show?id=";

	/*
	 * 饮食
	 */
	// 饮食列表（按疗效）
	public static final String API_DIET_MENU_LIST = "http://api.yi18.net/food/menulist";

	// 详细饮食内容
	public static final String API_DIET_MORE = "http://api.yi18.net/food/show?id=";

	/*
	 * 药箱
	 */
	// 药品列表
	public static final String API_MEDICINE_LIST = "http://api.yi18.net/drug/list";

	// 详细药品内容
	public static final String API_MEDICINE_MORE = "http://api.yi18.net/drug/show?id=";

	/*
	 * 健康知识
	 */
	// 分类列表
	public static final String API_KNOWLEDGE_LIST = "http://api.yi18.net/lore/list";

	// 健康知识信息详细
	public static final String API_KNOWLEDGE_MORE = "http://api.yi18.net/lore/show?id=";
	
	
	/*
	 * 操作标志位
	 * */
	public static final int GET_DATA_SUCCESS = 1;
	public static final int GET_DATA_FAIL = -1;
}
