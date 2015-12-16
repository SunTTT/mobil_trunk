package com.mobile.appPre;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import com.longhui.entity.AlermStationList;
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
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.mobile.appPre.R;

public class AlermStationActivity extends Activity{
	private String url="http://shortmsg.tmcadi.cn/SmsAlarmServerce.asmx";
	private List<NameValuePair> params = new ArrayList<NameValuePair>();
    PhoneEntityList phoneList=new PhoneEntityList();
    List<List<Map<String,Object>>> totalList=new ArrayList<List<Map<String,Object>>>();
    private PopupWindow popWin;
    private View view;
    Button a_add;
    TextView pop_station,pop_phone;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//全屏展示本窗口，无标题
		setContentView(R.layout.alermstation);
		a_add=(Button)this.findViewById(R.id.a_add);
		a_add.setOnClickListener(new OnClickListenerSpinner());
		
		
		view=getLayoutInflater().inflate(R.layout.popwindow_add, null,false);
		pop_station=(TextView)view.findViewById(R.id.pop_station);		
		pop_station.setOnClickListener(new OnClickListenerSpinner());
		pop_phone=(TextView)view.findViewById(R.id.pop_phone);		
		pop_phone.setOnClickListener(new OnClickListenerSpinner());
		params.add(new BasicNameValuePair("userName",ShareData.userName));
		params.add(new BasicNameValuePair("passWord",ShareData.userPass));
		
		InputStream	input = PublicHttpActivity.getJSONData(url+"/GetAllPhoneNo" ,params);
		//String inputStr=PublicHttpActivity.convertStreamToString(input);
		//ArrayList<HashMap<String,Object>> stationList= PublicHttpActivity.getArrayList(inputStr);
		phoneList=ReadFile.getPhoneList(input);
		if(phoneList!=null)
		{
			for(int i=0;i<phoneList.getPhoneList().size();i++)
			{
				String phoneNo=phoneList.getPhoneList().get(i).getPhoneNumber();
				params.clear();
				params.add(new BasicNameValuePair("userName",ShareData.userName));
				params.add(new BasicNameValuePair("passWord",ShareData.userPass));
				params.add(new BasicNameValuePair("Phone",phoneNo));
				InputStream	input2 = PublicHttpActivity.getJSONData(url+"/GetStationByPhone" ,params);
				AlermStationList alermList=ReadFile.getAlermList(input2);
				Map<String,Object> map;
				List<Map<String,Object>> station;
				if(alermList!=null)
				{
					station=new ArrayList<Map<String,Object>>();
					for(int j=0;j<alermList.getAlermList().size();j++){
						map=new HashMap<String,Object>();
						map.put("id",alermList.getAlermList().get(j).getId());
						map.put("stationName", alermList.getAlermList().get(j).getStationName());
						map.put("province", alermList.getAlermList().get(j).getProvince());
						station.add(map);
					}
					totalList.add(station);
				}
			}
		}
		
		
		final ExpandableListAdapter adapter = new BaseExpandableListAdapter() {
			
			
            //设置组视图的图片
            //int[] logos = new int[] { R.drawable.wei, R.drawable.shu,R.drawable.wu};
            //设置组视图的显示文字
			//String[] generalsTypes = new String[] { "电话号码：13109890987", "电话号码：13209890987", "电话号码：13309890987" };
            //子视图显示文字
			/* private String[][] generals = new String[][] {
                    { "夏侯惇", "甄姬", "许褚", "郭嘉", "司马懿", "杨修" },
                    { "马超", "张飞", "刘备", "诸葛亮", "黄月英", "赵云" },
                    { "吕蒙", "陆逊", "孙权", "周瑜", "孙尚香" }

            };
            //子视图图片
            public int[][] generallogos = new int[][] {
                    { R.drawable.xiahoudun, R.drawable.zhenji,
                            R.drawable.xuchu, R.drawable.guojia,
                            R.drawable.simayi, R.drawable.yangxiu },
                    { R.drawable.machao, R.drawable.zhangfei,
                            R.drawable.liubei, R.drawable.zhugeliang,
                            R.drawable.huangyueying, R.drawable.zhaoyun },
                    { R.drawable.lvmeng, R.drawable.luxun, R.drawable.sunquan,
                            R.drawable.zhouyu, R.drawable.sunshangxiang } };*/
            
            //自己定义一个获得文字信息的方法
            TextView getTextView() {
                AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
                        ViewGroup.LayoutParams.FILL_PARENT, 64);
                TextView textView = new TextView(
                		AlermStationActivity.this);
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
                return phoneList.getPhoneList().size();
            }

            @Override
            public Object getGroup(int groupPosition) {
                // TODO Auto-generated method stub
                return phoneList.getPhoneList().get(groupPosition).getPhoneNumber();
            }

