/*
a. Assignment #.   Home Work Assignment 3
b. File Name.      SingleAlbumUtil.java
c. Full name of all students in your group.
Udaykumar Bhupendra Kumar
Mrinali Lothey
*/
package com.example.photogallery;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SingleAlbumUtil {
	static public class SingleAlbumJSONParser {
		static ArrayList<UniqueAlbum> parsePersons(String jsonString)
				throws JSONException {
			ArrayList<UniqueAlbum> images = new ArrayList<UniqueAlbum>();
			JSONObject albumObject_1 = new JSONObject(jsonString);
			JSONObject albumObject_2 = albumObject_1.getJSONObject("photoset");
			JSONArray imageJSONArray = albumObject_2.getJSONArray("photo");
			for (int i = 0; i < imageJSONArray.length(); i++) {
				JSONObject imageJSONObject = imageJSONArray.getJSONObject(i);
				UniqueAlbum image = new UniqueAlbum(imageJSONObject,i);
				images.add(image);
			}
			return images;
		}
	}
}