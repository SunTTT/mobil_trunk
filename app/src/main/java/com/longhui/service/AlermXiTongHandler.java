package com.longhui.service;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.longhui.entity.AlermXiTong;


public class AlermXiTongHandler extends DefaultHandler{
	private String tag;
	AlermXiTong alerm;
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		String value = new String(ch, start, length);
		if ("interval".equals(tag)) {
			alerm.setinterval(value);
		}else if ("IsNormal".equals(tag)) {
			alerm.setIsNormal(value);
		}
		else if ("IsAlerm".equals(tag)) {
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
		if("SystemAlerm".equals(localName))
		{
			alerm=new AlermXiTong();
		}
		if("interval".equals(localName)){
			tag = "interval";
		}else if ("IsNormal".equals(localName)) {
			tag = "IsNormal";
		}else if ("IsAlerm".equals(localName)) {
			tag = "IsAlerm";
		}
	}

	public AlermXiTong getAlerm() {
		// TODO Auto-generated method stub
		return alerm;
	}

}
