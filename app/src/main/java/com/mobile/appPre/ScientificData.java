package com.mobile.appPre;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;
import android.widget.Toast;
import android.widget.ViewFlipper;
import com.longhui.entity.LoginStation;
import com.longhui.entity.Monitor;
import com.longhui.entity.MonitorList;
import com.mobile.appPre.TableAdapter;
import com.mobile.appPre.TableAdapter.TableCell;
import com.mobile.appPre.TableAdapter.TableRow;
import com.longhui.service.ReadFile;
import com.mobile.appPre.R;
import com.mobile.publiclass.HttpThread;
import com.mobile.publiclass.PublicHttpActivity;
import com.mobile.publiclass.ShareData;
import com.mobile.publiclass.SysExitUtil;

public class ScientificData extends Fragment{
	
	
	//private Button  dataup ;
	//private ImageButton datadown,dataup;
	private TextView data_stationname,data_date ,textcontent,date_time,ch_station;
	private Button data_button ;
	private String stationName ="";
	private static String stationtime;
	
	boolean data=false; //调用webservice 近回的数据,验证成功true,失败false 
	
	private final int WC = ViewGroup.LayoutParams.WRAP_CONTENT; 
	private final int FP = ViewGroup.LayoutParams.FILL_PARENT; 
	private List<NameValuePair> params = new ArrayList<NameValuePair>();
	private static List<NameValuePair> searchparams = new ArrayList<NameValuePair>();
	private int[] colors = new int[] { 0x30FF0000, 0x300000FF };
	private static Monitor monitor = null;
	private LoginStation checklogin = null;
	private static Map<String, Object> tabTitle = null;
	static List<MonitorList> monitorlist = null;
	public ArrayList<TableRow> table = new ArrayList<TableRow>();
	public  static Context foreverContext;
	private static PopupWindow popup;
	private PublicApplication App = new PublicApplication();
	private ListView lv;
	private int mSelectedRow = 0;
	private static final int ID_ADD = 1;
	private static final int ID_ACCEPT = 2;
	private static final int ID_UPLOAD = 3;
	private static int mHour; 
	private static int mMinute; 
	private static int mYear; 
	private static int mMonth; 
	private static int mDay;
	private static String satartdata = null;
	private static String enddata = null;
	private	static  int smy_year = 0;
	private static int smy_month = 0;
	private static int smy_day = 0;
	private static int smy_hour = 0;
	private static int smy_minute = 0;
	private static String stationId = null;
	private static	  int emy_year = 0;
	private  static int emy_month = 0;
	private  static int emy_day = 0;
	private static int emy_hour = 0;
	private static  int emy_minute = 0;
	private static Activity con =null;
	private static  QuickAction mQuickAction;
	private static ActionItem addItem;
	private static ActionItem acceptItem;
	private static TableAdapter tableAdapter;
	private static ActionItem uploadItem;
	private ProgressDialog progressDialog = null;
	
	
	private HorizontalScrollView scrollViewlayout ;
	private String currentPage ="";
	private int currentPage_int = 1 ;
	
	private String startstr1="";
	private String endtime1="";
	//private String edittime = "";
	
	HttpThread thread=null; //线程对像 
	private static final String NAMESPACE ="http://tempuri.org/"; // "http://202.196.80.146:86/WebService.asmx/";  
	//private static final String URL = "http://202.196.80.146:86/WebService1210.asmx";
//	private static final String URL = "http://219.151.41.248:8015/WebService1210.asmx";
	//private static final String URL ="http://202.196.80.146:86/WebService1210.asmx";
	private static final String URL = "http://192.168.1.4:8051/WebService1210.asmx";
	private String METHOD_NAME = "GetStationData"; // 函数名 
	
	private Boolean isBoolean = true ;
	
	//悬浮按钮所用变量
	private WindowManager wm=null;
	private WindowManager.LayoutParams wmParams=null;
	
	private ImageView leftbtn=null;
	private ImageView rightbtn=null;
	
	// ImageView的alpha值   
	private int mAlpha = 0;
	private boolean isHide;
	
	private ViewFlipper viewFlipper = null;
	
	String time;
	
	
	RelativeLayout mHead;
	LinearLayout lay;
	
	 //生成消息对象   
	Handler handlerwdy=new Handler(){ 
		
		public void handleMessage(Message m){ 
			switch(m.what){     
			case 1: 
				data= m.getData().getBoolean("data"); //从消息在拿出数据
				if (data) {
					isBoolean = true ;
				}else {
					//datadown.setBackgroundResource(R.drawable.down_1);
				}
			} 
	 }
		
   };
	
   @Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (savedInstanceState != null)
			  return;

	   Bundle getdata = getActivity().getIntent().getBundleExtra("data");
	   stationId =  getdata.getString("stationId");
	   stationName = getdata.getString("stationName");
	   params.add(new BasicNameValuePair("stationId",stationId ));

	   //设置时间为中国    设置查询时间
	   Calendar c = Calendar.getInstance(Locale.CHINA);
	   mYear = c.get(Calendar.YEAR); //获取当前年份
	   mMonth = c.get(Calendar.MONTH);//获取当前月份
	   mDay = c.get(Calendar.DAY_OF_MONTH);//获取当前月份的日期号码
	   mHour = c.get(Calendar.HOUR_OF_DAY);//获取当前的小时数
	   mMinute = c.get(Calendar.MINUTE);//获取当前的分钟数
	   Date    curDate    =   new    Date(System.currentTimeMillis());//获取当前时间
	   //Date before = getDateBefore(curDate,1);
	   //SimpleDateFormat    formatter    =   new    SimpleDateFormat ("yyyy-MM-dd HH:mm");
	   //String  endTime = formatter.format(curDate);
	   //String    startstr    =    formatter.format(before);

