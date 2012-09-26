package com.fykj.gpc.bmi;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class FrameAnimation extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frameanim);
		ImageView img = (ImageView) findViewById(R.id.img_frameanim);
		//获取img的Drawable对象并强制转换为AnimationDrawable对象
		final AnimationDrawable anim = (AnimationDrawable) img.getDrawable();
		img.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (anim.isRunning()) {
					anim.stop();
				}else{
					anim.start();
				}
			}
		});
	}

}
