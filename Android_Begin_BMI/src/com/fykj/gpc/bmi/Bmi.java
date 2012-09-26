package com.fykj.gpc.bmi;

import java.text.DecimalFormat;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Bmi extends Activity {
	private static final String BMIACTION = "com.fykj.gpc.bmi.ACTION";
	private TextView feildHeight, feildWeight, result;
	private Button btn, next, readXml, menuTest, layoutBtn, frameTest,
			tableTest, scrollviewBtn, tabTest, progBarBtn, listViewTest,
			listViewActivity;
	private Button expandableTest;
	private Button preferenceTest;
	private View btn_drawview;
	private View btn_intent;
	private View btn_actiondata;
	private View btn_myseekbar;
	private View btn_clipdraw;
	private View btn_attribute;
	private View btn_canvas;
	private View btn_matrix;
	private View btn_shader;
	private View btn_frameanim;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		findViews();
		setListeners();
	}

	private void findViews() {
		btn = (Button) findViewById(R.id.submit);
		next = (Button) findViewById(R.id.next);
		readXml = (Button) findViewById(R.id.readXml);
		feildHeight = (TextView) findViewById(R.id.height);
		feildWeight = (TextView) findViewById(R.id.weight);
		result = (TextView) findViewById(R.id.result);
		menuTest = (Button) findViewById(R.id.menuTest);
		layoutBtn = (Button) findViewById(R.id.layoutBtn);
		frameTest = (Button) findViewById(R.id.frameTest);
		tableTest = (Button) findViewById(R.id.tableTest);
		scrollviewBtn = (Button) findViewById(R.id.scrollviewBtn);
		tabTest = (Button) findViewById(R.id.tabTest);
		progBarBtn = (Button) findViewById(R.id.progressBarTest);
		listViewTest = (Button) findViewById(R.id.listViewTest);
		listViewActivity = (Button) findViewById(R.id.test);
		expandableTest = (Button) findViewById(R.id.expandableListTest);
		preferenceTest = (Button) findViewById(R.id.preferenceTest);
		btn_drawview = findViewById(R.id.btn_drawview);
		// 测试intent与action,category配置
		btn_intent = findViewById(R.id.btn_intent);
		// 测试action与data组合启动系统Activity
		btn_actiondata = findViewById(R.id.btn_actiondata);
		// 测试自定义的SeekBar
		btn_myseekbar = findViewById(R.id.btn_seekbar);
		// 测试ClipDrawable
		btn_clipdraw = findViewById(R.id.btn_clipdraw);
		// 测试attr
		btn_attribute = findViewById(R.id.btn_attribute);
		// 测试Canvas
		btn_canvas = findViewById(R.id.btn_canvas);
		// 测试Matrix
		btn_matrix = findViewById(R.id.btn_matrix);
		// 测试Shader
		btn_shader = findViewById(R.id.btn_shader);
		// 测试逐帧动画
		btn_frameanim = findViewById(R.id.btn_frameanim);
	}

	private void setListeners() {
		btn.setOnClickListener((android.view.View.OnClickListener) calcBMI);
		next.setOnClickListener((android.view.View.OnClickListener) nextOnclick);
		readXml.setOnClickListener((android.view.View.OnClickListener) toReadView);
		menuTest.setOnClickListener((android.view.View.OnClickListener) toMenuTest);
		layoutBtn.setOnClickListener(toLayoutTest);
		frameTest.setOnClickListener(toFrameTest);
		tableTest.setOnClickListener(toTableTest);
		scrollviewBtn.setOnClickListener(toScrollView);
		tabTest.setOnClickListener(toTabTest);
		progBarBtn.setOnClickListener(toProgBarTest);
		listViewTest.setOnClickListener(toListViewTest);
		listViewActivity.setOnClickListener(toListActivity);
		expandableTest.setOnClickListener(expandableTestListener);
		preferenceTest.setOnClickListener(prefereceListener);
		btn_drawview.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent it = new Intent();
				it.setClass(getApplicationContext(), ViewFollowFinger.class);
				startActivity(it);
			}
		});
		btn_intent.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent it = new Intent();
				// 该Intent会启动<intent-filter>中配置好该action的组件
				it.setAction(BMIACTION);
				// 设置Category属性，注意要在<intent-filter>中配置该Category属性值，否则会无法启动目标组件
				it.addCategory("com.fykj.gpc.bmi.CATEGORY");
				startActivity(it);
			}
		});
		btn_actiondata.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent it = new Intent();
				// 查看所有联系人的action
				// it.setAction(Intent.ACTION_VIEW);
				// it.setData(Uri.parse("content://contacts/people/"));
				// 在联系人姓名列表里选择联系人的action
				// it.setAction(Intent.ACTION_PICK);
				// it.setData(ContactsContract.Contacts.CONTENT_URI);
				// 借助Type属性在联系人信息列表里选择联系人的方法
				it.setAction(Intent.ACTION_GET_CONTENT);
				it.setType("vnd.android.cursor.item/phone");
				startActivity(it);
			}
		});
		btn_myseekbar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(getApplicationContext(), MySeekBarTest.class);
				startActivity(intent);
			}
		});
		btn_clipdraw.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent it = new Intent(getApplicationContext(),
						ClipDrawableTest.class);
				startActivity(it);
			}
		});
		btn_attribute.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent it = new Intent(getApplicationContext(),
						AttributesDemo.class);
				startActivity(it);
			}
		});
		btn_canvas.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent it = new Intent(getApplicationContext(),
						CanvasActivity.class);
				startActivity(it);
			}
		});
		btn_matrix.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent it = new Intent(getApplicationContext(),
						MatrixTest.class);
				startActivity(it);
			}
		});
		btn_shader.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent it = new Intent(getApplicationContext(),
						ShaderTest.class);
				startActivity(it);
			}
		});
		btn_frameanim.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent it = new Intent(getApplicationContext(),
						FrameAnimation.class);
				startActivity(it);
			}
		});

	}

	private static final int MENU_ABOUT = Menu.FIRST;
	private static final int MENU_QUIT = Menu.FIRST + 1;

	// 用于创建按menu键后弹出的菜单
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		// menu.setQwertyMode(false);
		menu.add(0, MENU_ABOUT, 0, "关于...");
		menu.add(0, MENU_QUIT, 0, "退出");
		return true;
	}

	// 用于创建按menu键后弹出的菜单所对应的点击后的处理方法
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case MENU_ABOUT:
			openAboutDialog();
			break;
		case MENU_QUIT:
			finish();
			break;
		}
		return true;
	}

	private Button.OnClickListener calcBMI = new Button.OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			DecimalFormat nf = new DecimalFormat("0.00");
			Double height = Double.parseDouble((feildHeight.getText()
					.toString())) / 100;
			Double weight = Double
					.parseDouble(feildWeight.getText().toString());
			Double bmi = weight / (height * height);
			result.setText("Your BMI is:" + nf.format(bmi));
			// give health advice deponds on bmi value
			TextView advice = (TextView) findViewById(R.id.suggest);
			if (bmi > 25) {
				advice.setText(R.string.heavy);
			} else if (bmi < 20) {
				advice.setText(R.string.light);
			} else {
				advice.setText(R.string.average);
			}
			// openAboutDialog();
			openShortDialog();

		}
	};

	private Button.OnClickListener toReadView = new Button.OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent in = new Intent();
			in.setClass(Bmi.this, XmlTest.class);
			startActivity(in);
		}
	};
	private Button.OnClickListener toMenuTest = new Button.OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent in = new Intent();
			in.setClass(Bmi.this, MenuTest.class);
			startActivity(in);
		}
	};

	private OnClickListener toFrameTest = new OnClickListener() {

		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(Bmi.this, FrameTest.class);
			intent.putExtra("msg", "welcome!");
			startActivity(intent);
		}
	};

	private OnClickListener toTableTest = new OnClickListener() {

		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(Bmi.this, TableTest.class);
			intent.putExtra("msg", "welcome,cowboy!");
			startActivity(intent);
		}
	};

	private OnClickListener toScrollView = new OnClickListener() {

		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(Bmi.this, ScrollViewTest.class);
			startActivity(intent);
		}
	};

	private OnClickListener toTabTest = new OnClickListener() {

		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(Bmi.this, TabTest.class);
			startActivity(intent);
		}
	};

	private Button.OnClickListener nextOnclick = new Button.OnClickListener() {

		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(Bmi.this, Report.class);
			Bundle bundle = new Bundle();
			bundle.putString("height", feildHeight.getText().toString());
			bundle.putString("weight", feildWeight.getText().toString());
			intent.putExtras(bundle);
			startActivity(intent);
		}

	};

	private OnClickListener toLayoutTest = new OnClickListener() {

		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(Bmi.this, LayoutTest.class);
			Bundle bundle = new Bundle();
			bundle.putString("msg", "欢迎！");
			intent.putExtras(bundle);
			startActivity(intent);
		}
	};

	private OnClickListener toProgBarTest = new OnClickListener() {

		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(Bmi.this, ProgressBarTest.class);
			startActivity(intent);
		}
	};

	private OnClickListener toListViewTest = new OnClickListener() {

		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(Bmi.this, ListViewTest.class);
			startActivity(intent);
		}
	};

	private OnClickListener toListActivity = new OnClickListener() {

		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(Bmi.this, ListViewActivity.class);
			startActivity(intent);
		}
	};

	private OnClickListener expandableTestListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(getApplicationContext(), ExpandableTest.class);
			startActivity(intent);
		}
	};

	// 布局文件中设置的与View的onClick属性匹配的方法会被调用
	public void originalTest(View v) {
		Intent intent = new Intent();
		intent.setClass(getApplicationContext(), OriginalResTest.class);
		startActivity(intent);
	}

	public void moveView(View v) {
		Intent intent = new Intent();
		intent.setClass(getApplicationContext(), MoveView.class);
		startActivity(intent);
	}

	public void meshImage(View v) {
		Intent intent = new Intent();
		intent.setClass(getApplicationContext(), MeshImageTest.class);
		startActivity(intent);
	}

	protected void openAboutDialog() {
		AlertDialog.Builder builder = new Builder(Bmi.this);
		builder.setItems(new String[] { "第一个", "打开安卓市场", "第三个" },
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int index) {
						// Toast.makeText(Bmi.this, arg0.toString(), 2).show();
						System.out.println(index);
						switch (index) {
						case 0:
							break;
						case 1:
							Uri uri = Uri.parse(getString(R.string.homouri));
							Intent intent = new Intent(Intent.ACTION_VIEW, uri);
							startActivity(intent);
							break;
						case 2:
							break;
						}
					}
				}).setTitle("hello");
		AlertDialog ald = builder.create();
		ald.show();
		/*
		 * new AlertDialog.Builder(Bmi.this).setTitle(R.string.app_name)
		 * .setMessage(R.string.msg) .setPositiveButton(R.string.ok, new
		 * DialogInterface.OnClickListener() {
		 * 
		 * @Override public void onClick(DialogInterface dialog, int which) {
		 * //Nothing TODO } }) .setNegativeButton(R.string.homepage, new
		 * DialogInterface.OnClickListener() {
		 * 
		 * @Override public void onClick(DialogInterface dialog, int which) { //
		 * TODO Auto-generated method stub Uri
		 * uri=Uri.parse(getString(R.string.homouri)); Intent intent=new
		 * Intent(Intent.ACTION_VIEW,uri); startActivity(intent); } }) .show();
		 */
	}

	private OnClickListener prefereceListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			Intent it = new Intent();
			it.setClass(getApplicationContext(), PreferenceActivityTest.class);
			startActivity(it);
		}
	};

	protected void openShortDialog() {
		Toast.makeText(Bmi.this, "我很快消失哦！", Toast.LENGTH_SHORT).show();
	}

}