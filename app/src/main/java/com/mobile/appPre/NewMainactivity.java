package com.mobile.appPre;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Picture;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.text.TextPaint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.longhui.entity.ImageAndTextListAdapter;
import com.longhui.entity.PictureList;
import com.longhui.service.AsyncImageLoader;
import com.longhui.service.ImageAndText;
import com.longhui.service.ReadFile;
import com.mobile.appPre.R;
import com.mobile.publiclass.PublicHttpActivity;
import com.mobile.publiclass.ShareData;
import com.mobile.publiclass.StationAdapter;
import com.mobile.publiclass.SysExitUtil;

public class NewMainactivity extends Fragment{

	private GridView gridview_png;
	private ImageView ItemImages;
	
	private TextView pig_stationName,pig_date,textcontent,data_date,ch_station;
	private Button pig_button;
	
	private LinearLayout layout ;
	private String stationName;
	
	private String startstr1="";
	private String endtime1="";
	
	private static int mHour; 
	private static int mMinute; 
	private static int mYear; 
	private static int mMonth; 
	private static int mDay;
	
	private static String stationId = null;
	private static Picture picture=null;
	private List<NameValuePair> params = new ArrayList<NameValuePair>();
	private PublicApplication App = new PublicApplication();
	private ArrayList<HashMap<String, Object>> pathList;
	private com.longhui.entity.Picture pictrue = null;
	private String firsttime="";
	 
	private AsyncImageLoader loader = new AsyncImageLoader();
	private boolean add;
	public String[] urlsStrings,stationtimes;
	
	
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
		mHour = c.get(Calendar.HOUR_OF_DAY);//获取当前的小时数
		mMinute = c.get(Calendar.MINUTE);//获取当前的分钟数
		Date    curDate    =   new    Date(System.currentTimeMillis());//获取当前时间
		Date before = getDateBefore(curDate,3);
		SimpleDateFormat  formatter    = new    SimpleDateFormat ("yyyy-MM-dd HH:mm");
		String  endTime = formatter.format(curDate);
		String    startstr    =    formatter.format(before);

		startstr1 = mYear+"-"+(mMonth+1)+"-"+mDay+" "+"00:00";
		endtime1 = mYear+"-"+(mMonth+1)+"-"+mDay+" "+"23:59";

		params.add(new BasicNameValuePair("startTime",startstr1));
		params.add(new BasicNameValuePair("endTime", endtime1));
		params.add(new BasicNameValuePair("currentPage","1"));
		params.add(new BasicNameValuePair("pageSize", "30"));

		Log.i("GetStationDatas路径：",App.GetPicturesPath);
		//访问网络得到数据流
		InputStream	input = PublicHttpActivity.getJSONData(App.GetPicturesPath,params);
		//解析得到的流
		pictrue = ReadFile.getPicture(input);
	}
	private View layoutView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		//requestWindowFeature(Window.FEATURE_NO_TITLE);//全屏展示本窗口，无标题
		 if(layoutView==null){  
		    	layoutView = inflater.inflate(R.layout.layout_gallery_new, null);
	        }  
	 //缓存的layoutView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个layoutView已经有parent的错误。  
	        ViewGroup parent = (ViewGroup) layoutView.getParent();  
	        if (parent != null) {  
	            parent.removeView(layoutView);  
	        }         
	        
		ch_station=(TextView) layoutView.findViewById(R.id.ch_station2);
		ch_station.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				getActivity().finish();
			}
			});
		//setTitle("图片显示页");
		//得到从Activity传来的数据
