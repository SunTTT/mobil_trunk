package com.longhui.service;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.longhui.entity.AlermJiWen;


public class AlermJiWenHandler extends DefaultHandler{
	private String tag;
	AlermJiWen alerm;
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		String value = new String(ch, start, length);
		if ("TempNo".equals(tag)) {
			alerm.setTempNo(value);
		}else if ("IsAlerm".equals(tag)) {
			alerm.setIsAlerm(value);
		}
		else if ("AlermStatus".equals(tag)) {
			alerm.setAlermStatus(value);
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
		if("TempAlerm".equals(localName))
		{
			alerm=new AlermJiWen();
		}
		if("TempNo".equals(localName)){
			tag = "TempNo";
		}else if ("IsAlerm".equals(localName)) {
			tag = "IsAlerm";
		}else if ("AlermStatus".equals(localName)) {
			tag = "AlermStatus";
		}
	}

	public AlermJiWen getAlerm() {
		// TODO Auto-generated method stub
		return alerm;
	}

}
