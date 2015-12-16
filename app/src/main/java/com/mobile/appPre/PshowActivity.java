package com.mobile.appPre;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.FloatMath;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Gallery;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.longhui.service.MyGallery;
import com.mobile.appPre.R;

public class PshowActivity extends Activity {
	public static int screenWidth;
	// 屏幕高度
	public static int screenHeight;
	private MyGallery gallery;
	private GalleryAdapter gAdapter;
	private LinearLayout layout ;
	private TextView stationtext,datetext ;
	private String[] jhk ,stationtimes;
	private int stationid ;
	private String stationName ;
	/*//Position po = new Position();
	public String[] urls = {"http://202.196.80.146:86/Picture.ashx?id=2663&tname=HeNanNanYang2_N70",
			"http://202.196.80.146:86/Picture.ashx?id=4128&tname=YaKeShi_04_N70",
			"http://202.196.80.146:86/Picture.ashx?id=4129&tname=YaKeShi_04_N70"};*/
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.pshow);
		
		//得到从上个Activity传来的数据
		Intent getintent = getIntent();
		Bundle getdata = getintent.getBundleExtra("data");
		stationid =  getdata.getInt("id");
		stationName = getdata.getString("stationName");
		jhk = getdata.getStringArray("jhk");
		stationtimes = getdata.getStringArray("stationtimes");
		
		stationtext = (TextView) findViewById(R.id.pngshow_stationname);
		datetext = (TextView) findViewById(R.id.png_textview);
		
		stationtext.setText(stationName);
		datetext.setText(stationtimes[stationid]);
		
	
		//layout = (LinearLayout) findViewById(R.id.layout);
		gallery = (MyGallery) findViewById(R.id.gallery);
		gallery.setVerticalFadingEdgeEnabled(false);// 取消竖直渐变边框
		gallery.setHorizontalFadingEdgeEnabled(false);// 取消水平渐变边框
		
		gAdapter = new GalleryAdapter(this,jhk);
		gallery.setAdapter(gAdapter);
		gallery.setSelection(stationid);
		
		
		screenWidth = getWindow().getWindowManager().getDefaultDisplay().getWidth();
		screenHeight = getWindow().getWindowManager().getDefaultDisplay().getHeight();

		
		gallery.setOnItemSelectedListener(new GalleryChangeListener());
		
	}
	
	
	
	float beforeLenght = 0.0f; // 两触点距离
	float afterLenght = 0.0f; // 两触点距离
	boolean isScale = false;
	float currentScale = 1.0f;// 当前图片的缩放比率

	private class GalleryChangeListener implements OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
			currentScale = 1.0f;
			isScale = false;
			beforeLenght = 0.0f;
			afterLenght = 0.0f;
			datetext.setText(stationtimes[arg2]);

		}

		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}

	}

	public boolean onTouch(View v, MotionEvent event) {

		// Log.i("","touched---------------");
		switch (event.getAction() & MotionEvent.ACTION_MASK) {
		case MotionEvent.ACTION_POINTER_DOWN:// 多点缩放
			beforeLenght = spacing(event);
			if (beforeLenght > 5f) {
				isScale = true;
			}
			break;
		case MotionEvent.ACTION_MOVE:
			if (isScale) {
				afterLenght = spacing(event);
				if (afterLenght < 5f)
					break;
				float gapLenght = afterLenght - beforeLenght;
				if (gapLenght == 0) {
					break;
				} else if (Math.abs(gapLenght) > 5f) {
					
					float scaleRate = gapLenght / 854;// 缩放比例
					
					Animation myAnimation_Scale = new ScaleAnimation(currentScale, currentScale + scaleRate, currentScale, currentScale + scaleRate, Animation.RELATIVE_TO_SELF, 0.5f,
							Animation.RELATIVE_TO_SELF, 0.5f);
					
					myAnimation_Scale.setDuration(100);
					myAnimation_Scale.setFillAfter(true);
					myAnimation_Scale.setFillEnabled(true);
					
					currentScale = currentScale + scaleRate;
					
					gallery.getSelectedView().setLayoutParams(new Gallery.LayoutParams((int) (480 * (currentScale)), (int) (854 * (currentScale))));
					
					beforeLenght = afterLenght;
				}
				return true;
			}
			break;
		case MotionEvent.ACTION_POINTER_UP:
			isScale = false;
			break;
		}

		return false;
	}

	/**
	 * 就算两点间的距离
	 */
	private float spacing(MotionEvent event) {
		float x = event.getX(0) - event.getX(1);
		float y = event.getY(0) - event.getY(1);
//		return FloatMath.sqrt(x * x + y * y);
		return (float) Math.sqrt((double) (x * x + y * y));
	}
}