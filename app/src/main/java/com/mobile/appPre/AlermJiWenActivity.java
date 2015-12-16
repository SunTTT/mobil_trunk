package com.mobile.appPre;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.longhui.entity.AlermJiWen;
import com.longhui.entity.AlermTempRule;
import com.longhui.service.ReadFile;
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

public class AlermJiWenActivity extends Activity{
	private String url="http://shortmsg.tmcadi.cn/SmsAlarmServerce.asmx";
	private List<NameValuePair> params = new ArrayList<NameValuePair>();
	TextView j_start,j_nostart,j_status,j_shangshu,j_ruleadd,j_wencshu,j_timeshu;
	String id,temNo;	
	AlermTempRule rule;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//全屏展示本窗口，无标题
		setContentView(R.layout.alerm_jiwen);
		j_shangshu=(TextView)this.findViewById(R.id.j_shangshu);
		j_ruleadd=(TextView)this.findViewById(R.id.j_ruleup);
		j_wencshu=(TextView)this.findViewById(R.id.j_wencshu);
		j_timeshu=(TextView)this.findViewById(R.id.j_timeshu);
		
		j_status=(TextView)this.findViewById(R.id.j_status);
		j_start=(TextView)this.findViewById(R.id.j_start);
		j_nostart=(TextView)this.findViewById(R.id.j_nostart);
		j_start.setOnClickListener(new MethodListener());
		j_nostart.setOnClickListener(new MethodListener());
		j_ruleadd.setOnClickListener(new MethodListener());
		
		Intent intent=getIntent();
		id=intent.getStringExtra("id");
		params.add(new BasicNameValuePair("userName",ShareData.userName));
		params.add(new BasicNameValuePair("passWord",ShareData.userPass));
		params.add(new BasicNameValuePair("Id",id));
		InputStream	input = PublicHttpActivity.getJSONData(url+"/GetTempAlerm" ,params);
		AlermJiWen jiwen=ReadFile.getAlermJiWen(input);
		if(jiwen!=null)
		{
			temNo=jiwen.getTempNo();
			if(jiwen.getIsAlerm().equals("是"))
			{
				j_start.setBackgroundResource(R.color.check);
				j_nostart.setBackgroundResource(R.color.nocheck);
				ShareData.isStart="是";
			}
			else
			{
				j_start.setBackgroundResource(R.color.nocheck);
				j_nostart.setBackgroundResource(R.color.check);
				ShareData.isStart="否";
			}
			j_status.setText(jiwen.getAlermStatus());
			params.clear();
			params.add(new BasicNameValuePair("userName",ShareData.userName));
			params.add(new BasicNameValuePair("passWord",ShareData.userPass));
			params.add(new BasicNameValuePair("RuleId",jiwen.getTempNo()));
			InputStream	input2 = PublicHttpActivity.getJSONData(url+"/GetRule" ,params);
			rule=ReadFile.getAlermTempRule(input2);
			if(rule!=null)
			{
				j_shangshu.setText(rule.getHeightTemp());
				j_wencshu.setText(rule.getTempConstant());
				j_timeshu.setText(rule.getStartTime());
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
			case R.id.j_start:
				j_start.setBackgroundResource(R.color.check);
				j_nostart.setBackgroundResource(R.color.nocheck);
				ShareData.isStart="是";
				break;
			case R.id.j_nostart:
				j_start.setBackgroundResource(R.color.nocheck);
				j_nostart.setBackgroundResource(R.color.check);
				ShareData.isStart="否";
				break;
			case R.id.j_ruleup:
				Intent intent=new Intent(AlermJiWenActivity.this,AlermJiWRuleListActivity.class);
				intent.putExtra("id", rule);
				startActivityForResult(intent,0);
				break;
			}
		}		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		AlermTempRule temp=(AlermTempRule)data.getSerializableExtra("id");
		temNo=temp.getId();
		j_shangshu.setText(temp.getHeightTemp());
		j_wencshu.setText(temp.getTempConstant());
		j_timeshu.setText(temp.getStartTime());
	}
	
	public void Complete(View v){
		params.clear();
		params.add(new BasicNameValuePair("userName",ShareData.userName));
		params.add(new BasicNameValuePair("passWord",ShareData.userPass));
		params.add(new BasicNameValuePair("Id",id));
		params.add(new BasicNameValuePair("TempNo",temNo));
		params.add(new BasicNameValuePair("IsAlarm",ShareData.isStart));
		
		InputStream	input = PublicHttpActivity.getJSONData(url+"/UpdateTempAlerm" ,params);
		
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
