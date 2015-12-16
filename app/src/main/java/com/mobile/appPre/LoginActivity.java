/*
                   _ooOoo_
                  o8888888o
                  88" . "88
                  (| -_- |)
                  O\  =  /O
               ____/`---'\____
             .'  \\|     |//  `.
            /  \\|||  :  |||//  \
           /  _||||| -:- |||||-  \
           |   | \\\  -  /// |   |
           | \_|  ''\---/''  |   |
           \  .-\__  `-`  ___/-. /
         ___`. .'  /--.--\  `. . __
      ."" '<  `.___\_<|>_/___.'  >'"".
     | | :  `- \`.;`\ _ /`;.`/ - ` : | |
     \  \ `-.   \_ __\ /__ _/   .-` /  /
======`-.____`-.___\_____/___.-`____.-'======
                   `=---='
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
         佛祖保佑       永无BUG
*/
package com.mobile.appPre;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import com.longhui.entity.UserInfo;
import com.mobile.appPre.R;
import com.mobile.publiclass.HttpThread;
import com.mobile.publiclass.ShareData;
import com.mobile.publiclass.SysExitUtil;

public class LoginActivity extends Activity {
	/** Called when the activity is first created. */
	Button btnlogin; //登录和退出按钮对像   
	boolean data=false; //调用webservice 近回的数据,验证成功true,失败false    
	HttpThread thread=null; //线程对像   
	
	private String names=""; //用户名    
	private String pass=""; //口令 
	
	private CheckBox cb ;
	
	private AutoCompleteTextView autoCompleteTextView ;
	private EditText loginpwd ;
	
	private String account = null;	
	private String pwd = null;
	
	LoginActivity lactivity ;
	private List<NameValuePair> params = new ArrayList<NameValuePair>();
	List<UserInfo> list = new ArrayList<UserInfo>();
	ArrayList<String> usernames = new ArrayList<String>();
	
	private PublicApplication App = new PublicApplication();
	String jsonstr;
	 public static final String PREFS_STRING = "login";
	JSONArray jArr;
	JSONObject jobj;
	private boolean dl = true ;

	private void Remind(String error){
		new AlertDialog.Builder(LoginActivity.this)
		.setTitle(R.string.reminder)				
		.setMessage(error)
		 .setNeutralButton( R.string.ok,null)
		 .show();
	}
    //生成消息对象   
	static class MyHandler extends Handler { 
		WeakReference<LoginActivity> mActivity; 
		MyHandler(LoginActivity activity) { 
			mActivity = new WeakReference<LoginActivity>(activity);
			} 
		@Override 
		public void handleMessage(Message m){
			
			LoginActivity theActivity = mActivity.get(); 		
			theActivity.btnlogin = (Button) theActivity.findViewById(R.id.login_btn_login);			
			theActivity.btnlogin.setBackgroundResource(R.drawable.denglu);
			switch(m.what){     			
				case 1:              
					theActivity.data= m.getData().getBoolean("data"); //从消息栈拿出数据 
					Log.i("LoginActivity/Message返回信息：",""+theActivity.data);					
					if(theActivity.data){   
						//如果用户名和密码正确则把用户名和密码保存本地
						SharedPreferences sp = theActivity.getSharedPreferences(PREFS_STRING, Context.MODE_PRIVATE);
						theActivity.names = sp.getString("names", "");
						theActivity.pass = sp.getString("passwords", "");
						Editor editor=sp.edit();

						if (!theActivity.names.contains(theActivity.autoCompleteTextView.getText().toString().replaceAll(" ", ""))) {//如果PREFS_STRING中不包含输入的用户名，则保存用户名
							theActivity.names += theActivity.autoCompleteTextView.getText().toString().replaceAll(" ", "")+",";
							editor.putString("names", theActivity.names);//将autoCompleteTextView取得的值放入到文件中的names中
							theActivity.cb=(CheckBox)theActivity.findViewById(R.id.login_savepwd);
							if (theActivity.cb.isChecked()) {//如果Checkbox被选中，则保存密码
								theActivity.pass+= theActivity.loginpwd.getText().toString().replaceAll(" ", "") +",";
								editor.putString("passwords", theActivity.pass);
							}
							editor.commit();//关闭打开的sp
						}else{
							
							Log.i( "LoginActivity/savenames", "User name already exists!");
						}						
							//成功后界面要交给项目的主界面了，把用户名传给下一个activity
							String SiteList =  m.getData().getString("SiteList");
							Log.i("LoginActivity/--1--/SiteList", SiteList);
							Bundle data = new Bundle();
							data.putString("SiteList", SiteList);
							Intent intent = new Intent();
							intent.putExtra("data", data);
							intent.setClass(theActivity.getApplicationContext(), HomeActivity.class);
							theActivity.startActivity(intent);
							theActivity.finish();
							//登录成功后的提示
							Toast.makeText(theActivity.getApplicationContext(),"登陆成功！！" , Toast.LENGTH_SHORT).show();							
						}else{						
								String error= m.getData().getString("error");
								theActivity.Remind(error);
							}
					break;
					case 2:
						String error= m.getData().getString("error");
						theActivity.Remind(error);						
					break;
				}	
			theActivity.dl = true ;
	}						
	};
//实例化MyHandler
	 MyHandler handler = new MyHandler(this);  
	 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SysExitUtil.activityList.add(LoginActivity.this); 
		requestWindowFeature(Window.FEATURE_NO_TITLE);//全屏展示本窗口，无标题
		setContentView(R.layout.loginpage_new);
		
