package com.longhui.entity;

import java.util.List;

public class AlermStationList {
	private int numId;
	private List<AlermStation> AlermList;
	
	public AlermStationList(){
		super();
	}
	
	public int getnumId() {
		return numId;
	}
	
	public void setnumId(int id) {
		numId = id;
	}
	
	public List<AlermStation> getAlermList() {
		return AlermList;
	}

	public void setAlermList(List<AlermStation> alermList) {
		AlermList = alermList;
	}
}
