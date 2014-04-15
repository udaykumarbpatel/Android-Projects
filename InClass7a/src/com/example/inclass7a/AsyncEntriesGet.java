/*
a. Assignment #.   In Class Assignment 7
b. File Name.      AsyncEntriesGet.java
c. Full name of all students in your group.
Udaykumar Bhupendra Kumar
Mrinali Lothey
*/

package com.example.inclass7a;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParserException;

import android.os.AsyncTask;
import android.util.Log;

public class AsyncEntriesGet extends
		AsyncTask<String, Void, ArrayList<EntryInfo>> {
	MainActivity activity;

	public AsyncEntriesGet(MainActivity activity) {
		this.activity = activity;
	}

	@Override
	protected ArrayList<EntryInfo> doInBackground(String... params) {
		String urlString = params[0];
		try {
			URL url = new URL(urlString);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.connect();
			int statusCode = con.getResponseCode();
			if (statusCode == HttpURLConnection.HTTP_OK) {
				InputStream in = con.getInputStream();
				ArrayList<EntryInfo> persons = EntryUtil.EntriesXMLPullParser
						.parseEntries(in);
				return persons;
			}
		} catch (MalformedURLException e) {
			Log.d("demo", e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			Log.d("demo", e.getMessage());
			e.printStackTrace();
		} catch (NumberFormatException e) {
			Log.d("demo", e.getMessage());
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			Log.d("demo", e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPostExecute(ArrayList<EntryInfo> result) {
		if (result != null) {
			activity.gResult(result);
		} else {
			Log.d("demo", "null result");
		}
	}
}