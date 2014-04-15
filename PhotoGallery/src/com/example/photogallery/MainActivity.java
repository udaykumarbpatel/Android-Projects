/*
a. Assignment #.   Home Work Assignment 3
b. File Name.      MainActivity.java
c. Full name of all students in your group.
Udaykumar Bhupendra Kumar
Mrinali Lothey
*/

package com.example.photogallery;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {
	ArrayList<TotalAlbum> albumList;
	String photoListJSONUrl = "http://api.flickr.com/services/rest/?method=flickr.photosets.getList&api_key=5aed449dfe66ca8acd475151c44dbe97&user_id=40729938%40N03&format=json&nojsoncallback=1";
	ListView listView;
	static String Album_Title = "name";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listView = (ListView) findViewById(R.id.listView1);
		new AsyncTotalAlbumGet(this).execute(photoListJSONUrl);
	}

	public void gResult(final ArrayList<TotalAlbum> result) {
		this.albumList = result;
		ArrayAdapter<TotalAlbum> adapter = new ArrayAdapter<TotalAlbum>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1,
				albumList);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent i = new Intent(getBaseContext(), GalleryActivity.class);
				i.putExtra(Album_Title, albumList.get(position).id);
				startActivity(i);
			}
		});
	}
}
