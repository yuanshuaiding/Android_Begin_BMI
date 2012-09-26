package com.fykj.gpc.bmi.mywidgets;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

public class MeshImageView extends ImageView {
	private Bitmap bitmap;// ԭʼͼƬ
	private final int WIDTH = 20;// ����ֳ�20��
	private final int HEIGHT = 20;// ����ֳ�20��
	private final int COUNT = (WIDTH + 1) * (HEIGHT + 1);// һ�����γ�21*21������
	private float[] verts = new float[COUNT * 2];// ��¼�ָ��Ķ�������
	private float[] origs = new float[COUNT * 2];// ��¼δŤ��ǰ��ԭʼ��������
	private boolean isTouched=false;

	public MeshImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		bitmap = ((BitmapDrawable) getDrawable()).getBitmap();
		if (bitmap != null && bitmap.getWidth() > 0 && bitmap.getHeight() > 0) {
			// ��ȡͼƬ�Ŀ��
			int bitmapWidth = bitmap.getWidth();
			int bitmapHeight = bitmap.getHeight();
			// ���ݷָ�ĳ��������verts����
			int index = 0;
			for (int y = 0; y <= HEIGHT; y++) {
				float fy = bitmapHeight * y / HEIGHT;
				for (int x = 0; x <= WIDTH; x++) {
					float fx = bitmapWidth * x / WIDTH;
					/*
					 * ��ʼ��orig��verts���顣 ��ʼ����orig��verts����������ȵر�����21 * 21�����x,y����
					 */
					origs[index * 2 + 0] = verts[index * 2 + 0] = fx;
					origs[index * 2 + 1] = verts[index * 2 + 1] = fy;
					index += 1;
				}
			}
		} else {
			// ˵��ͼƬ������
			bitmap = null;
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {
		if (isTouched) {
			if (bitmap != null && bitmap.getWidth() > 0
					&& bitmap.getHeight() > 0){
				Bitmap temp=Bitmap.createBitmap(getWidth(), getHeight(), Config.ARGB_8888);
				canvas.setBitmap(temp);
				//canvas.setMatrix(getImageMatrix());
				canvas.drawBitmapMesh(bitmap, WIDTH, HEIGHT, verts, 0, null, 0,
						null);
				this.setImageBitmap(temp);
			}
		} else
			super.onDraw(canvas);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		isTouched = true;
		mesh(event.getX(), event.getY());
		return super.onTouchEvent(event);
	}

	// ���ݴ���������verts����ķ���
	private void mesh(float cx, float cy) {
		for (int i = 0; i < COUNT * 2; i += 2) {
			float dx = cx - origs[i + 0];
			float dy = cy - origs[i + 1];
			float dd = dx * dx + dy * dy;
			// ����ÿ�����㵽������(cx��cy)�ľ���
			float d = (float) Math.sqrt(dd);
			// ����Ť���ȣ����봥����(cx��cy)�ľ���ԽԶ��Ť����ԽС
			float pull = 8000 / (float) (dd * d);
			if (pull >= 1) {
				verts[i + 0] = cx;
				verts[i + 1] = cy;
			} else {
				// ���Ƹ�����������(cx��cy)����ƫ��
				verts[i + 0] = origs[i + 0] + dx * pull;
				verts[i + 1] = origs[i + 1] + dy * pull;
			}
		}
		// ֪ͨView�ػ�
		invalidate();
	}
}
