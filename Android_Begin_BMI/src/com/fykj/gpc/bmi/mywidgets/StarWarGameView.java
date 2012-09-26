package com.fykj.gpc.bmi.mywidgets;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.fykj.gpc.bmi.R;

public class StarWarGameView extends View {

	private int BACK_HEIGHT;// 游戏背景图的原始高度
	private int BACK_WIDTH;// 游戏背景图的原始宽度
	private int viewHeight;// View实际高度
	private int viewWidth;// View实际宽度
	private int startY;// 开始挖取的y坐标
	private Bitmap back;// 背景图片
	private Bitmap plane;// 飞船图片

	public StarWarGameView(Context context) {
		super(context);
		back = BitmapFactory
				.decodeResource(getResources(), R.drawable.back_img);
		BACK_HEIGHT=back.getHeight();
		BACK_WIDTH=back.getWidth();
		plane = BitmapFactory.decodeResource(getResources(), R.drawable.plane);
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				viewHandler.sendEmptyMessage('u');
			}
		}, 0, 50);
	}

	private Handler viewHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if (msg.what == 'u') {
				// 设置新的挖取范围...
				startY -= 3;
				if (startY <= 0) {
					// 重新开始挖取
					startY = BACK_HEIGHT - viewHeight;
				} 
				System.out.println("startY:"+startY);
				invalidate();
			}
		}

	};

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		System.out.println("widthMeasureSpec:" + widthMeasureSpec
				+ ";heightMeasureSpec:" + heightMeasureSpec);
		viewHeight = getHeight();
		viewWidth = getWidth();
		startY = BACK_HEIGHT - viewHeight;
		System.out
				.println("cutHeight:" + viewHeight + ";viewWidth:" + viewWidth);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		//Bitmap temp = Bitmap.createBitmap(back, 0, startY, BACK_WIDTH,cutHeight);
		Matrix matrix=new Matrix();
		matrix.setScale(((float)viewWidth/BACK_WIDTH),1);
		System.out.println("matrix:"+matrix.toShortString());
		Bitmap temp=Bitmap.createBitmap(back, 0, startY, BACK_WIDTH,
				viewHeight);
		//canvas.drawBitmap(temp, 0, 0, null);
		canvas.drawBitmap(temp, matrix, null);
		canvas.drawBitmap(plane, viewWidth / 2-30, viewHeight-50, null);
	}
}
