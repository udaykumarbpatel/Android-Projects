/*a. Assignment #.   In Class 6
b. File Name.      MainActivity.java
c. Full name of all students in your group.
Udaykumar Bhupendra Kumar
Mrinali Lothey
 */


package com.example.inclass6_rt;

import java.util.ArrayList;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity {
	ArrayList<Movie> moviesList;
	String personsJSONUrl = "http://api.rottentomatoes.com/api/public/v1.0/lists/movies/in_theaters.json?page_limit=25&page=1&country=us&apikey=m7fpdqktbwjbwwakxt594brj";
	ListView lv;
	static String Movie_Title = "name";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lv = (ListView) findViewById(R.id.listView1);
		new AsyncMoviesGet(this).execute(personsJSONUrl);
	
	}

	public void gResult(final ArrayList<Movie> result) {
		this.moviesList = result;

		ArrayAdapter<Movie> adapter = new ArrayAdapter<Movie>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1,
				moviesList);
		lv.setAdapter(adapter);
		
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				Intent i = new Intent(getBaseContext(),DetailedActivity.class);
				i.putExtra(Movie_Title, moviesList.get(position));
				startActivity(i);
			}			
		});
	}

}
