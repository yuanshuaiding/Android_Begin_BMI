package com.fykj.gpc.bmi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class ListViewActivity extends ListActivity {

	private SimpleAdapter adapter;
	private ArrayList<Map<String,Object>> mModelData;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initModelData();  
		adapter = new SimpleAdapter(this, mModelData, R.layout.listview, 
		new String[]{"pic","name","type"}, new int[]{R.id.listitem_pic,R.id.listitem_title,R.id.listitem_content});  
		//因为继承了ListActivity所以直接调用setListAdapter即可
		this.setListAdapter(adapter);  
		this.setTitle("直接继承ListActivity");
		}  
	
	private void initModelData(){  
		mModelData = new ArrayList<Map<String, Object>>();  
		Map<String, Object> item = new HashMap<String,Object>();  
		item.put("pic", R.drawable.icon);
		item.put("name", "Linux");
		item.put("type", "OS");
		mModelData.add(item);  
		Map<String, Object> item2 = new HashMap<String,Object>();  
		item2.put("pic", R.drawable.icon);
		item2.put("name", "Android");
		item2.put("type", "Platform");  
		mModelData.add(item2);  
		Map<String, Object> item3 = new HashMap<String,Object>();  
		item3.put("pic", R.drawable.icon);
		item3.put("name", "Tomato");
		item3.put("type", "Fruit");  
		mModelData.add(item3);  
		}  
}
