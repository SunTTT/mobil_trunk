package com.mobile.appPre.sms;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.http.message.BasicNameValuePair;

import com.longhui.service.ReadFile;
import com.mobile.appPre.LoginActivity;
import com.mobile.appPre.MapActivity;
import com.mobile.appPre.NewMainactivity;
import com.mobile.appPre.R;
import com.mobile.publiclass.PublicHttpActivity;
import com.mobile.publiclass.ShareData;
import com.mobile.publiclass.SysExitUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class WarnSetActivity extends Activity {	
	
	private ListView listviewselect ;
	private TextView station_N_textview ;
	
	String[] listitem_N ;
	String[] listitem_id ;
	private String stationId  ,stationName ;
	private TextView system_statebutton ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.warnsetpage);
		
		Intent intent = getIntent();
		Bundle data = intent.getBundleExtra("data");
		stationId = data.getString("stationId");
		stationName = data.getString("stationName");
		
		findViewId();

	    station_N_textview.setText(stationName);
		
		listitem_N = new String[]{"每日提醒","实时汇报","红色预警","缺失报警","关于报警"};
		listitem_id = new String[]{"0","1","2","3","4"};
		
		List<Map<String, String>> listdata = new ArrayList<Map<String, String>>();//要循环的list
		
		for (int i = 0; i < listitem_N.length; i++) {
			Map<String, String> v = new HashMap<String, String>();
			v.put("textviewid", listitem_N[i]);
	        v.put("textviewname",listitem_N[i]);
	        listdata.add(v);
		}
		SimpleAdapter listadapter = new SimpleAdapter(this, listdata, R.layout.mainlist_item, 
        		new String[]{"textviewid","textviewname"},new int[]{R.id.textviewid,R.id.mainlist_textveiw});
		listviewselect.setAdapter(listadapter);
		
		listviewselect.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				if (arg2==0) {
					Intent intent = new Intent();
					Bundle data = new Bundle();
					data.putString("stationId", stationId);
					data.putString("stationName", stationName);
					intent.putExtra("data", data);
					intent.setClass(WarnSetActivity.this, MrtxActivity.class);
                    startActivity(intent);
				}else if (arg2==1) {
					Intent intent = new Intent();
					Bundle data = new Bundle();
					data.putString("stationId", stationId);
					data.putString("stationName", stationName);
					intent.putExtra("data", data);
                    intent.setClass(WarnSetActivity.this, SsbjActivity.class);
                    startActivity(intent);
				}else if (arg2==2) {
					Intent intent = new Intent();
					Bundle data = new Bundle();
					data.putString("stationId", stationId);
					data.putString("stationName", stationName);
					intent.putExtra("data", data);
                    intent.setClass(WarnSetActivity.this, HsyjActivity.class);
                    startActivity(intent);
				}else if (arg2==3) {
					Intent intent = new Intent();
					Bundle data = new Bundle();
					data.putString("stationId", stationId);
					data.putString("stationName", stationName);
					intent.putExtra("data", data);
                    intent.setClass(WarnSetActivity.this, QsbjActivity.class);
                    startActivity(intent);
				}else {
					Intent intent = new Intent();
					Bundle data = new Bundle();
					data.putString("stationId", stationId);
					data.putString("stationName", stationName);
					intent.putExtra("data", data);
                    intent.setClass(WarnSetActivity.this, AboutActivity.class);
                    startActivity(intent);
				}
				
			}
		});
		
		system_statebutton.setOnClickListener( new OnClickListener() {
			
			public void onClick(View v) {
				Intent intent = new Intent();
				Bundle data = new Bundle();
				data.putString("stationId", stationId);
				data.putString("stationName", stationName);
				intent.putExtra("data", data);
                intent.setClass(WarnSetActivity.this, SystemstateActivity.class);
                startActivity(intent);
				
			}
		});
	}//oncreate方法结束
	
	private void findViewId() {
		station_N_textview = (TextView) findViewById(R.id.set_stationName);
		TextPaint tp = station_N_textview.getPaint(); 
		tp.setFakeBoldText(true); 
		listviewselect = (ListView) findViewById(R.id.listview1);
		system_statebutton = (TextView) findViewById(R.id.set_leasttime);
		
	}
}

