/*
a. Assignment #.   Home Work Assignment 3
b. File Name.      TotalAlbum.java
c. Full name of all students in your group.
Udaykumar Bhupendra Kumar
Mrinali Lothey
*/

package com.example.photogallery;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

@SuppressWarnings("serial")
public class TotalAlbum implements Serializable {
	String title;
	String id, number_of_photos;

	public TotalAlbum(JSONObject personJSONObject) throws JSONException {
		this.id = personJSONObject.getString("id");
		this.title = personJSONObject.getJSONObject("title").getString(
				"_content");
		this.number_of_photos = personJSONObject.getString("photos");
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNumber_of_photos() {
		return number_of_photos;
	}

	public void setNumber_of_photos(String number_of_photos) {
		this.number_of_photos = number_of_photos;
	}

	@Override
	public String toString() {
		return title + "(" + number_of_photos + ")";
	}
}
