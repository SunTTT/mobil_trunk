package com.longhui.entity;

import android.graphics.Bitmap;

public class Movie {

	private Bitmap bitmap;
	private String title;

	public Movie() {
		super();
	}

	public Bitmap getBitmap() {
		return bitmap;
	}

	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
