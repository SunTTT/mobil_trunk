package com.longhui.entity;

import java.io.Serializable;

public class AlermTempRule implements Serializable{
	private String Id ;
	private String HeightTemp ;
	private String TempConstant ;
	private String StartTime ;
	public AlermTempRule(){
		super();
	}
	
	public String getId() {
		return Id;
	}

	public void setId(String alermTime) {
		Id = alermTime;
	}
	
	public String getHeightTemp() {
		return HeightTemp;
	}

	public void setHeightTemp(String isAlerm) {
		HeightTemp = isAlerm;
	}
	
	public String getTempConstant() {
		return TempConstant;
	}

	public void setTempConstant(String isAlerm) {
		TempConstant = isAlerm;
	}
	
	public String getStartTime() {
		return StartTime;
	}

	public void setStartTime(String isAlerm) {
		StartTime = isAlerm;
	}
}
