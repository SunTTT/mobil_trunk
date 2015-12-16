package com.longhui.entity;

public class AlermJiWen {
	private String TempNo ;
	private String IsAlerm ;
	private String AlermStatus ;
	public AlermJiWen(){
		super();
	}
	
	public String getTempNo() {
		return TempNo;
	}

	public void setTempNo(String alermTime) {
		TempNo = alermTime;
	}
	
	public String getIsAlerm() {
		return IsAlerm;
	}

	public void setIsAlerm(String isAlerm) {
		IsAlerm = isAlerm;
	}
	
	public String getAlermStatus() {
		return AlermStatus;
	}

	public void setAlermStatus(String isAlerm) {
		AlermStatus = isAlerm;
	}
}
