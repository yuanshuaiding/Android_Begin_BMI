package com.fykj.gpc.bmi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.fykj.gpc.bmi.mywidgets.MyProgressBarDialog;

public class ProgressBarTest extends Activity {
	// 要下载文件的网络路径
	public String fileUrl = "http://downmini.kugou.com/Kugou2012.exe";
	// 文件下载后的保存位置
	public String fileSavePaht = Environment.getExternalStorageDirectory()
			+ "/BMI_downfile.apk";
	private int total;// 文件总大小
	private int saved;// 已下载大小
	private MyProgressBarDialog progdialog;// 自定义进度条对话框
	protected ProgressDialog dialog_downImg;// 第二种异步加载方式的进度对话框
	private ImageView img_fromnet;//用于加载网络图片的ImageView

	/**
	 * Handler是沟通Activity 与Thread/runnable的桥梁。
	 * 而Handler是运行在主UI线程中的，它与子线程可以通过Message对象来传递数据
	 */
	private Handler myHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// 判断当前主线程是否被中断
			switch (msg.what) {
			case 0:
				progdialog.setProgBarMax(total);
				break;
			case 1:
				progdialog.setProgBarProgress(saved);
				break;
			case 2:
				// 隐藏对话框风格的进度条
				dismissDialog(1);
				// 提示信息
				Toast.makeText(ProgressBarTest.this, "文件下载完成", 1).show();
				break;
			}
			super.handleMessage(msg);
		}
	};

	// 调用Handler对象发送Message
	private void sendMsg(int flag) {
		Message msg = new Message();
		msg.what = flag;
		myHandler.sendMessage(msg);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 请求窗口特色风格，这里设置成不明确的进度风格
		// requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.progressbar);
		// 设置标题栏中的不明确的进度条是否可以显示
		// setProgressBarIndeterminateVisibility(true);
		// setProgressBarIndeterminateVisibility(false);
		// 显示弹出的进度条对话框
		// showDialog(0);
		// 获取视图控件
		Button showBtn = (Button) findViewById(R.id.btn_showPro);
		Button hideBtn = (Button) findViewById(R.id.btn_hidePro);
		Button addBtn = (Button) findViewById(R.id.btn_addPro);
		Button cutBtn = (Button) findViewById(R.id.btn_cutPro);
		Button btn_downfile1 = (Button) findViewById(R.id.btn_downfile1);
		Button btn_downfile2 = (Button) findViewById(R.id.btn_downfile2);
		img_fromnet=(ImageView)findViewById(R.id.img_fromnet);
		final ProgressBar progBar = (ProgressBar) findViewById(R.id.increm_prog);
		// 给控件绑定点击监听器
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
		btn_downfile1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 显示下载对话框
				showDialog(1);
				// 开启下载线程
				if (!DownFileThread1.isAlive())
					DownFileThread1.start();
			}
		});

		btn_downfile2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//图片的网络地址
				String urlStr="http://pic4.nipic.com/20091123/3789763_143553026583_2.jpg";
				//执行异步任务
				new DownFileThread2().execute(urlStr);
			}
		});

	}

	@Override
	protected Dialog onCreateDialog(int id) {

		switch (id) {
		case 0:
			ProgressDialog progdialog_normal = new ProgressDialog(this);
			progdialog_normal.setTitle("进度条");
			progdialog_normal.setMessage("正在加载，请稍候...");
			progdialog_normal.setIndeterminate(true);
			progdialog_normal.setMax(100);
			progdialog_normal.setIcon(getResources().getDrawable(
					R.drawable.icon));
			progdialog_normal.setCancelable(true);
			return progdialog_normal;
		case 1:
			//使用onCreateDialog()方式来创建自定义的进度对话框不会调用到MyProgressBarDialog的onCreate方法，应该在构造函数中完成界面初始化工作
			progdialog = new MyProgressBarDialog(ProgressBarTest.this);
			progdialog.setTitle("下载进度条");
			progdialog.setMessage("正在下载文件，请稍候...");
			progdialog.setIcon(getResources().getDrawable(R.drawable.icon));
			progdialog.setProgBarMax(100);
			progdialog.setCancelable(true);
			return progdialog;
		}
		return null;
	}

	private Thread DownFileThread1 = new Thread() {

		@Override
		public void run() {
			// 这里下载数据
			try {
				URL url = new URL(fileUrl);
				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();
				conn.setDoInput(true);
				conn.setConnectTimeout(5 * 1000);
				conn.connect();
				InputStream inputStream = conn.getInputStream();
				// 获取下载文件的总字节大小
				total = conn.getContentLength();
				System.out.println("total size:" + total);
				sendMsg(0);
				// 已经下载的字节大小
				saved = 0;
				int i = -1;
				File file = new File(fileSavePaht);
				if (file.exists())
					file.delete();
				file.createNewFile();
				FileOutputStream os = new FileOutputStream(file);
				byte[] tempbytes = new byte[1024];
				// 用于发送的Message
				while ((i = inputStream.read(tempbytes)) != -1) {
					os.write(tempbytes, 0, i);
					saved += i;
					// 发送更改进度条的Message
					sendMsg(1);
				}
				// 发送隐藏对话框的Message
				sendMsg(2);
				os.flush();
				os.close();
				inputStream.close();

			} catch (MalformedURLException e1) {
				e1.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};

	private class DownFileThread2 extends AsyncTask<String, Integer, Bitmap> {
		

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog_downImg = ProgressDialog.show(ProgressBarTest.this, "异步加载网络图片",
					"下载数据，请稍等 …", true, true);
			
		}

		@Override
		protected Bitmap doInBackground(String... params) {
			Bitmap bitmap = null;
			try {

				URL url = new URL(params[0]);
				HttpURLConnection con = (HttpURLConnection) url
						.openConnection();
				con.setDoInput(true);
				con.connect();
				InputStream inputStream = con.getInputStream();

				bitmap = BitmapFactory.decodeStream(inputStream);
				inputStream.close();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return bitmap;
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			super.onPostExecute(result);
			dialog_downImg.dismiss();
			img_fromnet.setImageBitmap(result);
		}
		
		

	}

}
