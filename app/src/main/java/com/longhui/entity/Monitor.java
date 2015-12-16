package com.longhui.entity;

import java.util.List;
import java.util.Map;

public class Monitor {

	private String categoryID;
	private String totalnum;
	private List<MonitorList> details;
	private List<MonitorList1>details1;
	


	private Map<String, Object> tabtitle ;
	
	
	
	public Monitor() {
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

	public List<MonitorList> getDetails() {
		return details;
	}

	public void setDetails(List<MonitorList> details) {
		this.details = details;
	}
	
	public Map<String, Object> getTitle() {
		return tabtitle;
	}
	

	public void setTitles(Map<String, Object> title) {
		this.tabtitle = title;
	}
	

	
}
