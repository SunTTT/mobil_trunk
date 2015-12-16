package com.mobile.publiclass;

import java.io.InputStream;
import java.util.List;

import org.apache.http.NameValuePair;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.longhui.entity.LoginStation;
import com.longhui.entity.Monitor;
import com.longhui.entity.SiteListLib;
import com.longhui.service.ReadFile;

//线程类
public class HttpThread extends Thread{
	ProgressDialog progressDialog = null;
	private Handler handle=null;
	String url=null;
	String methodname=null;
	String Context ;
	boolean Network =false;
	List<NameValuePair> params=null;
	private LoginStation checklogin = null;
	private static Monitor monitor = null;
	public HttpThread(Handler hander){
		handle=hander;    
	}    
	//线程开始  
	public void doStart(String url, String nameSpace, String methodName,List<NameValuePair> params,final Activity context)
	{       
		Network = PublicHttpActivity.isNetworkAvailable(context);
		
		if(Network){
			// 把参数传进来    
			methodname = methodName ;
			this.url=url+"/"+methodName;            
			this.params=params;            //告诉使用者，请求开始了            
			progressDialog=new ProgressDialog(context);
			progressDialog.setTitle("请求网络资源");            
			progressDialog.setMessage("正在请求，请稍等......");            
			progressDialog.setIndeterminate(true);    
			progressDialog.setCancelable(true);
			progressDialog.show();                            
			this.start(); 
			
		}else{
			// 构造消息，程序出错了

			Message message=handle.obtainMessage();//从handle的messagequeue中取出一个message 避免重复创建
			Bundle b=new Bundle();        
			message.what=2;			
			b.putString("error","请确保当前网络状态...");
			message.setData(b);         
			handle.sendMessage(message);
		}
		         
	}    
	/**     */ 
	@Override 
	public void run() {         
		// TODO Auto-generated method stub     
		super.run();     
		try{    
				
				//web service请求,result为返回结果           
				Log.i("url",""+url);
				Log.i("params",""+params);
				InputStream  res = PublicHttpActivity.getJSONData(url,params);
				Log.i("InputStream",""+res);
				//取消进度对话框     
				progressDialog.cancel();
				
				if (methodname.equals("GetStationData")) {
					monitor = ReadFile.getFile(res);
					if (monitor!=null) {

						Message message=handle.obtainMessage();
	                    Bundle data=new Bundle();                     
	                    message.what=1; //这里是消息的类型 
	                    
						data.putBoolean("data", true); //这里是消息传送的数据
	                    message.setData(data);  
	                    Log.v("message", "-----发送的message-----" + message);
	                    handle.sendMessage(message);
	                    
					}else{
		            	  Message message=handle.obtainMessage();
		            	  Bundle b=new Bundle();                 
		            	  message.what=1;                 
		            	  b.putBoolean("data", false);
		            	  message.setData(b);
		            	  handle.sendMessage(message); 
		            	}  
					
				}else if (methodname.equals("GetStationsByUser")) {
					checklogin = ReadFile.getLogin(res);					
					if(checklogin.getAllow()){                     
						//构造消息,验证通过了                    
						Message message=handle.obtainMessage();
	                    Bundle data=new Bundle();                     
	                    message.what=1; //这里是消息的类型   
	                    List<SiteListLib> medias = checklogin.getDetails();
						String string = "";
						
						for (int i = 0; i < medias.size(); i++) {
							SiteListLib media = medias.get(i);
							string += "{\"siteid\":"+"\"" + media.getSiteID() + "\",";	
							string += "\"Province\":"+"\"" + media.getAddrID() + "\",";
							string += "\"sitename\":"+"\"" + media.getSitename() + "\",";
							string += "\"longitude\":"+"\"" + media.getSiteLon() + "\",";
							string += "\"latitude\":"+"\"" + media.getSiteLat() + "\"";
							string += "},";
							
							Log.i("HttpThread/String:", string);
						}
						string = string.substring(0, string.lastIndexOf(","));
						
						String SiteList = "[" + string + "]";
						
						data.putString("SiteList", SiteList);
						data.putBoolean("data", true); //这里是消息传送的数据
	                   
	                    message.setData(data);                     
	                    handle.sendMessage(message);
		              }else{
		            	  Message message=handle.obtainMessage();
		            	  Bundle b=new Bundle();                 
		            	  message.what=1;                 
		            	  b.putBoolean("data", false);
		            	  message.setData(b);
		            	  handle.sendMessage(message); 
		            	}    
				}else {
	            	  Message message=handle.obtainMessage();
	            	  Bundle b=new Bundle();                 
	            	  message.what=1;                 
	            	  b.putBoolean("data", false);
	            	  message.setData(b);
	            	  handle.sendMessage(message); 
				}						
				Log.i("Allow####",""+checklogin.getAllow());
				Log.i("Message####",""+checklogin.getMessage());
				
		}catch(Exception ex){  
					// 构造消息，程序出错了       
					Message message=handle.obtainMessage();
					Bundle b=new Bundle();        
					message.what=2;          
					b.putString("error", "请确认用户名和密码...");
					message.setData(b);         
					handle.sendMessage(message);
		}finally{                  
					
				} 
		}  
		/**  *   */ 
		protected InputStream CallWebService() throws Exception{				
				InputStream inputStream = null;				      
				return inputStream;          
		}				
}