	   startstr1 = mYear+"-"+(mMonth+1)+"-"+mDay+" "+"00:00";
	   endtime1 = mYear+"-"+(mMonth+1)+"-"+mDay+" "+"23:59";
	   //将访问所用条件添加到Params里面
	   params.add(new BasicNameValuePair("startTime",startstr1));
	   params.add(new BasicNameValuePair("endTime", endtime1));
	   params.add(new BasicNameValuePair("currentPage",currentPage_int+""));
	   params.add(new BasicNameValuePair("pageSize", "144"));

	   // 真实环境中应
	   //String url = App.GetStationData+"?stationId="+stationId+"&&startTime="+startstr+"&&endTime="+endTime;

	   Log.i("GetStationDatas路径：",App.GetStationDataPath);
	   //将接口名和访问条件作为参数调用方法得到流
	   InputStream	input = PublicHttpActivity.getJSONData(App.GetStationDataPath,params);

	   //将取得的流用方法读出封装到一个公共类monitor里面
	   monitor = ReadFile.getFile(input);

	}
	private View layoutView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

	    if(layoutView==null){  
	    	layoutView = inflater.inflate(R.layout.scientificlist, null);
        }  
 //缓存的layoutView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个layoutView已经有parent的错误。  
        ViewGroup parent = (ViewGroup) layoutView.getParent();  
        if (parent != null) {  
            parent.removeView(layoutView);  
        }            
        
		con = getActivity();
		foreverContext = getActivity();
		//requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		ch_station=(TextView) layoutView.findViewById(R.id.ch_station);
	    //获得xml数据
		/*mHead = (RelativeLayout) this.findViewById(R.id.head);
		mHead.setFocusable(true);
		mHead.setClickable(true);
		mHead.setBackgroundColor(Color.parseColor("#b2d235"));
		mHead.setOnTouchListener(new ListViewAndHeadViewTouchLinstener());*/
		lv = (ListView) layoutView.findViewById(R.id.listviewContent);
		
		lv.setFocusable(true);
		lv.setClickable(true);
	
		//lay=(LinearLayout)this.findViewById(R.id.list2);
		ch_station=(TextView) layoutView.findViewById(R.id.ch_station);
		ch_station.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				con.finish();
			}
			});
