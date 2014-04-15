/*
a. Midterm
b. AsyncMovieGenreGet.java
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

public class AsyncMovieGenreGet extends AsyncTask<String, Void, String> {
	GenrePassing result_activity;

	public AsyncMovieGenreGet(GenrePassing result_activity) {
		this.result_activity = result_activity;
	}

	@Override
	protected String doInBackground(String... params) {
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
				String movies = MovieUtil.MovieGenreJSONParser.parseGenre(sb
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
	protected void onPostExecute(String result) {
		if (result != null) {
			result_activity.getResult_Genre(result);
		} else {
			Log.d("demo", "null result");
		}

	}

	public interface GenrePassing {

		public void getResult_Genre(String result);

	}

}