		loginpwd = (EditText) this.findViewById(R.id.login_edit_pwd);
		autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.login_edit_account);
		 
		 SharedPreferences sp = getSharedPreferences(PREFS_STRING, Context.MODE_PRIVATE);
		 names = sp.getString("names", "");
		 pass = sp.getString("passwords", "");
		 
		 if (names.contains(",")) {//如果含有用户名
			 //将names下的字符串用","隔开然后放入到users数组当中
			final String[] user = names.split(",");
			
			for(int i = 0;i<user.length;i++){
				UserInfo u = new UserInfo();
				u.namestring=user[i];
				usernames.add(user[i]);
				list.add(u);
			}
			//将本地存的用户名列到autoCompleteTextView列表上
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(LoginActivity.this, android.R.layout.simple_dropdown_item_1line,user);
			autoCompleteTextView.setAdapter(adapter);
			//将用户名对应的密码填充在密码框
			autoCompleteTextView.setOnItemClickListener(new OnItemClickListener() {
				
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					//将密码解析出来放入passes数组中
					if (pass.contains(",")) {
						String[] passes = pass.split(",");
						
                        for (int i = 0;i<passes.length;i++) {							
							UserInfo u = new UserInfo();
							u.pwdstring=passes[i];		
							usernames.add(passes[i]);
							list.add(u);
						} 
                        //因为用户名和密码是顺序放入两个数组中的，根据选中的用户名的序号判断要取得的密码的序号
                        int j = 0;
                        for (j = 0; j < user.length; j++) {
							if (user[j].equals(autoCompleteTextView.getText().toString().replaceAll(" ", ""))) {//判定选中的用户名是哪个
								
								break;
							}
						}                                               
                      //传入passes中下标为j的密码                       
                      loginpwd.getText().clear(); //清空缓存
                      loginpwd.getText().append(passes[j]);                        
					}					
				}				
			});			
		}
		 		 
		// 登陆按钮监控 
		 btnlogin = (Button) findViewById(R.id.login_btn_login);
		 btnlogin.setOnClickListener(
				new View.OnClickListener() {				
					public void onClick(View v) {
						// TODO Auto-generated method stub						
						if (dl==true) {
							dl = false ;							
							 account = autoCompleteTextView.getText().toString().replaceAll(" ", "");						
							 pwd = loginpwd.getText().toString().replace(" ", "");							 

						        if (params!=null) {
									params.clear();
								}
								params.add(new BasicNameValuePair("username", account));
								params.add(new BasicNameValuePair("password", pwd));
								ShareData.userName=account;
								ShareData.userPass=pwd;

								if(account!="" && pwd !=""){															
								thread=new HttpThread(handler);							
								//需调用webservice名称  　　  
								thread.doStart(App.requestURL,App.MyNamespace,App.GetStationsByUser, params ,LoginActivity.this);   //启动线程								
							} 
								else {
								
								AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
								builder.setTitle("错误提示")
								.setMessage("用户名和密码不能为空")
								       .setCancelable(false)
								       .setPositiveButton("确定", new DialogInterface.OnClickListener() {
								           public void onClick(DialogInterface dialog, int id) {
								        	   dialog.cancel();
												dl = true ;
								           }
								       });
								AlertDialog alert = builder.create();
								alert.show();
							}
						}
					}
				});
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
