package com.longhui.entity;

import java.util.List;

public class LoginStation {

	private String ID;
	private String name;
	private boolean allow;
	private List<SiteListLib> details;
	private String Message;
	
	public LoginStation() {
		super();
	}
	public String getID() {
		return ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public boolean getAllow() {
		return allow;
	}

	public void setAllow(boolean allow) {
		this.allow = allow;
	}

	public void setDetails(List<SiteListLib> details) {
		this.details = details;
	}
	
	public List<SiteListLib> getDetails() {
		return details;
	}
	
	public String getMessage() {
		return Message;
	}

	public void setMessage(String Message) {
		this.Message = Message;
	}
}
