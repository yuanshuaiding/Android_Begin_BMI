package com.fykj.gpc.bmi;

import com.fykj.gpc.bmi.mywidgets.ShaderView;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.PorterDuff.Mode;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class ShaderTest extends Activity implements OnClickListener{

	private View btn_bitmapshader;
	private View btn_linearshader;
	private View btn_radialshader;
	private View btn_sweepshader;
	private View btn_compossshader;
	private ShaderView shaderView;
	private Bitmap bitmap;
	private BitmapShader bitmapShader;
	private LinearGradient linearGradient;
	private RadialGradient radialGradient;
	private SweepGradient sweepGradient;
	private ComposeShader composeGradient;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shader);
		shaderView=(ShaderView)findViewById(R.id.shaderView1);
		btn_bitmapshader=findViewById(R.id.button4);
		btn_linearshader=findViewById(R.id.button2);
		btn_radialshader=findViewById(R.id.button1);
		btn_sweepshader=findViewById(R.id.button3);
		btn_compossshader=findViewById(R.id.button5);
		btn_bitmapshader.setOnClickListener(this);
		btn_linearshader.setOnClickListener(this);
		btn_radialshader.setOnClickListener(this);
		btn_sweepshader.setOnClickListener(this);
		btn_compossshader.setOnClickListener(this);
		bitmap=((BitmapDrawable)getResources().getDrawable(R.drawable.xiyy)).getBitmap();
		bitmapShader=new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.MIRROR);
		int[] colors=new int[]{Color.RED,Color.GREEN,Color.BLUE};
		linearGradient=new LinearGradient(0, 0, 100, 100, colors, null, Shader.TileMode.REPEAT);
		radialGradient=new RadialGradient(100, 100, 80, colors, null, Shader.TileMode.REPEAT);
		sweepGradient=new SweepGradient(150, 150, colors, null);
		composeGradient=new ComposeShader(bitmapShader, linearGradient, Mode.DARKEN);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.button4:
			shaderView.getPaint().setShader(bitmapShader);
			shaderView.invalidate();
			break;
		case R.id.button2:
			shaderView.getPaint().setShader(linearGradient);
			shaderView.invalidate();
			break;
		case R.id.button1:
			shaderView.getPaint().setShader(radialGradient);
			shaderView.invalidate();
			break;
		case R.id.button3:
			shaderView.getPaint().setShader(sweepGradient);
			shaderView.invalidate();
			break;
		case R.id.button5:
			shaderView.getPaint().setShader(composeGradient);
			shaderView.invalidate();
			break;
		}
	}
	
	
}
