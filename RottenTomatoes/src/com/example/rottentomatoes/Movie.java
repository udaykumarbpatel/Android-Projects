/*
a. Midterm
b. Movie.java
c. Udaykumar Bhupendra Kumar
 */
package com.example.rottentomatoes;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

@SuppressWarnings("serial")
public class Movie implements Serializable {
	String id;
	String title;
	String year;
	String mpaa_rating;
	String audience_rating = "none", critics_rating = "none";
	String audience_score, critics_score;
	String url_poster_thumb, url_poster_detailed, url_alternate;
	String release_date;
	String runtime;

	public Movie(JSONObject personJSONObject) throws JSONException {
		this.id = personJSONObject.getString("id");
		this.title = personJSONObject.getString("title");
		this.mpaa_rating = personJSONObject.getString("mpaa_rating");
		this.year = personJSONObject.getString("year");
		if (personJSONObject.getJSONObject("ratings").has("audience_rating")) {
			this.audience_rating = personJSONObject.getJSONObject("ratings")
					.getString("audience_rating");
		}
		this.audience_score = personJSONObject.getJSONObject("ratings")
				.getString("audience_score");
		if (personJSONObject.getJSONObject("ratings").has("critics_rating")) {
			this.critics_rating = personJSONObject.getJSONObject("ratings")
					.getString("critics_rating");
		}
		this.critics_score = personJSONObject.getJSONObject("ratings")
				.getString("critics_score");
		this.url_poster_thumb = personJSONObject.getJSONObject("posters")
				.getString("thumbnail");
		this.url_poster_detailed = personJSONObject.getJSONObject("posters")
				.getString("detailed");
		this.url_alternate = personJSONObject.getJSONObject("links").getString(
				"alternate");
		this.release_date = personJSONObject.getJSONObject("release_dates")
				.getString("theater");
		this.runtime = personJSONObject.getString("runtime");
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAudience_score() {
		return audience_score;
	}

	public void setAudience_score(String audience_score) {
		this.audience_score = audience_score;
	}

	public String getCritics_score() {
		return critics_score;
	}

	public void setCritics_score(String critics_score) {
		this.critics_score = critics_score;
	}

	public String getUrl_poster_thumb() {
		return url_poster_thumb;
	}

	public void setUrl_poster_thumb(String url_poster_thumb) {
		this.url_poster_thumb = url_poster_thumb;
	}

	public String getUrl_poster_detailed() {
		return url_poster_detailed;
	}

	public void setUrl_poster_detailed(String url_poster_detailed) {
		this.url_poster_detailed = url_poster_detailed;
	}

	public String getUrl_alternate() {
		return url_alternate;
	}

	public void setUrl_alternate(String url_alternate) {
		this.url_alternate = url_alternate;
	}

	public String getRelease_date() {
		return release_date;
	}

	public void setRelease_date(String release_date) {
		this.release_date = release_date;
	}

	public String getRuntime() {
		return runtime;
	}

	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMpaa_rating() {
		return mpaa_rating;
	}

	public void setMpaa_rating(String mpaa_rating) {
		this.mpaa_rating = mpaa_rating;
	}

	public String getAudience_rating() {
		return audience_rating;
	}

	public void setAudience_rating(String audience_rating) {
		this.audience_rating = audience_rating;
	}

	public String getCritics_rating() {
		return critics_rating;
	}

	public void setCritics_rating(String critics_rating) {
		this.critics_rating = critics_rating;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + "]";
	}

}
