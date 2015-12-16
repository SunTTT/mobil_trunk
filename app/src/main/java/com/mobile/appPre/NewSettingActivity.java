package com.mobile.appPre;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.longhui.entity.SiteDataList;
import com.longhui.service.ReadFile;
import com.mobile.appPre.R;
import com.mobile.publiclass.Asynctask;
import com.mobile.publiclass.HttpThread;
import com.mobile.publiclass.PublicHttpActivity;


public class NewSettingActivity extends Activity {

	
	private TextView sensor_name,latest_datas,setting_time,setTime,setstationName;	
	
	private EditText lowerdata;
	private EditText higerdata,teledittext ;
	private ListView info_listview ;
	private Button deleteTel,addTel ;
	
	private static String stationId,stationName ;
	private PublicApplication App = new PublicApplication();
	private com.longhui.entity.SiteListLib sitedata =null ;
	private List<String[]>   list=new ArrayList<String[]>();
	
	
	private static Map<String, Object> tabTitle ;
	private List<SiteDataList> SiteDataList;
	private RadioGroup rGroup1,rGroup2,rGroup3;
	private RadioButton radiobutton1,radiobutton2,radiobutton3,radiobutton4,radiobutton5,radiobutton6 ;
	private TextView Telephone_nums;
	private ImageButton tijiao;
	private String checked = null ;
	private String num ="";
	private String set_time = "";
	private String telnum = "";
	private String ksbj = null ;
	private String dsbj = "";
	private String lastTime ;
	
	private static int mYear; 
	private static int mMonth; 
	private static int mDay;
	
	
	private List<NameValuePair> params = new ArrayList<NameValuePair>();
	private String[] sensor_name_1 ;//定义传感器数组存放解析得到的传感器
	private String[] sensor_name_2 ;//各传感器对应的标签名（tag）、传值的时候用到
	private String[] latest_datas_1;//最新值
	private String[] lowerdata_1 ;//下限值
	private String[] higerdata_1 ;//上限值
	private int mHour; 
	private int mMinute; 
	
	List<Map<String, String>> x = new ArrayList<Map<String, String>>();//要循环的list
	//private static Map<String, Object> info_items = null;
	private EditText[] lower ; //下限值的文本框数组
	private EditText[] higer ;//上限值文本框数组
	private String AlarmSetting= "";
	
	private String[] Tel_delete;
	private boolean[] Tel_selected ;
	private boolean istj = true;
	
	private static final String NAMESPACE ="http://tempuri.org/"; // "http://202.196.80.146:86/WebService.asmx/";  
	private static final String URL = "http://202.196.80.146:86/WebService213.asmx";
	private String METHOD_NAME = "UpdateAlarmSetting"; // 函数名 
	HttpThread thread=null; //线程对像 
	boolean data=false;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//全屏展示本窗口，无标题
		setContentView(R.layout.waring_info_new);
		setTitle("报警设置");
		finds();
		
		
		
		//取得当前时间
		//设置时间为中国
	    Calendar c = Calendar.getInstance(Locale.CHINA);
	    mYear = c.get(Calendar.YEAR); //获取当前年份 
        mMonth = c.get(Calendar.MONTH);//获取当前月份 
        mDay = c.get(Calendar.DAY_OF_MONTH);//获取当前月份的日期号码
		
		
		
		
		//得到从前面Activity中传来的数据
		Intent getintent = getIntent();
		
	    Bundle getdata = getintent.getBundleExtra("data");
	    
	    stationName = getdata.getString("stationName");
	    stationId =  getdata.getString("stationId");
	    //将数据封装到params里面
	    params.add(new BasicNameValuePair("stationId",stationId ));
	    
	    //访问服务器并返回一个流
	    InputStream	input = PublicHttpActivity.getJSONData(App.GetAlarmSetting ,params);
	    //解析xml文件得到sitedata类
		sitedata = ReadFile.getSiteListLib(input);
		
		//从sitedata中取得传感器的map对象
		tabTitle=sitedata.getTitle();
		
