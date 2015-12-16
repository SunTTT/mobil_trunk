package com.longhui.service;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.longhui.entity.AlermProvince;
import com.longhui.entity.AlermStation;

public class AlermProvinceHandler extends DefaultHandler{
	private String tag;
	AlermProvince alerm;
	List<AlermProvince> AlermList=new ArrayList<AlermProvince>();
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		String value = new String(ch, start, length);
		if ("Name".equals(tag)) {
			alerm.setName(value);
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
		if ("Province".equals(localName)) {
			AlermList.add(alerm);			
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
		if("Province".equals(localName))
		{
			alerm=new AlermProvince();
		}
		if("Name".equals(localName)){
			tag = "Name";
		}
	}

	public List<AlermProvince> getAlerm() {
		// TODO Auto-generated method stub
		return AlermList;
	}
}
