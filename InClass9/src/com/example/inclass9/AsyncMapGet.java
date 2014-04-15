/*
a. Midterm
b. AsyncMoviesGet.java
c. Udaykumar Bhupendra Kumar
 */
package com.example.inclass9;

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

public class AsyncMapGet extends AsyncTask<String, Void, ArrayList<Place>> {
	ResultPassing result_activity;

	public AsyncMapGet(ResultPassing result_activity) {
		this.result_activity = result_activity;
	}

	@Override
	protected ArrayList<Place> doInBackground(String... params) {
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
				ArrayList<Place> movies = MapUtil.PlacesJSONParser
						.parsePlaces(sb.toString());
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
	protected void onPostExecute(ArrayList<Place> result) {
		if (result != null) {
			result_activity.getResult(result);
		} else {
			Log.d("demo", "null result");
		}
	}

	public interface ResultPassing {
		public void getResult(ArrayList<Place> result);
	}
}
