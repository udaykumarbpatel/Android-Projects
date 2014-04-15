/*
a. Midterm
b. AsyncImageDownloader.java
c. Udaykumar Bhupendra Kumar
 */

package com.example.rottentomatoes;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

public class AsyncImageDownload extends AsyncTask<Object, Void, String> {
	URL imageUrl;
	ImageView imageView;
	Bitmap bitmapImg;

	@Override
	protected String doInBackground(Object... params) {
		try {
			imageView = (ImageView) params[0];
			imageUrl = new URL((String) params[1]);
			HttpURLConnection con = (HttpURLConnection) imageUrl
					.openConnection();
			con.setDoInput(true);
			con.connect();
			InputStream is = con.getInputStream();
			bitmapImg = BitmapFactory.decodeStream(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPostExecute(String result) {
		if (imageView.getTag().equals(imageUrl.toString().trim())
				&& bitmapImg != null) {
			imageView.setImageBitmap(bitmapImg);
		}
	}
}