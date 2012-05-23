package com.fykj.gpc.bmi;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class TableTest extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tablelayout);
		Toast.makeText(this, getIntent().getStringExtra("msg"), Toast.LENGTH_SHORT).show();
	}
	
}
