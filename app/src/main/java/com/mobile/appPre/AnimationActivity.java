package com.mobile.appPre;

import com.mobile.appPre.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

public class AnimationActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//全屏展示本窗口，无标题
		setContentView(R.layout.welcome);
        Start();
	} 
	 public void Start() {
         new Thread() {
                 public void run() {
                         try {
                                 Thread.sleep(3*1000);
                         } catch (InterruptedException e) {
                                 e.printStackTrace();
                         }
                         Intent intent = new Intent();
                         intent.setClass(AnimationActivity.this, LoginActivity.class);
                         startActivity(intent);
                         finish();
                 }
         }.start();
 }
}