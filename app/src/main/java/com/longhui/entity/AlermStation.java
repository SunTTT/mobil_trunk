package com.longhui.entity;

import java.io.Serializable;

public class AlermStation implements Serializable{
	private int Id;
	private int StationId;
	private String Province;
	private String StationName;
	private int IntervalTime;
	private String SnedMethod;
	private int SendTotal;
	
	public AlermStation(){
		super();
	}
	
	public int getId() {
		return Id;
	}
	
	public void setId(int id) {
		Id = id;
	}
	public String getProvince() {
		return Province;
	}

	public void setProvince(String province) {
		Province = province;
	}

	public int getStationId() {
		return StationId;
	}

	public void setStationId(int stationId) {
		StationId = stationId;
	}

	public String getStationName() {
		return StationName;
	}

	public void setStationName(String stationName) {
		StationName = stationName;
	}
	
	public int getIntervalTime() {
		return IntervalTime;
	}
	
	public void setIntervalTime(int intervalTime) {
		IntervalTime = intervalTime;
	}
	
	public String getSnedMethod() {
		return SnedMethod;
	}
	
	public void setSnedMethod(String snedMethod) {
		SnedMethod = snedMethod;
	}
	public int getSendTotal() {
		return SendTotal;
	}

	public void setSendTotal(int sendTotal) {
		SendTotal = sendTotal;
	}
}
