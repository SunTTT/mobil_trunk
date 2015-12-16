package com.mobile.appPre.sms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextPaint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.longhui.entity.SiteDataList;
import com.longhui.entity.SiteListLib;
import com.longhui.service.ReadFile;
import com.mobile.appPre.PublicApplication;
import com.mobile.appPre.R;
import com.mobile.publiclass.OnChangedListener;
import com.mobile.publiclass.PublicHttpActivity;
import com.mobile.publiclass.SlipButton;

public class HsyjActivity extends Activity {
	private SlipButton mybutton ;
	String[] listitem_N ;
	String[] listitem_id ;
	private ListView listviewselect1, listviewselect2;
	private PublicApplication App = new PublicApplication();
	
	private List<NameValuePair> params = new ArrayList<NameValuePair>();
	private String stationId  ,stationName ;
	
	private String PhoneNumber , Checked,AlarmSetting ;
	
	private TextView station_N_textview ;
	SiteListLib sitedata = new SiteListLib();
	private static Map<String, Object> tabTitle ;
	private String[] sensor_name_1 ;//定义传感器数组存放解析得到的传感器
	private String[] sensor_name_2 ;//各传感器对应的标签名（tag）、传值的时候用到
	private List<SiteDataList> SiteDataList;
	private List<String[]>   list=new ArrayList<String[]>();
	private String[] latest_datas_1;//最新值
	private String[] lowerdata_1 ;//下限值
	private String[] higerdata_1 ;//上限值
	List<Map<String, String>> x = new ArrayList<Map<String, String>>();//要循环的list
	List<Map<String, String>> listdata = new ArrayList<Map<String, String>>();//要循环的list
	
	private EditText teledittext ,lowerdata ,higerdata;
	private Button addtel,setButton,cancleButton;
	
