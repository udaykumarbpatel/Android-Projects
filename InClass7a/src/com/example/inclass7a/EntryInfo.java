/*
a. Assignment #.   In Class Assignment 7
b. File Name.      EntryInfo.java
c. Full name of all students in your group.
Udaykumar Bhupendra Kumar
Mrinali Lothey
*/

package com.example.inclass7a;

import java.io.Serializable;

@SuppressWarnings("serial")
public class EntryInfo implements Serializable {

	String title = null; // title
	String r_date = null; // im:releaseDate
	String price = null; // im:price
	String category = null; // category
	String a_name = null; // im:artist
	String url_image = null;
	String url_preview = null;

	public String getUrl_preview() {
		return url_preview;
	}

	public void setUrl_preview(String url_preview) {
		this.url_preview = url_preview;
	}

	public String getUrl_image() {
		return url_image;
	}

	public void setUrl_image(String url_image) {
		this.url_image = url_image;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getR_date() {
		return r_date;
	}

	public void setR_date(String r_date) {
		this.r_date = r_date;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getA_name() {
		return a_name;
	}

	public void setA_name(String a_name) {
		this.a_name = a_name;
	}

	@Override
	public String toString() {
		return "EntryInfo [title=" + title + ", r_date=" + r_date + ", price="
				+ price + ", category=" + category + ", a_name=" + a_name
				+ ", url_image=" + url_image + ", url_preview=" + url_preview
				+ "]";
	}

}
