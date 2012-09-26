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
	private Bitmap bitmap;// 原始图片
	private final int WIDTH = 20;// 横向分成20格
	private final int HEIGHT = 20;// 纵向分成20格
	private final int COUNT = (WIDTH + 1) * (HEIGHT + 1);// 一共会形成21*21个顶点
	private float[] verts = new float[COUNT * 2];// 记录分割后的顶点坐标
	private float[] origs = new float[COUNT * 2];// 记录未扭曲前的原始顶点坐标
	private boolean isTouched=false;

	public MeshImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		bitmap = ((BitmapDrawable) getDrawable()).getBitmap();
		if (bitmap != null && bitmap.getWidth() > 0 && bitmap.getHeight() > 0) {
			// 获取图片的宽高
			int bitmapWidth = bitmap.getWidth();
			int bitmapHeight = bitmap.getHeight();
			// 根据分格的常量构造出verts数组
			int index = 0;
			for (int y = 0; y <= HEIGHT; y++) {
				float fy = bitmapHeight * y / HEIGHT;
				for (int x = 0; x <= WIDTH; x++) {
					float fx = bitmapWidth * x / WIDTH;
					/*
					 * 初始化orig、verts数组。 初始化后，orig、verts两个数组均匀地保存了21 * 21个点的x,y座标
					 */
					origs[index * 2 + 0] = verts[index * 2 + 0] = fx;
					origs[index * 2 + 1] = verts[index * 2 + 1] = fy;
					index += 1;
				}
			}
		} else {
			// 说明图片不可用
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

	// 根据触摸点重置verts数组的方法
	private void mesh(float cx, float cy) {
		for (int i = 0; i < COUNT * 2; i += 2) {
			float dx = cx - origs[i + 0];
			float dy = cy - origs[i + 1];
			float dd = dx * dx + dy * dy;
			// 计算每个顶点到触摸点(cx，cy)的距离
			float d = (float) Math.sqrt(dd);
			// 计算扭曲度，距离触摸点(cx，cy)的距离越远，扭曲度越小
			float pull = 8000 / (float) (dd * d);
			if (pull >= 1) {
				verts[i + 0] = cx;
				verts[i + 1] = cy;
			} else {
				// 控制各顶点向触摸点(cx，cy)发生偏移
				verts[i + 0] = origs[i + 0] + dx * pull;
				verts[i + 1] = origs[i + 1] + dy * pull;
			}
		}
		// 通知View重绘
		invalidate();
	}
}
