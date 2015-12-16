package com.longhui.service;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.longhui.entity.LoginStation;
import com.longhui.entity.SiteListLib;


public class LoginHandler extends DefaultHandler {
	
	private LoginStation login;
	private SiteListLib sitelist;
	private List<SiteListLib> details;

	private String tag;
	@Override
	public void startDocument() throws SAXException {
		super.startDocument(); 		
	}
	
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes); 
		if ("Stations".equals(localName)) {
			login = new LoginStation();
			details = new ArrayList<SiteListLib>();
			login.setAllow(Boolean.valueOf(true));
		}else if("Error".equals(localName)){
			login.setAllow(Boolean.valueOf(false));
		}else if ("Message".equals(localName)) {
			tag = "Message";
		}
		else if ("Index".equals(localName)) {
			tag = "Index";
		}else if ("Name".equals(localName)) {
			tag = "Name";
		}else if ("Province".equals(localName)) {		
			tag = "Province";
		}else if ("Longitude".equals(localName)) {
			tag = "Longitude";	//经度
		}else if ("Latitude".equals(localName)) {
			tag = "Latitude";	//纬度
		}
		else if ("Allow".equals(localName)) {
			tag = "Allow";			
		}else if ("Station".equals(localName)) {		
			sitelist = new SiteListLib();
		}else if ("Site".equals(localName)) {
			tag = "Site";				
		}
		else if ("SiteID".equals(localName)) {
			tag = "SiteID";			
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		 super.characters(ch, start, length);  
		 if (null != tag) {  
			String value = new String(ch, start, length);
			if ("UserId".equals(tag)) {
				login.setID(value);
			}else if ("Name".equals(tag)) {
				sitelist.setSitename(value);
			}else if ("Province".equals(tag)) {
				if (value.equals("未知")) {
					sitelist.setAddrID("实验室");
				}else {
					sitelist.setAddrID(value);
				}							
			}else if ("Index".equals(tag)) {
				sitelist.setSiteID(value);
			}else if ("Longitude".equals(tag)) {
				sitelist.setSiteLon(value);
			}else if ("Latitude".equals(tag)) {
				sitelist.setSiteLat(value);
			}else if("Message".equals(tag)){
				login.setMessage(value);
			}
			
			tag="";
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if ("Station".equals(localName)) {
			details.add(sitelist);			
		}if ("Stations".equals(localName)) {
			login.setDetails(details);		
		}
	}

	public LoginStation getLoginStation() {
		return login;
	}		
}