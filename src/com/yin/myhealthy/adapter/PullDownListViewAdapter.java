package com.yin.myhealthy.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.loopj.android.image.SmartImageView;
import com.yin.myhealthy.R;

public class PullDownListViewAdapter extends BaseAdapter {
	private List<String> titleList;
	private List<String> imgList;
	private TextView tv;
	private LayoutInflater mInflater = null;
	private Context context;

	public PullDownListViewAdapter(List<String> titleList, Context context) {
		this.titleList = titleList;
		this.context = context;
	}
	
	public PullDownListViewAdapter(List<String> titleList,List<String> imgList, Context context) {
		this.titleList = titleList;
		this.imgList = imgList;
		this.context = context;
	}

	@Override
	public int getCount() {
		return titleList.size();
	}

	@Override
	public Object getItem(int position) {
		return titleList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View view = View.inflate(context, R.layout.lv_pd_item, null);
		
		SmartImageView sivIcon = (SmartImageView) view.findViewById(R.id.siv_listview_item_icon);
		sivIcon.setImageUrl(imgList.get(position));		// …Ë÷√Õº∆¨
		
		tv = (TextView) view.findViewById(R.id.tv_lv_item);
		tv.setText(titleList.get(position));
		
		return view;
	}
}
