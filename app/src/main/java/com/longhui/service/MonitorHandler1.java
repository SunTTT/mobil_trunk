package com.longhui.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


import com.longhui.entity.Monitor;
import com.longhui.entity.Monitor1;
import com.longhui.entity.MonitorList;
import com.longhui.entity.MonitorList1;


public class MonitorHandler1 extends DefaultHandler {
	
	private Monitor1 monitor1;
	
	private MonitorList1 list;
	private List<MonitorList1> details;
	private List<HashMap<String, Object>> tabtitle = new ArrayList<HashMap<String, Object>>();
	private Map <String,Object> title ; 
	private String tag=null;
	private String key;
	@Override
	public void startDocument() throws SAXException {
//		System.out.println("---startDocument---");
		super.startDocument(); 
		
	}
	
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if ("Dates".equals(localName)) {
			monitor1 = new Monitor1();
			details = new ArrayList<MonitorList1>();
			tabtitle = new ArrayList<HashMap<String, Object>>();
			title = new LinkedHashMap <String,Object>();
		}else if ("Date".equals(localName)) {
			list = new MonitorList1();
		}else if ("categoryID".equals(localName)) {
			tag = "categoryID";
		}else if ("totalnum".equals(localName)) {
			tag = "totalnum";
		}else if ("id".equals(localName)) {
			tag = "id";
		}else if ("time".equals(localName)) {
			tag = "time";
			key = "Time";
			if(!title.containsKey(tag)){
				title.put(key,"数据接收时间");
				
			}
			
		}else if ("AirTemprature".equals(localName)) {
			tag = "AirTemprature";
			key = "bAirTemprature";
			if(!title.containsKey(tag)){
			title.put(tag,"空气温度");
			
			}
		}else if ("SoilTemprature".equals(localName)) {
			tag = "SoilTemprature";
			key = "cSoilTemprature";
			if(!title.containsKey(tag)){
			title.put(tag,"土壤温度");
			
			}
		}else if ("AirHumidity".equals(localName)) {
			tag = "AirHumidity";
			key = "dAirHumidity";
			if(!title.containsKey(tag)){
			title.put(tag,"空气湿度");
			
			}
		}else if ("SoilHumidity".equals(localName)) {
			tag = "SoilHumidity";
			key = "eSoilHumidity";
			if(!title.containsKey(tag)){
			title.put(tag,"土壤湿度");
			
			}
		}else if ("Radiation".equals(localName)) {
			tag = "Radiation";
			key = "fRadiation";
			if(!title.containsKey(tag)){
			title.put(tag,"总辐射");
			
			}
		}		
		else if ("CO2".equals(localName)) {
			tag = "CO2";
			key = "CO2";
			if(!title.containsKey(tag)){
			title.put(key,"CO2浓度");
			
			}
		}else if ("Pressure".equals(localName)) {
			tag = "Pressure";
			key = "hPressure";
			if(!title.containsKey(tag)){
			title.put(tag,"相对气压");
			
			}
		}else if ("SoilHumiditys".equals(localName)) {
			tag = "SoilHumiditys";
			key = "iSoilHumiditys";
			if(!title.containsKey(tag)){
			title.put(tag,"土壤水分");
			
			}
		}else if ("PAR".equals(localName)) {
			tag = "PAR";
			key = "jPAR";
			if(!title.containsKey(tag)){
			title.put(tag,"光量子");
			
			}
		}else if ("CAnemometer".equals(localName)) {
			tag = "CAnemometer";
			key = "kCAnemometer";
			if(!title.containsKey(tag)){
			title.put(tag,"风速");
			
			}
		}else if ("Dogvane".equals(localName)) {
			tag = "Dogvane";
			key = "lDogvane";
			if(!title.containsKey(tag)){
			title.put(tag,"风向");
			
			}
		}else if ("Rainfall".equals(localName)) {
			tag = "Rainfall";
			key = "mRainfall";
			if(!title.containsKey(tag)){
			title.put(tag,"降雨量");
			
			}
		}else if ("Fengshipc".equals(localName)) {
			tag = "Fengshipc";
			key = "nFengshipc";
			if(!title.containsKey(tag)){
			title.put(tag,"风蚀pc");
			
			}
		}else if ("AAnemometer".equals(localName)) {
			tag = "AAnemometer";
			key = "oAAnemometer";
			if(!title.containsKey(tag)){
			title.put(tag,"平均风速");
			
			}
		}else if ("MAnemometer".equals(localName)) {
			tag = "MAnemometer";
			key = "pMAnemometer";
			if(!title.containsKey(tag)){
			title.put(tag,"最大风速");
			
			}
		}else if ("FengshiKE".equals(localName)) {
			tag = "FengshiKE";
			key = "qFengshiKE";
			if(!title.containsKey(tag)){
			title.put(tag,"风蚀KE");
			
			}
		}else if ("Jingfushe".equals(localName)) {
			tag = "Jingfushe";
			key = "rJingfushe";
			if(!title.containsKey(tag)){
			title.put(tag,"净辐射");
			
			}
		}else if ("Guangliangdu".equals(localName)) {
			tag = "Guangliangdu";
			key = "sGuangliangdu";
			if(!title.containsKey(tag)){
			title.put(tag,"光亮度");
			
			}
		}else if ("oilpH".equals(localName)) {
			tag = "oilpH";
			key = "toilpH";
			if(!title.containsKey(tag)){
			title.put(tag,"土壤pH");
			
			}
		}else if ("SoilHeatFlux".equals(localName)) {
			tag = "SoilHeatFlux";
			key = "uSoilHeatFlux";
			if(!title.containsKey(tag)){
			title.put(tag,"土壤热通量");
			
			}
		}
		
	}
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		String value = new String(ch, start, length);
		if ("categoryID".equals(tag)) {
			monitor1.setCategoryID(value);
		}else if ("totalnum".equals(tag)) {
			monitor1.setTotalnum(value);
		}else if ("time".equals(tag)) {
			list.setTime(value);
		}else if ("AirTemprature".equals(tag)) {
			list.setAirTemprature(value);
		}else if ("SoilTemprature".equals(tag)) {
			list.setSoilTemprature(value);
		}else if ("AirHumidity".equals(tag)) {
			list.setAirHumidity(value);
		}else if ("SoilHumidity".equals(tag)) {
			list.setSoilHumidity(value);
		}else if ("Radiation".equals(tag)) {
			list.setRadiation(value);
		}else if ("CO2".equals(tag)) {
			list.setCO2(value);
		}else if ("Pressure".equals(tag)) {
			list.setPressure(value);
		}else if ("SoilHumiditys".equals(tag)) {
			list.setSoilHumiditys(value);
		}else if ("PAR".equals(tag)) {
			list.setPAR(value);
		}else if ("CAnemometer".equals(tag)) {
			list.setCAnemometer(value);
		}else if ("Dogvane".equals(tag)) {
			list.setDogvane(value);
		}else if ("Rainfall".equals(tag)) {
			list.setRainfall(value);
		}else if ("Fengshipc".equals(tag)) {
			list.setFengshipc(value);
		}else if ("AAnemometer".equals(tag)) {
			list.setAAnemometer(value);
		}else if ("MAnemometer".equals(tag)) {
			list.setMAnemometer(value);
		}else if ("FengshiKE".equals(tag)) {
			list.setFengshiKE(value);
		}else if ("Jingfushe".equals(tag)) {
			list.setJingfushe(value);
		}else if ("Guangliangdu".equals(tag)) {
			list.setGuangliangdu(value);
		}else if ("oilpH".equals(tag)) {
			list.setoilpH(value);
		}else if ("SoilHeatFlux".equals(tag)) {
			list.setSoilHeatFlux(value);
		}
		tag="";
	}
	
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if ("Date".equals(localName)) {
			details.add(list);			
		}
		if ("Dates".equals(localName)) {
			monitor1.setDetails(details);
	//		tabtitle.add(title);
			monitor1.setTitles(title);
		}
	}
	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.endDocument();
	}

	public Monitor1 getMonitor1() {
		return monitor1;
	}
	
	
/*
	public void setMonitor(List<Monitor> monitor) {
		this.details = monitor;
	}
*/	
}