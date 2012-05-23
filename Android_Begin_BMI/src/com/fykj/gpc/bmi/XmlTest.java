package com.fykj.gpc.bmi;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class XmlTest extends Activity {
	private TextView xml_txt;
	private Button readBtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xml_test);
		findViews();
		setOnClickListeners();
	}
	private void setOnClickListeners() {
		readBtn.setOnClickListener((android.view.View.OnClickListener)readListner);
	}
	
	private Button.OnClickListener readListner=new Button.OnClickListener(){

		@Override
		public void onClick(View v) {
			readXmlFile(R.xml.user);
		}

		@SuppressWarnings("static-access")
		private void readXmlFile(int i) {
			int num=0;//用于记录读取到第几个用户
			StringBuffer sb=new StringBuffer("");
			Resources rs=getResources();
			XmlResourceParser xmlparser=rs.getXml(i);
			//如果没有读取到文档的结尾就继续读取
			try {
				while(xmlparser.getEventType()!=xmlparser.END_DOCUMENT){
					//如果是开始标签
					if(xmlparser.getEventType()==xmlparser.START_TAG){
						String tagName=xmlparser.getName();
						if(tagName.equals("user")){
							num+=1;
							sb.append("第"+num+"个用户信息：\n姓名:"+xmlparser.getAttributeValue(0));
						}
					}
					xmlparser.next();
				}
				xml_txt.setText(sb.toString());
			} catch (XmlPullParserException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}};

	private void findViews() {
		xml_txt=(TextView)findViewById(R.id.xml_txt);
		readBtn=(Button)findViewById(R.id.read);
		
	}
	
}
