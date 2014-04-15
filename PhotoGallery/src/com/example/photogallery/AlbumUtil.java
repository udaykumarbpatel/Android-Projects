/*
a. Assignment #.   Home Work Assignment 3
b. File Name.      AlbumUtil.java
c. Full name of all students in your group.
Udaykumar Bhupendra Kumar
Mrinali Lothey
*/
package com.example.photogallery;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AlbumUtil {
	static public class AlbumsJSONParser {
		static ArrayList<TotalAlbum> parsePersons(String jsonString)
				throws JSONException {
			ArrayList<TotalAlbum> albums = new ArrayList<TotalAlbum>();
			JSONObject albumObject_1 = new JSONObject(jsonString);
			JSONObject albumObject_2 = albumObject_1.getJSONObject("photosets");
			JSONArray albumsJSONArray = albumObject_2.getJSONArray("photoset");
			for (int i = 0; i < albumsJSONArray.length(); i++) {
				JSONObject albumJSONObject = albumsJSONArray.getJSONObject(i);
				TotalAlbum album = new TotalAlbum(albumJSONObject);
				albums.add(album);
			}
			return albums;
		}
	}
}