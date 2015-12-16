package com.longhui.entity;

import java.util.List;
import java.util.Map;

public class PhoneEntityList {
	private List<PhoneListEntity> phoneList;
	private Map<String, Object> tabtitle ;
	public PhoneEntityList(){
		super();
	}
	
	public List<PhoneListEntity> getPhoneList() {
		return phoneList;
	}

	public void setPhoneList(List<PhoneListEntity> phone) {
		phoneList = phone;
	}
	
	public Map<String, Object> getTitle() {
		return tabtitle;
	}
	

	public void setTitles(Map<String, Object> title) {
		this.tabtitle = title;
	}
}
