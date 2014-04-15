/*
a. Assignment #.   In Class Assignment 7
b. File Name.      PreviewActivity.java
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

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import android.widget.TextView;

public class PreviewActivity extends Activity {
	String image = "image_url";
	Bitmap bmImg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_preview);
		EntryInfo eInfo = (EntryInfo) getIntent().getSerializableExtra(image);
		TextView app_name = (TextView) findViewById(R.id.textView1);
		ImageView imageview = (ImageView) findViewById(R.id.imageView1);
		app_name.setText(eInfo.title);
		downloadFile(imageview, eInfo.url_preview);
	}

	void downloadFile(final ImageView imageView, String fileUrl) {
		AsyncTask<String, Object, String> task = new AsyncTask<String, Object, String>() {
			@Override
			protected String doInBackground(String... params) {
				URL myFileUrl = null;
				try {
					myFileUrl = new URL(params[0]);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				try {
					HttpURLConnection conn = (HttpURLConnection) myFileUrl
							.openConnection();
					conn.setDoInput(true);
					conn.connect();
					InputStream is = conn.getInputStream();
					bmImg = BitmapFactory.decodeStream(is);
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			}

			protected void onPostExecute(String unused) {

				imageView.setImageBitmap(bmImg);
			}
		};
		task.execute(fileUrl);
	}

}
