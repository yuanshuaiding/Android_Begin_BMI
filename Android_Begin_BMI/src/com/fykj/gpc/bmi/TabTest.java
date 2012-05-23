package com.fykj.gpc.bmi;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TabHost;

public class TabTest extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
	        setContentView(R.layout.tab); 
	        //����1�����TabHost�Ķ��󣬲����г�ʼ��setup() 
	        TabHost tabs = (TabHost)findViewById(R.id.c92_tabhost);
	        //�Զ���TabHost��Ҫ���ø÷�����������Ч
	        tabs.setup(); 
	        //����2��ͨ��TabHost.TabSpec����tab��һҳ��ͨ��setContent()�������ݣ�ͨ��setIndicator����ҳ�ı�ǩ 
	        /*��1�����ӵ�1ҳ*/ 
	        TabHost.TabSpec spec = tabs.newTabSpec("Tag1");
	        spec.setContent (R.id.c92_tab1);
	        spec.setIndicator ("Clock");
	        tabs.addTab (spec);
	        /*��2�����ӵ�2ҳ*/ 
	        spec = tabs.newTabSpec("Tag2");
	        spec.setContent(R.id.c92_tab2);
	        spec.setIndicator("Button");
	        tabs.addTab(spec); 
	        //����3����ͨ��setCurrentTab(index)ָ����ʾ��ҳ����0��ʼ���㡣 
	        tabs.setCurrentTab (1);
	}
	
}
