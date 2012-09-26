package com.fykj.gpc.bmi;

import com.fykj.gpc.bmi.mywidgets.StarWarGameView;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

public class MoveView extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		StarWarGameView gameView = new StarWarGameView(getApplicationContext());
		ViewGroup.LayoutParams params = new LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT);
		gameView.setLayoutParams(params);
		setContentView(gameView);
	}

}
