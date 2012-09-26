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
	// ��Ϸ����Ĵ�С
	private int table_width;
	private int table_height;
	// С��İ뾶
	private int radius = 12;
	// ���ĵĳߴ�
	private int racket_width = 70;
	private int racket_height = 20;
	Random rand = new Random();
	// С�����ʼ����
	private int ballX = rand.nextInt(200) + 20;
	private int ballY = rand.nextInt(10) + 20;
	// ���ĵ���ʼ����
	private int racketX = rand.nextInt(200);
	private int racketY;
	// ��ȡһ��-0.5~0.5�ı��ʣ����ڿ���С������ƶ������з������ң�
	private double rate = rand.nextDouble() - 0.5;

	// С�������ƶ��ٶ�
	private int ySpeed = 10;
	// С�������ƶ��ٶ�
	private int xSpeed = (int) (ySpeed * rate * 2);
	// ��Ϸ�Ƿ�����ı�ʶ
	private boolean flag = false;
	// ����
	private Paint paint;

	// С���˶���ʱ�������
	private Timer timer;

	// ���½����Handler
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
		// ���û�ȡ��ǰview�Ľ��㣬������ܻ��޷���ȷ��ȡ�����¼�
		setFocusable(true);
		// ȷ��rate��Ϊ0
		while (rate == 0) {
			rate = rand.nextDouble() - 0.5;
		}
		// ���컭��
		paint = new Paint();
		paint.setStyle(Paint.Style.FILL);
		// ��ȡView��ʵ�ʴ�С
		table_width = this.getWidth();
		table_height = this.getHeight();
		System.out.println("���캯����width:" + table_width + " height:"
				+ table_height);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		// ��ȡView��ʵ�ʴ�С
		table_width = this.getWidth();
		table_height = this.getHeight();
		// ���ĵĴ�ֱλ��
		racketY = table_height - 40;
		System.out.println("onMeasure��width:" + table_width + " height:"
				+ table_height);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (flag) {
			paint.setColor(Color.RED);
			paint.setTextSize(30);
			canvas.drawText("��Ϸ�Ѿ�������", table_width / 2 - 100, table_height / 2,
					paint);
		} else {
			// ����С��
			paint.setColor(Color.parseColor("#FFDAB9"));
			canvas.drawCircle(ballX, ballY, radius, paint);
			// ��������
			paint.setColor(Color.parseColor("#EE6363"));
			canvas.drawRect(racketX, racketY, racketX + racket_width, racketY
					+ racket_height, paint);
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// ��ȡ���ĸ����������¼�
		switch (event.getKeyCode()) {
		// ���Ƶ�������
		case KeyEvent.KEYCODE_DPAD_LEFT:
			if (racketX > 0)
				racketX -= 5;
			break;
		// ���Ƶ�������
		case KeyEvent.KEYCODE_DPAD_RIGHT:
			if (racketX < table_width - racket_width)
				racketX += 5;
			break;
		}
		// ֪ͨplaneView����ػ�
		invalidate();
		return true;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getX() < table_width / 2) {
			// �����ƶ�
			if (racketX > 0)
				racketX -= 5;
		}

		if (event.getX() >= table_width / 2) {
			// �����ƶ�
			if (racketX < table_width - racket_width)
				racketX += 5;
		}
		// ֪ͨplaneView����ػ�
		invalidate();
		return true;
	}

	/*
	 * ��Ϸ��ʼ����
	 */
	public void GameStart() {
		if (timer != null) {
			timer.cancel();
		}
		flag = false;
		// ����С�����ʼ����
		ballX = rand.nextInt(200) + 20;
		ballY = rand.nextInt(10) + 20;
		// �������ĵ���ʼ����
		racketX = rand.nextInt(200);
		// ������ʱ��
		timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				// ���С��������߱߿�
				if (ballX - radius <= 0 || ballX >= table_width - radius) {
					xSpeed = -xSpeed;
				}
				// ���С��߶ȳ���������λ�ã��Һ��������ķ�Χ֮�ڣ���Ϸ������
				if (ballY >= racketY - radius
						&& (ballX < racketX || ballX > racketX + racket_width)) {
					timer.cancel();
					// ������Ϸ�Ƿ���������Ϊtrue��
					flag = true;
				}
				// ���С��λ������֮�ڣ��ҵ�������λ�ã�С�򷴵�
				else if (ballY <= 0
						|| (ballY >= racketY - radius && ballX > racketX && ballX <= racketX
								+ racket_width)) {
					ySpeed = -ySpeed;
				}
				// С����������
				ballY += ySpeed;
				ballX += xSpeed;
				// ������Ϣ��֪ͨϵͳ�ػ����
				myHandler.sendEmptyMessage('u');
			}
		}, 0, 50);
	}

	/*
	 * ��Ϸ��ͣ����
	 */
	public void GamePause() {
		if (timer != null)
			timer.cancel();
	}

	/*
	 * ��Ϸ��������
	 */
	public void GameRestart() {
		timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				// ���С��������߱߿�
				if (ballX - radius <= 0 || ballX >= table_width - radius) {
					xSpeed = -xSpeed;
				}
				// ���С��߶ȳ���������λ�ã��Һ��������ķ�Χ֮�ڣ���Ϸ������
				if (ballY >= racketY - radius
						&& (ballX < racketX || ballX > racketX + racket_width)) {
					timer.cancel();
					// ������Ϸ�Ƿ���������Ϊtrue��
					flag = true;
				}
				// ���С��λ������֮�ڣ��ҵ�������λ�ã�С�򷴵�
				else if (ballY <= 0
						|| (ballY >= racketY - radius && ballX > racketX && ballX <= racketX
								+ racket_width)) {
					ySpeed = -ySpeed;
				}
				// С����������
				ballY += ySpeed;
				ballX += xSpeed;
				// ������Ϣ��֪ͨϵͳ�ػ����
				myHandler.sendEmptyMessage('u');
			}
		}, 0, 50);
	}

}
