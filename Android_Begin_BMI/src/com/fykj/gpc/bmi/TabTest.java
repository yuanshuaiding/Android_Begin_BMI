package com.fykj.gpc.bmi;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TabHost;

public class TabTest extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
	        setContentView(R.layout.tab); 
	        //步骤1：获得TabHost的对象，并进行初始化setup() 
	        TabHost tabs = (TabHost)findViewById(R.id.c92_tabhost);
	        //自定义TabHost需要调用该方法，否则无效
	        tabs.setup(); 
	        //步骤2：通过TabHost.TabSpec增加tab的一页，通过setContent()增加内容，通过setIndicator增加页的标签 
	        /*（1）增加第1页*/ 
	        TabHost.TabSpec spec = tabs.newTabSpec("Tag1");
	        spec.setContent (R.id.c92_tab1);
	        spec.setIndicator ("Clock");
	        tabs.addTab (spec);
	        /*（2）增加第2页*/ 
	        spec = tabs.newTabSpec("Tag2");
	        spec.setContent(R.id.c92_tab2);
	        spec.setIndicator("Button");
	        tabs.addTab(spec); 
	        //步骤3：可通过setCurrentTab(index)指定显示的页，从0开始计算。 
	        tabs.setCurrentTab (1);
	}
	
}
