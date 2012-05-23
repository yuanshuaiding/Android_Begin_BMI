package com.fykj.gpc.bmi;

import java.text.DecimalFormat;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Report extends Activity {
	private TextView fieldResult,fieldAdvice;
	private Button back;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.report);
		findViews();
		showResult();
		setOnClickListeners();
	}
	private void setOnClickListeners() {
		// TODO Auto-generated method stub
		back.setOnClickListener(backListner);
	}
	private Button.OnClickListener backListner=new Button.OnClickListener(){

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Report.this.finish();
		}};
	private void showResult() {
		// TODO Auto-generated method stub
		DecimalFormat nf=new DecimalFormat("0.00");
		Bundle bundle=this.getIntent().getExtras();
		Double height=Double.parseDouble(bundle.getString("height"))/100;
		Double weight=Double.parseDouble(bundle.getString("weight"));
		try {
			Double result = weight/(height*height);
			fieldResult.setText(nf.format(result));
			if(result>25){
				fieldAdvice.setText(R.string.heavy);
			}else if(result<20){
				fieldAdvice.setText(R.string.light);
			}else{
				fieldAdvice.setText(R.string.average);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Toast.makeText(Report.this, "请确保你填的数字有效！",Toast.LENGTH_SHORT).show();
			Report.this.finish();
		}
	}
	private void findViews() {
		// TODO Auto-generated method stub
		fieldResult=(TextView)findViewById(R.id.result);
		fieldAdvice=(TextView)findViewById(R.id.advice);
		back=(Button)findViewById(R.id.back);
	}
}
