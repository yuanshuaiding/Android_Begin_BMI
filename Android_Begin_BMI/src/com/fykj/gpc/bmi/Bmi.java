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
		// ����intent��action,category����
		btn_intent = findViewById(R.id.btn_intent);
		// ����action��data�������ϵͳActivity
		btn_actiondata = findViewById(R.id.btn_actiondata);
		// �����Զ����SeekBar
		btn_myseekbar = findViewById(R.id.btn_seekbar);
		// ����ClipDrawable
		btn_clipdraw = findViewById(R.id.btn_clipdraw);
		// ����attr
		btn_attribute = findViewById(R.id.btn_attribute);
		// ����Canvas
		btn_canvas = findViewById(R.id.btn_canvas);
		// ����Matrix
		btn_matrix = findViewById(R.id.btn_matrix);
		// ����Shader
		btn_shader = findViewById(R.id.btn_shader);
		// ������֡����
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
				// ��Intent������<intent-filter>�����úø�action�����
				it.setAction(BMIACTION);
				// ����Category���ԣ�ע��Ҫ��<intent-filter>�����ø�Category����ֵ��������޷�����Ŀ�����
				it.addCategory("com.fykj.gpc.bmi.CATEGORY");
				startActivity(it);
			}
		});
		btn_actiondata.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent it = new Intent();
				// �鿴������ϵ�˵�action
				// it.setAction(Intent.ACTION_VIEW);
				// it.setData(Uri.parse("content://contacts/people/"));
				// ����ϵ�������б���ѡ����ϵ�˵�action
				// it.setAction(Intent.ACTION_PICK);
				// it.setData(ContactsContract.Contacts.CONTENT_URI);
				// ����Type��������ϵ����Ϣ�б���ѡ����ϵ�˵ķ���
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

	// ���ڴ�����menu���󵯳��Ĳ˵�
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		// menu.setQwertyMode(false);
		menu.add(0, MENU_ABOUT, 0, "����...");
		menu.add(0, MENU_QUIT, 0, "�˳�");
		return true;
	}

	// ���ڴ�����menu���󵯳��Ĳ˵�����Ӧ�ĵ����Ĵ�����
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
			bundle.putString("msg", "��ӭ��");
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

	// �����ļ������õ���View��onClick����ƥ��ķ����ᱻ����
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
		builder.setItems(new String[] { "��һ��", "�򿪰�׿�г�", "������" },
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
		Toast.makeText(Bmi.this, "�Һܿ���ʧŶ��", Toast.LENGTH_SHORT).show();
	}

}