package com.fykj.gpc.bmi;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.fykj.gpc.bmi.mywidgets.GameView;

public class CanvasActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.canvastest);
		final GameView game = (GameView) findViewById(R.id.gameView);
		View pause = findViewById(R.id.btn_pause);
		pause.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (v.getTag().equals("Pause")) {
					game.GamePause();
					((Button) v).setText("Restart");
					v.setTag("restart");
				} else {
					game.GameRestart();
					((Button) v).setText("Pause");
					v.setTag("Pause");
				}
			}
		});

		View start = findViewById(R.id.btn_start);
		start.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				game.GameStart();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.canvas_menu, menu);
		return true;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
			finish();
			return true;
		} else {
			return false;
		}
	}

}
