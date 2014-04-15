package com.example.inclass8;

import java.io.Serializable;

@SuppressWarnings("serial")
public class News implements Serializable {

	String title;
	String url_link;
	
	
	
	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getUrl_link() {
		return url_link;
	}



	public void setUrl_link(String url_link) {
		this.url_link = url_link;
	}



	@Override
	public String toString() {
		return  title;
	}
	
	
	
}