	    //迭代将map中的value值取出放入传感器数组中
		Iterator iter = tabTitle.entrySet().iterator();
		sensor_name_1 = new String[tabTitle.size()];
		sensor_name_2 = new String[tabTitle.size()];
		int i = 0 ; 
		 while (iter.hasNext()) {  
	            Map.Entry entry = (Map.Entry) iter.next();  
	            Object key = entry.getKey();  
	            Object val = entry.getValue();
	            sensor_name_1[i] = (String)val;
	            sensor_name_2[i] = (String)key;
	            i++;
	     }       
	
		 //将最新值、下限值和上限值取出并放入个自数组中
		 SiteDataList = sitedata.getDetails();
		 for (int j = 0; j < SiteDataList.size(); j++) {
			 
			 list = SiteDataList.get(j).getList(); 
		}
		 String[] a = new String[3];
		 latest_datas_1=new String[list.size()];//最新值
		 lowerdata_1= new String[list.size()] ;//下限值
		 higerdata_1= new String[list.size()] ;//上限值
		 for (int j = 0; j < list.size(); j++) {
			 a=list.get(j);
			 if (a.length==3) {
				latest_datas_1[j] = a[0];
				lowerdata_1[j] = a[1];
				higerdata_1[j] = a[2];
			}
			
			
		}
		 
		 
		/* Handler handler = new Handler();
		 Runnable updateTread = new Runnable() {
			
			public void run() {
				// TODO Auto-generated method stub
				ProgressDialog pBar = new ProgressDialog(NewSettingActivity.this);
				pBar.setMessage("正在访问网络，请稍后……");   
				pBar.show();
			}
		 };
		 handler.post(updateTread);*/
		 
		 
		 
		 		 
		//调用getListValues方法、得到封装了item信息的数据源
		getListValues();
		
		//自定义adapter
		SimpleAdapter adapter = new SimpleAdapter(this,
				x, //item参数来源
				R.layout.info_bottom, //item布局
				new String[]{"sensor_name","latest_datas","lowerdata","higerdata"}, //数组与Item对应名称
				new int[]{R.id.sensor_name,R.id.latest_datas,R.id.lowerdata,R.id.higerdata});//item各子项的id
				
		info_listview.setAdapter(adapter);
		/*
		if (adapter == null) {

            back_pic;

         }*/
		
		
	  //显示listview列表中的内容	
		//定义listview列表的高位0；
		int totalHeight = 0;
        for (int j = 0; j <adapter.getCount(); j++) {
			
			//layout.addView(myListview,params);
			
			
			 View listItem = adapter.getView(j, null, info_listview);
			 listItem.measure(0, 0);//起始位置
			 totalHeight += listItem.getMeasuredHeight(); //计算出总列表总高度 
		} 
        //将info_listview用scrollview显示
        ViewGroup.LayoutParams layoutparams = info_listview.getLayoutParams();
        layoutparams.height = totalHeight+info_listview.getDividerHeight()*(adapter.getCount()-1);
        info_listview.setLayoutParams(layoutparams);
        
      //显示电话号、和判断是否启用系统
		radiobutton1 = (RadioButton) findViewById(R.id.radiobutton1);
		 radiobutton2 = (RadioButton) findViewById(R.id.radiobutton2);
		 radiobutton3 = (RadioButton) findViewById(R.id.radiobutton3);
		 radiobutton4 = (RadioButton) findViewById(R.id.radiobutton4);
		 radiobutton5 = (RadioButton) findViewById(R.id.radiobutton5);
		 radiobutton6 = (RadioButton) findViewById(R.id.radiobutton6);
		 
		 
		 //循环得出是否设置报警、是否快速报警和报警电话值
		 for (int j = 0; j < SiteDataList.size(); j++) {
			checked = SiteDataList.get(j).getChecked();
			telnum = SiteDataList.get(j).getPhoneNumber();
			
			if (SiteDataList.get(j).getKsbj().equals("\n")) {
				ksbj = "";
			}else {
				ksbj = SiteDataList.get(j).getKsbj();
			}
			dsbj = SiteDataList.get(j).getDsbj();
			set_time = SiteDataList.get(j).getTime();
		}
		 setting_time = (TextView) findViewById(R.id.setting_time);
		 setstationName = (TextView) findViewById(R.id.set_stationName);
		 setTime = (TextView) findViewById(R.id.set_leasttime);
		 
		 setstationName.setText(stationName);
		 setTime.setText(set_time);
		 
		 
		 
