/*
a. Assignment #.   Home Work Assignment 3
b. File Name.      AsyncAlbumGet.java
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

public class AsyncAlbumGet extends
		AsyncTask<String, Void, ArrayList<UniqueAlbum>> {
	GalleryActivity activity;
	ProgressDialog progressDialog;

	public AsyncAlbumGet(GalleryActivity activity) {
		this.activity = activity;
	}

	@Override
	protected void onPreExecute() {
		progressDialog = ProgressDialog.show(activity, null , "Loading photos...");
		progressDialog.setCancelable(false);
	}

	@Override
	protected ArrayList<UniqueAlbum> doInBackground(String... params) {
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
				ArrayList<UniqueAlbum> albums = SingleAlbumUtil.SingleAlbumJSONParser
						.parsePersons(sb.toString());
				return albums;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPostExecute(ArrayList<UniqueAlbum> result) {
		progressDialog.dismiss();
		if (result != null) {
			activity.gResult(result);
		} else {
			Log.d("demo", "null result");
		}
	}
}
