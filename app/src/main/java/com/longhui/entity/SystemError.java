package com.longhui.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SystemError {

	private String ID;
	private String Message;
	private boolean allow;
	
	
	public SystemError() {
		super();
	}

	public String getID() {
		return ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String Message) {
		this.Message = Message;
	}
	
	public boolean getAllow() {
		return allow;
	}

	public void setAllow(boolean allow) {
		this.allow = allow;
	}


	
	
}