            @Override
            public long getGroupId(int groupPosition) {
                // TODO Auto-generated method stub
                return groupPosition;
            }

            @Override
            public int getChildrenCount(int groupPosition) {
                // TODO Auto-generated method stub
            	
                return totalList.get(groupPosition).size();
            }

            @Override
            public Object getChild(int groupPosition, int childPosition) {
                // TODO Auto-generated method stub
                return totalList.get(groupPosition).get(childPosition).get("id");
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
            	/*LinearLayout ll = new LinearLayout(
                		AlermStationActivity.this);
                ll.setOrientation(0);
                ImageView logo = new ImageView(AlermStationActivity.this);
                logo.setImageResource(logos[groupPosition]);
                logo.setPadding(50, 0, 0, 0);
                ll.addView(logo);
                TextView textView = getTextView();
                textView.setTextColor(Color.DKGRAY);
                textView.setPadding(80, 0, 0, 0);
                textView.setTextSize(18);
                textView.setText("电话号码："+getGroup(groupPosition).toString());
                ll.addView(textView);*/
                

				View view = convertView;
				if (view == null) {
					LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
					view = inflater.inflate(R.layout.member_listview, null);
				}
				TextView title = (TextView) view.findViewById(R.id.groupname);
				title.setText("电话号码："+getGroup(groupPosition).toString());
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
            	LayoutInflater infla=LayoutInflater.from(AlermStationActivity.this);
            	if (convertView == null) {
    				synchronized (AlermStationActivity.this) {
    					convertView = infla.inflate(R.layout.alerm_stationitem, null);
    					holder = new ViewHolder();
    					holder.txt1 = (TextView) convertView.findViewById(R.id.e_id);
    					holder.txt2 = (TextView) convertView.findViewById(R.id.e_province);
    					convertView.setTag(holder);
    				}
            	} else {
    				holder = (ViewHolder) convertView.getTag();
    			}
            	holder.txt1.setText(totalList.get(groupPosition).get(childPosition).get("stationName").toString());
            	holder.txt2.setText(totalList.get(groupPosition).get(childPosition).get("province").toString());
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

        ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.expenlist);
        expandableListView.setGroupIndicator(null);
        expandableListView.setAdapter(adapter);
        
        expandableListView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				if (popWin != null && popWin.isShowing()) {

					popWin.dismiss();

					popWin = null;

				}
			}        	 
        });
        //设置item点击的监听器
        expandableListView.setOnChildClickListener(new OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                    int groupPosition, int childPosition, long id) {
            	if (popWin != null && popWin.isShowing()) {

					popWin.dismiss();

					popWin = null;

				}
            	Intent intent=new Intent();
            	intent.putExtra("id", adapter.getChild(groupPosition, childPosition).toString());
            	intent.setClass(AlermStationActivity.this, AlermStationEditActivity.class);
            	startActivity(intent);
                /*Toast.makeText(
                		AlermStationActivity.this,
                        "你点击了" + adapter.getChild(groupPosition, childPosition),
                        Toast.LENGTH_SHORT).show();*/

                return false;
            }
        });
	}
	 public void initPopWindow(){
	     if(null==popWin){//(popwin自定义布局文件,popwin宽度,popwin高度)(注：若想指定位置则后两个参数必须给定值不能为WRAP_CONTENT)
	    	 popWin=new PopupWindow(view, 260,  160,true);
	    	 popWin.setOutsideTouchable(true);
	    	 popWin.setFocusable(false);
	     }
	     if(popWin.isShowing()){//如果当前正在显示，则将被处理
	         popWin.dismiss();
	     }
	 }
	 
	 public void MessCheck(View v){
		 Intent intent=new Intent(this,AlermMessageActivity.class);
		 intent.putExtra("meth", "0");
		 intent.putExtra("id", "");
		 startActivity(intent);
	 }
	 
	 class OnClickListenerSpinner implements View.OnClickListener{

		 @Override
		 public void onClick(View v) {
			 initPopWindow();
			 switch(v.getId()){
				case R.id.a_add:	
					popWin.showAsDropDown(v,Math.abs(v.getWidth()-popWin.getWidth())/2, 20);
					break;
				case R.id.pop_station:
					Intent intent=new Intent(AlermStationActivity.this,AlermAddStation.class);
					startActivity(intent);
					popWin.dismiss();
					break;
					
				case R.id.pop_phone:
					Intent intent2=new Intent(AlermStationActivity.this,AlermPhoneAddActivity.class);
					startActivity(intent2);
					popWin.dismiss();
					break;
				}		 
		  }
	 }
	 
	 @Override
		public boolean onTouchEvent(MotionEvent event) {

		// TODO Auto-generated method stub
			
		if (popWin != null && popWin.isShowing()) {

			popWin.dismiss();

			popWin = null;

		}
		return super.onTouchEvent(event);

		}
}
