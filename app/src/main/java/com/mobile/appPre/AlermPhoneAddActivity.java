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

import com.mobile.appPre.AlermAddStation.MethodListener;
import com.mobile.publiclass.PublicHttpActivity;
import com.mobile.publiclass.ShareData;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.mobile.appPre.R;

public class AlermPhoneAddActivity extends Activity{
	private String url="http://shortmsg.tmcadi.cn/SmsAlarmServerce.asmx";
	private List<NameValuePair> params = new ArrayList<NameValuePair>();
	EditText ap_phoneno;
	TextView ap_start,ap_nostart;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//全屏展示本窗口，无标题
		setContentView(R.layout.alerm_phoneadd);
		ap_phoneno=(EditText)this.findViewById(R.id.ap_phoneno);
		ap_start=(TextView)this.findViewById(R.id.ap_start);
		ap_nostart=(TextView)this.findViewById(R.id.ap_nostart);
		ap_start.setOnClickListener(new MethodListener());
		ap_nostart.setOnClickListener(new MethodListener());
	}
	
	class MethodListener implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			//TextView t=(TextView)v;
			switch(v.getId())
			{
			case R.id.ap_start:
				ap_start.setBackgroundResource(R.color.check);
				ap_nostart.setBackgroundResource(R.color.nocheck);
				ShareData.isStart="是";
				break;
			case R.id.ap_nostart:
				ap_start.setBackgroundResource(R.color.nocheck);
				ap_nostart.setBackgroundResource(R.color.check);
				ShareData.isStart="否";
				break;
			}
		}		
	}
	
	public void Complete(View v){
		if(ap_phoneno.getText().equals("")){
			Toast.makeText(this, "手机号码不能为空", Toast.LENGTH_SHORT).show();
			return;
		}
		String phoneNo=ap_phoneno.getText().toString();
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(phoneNo);
		if(ap_phoneno.getText().length()!=11||!m.matches()){
			Toast.makeText(this, "手机号码不正确", Toast.LENGTH_SHORT).show();
			return;
		}
		params.add(new BasicNameValuePair("userName",ShareData.userName));
		params.add(new BasicNameValuePair("passWord",ShareData.userPass));
		params.add(new BasicNameValuePair("PhoneNo",phoneNo));
		params.add(new BasicNameValuePair("IsStart",ShareData.isStart));
		
		InputStream	input = PublicHttpActivity.getJSONData(url+"/AddPhoneNo" ,params);
		
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
	    	Toast.makeText(this, "添加成功！", Toast.LENGTH_SHORT).show();
		}else {
			Toast.makeText(this, "添加失败！", Toast.LENGTH_SHORT).show();
		}
		this.finish();
	}
	
	public void goBack(View v){
		this.finish();
	}
}
