package com.fykj.gpc.bmi;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;

public class ProgressBarTest extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//���󴰿���ɫ����������óɲ���ȷ�Ľ��ȷ��
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.progressbar);
		//���ñ������еĲ���ȷ�Ľ������Ƿ������ʾ
		setProgressBarIndeterminateVisibility(true);
		showDialog(0);
		//setProgressBarIndeterminateVisibility(false);
		Button showBtn=(Button)findViewById(R.id.btn_showPro);
		Button hideBtn=(Button)findViewById(R.id.btn_hidePro);
		Button addBtn=(Button)findViewById(R.id.btn_addPro);
		Button cutBtn=(Button)findViewById(R.id.btn_cutPro);
		final ProgressBar progBar=(ProgressBar) findViewById(R.id.increm_prog);
		showBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setProgressBarIndeterminateVisibility(true);
			}
		});
		hideBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setProgressBarIndeterminateVisibility(false);
			}
		});
		addBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				progBar.incrementProgressBy(1);
			}
		});
		
		cutBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				progBar.incrementProgressBy(-1);
			}
		});
		
		/*
		 // Start lengthy operation in a background thread
        new Thread(new Runnable() {
            private int mProgressStatus=0;
			private View mHandler;

			public void run() {
                while (mProgressStatus < 100) {
                    mProgressStatus++;

                    // Update the progress bar
                    mHandler.post(new Runnable() {
                        public void run() {
                        	progBar.setProgress(mProgressStatus);
                        }
                    });
                }
            }
        }).start();
        */

	}


	@Override
	protected Dialog onCreateDialog(int id) {
		ProgressDialog progdialog=new ProgressDialog(this);
		progdialog.setTitle("������");
		progdialog.setMessage("���ڼ��أ����Ժ�..."); 
		progdialog.setIndeterminate(true);
		progdialog.setMax(3);
		progdialog.setIcon(getResources().getDrawable(R.drawable.icon));
		progdialog.setCancelable(true);
		return progdialog;
	}
	
	
	
}
