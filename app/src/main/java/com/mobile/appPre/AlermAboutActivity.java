package com.mobile.appPre;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import com.mobile.appPre.R;

public class AlermAboutActivity extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//全屏展示本窗口，无标题
		setContentView(R.layout.aboutpage);
		
	}
	
	public void goBack(View v){
		this.finish();
	}

}
