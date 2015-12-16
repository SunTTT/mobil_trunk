/**
 * Copyright (C) 2009, 2010 SC 4ViewSoft SRL
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mobile.appPre;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.achartengine.chartdemo.demo.chart.AverageCubicTemperatureChart;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.text.InputType;
import android.text.TextPaint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.longhui.entity.Monitor;
import com.longhui.entity.Monitor1;
import com.longhui.service.ReadFile;
import com.mobile.appPre.R;
import com.mobile.publiclass.PublicHttpActivity;

public class ChartDemo extends Fragment {
	protected static final Context Context = null;

	private String[] keydata ;
	private IDemoChart[] mCharts = new IDemoChart[] {
		      new AverageCubicTemperatureChart()};

	private ListView chartlist;
	private String[] mMenuText;

	private String[] mMenuSummary;
	private TextView chart_stationName,chart_time,textcontent,data_date,ch_station;
		  
	private View view1,view ;
	private ImageView imageView ,worrimage;
	private Spinner spinner_serner ;
	private Button charttime,seachButton ;
	private List<NameValuePair> params = new ArrayList<NameValuePair>();
	private PublicApplication App = new PublicApplication();
    private static int mYear; 			
    private static int mMonth; 
	private static int mDay;
	private static String stationId = null;
	private static String stationName = null;
	private static String chartdate = null;
	private static Monitor monitor = null;
	private static Monitor1 monitor1 = null;
	private static Map<String, Object> tabTitle = null;
		
	private String key1 = null ;
	private ArrayAdapter<String> adapter;			
			
	LinearLayout layout1 ;
	private DatePicker dPicker;
	private Calendar calendar;

		  /** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		if (savedInstanceState != null)
		  return;
		Bundle getdata = getActivity().getIntent().getBundleExtra("data");
		stationId =  getdata.getString("stationId");
		stationName = getdata.getString("stationName");
		params.add(new BasicNameValuePair("stationId",stationId ));

		//设置时间为中国
		Calendar c = Calendar.getInstance(Locale.CHINA);
		mYear = c.get(Calendar.YEAR); //获取当前年份
		mMonth = c.get(Calendar.MONTH);//获取当前月份
		mDay = c.get(Calendar.DAY_OF_MONTH);//获取当前月份的日期号码
		String startstr1 = mYear+"-"+(mMonth+1)+"-"+mDay+" "+"00:00";
		String endtime1 = mYear+"-"+(mMonth+1)+"-"+mDay+" "+"23:59";
		params.add(new BasicNameValuePair("startTime",startstr1));
		params.add(new BasicNameValuePair("endTime", endtime1));
		params.add(new BasicNameValuePair("currentPage","1"));
		params.add(new BasicNameValuePair("pageSize", "144"));
//		InputStream	input = PublicHttpActivity.getJSONData(App.GetStationDataPath,params);
//		monitor = ReadFile.getFile(input);
//
//		InputStream	input1 = PublicHttpActivity.getJSONData(App.GetStationDataPath,params);
//		monitor1 = ReadFile.getchartFile(input1);
	}
	private View layoutView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
//使用缓存的layoutview会导致再次加载chartdemo时spinner切换无法替换view
//		 if(layoutView==null){
				layoutView = inflater.inflate(R.layout.chartlist, null);
//	        }
	 //缓存的layoutView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个layoutView已经有parent的错误。  
//	        ViewGroup parent = (ViewGroup) layoutView.getParent();
//	        if (parent != null) {
//	            parent.removeView(layoutView);
//	        }
		//requestWindowFeature(Window.FEATURE_NO_TITLE);

		layout1 = (LinearLayout) layoutView.findViewById(R.id.layout1);
		ch_station=(TextView)layoutView.findViewById(R.id.ch_station3);
		ch_station.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				getActivity().finish();
			}
			});
		    //得到传来的数据
//			Bundle getdata = getActivity().getIntent().getBundleExtra("data");
//		    stationId =  getdata.getString("stationId");
//		    stationName = getdata.getString("stationName");
//		    params.add(new BasicNameValuePair("stationId",stationId ));
//
//		    //设置时间为中国
//		    Calendar c = Calendar.getInstance(Locale.CHINA);
//		    mYear = c.get(Calendar.YEAR); //获取当前年份
//	        mMonth = c.get(Calendar.MONTH);//获取当前月份
//	        mDay = c.get(Calendar.DAY_OF_MONTH);//获取当前月份的日期号码
//	        String startstr1 = mYear+"-"+(mMonth+1)+"-"+mDay+" "+"00:00";
//	        String endtime1 = mYear+"-"+(mMonth+1)+"-"+mDay+" "+"23:59";
//			params.add(new BasicNameValuePair("startTime",startstr1));
//			params.add(new BasicNameValuePair("endTime", endtime1));
//			params.add(new BasicNameValuePair("currentPage","1"));
//			params.add(new BasicNameValuePair("pageSize", "144"));
			InputStream	input = PublicHttpActivity.getJSONData(App.GetStationDataPath,params);
			monitor = ReadFile.getFile(input);

			InputStream	input1 = PublicHttpActivity.getJSONData(App.GetStationDataPath,params);
			monitor1 = ReadFile.getchartFile(input1);
			
			
			//创建要显示的view对象
			chart_stationName = (TextView) layoutView.findViewById(R.id.chart_stationName);
			TextPaint tp = chart_stationName.getPaint(); 
			tp.setFakeBoldText(true);
			chart_time = (TextView) layoutView.findViewById(R.id.chart_date);
			spinner_serner = (Spinner) layoutView.findViewById(R.id.chart_spinner);
			worrimage = new ImageView(getActivity());
			dPicker = new DatePicker(getActivity());
			//布局要显示的view对象
			chart_stationName.setText(stationName);
			
			chartdate = mYear+"-"+(mMonth+1)+"-"+mDay;
			chart_time.setText(chartdate);
			if(monitor1!=null){//如果此站点数据不为空
				getchartview(chartdate,monitor1,monitor);				
			}else{//如果站点数据为空则提示，并结束				
				getActivity().setContentView(R.layout.message);				
				chart_stationName = (TextView) layoutView.findViewById(R.id.stationname);
				chart_stationName.setText(stationName);
				
				//显示查询日期
				chart_time = (TextView) layoutView.findViewById(R.id.date);
				chart_time.setText(mYear+"-"+(mMonth+1)+"-"+mDay);
				
				 textcontent = (TextView) layoutView.findViewById(R.id.message_about);
				 textcontent.setText(R.string.chartdata);
			}
			//下拉列表加监听
			spinner_serner.setOnItemSelectedListener(new OnItemSelectedListener() {

				public void onItemSelected(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					key1 = keydata[arg2];
					layout1.removeView(view1);
					Log.d("layout.remove", "yes");
					view1 =   mCharts[0].execute(getActivity(),monitor,key1);

					layout1.addView(view1, new LayoutParams(LayoutParams.MATCH_PARENT,
							LayoutParams.MATCH_PARENT));

RelativeLayout.LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
					layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
					layout1.setLayoutParams(layoutParams);
					}

				public void onNothingSelected(AdapterView<?> arg0) {
					
				}
			});
			
			data_date=(TextView) layoutView.findViewById(R.id.data_time2);
			//给时间按钮加监听，修改和显示查询的日期
			data_date.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {

					String time = chart_time.getText().toString();
					int year = Integer.parseInt(time.split("-")[0]);
					int month = Integer.parseInt(time.split("-")[1]);
					int day = Integer.parseInt(time.split("-")[2]);
					// TODO Auto-generated method stub

					DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
						@Override
						public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
							StringBuffer sb = new StringBuffer();
							sb.append(String.format("%d-%02d-%02d",
									i, i1, i2));
							String changetime = (String) chart_time.getText();
							String startstr1 = changetime + " " + "00:00";
							String endtime1 = changetime + " " + "23:00";

							params.clear();
							params.add(new BasicNameValuePair("stationId", stationId));
							params.add(new BasicNameValuePair("startTime", startstr1));
							params.add(new BasicNameValuePair("endTime", endtime1));
							params.add(new BasicNameValuePair("currentPage", "1"));
							params.add(new BasicNameValuePair("pageSize", "144"));

							Log.i("ChartDemo/seachButton/params:", "" + params);

							InputStream input = PublicHttpActivity.getJSONData(App.GetStationDataPath, params);

							monitor = ReadFile.getFile(input);
							layout1.removeView(view1);
							if (monitor != null) {

								if (changetime.equals(monitor.getDetails().get(0).getTime().substring(0, 10))) {
									view1 = mCharts[0].execute(getActivity(), monitor, key1);

									//view1.setLayoutParams(params1);
									layout1.addView(view1);
								} else {

									chart_time.setText(monitor.getDetails().get(0).getTime().substring(0, 10));
									view1 = mCharts[0].execute(getActivity(), monitor, key1);
									Toast.makeText(getActivity(), "没有所选日期数据！", Toast.LENGTH_LONG).show();
									//view1.setLayoutParams(params1);
									layout1.addView(view1);
								}
							} else {
								//geterrowlayout(changetime);
								getActivity().setContentView(R.layout.message);

								chart_stationName = (TextView) layoutView.findViewById(R.id.stationname);
								chart_stationName.setText(stationName);

								//显示查询日期
								chart_time = (TextView) layoutView.findViewById(R.id.date);
								chart_time.setText(mYear + "-" + (mMonth + 1) + "-" + mDay);

								textcontent = (TextView) layoutView.findViewById(R.id.message_about);
								textcontent.setText(R.string.chartdata);

								Button errorButton = (Button) layoutView.findViewById(R.id.error_return);
								errorButton.setOnClickListener(new OnClickListener() {

									public void onClick(View arg0) {
										// TODO Auto-generated method stub
										getActivity().finish();
									}
								});

							}
						}

					}, year, month, day);

					datePickerDialog.show();


//					AlertDialog.Builder builder =new AlertDialog.Builder(getActivity());
//					View view = View.inflate(getActivity(), R.layout.change_station, null);
//					final DatePicker datePicker = (DatePicker) view.findViewById(R.id.date_picker);
//					 builder.setView(view);
//					 Calendar cal = Calendar.getInstance();
//					 cal.setTimeInMillis(System.currentTimeMillis());
//
//					 datePicker.init(year,month-1,day, null);
//
//					 final int inType = data_date.getInputType();
//						 //builder.setTitle("选择查询条件");
//						 builder.setPositiveButton("确  定", new DialogInterface.OnClickListener() {
//							 @Override
//							 public void onClick(DialogInterface dialog, int which) {
//								 StringBuffer sb = new StringBuffer();
//								 sb.append(String.format("%d-%02d-%02d",
//										 datePicker.getYear(),
//										 datePicker.getMonth() + 1,
//										 datePicker.getDayOfMonth()));
//								String stationtime=sb.toString();
//								chart_time.setText(stationtime);
//								 dialog.cancel();
//
//							String changetime = (String) chart_time.getText();
//							String startstr1 = changetime+" "+"00:00";
//					        String endtime1 = changetime+" "+"23:00";
//
//					        params.clear();
//					        params.add(new BasicNameValuePair("stationId",stationId ));
//					        params.add(new BasicNameValuePair("startTime",startstr1));
//							params.add(new BasicNameValuePair("endTime", endtime1));
//							params.add(new BasicNameValuePair("currentPage","1"));
//							params.add(new BasicNameValuePair("pageSize", "144"));
//
//							Log.i("ChartDemo/seachButton/params:", ""+params);
//
//							InputStream	input = PublicHttpActivity.getJSONData(App.GetStationDataPath,params);
//
//							monitor = ReadFile.getFile(input);
//							layout1.removeView(view1);
//							if (monitor!=null) {
//
//								if (changetime.equals(monitor.getDetails().get(0).getTime().substring(0, 10))) {
//									view1 =   mCharts[0].execute(getActivity(),monitor,key1);
//
//									//view1.setLayoutParams(params1);
//									layout1.addView(view1);
//								}else {
//
//									chart_time.setText(monitor.getDetails().get(0).getTime().substring(0, 10));
//									view1 =   mCharts[0].execute(getActivity(),monitor,key1);
//									Toast.makeText(getActivity(),"没有所选日期数据！" , Toast.LENGTH_LONG).show();
//									//view1.setLayoutParams(params1);
//									layout1.addView(view1);
//								}
//							}else {
//								//geterrowlayout(changetime);
//								getActivity().setContentView(R.layout.message);
//
//								chart_stationName = (TextView) layoutView.findViewById(R.id.stationname);
//								chart_stationName.setText(stationName);
//
//								//显示查询日期
//								chart_time = (TextView) layoutView.findViewById(R.id.date);
//								chart_time.setText(mYear+"-"+(mMonth+1)+"-"+mDay);
//
//								 textcontent = (TextView) layoutView.findViewById(R.id.message_about);
//								 textcontent.setText(R.string.chartdata);
//
//								 Button errorButton = (Button) layoutView.findViewById(R.id.error_return);
//								 errorButton.setOnClickListener(new OnClickListener() {
//
//									public void onClick(View arg0) {
//										// TODO Auto-generated method stub
//										getActivity().finish();
//									}
//								});
//
//							}
//						}
//					});
//						 Dialog dialog = builder.create();
//						 dialog.show();
//				}
//			});
//
				}
			});
		return layoutView;
		  }//oncreate 方法结束

		private void getchartview(String edittime, Monitor1 monitor12, Monitor monitor2) {
			// TODO Auto-generated method stub
			  tabTitle = monitor12.getTitle();
			  monitor12.getCategoryID();
				Iterator iter = tabTitle.entrySet().iterator();  
			    String[] titledata = new String[tabTitle.size()] ;
			    //final String[] keydata = new String[tabTitle.size()] ;
			    keydata = new String[tabTitle.size()] ;
			    int i = 0;
		        while (iter.hasNext()) {  
					Map.Entry entry = (Map.Entry) iter.next();  
		            
					Object key = entry.getKey();  
		            
					Object val =  entry.getValue(); 
					titledata[i]=(String)val;
					keydata[i]=(String)key;
					i++;
		        }
		        String[] choosedata = new String[tabTitle.size()];
		       
		        //2012-12-18
				for (int j = 0; j <titledata.length; j++) {
					choosedata[j] = titledata[j];
				}
				
				chartdate = monitor2.getDetails().get(0).getTime().substring(0, 10);//取得获取的数据中日期
				chart_time.setText(chartdate);				
				
				//begin
				
				//返回的chart曲线图				
				key1 = keydata[0];
				view1 =   mCharts[0].execute(getActivity(),monitor2,key1);				
				 
				 //将得到的view对象加载到页面布局中
				 			
				 //layout1.addView(view1, LayoutParams.WRAP_CONTENT, Layou tParams.FILL_PARENT) ;
				 layout1.addView(view1,  new LayoutParams(LayoutParams.MATCH_PARENT,
							LayoutParams.MATCH_PARENT));
				 
				 //下拉列表显示要查询的各个窗器
				adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, choosedata);
				adapter.setDropDownViewResource(R.layout.chart_spinner);
				spinner_serner.setAdapter(adapter);
		}
		
		public static Date getDateBefore(Date d, int day) {   
		        Calendar now = Calendar.getInstance();   
		        now.setTime(d);   
		        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);   
		        return now.getTime();   
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
			public void onDestory(){
				super.onDestroy();
			}
}