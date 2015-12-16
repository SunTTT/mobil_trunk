package com.mobile.appPre;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.longhui.service.AutoScollViewPagerAdapter;
import com.mobile.appPre.R;
import com.mobile.publiclass.ShareData;
import com.mobile.publiclass.SysExitUtil;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;

public class HomeActivity extends Activity {
	// 定义整形数组，即图片源
	private Object[][] mTitle = {
			{ R.string.DataMonitoring, "DataMonitoring",R.drawable.data_list },			
			{ R.string.DataAnalysis, "DataAnalysis", R.drawable.linechart },
			{ R.string.ImageMonitoring, "ImageMonitoring", R.drawable.picture },
			{ R.string.AlarmSet, "AlarmSet", R.drawable.camera },
	};
private AutoScrollViewPager autoScrollViewPager;
	private String stationId = "0";
	private String SiteList =  null ;
	private ArrayList<ImageView> imageViews = new ArrayList<ImageView>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SysExitUtil.activityList.add(HomeActivity.this); 
		requestWindowFeature(Window.FEATURE_NO_TITLE);//全屏展示本窗口，无标题
		setContentView(R.layout.desktop);
		//获得传入的数据
		Intent intent =  getIntent();
		Bundle getdata = intent.getBundleExtra("data");
		SiteList = getdata.getString("SiteList");
		autoScrollViewPager = (AutoScrollViewPager) findViewById(R.id.auto_scroll_viewpager);
for (int i = 0;i<4;i++){
	ImageView imageView = new ImageView(this);
    imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

	imageView.setBackgroundResource(R.drawable.site_list_top);
	imageViews.add(imageView);
}
		AutoScollViewPagerAdapter autoScollViewPagerAdapter = new AutoScollViewPagerAdapter(imageViews);
		autoScrollViewPager.setAdapter(autoScollViewPagerAdapter);
//		autoScrollViewPager.startAutoScroll();
//		autoScrollViewPager.setInterval(2000);
//		autoScrollViewPager.startAutoScroll();
//		autoScrollViewPager.setCurrentItem(Integer.MAX_VALUE/2-Integer.MAX_VALUE/2%imageViews.size());
		autoScrollViewPager.startAutoScroll(5*1000);
		autoScrollViewPager.setSlideBorderMode(AutoScrollViewPager.SLIDE_BORDER_MODE_CYCLE);
		autoScrollViewPager.setScrollDurationFactor(5);
		// 获得GridView对象
		GridView gridview = (GridView) findViewById(R.id.gridView);

		gridview.setNumColumns(3);// 设置每行列数
		gridview.setVerticalSpacing(20);// 垂直间隔
		gridview.setHorizontalSpacing(50);// 水平间隔
		gridview.setPadding(0, 10, 0, 0);

		
		// 生成动态数组，并且转入数据
		ArrayList<HashMap<String, Object>> lstImageItem = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i <= 2; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();

			map.put("ItemImage", mTitle[i][2]);
			map.put("ItemText", getString((Integer) mTitle[i][0]).toString());// 按序号做ItemText
			lstImageItem.add(map);
		}
		// 生成适配器的ImageItem <====> 动态数组的元素，两者一一对应
		SimpleAdapter saImageItems = new SimpleAdapter(this, 
				lstImageItem,// 数据来源
				R.layout.night_item,// night_item的XML实现

				// 动态数组与ImageItem对应的子项
				new String[] { "ItemImage", "ItemText" },

				// ImageItem的XML文件里面的一个ImageView,两个TextView ID
				new int[] { R.id.ItemImage, R.id.ItemText });

		// 添加并且显示
		gridview.setAdapter(saImageItems);
		// 事件监听
		// *
		gridview.setOnItemClickListener(new OnItemClickListener() {

			
			public void onItemClick(AdapterView<?> arg0,// The AdapterView where
														// the click happened
					View arg1,// The view within the AdapterView that was
								// clicked
					int arg2,// The position of the view in the adapter
					long arg3// The row id of the item that was clicked
			) {
				
				boolean IsLogin = true;
				Intent intent = new Intent();
				Bundle home_table_data = new Bundle();
				home_table_data.putString("SiteList", SiteList);
				ShareData.stationList=SiteList;
				switch (arg2) {
				case 0:
					Log.v("HomeActivity/case 0", "---case 0---");
					intent.setClass(HomeActivity.this,
							SiteList.class);
					home_table_data.putString("TOOLBAR_ITEM", ""
							+ arg2);

					home_table_data.putString("stationId",stationId);
					intent.putExtra("data", home_table_data);					
					
					break;
				case 1:
					Log.v("HomeActivity/case 1", "---case 1---");
					intent.setClass(HomeActivity.this,
							SiteList.class);
					home_table_data.putString("TOOLBAR_ITEM", ""
							+ arg2);
					home_table_data.putString("stationId",stationId);
					intent.putExtra("data", home_table_data);

					break;

				case 2:
					Log.v("HomeActivity/case 2", "---case 2---");
					intent.setClass(HomeActivity.this,
							SiteList.class);
					home_table_data.putString("TOOLBAR_ITEM", ""
							+ arg2);
					home_table_data.putString("stationId",stationId);
					intent.putExtra("data", home_table_data);									
					break;
				case 3:
					Log.i("HomeActivity/case 3", "---case 3---");
					intent.setClass(HomeActivity.this,AlermStationActivity.class);
					//home_table_data.putString("TOOLBAR_ITEM",""+ arg2);
					//home_table_data.putString("stationId",stationId);
					//home_table_data.putString("stationName","");
					//home_table_data.putString("SiteList","");
					//intent.putExtra("data", home_table_data);

					break;
				//短信报警模块入口
				case 10:

					Log.v("HomeActivity/case 10", "---case 10---");

					intent.setClass(HomeActivity.this,
							LoginActivity.class);
					home_table_data.putString("TOOLBAR_ITEM", ""
							+ arg2);
					intent.putExtra("Activity", "MobiledomainActivity");
					break;
				}
				if (IsLogin) {
					HomeActivity.this.startActivity(intent);
				}
			}

		});		
	}
	//再按一次退出
    long waitTime = 2000;
    long touchTime = 0;
    public void onBackPressed() {
		long currentTime = System.currentTimeMillis();
		if((currentTime-touchTime)>=waitTime) {
			Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
			touchTime = currentTime;
		}else {
			finish();
		}
	}
   
	public void onStart(){
		super.onStart();
	}
	public void onStop(){
		super.onStop();
		autoScrollViewPager.stopAutoScroll();
	}
	public void onPause(){
		super.onPause();
		autoScrollViewPager.stopAutoScroll();
	}
	public void onResume(){
		super.onResume();
		autoScrollViewPager.startAutoScroll();
	}
	public void onRestart(){
		super.onRestart();
	}
	public void onDestory(){
		super.onDestroy();
	}
	
}