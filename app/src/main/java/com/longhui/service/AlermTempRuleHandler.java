package com.longhui.service;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.longhui.entity.AlermTempRule;

public class AlermTempRuleHandler extends DefaultHandler{
	private String tag;
	AlermTempRule alerm;
	List<AlermTempRule> alermList=new ArrayList<AlermTempRule>();
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		String value = new String(ch, start, length);
		if ("Id".equals(tag)) {
			alerm.setId(value);
		}else if ("HeightTemp".equals(tag)) {
			alerm.setHeightTemp(value);
		}else if ("TempConstant".equals(tag)) {
			alerm.setTempConstant(value);
		}else if ("StartTime".equals(tag)) {
			alerm.setStartTime(value);
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
		if ("TempRules".equals(localName)) {
			alermList.add(alerm);			
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
		if("TempRule".equals(localName))
		{
			alerm=new AlermTempRule();
		}
		if("Id".equals(localName)){
			tag = "Id";
		}else if ("HeightTemp".equals(localName)) {
			tag = "HeightTemp";
		}else if ("TempConstant".equals(localName)) {
			tag = "TempConstant";
		}else if ("StartTime".equals(localName)) {
			tag = "StartTime";
		}
	}

	public AlermTempRule getAlerm() {
		// TODO Auto-generated method stub
		return alerm;
	}
	
	public List<AlermTempRule> getAlermList() {
		// TODO Auto-generated method stub
		return alermList;
	}

}
