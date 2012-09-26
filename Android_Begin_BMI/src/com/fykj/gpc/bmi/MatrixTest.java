package com.fykj.gpc.bmi;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class MatrixTest extends Activity {

	private Bitmap bitmap;
	// Matrix实例
	private Matrix matrix = new Matrix();
	// 倾斜角度
	private float sx = 0.0f;
	// 缩放比例
	private float scale = 1.0f;
	// 判断缩放还是旋转
	private boolean isScale = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.matrix);

		final ImageView imgView = (ImageView) findViewById(R.id.img_matrix);

		View btn_scale1 = findViewById(R.id.btn_scale1);
		View btn_scale2 = findViewById(R.id.btn_scale2);
		View btn_goleft = findViewById(R.id.btn_goleft);
		View btn_goright = findViewById(R.id.btn_goright);
		btn_scale1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 设置ImageView的缩放类型，否则调用setImageMatrix方法时会无效
				imgView.setScaleType(ScaleType.MATRIX);
				if (scale < 2.0)
					scale += 0.1;
				matrix.reset();
				matrix.setScale(scale, scale, imgView.getWidth() / 2,
						imgView.getHeight() / 2);
				imgView.setImageMatrix(matrix);
				System.out.println(matrix.toString());
			}
		});
		btn_scale2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 设置ImageView的缩放类型，否则调用setImageMatrix方法时会无效
				imgView.setScaleType(ScaleType.MATRIX);
				if (scale > 0.2)
					scale -= 0.1;
				matrix.reset();
				matrix.setScale(scale, scale, imgView.getWidth() / 2,
						imgView.getHeight() / 2);
				imgView.setImageMatrix(matrix);
				System.out.println(matrix.toString());
			}
		});
		btn_goleft.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 设置ImageView的缩放类型，否则调用setImageMatrix方法时会无效
				imgView.setScaleType(ScaleType.MATRIX);
				matrix.reset();
				sx -= 0.1;
				matrix.setSkew(sx, 0);
				imgView.setImageMatrix(matrix);
				System.out.println(matrix.toShortString());
			}
		});
		btn_goright.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 设置ImageView的缩放类型，否则调用setImageMatrix方法时会无效
				imgView.setScaleType(ScaleType.MATRIX);
				matrix.reset();
				sx += 0.1;
				matrix.setSkew(sx, 0);
				imgView.setImageMatrix(matrix);
				System.out.println(matrix.toShortString());
			}
		});
	}

}
