package com.example.inclass8;

import java.util.ArrayList;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class NewsActivity extends Activity {
	private static DataManager dm;
	String xmlUrl = "http://rss.cnn.com/rss/cnn_tech.rss";
	ArrayList<News> data;
	ListView listView;
	String NAME_KEY = "name";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news);
		dm = new DataManager(this);
		
		String button_string = getIntent().getExtras().getString("name");
		listView = (ListView) findViewById(R.id.listView1);
		if (button_string.equals("news")) {
			new AsyncNewsGet(this).execute(xmlUrl);
		} else if(button_string.equals("like")){
			ArrayList<News> notes = dm.getAllNotes();
			Log.d("notes", notes.toString());
			Log.d("notes", notes.size() + "");
			gResult(notes);
		}
	}

	void gResult(ArrayList<News> result) {
		this.data = result;

		ArrayAdapter<News> adapter = new ArrayAdapter<News>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1, data);
		listView.setAdapter(adapter);

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent i = new Intent(getBaseContext(), PreviewActivity.class);
				i.putExtra(NAME_KEY, data.get(position));
				startActivity(i);
			}
		});

	}
	
	

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId() == R.id.clear_likes){
			dm.deleteNote();
		}
		return true;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.news, menu);
		return true;
	}

}
