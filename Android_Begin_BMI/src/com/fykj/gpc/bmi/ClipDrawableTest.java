package com.fykj.gpc.bmi;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.graphics.drawable.ClipDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

public class ClipDrawableTest extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.clipdraw);
		ImageView imgv=(ImageView)findViewById(R.id.imageView1);
		final ClipDrawable cdr=(ClipDrawable)imgv.getDrawable();
		
		final Handler myHandler=new Handler(){

			@Override
			public void handleMessage(Message msg) {
				cdr.setLevel(msg.what);
				super.handleMessage(msg);
			}
			
		};
		
		final Timer timer=new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				Message msg=new Message();
				msg.what=cdr.getLevel()+100;
				myHandler.sendMessage(msg);
				if(msg.what>=10000){
					timer.cancel();//取消定时器
				}
			}
		}, 0, 100);

	}
	
}
