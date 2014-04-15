package com.example.inclass9;

import org.json.JSONException;
import org.json.JSONObject;

public class Place {

	double lat, lng;
	String name;
	
	public Place(JSONObject placesJSONObject) throws JSONException {
		this.lat = placesJSONObject.getJSONObject("geometry").getJSONObject("location").getDouble("lat");
		this.lng = placesJSONObject.getJSONObject("geometry").getJSONObject("location").getDouble("lng");
		this.name = placesJSONObject.getString("name");
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Place [lat=" + lat + ", lng=" + lng + ", name=" + name + "]";
	}
	
	
	
}
