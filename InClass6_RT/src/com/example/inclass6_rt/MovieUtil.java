/*a. Assignment #.   In Class 6
b. File Name.      MovieUtil.java
c. Full name of all students in your group.
Udaykumar Bhupendra Kumar
Mrinali Lothey
 */

package com.example.inclass6_rt;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class MovieUtil {
	static public class PersonsJSONParser {
		static ArrayList<Movie> parsePersons(String jsonString)
				throws JSONException {
			ArrayList<Movie> persons = new ArrayList<Movie>();
			JSONObject movieObject = new JSONObject(jsonString);
			JSONArray personsJSONArray = movieObject.getJSONArray("movies");
			for (int i = 0; i < personsJSONArray.length(); i++) {
				JSONObject personJSONObject = personsJSONArray.getJSONObject(i);
				Movie person = new Movie(personJSONObject);
				persons.add(person);
			}
			return persons;
		}
	}
}