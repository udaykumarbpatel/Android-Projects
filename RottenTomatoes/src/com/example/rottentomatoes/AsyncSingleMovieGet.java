/*
a. Midterm
b. AsyncSingleMovieGet.java
c. Udaykumar Bhupendra Kumar
 */

package com.example.rottentomatoes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.JSONException;

import android.os.AsyncTask;
import android.util.Log;

public class AsyncSingleMovieGet extends AsyncTask<String, Void, Movie> {
	ResultPassing_Singlemovie result_activity;

	public AsyncSingleMovieGet(ResultPassing_Singlemovie result_activity) {
		this.result_activity = result_activity;
	}

	@Override
	protected Movie doInBackground(String... params) {
		String urlString = params[0];
		try {
			URL url = new URL(urlString);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.connect();
			int statusCode = con.getResponseCode();
			if (statusCode == HttpURLConnection.HTTP_OK) {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(con.getInputStream()));
				StringBuilder sb = new StringBuilder();
				String line = reader.readLine();
				while (line != null) {
					sb.append(line);
					line = reader.readLine();
				}
				Movie movies = MovieUtil.MovieJSONParser.parseMovie(sb
						.toString());
				return movies;
			}
		} catch (MalformedURLException e) {
			Log.d("demo", e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			Log.d("demo", e.getMessage());
			e.printStackTrace();
		} catch (JSONException e) {
			Log.d("demo", e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPostExecute(Movie result) {
		if (result != null) {
			result_activity.getResult(result);
		} else {
			Log.d("demo", "null result");
		}
	}

	public interface ResultPassing_Singlemovie {
		public void getResult(Movie result);

		void result_toast(Response result);
	}

}
