package com.fykj.gpc.bmi.mywidgets;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.fykj.gpc.bmi.R;
/**
 * 自定义的一个进度对话框
 * @author dingys
 *
 */
public class MyProgressBarDialog extends AlertDialog {

	private ProgressBar mProgress;
	private TextView mProgressNumber;
	private TextView mProgressPercent;
	// public static final int M = 1024 * 1024;
	// public static final int K = 1024;
	private int promax;
	private int progress;
	// private static final NumberFormat nf = NumberFormat.getPercentInstance();
	// private static final DecimalFormat df = new DecimalFormat("###.##");
	private Handler mViewUpdateHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			mProgress.setProgress(progress);
			mProgressNumber.setText(progress + "/" + promax);
			mProgressPercent.setText(progress * 100 / promax + "%");
		}
	};

	public MyProgressBarDialog(Context context) {
		super(context);

		LayoutInflater inflater = LayoutInflater.from(getContext());
		// 获取要显示的视图
		ViewGroup view = (ViewGroup) inflater.inflate(
				R.layout.alert_dialog_progress, null);
		// 获取视图组件
		mProgress = (ProgressBar) view.findViewById(R.id.progress);
		mProgressNumber = (TextView) view.findViewById(R.id.progress_number);
		mProgressPercent = (TextView) view.findViewById(R.id.progress_percent);
		this.setView(view);
		// this.setContentView(view);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	private void onProgressChanged() {
		mViewUpdateHandler.sendEmptyMessage(0);
	}

	public void setProgBarMax(int max) {
		promax = max;
		mProgress.setMax(promax);
		onProgressChanged();
	}

	public void setProgBarProgress(int progress) {
		this.progress = progress;
		onProgressChanged();
	}

	@Override
	public void onStart() {
		super.onStart();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

}
