package com.fykj.gpc.bmi;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

public class ExpandableTest extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.expandable);
		ExpandableListView eplv=(ExpandableListView) findViewById(R.id.expandableListView);
		//ʵ����һ��adapter
		ExpandableListAdapter adapter=new BaseExpandableListAdapter() {
			private String[] groupStrs=new String[]{"�ҵ���","������","������"};
			private String[][] childStrss=new String[][]{{"���ӻ�","�����"},{"�ֻ�","DV��","�ʼǱ�����"},{"ԡ��","��Ͱ"}};
			@Override
			public int getGroupCount() {
				return groupStrs.length;
			}

			@Override
			public int getChildrenCount(int groupPosition) {
				return childStrss[groupPosition].length;
			}

			@Override
			public Object getGroup(int groupPosition) {
				return groupStrs[groupPosition];
			}

			@Override
			public Object getChild(int groupPosition, int childPosition) {
				return childStrss[groupPosition][childPosition];
			}

			@Override
			public long getGroupId(int groupPosition) {
				return groupPosition;
			}

			@Override
			public long getChildId(int groupPosition, int childPosition) {
				return childPosition;
			}

			@Override
			public boolean hasStableIds() {
				return true;
			}

			/**
			 * �÷�������ÿ����ѡ������
			 */
			@Override
			public View getGroupView(int groupPosition, boolean isExpanded,
					View convertView, ViewGroup parent) {
				TextView tv=new TextView(getApplicationContext());
				AbsListView.LayoutParams params=new AbsListView.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
				tv.setPadding(35, 5, 5, 5);
				tv.setLayoutParams(params);
				tv.setText(groupStrs[groupPosition]);
				return tv;
			}
			/**
			 * �÷�������ÿ����ѡ������
			 */
			@Override
			public View getChildView(int groupPosition, int childPosition,
					boolean isLastChild, View convertView, ViewGroup parent) {
				TextView tv=new TextView(getApplicationContext());
				AbsListView.LayoutParams params=new AbsListView.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
				tv.setLayoutParams(params);
				tv.setPadding(45, 5, 5, 5);
				tv.setText(getChild(groupPosition, childPosition).toString());
				return tv;
			}

			@Override
			public boolean isChildSelectable(int groupPosition,
					int childPosition) {
				return true;
			}
			
			
		};
		eplv.setAdapter(adapter);
	}
}
