package com.fykj.gpc.bmi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class ListViewTest extends Activity {

	private SimpleAdapter adapter;
	private ArrayList<Map<String,Object>> mModelData;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview2);
		//���ڼ̳�����ͨActivity��ListView�б���Ҫ�ȶ���ø�����Layout�����ڸ������в����һ��ListView
		//�ڳ�������Ҫ�ȸ���ID��ȡListView����
		ListView lv=(ListView) findViewById(R.id.lv_01);
		//�����������������
		//�����Ǽ򵥵����飬
		String[] strarr=new String[]{"tom","jim","tonny","angle"};
		ArrayAdapter<String> adapter1=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice,strarr);
		//Ҳ������Map����
		mModelData=initModelData();  
		adapter = new SimpleAdapter(this, mModelData,R.layout.listview, new String[]{"pic","name","type"}, new int[]{R.id.listitem_pic,R.id.listitem_title,R.id.listitem_content});  
		//lv.setAdapter(adapter1);
		lv.setAdapter(adapter);  
	}  
	
	private ArrayList<Map<String,Object>> initModelData(){  
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
		item3.put("name", "eric");
		item3.put("type", "superman");  
		mModelData.add(item3);  
		return mModelData;
	}  
}
