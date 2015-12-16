package com.mobile.appPre;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.longhui.entity.AlermProvince;
import com.longhui.entity.AlermStationList;
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
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ExpandableListView.OnChildClickListener;
import com.mobile.appPre.R;

public class AlermProvStationList extends Activity{
	private String url="http://shortmsg.tmcadi.cn/SmsAlarmServerce.asmx";
	private List<NameValuePair> params = new ArrayList<NameValuePair>();
	List<AlermProvince> prolist;
	AlermStationList stationList;
	List<AlermStationList> stalist=new ArrayList<AlermStationList>();
	String method;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//全屏展示本窗口，无标题
		setContentView(R.layout.alerm_provincelist);
		Intent intent=getIntent();
		method=intent.getStringExtra("method");
		params.add(new BasicNameValuePair("userName",ShareData.userName));
		params.add(new BasicNameValuePair("passWord",ShareData.userPass));
		InputStream	input = PublicHttpActivity.getJSONData(url+"/GetAlarmProvince" ,params);
		prolist=ReadFile.getAlermProvince(input);
		if(prolist!=null)
		{
			for(int i=0;i<prolist.size();i++)
			{
				params.clear();
				params.add(new BasicNameValuePair("userName",ShareData.userName));
				params.add(new BasicNameValuePair("passWord",ShareData.userPass));
				params.add(new BasicNameValuePair("Province",prolist.get(i).getName()));
				InputStream	input2 = PublicHttpActivity.getJSONData(url+"/GetStationByProvince" ,params);
				stationList=ReadFile.getAlermList(input2);
				if(stationList!=null)
				{
					stalist.add(stationList);
				}
			}
		}
		
		
		final ExpandableListAdapter adapter = new BaseExpandableListAdapter() {
            
            //自己定义一个获得文字信息的方法
            TextView getTextView() {
                AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
                        ViewGroup.LayoutParams.FILL_PARENT, 64);
                TextView textView = new TextView(
                		AlermProvStationList.this);
                textView.setLayoutParams(lp);
                textView.setGravity(Gravity.CENTER_VERTICAL);
                textView.setPadding(36, 0, 0, 0);
                textView.setTextSize(20);
                textView.setTextColor(Color.BLACK);
                return textView;
            }

            
            //重写ExpandableListAdapter中的各个方法
            @Override
            public int getGroupCount() {
                // TODO Auto-generated method stub
                return prolist.size();
            }

            @Override
            public final Object getGroup(int groupPosition) {
                // TODO Auto-generated method stub
                return prolist.get(groupPosition).getName();
            }

            @Override
            public long getGroupId(int groupPosition) {
                // TODO Auto-generated method stub
                return groupPosition;
            }

            @Override
            public int getChildrenCount(int groupPosition) {
                // TODO Auto-generated method stub
            	
                return stalist.get(groupPosition).getAlermList().size();
            }

            @Override
            public Object getChild(int groupPosition, int childPosition) {
                // TODO Auto-generated method stub
                return stalist.get(groupPosition).getAlermList().get(childPosition).getStationId();
            }

            @Override
            public long getChildId(int groupPosition, int childPosition) {
                // TODO Auto-generated method stub
                return childPosition;
            }

            @Override
            public boolean hasStableIds() {
                // TODO Auto-generated method stub
                return true;
            }

            @Override
            public View getGroupView(int groupPosition, boolean isExpanded,
                    View convertView, ViewGroup parent) {
                // TODO Auto-generated method stub                

				View view = convertView;
				if (view == null) {
					LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
					view = inflater.inflate(R.layout.member_listview, null);
				}
				TextView title = (TextView) view.findViewById(R.id.groupname);
				title.setText(getGroup(groupPosition).toString());
				ImageView image=(ImageView) view.findViewById(R.id.xiaotubiao);
				if(isExpanded){
					image.setBackgroundResource(R.drawable.sitelistmore1);
				}else {
					image.setBackgroundResource(R.drawable.sitelistmore2);
				}

                return view;
            }

            @Override
            public View getChildView(int groupPosition, int childPosition,
                    boolean isLastChild, View convertView, ViewGroup parent) {
                // TODO Auto-generated method stub
            	ViewHolder holder =null;
            	LayoutInflater infla=LayoutInflater.from(AlermProvStationList.this);
            	if (convertView == null) {
    				synchronized (AlermProvStationList.this) {
    					convertView = infla.inflate(R.layout.alerm_getphoneitem, null);
    					holder = new ViewHolder();
    					holder.txt1 = (TextView) convertView.findViewById(R.id.p_phoneid);
    					holder.txt2 = (TextView) convertView.findViewById(R.id.p_phonename);
    					convertView.setTag(holder);
    				}
            	} else {
    				holder = (ViewHolder) convertView.getTag();
    			}
            	holder.txt1.setText(stalist.get(groupPosition).getAlermList().get(childPosition).getStationId());
            	holder.txt2.setText(stalist.get(groupPosition).getAlermList().get(childPosition).getStationName());
                return convertView;
            }

            @Override
            public boolean isChildSelectable(int groupPosition,
                    int childPosition) {
                // TODO Auto-generated method stub
                return true;
            }
            class ViewHolder {
    			TextView txt1,txt2;
    		}

        };

        ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.s_expandablelistview);
        expandableListView.setGroupIndicator(null);
        expandableListView.setAdapter(adapter);
        //设置item点击的监听器
        expandableListView.setOnChildClickListener(new OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                    int groupPosition, int childPosition, long id) {
            	Intent intent=new Intent();
            	intent.putExtra("id", adapter.getChild(groupPosition, childPosition).toString());
            	intent.putExtra("name", stalist.get(groupPosition).getAlermList().get(childPosition).getStationName());
            	intent.putExtra("proname", prolist.get(groupPosition).getName());
            	if(method.equals("add"))
            	{
            		intent.setClass(AlermProvStationList.this, AlermAddStation.class);
            	}
            	else
            	{
            		intent.setClass(AlermProvStationList.this, AlermStationUpdateActivity.class);
            	}
            	setResult(1,intent);
            	finish();
                return false;
            }
        });
	}
	
	public void goBack(View v){
		this.finish();
	}
}
