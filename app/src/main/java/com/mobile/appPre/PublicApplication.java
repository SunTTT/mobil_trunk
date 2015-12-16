package com.mobile.appPre;

import java.util.List;

import org.apache.http.client.CookieStore;

import com.longhui.entity.SiteListLib;

import android.app.Application;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PublicApplication extends Application {
	private boolean globalLogin = false;
	protected TextView customWindowTitle = null;
	protected LinearLayout customWindowIcon = null;
	private CookieStore mCookieStore = null;
	public int TOOLBAR_ITEM_DEFAULT = 0;// 首页
	public int TOOLBAR_ITEM_PAGEHOME = 0;// 首页
	public int TOOLBAR_ITEM_AUCTION = 1;// 拍卖
	public int TOOLBAR_ITEM_HOT = 2;// 热销
	public int TOOLBAR_ITEM_DEAL = 3;// 交易
	public int TOOLBAR_ITEM_EXCURR = 4;// 货币兑换
	public int TOOLBAR_ITEM_SEARCH = 5;// 搜索
	public int TOOLBAR_ITEM_AUCTIONPAGE = 6;// 拍卖域名购买
	public int TOOLBAR_ITEM_HOTBUYPAGE = 7;// 热卖域名购买
	public final static int TOOLBAR_ITEM_PAYMENTMETHOD = 8;// 付款方式
	public int TOOLBAR_ITEM_DEALDETAIL =  9; // 交易详情
	public int TOOLBAR_ITEM_ERROE = 10; // Error
	public List<SiteListLib>  siteList = null;
	
	public String MyNamespace= "http://www.tmcadi.cn/";//自己
	//public String MyNamespace= "http://202.196.80.146:86/";//河南农大
	//public String MyNamespace= "http://219.151.41.248:8015/";//西藏
	//public String MyNamespace= "http://192.168.1.4:8051/";//测试
	//public String requestURL= "http://219.151.41.248:8015/WebService1210.asmx";	
	public String requestURL= "http://www.tmcadi.cn/WebService1210.asmx";
	//public String requestURL= "http://202.196.80.146:86/WebService1210.asmx";
	//public String requestURL= "http://192.168.1.4:8051/WebService1210.asmx";
	public String GetStationsByUser = "GetStationsByUser"; // 方法名 
	public String GetStationData = "GetStationData"; // 方法名 
	public String GetPictures = "GetPictures"; // 方法名
	//public String AshxPicturePath = "http://219.151.41.248:8015/Picture.ashx"; 
	//public String AshxPicturePath = "http://192.168.1.4:8051/Picture.ashx";
	//public String AshxPicturePath = "http://202.196.80.146:86/Picture.ashx"; 
	public String AshxPicturePath = "http://www.tmcadi.cn/Picture.ashx"; 
	
	
	public String GetStationDataPath = requestURL+ "/GetStationData";
	public String GetPicturesPath = requestURL+ "/GetPicturess";
	public String GetAlarmSetting = requestURL+"/GetAlarmSetting";
	public String UpdateAlarmSetting =requestURL+"/UpdateAlarmSetting";
	public String GetPictureTime = requestURL+ "/GetPictureTime";
	public String GetStation_Data = requestURL+ "/GetStation_Data";
	
	public String GetAlarmEveryDay = requestURL+ "/GetAlarmEveryDay";
	public String EveryDayAlarm = requestURL+ "/EveryDayAlarm";
	public String GetAlarmTime = requestURL+ "/GetAlarmTime";
	public String UpdateAlarmTime = requestURL+ "/UpdateAlarmTime";
	
	
	public String GetQueShiAlarmSetting = requestURL+ "/GetQueShiAlarmSetting";
	public String QueShiAlarmSetting = requestURL+ "/QueShiAlarmSetting";
	
	public String GetAlarmSystem = requestURL+ "/GetAlarmSystem";
	public String SystemAlarm = requestURL+ "/SystemAlarm";



	
	// private String globalBaseURL="http://www.4.com/iphone/";

	public boolean getGlobalLogin() {
		return globalLogin;
	}

	public void setSiteList(List<SiteListLib> sitelist) {
		this.siteList = sitelist;
	}

	public List<SiteListLib> getSiteList() {
		return siteList;
	}
	
	public void setCookieStoren(CookieStore cookieStoren) {
		this.mCookieStore = cookieStoren;
	}

	public CookieStore getCookieStore() {
		return mCookieStore;
	}

	public void setGlobalLogin(boolean globalLogin) {
		this.globalLogin = globalLogin;
	}

	public String getBaseURL() {
		return requestURL+ "iphone/";
	}

	public String getURL() {
		return requestURL;
	}

	public TextView getWindowTitle() {
		return customWindowTitle;
	}

	public void setWindowTitle(TextView view) {
		this.customWindowTitle = view;
	}

	public LinearLayout getWindowIcon() {
		return customWindowIcon;
	}

	public void setWindowIcon(LinearLayout view) {
		this.customWindowIcon = view;
	}
}
