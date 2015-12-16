package com.mobile.appPre;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import com.longhui.entity.PhoneEntityList;
import com.longhui.service.ReadFile;
import com.mobile.publiclass.PublicHttpActivity;
import com.mobile.publiclass.ShareData;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import com.mobile.appPre.R;

public class AlermGetPhoneList extends Activity{
	private String url="http://shortmsg.tmcadi.cn/SmsAlarmServerce.asmx";
	private List<NameValuePair> params = new ArrayList<NameValuePair>();
	 PhoneEntityList phoneList=new PhoneEntityList();
	 ListView p_listview;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//全屏展示本窗口，无标题
		setContentView(R.layout.alerm_getphonelist);
		p_listview=(ListView)this.findViewById(R.id.p_listview);
		params.add(new BasicNameValuePair("userName",ShareData.userName));
		params.add(new BasicNameValuePair("passWord",ShareData.userPass));
		InputStream	input = PublicHttpActivity.getJSONData(url+"/GetAllPhoneNo" ,params);
		phoneList=ReadFile.getPhoneList(input);
		if(phoneList!=null)
		{
			MyAdapter myAdapter=new MyAdapter(this);
			p_listview.setAdapter(myAdapter);
		}		
	}
	
	class MyAdapter extends BaseAdapter {

		Context context;
		LayoutInflater mInflater; 

		public MyAdapter(Context context) {
			this.context = context;
			mInflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			return phoneList.getPhoneList().size();
		}

		@Override
		public Object getItem(int position) {
			return phoneList.getPhoneList().get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {

			ViewHolder holder;
			if (convertView == null) {
				
				convertView = mInflater.inflate(R.layout.alerm_getphoneitem, null);
				holder = new ViewHolder();
				holder.p_phoneid = (TextView) convertView.findViewById(R.id.p_phoneid);
				holder.p_phonename = (TextView) convertView.findViewById(R.id.p_phonename);
				convertView.setTag(holder);
			}else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.p_phoneid.setText(String.valueOf(phoneList.getPhoneList().get(position).getPhoneId()));
			holder.p_phonename.setText(String.valueOf(phoneList.getPhoneList().get(position).getPhoneNumber()));
			holder.p_phonename.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					Intent intent=new Intent(AlermGetPhoneList.this,AlermAddStation.class);
					intent.putExtra("phoneId", String.valueOf(phoneList.getPhoneList().get(position).getPhoneId()));
					intent.putExtra("phoneNum", String.valueOf(phoneList.getPhoneList().get(position).getPhoneNumber()));
					setResult(0,intent);
					finish();
				}
				
			});
			return convertView;
		}
		
		class ViewHolder{
			TextView p_phoneid,p_phonename;
		}
	}
}
