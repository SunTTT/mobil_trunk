package com.longhui.entity;

import java.util.List;

public class AlermShangXiaList {
	private String isAlarm ;
	private String interval ;
	private String setValues ;
	private List<AlermShangXia> list;
	public AlermShangXiaList(){
		super();
	}
	
	public String getisAlarm() {
		return isAlarm;
	}

	public void setisAlarm(String alermTime) {
		isAlarm = alermTime;
	}
	
	public String getinterval() {
		return interval;
	}

	public void setinterval(String isAlerm) {
		interval = isAlerm;
	}
	
	public String getsetValues() {
		return setValues;
	}

	public void setsetValues(String isAlerm) {
		setValues = isAlerm;
	}
	
	public List<AlermShangXia> getlist() {
		return list;
	}

	public void setlist(List<AlermShangXia> isAlerm) {
		list = isAlerm;
	}
}
