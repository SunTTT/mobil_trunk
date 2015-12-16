package com.longhui.entity;

public class PictureList {

	
	private String Time;		// 时间
	private String Url;	//图片路径
	private String Name;	//图片路径
	private String bitmap;	//图片路径
	private String tname;
	private String id;
	
	public PictureList() {
		super();
	}

	
	public String getTime() {
		return Time;
	}

	public void setTime(String time) {
		this.Time = time;
	}
	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		this.Url = url;
	}
	
	public String getBitmap() {
		return bitmap;
	}

	public void setBitmap(String bitmap) {
		this.bitmap = bitmap;
	}
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		this.Name = name;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}
	
}