		 //将得出的值判断，并确定是哪个按钮被选中
		 rGroup1 = (RadioGroup) findViewById(R.id.myradiogroup1);
		 rGroup2 = (RadioGroup) findViewById(R.id.myradiogroup2);
		 rGroup3 = (RadioGroup) findViewById(R.id.myradiogroup3);
		 
		 
		 if (checked.equals("1")) {//是否启用了报警系统
			 radiobutton2.setChecked(true);//否
			
		}else {
			radiobutton1.setChecked(true);//是
		}
		 
		if (ksbj.equals("1")) {//是否启用了快速报警设置
			radiobutton4.setChecked(true);//否
		} else {
			radiobutton3.setChecked(true);//是
		}
		
		if (dsbj.equals("\n")) {
			 radiobutton6.setChecked(true);//否
		}else {
			 radiobutton5.setChecked(true);//是
			 setting_time.setText(dsbj);
		}
		
		//将得到的电话号码传入电话号码的文本框中
		//如果有已经设置号码，则将号码显示在setting_time上面
		Telephone_nums = (TextView) findViewById(R.id.mautoTx);
		//Telephone_nums.getText().clear();//清除缓存
		//Telephone_nums.getText().append(num);
		if (telnum.equals("\n")) {
			//Telephone_nums.setText("");
			telnum="";
		}else {
			Telephone_nums.setText(telnum);
			
		}
		Log.i("telnum", telnum);
		
		
		
		
		setting_time.setText(dsbj);
		
		
		
		
		//当编辑完文本内容并点提交按钮时将获得的数据传给服务器
		
				//添加电话号码
				addTel = (Button) findViewById(R.id.addtel);
				
