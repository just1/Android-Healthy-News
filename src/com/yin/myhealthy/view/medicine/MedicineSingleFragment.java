package com.yin.myhealthy.view.medicine;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.yin.myhealthy.GlobalDate;
import com.yin.myhealthy.base.BaseListController;
import com.yin.myhealthy.base.BaseListViewFragment;

public class MedicineSingleFragment extends BaseListViewFragment {

	public MedicineSingleFragment(String url, String id) {
		super(url,id);
	}
	
	public MedicineSingleFragment(String url, String id,BaseListController ctrl) {
		super(url,id);
		setController(ctrl);
	}


	// ����listView��item�������
	@Override
	public void setLvItemClickListener() {

		// listview���õ�������¼�
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String medicine_id = controller.getIdList().get(position-1);
				String url = GlobalDate.API_MEDICINE_MORE + medicine_id;

				Intent startIntent = new Intent(getActivity(),
						MedicineContextActivity.class);
				startIntent.putExtra("url", url);
				startActivity(startIntent);
			}

		});

	}

}
