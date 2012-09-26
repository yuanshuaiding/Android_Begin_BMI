package com.fykj.gpc.bmi.mywidgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class ShaderView extends View {
	private Paint paint;
	public Paint getPaint() {
		return paint;
	}
	public void setPaint(Paint paint) {
		this.paint = paint;
	}
	public ShaderView(Context context, AttributeSet attrs) {
		super(context, attrs);
		paint=new Paint();
		paint.setColor(Color.CYAN);
	}
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
	}
}
