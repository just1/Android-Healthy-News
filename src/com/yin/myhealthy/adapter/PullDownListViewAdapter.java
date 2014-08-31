package com.yin.myhealthy.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myhealthy.R;

public class PullDownListViewAdapter extends BaseAdapter {
	private List<String> list;
	private TextView tv;
	private LayoutInflater mInflater = null;
	private Context context;

	public PullDownListViewAdapter(List<String> list,Context context) {
		this.list = list;
		this.context = context;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View view = View.inflate(context, R.layout.lv_pd_item, null);
		//View view = View.inflate(HomeActivity.this, R.layout.list_item_home, null);
		
		
		tv = (TextView) view.findViewById(R.id.tv_lv_item);
		tv.setText(list.get(position));
		
		return view;
	}
}
