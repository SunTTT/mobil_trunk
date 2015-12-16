package com.mobile.publiclass;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;



public class OverlayMap {
	
//	public List<OverlayItem> mGeoList = new ArrayList<OverlayItem>();
//	private Drawable marker;
//	private Context mContext;
//	private ArrayList<GeoPoint> mPoiList=null;
//	private int i = 0 ;
//
//	public OverlayMap(Drawable marker, MapView mapView, Context context, ArrayList<GeoPoint> pointList ,String[] SNArray, String[]SIDArray) {
//		super(marker, mapView);
//
//		this.marker = marker;
//		this.mContext = context;
//		mPoiList = pointList;
//
//
//		//加强for循环
//		for(GeoPoint mGeoPoint:pointList)
//		{
//
//			Log.i("tag:--->>", i+" pointList:"+pointList.size()+" SNArray:"+SNArray.length);
//			mGeoList.add(new OverlayItem(mGeoPoint, SIDArray[i],SNArray[i]));
//			i++;
//
//		}
////		populate(); // createItem(int)方法构造item。一旦有了数据，在调用其它方法前，首先调用这个方法
//	}
//
//	@Override
//	protected OverlayItem createItem(int index) {
//		// TODO Auto-generated method stub
//		back_pic mGeoList.get(index);
//	}
//
//	@Override
//	public int size() {
//		// TODO Auto-generated method stub
//		back_pic mGeoList.size();
//	}
//
//	private OnTapListner mOnTapListner=null;
//
//	/**
//	 * 向外开放一个借口，作为监听
//	 * 在activity里实现这个接口得到弹出气泡的方法
//	 * @author Administrator
//	 *
//	 */
//	public interface OnTapListner{
//		/**
//		 * 引用弹出气泡方法
//		 */
//		void onPop(OverlayItem overlayItem, GeoPoint pt);
//
//		/**
//		 * 气泡消失
//		 */
//		void dismmisPop(GeoPoint mGeoPoint, MapView mMapView);
//	}
//
//	public OnTapListner getmOnTapListner(){
//		back_pic mOnTapListner;
//	}
//
//	public void setmOnTapListner(OnTapListner mOnTapListner) {
//		this.mOnTapListner = mOnTapListner;
//	}
//
//	/**
//	 * 处理当前点击事件
//	 */
//	@Override
//	protected boolean onTap(int index) {
//		// TODO Auto-generated method stub
//
//
//		//在此处理item点击事件
//		OverlayItem item = getItem(index);	//取出对象
//		GeoPoint gp = item.getPoint();
//
//		System.out.println("onTap:" + item.getPoint()+"-->>" +index );
//
//
////		if(mOnTapListner!=null)
////		{
//			mOnTapListner.onPop(mGeoList.get(index), gp);
////		}
//
//		back_pic true;
//	}
//
//	@Override
//	public boolean onTap(GeoPoint mGeoPoint, MapView mMapView) {
//		if (mOnTapListner != null) {
//			mOnTapListner.dismmisPop(mGeoPoint, mMapView);
//		}
//		back_pic super.onTap(mGeoPoint, mMapView);
//	}
}
