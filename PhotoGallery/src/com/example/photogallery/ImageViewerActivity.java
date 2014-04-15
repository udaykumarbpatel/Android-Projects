/*
a. Assignment #.   Home Work Assignment 3
b. File Name.      ImageViewerActivity.java
c. Full name of all students in your group.
Udaykumar Bhupendra Kumar
Mrinali Lothey
*/

package com.example.photogallery;

import java.io.InputStream;
import java.util.ArrayList;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;

public class ImageViewerActivity extends Activity {
	String Album_Position = "position";
	String Album_Result = "result";
	ProgressDialog progressDialog;
	ArrayList<UniqueAlbum> photoList;
	int index;
	private static final int SWIPE_MIN_DISTANCE = 120;
	private static final int SWIPE_MAX_OFF_PATH = 250;
	private static final int SWIPE_THRESHOLD_VELOCITY = 200;
	private GestureDetectorCompat mDetector;
	
	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_viewer);
		mDetector = new GestureDetectorCompat(this, new MyGestureListener());
		index = (Integer) getIntent().getSerializableExtra(Album_Position);
		this.photoList = (ArrayList<UniqueAlbum>) getIntent().getSerializableExtra(Album_Result);
		new DownloadImageTask((ImageView) findViewById(R.id.imageView1))
				.execute(photoList.get(index).url_m);
	}

	private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
		ImageView bmImage;

		public DownloadImageTask(ImageView bmImage) {
			this.bmImage = bmImage;
		}

		@Override
		protected void onPreExecute() {
			progressDialog = ProgressDialog.show(ImageViewerActivity.this, null , "Loading Photo...");
			progressDialog.setCancelable(false);
		}

		protected Bitmap doInBackground(String... params) {
			String url = params[0];
			Bitmap bmImage = null;
			try {
				InputStream in = new java.net.URL(url).openStream();
				bmImage = BitmapFactory.decodeStream(in);
			} catch (Exception e) {
				Log.e("Error", e.getMessage());
				e.printStackTrace();
			}	
			return bmImage;
		}

		protected void onPostExecute(Bitmap result) {
			progressDialog.dismiss();
			bmImage.setImageBitmap(result);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		this.mDetector.onTouchEvent(event);
		return super.onTouchEvent(event);
	}

	class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

		@Override
		public boolean onDown(MotionEvent event) {
			return true;
		}

		@Override
		public boolean onFling(MotionEvent event1, MotionEvent event2,
				float velocityX, float velocityY) {
			if (Math.abs(event1.getY() - event2.getY()) > SWIPE_MAX_OFF_PATH)
				return false;
			if (event1.getX() - event2.getX() > SWIPE_MIN_DISTANCE
					&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
//				Toast.makeText(ImageViewerActivity.this, "Left Swipe",
//						Toast.LENGTH_SHORT).show();
				if(index == photoList.size()-1)
				{
					index=-1;
				}
				index++;
				new DownloadImageTask((ImageView) findViewById(R.id.imageView1))
				.execute(photoList.get(index).url_m);
			} else if (event2.getX() - event1.getX() > SWIPE_MIN_DISTANCE
					&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
//				Toast.makeText(ImageViewerActivity.this, "Right Swipe",
//						Toast.LENGTH_SHORT).show();
				if(index==0)
				{
					index=photoList.size();
				}
				index--;
				new DownloadImageTask((ImageView) findViewById(R.id.imageView1))
				.execute(photoList.get(index).url_m);
			}

			return true;
		}
	}
	
	
	
}
