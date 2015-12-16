package com.longhui.entity;

import java.util.List;

public class Picture {

	private String totalnum;
	private List<PictureList> details;

	public Picture() {
		super();
	}

	

	public String getTotalnum() {
		return totalnum;
	}

	public void setTotalnum(String totalnum) {
		this.totalnum = totalnum;
	}

	public List<PictureList> getDetails() {
		return details;
	}

	public void setDetails(List<PictureList> details) {
		this.details = details;
	}
	
	
	

	
}
