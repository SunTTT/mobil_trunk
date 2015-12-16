package com.longhui.entity;

public class AlermShangXia {
	private String ColumnName ;
	private String Min ;
	private String Max ;
	private String SideMin ;
	private String SideMax ;
	public AlermShangXia(){
		super();
	}
	
	public String getColumnName() {
		return ColumnName;
	}

	public void setColumnName(String alermTime) {
		ColumnName = alermTime;
	}
	
	public String getMin() {
		return Min;
	}

	public void setMin(String isAlerm) {
		Min = isAlerm;
	}
	
	public String getMax() {
		return Max;
	}

	public void setMax(String isAlerm) {
		Max = isAlerm;
	}
	
	public String getSideMin() {
		return SideMin;
	}

	public void setSideMin(String isAlerm) {
		SideMin = isAlerm;
	}
	
	public String getSideMax() {
		return SideMax;
	}

	public void setSideMax(String isAlerm) {
		SideMax = isAlerm;
	}
}
