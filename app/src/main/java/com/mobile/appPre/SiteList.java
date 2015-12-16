package com.mobile.appPre;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.appPre.R;
import com.mobile.publiclass.HttpThread;
import com.mobile.publiclass.PublicHttpActivity;
import com.mobile.publiclass.SysExitUtil;

public class SiteList extends Activity {
    private String TOOLBAR_ITEM = null;

    HttpThread thread = null; //线程对像
    private String METHOD_NAME = ""; // 函数名
    private List<NameValuePair> params = new ArrayList<NameValuePair>();
    private PublicApplication App = new PublicApplication();
    String[] Groupname;
    String[] Groupid;

    ExpandableAdapter expandableAdapter;
    ExpandableListView exList;

    List<Map<String, String>> groupData = new ArrayList<Map<String, String>>();

    List<List<Map<String, String>>> childData = new ArrayList<List<Map<String, String>>>();
    private static final String C_TEXT1 = "c_text1";
    private ArrayList<HashMap<String, Object>> mylist;
    String[] groupid, groupname;

    String groupidstring = "";
    String groupnamestring = "";

    private Button mapButton;

    String list;

    //生成消息对象   
    static class MyHandler extends Handler {
        WeakReference<SiteList> mActivity;

        MyHandler(SiteList activity) {
            mActivity = new WeakReference<SiteList>(activity);
        }

        @Override
        public void handleMessage(Message m) {
            switch (m.what) {

            }
        }
    }

    //实例化MyHandler
    MyHandler handlerwdy = new MyHandler(this);

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//全屏展示本窗口，无标题
        SysExitUtil.activityList.add(SiteList.this);
        setContentView(R.layout.expandablelistview);
        Intent intent = getIntent();
        final Bundle data = intent.getBundleExtra("data");
        TOOLBAR_ITEM = data.getString("TOOLBAR_ITEM");
        mapButton = (Button) findViewById(R.id.map_button);
        list = data.getString("SiteList");
        mapButton.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                Intent mapIntent = new Intent();
                Bundle mapBundle = new Bundle();

                mapBundle.putString("SiteList", data.getString("SiteList"));
                mapBundle.putString("TOOLBAR_ITEM", TOOLBAR_ITEM);//传时间过去
                mapIntent.putExtra("data", mapBundle);

