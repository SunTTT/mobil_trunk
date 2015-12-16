package com.longhui.service;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.longhui.entity.AlermDingShi;

public class AlermDingShiHandler extends DefaultHandler{
	private String tag;
	AlermDingShi alerm;
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		String value = new String(ch, start, length);
		if ("AlermTime".equals(tag)) {
			alerm.setAlermTime(value);
		}else if ("IsAlerm".equals(tag)) {
			alerm.setIsAlerm(value);
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
		//if ("Station".equals(localName)) {
			//AlermList.add(alerm);			
		//}
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
		if("TimeAlerm".equals(localName))
		{
			alerm=new AlermDingShi();
		}
		if("AlermTime".equals(localName)){
			tag = "AlermTime";
		}else if ("IsAlerm".equals(localName)) {
			tag = "IsAlerm";
		}
	}

	public AlermDingShi getAlerm() {
		// TODO Auto-generated method stub
		return alerm;
	}

}
