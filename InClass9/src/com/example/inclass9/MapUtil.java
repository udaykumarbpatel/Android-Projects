/*
a. Midterm
b. MovieUtil.java
c. Udaykumar Bhupendra Kumar
 */

package com.example.inclass9;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MapUtil {
	// To Parse all the movies in shot
	static public class PlacesJSONParser {
		static ArrayList<Place> parsePlaces(String jsonString)
				throws JSONException {
			ArrayList<Place> places = new ArrayList<Place>();
			JSONObject placeObject = new JSONObject(jsonString);
			JSONArray placesJSONArray = placeObject.getJSONArray("results");
			for (int i = 0; i < placesJSONArray.length(); i++) {
				JSONObject placeJSONObject = placesJSONArray.getJSONObject(i);
				Place place = new Place(placeJSONObject);
				places.add(place);
			}
			return places;
		}
	}
}