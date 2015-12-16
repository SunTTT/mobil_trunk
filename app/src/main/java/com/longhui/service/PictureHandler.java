package com.longhui.service;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.longhui.entity.Picture;
import com.longhui.entity.PictureList;


public class PictureHandler extends DefaultHandler {
	
	private Picture picture;
	private PictureList list;
	private List<PictureList> details;
	private String tag;
	
	private StringBuffer currentValue = new StringBuffer();
	@Override
	public void startDocument() throws SAXException {
		super.startDocument(); 
		
	}
	
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		
		currentValue.delete(0, currentValue.length());
		
		if ("Pictures".equals(localName)) {
			picture = new Picture();
			details = new ArrayList<PictureList>();		
		}else if ("Picture".equals(localName)) {
			list = new PictureList();
		}else if ("totalnum".equals(localName)) {
			tag = "totalnum";
		}else if ("Id".equals(localName)) {
			tag = "Id";
		}else if ("Time".equals(localName)) {
			tag = "Time";			
		}else if ("Url".equals(localName)) {
			tag = "Url";			
		}else if ("Name".equals(localName)) {
			tag = "Name";			
		}
		else if ("Tname".equals(localName)) {
			tag = "Tname";			
		}
		else if ("Data".equals(localName)) {
			tag = "Data";			
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		
		currentValue.append(ch, start, length); 
	}
	
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
				
		if ("totalnum".equals(tag)) {
			picture.setTotalnum(currentValue.toString().trim());
		}else if ("Time".equals(tag)) {
			list.setTime(currentValue.toString().trim());
		}else if ("Url".equals(tag)) {
			list.setUrl(currentValue.toString().trim());
		}else if ("Name".equals(tag)) {
			list.setName(currentValue.toString().trim());
		}else if ("Tname".equals(tag)) {
			list.setTname(currentValue.toString().trim());
		}else if ("Id".equals(tag)) {
			list.setId(currentValue.toString().trim());
		}else if ("Data".equals(tag)) {
			list.setBitmap(currentValue.toString().trim());
		}
		
		if ("Picture".equals(localName)) {
			details.add(list);			
		}
		if ("Pictures".equals(localName)) {
			picture.setDetails(details);	
		}
		tag="";
	}

	public Picture getPicture() {
		return picture;
	}
	
}