package com.longhui.entity;

import java.util.List;

public class Movies {

	private String categoryID;
	private String totalnum;
	private List<Media> details;

	public Movies() {
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

	public List<Media> getDetails() {
		return details;
	}

	public void setDetails(List<Media> details) {
		this.details = details;
	}

}
