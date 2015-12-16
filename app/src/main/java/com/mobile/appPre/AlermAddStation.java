package com.mobile.appPre;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.longhui.entity.AlermStation;
import com.mobile.appPre.AlermStationUpdateActivity.MethodListener;
import com.mobile.publiclass.PublicHttpActivity;
import com.mobile.publiclass.ShareData;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.mobile.appPre.R;

public class AlermAddStation extends Activity{
	private String url="http://shortmsg.tmcadi.cn/SmsAlarmServerce.asmx";
	private List<NameValuePair> params = new ArrayList<NameValuePair>();
	TextView add_phoneno,add_province,add_stationname,add_stationid,add_mess,add_yuyin,add_start,add_nostart;
	EditText add_total,add_inval;
	LinearLayout phonelist_click,add_stationclick;
	int phoneId,stationId;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//全屏展示本窗口，无标题
		setContentView(R.layout.alerm_addstation);
		//add_province=(TextView)this.findViewById(R.id.add_province);
		add_stationname=(TextView)this.findViewById(R.id.add_stationname);
		add_mess=(TextView)this.findViewById(R.id.add_mess);
		add_yuyin=(TextView)this.findViewById(R.id.add_yuyin);
		//add_stationid=(TextView)this.findViewById(R.id.add_stationid);
		add_start=(TextView)this.findViewById(R.id.add_start);
		add_nostart=(TextView)this.findViewById(R.id.add_nostart);
		add_phoneno=(TextView)this.findViewById(R.id.add_phoneno);
		phonelist_click=(LinearLayout)this.findViewById(R.id.phonelist_click);
		phonelist_click.setOnClickListener(new GetPhoneListListener());
		add_stationclick=(LinearLayout)this.findViewById(R.id.add_stationclick);
		add_stationclick.setOnClickListener(new GetPhoneListListener());
		
		add_inval=(EditText)this.findViewById(R.id.add_inval);
		add_total=(EditText)this.findViewById(R.id.add_total);
		
		add_mess.setOnClickListener(new MethodListener());
		add_yuyin.setOnClickListener(new MethodListener());
		add_start.setOnClickListener(new MethodListener());
		add_nostart.setOnClickListener(new MethodListener());
	}
	
	class GetPhoneListListener implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			switch(v.getId())
			{
			case R.id.phonelist_click:
				Intent intent=new Intent(AlermAddStation.this,AlermGetPhoneList.class);
				//startActivity(intent);
				startActivityForResult(intent,0);
				break;
			case R.id.add_stationclick:
				Intent intent2=new Intent(AlermAddStation.this,AlermProvStationList.class);
				intent2.putExtra("method", "add");
				startActivityForResult(intent2,1);
				break;
			}
			
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		//Intent intent=new Intent();
		switch(resultCode)
		{
		case 0:
			phoneId=Integer.valueOf(data.getStringExtra("phoneId"));
			add_phoneno.setText(data.getStringExtra("phoneNum"));
			break;
		case 1:
			stationId=Integer.valueOf(data.getStringExtra("id"));
			add_stationname.setText(data.getStringExtra("name"));
			//add_province.setText(data.getStringExtra("proname"));
			break;
		}		
	}
	
	class MethodListener implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			//TextView t=(TextView)v;
			switch(v.getId())
			{
			case R.id.add_mess:
				add_mess.setBackgroundResource(R.color.check);
				add_yuyin.setBackgroundResource(R.color.nocheck);
				ShareData.method="短信";
				break;
			case R.id.add_yuyin:
				add_mess.setBackgroundResource(R.color.nocheck);
				add_yuyin.setBackgroundResource(R.color.check);
				ShareData.method="语音";
				break;	
			case R.id.add_start:
				add_start.setBackgroundResource(R.color.check);
				add_nostart.setBackgroundResource(R.color.nocheck);
				ShareData.isStart="是";
				break;
			case R.id.add_nostart:
				add_start.setBackgroundResource(R.color.nocheck);
				add_nostart.setBackgroundResource(R.color.check);
				ShareData.isStart="否";
				break;
			}
		}
		
	}
	
	public void Complete(View v){
		if(add_phoneno.getText().equals("")){
			Toast.makeText(AlermAddStation.this, "手机号码不能为空", Toast.LENGTH_SHORT).show();
			return;
		}
		if(add_stationname.getText().equals("")){
			Toast.makeText(AlermAddStation.this, "站点不能为空", Toast.LENGTH_SHORT).show();
			return;
		}
		if(add_inval.getText().equals("")){
			Toast.makeText(AlermAddStation.this, "间隔时间不能为空", Toast.LENGTH_SHORT).show();
			return;
		}
		if(add_total.getText().equals("")){
			Toast.makeText(AlermAddStation.this, "发送次数不能为空", Toast.LENGTH_SHORT).show();
			return;
		}
		params.clear();
		params.add(new BasicNameValuePair("userName",ShareData.userName));
		params.add(new BasicNameValuePair("passWord",ShareData.userPass));
		params.add(new BasicNameValuePair("PhoneId",String.valueOf(phoneId)));
		params.add(new BasicNameValuePair("StationId",String.valueOf(stationId)));
		params.add(new BasicNameValuePair("IntervalTime",add_inval.getText().toString()));
		params.add(new BasicNameValuePair("SendMethod",ShareData.method));
		params.add(new BasicNameValuePair("SendTotal",add_total.getText().toString()));
		params.add(new BasicNameValuePair("Recycle","1"));
		params.add(new BasicNameValuePair("IsStart",ShareData.isStart));
		
		InputStream	input = PublicHttpActivity.getJSONData(url+"/AddAlarmStation" ,params);
		
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
	    
	    if (sb.toString().contains("true")) {
	    	Toast.makeText(AlermAddStation.this, "修改成功！", Toast.LENGTH_SHORT).show();
		}else {
			Toast.makeText(AlermAddStation.this, "修过失败！", Toast.LENGTH_SHORT).show();
		}
		this.finish();
	}
	
	public void goBack(View v){
		this.finish();
	}

}
