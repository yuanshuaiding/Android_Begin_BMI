package com.fykj.gpc.bmi;

import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class OriginalResTest extends Activity {

	private MediaPlayer mp1;
	private MediaPlayer mp2;
	private int currentIndex = 0;// ��¼ͼƬ������
	private AssetManager am;
	private String[] imgsArr;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.originalres);
		// ʹ��raw�ļ����µ���Դ��ֱ��ʹ��R�嵥���������
		mp1 = MediaPlayer.create(this, R.raw.lingwu);
		mp1.setOnCompletionListener(new OnCompletionListener() {

			@Override
			public void onCompletion(MediaPlayer mp) {
				mp1.release();
			}
		});
		// ʹ��assets�ļ����µ���Դ������AssetManager
		am = getAssets();
		AssetFileDescriptor afd;
		try {
			afd = am.openFd("lingwu.mp3");
			FileDescriptor fd = afd.getFileDescriptor();
			mp2 = new MediaPlayer();
			mp2.setDataSource(fd);
			mp2.setOnCompletionListener(new OnCompletionListener() {

				@Override
				public void onCompletion(MediaPlayer mp) {
					mp2.release();
				}
			});
			mp2.prepare();
		} catch (IOException e) {
			e.printStackTrace();
		}
		View play1 = findViewById(R.id.btn_play1);

		play1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mp1.start();
			}
		});

		View play2 = findViewById(R.id.btn_play2);
		play2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mp2.start();
			}
		});

		// �鿴assets�µ�ȫ��ͼƬ
		try {
			imgsArr = am.list("");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		View btn_nextImg = findViewById(R.id.btn_nextImg);
		final ImageView imgView = (ImageView) findViewById(R.id.img_asset);
		btn_nextImg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (currentIndex >= imgsArr.length) {
					currentIndex = 0;
				}
				// �ҵ���һ��ͼƬ�ļ�������λ��
				while (!imgsArr[currentIndex].endsWith((".png"))
						&& !imgsArr[currentIndex].endsWith((".jpg"))
						&& !imgsArr[currentIndex].endsWith((".gif"))) {
					currentIndex++;
					if(currentIndex>=imgsArr.length){
						currentIndex=0;
					}

				}

				// �жϸ��ļ��Ƿ�ΪͼƬ
				if (imgsArr[currentIndex].endsWith((".png"))
						|| imgsArr[currentIndex].endsWith((".jpg"))
						|| imgsArr[currentIndex].endsWith((".gif"))) {
					InputStream in = null;
					try {
						in = am.open(imgsArr[currentIndex]);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// �����ٵ�ǰ��ͼƬ��Դ���ͷ�ռ�õ��ڴ�
					BitmapDrawable bdm = (BitmapDrawable) imgView.getDrawable();
					if (bdm != null && !bdm.getBitmap().isRecycled()) {
						bdm.getBitmap().recycle();
					}
					// ��������ͼƬ
					imgView.setImageBitmap(BitmapFactory.decodeStream(in));
				}
				currentIndex++;
			}
		});

	}
}
