package com.fykj.gpc.bmi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class FrameTest extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent=this.getIntent();
		String msg=intent.getStringExtra("msg");
		setContentView(R.layout.framelayout);
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}
	
}
