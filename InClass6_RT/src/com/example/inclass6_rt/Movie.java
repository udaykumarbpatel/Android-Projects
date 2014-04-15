/*a. Assignment #.   In Class 6
b. File Name.      Movie.java
c. Full name of all students in your group.
Udaykumar Bhupendra Kumar
Mrinali Lothey
 */

package com.example.inclass6_rt;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

@SuppressWarnings("serial")
public class Movie implements Serializable{
	String title;
	String desc;
	int runtime;

	public Movie(JSONObject personJSONObject) throws JSONException {

		this.title = personJSONObject.getString("title");
		this.runtime = personJSONObject.getInt("runtime");
		this.desc = personJSONObject.getString("synopsis");
	}

	public String getName() {
		return title;
	}

	public void setName(String name) {
		this.title = name;
	}

	public int getId() {
		return runtime;
	}

	public void setId(int id) {
		this.runtime = id;
	}

	@Override
	public String toString() {
		return title;
	}

	public String getDept() {
		return desc;
	}

	public void setDept(String dept) {
		this.desc = dept;
	}

}