//		Bundle getdata = getActivity().getIntent().getBundleExtra("data");
//	    stationId =  getdata.getString("stationId");
//	    stationName = getdata.getString("stationName");
//	    params.add(new BasicNameValuePair("stationId",stationId ));
//
//	  //设置时间为中国
//	    Calendar c = Calendar.getInstance(Locale.CHINA);
//	    mYear = c.get(Calendar.YEAR); //获取当前年份
//        mMonth = c.get(Calendar.MONTH);//获取当前月份
//        mDay = c.get(Calendar.DAY_OF_MONTH);//获取当前月份的日期号码
//        mHour = c.get(Calendar.HOUR_OF_DAY);//获取当前的小时数
//        mMinute = c.get(Calendar.MINUTE);//获取当前的分钟数
//        Date    curDate    =   new    Date(System.currentTimeMillis());//获取当前时间
//        Date before = getDateBefore(curDate,3);
//        SimpleDateFormat  formatter    = new    SimpleDateFormat ("yyyy-MM-dd HH:mm");
//        String  endTime = formatter.format(curDate);
//        String    startstr    =    formatter.format(before);
//
//        startstr1 = mYear+"-"+(mMonth+1)+"-"+mDay+" "+"00:00";
//        endtime1 = mYear+"-"+(mMonth+1)+"-"+mDay+" "+"23:59";
//
//		params.add(new BasicNameValuePair("startTime",startstr1));
//		params.add(new BasicNameValuePair("endTime", endtime1));
//		params.add(new BasicNameValuePair("currentPage","1"));
//		params.add(new BasicNameValuePair("pageSize", "30"));
//
//		Log.i("GetStationDatas路径：",App.GetPicturesPath);
//		//访问网络得到数据流
//		InputStream	input = PublicHttpActivity.getJSONData(App.GetPicturesPath,params);
//		//解析得到的流
//		pictrue = ReadFile.getPicture(input);
		
		//显示站点名称
		pig_stationName = (TextView) layoutView.findViewById(R.id.pig_stationname);
		TextPaint tp = pig_stationName.getPaint(); 
		tp.setFakeBoldText(true); 
		pig_stationName.setText(stationName);
		
		
		firsttime = mYear+"-"+(mMonth+1)+"-"+mDay ;
		
		
		gridview_png = (GridView) layoutView.findViewById(R.id.girdview_png);
		
		//给编辑时间按钮添加监听
		pig_date =  (TextView) layoutView.findViewById(R.id.pig_date);
		data_date=(TextView) layoutView.findViewById(R.id.data_time);
		data_date.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				AlertDialog.Builder builder =new AlertDialog.Builder(getActivity());          
				View view = View.inflate(getActivity(), R.layout.change_station, null);              
				final DatePicker datePicker = (DatePicker) view.findViewById(R.id.date_picker);
				 builder.setView(view);                
				 Calendar cal = Calendar.getInstance();              
				 cal.setTimeInMillis(System.currentTimeMillis());  
				 String time=pig_date.getText().toString();
				 int year=Integer.parseInt(time.split("-")[0]);
				 int month=Integer.parseInt(time.split("-")[1]);
				 int day=Integer.parseInt(time.split("-")[2]);
				 datePicker.init(year,month-1,day, null);                 
				      
				 final int inType = data_date.getInputType();                  
				 data_date.setInputType(InputType.TYPE_NULL);              
				 data_date.setInputType(inType);                                   
					 //builder.setTitle("选择查询条件");                  
					 builder.setPositiveButton("确  定", new DialogInterface.OnClickListener() {                         
						 @Override                     
						 public void onClick(DialogInterface dialog, int which) {                             
							 StringBuffer sb = new StringBuffer();                          
							 sb.append(String.format("%d-%02d-%02d",                                   
									 datePicker.getYear(),                                   
									 datePicker.getMonth() + 1,                                  
									 datePicker.getDayOfMonth())); 
							String stationtime=sb.toString();
							pig_date.setText(stationtime);                                                   
							dialog.cancel();
						
						startstr1 = stationtime+" "+"00:00";
						endtime1 = stationtime+" "+"23:59";
				        params.clear();
						params.add(new BasicNameValuePair("startTime",startstr1));
						params.add(new BasicNameValuePair("endTime", endtime1));
						params.add(new BasicNameValuePair("stationId",stationId ));
						params.add(new BasicNameValuePair("currentPage","1"));
						params.add(new BasicNameValuePair("pageSize", "50"));
						
						//访问网络得到数据流
						InputStream	input = PublicHttpActivity.getJSONData(App.GetPicturesPath,params);
						//解析得到的流
						pictrue = ReadFile.getPicture(input);
						
						
						getPigShow(pictrue,layoutView,stationtime);
						
					}
				});
					 Dialog dialog = builder.create();             
					 dialog.show();
			}
		});
		
		getPigShow(pictrue,layoutView,firsttime);
		return layoutView;
		
		}//oncreate方法结束
		
		

	private void getPigShow(com.longhui.entity.Picture pictrue2,View layoutView, String timestring) {
		// TODO Auto-generated method stub
		
		
		 ItemImages = (ImageView) layoutView.findViewById(R.id.ItemImages);
		//设置gridview显示形式
		gridview_png.setNumColumns(3);//分3列
		gridview_png.setVerticalSpacing(3);//水平间距
		gridview_png.setHorizontalSpacing(4);//垂直间距
		gridview_png.setPadding(8, 8,8,8);//位置
		
		
		if (pictrue!=null) {
			List<PictureList> pictruelist = pictrue.getDetails();
			
			Log.i("NewMain/piclist.size", "pictruelist.size = "+pictruelist.size());
			
			if(pictruelist.size()!=0){//判断获得的picture内容不为空
				//异步加载方法，自定义adapter
				
				//显示查询日期
				pig_date = (TextView) layoutView.findViewById(R.id.pig_date);
				
				//pictruelist.get(0).getTime().substring(0, 10);
				pig_date.setText(pictruelist.get(0).getTime().substring(0, 10));
				
				
				pathList = new ArrayList<HashMap<String, Object>>();
				//List<ImageAndText> list = new ArrayList<ImageAndText>();  
				
				 String urls = null;
				 String time = null;
				//将获得的picture列表得到之后，封装到map里面并且用gridview显示图像
				 List<ImageAndText> list = new ArrayList<ImageAndText>(); 
				 urlsStrings = new String[pictruelist.size()];
				 stationtimes = new String[pictruelist.size()];
				for (int i = 0; i < pictruelist.size(); i++) {
					PictureList media = pictruelist.get(i);
					HashMap<String, Object> map = new HashMap<String,Object>();
					//图片url
					urls = App.AshxPicturePath+"?id="+media.getId()+"&tname="+media.getTname() ;
					
					Log.i("txURL", urls+ ",id="+media.getId()+",tname="+media.getTname());
					time = media.getTime();
					//String time1 = time.substring(10);
					String[] time1 = time.split(" ");
					
					list.add(new ImageAndText(urls, time1[1]));
					 
					urlsStrings[i] = urls ;
					stationtimes[i] = pictruelist.get(i).getTime().substring(11, 16);
				}
				
				
				
				//异步加载方法，自定义adapter
			 gridview_png.setAdapter(new ImageAndTextListAdapter(getActivity(), list, gridview_png));
			 
			//给gridview添加监听，当点击某一个图片将会单独放大显示图片
				gridview_png.setOnItemClickListener(new OnItemClickListener() {

					public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
							long arg3) {
						// TODO Auto-generated method stub
						
						List<PictureList> pictruelist = pictrue.getDetails();
						//得到第arg2个item上图片的信息
						PictureList media = pictruelist.get(arg2);
						//将信息封装到intent中传给PngShow
						Intent intent = new Intent();
						Bundle png_data = new Bundle();
						
						png_data.putStringArray("stationtimes", stationtimes);
						png_data.putStringArray("jhk", urlsStrings);
						png_data.putInt("id", arg2);
						png_data.putString("stationName", stationName);
						intent.putExtra("data", png_data);
						intent.setClass(getActivity(),
								ViewPagerActivity.class);
						
						startActivity(intent);
						
						
					}
				});
				
			}else{
//				NewMainactivity.onCreateView(R.layout.message);
				getActivity().setContentView(R.layout.message);
				layoutView= getActivity().getCurrentFocus();
				//显示站点名称
				pig_stationName = (TextView) layoutView.findViewById(R.id.stationname);
				pig_stationName.setText(stationName);
				
				//显示查询日期
				pig_date = (TextView) layoutView.findViewById(R.id.date);
				pig_date.setText(timestring);
				
				 textcontent = (TextView) layoutView.findViewById(R.id.message_about);
				 textcontent.setText(R.string.imageview);
				 
				 Button errorButton = (Button) layoutView.findViewById(R.id.error_return);
				 errorButton.setOnClickListener(new OnClickListener() {
					
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						getActivity().finish();
					}
				});
				 
			}
			
		}else {
			getActivity().setContentView(R.layout.message);
			
			
			//显示站点名称
			pig_stationName = (TextView) layoutView.findViewById(R.id.stationname);
			pig_stationName.setText(stationName);
			
			//显示查询日期
			pig_date = (TextView) layoutView.findViewById(R.id.date);
			pig_date.setText(timestring);
			
			 textcontent = (TextView) layoutView.findViewById(R.id.message_about);
			 textcontent.setText(R.string.imageview);
			
			 Button errorButton = (Button) layoutView.findViewById(R.id.error_return);
				 errorButton.setOnClickListener(new OnClickListener() {
					
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						getActivity().finish();
					}
				});
		}
	}


	private void geterrow() {
		
		new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
			
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				// TODO Auto-generated method stub
				getActivity().setContentView(R.layout.layout_gallery_new);
				
				//显示站点名称
				pig_stationName = (TextView) layoutView.findViewById(R.id.pig_stationname);
				pig_stationName.setText(stationName);
				
				
				gridview_png = (GridView) layoutView.findViewById(R.id.girdview_png);
				gridview_png.setAdapter(null);
				
				String edittime = year+"-"+(monthOfYear+1)+"-"+dayOfMonth ;
				
				pig_date.setText(edittime);
				
				startstr1 = edittime+" "+"00:00";
				endtime1 = edittime+" "+"23:59";
		        params.clear();
				params.add(new BasicNameValuePair("startTime",startstr1));
				params.add(new BasicNameValuePair("endTime", endtime1));
				params.add(new BasicNameValuePair("stationId",stationId ));
				params.add(new BasicNameValuePair("currentPage","1"));
				params.add(new BasicNameValuePair("pageSize", "50"));
				
				//访问网络得到数据流
				InputStream	input = PublicHttpActivity.getJSONData(App.GetPicturesPath,params);
				//解析得到的流
				pictrue = ReadFile.getPicture(input);				
				
				getPigShow(pictrue,layoutView,edittime);
				
			}
		}, mYear, mMonth, mDay).show();
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


