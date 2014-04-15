/*
a. Midterm
b. MainActivity.java
c. Udaykumar Bhupendra Kumar
 */
package com.example.rottentomatoes;

import com.example.rottentomatoes.AsyncFavoritesGet.Result_Favorite;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends Activity implements Result_Favorite {

	String Item_Position = "item";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = cm.getActiveNetworkInfo();

		if (networkInfo != null && networkInfo.isConnected()) {
			Log.d("demo", "Internet Available");
		} else {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Restart the Application with Internet")
					.setTitle("Internet Not Found!");
			builder.setPositiveButton("Ok",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							finish();
						}
					}).show();

		}

		String[] list_view_items = { "My Favorite Movies", "Box Office Movies",
				"In Theaters Movies", "Opening Movies", "Upcoming Movies" };

		ListView main_list = (ListView) findViewById(R.id.main_list);
		main_list.setAdapter(new MainListBaseAdapter(this, list_view_items));
		main_list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent i = new Intent(getBaseContext(), MoviesActivity.class);
				i.putExtra(Item_Position, position);
				startActivity(i);
			}
		});
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.exit) {
			finish();
		} else if (item.getItemId() == R.id.clear_fav) {
			new AsyncFavoritesGet(this)
					.execute("http://cci-webdev.uncc.edu/~mshehab/api-rest/favorites/deleteAllFavorites.php");
		}
		return false;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void result_toast(Response result) {
		Toast.makeText(MainActivity.this, result.error_msg,
					Toast.LENGTH_SHORT).show();
	}

	@Override
	public void result_get_all_fav(Response result) {
		// Nothing to do here
	}
}
