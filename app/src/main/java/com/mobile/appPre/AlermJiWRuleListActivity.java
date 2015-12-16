package com.mobile.appPre;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import com.longhui.entity.AlermTempRule;
import com.longhui.service.ReadFile;
import com.mobile.publiclass.PublicHttpActivity;
import com.mobile.publiclass.ShareData;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class AlermJiWRuleListActivity extends Activity{
	private String url="http://shortmsg.tmcadi.cn/SmsAlarmServerce.asmx";
	private List<NameValuePair> params = new ArrayList<NameValuePair>();
	List<Map<String,String>> list=new ArrayList<Map<String,String>>();
	List<Boolean> checkBoxesStatus=null; //用于保存checkBox的选择状态 
	int ckbid;
	ListView listview;
	TextView ruleok;
	String id,height,consta,stime;
	AlermTempRule temp;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//全屏展示本窗口，无标题
		setContentView(R.layout.alerm_rulelist);
		listview=(ListView)this.findViewById(R.id.rulelist);
		ruleok=(TextView)this.findViewById(R.id.ruleok);
		ruleok.setOnClickListener(new Complite());
		Intent intent=getIntent();
		temp=(AlermTempRule)intent.getSerializableExtra("id");
		id=temp.getId();
		ckbid=Integer.parseInt(id);
		
		params.add(new BasicNameValuePair("userName",ShareData.userName));
		params.add(new BasicNameValuePair("passWord",ShareData.userPass));
		InputStream	input = PublicHttpActivity.getJSONData(url+"/GetRules" ,params);
		List<AlermTempRule> rule=ReadFile.getAlermTempRuleList(input);
		Map<String,String> map=null;
		if(rule!=null)
		{	
			for(int i=0;i<rule.size();i++)
			{
				map=new HashMap<String,String>();
				map.put("id",rule.get(i).getId());
				map.put("height",rule.get(i).getHeightTemp());
				map.put("const",rule.get(i).getTempConstant());
				map.put("start",rule.get(i).getStartTime());
				list.add(map);
			}
		}		
		MyAdapter myAdapter=new MyAdapter(this,R.layout.alerm_ruleitem);
		listview.setAdapter(myAdapter);		
	}
	
	class Complite implements View.OnClickListener{

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent=new Intent(AlermJiWRuleListActivity.this,AlermJiWenActivity.class);			
			intent.putExtra("id", temp);
			setResult(1,intent);
		}		
	}
	
	public class MyAdapter extends BaseAdapter {
		public List<ViewHolder> mHolderList = new ArrayList<ViewHolder>();
		
		int id_row_layout;
		LayoutInflater mInflater;
		Context context;
		public MyAdapter(Context context, int id_row_layout) {
			super();
			this.id_row_layout = id_row_layout;			
			this.context=context;
			//if(data)
			//checkBoxesStatus = new ArrayList<Boolean>(list.size());
			//for(int i = 0;i<list.size();i++){
			//    checkBoxesStatus.add(false);
			//}
			mInflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return list.get(position).get("id");
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}
		//public List<Boolean> getCheckBoxesStatus() {
		//	   back_pic checkBoxesStatus;
		//}

		@Override
		public View getView(int position, View convertView, ViewGroup parentView) {
			ViewHolder holder =null;
			//Boolean checkBoxStatus = checkBoxesStatus.get(position);
			if (convertView == null) {
				synchronized (AlermJiWRuleListActivity.this) {
					convertView = mInflater.inflate(id_row_layout, null);
					holder = new ViewHolder();

					holder.ckb  = (CheckBox)convertView.findViewById(R.id.r_ckb);           
						
					holder.txt1 = (TextView) convertView.findViewById(R.id.r_htemp);
					holder.txt2 = (TextView) convertView.findViewById(R.id.r_const);
					holder.txt3 = (TextView) convertView.findViewById(R.id.r_stime);

					convertView.setTag(holder);
					mHolderList.add(holder);
				}
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.ckb.setText(list.get(position).get("id").toString());
			holder.ckb.setId(position);			
			if(id.equals(list.get(position).get("id")))
			{
				holder.ckb.setChecked(true);
			}
			else
			{
				holder.ckb.setChecked(false);
			}
			holder.txt1.setText(list.get(position).get("height"));
			holder.txt2.setText(list.get(position).get("const"));
			holder.txt3.setText(list.get(position).get("start"));
			holder.ckb.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			    @Override
			    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			    	if(ckbid==Integer.parseInt(list.get(buttonView.getId()).get("id")))
			    	{
			    		ckbid=-1;
			    	}
			    	if(isChecked==true)
			    	{
			    		ckbid=Integer.parseInt(getItem(buttonView.getId()).toString());
			    		temp.setId(list.get(buttonView.getId()).get("id"));
			    		temp.setHeightTemp(list.get(buttonView.getId()).get("height"));
			    		temp.setTempConstant(list.get(buttonView.getId()).get("const"));
			    		temp.setStartTime(list.get(buttonView.getId()).get("start"));
			    	}
			    	//int id=buttonView.getId();
			     //checkBoxesStatus.set(buttonView.getId(), isChecked);
			     notifyDataSetChanged();
			    }
			   });
			return convertView;
		}

		class ViewHolder {
			CheckBox ckb; 
			TextView txt1,txt2,txt3;
		}
	}// end class my
}
