package com.yin.myhealthy.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.yin.myhealthy.bean.DietListBean;

public class ListViewDB extends SQLiteOpenHelper{

	
	final String CREATE_TABLE_NEWS = "";
	
	//��ʳ�������ݿ�
	final String CREATE_TABLE_DIET = "create table if not exists diet("
			+ " d_id int primary key, "
			+ " d_title string not null, "
			+ " d_time time,"
			+ " d_image string"
			+ ");";
	
	final String CREATE_TABLE_MEDICINE = "";
	final String CREATE_TABLE_KNOWLEDGE = "";
	
	
	
	public ListViewDB(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		//��һ��ʹ�����ݿ�ʱ�Զ�����
		db.execSQL(CREATE_TABLE_DIET);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
	
	//����
	public void InsertDietData(DietListBean bean){
		//insert into diet values(5,"���",'2014-01-17 00:20:00',"/img/12.jpg");
		
		
		
		getReadableDatabase().execSQL("insert into diet values("
				+ String.valueOf(bean.getId())
				+ ","
				+ bean.getName()
				+ ",?"
				+ ","
				+ "?"
				+ ")");
		
		
	}
	
	
	//ɾ��
	public void DeleteDietData(){
		
	}
	
	
	//��ѯ
	public void CheckDietData(SQLiteDatabase db){
		//SELECT * FROM diet ORDER BY d_id DESC limit 0,10;
		db.execSQL("select * from diet ORDER BY d_id DESC;");
		
		Cursor cursor = getReadableDatabase().rawQuery("select * from diet ORDER BY d_id DESC;", null);
		
		
	}
}
