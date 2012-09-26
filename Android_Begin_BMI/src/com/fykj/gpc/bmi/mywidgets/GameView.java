package com.fykj.gpc.bmi.mywidgets;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

public class GameView extends View {
	// 游戏桌面的大小
	private int table_width;
	private int table_height;
	// 小球的半径
	private int radius = 12;
	// 球拍的尺寸
	private int racket_width = 70;
	private int racket_height = 20;
	Random rand = new Random();
	// 小球的起始坐标
	private int ballX = rand.nextInt(200) + 20;
	private int ballY = rand.nextInt(10) + 20;
	// 球拍的起始坐标
	private int racketX = rand.nextInt(200);
	private int racketY;
	// 获取一个-0.5~0.5的比率，用于控制小球横向移动的运行方向（左右）
	private double rate = rand.nextDouble() - 0.5;

	// 小球纵向移动速度
	private int ySpeed = 10;
	// 小球横向的移动速度
	private int xSpeed = (int) (ySpeed * rate * 2);
	// 游戏是否结束的标识
	private boolean flag = false;
	// 画笔
	private Paint paint;

	// 小球运动的时间控制器
	private Timer timer;

	// 更新界面的Handler
	private Handler myHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			if (msg.what == 'u') {
				invalidate();
			}
		}

	};

	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// 设置获取当前view的焦点，否则可能会无法正确获取按键事件
		setFocusable(true);
		// 确保rate不为0
		while (rate == 0) {
			rate = rand.nextDouble() - 0.5;
		}
		// 构造画笔
		paint = new Paint();
		paint.setStyle(Paint.Style.FILL);
		// 获取View的实际大小
		table_width = this.getWidth();
		table_height = this.getHeight();
		System.out.println("构造函数：width:" + table_width + " height:"
				+ table_height);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		// 获取View的实际大小
		table_width = this.getWidth();
		table_height = this.getHeight();
		// 球拍的垂直位置
		racketY = table_height - 40;
		System.out.println("onMeasure：width:" + table_width + " height:"
				+ table_height);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (flag) {
			paint.setColor(Color.RED);
			paint.setTextSize(30);
			canvas.drawText("游戏已经结束！", table_width / 2 - 100, table_height / 2,
					paint);
		} else {
			// 绘制小球
			paint.setColor(Color.parseColor("#FFDAB9"));
			canvas.drawCircle(ballX, ballY, radius, paint);
			// 绘制球拍
			paint.setColor(Color.parseColor("#EE6363"));
			canvas.drawRect(racketX, racketY, racketX + racket_width, racketY
					+ racket_height, paint);
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// 获取由哪个键触发的事件
		switch (event.getKeyCode()) {
		// 控制挡板左移
		case KeyEvent.KEYCODE_DPAD_LEFT:
			if (racketX > 0)
				racketX -= 5;
			break;
		// 控制挡板右移
		case KeyEvent.KEYCODE_DPAD_RIGHT:
			if (racketX < table_width - racket_width)
				racketX += 5;
			break;
		}
		// 通知planeView组件重绘
		invalidate();
		return true;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getX() < table_width / 2) {
			// 往左移动
			if (racketX > 0)
				racketX -= 5;
		}

		if (event.getX() >= table_width / 2) {
			// 往右移动
			if (racketX < table_width - racket_width)
				racketX += 5;
		}
		// 通知planeView组件重绘
		invalidate();
		return true;
	}

	/*
	 * 游戏开始函数
	 */
	public void GameStart() {
		if (timer != null) {
			timer.cancel();
		}
		flag = false;
		// 重置小球的起始坐标
		ballX = rand.nextInt(200) + 20;
		ballY = rand.nextInt(10) + 20;
		// 重置球拍的起始坐标
		racketX = rand.nextInt(200);
		// 开启定时器
		timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				// 如果小球碰到左边边框
				if (ballX - radius <= 0 || ballX >= table_width - radius) {
					xSpeed = -xSpeed;
				}
				// 如果小球高度超出了球拍位置，且横向不在球拍范围之内，游戏结束。
				if (ballY >= racketY - radius
						&& (ballX < racketX || ballX > racketX + racket_width)) {
					timer.cancel();
					// 设置游戏是否结束的旗标为true。
					flag = true;
				}
				// 如果小球位于球拍之内，且到达球拍位置，小球反弹
				else if (ballY <= 0
						|| (ballY >= racketY - radius && ballX > racketX && ballX <= racketX
								+ racket_width)) {
					ySpeed = -ySpeed;
				}
				// 小球座标增加
				ballY += ySpeed;
				ballX += xSpeed;
				// 发送消息，通知系统重绘组件
				myHandler.sendEmptyMessage('u');
			}
		}, 0, 50);
	}

	/*
	 * 游戏暂停函数
	 */
	public void GamePause() {
		if (timer != null)
			timer.cancel();
	}

	/*
	 * 游戏继续运行
	 */
	public void GameRestart() {
		timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				// 如果小球碰到左边边框
				if (ballX - radius <= 0 || ballX >= table_width - radius) {
					xSpeed = -xSpeed;
				}
				// 如果小球高度超出了球拍位置，且横向不在球拍范围之内，游戏结束。
				if (ballY >= racketY - radius
						&& (ballX < racketX || ballX > racketX + racket_width)) {
					timer.cancel();
					// 设置游戏是否结束的旗标为true。
					flag = true;
				}
				// 如果小球位于球拍之内，且到达球拍位置，小球反弹
				else if (ballY <= 0
						|| (ballY >= racketY - radius && ballX > racketX && ballX <= racketX
								+ racket_width)) {
					ySpeed = -ySpeed;
				}
				// 小球座标增加
				ballY += ySpeed;
				ballX += xSpeed;
				// 发送消息，通知系统重绘组件
				myHandler.sendEmptyMessage('u');
			}
		}, 0, 50);
	}

}
