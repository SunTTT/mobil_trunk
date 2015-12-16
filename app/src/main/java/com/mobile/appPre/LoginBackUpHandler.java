package com.mobile.appPre;

import com.longhui.entity.LoginStation;
import com.longhui.entity.SiteListLib;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TT on 2015/11/26.
 */
public class LoginBackUpHandler extends DefaultHandler {
    private LoginStation login;
    private SiteListLib siteList;
    private List<SiteListLib> details;
    private String content;

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        if ("Stations".equals(localName)) {
            login = new LoginStation();
            details = new ArrayList<SiteListLib>();


            login.setAllow(Boolean.valueOf(true));
        } else if ("Error".equals(localName)) {
            login.setAllow(Boolean.valueOf(false));

        }else if ("Station".equals(localName)){
            siteList = new SiteListLib();
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        content = new String(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if ("Index".equals(localName)) {
            siteList.setSiteID(content);
        } else if ("Name".equals(localName)) {
            siteList.setSitename(content);

        } else if ("Province".equals(localName)) {
            if (content.equals("未知")) {
                siteList.setAddrID("实验室");
            } else {
                siteList.setAddrID(content);
            }
        } else if ("Longitude".equals(localName)) {
            siteList.setSiteLon(content);
        } else if ("Latitude".equals(localName)) {
            siteList.setSiteLat(content);
        } else if ("Message".equals(localName)) {
            login.setMessage(content);
        } else if ("Station".equals(localName)) {
            details.add(siteList);
        }
        else if ("Stations".equals(localName)) {
            login.setDetails(details);
        }

    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();

    }

    public LoginStation getLoginStation() {
        return login;
    }
}
