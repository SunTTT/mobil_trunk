package com.longhui.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.R.integer;

import com.longhui.entity.Monitor;
import com.longhui.entity.MonitorList;
import com.longhui.entity.SiteDataList;
import com.longhui.entity.SiteListLib;


public class SiteHandler extends DefaultHandler{
	
	private SiteListLib sitedata;
	private SiteDataList sitedatalist ;
	private List<SiteDataList> details;
	private List<HashMap<String, Object>> tabtitle = new ArrayList<HashMap<String, Object>>();
	private List<String[]>   list=new ArrayList<String[]>();

	private Map <String,Object> title ; 
	private String tag=null;
	private String key;
	int n,n1,n2,n3,n4,n5,n6,n7,n8,n9,n10,n11,n12,n13,n14,n15,n16,n17,n18,n19 ;
	
	
	
	public void startDocument() throws SAXException {
//		System.out.println("---startDocument---");
		super.startDocument(); 
		
	}
	
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if ("AlarmSetting".equals(localName)) {
			sitedata = new SiteListLib();
			details = new ArrayList<SiteDataList>();
			tabtitle = new ArrayList<HashMap<String, Object>>();
			title = new LinkedHashMap <String,Object>();
			sitedatalist = new SiteDataList();
		}else if ("categoryID".equals(localName)) {
			tag = "categoryID";
		}else if ("totalnum".equals(localName)) {
			tag = "totalnum";
		}else if ("id".equals(localName)) {
			tag = "id";
		}else if (localName.contains("AirTemprature")) {
			
			tag = "AirTemprature";
			key = "bAirTemprature";
			n++;
			/*if(!title.containsKey(tag)){
			title.put(tag,"空气温度");
			
			}*/
		}else if (localName.contains("SoilTemprature")) {
			tag = "SoilTemprature";
			key = "cSoilTemprature";
			n1++;
			//if(!title.containsKey(tag)){
			//title.put(tag,"土壤温度");
			
			//}
		}else if (localName.contains("AirHumidity")) {
			tag = "AirHumidity";
			key = "dAirHumidity";
			n2++;
			/*if(!title.containsKey(tag)){
			title.put(tag,"空气湿度");
			
			}*/
		}else if (localName.contains("SoilHumidity")) {
			tag = "SoilHumidity";
			key = "eSoilHumidity";
			n3++;
			/*if(!title.containsKey(tag)){
			title.put(tag,"土壤湿度");
			
			}*/
		}else if (localName.contains("Radiation")) {
			tag = "Radiation";
			key = "fRadiation";
			n4++;
			/*if(!title.containsKey(tag)){
			title.put(tag,"总辐射");
			
			}*/
		}else if (localName.contains("CO2")) {
			tag = "CO2";
			key = "CO2";
			n5++;
			/*if(!title.containsKey(tag)){
			title.put(key,"CO2浓度");
			
			}*/
		}else if (localName.contains("Pressure")) {
			tag = "Pressure";
			key = "hPressure";
			n6++;
			/*if(!title.containsKey(tag)){
			title.put(tag,"相对气压");
			
			}*/
		}else if (localName.contains("SoilHumiditys")) {
			tag = "SoilHumiditys";
			key = "iSoilHumiditys";
			n7++;
			/*if(!title.containsKey(tag)){
			title.put(tag,"土壤水分");
			
			}*/
		}else if (localName.contains("PAR")) {
			tag = "PAR";
			key = "jPAR";
			n8++;
			/*if(!title.containsKey(tag)){
			title.put(tag,"光量子");
			
			}*/
		}else if (localName.contains("CAnemometer")) {
			tag = "CAnemometer";
			key = "CAnemometer";
			n9++;
			/*if(!title.containsKey(tag)){
			title.put(tag,"风向");
			
			}*/
		}else if (localName.contains("Dogvane")) {
			tag = "Dogvane";
			key = "lDogvane";
			n10++;
			/*if(!title.containsKey(tag)){
			title.put(tag,"风向");
			
			}*/
		}else if (localName.contains("Rainfall")) {
			tag = "Rainfall";
			key = "mRainfall";
			n11++;
			/*if(!title.containsKey(tag)){
			title.put(tag,"降雨量");
			
			}*/
		}else if (localName.contains("Fengshipc")) {
			tag = "Fengshipc";
			key = "nFengshipc";
			n12++;
			/*if(!title.containsKey(tag)){
			title.put(tag,"风蚀pc");
			
			}*/
		}else if (localName.contains("AAnemometer")) {
			tag = "AAnemometer";
			key = "oAAnemometer";
			n13++;
			/*if(!title.containsKey(tag)){
			title.put(tag,"平均风速");
			
			}*/
		}else if (localName.contains("MAnemometer")) {
			tag = "MAnemometer";
			key = "pMAnemometer";
			n14++;
			/*if(!title.containsKey(tag)){
			title.put(tag,"最大风速");
			
			}*/
		}else if (localName.contains("FengshiKE")) {
			tag = "FengshiKE";
			key = "qFengshiKE";
			n15++;
			/*if(!title.containsKey(tag)){
			title.put(tag,"风蚀KE");
			
			}*/
		}else if (localName.contains("Jingfushe")) {
			tag = "Jingfushe";
			key = "rJingfushe";
			n16++;
			/*if(!title.containsKey(tag)){
			title.put(tag,"净辐射");
			
			}*/
		}else if (localName.contains("Guangliangdu")) {
			tag = "Guangliangdu";
			key = "sGuangliangdu";
			n17++;
			/*if(!title.containsKey(tag)){
			title.put(tag,"光亮度");
			
			}*/
		}else if (localName.contains("oilpH")) {
			tag = "oilpH";
			key = "toilpH";
			n18++;
			
		}else if (localName.contains("SoilHeatFlux")) {
			tag = "SoilHeatFlux";
			key = "uSoilHeatFlux";
			n19++;
			
		}else if (localName.contains("PhoneNumber")) {
			tag = "PhoneNumber";
			key = "uPhoneNumber";
			
			
		}else if (localName.contains("Checked")) {
			tag = "Checked";
			key = "uChecked";
		
			
		}
		
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		//super.characters(ch, start, length);
	    
