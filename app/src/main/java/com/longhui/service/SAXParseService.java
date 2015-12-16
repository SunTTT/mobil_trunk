package com.longhui.service;

import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import com.longhui.entity.*;
import com.mobile.appPre.LoginBackUpHandler;

public class SAXParseService {
	public static Monitor readXml(InputStream inStream) throws Exception {
	  
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser saxParser = spf.newSAXParser(); //����������		
			MonitorHandler handler = new MonitorHandler();
			saxParser.parse(inStream, handler);
			inStream.close();
			return handler.getMonitor();
			
	}
	public static Monitor1 chartreadXml(InputStream inStream) throws Exception {
		  
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser saxParser = spf.newSAXParser(); //����������		
		MonitorHandler1 handler1 = new MonitorHandler1();
		saxParser.parse(inStream, handler1);
		inStream.close();
		return handler1.getMonitor1();
		
}
	
	public static LoginStation readLoginXml(InputStream inStream) throws Exception {
		  
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser saxParser = spf.newSAXParser(); //����������		
		LoginHandler handler = new LoginHandler();//这是原版
//		LoginBackUpHandler handler = new LoginBackUpHandler();//自己更改的sax解析，可用
		saxParser.parse(inStream, handler);
		inStream.close();
		return handler.getLoginStation();	
	}
	
	public static Picture readPictureXml(InputStream inStream) throws Exception {
		  
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser saxParser = spf.newSAXParser(); //����������		
		PictureHandler handler = new PictureHandler();
		saxParser.parse(inStream, handler);
		inStream.close();
		return handler.getPicture();	
	}
	public static SiteListLib readSiteListLibXml(InputStream inStream) throws Exception{
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser saxParser = spf.newSAXParser();
		SiteHandler handler = new SiteHandler();
		saxParser.parse(inStream, handler);
		inStream.close();
		return handler.getSiteListLib() ;
	}
	public static pigTime readpigTimeXml(InputStream inStream) throws Exception{
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser saxParser = spf.newSAXParser();
		pigTimeHandler handler = new pigTimeHandler();
		saxParser.parse(inStream, handler);
		inStream.close();
		
		return handler.getpigTime();
	}
	
	public static MrtxList readMrtxListXml(InputStream inputStream) throws Exception{
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser saxParser = spf.newSAXParser();
		MrtxHandler handler = new MrtxHandler();
		saxParser.parse(inputStream, handler);
		inputStream.close();
		return handler.getMrtxList();
	}


	public static SshbList readSshbListXml(InputStream inputStream) throws Exception{
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser saxParser = spf.newSAXParser();
		SshbHandler handler = new SshbHandler() ;
		saxParser.parse(inputStream, handler);
		inputStream.close();
		return handler.getSshbList();
	}


	public static QsbjList readQsbjListXml(InputStream input) throws Exception{
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser saxParser = spf.newSAXParser();
		QsbjHandler handler = new QsbjHandler() ;
		saxParser.parse(input, handler);
		input.close();
		return handler.getQsbjList();
	}
	
	public static PhoneEntityList readPhoneListXml(InputStream input) throws Exception{
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser saxParser = spf.newSAXParser();
		PhoneListHandler handler = new PhoneListHandler() ;
		saxParser.parse(input, handler);
		input.close();
		return handler.getPhoneList();
	}
	
	public static AlermStationList readAlermListXml(InputStream input) throws Exception{
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser saxParser = spf.newSAXParser();
		AlermStationListHandler handler = new AlermStationListHandler() ;
		saxParser.parse(input, handler);
		input.close();
		return handler.getAlermList();
	}
	
	public static AlermStation readAlermStationXml(InputStream input) throws Exception{
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser saxParser = spf.newSAXParser();
		AlermStationHandler handler = new AlermStationHandler() ;
		saxParser.parse(input, handler);
		input.close();
		return handler.getAlerm();
	}
	
	public static List<AlermProvince> readAlermProvinceXml(InputStream input) throws Exception{
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser saxParser = spf.newSAXParser();
		AlermProvinceHandler handler = new AlermProvinceHandler() ;
		saxParser.parse(input, handler);
		input.close();
		return handler.getAlerm();
	}
	
	public static AlermDingShi readAlermDingShiXml(InputStream input) throws Exception{
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser saxParser = spf.newSAXParser();
		AlermDingShiHandler handler = new AlermDingShiHandler() ;
		saxParser.parse(input, handler);
		input.close();
		return handler.getAlerm();
	}
	
	public static AlermXiTong readAlermXiTongXml(InputStream input) throws Exception{
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser saxParser = spf.newSAXParser();
		AlermXiTongHandler handler = new AlermXiTongHandler() ;
		saxParser.parse(input, handler);
		input.close();
		return handler.getAlerm();
	}
	
	public static AlermJiWen readAlermJiWenXml(InputStream input) throws Exception{
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser saxParser = spf.newSAXParser();
		AlermJiWenHandler handler = new AlermJiWenHandler() ;
		saxParser.parse(input, handler);
		input.close();
		return handler.getAlerm();
	}
	
	public static AlermTempRule readAlermTempRuleXml(InputStream input) throws Exception{
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser saxParser = spf.newSAXParser();
		AlermTempRuleHandler handler = new AlermTempRuleHandler() ;
		saxParser.parse(input, handler);
		input.close();
		return handler.getAlerm();
	}
	
	public static List<AlermTempRule> readAlermTempRuleListXml(InputStream input) throws Exception{
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser saxParser = spf.newSAXParser();
		AlermTempRuleHandler handler = new AlermTempRuleHandler() ;
		saxParser.parse(input, handler);
		input.close();
		return handler.getAlermList();
	}
	
	public static List<AlermMessage> readAlermMessageXml(InputStream input) throws Exception{
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser saxParser = spf.newSAXParser();
		AlermMessageHandler handler = new AlermMessageHandler() ;
		saxParser.parse(input, handler);
		input.close();
		return handler.getAlerm();
	}
}
