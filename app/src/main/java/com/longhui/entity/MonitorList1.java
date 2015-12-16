package com.longhui.entity;
import java.util.List;

public class MonitorList1 {

	private String categoryID;
	private String totalnum;
	
	private String time;		// 时间
	private String AirTemprature;	//空气温度	[℃]
	private String SoilTemprature;	//土壤温度	[℃]
	private String AirHumidity;	//空气湿度	[%HR]
	private String SoilHumidity;	//土壤湿度	[%HR]
	private String Radiation;	//总辐射	[wm-2]
	private String CO2;	//CO2浓度	[ppm]
	private String Pressure;	//相对气压	[Pa]
	private String SoilHumiditys;	//土壤水分	[%HR]
	private String PAR;	//光量子	[μmols-1m-2] 
	private String CAnemometer;	//风速	[ms-1]
	private String Dogvane;	//风向	[度]
	private String Rainfall;	//降雨量	[mm]
	private String Fengshipc;	//风蚀pc	[次]
	private String AAnemometer;	//平均风速	[ms-1]
	private String MAnemometer;	//最大风速	[ms-1]
	private String FengshiKE;	//风蚀KE	[J]
	private String Jingfushe;	//净辐射	[wm-2]
	private String Guangliangdu;	//光亮度	[klux]
	private String oilpH;	//土壤pH	[未定义T]
	private String SoilHeatFlux;	//土壤热通量	[w/m-2]

	private String AirTemprature_str;	//空气温度	[℃]
	private String SoilTemprature_str;	//土壤温度	[℃]
	private String AirHumidity_str;	//空气湿度	[%HR]
	private String SoilHumidity_str;	//土壤湿度	[%HR]
	private String Radiation_str;	//总辐射	[wm-2]
	private String CO2_str;	//CO2浓度	[ppm]
	private String Pressure_str;	//相对气压	[Pa]
	private String SoilHumiditys_str;	//土壤水分	[%HR]
	private String PAR_str;	//光量子	[μmols-1m-2] 
	private String CAnemometer_str;	//风速	[ms-1]
	private String Dogvane_str;	//风向	[度]
	private String Rainfall_str;	//降雨量	[mm]
	private String Fengshipc_str;	//风蚀pc	[次]
	private String AAnemometer_str;	//平均风速	[ms-1]
	private String MAnemometer_str;	//最大风速	[ms-1]
	private String FengshiKE_str;	//风蚀KE	[J]
	private String Jingfushe_str;	//净辐射	[wm-2]
	private String Guangliangdu_str;	//光亮度	[klux]
	private String oilpH_str;	//土壤pH	[未定义T]
	private String SoilHeatFlux_str;	//土壤热通量	[w/m-2]
	
	public MonitorList1() {
		super();
	}

	public String getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(String categoryID) {
		this.categoryID = categoryID;
	}

	public String getTotalnum() {
		return totalnum;
	}

	public void setTotalnum(String totalnum) {
		this.totalnum = totalnum;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	public String getAirTemprature() {
		return AirTemprature;
	}

	public void setAirTemprature(String airTemprature) {
		this.AirTemprature = airTemprature;
	}
	public String getSoilTemprature() {
		return SoilTemprature;
	}

	public void setSoilTemprature(String soilTemprature) {
		this.SoilTemprature = soilTemprature;
	}
	public String getAirHumidity() {
		return SoilTemprature;
	}

	public void setAirHumidity(String airHumidity) {
		this.AirHumidity = airHumidity;
	}
	public String getSoilHumidity() {
		return SoilHumidity;
	}

	public void setSoilHumidity(String soilHumidity) {
		this.SoilHumidity = soilHumidity;
	}
	public String getRadiation() {
		return Radiation;
	}

	public void setRadiation(String radiation) {
		this.Radiation = radiation;
	}

	public String getCO2() {
		return CO2;
	}

	public void setCO2(String CO2) {
		this.CO2 = CO2;
	}

	public String getPressure() {
		return Pressure;
	}

	public void setPressure(String pressure) {
		this.Pressure = pressure;
	}

	public String getSoilHumiditys() {
		return SoilHumiditys;
	}

	public void setSoilHumiditys(String soilHumiditys) {
		this.SoilHumiditys = soilHumiditys;
	}

	public String getPAR() {
		return PAR;
	}

	public void setPAR(String PAR) {
		this.PAR = PAR;
	}

	public String getCAnemometer() {
		return CAnemometer;
	}

	public void setCAnemometer(String cAnemometer) {
		this.CAnemometer = cAnemometer;
	}

	public String getDogvane() {
		return Dogvane;
	}

	public void setDogvane(String dogvane) {
		this.Dogvane = dogvane;
	}

	public String getRainfall() {
		return Rainfall;
	}

	public void setRainfall(String rainfall) {
		this.Rainfall = rainfall;
	}
	public String getFengshipc() {
		return Fengshipc;
	}

	public void setFengshipc(String fengshipc) {
		this.Fengshipc = fengshipc;
	}
	public String getAAnemometer() {
		return AAnemometer;
	}

	public void setAAnemometer(String aAnemometer) {
		this.AAnemometer = aAnemometer;
	}
	public String getMAnemometer() {
		return MAnemometer;
	}

	public void setMAnemometer(String mAnemometer) {
		this.MAnemometer = mAnemometer;
	}
	public String getFengshiKE() {
		return FengshiKE;
	}

	public void setFengshiKE(String fengshiKE) {
		this.FengshiKE = fengshiKE;
	}
	public String getJingfushe() {
		return Jingfushe;
	}

	public void setJingfushe(String jingfushe) {
		this.Jingfushe = jingfushe;
	}
	public String getGuangliangdu() {
		return Guangliangdu;
	}

	public void setGuangliangdu(String guangliangdu) {
		this.Guangliangdu = guangliangdu;
	}
	public String getoilpH() {
		return oilpH;
	}

	public void setoilpH(String oilpH) {
		this.oilpH = oilpH;
	}
	public String getSoilHeatFlux() {
		return SoilHeatFlux;
	}

	public void setSoilHeatFlux(String soilHeatFlux) {
		this.SoilHeatFlux = soilHeatFlux;
	}
	

	
}
