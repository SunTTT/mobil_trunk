package com.mobile.appPre;


import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.mobile.appPre.R;
import com.mobile.publiclass.HttpThread;
import com.mobile.publiclass.ShareData;
import com.mobile.publiclass.SysExitUtil;

public class SiteTabHost extends FragmentActivity {
	// 声名TabHost对象	
	private FragmentTabHost tabHost;
	//定义一个布局  
    private LayoutInflater layoutInflater;  
    
	String tabs[] = {"数据监控","数据分析","图像监控"};
	Class<?> cls[] = {ScientificData.class,ChartDemo.class,NewMainactivity.class};
	
	int[] topbar_image_array = { R.drawable.tab_site_img,
				R.drawable.tab_picture_img};

	Integer[] topbar_text_array = { R.string.tab_aution,
				R.string.tab_my_achieve };
	private PublicApplication App = new PublicApplication();
		/*-- Toolbar底部菜单选项下标--*/
	private int TOOLBAR_ITEM_DEFAULT = 0;// 首页
		
		
	HttpThread thread=null; //线程对像 
	private String METHOD_NAME = ""; // 函数名 
	private List<NameValuePair> params = new ArrayList<NameValuePair>();
		
	TabSpec firstTabSpec,secondTabSpec,threeTabSpec;	
	
	private int myMenuRes[] = {
				R.drawable.tab1,
				R.drawable.tab2,
				R.drawable.tab3,
			
		};				
	//生成消息对象   
		static class MyHandler extends Handler { 
			WeakReference<SiteTabHost> mActivity; 
			MyHandler(SiteTabHost activity) { 
				mActivity = new WeakReference<SiteTabHost>(activity);
				} 
			@Override 
			public void handleMessage(Message m){		
				switch(m.what){  
				}
				}
		};
			//实例化MyHandler
		MyHandler handlerwdy = new MyHandler(this);  	
		
		private Intent getintent ;			 
		private Bundle getdata ;
		private String stationId;
		private String stationName ;	

		private Bundle dataid;
		public void onCreate(Bundle savedInstanceState) {
			 getintent =  getIntent();			 
			 getdata = getintent.getBundleExtra("data");
			 stationId =  getdata.getString("stationId");
			Log.e("SiteTabHost","stationid"+stationId);
			 stationName = getdata.getString("stationName");
			Log.e("SiteTabHost","stationname"+stationName);
			TOOLBAR_ITEM_DEFAULT = Integer.parseInt(getdata.getString("TOOLBAR_ITEM"));
			Log.e("SiteTabHost","toolbar_item"+getdata.getString("TOOLBAR_ITEM"));
				
			 super.onCreate(savedInstanceState);
			 SysExitUtil.activityList.add(SiteTabHost.this);
			 requestWindowFeature(Window.FEATURE_NO_TITLE);//全屏展示本窗口，无标题
			 setContentView(R.layout.sitetabhost);	
			 
			//实例化布局对象  
		        layoutInflater = LayoutInflater.from(this);            
		        //实例化TabHost对象，得到TabHost  
		        FragmentManager fManager=getSupportFragmentManager();		    		        
		        tabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);  
		        tabHost.setup(this, fManager, R.id.realtabcontent); 
		        dataid = new Bundle();
			     ShareData.stationName =stationName;
				 ShareData.stationId = stationId;
			     dataid.putString("stationId", stationId);
			     dataid.putString("stationName", stationName);				 										
		        //tabHost.getTabWidget().setVisibility(View.GONE);		          
		        //得到fragment的个数  
		        int count = cls.length;     
		                  
		        for(int i = 0; i < count; i++){    
		            //为每一个Tab按钮设置图标、文字和内容  
		            TabSpec tabSpec = tabHost.newTabSpec(tabs[i]).setIndicator(getTabItemView(i));	       
		            //将Tab按钮添加进Tab选项卡中  
		            tabHost.addTab(tabSpec, cls[i], null);  
		            //设置Tab按钮的背景  
//		            tabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.layout.select_bg);
		            
		        }		        
			 
			 tabHost.setCurrentTab(TOOLBAR_ITEM_DEFAULT);
						 				
						
			 tabHost.setOnTabChangedListener(new  OnTabChangeListener(){ 
				 public void onTabChanged(String tabId) {
	           		 	
	            	 thread=new HttpThread(handlerwdy);
		 				String url=App.requestURL;//webserivce地址
		 				String nameSpace = App.MyNamespace;	
		 			      if (tabId.equals(tabs[0])) {
		 	                	METHOD_NAME = App.GetStationData;
		 			      }else if ((tabId.equals(tabs[1]))){
			 						METHOD_NAME = App.GetStationData;
		 			      }else if ((tabId.equals(tabs[2]))) {
		 						METHOD_NAME =App.GetPictures;
		 					}
		 				String methodName = METHOD_NAME;
		 				//需调用webservice名称  　　  
		 				thread.doStart(url,nameSpace,methodName, params ,SiteTabHost.this);   
				 }
			 });	
			    	 	    					     				     					 	   	       
           }       	
				 
		
	    private View getTabItemView(int index){  
	        View view = layoutInflater.inflate(R.layout.tab_item_view, null);  
	      
	        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);  
	        imageView.setImageResource(myMenuRes[index]);  	     	          
	        TextView textView = (TextView) view.findViewById(R.id.textview);          
	        textView.setText(tabs[index]);  
	      
	        return view;  
	    } 
	    

		@Override	
		public boolean dispatchKeyEvent(KeyEvent event) {	
			// TODO Auto-generated method stub	
			if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
			      /*
			       * 注意在if判断中要加一个event.getAction() == KeyEvent.ACTION_DOWN判断，
			       * 因为按键有两个事件ACTION_DOWN和ACTION_UP，也就是按下和松开，
			       * 如果不加这个判断，代码会执行两遍
			       */
			      if (event.getAction() == KeyEvent.ACTION_DOWN) {
			      }
			      else{
			    	  this.finish();
			    	  //System.exit(0);
			      }			      
			      return true;
			    }

			return super.dispatchKeyEvent(event);
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
