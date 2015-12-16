package com.longhui.entity;

import java.util.List;
import java.util.Map;

public class SiteListLib {

	private String categoryID;
	private String totalnum;
	
	private String AddrID ;		//Province
	private String Sitename;	// 站点名称  Name
	private String SiteID;		//站点ID  Index
	private String SiteLon;		//经度
	private String SiteLat;		//纬度
	
	
	private Map<String, Object> tabtitle ;
	private List<SiteDataList> details;
	
	public String getSiteLon() {
		return SiteLon;
	}

	public void setSiteLon(String siteLon) {
		SiteLon = siteLon;
	}

	public String getSiteLat() {
		return SiteLat;
	}

	public void setSiteLat(String siteLat) {
		SiteLat = siteLat;
	}


	
	
	public SiteListLib() {
		super();
	}
	

	public List<SiteDataList> getDetails() {
		return details;
	}

	public void setDetails(List<SiteDataList> details) {
		this.details = details;
	}

	

	public String getSiteID() {
		return SiteID;
	}

	public void setSiteID(String SiteID) {
		this.SiteID = SiteID;
	}

	public String getSitename() {
		return Sitename;
	}

	public void setSitename(String Sitename) {
		this.Sitename = Sitename;
	}


	
	public String getAddrID() {
		return AddrID;
	}


	public void setAddrID(String addrID) {
		AddrID = addrID;
	}


	public Map<String, Object> getTitle() {
		return tabtitle;
	}
	

	public void setTitles(Map<String, Object> title) {
		this.tabtitle = title;
	}


	public String getCategoryID() {
		return categoryID;
	}


	public void setCategoryID(String categoryID) {
		this.categoryID = categoryID;
	}


	public String getTotalnum() {
		return totalnum;
	}


	public void setTotalnum(String totalnum) {
		this.totalnum = totalnum;
	}

	

	
}
