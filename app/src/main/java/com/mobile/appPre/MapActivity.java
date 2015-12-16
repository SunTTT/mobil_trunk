package com.mobile.appPre;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.http.NameValuePair;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMapOptions;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;

import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;

import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;

import com.longhui.entity.PictureList;
import com.longhui.service.ReadFile;
import com.mobile.appPre.R;
import com.mobile.publiclass.OverlayMap;
import com.mobile.publiclass.SysExitUtil;

import com.mobile.publiclass.PublicHttpActivity;

public class MapActivity extends Activity {

    private BMapManager mBMapMan;
    private MapView mMapView;
    private ImageView image;
    private TextView time, textback;

    private String startstr1 = "";
    private String endtime1 = "";
    private PublicApplication App = new PublicApplication();
    private List<NameValuePair> params = new ArrayList<NameValuePair>();
    private com.longhui.entity.Picture pictrue = null;
    private String firsttime = "";
    private static int mYear;
    private static int mMonth;
    private static int mDay;
    public String[] urlsStrings, stationtimes;
    private TextView stationName = null;
    private TextView geoPoints = null;
    private LinearLayout magButton = null;
    private View view;
    private InfoWindow mInfoWindow;
    double mLon, mLat;
    private ProgressDialog dialog;
    private View mPopView = null;
    private Context context = MapActivity.this;

    private OverlayMap itemOverlay = null;
    private Drawable mark;
    private String TOOLBAR_ITEM = null;
    String jsonString;
    private BaiduMap mBaiduMap;
    private LatLng latLng;
    private Marker marker;
    private LinearLayout mLineraLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.map);
        SysExitUtil.activityList.add(MapActivity.this);
        initUI();


        initDate();
        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                LatLng latLng = marker.getPosition();


                Bundle bundle = marker.getExtraInfo();

                onPop(view, latLng, 5, bundle);
//                startActivity(intent);
                onPop(view, latLng, -47, bundle);

                return true;
            }
        });

