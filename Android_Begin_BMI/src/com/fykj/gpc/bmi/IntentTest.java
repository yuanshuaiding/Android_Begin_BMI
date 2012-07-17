package com.fykj.gpc.bmi;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class IntentTest extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.intent);
		TextView tv_action=(TextView)findViewById(R.id.txt_action);
		TextView tv_category=(TextView)findViewById(R.id.txt_category);
		tv_action.setText("ActionÎª£º"+getIntent().getAction());
		tv_category.setText("CategoryÊôÐÔÎª£º"+getIntent().getCategories());
		View btn_finish=findViewById(R.id.btn_finish);
		btn_finish.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
}
