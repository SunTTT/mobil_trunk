package com.longhui.service;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;

import com.longhui.entity.MrtxList;

public class MrtxHandler extends DefaultHandler {
	
	private MrtxList mrtxList = new MrtxList();
	private String tag;
	
	

	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
//		Log.i("startDocument", "ִ�д˷���");
		super.startDocument();
	}




	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
//		Log.i("startElement", "ִ�д˷���");
		if("PhoneNumber".equals(localName)){
			tag = "PhoneNumber";
		}else if ("Checked".equals(localName)) {
			tag = "Checked";
		}else if ("AlertTime".equals(localName)) {
			tag = "AlertTime";
		}
	}	
	
	
	
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		String value = new String(ch, start, length);
		if ("PhoneNumber".equals(tag)) {
			mrtxList.setPhoneNumber(value);
		}else if ("Checked".equals(tag)) {
			mrtxList.setChecked(value);
		}else if ("AlertTime".equals(tag)) {
			mrtxList.setAlertTime(value);
		}
		tag="";
	}








	@Override
	public void endDocument() throws SAXException {
		
		super.endDocument();
	}






	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);
	}


	
	
	




	public MrtxList getMrtxList() {
		
		return mrtxList;
	}




}