	private EditText[] lower ; //下限值的文本框数组
	private EditText[] higer ;//上限值文本框数组
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hsyjpage);
		
		Intent intent = getIntent();
		Bundle data = intent.getBundleExtra("data");
		stationId = data.getString("stationId");
		stationName = data.getString("stationName");
		
		findViewById();
		station_N_textview.setText(stationName);
		
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
		int ii= 0 ; 
		 while (iter.hasNext()) {  
	            Map.Entry entry = (Map.Entry) iter.next();  
	            Object key = entry.getKey();  
	            Object val = entry.getValue();
	            sensor_name_1[ii] = (String)val;
	            sensor_name_2[ii] = (String)key;
	            ii++;
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
		//调用getListValues方法、得到封装了item信息的数据源
		getListValues();

		
		/*
		

		listviewselect1 = (ListView) findViewById(R.id.listview2);
		listitem_N = new String[]{"15811289858","15811289858","15811289858","15811289858","15811289858"};
		listitem_id = new String[]{"0","1","2","3","4"};
		
		List<Map<String, String>> listdata = new ArrayList<Map<String, String>>();//要循环的list
		
		for (int i = 0; i < listitem_N.length; i++) {
			Map<String, String> v = new HashMap<String, String>();
			
	        v.put("sensor_name",listitem_N[i]);
	        listdata.add(v);
		}*/
		SimpleAdapter listadapter = new SimpleAdapter(this, x, R.layout.cgq_list_item, 
        		new String[]{"sensor_name","lowerdata","higerdata"},new int[]{R.id.sensor_name,R.id.lowerdata,R.id.higerdata});
		listviewselect1.setAdapter(listadapter);
		//显示listview列表中的内容	
		//定义listview列表的高位0；
		int totalHeight = 0;
		for (int j = 0; j <listadapter.getCount(); j++) {
					
				//layout.addView(myListview,params);
					
					
				View listItem = listadapter.getView(j, null, listviewselect1);
				listItem.measure(0, 0);//起始位置
				totalHeight += listItem.getMeasuredHeight(); //计算出总列表总高度 
		} 
		//将info_listview用scrollview显示
		ViewGroup.LayoutParams layoutparams = listviewselect1.getLayoutParams();
		layoutparams.height = totalHeight+listviewselect1.getDividerHeight()*(listadapter.getCount()-1)-75;
		
		((MarginLayoutParams)layoutparams).setMargins(10, 10, 10, 10);
		listviewselect1.setLayoutParams(layoutparams);
		
		
		//循环得出是否设置报警、是否快速报警和报警电话值
		 for (int j = 0; j < SiteDataList.size(); j++) {
			Checked = SiteDataList.get(j).getChecked();
			PhoneNumber = SiteDataList.get(j).getPhoneNumber();
			
		}
		 
		 if (Checked.equals("1")) {
		    	mybutton.IsOpen =true ;
		    	
			}else {
				mybutton.IsOpen = false ;
			}
		 if (PhoneNumber.equals("null")) {
		    	PhoneNumber ="";
			}else {
				
			    listitem_N  = PhoneNumber.split(",");
				/*List<Map<String, String>> listdata = new ArrayList<Map<String, String>>();//要循环的list*/			
			    listdata.clear();
				for (int i = 0; i < listitem_N.length; i++) {
					Map<String, String> v = new HashMap<String, String>();
					/*v.put("textviewid", listitem_N[i]);*/
			        v.put("textviewname",listitem_N[i]);
			        listdata.add(v);
				}
				SimpleAdapter listadapter1 = new SimpleAdapter(this, listdata, R.layout.tel_list_item, 
		        		new String[]{"textviewname"},new int[]{R.id.mainlist_textveiw});
				listviewselect2.setAdapter(listadapter1);
				
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
			
			addtel.setOnClickListener(new OnClickListener() {
				
				public void onClick(View arg0) {
					
					
					LayoutInflater inflater = getLayoutInflater();
					final View layout = inflater.inflate(R.layout.addtelview,
						     (ViewGroup) findViewById(R.id.dialog));
					
					//给弹出的Dialog确定按钮添加监听
					new AlertDialog.Builder(HsyjActivity.this).setTitle("添加电话号").setView(layout)
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
									/*v.put("textviewid", listitem_N[i]);*/
							        v.put("textviewname",teledittextString);
							        listdata.add(v);
								 	
									SimpleAdapter listadapter = new SimpleAdapter(HsyjActivity.this, listdata, R.layout.tel_list_item, 
							        		new String[]{"textviewname"},new int[]{R.id.mainlist_textveiw});
									listviewselect2.setAdapter(listadapter);
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
			
		 
			listviewselect2.setOnItemClickListener(new OnItemClickListener() {

				
				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
						long arg3) {
					// TODO Auto-generated method stub
					final int i = arg2 ;
					new AlertDialog.Builder(HsyjActivity.this)
					
					.setTitle("温馨提醒")
					.setMessage("删除选中电话号")
					.setPositiveButton("确定", new DialogInterface.OnClickListener() {
						
						public void onClick(DialogInterface dialog, int which) {
						
							
							listdata.remove(i);
							SimpleAdapter listadapter = new SimpleAdapter(HsyjActivity.this, listdata, R.layout.tel_list_item, 
					        		new String[]{"textviewname"},new int[]{R.id.mainlist_textveiw});
							listviewselect2.setAdapter(listadapter);
							
							
							
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
					InputStream	input1 = PublicHttpActivity.getJSONData(App.UpdateAlarmSetting ,params);
					
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
				    	Toast.makeText(HsyjActivity.this, "设置成功！", Toast.LENGTH_SHORT).show();
					}else {
						Toast.makeText(HsyjActivity.this, "设置失败！", Toast.LENGTH_SHORT).show();
					}
				    
				    
					
				}
			});
			
			cancleButton.setOnClickListener(new OnClickListener() {
				
				
				public void onClick(View v) {
					// TODO Auto-generated method stub
					HsyjActivity.this.finish();
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
		
		
		getlower();
		gethiger();
		
		String data = "" ;
		String listviewdata="";
		
		for (int i = 0; i < listviewselect1.getCount(); i++) {
		    //将循环取得各个文本框的值以"，"隔开赋给一个字符串
			
			data =lower[i].getText().toString().replace(" ", "")+"-"+higer[i].getText().toString().replace(" ", "");
			listviewdata +=  sensor_name_2[i]+":"+"'"+"'"+data+"'"+"'"+",";
			
		}
		
		AlarmSetting = "{" +"PhoneNumber"+":"+"'"+"'"+PhoneNumber+"'"+"'"+","+listviewdata+"Checked"+":"+"'"+"'"+Checked+"'"+"'"+"}";
		
		params.add(new BasicNameValuePair("stationId",stationId ));
		params.add(new BasicNameValuePair("AlarmSetting",AlarmSetting ));
		
		
		
		
	}
	
	
	
	//将循环出来的EditText放入到定义的两个EditText数组里面，相当于给每个文本框添加序号
		private EditText[] gethiger() {
			// TODO Auto-generated method stub
			//higerdata = (EditText) findViewById(R.id.higerdata);
			higer = new EditText[tabTitle.size()];
			for (int i =listviewselect1.getFirstVisiblePosition(); i < listviewselect1.getCount(); i++) {
				listviewselect1.getCount();
				higerdata = (EditText) listviewselect1.getChildAt(i).findViewById(R.id.higerdata);
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
			for (int i =listviewselect1.getFirstVisiblePosition(); i < listviewselect1.getCount(); i++) {
				//info_listview.invalidate();
				
				listviewselect1.getCount();
				
				lowerdata = (EditText) listviewselect1.getChildAt(i).findViewById(R.id.lowerdata);
				//String.valueOf(info_listview.getChildAt(i));
				 
				
				lower[i]=lowerdata;
			}
			
			return lower;
		}
		
	
	
	
	

	private List<Map<String, String>> getListValues(){
		
		for (int i = 0; i < sensor_name_1.length-2; i++) {
			Map<String, String> v = new HashMap<String, String>();
			v.put("sensor_name", sensor_name_1[i]);
			v.put("latest_datas", latest_datas_1[i]);
			v.put("lowerdata", lowerdata_1[i]);
			v.put("higerdata", higerdata_1[i]);
			x.add(v);
		}
		return x ;
	}

	private void findViewById() {
		// TODO Auto-generated method stub
		station_N_textview = (TextView) findViewById(R.id.set_stationName);
		TextPaint tp = station_N_textview.getPaint(); 
		tp.setFakeBoldText(true);
		listviewselect1 = (ListView) findViewById(R.id.listview2);
		listviewselect2 = (ListView) findViewById(R.id.listview1);
		mybutton = (SlipButton) findViewById(R.id.slipBtn);
		addtel = (Button) findViewById(R.id.addtel)	;
		setButton = (Button) findViewById(R.id.setbutton_hsyj);
		cancleButton = (Button) findViewById(R.id.canclebutton_hsyj);
	}

}

