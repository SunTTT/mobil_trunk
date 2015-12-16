package com.longhui.service;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.longhui.entity.QsbjList;

public class QsbjHandler  extends DefaultHandler{

	QsbjList qsbjList = new QsbjList() ;
	private String tag;
	

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		String value = new String(ch, start, length);
		if ("PhoneNumber".equals(tag)) {
			qsbjList.setPhoneNumber(value);
		}else if ("Checked".equals(tag)) {
			qsbjList.setChecked(value);
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
		}
	}




	public QsbjList getQsbjList() {
		// TODO Auto-generated method stub
		return qsbjList;
	}

}
