package com.longhui.entity;

import java.util.List;
import java.util.Map;

public class PhoneListEntity {
	private int PhoneId ;
	private String PhoneNo ;	
	private String IsStart ;
	
	
	public PhoneListEntity(){
		super();
	}
	
	public String getPhoneNumber() {
		return PhoneNo;
	}

	public void setPhoneNumber(String phoneNumber) {
		PhoneNo = phoneNumber;
	}

	public int getPhoneId() {
		return PhoneId;
	}

	public void setPhoneId(int Phoneid) {
		PhoneId = Phoneid;
	}

	public String getIsStart() {
		return IsStart;
	}

	public void setIsStart(String isStart) {
		IsStart = isStart;
	}
}

