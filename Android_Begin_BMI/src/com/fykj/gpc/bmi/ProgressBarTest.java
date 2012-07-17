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
	// Ҫ�����ļ�������·��
	public String fileUrl = "http://downmini.kugou.com/Kugou2012.exe";
	// �ļ����غ�ı���λ��
	public String fileSavePaht = Environment.getExternalStorageDirectory()
			+ "/BMI_downfile.apk";
	private int total;// �ļ��ܴ�С
	private int saved;// �����ش�С
	private MyProgressBarDialog progdialog;// �Զ���������Ի���
	protected ProgressDialog dialog_downImg;// �ڶ����첽���ط�ʽ�Ľ��ȶԻ���
	private ImageView img_fromnet;//���ڼ�������ͼƬ��ImageView

	/**
	 * Handler�ǹ�ͨActivity ��Thread/runnable��������
	 * ��Handler����������UI�߳��еģ��������߳̿���ͨ��Message��������������
	 */
	private Handler myHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// �жϵ�ǰ���߳��Ƿ��ж�
			switch (msg.what) {
			case 0:
				progdialog.setProgBarMax(total);
				break;
			case 1:
				progdialog.setProgBarProgress(saved);
				break;
			case 2:
				// ���ضԻ�����Ľ�����
				dismissDialog(1);
				// ��ʾ��Ϣ
				Toast.makeText(ProgressBarTest.this, "�ļ��������", 1).show();
				break;
			}
			super.handleMessage(msg);
		}
	};

	// ����Handler������Message
	private void sendMsg(int flag) {
		Message msg = new Message();
		msg.what = flag;
		myHandler.sendMessage(msg);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ���󴰿���ɫ����������óɲ���ȷ�Ľ��ȷ��
		// requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.progressbar);
		// ���ñ������еĲ���ȷ�Ľ������Ƿ������ʾ
		// setProgressBarIndeterminateVisibility(true);
		// setProgressBarIndeterminateVisibility(false);
		// ��ʾ�����Ľ������Ի���
		// showDialog(0);
		// ��ȡ��ͼ�ؼ�
		Button showBtn = (Button) findViewById(R.id.btn_showPro);
		Button hideBtn = (Button) findViewById(R.id.btn_hidePro);
		Button addBtn = (Button) findViewById(R.id.btn_addPro);
		Button cutBtn = (Button) findViewById(R.id.btn_cutPro);
		Button btn_downfile1 = (Button) findViewById(R.id.btn_downfile1);
		Button btn_downfile2 = (Button) findViewById(R.id.btn_downfile2);
		img_fromnet=(ImageView)findViewById(R.id.img_fromnet);
		final ProgressBar progBar = (ProgressBar) findViewById(R.id.increm_prog);
		// ���ؼ��󶨵��������
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
				// ��ʾ���ضԻ���
				showDialog(1);
				// ���������߳�
				if (!DownFileThread1.isAlive())
					DownFileThread1.start();
			}
		});

		btn_downfile2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//ͼƬ�������ַ
				String urlStr="http://pic4.nipic.com/20091123/3789763_143553026583_2.jpg";
				//ִ���첽����
				new DownFileThread2().execute(urlStr);
			}
		});

	}

	@Override
	protected Dialog onCreateDialog(int id) {

		switch (id) {
		case 0:
			ProgressDialog progdialog_normal = new ProgressDialog(this);
			progdialog_normal.setTitle("������");
			progdialog_normal.setMessage("���ڼ��أ����Ժ�...");
			progdialog_normal.setIndeterminate(true);
			progdialog_normal.setMax(100);
			progdialog_normal.setIcon(getResources().getDrawable(
					R.drawable.icon));
			progdialog_normal.setCancelable(true);
			return progdialog_normal;
		case 1:
			//ʹ��onCreateDialog()��ʽ�������Զ���Ľ��ȶԻ��򲻻���õ�MyProgressBarDialog��onCreate������Ӧ���ڹ��캯������ɽ����ʼ������
			progdialog = new MyProgressBarDialog(ProgressBarTest.this);
			progdialog.setTitle("���ؽ�����");
			progdialog.setMessage("���������ļ������Ժ�...");
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
			// ������������
			try {
				URL url = new URL(fileUrl);
				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();
				conn.setDoInput(true);
				conn.setConnectTimeout(5 * 1000);
				conn.connect();
				InputStream inputStream = conn.getInputStream();
				// ��ȡ�����ļ������ֽڴ�С
				total = conn.getContentLength();
				System.out.println("total size:" + total);
				sendMsg(0);
				// �Ѿ����ص��ֽڴ�С
				saved = 0;
				int i = -1;
				File file = new File(fileSavePaht);
				if (file.exists())
					file.delete();
				file.createNewFile();
				FileOutputStream os = new FileOutputStream(file);
				byte[] tempbytes = new byte[1024];
				// ���ڷ��͵�Message
				while ((i = inputStream.read(tempbytes)) != -1) {
					os.write(tempbytes, 0, i);
					saved += i;
					// ���͸��Ľ�������Message
					sendMsg(1);
				}
				// �������ضԻ����Message
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
			dialog_downImg = ProgressDialog.show(ProgressBarTest.this, "�첽��������ͼƬ",
					"�������ݣ����Ե� ��", true, true);
			
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
