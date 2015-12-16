package com.mobile.appPre;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.longhui.entity.AlermMessage;
import com.longhui.service.ReadFile;
import com.mobile.publiclass.PublicHttpActivity;
import com.mobile.publiclass.ShareData;

public class AlermMessageActivity  extends Activity{
	private String url="http://shortmsg.tmcadi.cn/SmsAlarmServerce.asmx";
	private List<NameValuePair> params = new ArrayList<NameValuePair>();
	ListView listview;
	String meth,id;
	List<AlermMessage> messList;
	protected static final int Menu_Item1=Menu.FIRST;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//全屏展示本窗口，无标题
		setContentView(R.layout.alerm_mess);
		Intent intent=getIntent();
		meth=intent.getStringExtra("meth");
		id=intent.getStringExtra("id");
		listview=(ListView)this.findViewById(R.id.amlistveiw);
		if(meth.equals("1"))
		{
			params.clear();
			params.add(new BasicNameValuePair("userName",ShareData.userName));
			params.add(new BasicNameValuePair("passWord",ShareData.userPass));
			params.add(new BasicNameValuePair("Id",id));
			InputStream	input = PublicHttpActivity.getJSONData(url+"/GetAlermMessage" ,params);
			messList=ReadFile.getAlermMessage(input);
			if(messList!=null){
				MyAdapter myAdapter=new MyAdapter(this,R.layout.alerm_messitem);
				listview.setAdapter(myAdapter);	
			}
		}
		else if(meth.equals("0"))
		{
			params.clear();
			params.add(new BasicNameValuePair("userName",ShareData.userName));
			params.add(new BasicNameValuePair("passWord",ShareData.userPass));
			InputStream	input = PublicHttpActivity.getJSONData(url+"/GetAllAlermMessage" ,params);
			messList=ReadFile.getAlermMessage(input);
			if(messList!=null){
				MyAdapter myAdapter=new MyAdapter(this,R.layout.alerm_messlistitem);
				listview.setAdapter(myAdapter);	
			}
		}
		listview.setOnCreateContextMenuListener(MenuLis);
	}
	
	ListView.OnCreateContextMenuListener MenuLis=new ListView.OnCreateContextMenuListener(){
		@Override
		public void onCreateContextMenu(ContextMenu menu, View v,
		ContextMenuInfo menuInfo) {
			// TODO Auto-generated method stub
			//添加菜单项
			//menu.add(Menu.NONE,Menu_Item1,0,"获取position");
			menu.add(Menu.NONE,1,0,"删除");
			menu.add(Menu.NONE,2,0,"全部删除");
		}

		};
		//选中菜单Item后触发
		public boolean onContextItemSelected(MenuItem item){
			 
			//关键代码在这里
			final AdapterView.AdapterContextMenuInfo menuInfo;
			menuInfo =(AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
			//输出position
			//Toast.makeText(AlermMessageActivity.this,String.valueOf(menuInfo.position), Toast.LENGTH_LONG).show();
			AlertDialog.Builder builder =new AlertDialog.Builder(AlermMessageActivity.this);
			switch(item.getItemId())
			{
			case 1:				
				builder.setMessage("确定删除数据？");	
				builder.setPositiveButton("确  定", new AlertDialogOnClickListener(messList.get(menuInfo.position).getId())); 
		    break;
			case 2:
				builder.setMessage("确定全部删除数据？");
				String ids="";
				for(int i=0;i<messList.size();i++)
				{
					ids+=messList.get(i).getId()+",";
				}
				ids=ids.substring(0, ids.length()-2).trim();
				builder.setPositiveButton("确  定", new AlertDialogOnClickListener(ids)); 
				break;
			}

			builder.setTitle("删除确认");
			builder.setNegativeButton("取 消", new DialogInterface.OnClickListener() {                         
				@Override                     
				public void onClick(DialogInterface dialog, int which) {  
					
					dialog.cancel();
				}
			});  
			Dialog dialog = builder.create();             
			dialog.show();
			return super.onContextItemSelected(item);

		}
		
		class AlertDialogOnClickListener implements DialogInterface.OnClickListener{
			String ids="";
			public AlertDialogOnClickListener(String id){
				ids=id;
			}
			@Override                     
			public void onClick(DialogInterface dialog, int which) { 					
				params.clear();
				params.add(new BasicNameValuePair("userName",ShareData.userName));
				params.add(new BasicNameValuePair("passWord",ShareData.userPass));
				params.add(new BasicNameValuePair("Id",ids));
				InputStream	input = PublicHttpActivity.getJSONData(url+"/DeleteAlermMessage" ,params);
				
				BufferedReader reader = new BufferedReader(new InputStreamReader(input));   
			    String str = null;   
			    
			    StringBuffer sb = new StringBuffer();   
			    try {
					while((str = reader.readLine())!=null){   
					  System.err.println(str+"str");   
					  sb.append(str);   
					}
					System.out.println(sb);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			    
			    if (sb.toString().equals("true")) {
			    	Toast.makeText(AlermMessageActivity.this, "删除成功！", Toast.LENGTH_SHORT).show();
				}else {
					Toast.makeText(AlermMessageActivity.this, "删除失败！", Toast.LENGTH_SHORT).show();
				}
			}
		}
	
	public class MyAdapter extends BaseAdapter {
		//public List<ViewHolder> mHolderList = new ArrayList<ViewHolder>();
		
		int id_row_layout;
		LayoutInflater mInflater;
		Context context;
		public MyAdapter(Context context, int id_row_layout) {
			super();
			this.id_row_layout = id_row_layout;			
			this.context=context;
			mInflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return messList.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return messList.get(position).getId();
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parentView) {
			ViewHolder holder =null;
			if (convertView == null) {
				synchronized (AlermMessageActivity.this) {
					convertView = mInflater.inflate(id_row_layout, null);
					holder = new ViewHolder();      
						
					holder.am_content = (TextView) convertView.findViewById(R.id.am_content);
					holder.am_time = (TextView) convertView.findViewById(R.id.am_time);
					holder.am_method = (TextView) convertView.findViewById(R.id.am_method);
					holder.am_issend = (TextView) convertView.findViewById(R.id.am_issend);
					holder.am_type = (TextView) convertView.findViewById(R.id.am_type);
					if(id_row_layout==R.layout.alerm_messlistitem)
					{
						holder.am_station = (TextView) convertView.findViewById(R.id.am_station);
						holder.am_phone = (TextView) convertView.findViewById(R.id.am_phone);
					}
					convertView.setTag(holder);
					//mHolderList.add(holder);
				}
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.am_content.setText(messList.get(position).getContant());
			holder.am_time.setText(messList.get(position).getSendTime());
			holder.am_method.setText(messList.get(position).getSnedMethod());
			holder.am_issend.setText(messList.get(position).getIsSend());
			holder.am_type.setText(messList.get(position).getMessType());
			holder.am_method.setText(messList.get(position).getSnedMethod());
			if(id_row_layout==R.layout.alerm_messlistitem)
			{
				holder.am_station.setText(messList.get(position).getStationName());
				holder.am_phone.setText(messList.get(position).getPhone());
			}
			return convertView;
		}

		class ViewHolder {
			TextView am_content,am_time,am_method,am_issend,am_type,am_station,am_phone;
		}
	}
}