//        mBaiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
//            @Override
//            public void onMapClick(LatLng latLng) {
////                mBaiduMap.hideInfoWindow();
//                mLineraLayout.setVisibility(View.GONE);
//            }
//
//            @Override
//            public boolean onMapPoiClick(MapPoi mapPoi) {
//                back_pic false;
//            }
//        });
        mBaiduMap.setOnMapTouchListener(new BaiduMap.OnMapTouchListener() {
            @Override
            public void onTouch(MotionEvent motionEvent) {
                if (mLineraLayout.getVisibility()==View.VISIBLE){
                    mLineraLayout.setVisibility(View.GONE);
                }

            }
        });

    }

    private void initDate() {
        String stationName = "";
        String stationId = "";
        Intent intent = getIntent();
        Bundle getdata = intent.getBundleExtra("data");
        jsonString = getdata.getString("SiteList");
        TOOLBAR_ITEM = getdata.getString("TOOLBAR_ITEM");

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        try {
            // 经度点集合
            JSONArray lonList = new JSONArray(jsonString);// 获取JSONArray

            String str = "";
            /*double[][] mapPoint;
            mapPoint = new double[lonList.length()][];*/
            for (int i = 0; i < lonList.length(); i++) {

                JSONObject temp = (JSONObject) lonList.get(i);

                String lonPoint = temp.getString("longitude");
                String latPoint = temp.getString("latitude");

                stationId += temp.getString("siteid") + ",";
                stationName += temp.getString("sitename") + ",";

                if (lonPoint.equals("NULL")) {
                    lonPoint = "0";
                }
                if (latPoint.equals("NULL")) {
                    latPoint = "0";
                }

                mLon = Double.valueOf(lonPoint);
                mLat = Double.valueOf(latPoint);

                str += (mLon + "," + mLat + ";");

            }
            stationId = stationId.substring(0, stationId.length() - 1);
            stationName = stationName.substring(0, stationName.length() - 1);


            str = str.substring(0, str.lastIndexOf(";"));

            Log.v("MapActivity/list", "-----list-----" + list);

            myOverLay(str, stationName, stationId);
            MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(latLng);
            MapStatusUpdate zoomTo = MapStatusUpdateFactory.zoomTo(8);
            mBaiduMap.animateMapStatus(zoomTo);
            mBaiduMap.setMapStatus(u);
//			myoverlay(mapPoint);

        } catch (JSONException e) {
            Log.v("error:", "-----error 没经过解析-----");
            e.printStackTrace();
        }

    }

    private void getPigShow(com.longhui.entity.Picture pictrue2, String timestring) {


        if (pictrue != null) {
            List<PictureList> pictruelist = pictrue.getDetails();

            Log.i("NewMainactivity/pictruelist.size", "pictruelist.size = " + pictruelist.size());

            if (pictruelist.size() != 0) {//判断获得的picture内容不为空
                String urls;
                urlsStrings = new String[pictruelist.size()];
                for (int i = 0; i < pictruelist.size(); i++) {
                    PictureList media = pictruelist.get(i);
                    if (media != null) {
                        //图片url
                        urls = App.AshxPicturePath + "?id=" + media.getId() + "&tname=" + media.getTname();
                        //得到可用的图片
                        Bitmap bitmap = getHttpBitmap(urls);

                        Log.i("txURL", urls + ",id=" + media.getId() + ",tname=" + media.getTname());
                        time = (TextView) this.findViewById(R.id.point_desc);
                        time.setText(media.getTime());
                        image.setImageBitmap(bitmap);
                        return;
                    }
                }
            }
        }
    }

    public static Bitmap getHttpBitmap(String url) {
        URL myFileURL;
        Bitmap bitmap = null;
        try {
            myFileURL = new URL(url);
            //获得连接
            HttpURLConnection conn = (HttpURLConnection) myFileURL.openConnection();
            //设置超时时间为6000毫秒，conn.setConnectionTiem(0);表示没有时间限制
            conn.setConnectTimeout(6000);
            //连接设置获得数据流
            conn.setDoInput(true);
            //不使用缓存
            conn.setUseCaches(false);
            //这句可有可无，没有影响
            //conn.connect();
            //得到数据流
            InputStream is = conn.getInputStream();
            //解析得到图片
            bitmap = BitmapFactory.decodeStream(is);
            //关闭数据流
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }


    double lon = 0;
    double lat = 0;

    private void myOverLay(String str, String stationName, String stationId) {
        // TODO Auto-generated method stub
        mBaiduMap.clear();
        String a = str;
        Log.v("str", "---str---" + a);
        String[] s = a.split(";");
        String[] SNArray = stationName.split(",");
        String[] SIDArray = stationId.split(",");
        double[][] d;
        d = new double[s.length][];

//        ArrayList<GeoPoint> pointList = new ArrayList<GeoPoint>();
//        ArrayList<OverlayItem> pointItemList = new ArrayList<OverlayItem>();

        for (int i = 0; i < s.length; i++) {
            String[] s_num = s[i].split(",");
            for (int j = 0; j < s_num.length; j++) {
                d[i] = new double[s_num.length];
                d[i][j] = Double.parseDouble(s_num[j]);
                System.out.println("d[" + i + "][" + j + "] = " + d[i][j]);
                if (j == 0) {
                    lon = d[i][j];
                } else if (j == 1) {
                    lat = d[i][j];
                }
            }
            String stationname = SNArray[i];
            String stationid = SIDArray[i];
            System.out.println("----------");
            // 用给定的经纬度构造GeoPoint，单位是微度 (度 * 1E6)

            latLng = new LatLng(lon, lat);

            BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromResource(R.drawable.marks);
            OverlayOptions options = new MarkerOptions().position(latLng).icon(bitmapDescriptor).zIndex(5);
            marker = (Marker) mBaiduMap.addOverlay(options);
            Bundle bundle = new Bundle();
            bundle.putString("stationId", stationid);
            bundle.putString("TOOLBAR_ITEM", TOOLBAR_ITEM);
            bundle.putString("stationName", stationname);
            marker.setExtraInfo(bundle);

        }

    }


    private void initUI() {
        mMapView = (MapView) findViewById(R.id.bmapView);
//        view = LayoutInflater.from(MapActivity.this).inflate(R.layout.popview, null);
        mLineraLayout = (LinearLayout) findViewById(R.id.user_info);
        image = (ImageView) findViewById(R.id.img);
        stationName = (TextView) findViewById(R.id.desc);

        //在气泡上显示站点经纬度etSnippet());
        geoPoints = (TextView) findViewById(R.id.point_info);
        // 中心点
        mBaiduMap = mMapView.getMap();

        //哈尔滨经纬度:(126.63333,45.75000) 
//		point = new GeoPoint((int) (45.75000 * 1E6), (int) (126.63333 * 1E6));
//        point = new GeoPoint((int) (39.54 * 1E6), (int) (116.23 * 1E6));
        textback = (TextView) findViewById(R.id.textback);
        textback.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                finish();
            }
        });
    }


    public void onPop(View view, LatLng latLng, int offset, final Bundle bundle) {
        /**OverlayItem overlayItem, GeoPoint pt
         * 弹出气泡
         */
mLineraLayout.setVisibility(View.VISIBLE);
//        mInfoWindow = new InfoWindow(view,latLng,offset);

        mLineraLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MapActivity.this, SiteTabHost.class);
                intent.putExtra("data", bundle);
                startActivity(intent);
            }
        });

//        mBaiduMap.showInfoWindow(mInfoWindow);

        stationName.setText(bundle.getString("stationName"));
        //在气泡上显示站点名称


        Calendar c = Calendar.getInstance(Locale.CHINA);
        mYear = c.get(Calendar.YEAR); //获取当前年份
        mMonth = c.get(Calendar.MONTH);//获取当前月份
        mDay = c.get(Calendar.DAY_OF_MONTH);//获取当前月份的日期号码

        firsttime = mYear + "-" + (mMonth + 1) + "-" + mDay;
        startstr1 = mYear + "-" + (mMonth + 1) + "-" + mDay + " " + "00:00";
        endtime1 = mYear + "-" + (mMonth + 1) + "-" + mDay + " " + "23:59";
        params.clear();
        params.add(new BasicNameValuePair("startTime", startstr1));
        params.add(new BasicNameValuePair("endTime", endtime1));
        params.add(new BasicNameValuePair("stationId", bundle.getString("stationId")));
        params.add(new BasicNameValuePair("currentPage", "1"));
        params.add(new BasicNameValuePair("pageSize", "1"));

        //访问网络得到数据流
        InputStream input = PublicHttpActivity.getJSONData(App.GetPicturesPath, params);
        //解析得到的流
        pictrue = ReadFile.getPicture(input);
        getPigShow(pictrue, firsttime);



    }


    @Override
    protected void onDestroy() {

        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    protected void onPause() {

        super.onPause();

        mMapView.onPause();
    }

    @Override
    protected void onResume() {

        super.onResume();
        mMapView.onResume();
    }
}
