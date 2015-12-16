package com.longhui.entity;

public class AlermXiTong {
	private String interval ;
	private String IsNormal ;
	private String IsAlerm ;
	public AlermXiTong(){
		super();
	}
	
	public String getinterval() {
		return interval;
	}

	public void setinterval(String alermTime) {
		interval = alermTime;
	}
	
	public String getIsNormal() {
		return IsNormal;
	}

	public void setIsNormal(String isAlerm) {
		IsNormal = isAlerm;
	}
	
	public String getIsAlerm() {
		return IsAlerm;
	}

	public void setIsAlerm(String isAlerm) {
		IsAlerm = isAlerm;
	}
}
