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

	private LayoutInflater mInflater = null;
	private Context context;

	public PullDownListViewAdapter(List<String> titleList, Context context) {
		this.titleList = titleList;
		this.context = context;
	}

	public PullDownListViewAdapter(List<String> titleList,
			List<String> imgList, Context context) {
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
		ViewHolder holder;
		
		if (convertView == null) {
			convertView = View.inflate(context, R.layout.lv_pd_item, null);
			holder = new ViewHolder();

			holder.sivIcon = (SmartImageView) convertView
					.findViewById(R.id.siv_listview_item_icon);
			holder.tv = (TextView) convertView.findViewById(R.id.tv_lv_item);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.sivIcon.setImageUrl(imgList.get(position)); // 设置图片
		holder.tv.setText(titleList.get(position));

		return convertView;
	}

	//item条目较少的时候就用static，否则不要用
	static class ViewHolder {
		SmartImageView sivIcon;
		TextView tv;
	}
}
