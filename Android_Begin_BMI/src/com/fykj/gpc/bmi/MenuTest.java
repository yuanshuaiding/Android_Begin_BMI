package com.fykj.gpc.bmi;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MenuTest extends Activity {
	private static final int ITEM1 = Menu.FIRST;
	private static final int ITEM2 = Menu.FIRST+1;
	private static final int ITEM3 = Menu.FIRST+2;
	private MenuInflater mif;
	private TextView mtxt;
	private AlertDialog.Builder builder;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_test);
		getWindow().setBackgroundDrawableResource(android.R.drawable.screen_background_light_transparent);
		mif=getMenuInflater();
		mtxt=(TextView) findViewById(R.id.txt_menu);
		this.registerForContextMenu(mtxt);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		mif.inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case R.id.openFile:
			openDialog();
			break;
		case R.id.about:
			aboutDialog();
			break;
		case R.id.exit:
			exitApp();
			break;
		}
		return true;
	}
	
	private void openDialog() {
		String[] items=new String[]{"横着打开","竖着打开","斜着打开"};
		builder=new Builder(this);
		builder.setTitle("你想怎样打开呢？")
			   .setItems(items, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					switch(which){
					case 0:
						Toast.makeText(MenuTest.this, "你想横着打开", 2).show();
						break;
					case 1:
						Toast.makeText(MenuTest.this, "你想竖着打开", 2).show();
						break;
					case 2:
						Toast.makeText(MenuTest.this, "你想斜着打开", 2).show();
						break;
					}
				}
			});
		builder.create().show();
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		menu.add(0, ITEM1, 0, "红色背景");
		menu.add(0, ITEM2, 0, "蓝色背景");
		menu.add(0, ITEM3, 0, "白色背景");
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case ITEM1:
			mtxt.setBackgroundColor(Color.RED);
			break;
		case ITEM2:
			mtxt.setBackgroundColor(Color.BLUE);
			break;
		case ITEM3:
			mtxt.setBackgroundColor(Color.WHITE);
			break;
		}
		return true;
	}

	private void exitApp() {
		builder=new Builder(this);
		builder.setMessage("你确定要退出？")
			   .setPositiveButton("确定", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					finish();
				}
			}).setNegativeButton("取消", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					return;
				}
			});
		AlertDialog dialog=builder.create();
		dialog.show();
	}

	private void aboutDialog() {
		builder=new Builder(this);
		builder.setPositiveButton("确定", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
			}
		});
		AlertDialog dialog=builder.create();
		dialog.setMessage("现在演示的是如何通过xml文件生成菜单资源！");
		dialog.setCancelable(false);
		dialog.show();
	}
	
}
