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
import com.mobile.appPre.sms.HsyjActivity;
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

public class AlermStationUpdateActivity extends Activity{
	private String url="http://shortmsg.tmcadi.cn/SmsAlarmServerce.asmx";
	private List<NameValuePair> params = new ArrayList<NameValuePair>();
	TextView up_province,up_stationname,up_mess,up_yuyin,up_stationid;
	EditText up_inval,up_total;
	LinearLayout up_station;
	AlermStation alerm;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//全屏展示本窗口，无标题
		setContentView(R.layout.alerm_stationupdate);
		
		up_province=(TextView)this.findViewById(R.id.up_province);
		up_stationname=(TextView)this.findViewById(R.id.up_stationname);
		up_mess=(TextView)this.findViewById(R.id.up_mess);
		up_yuyin=(TextView)this.findViewById(R.id.up_yuyin);
		up_stationid=(TextView)this.findViewById(R.id.up_stationid);
		up_inval=(EditText)this.findViewById(R.id.up_inval);
		up_total=(EditText)this.findViewById(R.id.up_total);
		up_station=(LinearLayout)this.findViewById(R.id.up_station);
		
		up_station.setOnClickListener(new MethodListener());
		up_mess.setOnClickListener(new MethodListener());
		up_yuyin.setOnClickListener(new MethodListener());
		
		Intent intent=getIntent();
		alerm=(AlermStation)intent.getSerializableExtra("alerm");
		up_province.setText(alerm.getProvince());
		up_stationname.setText(alerm.getStationName());
		up_stationid.setText(String.valueOf(alerm.getStationId()));
		up_inval.setText(String.valueOf(alerm.getIntervalTime()));
		up_total.setText(String.valueOf(alerm.getSendTotal()));
		ShareData.method=alerm.getSnedMethod();
		if(alerm.getSnedMethod().equals("短信"))
		{
			up_mess.setBackgroundResource(R.color.check);
			up_yuyin.setBackgroundResource(R.color.nocheck);
		}
		else
		{
			up_mess.setBackgroundResource(R.color.nocheck);
			up_yuyin.setBackgroundResource(R.color.check);
		}
	}
	
	class MethodListener implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId())
			{
			case R.id.up_mess:
				up_mess.setBackgroundResource(R.color.check);
				up_yuyin.setBackgroundResource(R.color.nocheck);
				ShareData.method="短信";
				break;
			case R.id.up_yuyin:
				up_mess.setBackgroundResource(R.color.nocheck);
				up_yuyin.setBackgroundResource(R.color.check);
				ShareData.method="语音";
				break;	
			case R.id.up_station:
				Intent intent=new Intent(AlermStationUpdateActivity.this,AlermProvStationList.class);
				intent.putExtra("method", "update");
				startActivityForResult(intent,0);
				break;
			}
		}		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		up_stationid.setText(data.getStringExtra("id").toString());
		up_stationname.setText(data.getStringExtra("name"));
		up_province.setText(data.getStringExtra("proname"));
	}
	
	public void Complete(View v){
		if(up_inval.getText().equals("")){
			Toast.makeText(AlermStationUpdateActivity.this, "间隔时间不能为空", Toast.LENGTH_SHORT).show();
			return;
		}
		if(up_total.getText().equals("")){
			Toast.makeText(AlermStationUpdateActivity.this, "发送次数不能为空", Toast.LENGTH_SHORT).show();
			return;
		}
		
		params.add(new BasicNameValuePair("userName",ShareData.userName));
		params.add(new BasicNameValuePair("passWord",ShareData.userPass));
		params.add(new BasicNameValuePair("Id",String.valueOf(alerm.getId())));
		params.add(new BasicNameValuePair("StationId",String.valueOf(up_stationid.getText())));
		params.add(new BasicNameValuePair("IntervalTime",up_inval.getText().toString()));
		params.add(new BasicNameValuePair("SendMethod",ShareData.method));
		params.add(new BasicNameValuePair("SendTotal",up_total.getText().toString()));
		params.add(new BasicNameValuePair("Recycle","1"));
		params.add(new BasicNameValuePair("IsStart","是"));
		
		InputStream	input = PublicHttpActivity.getJSONData(url+"/UpdateAlarmStation" ,params);
		
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
	    	Toast.makeText(AlermStationUpdateActivity.this, "修改成功！", Toast.LENGTH_SHORT).show();
		}else {
			Toast.makeText(AlermStationUpdateActivity.this, "修过失败！", Toast.LENGTH_SHORT).show();
		}
		this.finish();
	}
	
	public void goBack(View v){
		this.finish();
	}

}
