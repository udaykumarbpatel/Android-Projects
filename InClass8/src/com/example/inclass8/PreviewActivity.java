package com.example.inclass8;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

public class PreviewActivity extends Activity {
	private static DataManager dm;
	News items;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_preview);
		dm = new DataManager(this);
		
		items = (News) getIntent().getSerializableExtra("name");

		WebView webview = new WebView(this);
		setContentView(webview);
		webview.loadUrl(items.url_link);

	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId() == R.id.add_likes){
			dm.saveNote(items);
		}
		return true;
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.preview, menu);
		return true;
	}

}
