package com.yin.myhealthy.view.diet;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.yin.myhealthy.GlobalDate;
import com.yin.myhealthy.base.BaseListController;
import com.yin.myhealthy.base.BaseListViewFragment;

public class DietSingleFragment extends BaseListViewFragment {
	public DietSingleFragment(String url, String id) {
		super(url,id);
	}
	
	public DietSingleFragment(String url, String id,BaseListController ctrl) {
		super(url,id);
		setController(ctrl);
	}


	// 设置listView的item点击处理
	//以后可以把ContextActivity.class和API_XXX_MORE作为参数，抽取出来
	@Override
	public void setLvItemClickListener() {
		
		// listview设置点击处理事件
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String diet_id = controller.getIdList().get(position-1);
				String url = GlobalDate.API_DIET_MORE + diet_id;

				Intent startIntent = new Intent(getActivity(),
						DietContextActivity.class);
				startIntent.putExtra("url", url);
				startActivity(startIntent);
			}
		});
	}

}
