package com.longhui.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


import android.util.Log;

import com.longhui.entity.Monitor;
import com.longhui.entity.MonitorList;


public class MonitorHandler extends DefaultHandler {
	
	private Monitor monitor;
	private MonitorList list;
	private List<MonitorList> details;
	private List<HashMap<String, Object>> tabtitle = new ArrayList<HashMap<String, Object>>();
	private Map <String,Object> title ; 
	private String tag=null;
	private String key;
	
	private String lastTime="";
	@Override
	public void startDocument() throws SAXException {

		//Log.i("MonitorHandler/startDocument", "startDocument start.");
		super.startDocument(); 
		
	}
	
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		
		//Log.i("MonitorHandler/startElement", "startElement start..");
		if ("Dates".equals(localName)) {
			//Log.i("MonitorHandler/startElement", "Dates start");
			monitor = new Monitor();
			details = new ArrayList<MonitorList>();
			tabtitle = new ArrayList<HashMap<String, Object>>();
			title = new LinkedHashMap <String,Object>();
		}else if ("Date".equals(localName)) {
			//Log.i("MonitorHandler/startElement", "Date start");
			list = new MonitorList();
		}/*else if ("categoryID".equals(localName)) {
			tag = "categoryID";
		}else if ("totalnum".equals(localName)) {
			tag = "totalnum";
		}else if ("id".equals(localName)) {
			tag = "id";
		}*/else if ("CreateDate".equals(localName)) {
			
			tag = "CreateDate";
			key = "Time";
			
			
		}else if ("AirTemprature".equals(localName)) {
			
			tag = "AirTemprature";
			key = "bAirTemprature";
			
		   }else if ("SoilTemprature".equals(localName)) {
			tag = "SoilTemprature";
			key = "cSoilTemprature";
			
		}else if ("AirHumidity".equals(localName)) {
			tag = "AirHumidity";
			key = "dAirHumidity";
			
		}else if ("SoilHumidity".equals(localName)) {
			tag = "SoilHumidity";
			key = "eSoilHumidity";
			
		}else if ("Radiation".equals(localName)) {
			tag = "Radiation";
			key = "fRadiation";
			
		}else if ("CO2".equals(localName)) {
			tag = "CO2";
			key = "CO2";
			
		}else if ("Pressure".equals(localName)) {
			tag = "Pressure";
			key = "hPressure";
			
			
		}else if ("SoilHumiditys".equals(localName)) {
			tag = "SoilHumiditys";
			key = "iSoilHumiditys";
			
		}else if ("PAR".equals(localName)) {
			tag = "PAR";
			key = "jPAR";
			
		}else if ("Dogvane".equals(localName)) {
			tag = "Dogvane";
			key = "lDogvane";
			
		}else if ("Rainfall".equals(localName)) {
			tag = "Rainfall";
			key = "mRainfall";
			
		}else if ("Fengshipc".equals(localName)) {
			tag = "Fengshipc";
			key = "nFengshipc";
			
		}else if ("AAnemometer".equals(localName)) {
			tag = "AAnemometer";
			key = "oAAnemometer";
			
		}else if ("MAnemometer".equals(localName)) {
			tag = "MAnemometer";
			key = "pMAnemometer";
			
		}else if ("FengshiKE".equals(localName)) {
			tag = "FengshiKE";
			key = "qFengshiKE";
			
		}else if ("Jingfushe".equals(localName)) {
			tag = "Jingfushe";
			key = "rJingfushe";
			
		}else if ("Guangliangdu".equals(localName)) {
			tag = "Guangliangdu";
			key = "sGuangliangdu";
			
		}else if ("oilpH".equals(localName)) {
			tag = "oilpH";
			key = "toilpH";
			
		}else if ("SoilHeatFlux".equals(localName)) {
			tag = "SoilHeatFlux";
			key = "uSoilHeatFlux";
			
		}else if ("CAnemometer".equals(localName)) {
			tag = "CAnemometer";
			key = "vCAnemometer";
			
		}
		
	}
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		//Log.i("MonitorHandler/characters", "characters ...");
		
		
			String value = new String(ch, start, length);
			String[] a = value.split(",");
			if ("CreateDate".equals(tag)) {

				value = "";
				value = new String(ch, 0, 16);
				//value = value.replace('/', '-');
				//Pattern pattern = Pattern.compile("[0-9]{4}-[0-9]{1,2}-[0-9]{1,2} [0-9]{1,2}:[0-9]{1,2}:[0-9]{1,2}");
				Pattern pattern = Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}");
				Matcher matcher = pattern.matcher(value);

				boolean b = matcher.matches();

				if (!title.containsKey(tag)) {
					title.put(key, "数据时间" + "\n" + " [小时:分钟]");
					//title.put(key, "数据时间"+ "\n");
				}
				if (b == false) {
					if (lastTime != "" && lastTime.length() > 14) {
						int m = Integer.valueOf(lastTime.substring(14, 15));//分钟：0
						int n = m + 5;
						lastTime.replace(lastTime.substring(14, 15), n + "");
						list.setTime(lastTime);
					}
				} else {
					list.setTime(value);
				}

				lastTime = value;
			} else if ("AirTemprature".equals(tag)) {
				for (int i = 0; i <= a.length - 1; i++) {

					tag = "AirTemprature" + (i + 1);
					key = "bAirTemprature" + (i + 1);
					if (!title.containsKey(tag)) {
						if (a.length <= 1) {
							title.put(tag, "空气温度" + "\n" + " [℃]");
						} else {
							title.put(tag, "空气温度" + (i + 1) + "\n" + " [℃]");
						}

					}
					list.setAirTemprature(a);
				}
				//list.setAirTemprature(value);
			} else if ("SoilTemprature".equals(tag)) {
				for (int i = 0; i <= a.length - 1; i++) {
					tag = "SoilTemprature" + (i + 1);
					key = "bSoilTemprature" + (i + 1);
					if (!title.containsKey(tag)) {
						if (a.length <= 1) {
							title.put(tag, "土壤温度" + "\n" + " [℃]");
						} else {
							title.put(tag, "土壤温度" + (i + 1) + "\n" + " [℃]");
						}

					}
					list.setSoilTemprature(a);
				}
				//list.setSoilTemprature(value);
			} else if ("AirHumidity".equals(tag)) {
				for (int i = 0; i <= a.length - 1; i++) {
					tag = "AirHumidity" + (i + 1);
					key = "bAirHumidity" + (i + 1);
					if (!title.containsKey(tag)) {
						if (a.length <= 1) {
							title.put(tag, "空气湿度" + "\n" + " [%HR]");
						} else {
							title.put(tag, "空气湿度" + (i + 1) + "\n" + " [%HR]");
						}

					}
					list.setAirHumidity(a);
				}
				//list.setAirHumidity(value);
			} else if ("SoilHumidity".equals(tag)) {
				for (int i = 0; i <= a.length - 1; i++) {
					tag = "SoilHumidity" + (i + 1);
					key = "bSoilHumidity" + (i + 1);
					if (!title.containsKey(tag)) {
						if (a.length <= 1) {
							title.put(tag, "土壤湿度" + "\n" + " [%HR]");
						} else {
							title.put(tag, "土壤湿度" + (i + 1) + "\n" + " [%HR]");
						}

					}
					list.setSoilHumidity(a);
				}
				//list.setSoilHumidity(value);
			} else if ("Radiation".equals(tag)) {

				for (int i = 0; i <= a.length - 1; i++) {
					tag = "Radiation" + (i + 1);
					key = "bRadiation" + (i + 1);
					if (!title.containsKey(tag)) {
						if (a.length <= 1) {
							title.put(tag, "总辐射" + "\n" + " [wm-2]");
						} else {
							title.put(tag, "总辐射" + (i + 1) + "\n" + " [wm-2]");
						}

					}
					list.setRadiation(a);
				}

				//list.setRadiation(value);
			} else if ("CO2".equals(tag)) {
				for (int i = 0; i <= a.length - 1; i++) {

					tag = "CO2" + (i + 1);
					key = "bCO2" + (i + 1);
					if (!title.containsKey(tag)) {
						if (a.length <= 1) {
							title.put(tag, "CO2浓度" + "\n" + " [ppm]");
						} else {
							title.put(tag, "CO2浓度" + (i + 1) + "\n" + " [ppm]");
						}

					}
					list.setCO2(a);
				}
				//list.setCO2(value);
			} else if ("Pressure".equals(tag)) {
				for (int i = 0; i <= a.length - 1; i++) {
					tag = "Pressure" + (i + 1);
					key = "bPressure" + (i + 1);
					if (!title.containsKey(tag)) {
						if (a.length <= 1) {
							title.put(tag, "相对气压" + "\n" + " [Pa]");
						} else {
							title.put(tag, "相对气压" + (i + 1) + "\n" + " [Pa]");
						}

					}
					list.setPressure(a);
				}
				//list.setPressure(value);
			} else if ("SoilHumiditys".equals(tag)) {
				for (int i = 0; i <= a.length - 1; i++) {
					tag = "SoilHumiditys" + (i + 1);
					key = "bSoilHumiditys" + (i + 1);
					if (!title.containsKey(tag)) {
						if (a.length <= 1) {
							title.put(tag, "土壤水分" + "\n" + " [%HR]");
						} else {
							title.put(tag, "土壤水分" + (i + 1) + "\n" + " [%HR]");
						}

					}
					list.setSoilHumiditys(a);
				}
				//list.setSoilHumiditys(value);
			} else if ("PAR".equals(tag)) {
				for (int i = 0; i <= a.length - 1; i++) {
					tag = "PAR" + (i + 1);
					key = "bPAR" + (i + 1);
					if (!title.containsKey(tag)) {
						if (a.length <= 1) {
							title.put(tag, "光量子" + "\n" + " [μmols-1m-2]");
						} else {
							title.put(tag, "光量子" + (i + 1) + "\n"
									+ " [μmols-1m-2]");
						}

					}
					list.setPAR(a);
				}
				//list.setPAR(value);
			} else if ("CAnemometer".equals(tag)) {
				for (int i = 0; i <= a.length - 1; i++) {
					tag = "CAnemometer" + (i + 1);
					key = "bCAnemometer" + (i + 1);
					if (!title.containsKey(tag)) {
						if (a.length <= 1) {
							title.put(tag, "风速" + "\n" + " [ms-1]");
						} else {
							title.put(tag, "风速" + (i + 1) + "\n" + " [ms-1]");
						}

					}
					list.setCAnemometer(a);
				}
				//list.setCAnemometer(value);
			} else if ("Dogvane".equals(tag)) {

				for (int i = 0; i <= a.length - 1; i++) {
					tag = "Dogvane" + (i + 1);
					key = "bDogvane" + (i + 1);
					if (!title.containsKey(tag)) {
						if (a.length <= 1) {
							title.put(tag, "风向" + "\n" + " [度]");
						} else {
							title.put(tag, "风向" + (i + 1) + "\n" + " [度]");
						}

					}
					list.setDogvane(a);
				}

				//list.setDogvane(value);
			} else if ("Rainfall".equals(tag)) {
				for (int i = 0; i <= a.length - 1; i++) {
					tag = "Rainfall" + (i + 1);
					key = "bRainfall" + (i + 1);
					if (!title.containsKey(tag)) {
						if (a.length <= 1) {
							title.put(tag, "降雨量" + "\n" + " [mm]");
						} else {
							title.put(tag, "降雨量" + (i + 1) + "\n" + " [mm]");
						}

					}
					list.setRainfall(a);
				}
				//list.setRainfall(value);
			} else if ("Fengshipc".equals(tag)) {
				for (int i = 0; i <= a.length - 1; i++) {
					tag = "Fengshipc" + (i + 1);
					key = "bFengshipc" + (i + 1);
					if (!title.containsKey(tag)) {
						if (a.length <= 1) {
							title.put(tag, "风蚀pc" + "\n" + " [次]");
						} else {
							title.put(tag, "风蚀pc" + (i + 1) + "\n" + " [次]");
						}

					}
					list.setRainfall(a);
				}
				//list.setFengshipc(value);
			} else if ("AAnemometer".equals(tag)) {
				for (int i = 0; i <= a.length - 1; i++) {
					tag = "AAnemometer" + (i + 1);
					key = "bAAnemometer" + (i + 1);
					if (!title.containsKey(tag)) {
						if (a.length <= 1) {
							title.put(tag, "平均风速" + "\n" + " [ms-1]");
						} else {
							title.put(tag, "平均风速" + (i + 1) + "\n" + " [ms-1]");
						}

					}
					list.setAAnemometer(a);
				}
				//list.setAAnemometer(value);
			} else if ("MAnemometer".equals(tag)) {
				for (int i = 0; i <= a.length - 1; i++) {
					tag = "MAnemometer" + (i + 1);
					key = "bMAnemometer" + (i + 1);
					if (!title.containsKey(tag)) {
						if (a.length <= 1) {
							title.put(tag, "最大风速" + "\n" + " [ms-1]");
						} else {
							title.put(tag, "最大风速" + (i + 1) + "\n" + " [ms-1]");
						}

					}
					list.setMAnemometer(a);
				}
				//list.setMAnemometer(value);
			} else if ("FengshiKE".equals(tag)) {
				for (int i = 0; i <= a.length - 1; i++) {
					tag = "FengshiKE" + (i + 1);
					key = "bFengshiKE" + (i + 1);
					if (!title.containsKey(tag)) {
						if (a.length <= 1) {
							title.put(tag, "风蚀KE" + "\n" + " [J]");
						} else {
							title.put(tag, "风蚀KE" + (i + 1) + "\n" + " [J]");
						}

					}
					list.setFengshiKE(a);
				}
				//list.setFengshiKE(value);
			} else if ("Jingfushe".equals(tag)) {
				for (int i = 0; i <= a.length - 1; i++) {
					tag = "Jingfushe" + (i + 1);
					key = "bJingfushe" + (i + 1);
					if (!title.containsKey(tag)) {
						if (a.length <= 1) {
							title.put(tag, "净辐射" + "\n" + " [wm-2]");
						} else {
							title.put(tag, "净辐射" + (i + 1) + "\n" + " [wm-2]");
						}

					}
					list.setJingfushe(a);
				}
				//list.setJingfushe(value);
			} else if ("Guangliangdu".equals(tag)) {
				for (int i = 0; i <= a.length - 1; i++) {
					tag = "Guangliangdu" + (i + 1);
					key = "bGuangliangdu" + (i + 1);
					if (!title.containsKey(tag)) {
						if (a.length <= 1) {
							title.put(tag, "光亮度" + "\n" + " [klux]");
						} else {
							title.put(tag, "光亮度" + (i + 1) + "\n" + " [klux]");
						}

					}
					list.setGuangliangdu(a);
				}
				//list.setGuangliangdu(value);
			} else if ("oilpH".equals(tag)) {
				for (int i = 0; i <= a.length - 1; i++) {
					tag = "oilpH" + (i + 1);
					key = "boilpH" + (i + 1);
					if (!title.containsKey(tag)) {
						if (a.length <= 1) {
							title.put(tag, "土壤pH" + "\n" + " [未定义T]");
						} else {
							title.put(tag, "土壤pH" + (i + 1) + "\n" + " [未定义T]");
						}

					}
					list.setOilpH(a);
				}
				//list.setoilpH(value);
			} else if ("SoilHeatFlux".equals(tag)) {
				for (int i = 0; i <= a.length - 1; i++) {
					tag = "SoilHeatFlux" + (i + 1);
					key = "bSoilHeatFlux" + (i + 1);
					if (!title.containsKey(tag)) {
						if (a.length <= 1) {
							title.put(tag, "土壤热通量" + "\n" + " [w/m-2]");
						} else {
							title.put(tag, "土壤热通量" + (i + 1) + "\n"
									+ " [w/m-2]");
						}

					}
					list.setSoilHeatFlux(a);
				}
				//list.setSoilHeatFlux(value);
			}
			tag = "";
	}
	
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		
		//Log.i("MonitorHandler/endElement", "endElement end..");
		if ("Date".equals(localName)) {
			details.add(list);			
		}
		if ("Dates".equals(localName)) {
			monitor.setDetails(details);
	//		tabtitle.add(title);
			monitor.setTitles(title);
		}
	}

	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		//Log.i("MonitorHandler/endDocument", "endDocument end.");
		super.endDocument();
	}
	public Monitor getMonitor() {
		return monitor;
	}
	
	
/*
	public void setMonitor(List<Monitor> monitor) {
		this.details = monitor;
	}
*/	
}