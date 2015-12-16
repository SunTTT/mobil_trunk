package com.mobile.appPre;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.longhui.entity.AlermDingShi;
import com.longhui.entity.AlermXiTong;
import com.longhui.service.ReadFile;
import com.mobile.appPre.AlermDingShiActivity.MethodListener;
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

public class AlermXiTongActivity extends Activity{
	private String url="http://shortmsg.tmcadi.cn/SmsAlarmServerce.asmx";
	private List<NameValuePair> params = new ArrayList<NameValuePair>();
	EditText x_invaltime;
	TextView x_start,x_nostart,x_normal;
	String id;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//全屏展示本窗口，无标题
		setContentView(R.layout.alerm_xitong);
		x_invaltime=(EditText)this.findViewById(R.id.x_invaltime);
		x_normal=(EditText)this.findViewById(R.id.x_normal);
		x_start=(TextView)this.findViewById(R.id.x_start);
		x_nostart=(TextView)this.findViewById(R.id.x_nostart);
		x_start.setOnClickListener(new MethodListener());
		x_nostart.setOnClickListener(new MethodListener());
		
		Intent intent=getIntent();
		id=intent.getStringExtra("id");
		params.add(new BasicNameValuePair("userName",ShareData.userName));
		params.add(new BasicNameValuePair("passWord",ShareData.userPass));
		params.add(new BasicNameValuePair("Id",id));
		InputStream	input = PublicHttpActivity.getJSONData(url+"/GetSystemAlerm" ,params);
		AlermXiTong phone=ReadFile.getAlermXiTong(input);
		if(phone!=null)
		{
			x_invaltime.setText(phone.getinterval());
			if(!ShareData.userName.equals("cealadmin"))
			{
				x_invaltime.setVisibility(View.GONE);
			}
			x_normal.setText(phone.getIsNormal());
			if(phone.getIsAlerm().equals("是"))
			{
				x_start.setBackgroundResource(R.color.check);
				x_nostart.setBackgroundResource(R.color.nocheck);
				ShareData.isStart="是";
			}
			else
			{
				x_start.setBackgroundResource(R.color.nocheck);
				x_nostart.setBackgroundResource(R.color.check);
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
			case R.id.x_start:
				x_start.setBackgroundResource(R.color.check);
				x_nostart.setBackgroundResource(R.color.nocheck);
				ShareData.isStart="是";
				break;
			case R.id.x_nostart:
				x_start.setBackgroundResource(R.color.nocheck);
				x_nostart.setBackgroundResource(R.color.check);
				ShareData.isStart="否";
				break;
			}
		}		
	}
	
	public void Complete(View v){
		if(x_invaltime.getText().equals("")){
			Toast.makeText(this, "间隔时间不能为空", Toast.LENGTH_SHORT).show();
			return;
		}
		params.clear();
		params.add(new BasicNameValuePair("userName",ShareData.userName));
		params.add(new BasicNameValuePair("passWord",ShareData.userPass));
		params.add(new BasicNameValuePair("Id",id));
		params.add(new BasicNameValuePair("IsAlarm",ShareData.isStart));
		
		InputStream	input = PublicHttpActivity.getJSONData(url+"/UpdateSystemAlerm" ,params);
		
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
