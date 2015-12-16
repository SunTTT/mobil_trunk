package com.mobile.appPre;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import com.mobile.appPre.R;

public class AlermTabHost extends TabActivity{
	TabHost tabHost;
	TabSpec firstTabSpec,secondTabSpec;
	private int myMenuRes[] = {
			R.drawable.tab1,
			R.drawable.tab2
	};
	public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
		 requestWindowFeature(Window.FEATURE_NO_TITLE);//全屏展示本窗口，无标题
		 setContentView(R.layout.alermtabhost);
		 tabHost = getTabHost(); 
		 tabHost.setBackgroundResource(R.drawable.nav_background);
		 firstTabSpec = tabHost.newTabSpec("tid1");
		 secondTabSpec = tabHost.newTabSpec("tid2");
		 firstTabSpec.setIndicator("站点监控", getResources().getDrawable(
					myMenuRes[0]));
		 secondTabSpec.setIndicator("短信管理", getResources().getDrawable(
					myMenuRes[1]));
		 Intent intent = new Intent();
			
		 //intent.putExtra("data",dataid);
		 intent.setClass(this, ScientificData.class);
		 firstTabSpec.setContent(intent);
		
		 Intent Chartintent = new Intent();
		 //Chartintent.putExtra("data",dataid);
		 Chartintent.setClass(this, ChartDemo.class);
		 secondTabSpec.setContent(Chartintent);
		 
		tabHost.addTab(firstTabSpec);
		tabHost.addTab(secondTabSpec);
		
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
