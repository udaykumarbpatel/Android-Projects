/*a. Assignment #.   In Class 6
b. File Name.      AsyncMoviesGet.java
c. Full name of all students in your group.
Udaykumar Bhupendra Kumar
Mrinali Lothey
 */

package com.example.inclass6_rt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONException;

import android.os.AsyncTask;



public class AsyncMoviesGet extends AsyncTask<String, Void, ArrayList<Movie>> {
	MainActivity activity;
	public AsyncMoviesGet(MainActivity activity){
		this.activity = activity;
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
				// Log.d("demo", sb.toString());
				ArrayList<Movie> persons = MovieUtil.PersonsJSONParser
						.parsePersons(sb.toString());
				return persons;
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPostExecute(ArrayList<Movie> result) {
		if (result != null) {
			//Log.d("demo", result.toString());
			//super.onPostExecute(result);
	        activity.gResult(result);
		} else {
			//Log.d("demo", "null result");
		}

	}
}
