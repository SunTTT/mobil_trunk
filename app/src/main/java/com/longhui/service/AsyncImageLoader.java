package com.longhui.service;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;



import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;

//该类的主要作用是实现图片的异步加载
public class AsyncImageLoader {
	//图片缓存对象
		//键是图片的URL，值是一个SoftReference对象，该对象指向一个Drawable对象
	private Map<String, SoftReference<Drawable>> imageCache ;
	
	 public AsyncImageLoader() {  
		  imageCache = new HashMap<String, SoftReference<Drawable>>();  
	 }  

	//实现图片的异步加载
	public Drawable loadDrawable(final String urls, final imageCallback imageCallback){
		//查询缓存，查看当前需要下载的图片是否已经存在于缓存当中
		if(imageCache.containsKey(urls)){
			SoftReference<Drawable> softReference=imageCache.get(urls);
			Drawable drawable = softReference.get();
			if(drawable != null){
				return drawable;
			}
		}
		
		final Handler handler=new Handler(){
			@Override
			public void handleMessage(Message msg) {
				
				imageCallback .imageLoaded((Drawable) msg.obj,urls);
			}
		};
		//新开辟一个线程，该线程用于进行图片的下载
		new Thread(){
			public void run() {
				Drawable drawable=loadImageFromUrl(urls);
				imageCache.put(urls, new SoftReference<Drawable>(drawable));
				Message message = handler.obtainMessage(0, drawable);
				handler.sendMessage(message);
			}

			
		}.start();
		return null;
	}
	
	
	/*public static Drawable loadImageFromUrl(String urls){
		Options options=new Options();  
		options.inSampleSize=2;  
		Bitmap bitmap=BitmapFactory.decodeFile(urls, options);  
		Drawable drawable=new BitmapDrawable(bitmap);  
		back_pic drawable;

	}*/
	
	public static Drawable loadImageFromUrl(String urls){
		URL m;
		InputStream i = null;
		try {
			m = new URL(urls);
			i = (InputStream) m.getContent();
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Drawable d = Drawable.createFromStream(i, "src");
		return d;
		
	}
	
	
	//回调接口
	public interface imageCallback{
		public void imageLoaded(Drawable imageDrawable,String urls);
	}

}
