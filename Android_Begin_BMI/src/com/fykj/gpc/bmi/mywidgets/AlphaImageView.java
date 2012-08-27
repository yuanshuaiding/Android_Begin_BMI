package com.fykj.gpc.bmi.mywidgets;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.fykj.gpc.bmi.R;

public class AlphaImageView extends ImageView {

	// 图片透明度每次改变的大小
	private int alphaDelta = 0;
	// 当前的透明度
	private int curAlpha = 0;
	//渐变速度
	private int speed;

	public AlphaImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray typedArr = context.obtainStyledAttributes(attrs,
				R.styleable.AlphaImageViewAttrs);
		int duration = typedArr.getInteger(
				R.styleable.AlphaImageViewAttrs_duration, 0);
		speed = typedArr.getInteger(R.styleable.AlphaImageViewAttrs_speed,
				100);
		// 根据时长和速度就可以计算出图片每次改变的大小
		alphaDelta = 255 * speed / duration;
	}

	private Handler myHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			if (msg.what == 'a') {
				curAlpha += alphaDelta;
				if(curAlpha>255)
					curAlpha=255;
				AlphaImageView.this.setAlpha(curAlpha);
			}
		}
	};

	@Override
	protected void onDraw(Canvas canvas) {
		this.setAlpha(curAlpha);
		super.onDraw(canvas);
		final Timer timer=new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				Message msg=myHandler.obtainMessage('a');
				if(curAlpha>=255){
					timer.cancel();
				}else{
					msg.sendToTarget();
				}
			}
		}, 0, speed);
	}
	
	

}