		String value = new String(ch, start, length);
		
		String[] a = value.split(",");
		
		if ("categoryID".equals(tag)) {
			sitedata.setCategoryID(value);
		}else if ("totalnum".equals(tag)) {
			sitedata.setTotalnum(value);
		}else if ("time".equals(tag)) {
			sitedatalist.setTime(value);
		}else if ("AirTemprature".equals(tag)) {
			   
				tag = "AirTemprature"+n;
				key = "bAirTemprature"+n;
				
				title.put(tag,"空气温度"+n);				
				list.add(a);
				//sitedatalist.setAirTemprature(list);
				
			
			
		}else if ("SoilTemprature".equals(tag)) {
			tag = "SoilTemprature"+n1;
			key = "bSoilTemprature"+n1;
			
			title.put(tag,"土壤温度"+n1);
			list.add(a);
			//sitedatalist.setSoilTemprature(list);
			
			
		}else if ("AirHumidity".equals(tag)) {
			tag = "AirHumidity"+n2;
			key = "bAirHumidity"+n2;
			
			title.put(tag,"空气湿度"+n2);
			list.add(a);
			//sitedatalist.setAirHumidity(list);
		
		}else if ("SoilHumidity".equals(tag)) {
			
			tag = "SoilHumidity"+n3;
			key = "bSoilHumidity"+n3;
			
			title.put(tag,"土壤湿度"+n3);
			list.add(a);
			//sitedatalist.setSoilHumidity(list);
			
		}else if ("Radiation".equals(tag)) {
			tag = "Radiation"+n4;
			key = "bRadiation"+n4;
			
			title.put(tag,"总辐射"+n4);
			list.add(a);
			//sitedatalist.setRadiation(list);
			
		}else if ("CO2".equals(tag)) {
			tag = "CO2"+n5;
			key = "bCO2"+n5;
			
			title.put(tag,"CO2浓度"+n5);
			list.add(a);
			//sitedatalist.setCO2(list);
			
		}else if ("Pressure".equals(tag)) {
			tag = "Pressure"+n6;
			key = "bPressure"+n6;
			
			title.put(tag,"相对气压"+n6);
			list.add(a);
			//sitedatalist.setPressure(list);
			
		}else if ("SoilHumiditys".equals(tag)) {
			tag = "SoilHumiditys"+n7;
			key = "bSoilHumiditys"+n7;
			
			title.put(tag,"土壤水分"+n7);
			list.add(a);
			//sitedatalist.setSoilHumiditys(list);
			
		}else if ("PAR".equals(tag)) {	
			tag = "PAR"+n8;
			key = "bPAR"+n8;
			
			title.put(tag,"光量子"+n8);
			list.add(a);
			//sitedatalist.setPAR(list);
			
		}else if ("CAnemometer".equals(tag)) {
			tag = "CAnemometer"+n9;
			key = "bCAnemometer"+n9;
			
			title.put(tag,"风速"+n9);
			list.add(a);
			//sitedatalist.setCAnemometer(list);
			
		}else if ("Dogvane".equals(tag)) {
			tag = "Dogvane"+n10;
			key = "bDogvane"+n10;
			
			title.put(tag,"风向"+n10);
			list.add(a);
			//sitedatalist.setDogvane(list);
			
		}else if ("Rainfall".equals(tag)) {
			tag = "Rainfall"+n11;
			key = "bRainfall"+n11;
			
			title.put(tag,"降雨量"+n11);
			list.add(a);
			//sitedatalist.setRainfall(list);
			
		}else if ("Fengshipc".equals(tag)) {
			tag = "Fengshipc"+n12;
			key = "bFengshipc"+n12;
			
			title.put(tag,"风蚀pc"+n12);
			list.add(a);
			//sitedatalist.setFengshipc(list);
			
		}else if ("AAnemometer".equals(tag)) {
			tag = "AAnemometer"+n13;
			key = "bAAnemometer"+n13;
			
			title.put(tag,"平均风速"+n13);
			list.add(a);
			//sitedatalist.setAAnemometer(list);
			
		}else if ("MAnemometer".equals(tag)) {
			tag = "MAnemometer"+n14;
			key = "bMAnemometer"+n14;
			
			title.put(tag,"最大风速"+n14);
			list.add(a);
			//sitedatalist.setMAnemometer(list);
			
		}else if ("FengshiKE".equals(tag)) {
			tag = "FengshiKE"+n15;
			key = "bFengshiKE"+n15;
			
			title.put(tag,"风蚀KE"+n15);
			list.add(a);
			//sitedatalist.setFengshiKE(list);
			
		}else if ("Jingfushe".equals(tag)) {
			tag = "Jingfushe"+n16;
			key = "bJingfushe"+n16;
			
			title.put(tag,"净辐射"+n16);
			
			list.add(a);
			//sitedatalist.setJingfushe(list);
			
		}else if ("Guangliangdu".equals(tag)) {
			tag = "Guangliangdu"+n17;
			key = "bGuangliangdu"+n17;
			
			title.put(tag,"光亮度"+n17);
			list.add(a);
			//sitedatalist.setGuangliangdu(list);
			
		}else if ("oilpH".equals(tag)) {
			tag = "oilpH"+n18;
			key = "boilpH"+n18;
			
			title.put(tag,"光亮度"+n18);
			list.add(a);
			//sitedatalist.setOilpH(list);
			
		}else if ("SoilHeatFlux".equals(tag)) {
			tag = "SoilHeatFlux"+n19;
			key = "bSoilHeatFlux"+n19;
			
			title.put(tag,"土壤热通量"+n19);
			list.add(a);
			sitedatalist.setSoilHeatFlux(list);
			
		}else if ("PhoneNumber".equals(tag)){
			tag = "PhoneNumber";
			key = "bPhoneNumber";
			
			title.put(tag,"报警电话号");
			
			sitedatalist.setPhoneNumber(value);
		}else if ("Checked".equals(tag)) {
			tag = "Checked";
			key = "bChecked";
			
			title.put(tag,"是否开启");
			
			sitedatalist.setChecked(value);
		}
		
		tag="";
		
		
		
	}
	
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
	
		if ("AlarmSetting".equals(localName)) {
			sitedatalist.setList(list);
			
			details.add(sitedatalist);
			sitedata.setDetails(details);
			sitedata.setTitles(title);			
		}
	}



	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.endDocument();
	}


	
	


	public SiteListLib getSiteListLib(){
		return sitedata;
	}
	
	
	
}
