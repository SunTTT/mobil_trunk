package com.mobile.appPre;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import com.mobile.appPre.R;
import com.mobile.publiclass.PublicHttpActivity;
import com.mobile.publiclass.ShareData;

public class Alerm_ShangXiaActivity extends Activity{
	private String url="http://shortmsg.tmcadi.cn/SmsAlarmServerce.asmx";
	private List<NameValuePair> params = new ArrayList<NameValuePair>();
	EditText ds_hour,ds_minite;
	TextView ds_start,ds_nostart;
	String id;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//全屏展示本窗口，无标题
		setContentView(R.layout.alerm_shangxia);
		Intent intent=getIntent();
		id=intent.getStringExtra("id");
		params.add(new BasicNameValuePair("userName",ShareData.userName));
		params.add(new BasicNameValuePair("passWord",ShareData.userPass));
		params.add(new BasicNameValuePair("Id",id));
		InputStream	input = PublicHttpActivity.getJSONData(url+"/GetOutSideAlarmJson" ,params);
		String str=PublicHttpActivity.convertStreamToString(input);
		str=str.split(">")[2];
		str=str.substring(0, str.length()-8);
		ArrayList<HashMap<String, Object>> hash=PublicHttpActivity.getArrayList(str);
		
	}
	
	public void goBack(View v){
		this.finish();
	}
}
