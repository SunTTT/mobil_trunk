package com.mobile.appPre;
/**  
 * GalleryAdapter.java
 * @version 1.0
 * @author Haven
 * @createTime 2011-12-9 ����05:04:34
 */


import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Gallery;

import com.longhui.service.MyGallery;
import com.longhui.service.MyImageView;

public class GalleryAdapter extends BaseAdapter {

	//public Position po = new Position();
	private Bitmap bitmap = null;
	private Context context;
	
	private String[] urls;
	/*public GalleryAdapter(Context context, String stationid) {
		id = stationid ;
		
		this.context = context;
	}*/

	


	public GalleryAdapter(PshowActivity pshowActivity, String[] jhk) {
		// TODO Auto-generated constructor stub
		urls = jhk ;
		
		this.context = pshowActivity;	
	}

	public int getCount() {
		
		return urls.length;
	}

	public Object getItem(int position) {
		return null;
	}

	public long getItemId(int position) {
		
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		//Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), images[position]);
		Bitmap bmp =returnBitMap(urls[position]);
		//Bitmap bmp =returnBitMap(urls[position]);
		MyImageView view = new MyImageView(context, bmp.getWidth(), bmp.getHeight());
		
		//view.setLayoutParams(new Gallery.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		view.setLayoutParams(new MyGallery.LayoutParams(LayoutParams.WRAP_CONTENT+bmp.getWidth()+50, LayoutParams.FILL_PARENT));
		view.setImageResource(position);
		view.setImageBitmap(bmp);
		return view;
	}

	//url��ʾͼƬ
		public Bitmap returnBitMap(String url) {   
			   URL myFileUrl = null;   
			   //Bitmap bitmap = null;   
			   InputStream is = null;
			   try {   
			    myFileUrl = new URL(url);  
			   
			   } catch (MalformedURLException e) {   
				   Log.i("uri+++++++++", "myFileUrl="+myFileUrl);
			    e.printStackTrace();   
			   }   
			   try {   
			    HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();   
			    conn.setDoInput(true);   
			    conn.connect();   
			    is = conn.getInputStream();   
			    bitmap = BitmapFactory.decodeStream(is); 
			    if(bitmap == null) {
			    	Log.i("++++++", "bipmap is null");
			    }
			    is.close();
			    return bitmap;
			    
			   } catch (IOException e) { 
				   Log.i("+++++++++++", "for test");
			    e.printStackTrace();   
			   }  
			   return null;   
			} 
}
