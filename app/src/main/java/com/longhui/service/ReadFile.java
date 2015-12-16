package com.longhui.service;

import java.io.InputStream;
import java.util.List;

import com.longhui.entity.AlermDingShi;
import com.longhui.entity.AlermJiWen;
import com.longhui.entity.AlermMessage;
import com.longhui.entity.AlermProvince;
import com.longhui.entity.AlermStation;
import com.longhui.entity.AlermStationList;
import com.longhui.entity.AlermTempRule;
import com.longhui.entity.AlermXiTong;
import com.longhui.entity.LoginStation;
import com.longhui.entity.Monitor;
import com.longhui.entity.Monitor1;
import com.longhui.entity.MrtxList;
import com.longhui.entity.PhoneEntityList;
import com.longhui.entity.Picture;
import com.longhui.entity.QsbjList;
import com.longhui.entity.SiteListLib;
import com.longhui.entity.SshbList;
import com.longhui.entity.pigTime;


public class ReadFile {

	public static Monitor getFile(InputStream inputStream) {
		Monitor monitor = null;
		try {
			monitor = SAXParseService.readXml(inputStream);
		} 
		catch (Exception e) {
			e.printStackTrace();
			//Log.i("数据异常", e.getStackTrace()+e.getMessage()+e.getLocalizedMessage()+e.getCause().toString());
		}
		return monitor;
	}
	public static Monitor1 getchartFile(InputStream inputStream) {
		Monitor1 monitor1 = null;
		try {
			monitor1 = SAXParseService.chartreadXml(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return monitor1;
	}
	
	public static LoginStation getLogin(InputStream inputStream) {
		LoginStation checklogin = null;
		try {
			checklogin = SAXParseService.readLoginXml(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return checklogin;
	}
	
	public static Picture getPicture(InputStream inputStream) {
		Picture picture = null;
		try {
			picture = SAXParseService.readPictureXml(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return picture;
		
	}
	public static SiteListLib getSiteListLib(InputStream inputStream){
		SiteListLib sitedata = null ;
		try {
			sitedata = SAXParseService.readSiteListLibXml(inputStream);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sitedata;
	}
	public static pigTime getpigTime(InputStream inputStream) {
		pigTime pigTime = null;
		try {
			pigTime = SAXParseService.readpigTimeXml(inputStream);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pigTime;
	}
	

	public static MrtxList getMrtxList(InputStream inputStream) {
		MrtxList mrtxList = null ;
		try {
			mrtxList = SAXParseService.readMrtxListXml(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mrtxList;
	}

	public static SshbList getSshbList(InputStream input) {
		SshbList sshbList = null ;
		try {
			sshbList = SAXParseService.readSshbListXml(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sshbList;
	}

	public static QsbjList getQsbjList(InputStream input) {
		QsbjList qsbjList = null ;
		try {
			qsbjList = SAXParseService.readQsbjListXml(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return qsbjList;
	}
	
	public static PhoneEntityList getPhoneList(InputStream input) {
		PhoneEntityList phoneList = null ;
		try {
			phoneList = SAXParseService.readPhoneListXml(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return phoneList;
	}
	
	public static AlermStationList getAlermList(InputStream input) {
		AlermStationList phoneList = null ;
		try {
			phoneList = SAXParseService.readAlermListXml(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return phoneList;
	}
	
	public static AlermStation getAlermStation(InputStream input) {
		AlermStation alerm = null ;
		try {
			alerm = SAXParseService.readAlermStationXml(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return alerm;
	}
	
	public static List<AlermProvince> getAlermProvince(InputStream input) {
		List<AlermProvince> alerm = null ;
		try {
			alerm = SAXParseService.readAlermProvinceXml(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return alerm;
	}
	
	public static AlermDingShi getAlermDingShi(InputStream input) {
		AlermDingShi alerm = null ;
		try {
			alerm = SAXParseService.readAlermDingShiXml(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return alerm;
	}
	
	public static AlermXiTong getAlermXiTong(InputStream input) {
		AlermXiTong alerm = null ;
		try {
			alerm = SAXParseService.readAlermXiTongXml(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return alerm;
	}
	
	public static AlermJiWen getAlermJiWen(InputStream input) {
		AlermJiWen alerm = null ;
		try {
			alerm = SAXParseService.readAlermJiWenXml(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return alerm;
	}
	
	public static AlermTempRule getAlermTempRule(InputStream input) {
		AlermTempRule alerm = null ;
		try {
			alerm = SAXParseService.readAlermTempRuleXml(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return alerm;
	}
	
	public static List<AlermTempRule> getAlermTempRuleList(InputStream input) {
		List<AlermTempRule> alerm = null ;
		try {
			alerm = SAXParseService.readAlermTempRuleListXml(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return alerm;
	}
	
	public static List<AlermMessage> getAlermMessage(InputStream input) {
		List<AlermMessage> alerm = null ;
		try {
			alerm = SAXParseService.readAlermMessageXml(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return alerm;
	}
	
}
