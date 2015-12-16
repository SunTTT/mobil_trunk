package com.longhui.service;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.longhui.entity.pigTime;

public class pigTimeHandler extends DefaultHandler {

	private pigTime pigtime  ;
	private String tag=null;
	private String key;
	String value = null;
	
	
	@Override
	public void startDocument() throws SAXException {
		System.out.println("---1---");
		super.startDocument(); 
		
	}
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException{
		System.out.println("---2---"+tag);
		if ("Pictures".equals(localName)){
			pigtime = new pigTime();
			tag = "Pictures";
			
		}else if ("Picture".equals(localName)){
			
			tag = "Picture";
			
		}else if ("Time".equals(localName)){
			
			tag = "Time";
			
		}
		
		super.startElement(uri, localName, qName, attributes);
	}
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		System.out.println("---3---"+tag);
		value = new String(ch, start, length);
		if ("Time".equals(tag)) {
			
			pigtime.setPigtime(value);
		}
		tag="";
		super.characters(ch, start, length);
	}
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException{
		super.endElement(uri, localName, qName);

		System.out.println("---4---"+tag);
		
	}
	@Override
	public void endDocument() throws SAXException{
		System.out.println("---5---"+tag);
		super.endDocument();
	}
	public pigTime getpigTime() {
		// TODO Auto-generated method stub
		return pigtime;
	}
	

	
	
	





}
