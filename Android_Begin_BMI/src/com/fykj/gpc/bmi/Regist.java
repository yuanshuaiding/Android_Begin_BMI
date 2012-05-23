package com.fykj.gpc.bmi;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;

public class Regist extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.regist);
		ArrayAdapter<String>  adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
        //添加下拉菜单项
        adapter.add("高管");
        adapter.add("主管");
        adapter.add("经理");
        Spinner spinner = (Spinner) findViewById(R.id.position);
        spinner.setAdapter(adapter);
        
        //设置自动完成文本框的备选项
        AutoCompleteTextView autotxt=(AutoCompleteTextView)findViewById(R.id.address);
        String[] addrs=new String[]{"china","canada","usa","japan","chichia","calumbia"};
        //创建适配器
        ArrayAdapter<String> adp=new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, addrs);
        autotxt.setAdapter(adp);
	}
	
}