                mapIntent.setClass(SiteList.this, MapActivity.class);
                startActivity(mapIntent);
            }
        });

        //访问
        mylist = PublicHttpActivity.getArrayList(data.getString("SiteList"));
        Log.i("SiteList", "SiteList/mylist.size= " + mylist.size());
        //定义一级列表数据源
        Groupname = new String[]{"实验室", "北京市", "天津市", "河北省", "黑龙江", "内蒙古", "河南省", "广东省", "山西省", "辽宁省",
                "吉林省", "山东省", "陕西省", "上海市", "甘肃省", "江苏省", "安徽省", "重庆市", "湖北省", "四川省", "福建省", "预留20", "预留30",
                "黄淮海小麦主产区", "东北小麦主产区", "西部小麦主产区", "长江中下游小麦主产区", "宁夏", "新疆", "西藏", "未知"};
        Groupid = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"
                , "16", "17", "18", "19", "148", "55", "149", "150", "151", "152", "153", "154", "58", "59", "76", "0"};
        int[] isGroupid = new int[Groupname.length];

        //循环mylist将解析到的Province与给定的比较  如果存在定义一个isGroupid的第i个值赋值为1
        for (int i = 0; i < Groupname.length; i++) {

            for (int j = 0; j < mylist.size(); j++) {
                if (mylist.get(j).get("Province").equals(Groupname[i])) {
                    //如果mylist中存在  isGroupid第i个值为1，
                    isGroupid[i] = 1;
                }
            }
        }
        //循环isGroupid数组 第i个为1 则存在Groupid 将存在的Groupid放入到groupid，其对应的Groupname则放入到groupname
        for (int i = 0; i < isGroupid.length; i++) {

            if (isGroupid[i] == 1) {
                groupidstring += Groupid[i] + ",";
                groupnamestring += Groupname[i] + ",";
                groupid = groupidstring.split(",");
                groupname = groupnamestring.split(",");
            }
        }

        //循环groupid 将存在的站点分类名放入到curGroupMap
        for (int i = 0; i < groupid.length; i++) {
            Map<String, String> curGroupMap = new HashMap<String, String>();
            groupData.add(curGroupMap);
            curGroupMap.put(groupid[i], groupname[i]);
        }

        //将mylist中解析的站点根据站点所属的 AddrID放入到对应的group中
        for (int i = 0; i < groupname.length; i++) {

            List<Map<String, String>> children = new ArrayList<Map<String, String>>();
            for (int j = 0; j < mylist.size(); j++) {

                if (mylist.get(j).get("Province").equals(groupname[i])) {


                    Map<String, String> curChildMap = new HashMap<String, String>();
                    children.add(curChildMap);
                    String string1 = (String) mylist.get(j).get("sitename");
                    curChildMap.put(C_TEXT1, string1);
                }
            }
            childData.add(children);
        }

        //加载分组数据
        expandableAdapter = new ExpandableAdapter(SiteList.this);
        exList = (ExpandableListView) findViewById(R.id.expandablelistview);

        exList.setAdapter(expandableAdapter);
        exList.setGroupIndicator(null);
        exList.setDivider(null);

        exList.setOnChildClickListener(new OnChildClickListener() {

            public boolean onChildClick(ExpandableListView parent, View clickedView,
                                        int groupPosition, int childPosition, long id) {

                thread = new HttpThread(handlerwdy);
                String url = App.requestURL;
                String nameSpace = App.MyNamespace;
                //空间名,可修改   　　
                if (TOOLBAR_ITEM.equals("0")) {
                    METHOD_NAME = App.GetStationData;
                } else if ((TOOLBAR_ITEM.equals("2"))) {
                    METHOD_NAME = App.GetPictures;
                } else if ((TOOLBAR_ITEM.equals("1"))) {
                    METHOD_NAME = App.GetStationData;
                } else if ((TOOLBAR_ITEM.equals("3"))) {
                    METHOD_NAME = App.GetStationData;
                }
                String methodName = METHOD_NAME;
                //需调用webservice名称  　　
                thread.doStart(url, nameSpace, methodName, params, SiteList.this);   //启动线程

                String sitename1 = null, sitename2 = null;
                //得到点击的item的值
                sitename1 = expandableAdapter.getChild(groupPosition, childPosition).toString();

                Intent intent = new Intent();
                Bundle dataid = new Bundle();
                String stationId = null, stationName = null;

		    	/*循环查询到的list列表，如果点击的item的值在list中，则把list中的第j个sitename和siteid
		    	封装到dataid 通过intent传给下一个activity*/
                for (int j = 0; j < mylist.size(); j++) {
                    sitename2 = (String) mylist.get(j).get("sitename");

                    if (sitename1.endsWith(sitename2)) {
                        stationId = (String) mylist.get(j).get("siteid");
                        stationName = sitename1;
                    }
                }

                dataid.putString("stationId", stationId);
                dataid.putString("TOOLBAR_ITEM", TOOLBAR_ITEM);
                dataid.putString("stationName", stationName);
                dataid.putString("SiteList", list);

                intent.putExtra("data", dataid);
                intent.setClass(SiteList.this, SiteTabHost.class);
                startActivity(intent);
                return false;
            }
        });
    }

    //自定义ExpandableAdapter继承BaseExpandableListAdapter类
    class ExpandableAdapter extends BaseExpandableListAdapter {

        SiteList siteList;

        public ExpandableAdapter(SiteList sil) {
            super();
            siteList = sil;
        }

        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

            View view = convertView;
            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.member_listview, null);
            }

            TextView title = (TextView) view.findViewById(R.id.groupname);
            title.setText(getGroup(groupPosition).toString());

            ImageView image = (ImageView) view.findViewById(R.id.xiaotubiao);
            if (isExpanded) {
                image.setBackgroundResource(R.drawable.open);
            } else {
                image.setBackgroundResource(R.drawable.close);
            }

            return view;
        }

        public Object getChild(int groupPosition, int childPosition) {

            return childData.get(groupPosition).get(childPosition).get(C_TEXT1).toString();
        }

        public long getChildId(int groupPosition, int childPosition) {

            Log.i("siteList", " childPosition = " + childPosition + ",");
            return childPosition;

        }

        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

            View view = convertView;
            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.member_childitem, null);
            }
            final TextView title = (TextView) view.findViewById(R.id.site_name);
            title.setText(childData.get(groupPosition).get(childPosition).get(C_TEXT1).toString());

            return view;
        }

        public int getChildrenCount(int groupPosition) {

            Log.i("SiteList/ChildrenCount", "getChildrenCount = " + childData.get(groupPosition).size());
            return childData.get(groupPosition).size();
        }

        public Object getGroup(int groupPosition) {

            return groupData.get(groupPosition).get(groupid[groupPosition]).toString();
        }

        public int getGroupCount() {
            Log.i("SiteList/getGroupCount", "getGroupCount = " + groupData.size());
            return groupData.size();
        }

        public long getGroupId(int groupPosition) {

            Log.i("siteList", " groupPosition = " + groupPosition + ",");
            return groupPosition;
        }

        public boolean hasStableIds() {

            return true;
        }

        public boolean isChildSelectable(int groupPosition, int childPosition) {

            return true;
        }

    }


    class ItemClickEvent implements AdapterView.OnItemClickListener {
        public void onItemClick(AdapterView<?> arg0, View view, int position,
                                long arg3) {

        }
    }

    public void onStart() {
        super.onStart();
    }

    public void onStop() {
        super.onStop();
    }

    public void onPause() {
        super.onPause();
    }

    public void onResume() {
        super.onResume();
    }

    public void onRestart() {
        super.onRestart();
    }

    public void onDestory() {
        super.onDestroy();
    }
}
