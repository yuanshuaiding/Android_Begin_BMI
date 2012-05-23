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
		//实例化一个adapter
		ExpandableListAdapter adapter=new BaseExpandableListAdapter() {
			private String[] groupStrs=new String[]{"家电类","数码类","厨卫类"};
			private String[][] childStrss=new String[][]{{"电视机","电冰箱"},{"手机","DV机","笔记本电脑"},{"浴缸","马桶"}};
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
			 * 该方法决定每个组选项的外观
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
			 * 该方法决定每个子选项的外观
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
