package com.longhui.service;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.longhui.entity.AlermShangXiaList;

public class AlermShangXiaHandler extends DefaultHandler{
	private String tag;
	AlermShangXiaList alerm;
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		String value = new String(ch, start, length);
		/*if ("Id".equals(tag)) {
			alerm.setId(Integer.parseInt(value));
		}else if ("StationId".equals(tag)) {
			alerm.setStationId(Integer.parseInt(value));
		}else if ("Province".equals(tag)) {
			alerm.setProvince(value);
		}else if ("StationName".equals(tag)) {
			alerm.setStationName(value);
		}else if ("IntervalTime".equals(tag)) {
			alerm.setIntervalTime(Integer.parseInt(value));
		}else if ("SnedMethod".equals(tag)) {
			alerm.setSnedMethod(value);
		}else if ("SendTotal".equals(tag)) {
			alerm.setSendTotal(Integer.parseInt(value));
		}*/
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
		if("Station".equals(localName))
		{
			alerm=new AlermShangXiaList();
		}
		if("Id".equals(localName)){
			tag = "Id";
		}else if ("StationId".equals(localName)) {
			tag = "StationId";
		}else if ("Province".equals(localName)) {
			tag = "Province";
		}else if("StationName".equals(localName)){
			tag = "StationName";
		}else if ("IntervalTime".equals(localName)) {
			tag = "IntervalTime";
		}else if ("SnedMethod".equals(localName)) {
			tag = "SnedMethod";
		}else if ("SendTotal".equals(localName)) {
			tag = "SendTotal";
		}
	}

	public AlermShangXiaList getAlerm() {
		// TODO Auto-generated method stub
		return alerm;
	}

}
