package com.mobile.appPre.sms ;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.mobile.appPre.PublicApplication;
import com.mobile.appPre.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextPaint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.longhui.entity.MrtxList;
import com.longhui.service.ReadFile;

import com.mobile.publiclass.OnChangedListener;
import com.mobile.publiclass.PublicHttpActivity;
import com.mobile.publiclass.SlipButton;


public class MrtxActivity extends Activity {
	private SlipButton mybutton ;
	String[] listitem_N ;
	String[] listitem_id ;
	private ListView listviewselect ;
	private TextView station_N_textview ,mrfstimeEditText;
	private Button addtel , setButton ,cancleButton ;
	private EditText teledittext ;

	
	
	private PublicApplication App = new PublicApplication();
	
	private List<NameValuePair> params = new ArrayList<NameValuePair>();
	private String stationId  ,stationName ;
	MrtxList mrtxList = new MrtxList();
	
	List<Map<String, String>> listdata = new ArrayList<Map<String, String>>();//要循环的list
	private String PhoneNumber , Checked ,AlertTime ,AlarmEveryDay ;

	
	//默认生成系统当前时间
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");		
	String str=sdf.format(new Date());
	/*int year=Integer.parseInt(str.substring(0, 4));
	int month=Integer.parseInt(str.substring(5,7))-1;
	int day=Integer.parseInt(str.substring(8,10));*/
	int hour=Integer.parseInt(str.substring(11,13));
	int minute=Integer.parseInt(str.substring(14,16));
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mrtxpage);
		
		Intent intent = getIntent();
		Bundle data = intent.getBundleExtra("data");
		stationId = data.getString("stationId");
		stationName = data.getString("stationName");
		
		
		findViewById();
		
		
		
	    station_N_textview.setText(stationName);
	    
		params.add(new BasicNameValuePair("stationId",stationId ));
	    //访问服务器并返回一个流
	    InputStream	input = PublicHttpActivity.getJSONData(App.GetAlarmEveryDay ,params);
	    mrtxList = ReadFile.getMrtxList(input);
	    
	    
	    
	    AlertTime = mrtxList.getAlertTime();
	    if (AlertTime.equals("null")) {
	    	mrfstimeEditText.setText("");
		}else {
			mrfstimeEditText.setText(AlertTime);
		}
	    
	    Checked = mrtxList.getChecked() ;
	    if (Checked.equals("1")) {
	    	mybutton.IsOpen =true ;
	    	
		}else {
			mybutton.IsOpen = false ;
		}
	    
	    PhoneNumber = mrtxList.getPhoneNumber();
	    if (PhoneNumber.equals("null")) {
	    	PhoneNumber ="";
		}else {
			PhoneNumber = mrtxList.getPhoneNumber() ;
		    listitem_N  = PhoneNumber.split(",");
			/*List<Map<String, String>> listdata = new ArrayList<Map<String, String>>();//要循环的list*/			
		    listdata.clear();
			for (int i = 0; i < listitem_N.length; i++) {
				Map<String, String> v = new HashMap<String, String>();
				/*v.put("textviewid", listitem_N[i]);*/
		        v.put("textviewname",listitem_N[i]);
		        listdata.add(v);
			}
			SimpleAdapter listadapter = new SimpleAdapter(this, listdata, R.layout.tel_list_item, 
	        		new String[]{"textviewname"},new int[]{R.id.mainlist_textveiw});
			listviewselect.setAdapter(listadapter);
			
		}
	    
		

	    //开启按钮
		//获得指定控件
		mybutton.SetOnChangedListener(new OnChangedListener() {
			
			
			public void OnChanged(boolean CheckState) {
				// TODO Auto-generated method stub
				if(CheckState){
					Log.i("CheckState", CheckState+"");
					Checked = 1+"";
					mybutton.IsOpen =true ;
					
				}else{
					Checked = 0+"";
					Log.i("CheckState", CheckState+"");
					mybutton.IsOpen =false ;
				}
			}
		});
		
		mrfstimeEditText.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new TimePickerDialog(MrtxActivity.this, new TimePickerDialog.OnTimeSetListener() {
					
					public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
						// TODO Auto-generated method stub
						if (minute<10) {
							mrfstimeEditText.setText(hourOfDay+":0"+minute);
						}else {
							mrfstimeEditText.setText(hourOfDay+":"+minute);
						}
						
					}
				}, hour, minute, true).show();
			}
		});
		
		
		
		addtel.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				
				
				LayoutInflater inflater = getLayoutInflater();
				final View layout = inflater.inflate(R.layout.addtelview,
					     (ViewGroup) findViewById(R.id.dialog));
				
				//给弹出的Dialog确定按钮添加监听
				new AlertDialog.Builder(MrtxActivity.this).setTitle("添加电话号").setView(layout)
			     .setPositiveButton("确定", new DialogInterface.OnClickListener(){

					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						
						 teledittext = (EditText) layout.findViewById(R.id.teledittext);
						 String teledittextString = teledittext.getText().toString().replaceAll(" ", "") ;
						 
						
						  //num = teledittext.getText().toString().replaceAll(" ", "") ;
						 //如果原先没有设置电话号码则telnum是空，设置的电话号码不为空，则将设置的号码直接赋值给telnum
						 if (teledittextString.isEmpty()) {

								Log.i("ddddddddd", "没有设置号码");
							} else {
								Map<String, String> v = new HashMap<String, String>();
								/*
								 * v.put("textviewid",
								 * listitem_N[i]);
								 */
								v.put("textviewname",
										teledittextString);
								listdata.add(v);

								SimpleAdapter listadapter = new SimpleAdapter(
										MrtxActivity.this,
										listdata,
										R.layout.tel_list_item,
										new String[] { "textviewname" },
										new int[] { R.id.mainlist_textveiw });
								listviewselect.setAdapter(listadapter);
							}
						 	
						 
						 
					}

					/*private EditText findViewById(int teledittext) {
						// TODO Auto-generated method stub
						back_pic null;
					}*/
			    	 
			     })
			     .setNegativeButton("取消", null).show();
				
				 
			}
		});
		
	

		listviewselect.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				
				final int i = arg2 ;
				new AlertDialog.Builder(MrtxActivity.this)
				
				.setTitle("温馨提醒")
				.setMessage("删除选中电话号")
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
					
						
						listdata.remove(i);
						SimpleAdapter listadapter = new SimpleAdapter(MrtxActivity.this, listdata, R.layout.tel_list_item, 
				        		new String[]{"textviewname"},new int[]{R.id.mainlist_textveiw});
						listviewselect.setAdapter(listadapter);
						
						
						
					}
				})
				.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						
					}
				})
				.show();
			}
		});
		
		
		setButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				getparams();
				InputStream	input1 = PublicHttpActivity.getJSONData(App.EveryDayAlarm ,params);
				
				BufferedReader reader = new BufferedReader(new InputStreamReader(input1));   
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
			    String sbstring = sb+"";
			    if (sbstring.contains("设置成功")) {
			    	Toast.makeText(MrtxActivity.this, "设置成功！", Toast.LENGTH_SHORT).show();
				}else {
					Toast.makeText(MrtxActivity.this, "设置失败！", Toast.LENGTH_SHORT).show();
				}
			    
			    
				
			}
		});
		
		cancleButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MrtxActivity.this.finish();
			}
		});
	
		
		
		
		
	}//oncreate方法结束

	protected void getparams() {
		params.clear();
		
		
		PhoneNumber = "";
		for (int i=0;i<listdata.size();i++)
		{
		       Map  map=(Map)listdata.get(i);

		        Iterator iterator = map.keySet().iterator();
		            while (iterator.hasNext())
		            {
		                String key = (String) iterator.next();
		                if (key.equals("textviewname")) {
		                	Object object = map.get(key);
		                	PhoneNumber += ","+object;
						}
		                
		            }

		}
		if (PhoneNumber!="") {
			PhoneNumber = PhoneNumber.substring(1);
		}
		
		
		AlertTime = mrfstimeEditText.getText().toString();
		AlarmEveryDay = "{" +"PhoneNumber"+":"+"'"+"'"+PhoneNumber+"'"+"'"+","+"AlertTime"+":"+"'"+"'"+AlertTime+"'"+"'"+","+"Checked"+":"+"'"+"'"+Checked+"'"+"'"+"}";
		
		params.add(new BasicNameValuePair("stationId",stationId ));
		params.add(new BasicNameValuePair("AlarmEveryDay",AlarmEveryDay ));
		
	}

	private void findViewById() {
		station_N_textview = (TextView) findViewById(R.id.set_stationName);
		TextPaint tp = station_N_textview.getPaint(); 
		tp.setFakeBoldText(true);
		listviewselect = (ListView) findViewById(R.id.listview1);
		mybutton =(SlipButton) findViewById(R.id.slipBtn);
		mrfstimeEditText  = (TextView) findViewById(R.id.mrtxtime);
		addtel = (Button) findViewById(R.id.addtel);
		setButton = (Button) findViewById(R.id.setbutton_mrtx);
		cancleButton = (Button) findViewById(R.id.canclebutton_mrtx);
		
	}
	
	
	
}