//		Bundle getdata = getActivity().getIntent().getBundleExtra("data");
//	    stationId =  getdata.getString("stationId");
//	    stationName = getdata.getString("stationName");
//	    params.add(new BasicNameValuePair("stationId",stationId ));
//
//	  //设置时间为中国    设置查询时间
//	    Calendar c = Calendar.getInstance(Locale.CHINA);
//	    mYear = c.get(Calendar.YEAR); //获取当前年份
//        mMonth = c.get(Calendar.MONTH);//获取当前月份
//        mDay = c.get(Calendar.DAY_OF_MONTH);//获取当前月份的日期号码
//        mHour = c.get(Calendar.HOUR_OF_DAY);//获取当前的小时数
//        mMinute = c.get(Calendar.MINUTE);//获取当前的分钟数
//        Date    curDate    =   new    Date(System.currentTimeMillis());//获取当前时间
//        //Date before = getDateBefore(curDate,1);
//        //SimpleDateFormat    formatter    =   new    SimpleDateFormat ("yyyy-MM-dd HH:mm");
//        //String  endTime = formatter.format(curDate);
//        //String    startstr    =    formatter.format(before);
//
//        startstr1 = mYear+"-"+(mMonth+1)+"-"+mDay+" "+"00:00";
//        endtime1 = mYear+"-"+(mMonth+1)+"-"+mDay+" "+"23:59";
//        //将访问所用条件添加到Params里面
//		params.add(new BasicNameValuePair("startTime",startstr1));
//		params.add(new BasicNameValuePair("endTime", endtime1));
//		params.add(new BasicNameValuePair("currentPage",currentPage_int+""));
//		params.add(new BasicNameValuePair("pageSize", "144"));
//
//		// 真实环境中应
//		//String url = App.GetStationData+"?stationId="+stationId+"&&startTime="+startstr+"&&endTime="+endTime;
//
//		Log.i("GetStationDatas路径：",App.GetStationDataPath);
//		//将接口名和访问条件作为参数调用方法得到流
//		InputStream	input = PublicHttpActivity.getJSONData(App.GetStationDataPath,params);
//
//		//将取得的流用方法读出封装到一个公共类monitor里面
//		monitor = ReadFile.getFile(input);
		

        //		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.window_title);
		//显示站点名称
		 data_stationname = (TextView) layoutView.findViewById(R.id.data_stationname);
		 TextPaint tp = data_stationname.getPaint(); 
		 tp.setFakeBoldText(true); 
		 data_stationname.setText(stationName);
		 //时间显示
		 data_date = (TextView) layoutView.findViewById(R.id.data_date);
		 date_time= (TextView) layoutView.findViewById(R.id.date_time);
		 //判定拿到monitor是否为空，不为空的话：调用getdatadefult方法传入monitor做参数显示数据；为空的话：显示空数据页面
		if (monitor!=null) {
			//getdatadefult(monitor); 
			ShareData.monitor=monitor;
			getview(monitor);
			time=monitor.getDetails().get(0).getTime().substring(0, 10);
			date_time.setText(time);
			
			//data_button = (Button) findViewById(R.id.data_button);
			//设置时间，根据时间查询
			data_date.setOnClickListener(new OnClickListener() {
				
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					AlertDialog.Builder builder =new AlertDialog.Builder(con);          
					View view = View.inflate(con, R.layout.change_station, null);              
					final DatePicker datePicker = (DatePicker) view.findViewById(R.id.date_picker);
					 builder.setView(view);                
					 Calendar cal = Calendar.getInstance();              
					 cal.setTimeInMillis(System.currentTimeMillis());
					 int year=Integer.parseInt(time.split("-")[0]);
					 int month=Integer.parseInt(time.split("-")[1]);
					 int day=Integer.parseInt(time.split("-")[2]);
					 datePicker.init(year,month-1,day, null);                 
					      
					 final int inType = data_date.getInputType();                  
						 //data_date.setInputType(InputType.TYPE_NULL);        
						 //data_date.setInputType(inType);                                   
						 //builder.setTitle("选择查询条件");                  
						 builder.setPositiveButton("确  定", new DialogInterface.OnClickListener() {                         
							 @Override                     
							 public void onClick(DialogInterface dialog, int which) {                             
								 StringBuffer sb = new StringBuffer();                          
								 sb.append(String.format("%d-%02d-%02d",                                   
										 datePicker.getYear(),                                   
										 datePicker.getMonth() + 1,                                  
										 datePicker.getDayOfMonth())); 
								 stationtime=sb.toString();
								 date_time.setText(stationtime);                                                   
								 dialog.cancel();
								 //GetData(sb.toString());
								 
								 currentPage_int=1 ;
									startstr1 = stationtime+" "+"00:00";
							        endtime1 = stationtime+" "+"23:59";
							        params.clear();
									params.add(new BasicNameValuePair("startTime",startstr1));
									params.add(new BasicNameValuePair("endTime", endtime1));
									params.add(new BasicNameValuePair("stationId",stationId ));
									params.add(new BasicNameValuePair("currentPage",currentPage_int+""));
									params.add(new BasicNameValuePair("pageSize", "144"));
									
									InputStream	input = PublicHttpActivity.getJSONData(App.GetStationDataPath,params);

									monitor = ReadFile.getFile(input);
									//重新设置查询时间后，再判定monitor是否为空，非空：调用方法显示数据；为空：继续显示空数据界面。
									if (monitor!=null) {
										//setContentView(R.layout.scientificlist);
										ShareData.monitor=monitor;
										//调用显示处理数据后的view方法
										getview(monitor);
										date_time.setText(stationtime);
									}else {
										//编辑过时间之后调用该方法可以继续加载是否有处理后的view
										getcenterview(stationtime);
										
									}
								 }                 
							 });         
						 Dialog dialog = builder.create();             
						 dialog.show();
					}
			});
			
			
		}else {
			 con.setContentView(R.layout.message);
			//显示站点名称

			 data_stationname = (TextView) layoutView.findViewById(R.id.stationname);
			 data_stationname.setText(stationName);

			 data_date = (TextView) layoutView.findViewById(R.id.date);


			 data_date.setText(new StringBuilder().append(mYear).append("-")
						.append(mMonth+1).append("-")
						.append(mDay));
			 textcontent = (TextView) layoutView.findViewById(R.id.message_about);
			 textcontent.setText(R.string.ScientificData);


			 Button errorButton = (Button) layoutView.findViewById(R.id.error_return);
			 errorButton.setOnClickListener(new OnClickListener() {
				
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					con.finish();
				}
			});
		}
		return layoutView;
	}
	
	class ListViewAndHeadViewTouchLinstener implements View.OnTouchListener {

		@Override
		public boolean onTouch(View arg0, MotionEvent arg1) {
			//当在列头 和 listView控件上touch时，将这个touch的事件分发给 ScrollView
			HorizontalScrollView headSrcrollView = (HorizontalScrollView) mHead
					.findViewById(R.id.horizontalScrollView1);
			headSrcrollView.onTouchEvent(arg1);
			return false;
		}
	}
	
	//重新设置时间后，获取空数据页面的方法
	protected void getcenterview(String edittime2) {
		// TODO Auto-generated method stub
		con.setContentView(R.layout.message);
		//显示站点名称
		 data_stationname = (TextView) layoutView.findViewById(R.id.stationname);
		 data_stationname.setText(stationName);
		 
		 data_date = (TextView) layoutView.findViewById(R.id.date);
		 
		 
		 data_date.setText(edittime2);
		 textcontent = (TextView) layoutView.findViewById(R.id.message_about);
		 textcontent.setText(R.string.ScientificData);
		 
		 
		 Button errorButton = (Button) layoutView.findViewById(R.id.error_return);
		 errorButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				con.finish();
			}
		});
	}
   /*oncreate方法结束*/	
	
	private void getview(Monitor monitor2) {
		// TODO Auto-generated method stub
		
		DisplayMetrics dm = new DisplayMetrics() ;
		con.getWindowManager().getDefaultDisplay().getMetrics(dm);
		//int height1 = (dm.heightPixels-60)*4/5 ;
		int height1 = dm.heightPixels ;
		int width1 = height1*7/2;
		//lv = (ListView) this .findViewById(R.id.listview); 
		
		//listview需要重新加监听来调用浮动按钮的显示与隐藏监听
		lv.setOnTouchListener(new OnTouchListener() {
			
			public boolean onTouch(View arg0, MotionEvent arg1) {
				// TODO Auto-generated method stub
				con.onTouchEvent(arg1);
				return false;
			}
		});
		
	    Drawable drawable = getResources().getDrawable(R.drawable.menu_bg);
	    lv.setSelector(drawable); 
	    
		tabTitle = monitor.getTitle();
	
		ActionItem addItem 		= new ActionItem(ID_ADD, "光量子", getResources().getDrawable(R.drawable.sun));
		ActionItem acceptItem 	= new ActionItem(ID_ACCEPT, "空气湿度", getResources().getDrawable(R.drawable.water));
        ActionItem uploadItem 	= new ActionItem(ID_UPLOAD, "空气温度", getResources().getDrawable(R.drawable.temprature));
		
       mQuickAction 	= new QuickAction(con);
	//*
	//设置TableRow 
	table = new  ArrayList<TableRow>();  
    TableCell[] titles = new  TableCell[ tabTitle.size()]; // 每行tabTitle.size()个单元   
    int  width =  con .getWindowManager().getDefaultDisplay().getWidth()/titles.length; 
	
//       Toast.makeText(TestActivity.this ,  "选中第" +tabTitle.get(0).size()+ "行" ,  Toast.LENGTH_LONG ).show();
    /* 第一种: entryset只遍历一次:它把key和value放到entry */  
    int t = 0;
       
    Iterator iter = tabTitle.entrySet().iterator();  
  
    while (iter.hasNext()) {  
        Map.Entry entry = (Map.Entry) iter.next();  
        Object key = entry.getKey();  
        Object val = entry.getValue();  
        if(t==0){
        	titles[t] = new  TableCell( val , 
        			width1/20,     //titles[l].width,   
        			//height1/19, 
        			(height1/20)*2, 
                    TableCell.STRING);
        }else{
            titles[t] = new  TableCell( val , width1/20,     //titles[l].width,   
            		(height1/20)*2, 
                    TableCell.STRING);
            if("PAR"==key){
            	mQuickAction.addActionItem(addItem);
            }
            if("AirHumidity"==key){
            	mQuickAction.addActionItem(acceptItem);
            } if("AirTemprature"==key){
            	mQuickAction.addActionItem(uploadItem);
            }
        }
        t++;
    }//高效,使用此种方式  
    
    table.add(new TableRow(titles));  
    //*
    //下面引着的就是这块的
    
    String data = "";
    String str = "";
    monitorlist = monitor.getDetails();
    for (int i = 0; i < monitorlist.size(); i++) {
		MonitorList mon = monitorlist.get(i); 
        Iterator list_iter = tabTitle.entrySet().iterator();  
        // 每行的数据   
        TableCell[] cells = new  TableCell[ tabTitle.size()]; // 每行5个单元   
        int l = 0;
        while (list_iter.hasNext()) {  
            Map.Entry entry = (Map.Entry) list_iter.next();  
            Object key = entry.getKey();  
            Object val = entry.getValue();	           
            data =  getData(key,mon);
            str += data+"  \n  ";
            cells[l] = new  TableCell( data,  
                             width1/20,     //titles[l].width,   
                             height1/20,        //LayoutParams.FILL_PARENT,   
                    TableCell.STRING); 
            l++;
        }//高效,使用此种方式  
    /*
        cells[l] =  new  TableCell(R.drawable.ic_launcher,  
                titles[l].width,   
                LayoutParams.WRAP_CONTENT,  
                TableCell.STRING);  
        */
        table.add(new  TableRow(cells));
    }
    try {
		tableAdapter = new TableAdapter(con,table);  
		lv.setAdapter(tableAdapter);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}  
		
	}
	class  ItemClickEvent  implements  AdapterView.OnItemClickListener {  
	    public   void  onItemClick(AdapterView<?> arg0, View arg1,  int  arg2,  
	            long  arg3) {  
	       // Toast.makeText(ScientificData.this ,  "选中第" +String.valueOf(arg2)+ "行" ,  500 ).show();  
	    }  
	} 
	
	public static String getData(Object key,MonitorList mon){
		
		String data = "";
		String[] AirTemprature = mon.getAirTemprature();//空气温度
        String[] SoilTemprature = mon.getSoilTemprature();//土壤温度
        
        String[] AirHumidity = mon.getAirHumidity();	//空气湿度	[%HR]
    	String[] SoilHumidity = mon.getSoilHumidity();	//土壤湿度	[%HR]
    	String[] Radiation = mon.getRadiation();	//总辐射	[wm-2]
    	String[] CO2 = mon.getCO2();	//CO2浓度	[ppm]
    	String[] Pressure = mon.getPressure();	//相对气压	[Pa]
    	String[] SoilHumiditys = mon.getSoilHumiditys();	//土壤水分	[%HR]
    	String[] PAR = mon.getPAR();	//光量子	[μmols-1m-2] 
    	String[] CAnemometer = mon.getCAnemometer();	//风速	[ms-1]
    	String[] Dogvane= mon.getDogvane();	//风向	[度]
    	String[] Rainfall= mon.getRainfall();	//降雨量	[mm]
    	String[] Fengshipc = mon.getFengshipc();	//风蚀pc	[次]
    	String[] AAnemometer = mon.getAAnemometer();	//平均风速	[ms-1]
    	String[] MAnemometer = mon.getMAnemometer();	//最大风速	[ms-1]
    	String[] FengshiKE = mon.getFengshiKE();	//风蚀KE	[J]
    	String[] Jingfushe = mon.getJingfushe();	//净辐射	[wm-2]
    	String[] Guangliangdu = mon.getGuangliangdu();	//光亮度	[klux]
    	String[] oilpH = mon.getOilpH();	//土壤pH	[未定义T]
    	String[] SoilHeatFlux = mon.getSoilHeatFlux();	//土壤热通量	[w/m-2]
        String key1 = (String) key;
		 //list数据
        
      
        if(key=="Time"){
        	//int d = Integer.valueOf(mon.getTime().substring(0, 10));
        	//String xs = mon.getTime().substring(11, 13);
        	//String fz = mon.getTime().substring(14, 15);
        	//String fz1 = mon.getTime().substring(15, 16);
        	//String xs = mon.getTime().split(":")[0].substring(10);
        	//String fz = mon.getTime().split(":")[1];
        	//String fz1 = mon.getTime().split(":")[2];
        	stationtime = mon.getTime().substring(0, 10);
        	//data = xs+":"+fz+fz1;
        	data =mon.getTime().substring(11, 16);
        }
		if(key1.contains("AirTemprature")){
			for(int i=0;i<=AirTemprature.length-1;i++){
				
				//String a = "AirTemprature"+(i+1);
				if(key.equals( "AirTemprature"+(i+1))){
		        	//data = vo_num(mon.getAirTemprature().toString());
					data = AirTemprature[i];
		        }
			}
		}
		if(key1.contains("SoilTemprature")){
        	/*data = vo_num(mon.getSoilTemprature());*/
            for(int i=0;i<=SoilTemprature.length-1;i++){
				
				//String a = "AirTemprature"+(i+1);
				if(key.equals( "SoilTemprature"+(i+1))){
		        	//data = vo_num(mon.getAirTemprature().toString());
					data = SoilTemprature[i];
		        }
			}
        }if(key1.contains("AirHumidity")){
        	/*data = vo_num(mon.getSoilTemprature());*/
            for(int i=0;i<=AirHumidity.length-1;i++){
				
				//String a = "AirTemprature"+(i+1);
				if(key.equals( "AirHumidity"+(i+1))){
		        	//data = vo_num(mon.getAirTemprature().toString());
					data = AirHumidity[i];
		        }
			}
        }
        if(key1.contains("SoilHumidity")){
        	/*data = vo_num(mon.getSoilTemprature());*/
            for(int i=0;i<=SoilHumidity.length-1;i++){
				
				//String a = "AirTemprature"+(i+1);
				if(key.equals( "SoilHumidity"+(i+1))){
		        	//data = vo_num(mon.getAirTemprature().toString());
					data = SoilHumidity[i];
		        }
			}
        }
        if(key1.contains("Radiation")){
        	/*data = vo_num(mon.getSoilTemprature());*/
            for(int i=0;i<=Radiation.length-1;i++){
				
				//String a = "AirTemprature"+(i+1);
				if(key.equals( "Radiation"+(i+1))){
		        	//data = vo_num(mon.getAirTemprature().toString());
					data = Radiation[i];
		        }
			}
        }
        if(key1.contains("CO2")){
        	/*data = vo_num(mon.getSoilTemprature());*/
            for(int i=0;i<=CO2.length-1;i++){
				
				//String a = "AirTemprature"+(i+1);
				if(key.equals( "CO2"+(i+1))){
		        	//data = vo_num(mon.getAirTemprature().toString());
					data = CO2[i];
		        }
			}
        }
        if(key1.contains("Pressure")){
        	/*data = vo_num(mon.getSoilTemprature());*/
            for(int i=0;i<=Pressure.length-1;i++){
				
				//String a = "AirTemprature"+(i+1);
				if(key.equals( "Pressure"+(i+1))){
		        	//data = vo_num(mon.getAirTemprature().toString());
					data = Pressure[i];
		        }
			}
        }
        if(key1.contains("SoilHumiditys")){
        	/*data = vo_num(mon.getSoilTemprature());*/
            for(int i=0;i<=SoilHumiditys.length-1;i++){
				
				//String a = "AirTemprature"+(i+1);
				if(key.equals( "SoilHumiditys"+(i+1))){
		        	//data = vo_num(mon.getAirTemprature().toString());
					data = SoilHumiditys[i];
		        }
			}
        }
        if(key1.contains("PAR")){
        	/*data = vo_num(mon.getSoilTemprature());*/
            for(int i=0;i<=PAR.length-1;i++){
				
				//String a = "AirTemprature"+(i+1);
				if(key.equals( "PAR"+(i+1))){
		        	//data = vo_num(mon.getAirTemprature().toString());
					data = PAR[i];
		        }
			}
        }
        if(key1.contains("CAnemometer")){
        	/*data = vo_num(mon.getSoilTemprature());*/
            for(int i=0;i<=CAnemometer.length-1;i++){
				
				//String a = "AirTemprature"+(i+1);
				if(key.equals( "CAnemometer"+(i+1))){
		        	//data = vo_num(mon.getAirTemprature().toString());
					data = CAnemometer[i];
		        }
			}
        }
        if(key1.contains("Dogvane")){
        	/*data = vo_num(mon.getSoilTemprature());*/
            for(int i=0;i<=Dogvane.length-1;i++){
				
				//String a = "AirTemprature"+(i+1);
				if(key.equals( "Dogvane"+(i+1))){
		        	//data = vo_num(mon.getAirTemprature().toString());
					data = Dogvane[i];
		        }
			}
        }
        if(key1.contains("Rainfall")){
        	/*data = vo_num(mon.getSoilTemprature());*/
            for(int i=0;i<=Rainfall.length-1;i++){
				
				//String a = "AirTemprature"+(i+1);
				if(key.equals( "Rainfall"+(i+1))){
		        	//data = vo_num(mon.getAirTemprature().toString());
					data = Rainfall[i];
		        }
			}
        }
        if(key1.contains("Fengshipc")){
        	/*data = vo_num(mon.getSoilTemprature());*/
            for(int i=0;i<=Fengshipc.length-1;i++){
				
				//String a = "AirTemprature"+(i+1);
				if(key.equals( "Fengshipc"+(i+1))){
		        	//data = vo_num(mon.getAirTemprature().toString());
					data = Fengshipc[i];
		        }
			}
        }
        if(key1.contains("AAnemometer")){
        	/*data = vo_num(mon.getSoilTemprature());*/
            for(int i=0;i<=AAnemometer.length-1;i++){
				
				//String a = "AirTemprature"+(i+1);
				if(key.equals( "AAnemometer"+(i+1))){
		        	//data = vo_num(mon.getAirTemprature().toString());
					data = AAnemometer[i];
		        }
			}
        }
        if(key1.contains("MAnemometer")){
        	/*data = vo_num(mon.getSoilTemprature());*/
            for(int i=0;i<=MAnemometer.length-1;i++){
				
				//String a = "AirTemprature"+(i+1);
				if(key.equals( "MAnemometer"+(i+1))){
		        	//data = vo_num(mon.getAirTemprature().toString());
					data = MAnemometer[i];
		        }
			}
        }
        if(key1.contains("FengshiKE")){
        	/*data = vo_num(mon.getSoilTemprature());*/
            for(int i=0;i<=FengshiKE.length-1;i++){
				
				//String a = "AirTemprature"+(i+1);
				if(key.equals( "FengshiKE"+(i+1))){
		        	//data = vo_num(mon.getAirTemprature().toString());
					data = FengshiKE[i];
		        }
			}
        }
        if(key1.contains("Jingfushe")){
        	/*data = vo_num(mon.getSoilTemprature());*/
            for(int i=0;i<=Jingfushe.length-1;i++){
				
				//String a = "AirTemprature"+(i+1);
				if(key.equals( "Jingfushe"+(i+1))){
		        	//data = vo_num(mon.getAirTemprature().toString());
					data = Jingfushe[i];
		        }
			}
        }
        if(key1.contains("Guangliangdu")){
        	/*data = vo_num(mon.getSoilTemprature());*/
            for(int i=0;i<=Guangliangdu.length-1;i++){
				
				//String a = "AirTemprature"+(i+1);
				if(key.equals( "Guangliangdu"+(i+1))){
		        	//data = vo_num(mon.getAirTemprature().toString());
					data = Guangliangdu[i];
		        }
			}
        }
        if(key1.contains("oilpH")){
        	/*data = vo_num(mon.getSoilTemprature());*/
            for(int i=0;i<=oilpH.length-1;i++){
				
				//String a = "AirTemprature"+(i+1);
				if(key.equals( "oilpH"+(i+1))){
		        	//data = vo_num(mon.getAirTemprature().toString());
					data = oilpH[i];
		        }
			}
        }
        if(key1.contains("SoilHeatFlux")){
        	/*data = vo_num(mon.getSoilTemprature());*/
            for(int i=0;i<=SoilHeatFlux.length-1;i++){
				
				//String a = "AirTemprature"+(i+1);
				if(key.equals( "SoilHeatFlux"+(i+1))){
		        	//data = vo_num(mon.getAirTemprature().toString());
					data = SoilHeatFlux[i];
		        }
			}
        }
		
		return data;
	}
	public static String vo_num(String total){
		String vo="";
		double sum=0;
		String [] temp = null;
		temp =  total.split(",");
		for(String str:temp){
			sum += Double.parseDouble(str);
		}
		return String .format("%.2f",sum/temp.length);

	}
	/**
	 * 根据list列表是否滚动到底部来决定显示加载加载进度条
	 * 
	 */
	public static void showsearch(int resource) {	
		
		LayoutInflater inflater = (LayoutInflater) foreverContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(resource, null);
		//设置时间为中国
			
			//获取日期
			emy_year =  mYear; //获取当前年份 	       
			emy_month = mMonth;
			emy_day = mDay;
			emy_hour = mHour;
			emy_minute = mMinute;
		// 创建Popup
		popup = new PopupWindow(layout, LayoutParams.FILL_PARENT,
				LayoutParams.WRAP_CONTENT, true);
		
		popup.setBackgroundDrawable(new BitmapDrawable());
		popup.setAnimationStyle(R.style.PopupAnimation);
		popup.showAtLocation(((Activity) foreverContext).findViewById(R.id.horizontalScrollView1), Gravity.BOTTOM, 0, 90);
		popup.update();
		Button but = (Button)layout.findViewById(R.id.search);
		//获取控件
		DatePicker startdate_picker = (DatePicker)layout.findViewById(R.id.satartdatepicker);
		DatePicker enddate_picker = (DatePicker)layout.findViewById(R.id.enddatepicker);
		
		TimePicker starttime_picker = (TimePicker) layout.findViewById(R.id.starttime_picker);
		TimePicker endtime_picker = (TimePicker) layout.findViewById(R.id.endtimepicker);
		
		//设置时间格式为24小时
		starttime_picker.setIs24HourView(true);
		endtime_picker.setIs24HourView(true);
		smy_year = emy_year;
		smy_month = emy_month;
		smy_hour = emy_hour;
		smy_minute= emy_minute;
		smy_day = emy_day;
	
		satartdata = smy_year+"-"+(smy_month+1)+"-"+smy_day+" "+smy_hour+":"+smy_minute;
		enddata = emy_year+"-"+(emy_month+1)+"-"+emy_day+" "+emy_hour+":"+emy_minute;
		//日历控件
		startdate_picker.init(smy_year, smy_month, smy_day, new OnDateChangedListener() {
		//日期修改的单击事件
		public void onDateChanged(DatePicker view, int year, int monthOfYear,
		int dayOfMonth) {
			int my_year = year;
			int my_month = monthOfYear;
			int my_day = dayOfMonth;
			int my_hour =0;
			int my_minute=0;
		//显示时间
			satartdata = my_year+"-"+(my_month+1)+"-"+my_day+" "+my_hour+":"+my_minute;
		}
		});
		//为时间控件添加事件
		starttime_picker.setOnTimeChangedListener(new OnTimeChangedListener() {

		public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
		smy_hour = hourOfDay;
		smy_minute = minute;
		//显示时间
		satartdata = smy_year+"-"+(smy_month+1)+"-"+smy_day+" "+smy_hour+":"+smy_minute;
		}
		});
		
		//为时间控件添加事件
		enddate_picker.init(emy_year, emy_month, emy_day, new OnDateChangedListener() {

			//日期修改的单击事件
			public void onDateChanged(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
				int my_year = year;
				int my_month = monthOfYear;
				int my_day = dayOfMonth;
				int my_hour =0;
				int my_minute=0;
			//显示时间
				enddata = my_year+"-"+(my_month+1)+"-"+my_day+" "+my_hour+":"+my_minute;
			}
			});
		//为时间控件添加事件
		endtime_picker.setOnTimeChangedListener(new OnTimeChangedListener() {

			
				public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
				emy_hour = hourOfDay;
				emy_minute = minute;
				//显示时间
				enddata = emy_year+"-"+(emy_month+1)+"-"+emy_day+" "+emy_hour+":"+emy_minute;
				}
				});
		
		
		but.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				searchparams.add(new BasicNameValuePair("stationId",stationId));				
				searchparams.add(new BasicNameValuePair("startTime",satartdata));
				searchparams.add(new BasicNameValuePair("endTime", enddata));
				 Toast.makeText(con ,  "选中第" +stationId+ "行 "+satartdata+"结束："+enddata , Toast.LENGTH_LONG  ).show();  
			/*
				 InputStream	input = PublicHttpActivity.getJSONData(con,"http://202.196.80.146:86/WebService.asmx/GetStationData",searchparams);
				monitor = ReadFile.getFile(input);
				createTab(monitor);
			*/	
				
				tableAdapter.notifyDataSetChanged();
			}

		});
		// 添加到脚页显示
		/*
		 * popup.showAtLocation(parent, Gravity.BOTTOM, 0, 70);
		 */
		// progress.setVisibility(View.VISIBLE);
	}
	
	public static void showinfolist(final Activity act,String info,String key) {
		List<Map<String, Object>> arraylist = new ArrayList<Map<String, Object>>();
		
		LayoutInflater inflater = (LayoutInflater) foreverContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(R.layout.list, null);
		ListView list = (ListView) layout.findViewById(R.id.listviewinfo);
		String [] temp = null;
		temp =  info.split(",");
		int i=1;
		for(String str:temp){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("title", key+""+i);
			map.put("num", str);
			i++;
			arraylist.add(map);
		}
		
		SimpleAdapter listAdapter = new SimpleAdapter(act,arraylist,R.layout.infolist, 
				
                new String[]{"title","num"}, 
                new int[]{R.id.name,R.id.val});

		 list.setAdapter(listAdapter);
	
		// 创建Popup 
		popup = new PopupWindow(layout, LayoutParams.FILL_PARENT,
				LayoutParams.WRAP_CONTENT, true);

		popup.setBackgroundDrawable(new BitmapDrawable());
		popup.setAnimationStyle(R.style.PopupAnimation);
		popup.showAtLocation(((Activity) foreverContext).findViewById(R.id.horizontalScrollView1), Gravity.BOTTOM, 0, 90);
		popup.update();

		// 添加到脚页显示
		/*
		 * popup.showAtLocation(parent, Gravity.BOTTOM, 0, 70);
		 */
		// progress.setVisibility(View.VISIBLE);
	}
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		  if (keyCode == KeyEvent.KEYCODE_SEARCH) {
			if (con.getApplicationInfo().targetSdkVersion>= Build.VERSION_CODES.ECLAIR) {
				showsearch(R.layout.date_view);	 
			} else {
				Toast.makeText(con ,  "选中##############" ,  Toast.LENGTH_LONG  ).show(); 
			}
			
		} 
		if (keyCode == KeyEvent.KEYCODE_BACK) {
				if (con.getApplicationInfo().targetSdkVersion>= Build.VERSION_CODES.ECLAIR) {
					event.startTracking();
				} else {
					con.onBackPressed(); 
				}
				
		}
		  return true;
	}
	
	/**  
     * 得到几天前的时间  
     *   
     * @param d  
     * @param day  
     * @return  
     */  
    public static Date getDateBefore(Date d, int day) {   
        Calendar now = Calendar.getInstance();   
        now.setTime(d);   
        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);   
        return now.getTime();   
    }  
    
    private void initFloatView() {
		//获取WindowManager
    	wm=(WindowManager)con.getSystemService("Window");
        //设置LayoutParams(全局变量）相关参数
    	wmParams = new WindowManager.LayoutParams();
    	
        wmParams.type=LayoutParams.TYPE_PHONE;   //设置window type
        wmParams.format=PixelFormat.RGBA_8888;   //设置图片格式，效果为背景透明
        //设置Window flag
         wmParams.flags=LayoutParams.FLAG_NOT_TOUCH_MODAL 
                               | LayoutParams.FLAG_NOT_FOCUSABLE;

        //以屏幕左上角为原点，设置x、y初始值
        wmParams.x=0;
        wmParams.y=0;
        //设置悬浮窗口长宽数据
        wmParams.width=50;
        wmParams.height=50;
    	
        //创建悬浮按钮
        createLeftFloatView();
        createRightFloatView();
		
	}
	
	
	
	/**
	 * 创建左边悬浮按钮
	 */
    private void createLeftFloatView(){
    	leftbtn=new ImageView(con);
    	leftbtn.setImageResource(R.drawable.prev);
    	leftbtn.setAlpha(0);
    	leftbtn.setOnClickListener(new View.OnClickListener() {	
			public void onClick(View arg0) {
				//上一篇
				if (currentPage_int==1) {
					currentPage_int=1;
					Toast.makeText(con,"已经是当天最新值！" , Toast.LENGTH_LONG).show();
					//没有最新值
				}else{
					currentPage_int-- ;
					
				}
				currentPage = currentPage_int+"" ;
				params.clear();
				startstr1 = stationtime+" "+"00:00";
		        endtime1 = stationtime+" "+"23:59";
				params.add(new BasicNameValuePair("startTime",startstr1));
				params.add(new BasicNameValuePair("endTime", endtime1));
				params.add(new BasicNameValuePair("stationId",stationId ));
				params.add(new BasicNameValuePair("currentPage",currentPage));
				params.add(new BasicNameValuePair("pageSize", "18"));
				
				InputStream	input = PublicHttpActivity.getJSONData(App.GetStationDataPath,params);
				monitor = ReadFile.getFile(input);
				if (monitor!=null) {
					ShareData.monitor=monitor;
					//getdatadefult(monitor);
					getview(monitor);
				}
				
				
			}
		});
    	//调整悬浮窗口
        wmParams.gravity=Gravity.LEFT|Gravity.CENTER_VERTICAL;
        //显示myFloatView图像
        wm.addView(leftbtn, wmParams);
        
    }
    /**
	 * 创建右边悬浮按钮
	 */
    private void createRightFloatView(){
    	rightbtn=new ImageView(con);
    	rightbtn.setImageResource(R.drawable.next);
    	rightbtn.setAlpha(0);
    	rightbtn.setOnClickListener(new View.OnClickListener() {	
			public void onClick(View arg0) {
				//下一篇
				currentPage_int++ ;
				currentPage = currentPage_int+"" ;
				params.clear();
				startstr1 = stationtime+" "+"00:00";
		        endtime1 = stationtime+" "+"23:59";
				params.add(new BasicNameValuePair("startTime",startstr1));
				params.add(new BasicNameValuePair("endTime", endtime1));
				params.add(new BasicNameValuePair("stationId",stationId ));
				params.add(new BasicNameValuePair("currentPage",currentPage));
				params.add(new BasicNameValuePair("pageSize", "18"));
				
				thread=new HttpThread(handlerwdy);
				String url=URL;//webserivce地址
				
				String nameSpace = NAMESPACE;
				String methodName = METHOD_NAME;
				
				
				
				
				InputStream	input = PublicHttpActivity.getJSONData(App.GetStationDataPath,params);
				monitor = ReadFile.getFile(input);
				
				
				
				if (monitor!=null) {
					ShareData.monitor=monitor;
					if (monitor.getDetails().get(0).getTime().substring(0, 10).equals(stationtime)) {
						thread.doStart(url,nameSpace,methodName, params ,con);   //启动线程
						
						getview(monitor);
					}else {
						Toast.makeText(con,"请设置时间查询其他日期!" , Toast.LENGTH_LONG).show();
						currentPage_int--;
					}
					
					
				}else{
					
					Toast.makeText(con,"请设置时间查询其他日期!" , Toast.LENGTH_LONG).show();
					currentPage_int--;
						
				}
				
			}
		});
    	//调整悬浮窗口
        wmParams.gravity=Gravity.RIGHT|Gravity.CENTER_VERTICAL;
        //显示myFloatView图像
        wm.addView(rightbtn, wmParams);
    }
    /**
     * 图片渐变显示处理
     */
    /*private Handler mHandler = new Handler()
	{
		public void handleMessage(Message msg) {
			if(msg.what==1 && mAlpha<255){   
				//System.out.println("---"+mAlpha);					
				mAlpha += 50;
				if(mAlpha>255)
					mAlpha=255;
		        leftbtn.setAlpha(mAlpha);
		        leftbtn.invalidate();
		        rightbtn.setAlpha(mAlpha);
		        rightbtn.invalidate();
				if(!isHide && mAlpha<255)
					mHandler.sendEmptyMessageDelayed(1, 100);
			}else if(msg.what==0 && mAlpha>0){
				//System.out.println("---"+mAlpha);
				mAlpha -= 10;
				if(mAlpha<0)
					mAlpha=0;
		        leftbtn.setAlpha(mAlpha);
		        leftbtn.invalidate();
		        rightbtn.setAlpha(mAlpha);
		        rightbtn.invalidate();
		        if(isHide && mAlpha>0)
		        	mHandler.sendEmptyMessageDelayed(0, 100);
			}			
		}
	};/*
	
    private void showFloatView(){
    	isHide = false;
    	mHandler.sendEmptyMessage(1);
    }
    
    private void hideFloatView(){
		new Thread(){
			public void run() {
				try {
	                Thread.sleep(1500);
	                isHide = true;
	                mHandler.sendEmptyMessage(0);
	            } catch (Exception e) {
	                
	            }
			}
		}.start();
    }
    
   /* @Override
	public boolean onTouchEvent(MotionEvent event) {
    	switch (event.getAction()) {
    		case MotionEvent.ACTION_MOVE:
			case MotionEvent.ACTION_DOWN:
				//System.out.println("========ACTION_DOWN");
				showFloatView();			
				break;
			case MotionEvent.ACTION_UP:
				//System.out.println("========ACTION_UP");
				hideFloatView();				
				break;
		}
		back_pic true;
	}
   
    @Override
    public boolean onTouchEvent(MotionEvent event) {        
    	// 执行touch 事件
    	super.onTouchEvent(event);
    	back_pic ListViewAdapter.detector.onTouchEvent(event);
    	}  
    @Override   
    public boolean dispatchTouchEvent(MotionEvent ev){
    	//先执行滑屏事件
    	ListViewAdapter.detector.onTouchEvent(ev);        
    	super.dispatchTouchEvent(ev);        
    	back_pic true;
    } */

    public void onStart(){
		super.onStart();
	}
	public void onStop(){
		super.onStop();
		//在程序退出(Activity销毁）时销毁悬浮窗口
    	//wm.removeView(leftbtn);
    	//wm.removeView(rightbtn);
	}
	public void onPause(){
		super.onPause();
		//在程序退出(Activity销毁）时销毁悬浮窗口
		//hideFloatView();
	}
	public void onResume(){
		super.onResume();
	}

	public void onDestory(){
		super.onDestroy();
		//在程序退出(Activity销毁）时销毁悬浮窗口
    	/*wm.removeView(leftbtn);
    	wm.removeView(rightbtn);*/
	}
}

