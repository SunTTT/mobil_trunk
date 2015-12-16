package com.mobile.publiclass;

import com.mobile.appPre.R;
import com.mobile.appPre.ScientificData;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class StationAdapter extends BaseAdapter{
	private String[] sName;
	private String[] sId;
	private Activity context;
	
	private LayoutInflater mInflater;


	public StationAdapter(Activity context, String[] sName, String[] sId) {
		this.context = context;
		this.sName = sName;
		this.sId = sId;
		
		this.mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return sName.length;
	}

	@Override
	public Object getItem(int position) {
		return sName[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		System.out.println("-----StationAdapter/getView-------");
		
		ViewHolder holder;
		
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.stationlist, null);
			
			//�õ������ؼ�
			holder.sName = (TextView) convertView.findViewById(R.id.tv_stationName);
			holder.sId = (TextView) convertView.findViewById(R.id.tv_stationId);
			
			
			convertView.setTag(holder);
		}else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		//��TextView��ֵ
		holder.sName.setText(sName[position]);
		holder.sId.setText(sId[position]);
		
		return convertView;
	}

	class ViewHolder{
		public TextView sName;
		public TextView sId;
	}

}
