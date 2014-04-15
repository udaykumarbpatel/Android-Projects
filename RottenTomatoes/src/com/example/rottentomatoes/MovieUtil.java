/*
a. Midterm
b. MovieUtil.java
c. Udaykumar Bhupendra Kumar
 */

package com.example.rottentomatoes;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MovieUtil {
	// To Parse all the movies in shot
	static public class MoviesJSONParser {
		static ArrayList<Movie> parseMovies(String jsonString)
				throws JSONException {
			ArrayList<Movie> movies = new ArrayList<Movie>();
			JSONObject movieObject = new JSONObject(jsonString);
			JSONArray moviesJSONArray = movieObject.getJSONArray("movies");
			for (int i = 0; i < moviesJSONArray.length(); i++) {
				JSONObject movieJSONObject = moviesJSONArray.getJSONObject(i);
				Movie movie = new Movie(movieJSONObject);
				movies.add(movie);
			}
			return movies;
		}
	}

	// To parse movie one after other
	static public class MovieJSONParser {
		static Movie parseMovie(String jsonString) throws JSONException {
			Movie movie = null;
			JSONObject movieObject = new JSONObject(jsonString);
			movie = new Movie(movieObject);
			return movie;
		}
	}

	// To retrieve Genre for the movie in Movie Activity
	static public class MovieGenreJSONParser {
		static String parseGenre(String jsonString) throws JSONException {
			JSONObject movieObject = new JSONObject(jsonString);
			JSONArray genreJSONArray = movieObject.getJSONArray("genres");
			ArrayList<String> movie = new ArrayList<String>();
			for (int i = 0; i < genreJSONArray.length(); i++) {
				movie.add(genreJSONArray.getString(i));
			}
			String genre = "";
			for (int i = 0; i < movie.size(); i++) {
				genre = genre + movie.get(i) + ",";
			}
			return genre;
		}
	}
}