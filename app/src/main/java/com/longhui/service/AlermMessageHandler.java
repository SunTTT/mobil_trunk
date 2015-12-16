package com.longhui.service;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.longhui.entity.AlermMessage;

public class AlermMessageHandler extends DefaultHandler{
	private String tag;
	AlermMessage alerm;
	List<AlermMessage> list=new ArrayList<AlermMessage>();
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		String value = new String(ch, start, length);
		if ("Id".equals(tag)) {
			alerm.setId(value);
		}else if ("StationName".equals(tag)) {
			alerm.setStationName(value);
		}
		else if ("Contant".equals(tag)) {
			alerm.setContant(value);
		}else if ("SnedMethod".equals(tag)) {
			alerm.setSnedMethod(value);
		}else if ("IsSend".equals(tag)) {
			alerm.setIsSend(value);
		}
		else if ("StationInfoId".equals(tag)) {
			alerm.setStationInfoId(value);
		}else if ("MessType".equals(tag)) {
			alerm.setMessType(value);
		}
		else if ("Surplus".equals(tag)) {
			alerm.setSurplus(value);
		}else if ("SendTime".equals(tag)) {
			alerm.setSendTime(value);
		}
		else if ("Phone".equals(tag)) {
			alerm.setPhone(value);
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
		if ("Messages".equals(localName)) {
			list.add(alerm);			
		}else if("Error".equals(localName))
		{
			//list.a
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
		if("Message".equals(localName))
		{
			alerm=new AlermMessage();
		}
		if("Id".equals(localName)){
			tag = "Id";
		}else if ("StationName".equals(localName)) {
			tag = "StationName";
		}else if ("Contant".equals(localName)) {
			tag = "Contant";
		}if("SnedMethod".equals(localName)){
			tag = "SnedMethod";
		}else if ("IsSend".equals(localName)) {
			tag = "IsSend";
		}else if ("StationInfoId".equals(localName)) {
			tag = "StationInfoId";
		}if("MessType".equals(localName)){
			tag = "MessType";
		}else if ("Surplus".equals(localName)) {
			tag = "Surplus";
		}else if ("SendTime".equals(localName)) {
			tag = "SendTime";
		}else if ("Phone".equals(localName)) {
			tag = "Phone";
		}
	}

	public List<AlermMessage> getAlerm() {
		// TODO Auto-generated method stub
		return list;
	}

}
