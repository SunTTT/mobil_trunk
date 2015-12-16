package com.longhui.entity;

import java.util.ArrayList;
import java.util.List;

public class SiteDataList {
	private String categoryID;
	private String totalnum;
	
	private String time;		// 时间
	
	private List<String[]> AirTemprature;	//空气温度	[℃]
	private List<String[]> SoilTemprature;	//土壤温度	[℃]
	private List<String[]> AirHumidity;	//空气湿度	[%HR]
	private List<String[]> SoilHumidity;	//土壤湿度	[%HR]
	
	
	private List<String[]> Radiation;	//总辐射	[wm-2]
	private List<String[]> CO2;	//CO2浓度	[ppm]
	private List<String[]> Pressure;	//相对气压	[Pa]
	private List<String[]> SoilHumiditys;	//土壤水分	[%HR]
	private List<String[]> PAR;	//光量子	[μmols-1m-2] 
	private List<String[]> CAnemometer;	//风速	[ms-1]
	private List<String[]> Dogvane;	//风向	[度]
	private List<String[]> Rainfall;	//降雨量	[mm]
	private List<String[]> Fengshipc;	//风蚀pc	[次]
	private List<String[]> AAnemometer;	//平均风速	[ms-1]
	private List<String[]> MAnemometer;	//最大风速	[ms-1]
	private List<String[]> FengshiKE;	//风蚀KE	[J]
	private List<String[]> Jingfushe;	//净辐射	[wm-2]
	private List<String[]> Guangliangdu;	//光亮度	[klux]
	private List<String[]> oilpH;	//土壤pH	[未定义T]
	private List<String[]> SoilHeatFlux;	//土壤热通量	[w/m-2]
	private String PhoneNumber ;//报警电话号
	private String Checked ;
	private String Ksbj;
	private String dsbj ;
	
	private List<String[]>   list=new ArrayList<String[]>();
	
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
	
	
		
	
	public List<String[]> getSoilTemprature() {
		return SoilTemprature;
	}
	public void setSoilTemprature(List<String[]> soilTemprature) {
		SoilTemprature = soilTemprature;
	}
	public List<String[]> getAirHumidity() {
		return AirHumidity;
	}
	public void setAirHumidity(List<String[]> airHumidity) {
		AirHumidity = airHumidity;
	}
	public List<String[]> getSoilHumidity() {
		return SoilHumidity;
	}
	public void setSoilHumidity(List<String[]> soilHumidity) {
		SoilHumidity = soilHumidity;
	}
	public List<String[]> getRadiation() {
		return Radiation;
	}
	public void setRadiation(List<String[]> radiation) {
		Radiation = radiation;
	}
	public List<String[]> getCO2() {
		return CO2;
	}
	public void setCO2(List<String[]> cO2) {
		CO2 = cO2;
	}
	public List<String[]> getPressure() {
		return Pressure;
	}
	public void setPressure(List<String[]> pressure) {
		Pressure = pressure;
	}
	public List<String[]> getSoilHumiditys() {
		return SoilHumiditys;
	}
	public void setSoilHumiditys(List<String[]> soilHumiditys) {
		SoilHumiditys = soilHumiditys;
	}
	public List<String[]> getPAR() {
		return PAR;
	}
	public void setPAR(List<String[]> pAR) {
		PAR = pAR;
	}
	public List<String[]> getCAnemometer() {
		return CAnemometer;
	}
	public void setCAnemometer(List<String[]> cAnemometer) {
		CAnemometer = cAnemometer;
	}
	public List<String[]> getDogvane() {
		return Dogvane;
	}
	public void setDogvane(List<String[]> dogvane) {
		Dogvane = dogvane;
	}
	public List<String[]> getRainfall() {
		return Rainfall;
	}
	public void setRainfall(List<String[]> rainfall) {
		Rainfall = rainfall;
	}
	public List<String[]> getFengshipc() {
		return Fengshipc;
	}
	public void setFengshipc(List<String[]> fengshipc) {
		Fengshipc = fengshipc;
	}
	public List<String[]> getAAnemometer() {
		return AAnemometer;
	}
	public void setAAnemometer(List<String[]> aAnemometer) {
		AAnemometer = aAnemometer;
	}
	public List<String[]> getMAnemometer() {
		return MAnemometer;
	}
	public void setMAnemometer(List<String[]> mAnemometer) {
		MAnemometer = mAnemometer;
	}
	public List<String[]> getFengshiKE() {
		return FengshiKE;
	}
	public void setFengshiKE(List<String[]> fengshiKE) {
		FengshiKE = fengshiKE;
	}
	public List<String[]> getJingfushe() {
		return Jingfushe;
	}
	public void setJingfushe(List<String[]> jingfushe) {
		Jingfushe = jingfushe;
	}
	public List<String[]> getGuangliangdu() {
		return Guangliangdu;
	}
	public void setGuangliangdu(List<String[]> guangliangdu) {
		Guangliangdu = guangliangdu;
	}
	public List<String[]> getOilpH() {
		return oilpH;
	}
	public void setOilpH(List<String[]> oilpH) {
		this.oilpH = oilpH;
	}
	public List<String[]> getSoilHeatFlux() {
		return SoilHeatFlux;
	}
	public void setSoilHeatFlux(List<String[]> soilHeatFlux) {
		this.SoilHeatFlux = soilHeatFlux;
	}
	
	
	public List<String[]> getAirTemprature() {
		return AirTemprature;
	}
	public void setAirTemprature(List<String[]> airTemprature) {
		AirTemprature = airTemprature;
	}
	
	
	
	
	
	
	public String getPhoneNumber() {
		return PhoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.PhoneNumber = phoneNumber;
	}
	public String getChecked() {
		return Checked;
	}
	public void setChecked(String checked) {
		this.Checked = checked;
	}
	public String getKsbj() {
		return Ksbj;
	}
	public void setKsbj(String ksbj) {
		this.Ksbj = ksbj;
	}
	public List<String[]> getList() {
		return list;
	}
	public void setList(List<String[]> list) {
		this.list = list;
	}
	public String getDsbj() {
		return dsbj;
	}
	public void setDsbj(String dsbj) {
		this.dsbj = dsbj;
	}
	
	
	
	
	
}
