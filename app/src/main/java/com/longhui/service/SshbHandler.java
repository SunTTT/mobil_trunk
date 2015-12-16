package com.longhui.service;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.longhui.entity.SshbList;


public class SshbHandler extends DefaultHandler{

	SshbList sshbList = new SshbList() ;
	private String tag;
	
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		String value = new String(ch, start, length);
		if ("PhoneNumber".equals(tag)) {
			sshbList.setPhoneNumber(value);
		}else if ("Checked".equals(tag)) {
			sshbList.setChecked(value);
		}else if ("Interval".equals(tag)) {
			sshbList.setInterval(value);
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
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);
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
		if("PhoneNumber".equals(localName)){
			tag = "PhoneNumber";
		}else if ("Checked".equals(localName)) {
			tag = "Checked";
		}else if ("Interval".equals(localName)) {
			tag = "Interval";
		}
	}




	public SshbList getSshbList() {
		// TODO Auto-generated method stub
		return sshbList;
	}

}