				addTel.setOnClickListener(new OnClickListener() {
					
					public void onClick(View arg0) {
						
						
						LayoutInflater inflater = getLayoutInflater();
						final View layout = inflater.inflate(R.layout.addtelview,
							     (ViewGroup) findViewById(R.id.dialog));
						
						//给弹出的Dialog确定按钮添加监听
						new AlertDialog.Builder(NewSettingActivity.this).setTitle("添加电话号").setView(layout)
					     .setPositiveButton("确定", new DialogInterface.OnClickListener(){

							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								
								 teledittext = (EditText) layout.findViewById(R.id.teledittext);
								 String teledittextString = teledittext.getText().toString().replaceAll(" ", "") ;
								 
								  //num = teledittext.getText().toString().replaceAll(" ", "") ;
								 //如果原先没有设置电话号码则telnum是空，设置的电话号码不为空，则将设置的号码直接赋值给telnum
								
								 if (telnum=="") {
									 if (teledittextString.length()!=0) {
										 telnum = teledittextString ;
									}
									 
								}else { //如果原来已经设置了电话号码，设置的电话号码不为空，则在telnum后加上“，”后再接上设置的号码
									if (teledittextString.length()!=0) {
										telnum = telnum +","+teledittextString ;
								    }
								}
								  
								 Telephone_nums = (TextView) findViewById(R.id.mautoTx);
								  Telephone_nums.setText(telnum);
								  Telephone_nums.setTextColor(R.color.black);
								 // Telephone_nums.requestFocus();
							}

							/*private EditText findViewById(int teledittext) {
								// TODO Auto-generated method stub
								back_pic null;
							}*/
					    	 
					     })
					     .setNegativeButton("取消", null).show();
						
						 
					}
				});
				
				//删除电话号码
				deleteTel = (Button) findViewById(R.id.deletetel);
				deleteTel.setOnClickListener(new OnClickListener() {
					
					public void onClick(View v) {
						// TODO Auto-generated method stub
						
						Tel_delete= telnum.split(",");
						Tel_selected = new boolean[Tel_delete.length];
						
						new AlertDialog.Builder(NewSettingActivity.this).setTitle("号码列表").setIcon(android.R.drawable.ic_dialog_info)
						.setMultiChoiceItems(Tel_delete, Tel_selected, new OnMultiChoiceClickListener(){
							
							public void onClick(DialogInterface dialog, int which,
									boolean isChecked) {
								// TODO Auto-generated method stub
								Tel_selected[which] = isChecked ;
							}
						})
						.setPositiveButton("删除",  new DialogInterface.OnClickListener() {
							
							public void onClick(DialogInterface dlg, int sumthin) { 
								// do nothing ?C it will close on its own 
								
								telnum = "";
								for(int i=0; i<Tel_selected.length; i++) {   
		                            
									if (Tel_selected[i] == false) {
										telnum += ","+Tel_delete[i];
									}
								} 
								telnum=telnum.replaceFirst(",", "");
								Telephone_nums.setText(telnum);
                                Telephone_nums.setTextColor(R.color.black);

							}
							})
						.setNegativeButton("取消", null).show();
					}
				});
				
				//num = Telephone_nums.getText().toString().replace(" ", "");
		
		//设置报警时间
		
				radiobutton5.setOnClickListener(new OnClickListener() {
					
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						//设置时间
						new TimePickerDialog(NewSettingActivity.this, new TimePickerDialog.OnTimeSetListener() {
							
							public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
								// TODO Auto-generated method stub
								if (minute<10) {
									dsbj = hourOfDay+":0" +minute ;
								}else {
									dsbj = hourOfDay+":" +minute ;
								}
								
								setting_time.setText(dsbj);
								setting_time.setTextColor(R.color.black);
								setting_time.setVisibility(View.VISIBLE);
							}
						},  mHour, mMinute, true).show();
					}
				});
				radiobutton6.setOnClickListener(new OnClickListener() {
					
					public void onClick(View v) {
						// TODO Auto-generated method stub
						 dsbj="";
						 setting_time.setText(dsbj);
						 
						 setting_time.setVisibility(View.INVISIBLE);
					}
				});
					
		
		
		tijiao=(ImageButton) findViewById(R.id.tijiao_view);
		tijiao.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (istj==true) {
					istj = false ;
					info_listview.invalidate();
					getValues();
					
					Log.i("newsettingactivity/params", ""+params);
					//提交输入编辑后的params到相应的接口
					
					String path = App.UpdateAlarmSetting ;
					//PublicHttpActivity.HttpPost(path, params);
					

					try {
					   //httpPost方式发送请求
						HttpEntity requestHttpEntity = new UrlEncodedFormEntity(params);
						//String baseurl = "http://202.196.80.146:86/post.aspx"+"?id="+stationId+"&AlarmSetting={"+AlarmSetting+"}"+"";
						HttpPost  httpPost = new HttpPost(path);
						httpPost.setEntity(requestHttpEntity);
						HttpClient httpClient = new DefaultHttpClient();
						InputStream inputStream = null ;
						
						try {
							HttpResponse httpResponse = httpClient.execute(httpPost);
							HttpEntity httpentity = httpResponse.getEntity();
							inputStream = httpentity.getContent();
							BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
							String result = "";
							String line ="";
							Toast.makeText(NewSettingActivity.this,"设置参数成功！！" , Toast.LENGTH_SHORT).show();
							
							thread=new HttpThread(handlerwdy);
							String url=URL;//webserivce地址
							
							String nameSpace = NAMESPACE;
							String methodName = METHOD_NAME;
							while((line=reader.readLine())!=null){
								result = result +line ;
								//Toast.makeText(NewSettingActivity.this,"设置参数成功！！" , Toast.LENGTH_SHORT).show();
								
							}
							/*Asynctask asynctask = new Asynctask();
							asynctask.execute();*/
							thread.doStart(url,nameSpace,methodName, params ,NewSettingActivity.this);   //启动线程
							
							Log.i("NewSettingActivity/result", result);
							params.clear();
						}  catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}finally{
							try {
								inputStream.close();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				}
				
				
			}

			

			
		});
		
		
	}
	
	
	

	//生成消息对象   
		Handler handlerwdy=new Handler(){ 
			
			public void handleMessage(Message m){ 
				
	         
				switch(m.what){     
				case 1: 
					data= m.getData().getBoolean("data"); //从消息在拿出数据
					if (data) {
						 istj = true ;
						 
					}else {
						//datadown.setBackgroundResource(R.drawable.down_1);
						istj = true ;
					}
					
				}  
				

		 }
			
	   };
	

	
	//获取各个文本框的值，并且将值封装到params里面并返回。
	private List<NameValuePair> getValues(){
		
		getlower();
		gethiger();
		
		String data = "" ;
		String listviewdata="";
		
		for (int i = 0; i < info_listview.getCount(); i++) {
		    //将循环取得各个文本框的值以"，"隔开赋给一个字符串
			
			data =lower[i].getText().toString().replace(" ", "")+"-"+higer[i].getText().toString().replace(" ", "");
			listviewdata +=  sensor_name_2[i]+":"+"'"+"'"+data+"'"+"'"+",";
			
		}
		
		
		
		//设置下面选项	
		if (radiobutton1.isChecked()) {
			checked = "0";
		}else if (radiobutton2.isChecked()){
			checked = "1";
		}
		if (radiobutton3.isChecked()) {
			ksbj ="0";
		}else if(radiobutton4.isChecked()){
			ksbj="1";
		}
		//将字符串封装到params里面并返回params。
		
		
		lastTime = mYear+"-"+(mMonth+1)+"-"+mDay ;
		AlarmSetting= "{"+listviewdata +"PhoneNumber"+":"+"'"+"'"+telnum+"'"+"'"+","+"Checked"+":"+"'"+"'"+checked+"'"+"'"+","+"Ksbj"+":"+"'"+"'"+ksbj+"'"+"'"+","+"lastTime"+":"+"'"+"'"+lastTime+"'"+"'"+","+"Dsbj"+":"+"'"+"'"+dsbj+"'"+"'"+"}";
		
		params.add(new BasicNameValuePair("AlarmSetting", AlarmSetting));
		Log.i("NewSettingActivity/tijiao/params", ""+params);
		return params;
		
	}
	
	//将循环出来的EditText放入到定义的两个EditText数组里面，相当于给每个文本框添加序号
	private EditText[] gethiger() {
		// TODO Auto-generated method stub
		//higerdata = (EditText) findViewById(R.id.higerdata);
		higer = new EditText[tabTitle.size()];
		for (int i =info_listview.getFirstVisiblePosition(); i < info_listview.getCount(); i++) {
			info_listview.getCount();
			higerdata = (EditText) info_listview.getChildAt(i).findViewById(R.id.higerdata);
			higer[i]=higerdata;
		}
		return higer;
	}
	
	
	
	
	private EditText[] getlower(){
		//lowerdata = (EditText) findViewById(R.id.lowerdata);
		
		lower = new EditText[tabTitle.size()];
		
		/*
		for (int i = info_listview.getFirstVisiblePosition();i <= info_listview.getLastVisiblePosition(); i++){
			lowerdata = (EditText) info_listview.getChildAt(i).findViewById(R.id.lowerdata);
			//findViewById(R.id.lowerdata).setFocusable(true);
			lower[i]=lowerdata;
		}*/
		for (int i =info_listview.getFirstVisiblePosition(); i < info_listview.getCount(); i++) {
			//info_listview.invalidate();
			
			info_listview.getCount();
			
			lowerdata = (EditText) info_listview.getChildAt(i).findViewById(R.id.lowerdata);
			//String.valueOf(info_listview.getChildAt(i));
			 
			
			lower[i]=lowerdata;
		}
		
		return lower;
	}
	
	//
	private List<Map<String, String>> getListValues(){
		
		for (int i = 0; i < sensor_name_1.length-5; i++) {
			Map<String, String> v = new HashMap<String, String>();
			v.put("sensor_name", sensor_name_1[i]);
			v.put("latest_datas", latest_datas_1[i]);
			v.put("lowerdata", lowerdata_1[i]);
			v.put("higerdata", higerdata_1[i]);
			x.add(v);
		}
		return x ;
	}
	
    //获取各个控件的id
	private void finds() {
		// TODO Auto-generated method stub
		
	
		sensor_name=(TextView) findViewById(R.id.sensor_name);	
		latest_datas = (TextView) findViewById(R.id.latest_datas);
		lowerdata = (EditText) findViewById(R.id.lowerdata);
		higerdata  = (EditText) findViewById(R.id.higerdata);
		
		info_listview = (ListView) findViewById(R.id.info_listview);
		
		
		
	}

	
	
	
	
	











	public void onStart(){
		super.onStart();
	}
	public void onStop(){
		super.onStop();
	}
	public void onPause(){
		super.onPause();
	}
	public void onResume(){
		super.onResume();
	}
	public void onRestart(){
		super.onRestart();
	}
	public void onDestory(){
		super.onDestroy();
	}
}
