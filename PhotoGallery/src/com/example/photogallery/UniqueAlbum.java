/*
a. Assignment #.   Home Work Assignment 3
b. File Name.      UniqueAlbum.java
c. Full name of all students in your group.
Udaykumar Bhupendra Kumar
Mrinali Lothey
*/

package com.example.photogallery;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

@SuppressWarnings("serial")
public class UniqueAlbum implements Serializable {
	int id;
	String url_m, url_sq;

	public UniqueAlbum(JSONObject personJSONObject, int count) throws JSONException {
		this.id = count;
		this.url_m = personJSONObject.getString("url_m");
		this.url_sq = personJSONObject.getString("url_sq");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl_m() {
		return url_m;
	}

	public void setUrl_m(String url_m) {
		this.url_m = url_m;
	}

	public String getUrlsq() {
		return url_sq;
	}

	public void setUrlsq(String url_sq) {
		this.url_sq = url_sq;
	}
}
