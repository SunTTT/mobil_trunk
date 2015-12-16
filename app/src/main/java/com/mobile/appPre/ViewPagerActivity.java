/*******************************************************************************
 * Copyright 2011, 2012 Chris Banes.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.mobile.appPre;

import com.longhui.service.HackyViewPager;

import uk.co.senab.photoview.PhotoView;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;

public class ViewPagerActivity extends Activity {

	private ViewPager mViewPager;
	
	private static String[] jhk ,stationtimes;
	private static int stationid ;
	private static String stationName ;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mViewPager = new HackyViewPager(this);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(mViewPager);
		
		//得到从上个Activity传来的数据
		Intent getintent = getIntent();
		Bundle getdata = getintent.getBundleExtra("data");
		stationid =  getdata.getInt("id");
		stationName = getdata.getString("stationName");
		jhk = getdata.getStringArray("jhk");
		stationtimes = getdata.getStringArray("stationtimes");
				
		mViewPager.setAdapter(new SamplePagerAdapter());
		mViewPager.setCurrentItem(stationid);
	}

	static class SamplePagerAdapter extends PagerAdapter {
		@Override
		public int getCount() {
			return jhk.length;
		}

		@Override
		public View instantiateItem(ViewGroup container, int position) {
			PhotoView photoView= new PhotoView(container.getContext());
			Bitmap bmp=photoView.returnBitMap(jhk[position],stationName,stationtimes[position]);
			photoView.setImageBitmap(bmp);
			// Now just add PhotoView to ViewPager and back_pic it
			container.addView(photoView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

			return photoView;
		}
		

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}

	}

}
