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
			int num=0;//���ڼ�¼��ȡ���ڼ����û�
			StringBuffer sb=new StringBuffer("");
			Resources rs=getResources();
			XmlResourceParser xmlparser=rs.getXml(i);
			//���û�ж�ȡ���ĵ��Ľ�β�ͼ�����ȡ
			try {
				while(xmlparser.getEventType()!=xmlparser.END_DOCUMENT){
					//����ǿ�ʼ��ǩ
					if(xmlparser.getEventType()==xmlparser.START_TAG){
						String tagName=xmlparser.getName();
						if(tagName.equals("user")){
							num+=1;
							sb.append("��"+num+"���û���Ϣ��\n����:"+xmlparser.getAttributeValue(0));
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
