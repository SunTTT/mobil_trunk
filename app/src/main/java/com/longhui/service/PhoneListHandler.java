package com.longhui.service;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.longhui.entity.PhoneEntityList;
import com.longhui.entity.PhoneListEntity;
public class PhoneListHandler extends DefaultHandler{
	PhoneEntityList list;
	PhoneListEntity phone;
	List<PhoneListEntity> phoneList;
	private String tag;
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		String value = new String(ch, start, length);
		if ("PhoneNo".equals(tag)) {
			phone.setPhoneNumber(value);
		}else if ("PhoneId".equals(tag)) {
			phone.setPhoneId(Integer.parseInt(value));
		}else if ("IsStart".equals(tag)) {
			phone.setIsStart(value);
		}
		tag="";
	}

	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.endDocument();
	}
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if ("Phone".equals(localName)) {
			phoneList.add(phone);			
		}
		if ("Phones".equals(localName)) {
			list.setPhoneList(phoneList);
		}
	}

	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.startDocument();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
				// TODO Auto-generated method stub
		if("Phones".equals(localName))
		{
			list=new PhoneEntityList();
			phoneList=new ArrayList<PhoneListEntity>();
		}
		if("Phone".equals(localName))
		{
			phone=new PhoneListEntity();
		}
		if("PhoneNo".equals(localName)){
			tag = "PhoneNo";
		}else if ("PhoneId".equals(localName)) {
			tag = "PhoneId";
		}else if ("IsStart".equals(localName)) {
			tag = "IsStart";
		}
	}

	public PhoneEntityList getPhoneList() {
		// TODO Auto-generated method stub
		return list;
	}
}
