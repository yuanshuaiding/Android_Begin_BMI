package com.fykj.gpc.bmi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class LayoutTest extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.linearlayout);
		Intent intent=getIntent();
		Bundle bd=intent.getExtras();
		Toast.makeText(this, bd.getString("msg").toString(), Toast.LENGTH_SHORT).show();
	}
	
}
