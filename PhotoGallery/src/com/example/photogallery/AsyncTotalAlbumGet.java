/*
a. Assignment #.   Home Work Assignment 3
b. File Name.      AsyncTotalAlbumGet.java
c. Full name of all students in your group.
Udaykumar Bhupendra Kumar
Mrinali Lothey
*/
package com.example.photogallery;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import org.json.JSONException;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

public class AsyncTotalAlbumGet extends
		AsyncTask<String, Void, ArrayList<TotalAlbum>> {
	MainActivity activity;
	ProgressDialog progressDialog;
	@Override
	protected void onPreExecute() {
		progressDialog = ProgressDialog.show(activity, null , "Loading photo sets...");
		progressDialog.setCancelable(false);
	}

	public AsyncTotalAlbumGet(MainActivity activity) {
		this.activity = activity;
	}

	@Override
	protected ArrayList<TotalAlbum> doInBackground(String... params) {
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
				ArrayList<TotalAlbum> albums = AlbumUtil.AlbumsJSONParser
						.parsePersons(sb.toString());
				return albums;
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
	protected void onPostExecute(ArrayList<TotalAlbum> result) {
		progressDialog.dismiss();
		if (result != null) {
			activity.gResult(result);
		} else {
			Log.d("demo", "null result");
		}

	}
}
