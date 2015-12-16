package com.mobile.appPre;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.longhui.entity.AlermStation;
import com.longhui.service.ReadFile;
import com.mobile.publiclass.PublicHttpActivity;
import com.mobile.publiclass.ShareData;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.mobile.appPre.R;

public class AlermStationEditActivity extends Activity{
	private String url="http://shortmsg.tmcadi.cn/SmsAlarmServerce.asmx";
	private List<NameValuePair> params = new ArrayList<NameValuePair>();
	AlermStation alerm=new AlermStation();
	TextView e_stationname,e_method,e_invaltime,e_sendtotal,e_xiangqing;
	LinearLayout e_jishialerm,e_dingshialerm,e_xitongalerm,e_shangxiaalerm,e_jiwenalerm,e_guanyualerm;
	String id;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//全屏展示本窗口，无标题
		setContentView(R.layout.alerm_stationedit);
		e_stationname=(TextView)this.findViewById(R.id.e_stationname);
		e_method=(TextView)this.findViewById(R.id.e_method);
		e_invaltime=(TextView)this.findViewById(R.id.e_invaltime);
		e_sendtotal=(TextView)this.findViewById(R.id.e_sendtotal);
		e_xiangqing=(TextView)this.findViewById(R.id.e_xiangqing);
		
		e_jishialerm=(LinearLayout)this.findViewById(R.id.e_jishialerm);
		e_dingshialerm=(LinearLayout)this.findViewById(R.id.e_dingshialerm);
		e_xitongalerm=(LinearLayout)this.findViewById(R.id.e_xitongalerm);
		e_shangxiaalerm=(LinearLayout)this.findViewById(R.id.e_shangxiaalerm);
		e_jiwenalerm=(LinearLayout)this.findViewById(R.id.e_jiwenalerm);
		e_guanyualerm=(LinearLayout)this.findViewById(R.id.e_guanyualerm);
		e_jishialerm.setOnClickListener(new AlermOnClick());
		e_dingshialerm.setOnClickListener(new AlermOnClick());
		e_xitongalerm.setOnClickListener(new AlermOnClick());
		e_shangxiaalerm.setOnClickListener(new AlermOnClick());
		e_jiwenalerm.setOnClickListener(new AlermOnClick());
		e_guanyualerm.setOnClickListener(new AlermOnClick());
		e_xiangqing.setOnClickListener(new AlermOnClick());
		//e_method=(TextView)this.findViewById(R.id.e_method);
		
		if(!ShareData.userName.equals("cealadmin"))
		{
			e_jishialerm.setVisibility(View.GONE);
		}
		Intent intent=getIntent();
		id=intent.getStringExtra("id");
		
		params.add(new BasicNameValuePair("userName",ShareData.userName));
		params.add(new BasicNameValuePair("passWord",ShareData.userPass));
		params.add(new BasicNameValuePair("Id",id));
		InputStream	input = PublicHttpActivity.getJSONData(url+"/GetStationById" ,params);
		alerm=ReadFile.getAlermStation(input);
		if(alerm!=null){
			e_stationname.setText(alerm.getStationName());
			e_method.setText(alerm.getSnedMethod());
			e_invaltime.setText(String.valueOf(alerm.getIntervalTime())+"分钟");
			e_sendtotal.setText(String.valueOf(alerm.getSendTotal())+"次");
		}
		
	}
	
	 class AlermOnClick implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId()){
			case R.id.e_jishialerm: 
				Intent intent=new Intent(AlermStationEditActivity.this,AlermJiShiActivity.class);
				startActivity(intent);
				break;
			case R.id.e_dingshialerm: 
				Intent intent2=new Intent(AlermStationEditActivity.this,AlermDingShiActivity.class);
				intent2.putExtra("id", id);
				startActivity(intent2);
				break;
			case R.id.e_xitongalerm: 
				Intent intent3=new Intent(AlermStationEditActivity.this,AlermXiTongActivity.class);
				intent3.putExtra("id", id);
				startActivity(intent3);
				break;
			case R.id.e_shangxiaalerm: 
				Intent intent4=new Intent(AlermStationEditActivity.this,Alerm_ShangXiaActivity.class);
				intent4.putExtra("id", id);
				startActivity(intent4);
				break;
			case R.id.e_jiwenalerm: 
				Intent intent5=new Intent(AlermStationEditActivity.this,AlermJiWenActivity.class);
				intent5.putExtra("id", id);
				startActivity(intent5);
				break;
			case R.id.e_guanyualerm: 
				Intent intent6=new Intent(AlermStationEditActivity.this,AlermAboutActivity.class);
				intent6.putExtra("id", id);
				startActivity(intent6);
				break;
			case R.id.e_xiangqing: 
				Intent intent7=new Intent(AlermStationEditActivity.this,AlermMessageActivity.class);
				intent7.putExtra("id", id);
				intent7.putExtra("meth", "1");
				startActivity(intent7);
				break;
			}
		}
	}
	 
	 public void UpdateStation(View v){
		 Intent intent=new Intent(AlermStationEditActivity.this,AlermStationUpdateActivity.class);
		 intent.putExtra("alerm",alerm);
		 startActivity(intent);
		}
	
	public void goBack(View v){
		this.finish();
	}
}
