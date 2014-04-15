/*
a. Midterm
b. AsyncMoviesGet.java
c. Udaykumar Bhupendra Kumar
 */
package com.example.rottentomatoes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONException;

import android.os.AsyncTask;
import android.util.Log;

public class AsyncMoviesGet extends AsyncTask<String, Void, ArrayList<Movie>> {
	ResultPassing result_activity;

	public AsyncMoviesGet(ResultPassing result_activity) {
		this.result_activity = result_activity;
	}

	@Override
	protected ArrayList<Movie> doInBackground(String... params) {
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
				ArrayList<Movie> movies = MovieUtil.MoviesJSONParser
						.parseMovies(sb.toString());
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
	protected void onPostExecute(ArrayList<Movie> result) {
		if (result != null) {
			result_activity.getResult(result);
		} else {
			Log.d("demo", "null result");
		}
	}

	public interface ResultPassing {
		public void getResult(ArrayList<Movie> result);

		void result_toast(Response result);
	}
}
