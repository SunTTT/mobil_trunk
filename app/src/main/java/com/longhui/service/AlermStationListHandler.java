package com.longhui.service;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.longhui.entity.AlermStation;
import com.longhui.entity.AlermStationList;
public class AlermStationListHandler extends DefaultHandler{
	private String tag;
	AlermStation alerm;
	AlermStationList list;
	List<AlermStation> AlermList;
	//List<AlermStationList> alermlist;
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		String value = new String(ch, start, length);
		if ("Id".equals(tag)) {
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
		if ("Station".equals(localName)) {
			AlermList.add(alerm);			
		}
		if ("Stations".equals(localName)) {
			list.setAlermList(AlermList);
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
		if("Stations".equals(localName))
		{
			list=new AlermStationList();
			AlermList=new ArrayList<AlermStation>();
		}
		if("Station".equals(localName))
		{
			alerm=new AlermStation();
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

	public AlermStationList getAlermList() {
		// TODO Auto-generated method stub
		return list;
	}
}
