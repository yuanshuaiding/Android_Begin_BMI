package com.fykj.gpc.bmi.mywidgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class DrawView extends View {

	private float cx=50;
	private float cy=40;

	public DrawView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		//创建画笔
		Paint pen=new Paint();
		
		pen.setColor(Color.CYAN);
		pen.setAlpha(200);
		
		canvas.drawCircle(cx, cy, 20, pen);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		this.cx=event.getX();
		this.cy=event.getY();
		this.invalidate();
		//表示该事件已经被处理，不会继续传播
		return true;
	}
	
}
