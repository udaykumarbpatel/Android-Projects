/*
a. Assignment #.   Home Work Assignment 3
b. File Name.      GalleryActivity.java
c. Full name of all students in your group.
Udaykumar Bhupendra Kumar
Mrinali Lothey
*/

package com.example.photogallery;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class GalleryActivity extends Activity {
	ArrayList<UniqueAlbum> photoList;
	String Album_Title = "name";
	String Album_Position = "position";
	String Album_Result = "result";
	GridView gridView;
	String albumJSONUrl = "http://api.flickr.com/services/rest/?method=flickr.photosets.getPhotos&api_key=5aed449dfe66ca8acd475151c44dbe97&extras=url_sq%2Curl_m&format=json&nojsoncallback=1&photoset_id=";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gallery);
		gridView = (GridView) findViewById(R.id.gridView1);
		new AsyncAlbumGet(this).execute(albumJSONUrl + getIntent().getExtras().getString(Album_Title));
	}

	public void gResult(final ArrayList<UniqueAlbum> result) {
		this.photoList = result;
			
		gridView.setAdapter(new ImageAdapter(this));
		gridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent i = new Intent(getBaseContext(),
						ImageViewerActivity.class);
				i.putExtra(Album_Position, position);
				i.putExtra(Album_Result, photoList);
				startActivity(i);
			}
		});
	}

	public class ImageAdapter extends BaseAdapter {
		private Context mContext;
		Bitmap bmImg;

		public ImageAdapter(Context c) {
			mContext = c;
		}

		public int getCount() {
			return photoList.size();
		}

		public Object getItem(int position) {
			return photoList.get(position);
		}

		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			//View v = convertView;
			ImageView imageView;
//			if (convertView == null) {
				imageView = new ImageView(mContext);
				downloadFile(imageView, photoList.get(position).url_sq);
				imageView.setLayoutParams(new GridView.LayoutParams(135, 135));
				imageView.setPadding(0, 0, 1, 0);
//				return imageView;
//			} else {
//				imageView = (ImageView) convertView;
//			}
	        return imageView;
		}

		void downloadFile(final ImageView imageview, String fileUrl) {
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

				@Override
				protected void onPreExecute() {
					
				}

				protected void onPostExecute(String unused) {
					
					imageview.setImageBitmap(bmImg);
				}
			};
			task.execute(fileUrl);
		}
	}
}
