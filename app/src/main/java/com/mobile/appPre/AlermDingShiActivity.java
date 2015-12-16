package com.mobile.appPre;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.longhui.entity.AlermDingShi;
import com.longhui.entity.PhoneEntityList;
import com.longhui.service.ReadFile;
import com.mobile.appPre.AlermPhoneAddActivity.MethodListener;
import com.mobile.publiclass.PublicHttpActivity;
import com.mobile.publiclass.ShareData;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.mobile.appPre.R;

public class AlermDingShiActivity extends Activity{
	private String url="http://shortmsg.tmcadi.cn/SmsAlarmServerce.asmx";
	private List<NameValuePair> params = new ArrayList<NameValuePair>();
	EditText ds_hour,ds_minite;
	TextView ds_start,ds_nostart;
	String id;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//全屏展示本窗口，无标题
		setContentView(R.layout.alerm_dingshi);
		ds_hour=(EditText)this.findViewById(R.id.ds_hour);
		ds_minite=(EditText)this.findViewById(R.id.ds_minite);
		ds_start=(TextView)this.findViewById(R.id.ds_start);
		ds_nostart=(TextView)this.findViewById(R.id.ds_nostart);
		ds_start.setOnClickListener(new MethodListener());
		ds_nostart.setOnClickListener(new MethodListener());
		
		Intent intent=getIntent();
		id=intent.getStringExtra("id");
		params.add(new BasicNameValuePair("userName",ShareData.userName));
		params.add(new BasicNameValuePair("passWord",ShareData.userPass));
		params.add(new BasicNameValuePair("Id",id));
		InputStream	input = PublicHttpActivity.getJSONData(url+"/GetTimingAlerm" ,params);
		AlermDingShi phone=ReadFile.getAlermDingShi(input);
		if(phone!=null)
		{
			String time=phone.getAlermTime();			
			ds_hour.setText(time.split(":")[0]);
			ds_minite.setText(time.split(":")[1]);
			if(phone.getIsAlerm().equals("是"))
			{
				ds_start.setBackgroundResource(R.color.check);
				ds_nostart.setBackgroundResource(R.color.nocheck);
				ShareData.isStart="是";
			}
			else
			{
				ds_start.setBackgroundResource(R.color.nocheck);
				ds_nostart.setBackgroundResource(R.color.check);
				ShareData.isStart="否";
			}
		}
	}
	
	class MethodListener implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			//TextView t=(TextView)v;
			switch(v.getId())
			{
			case R.id.ds_start:
				ds_start.setBackgroundResource(R.color.check);
				ds_nostart.setBackgroundResource(R.color.nocheck);
				ShareData.isStart="是";
				break;
			case R.id.ds_nostart:
				ds_start.setBackgroundResource(R.color.nocheck);
				ds_nostart.setBackgroundResource(R.color.check);
				ShareData.isStart="否";
				break;
			}
		}		
	}
	
	public void Complete(View v){
		if(ds_hour.getText().equals("")||ds_minite.getText().equals("")){
			Toast.makeText(this, "小时和分钟都不能为空", Toast.LENGTH_SHORT).show();
			return;
		}
		params.clear();
		params.add(new BasicNameValuePair("userName",ShareData.userName));
		params.add(new BasicNameValuePair("passWord",ShareData.userPass));
		params.add(new BasicNameValuePair("Id",id));
		params.add(new BasicNameValuePair("AlarmTime",ds_hour.getText()+":"+ds_minite.getText()));
		params.add(new BasicNameValuePair("IsAlarm",ShareData.isStart));
		
		InputStream	input = PublicHttpActivity.getJSONData(url+"/UpdateTimingAlerm" ,params);
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));   
	    String str = null;   
	    
	    StringBuffer sb = new StringBuffer();   
	    try {
			while((str = reader.readLine())!=null){   
			  System.err.println(str+"str");   
			  sb.append(str);   
			}
			System.out.println(sb);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	    
	    if (sb.toString().equals("true")) {
	    	Toast.makeText(this, "定时报警设置成功！", Toast.LENGTH_SHORT).show();
		}else {
			Toast.makeText(this, "定时报警设置失败！", Toast.LENGTH_SHORT).show();
		}
		this.finish();
	}
	
	public void goBack(View v){
		this.finish();
	}
}
