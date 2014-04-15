/*
a. Midterm
b. MoviesActivity.java
c. Udaykumar Bhupendra Kumar
 */

package com.example.rottentomatoes;

import java.util.ArrayList;

import com.example.rottentomatoes.AsyncFavoritesGet.Result_Favorite;
import com.example.rottentomatoes.AsyncFavoritesMovieIdGet.Result_MovieId;
import com.example.rottentomatoes.AsyncMovieGenreGet.GenrePassing;
import com.example.rottentomatoes.AsyncMoviesGet.ResultPassing;
import com.example.rottentomatoes.AsyncSingleMovieGet.ResultPassing_Singlemovie;

import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

public class MoviesActivity extends Activity implements ResultPassing,
		Result_Favorite, Result_MovieId, GenrePassing,
		ResultPassing_Singlemovie {
	String Item_Position = "item", item_id_delete, RESULT_KEY = "result",
			Menu_Item_Poistion = "menu_position";
	ArrayList<Movie> final_result, movies;
	ListView list_view;
	ItemListBaseAdapter movies_list_adapter;
	ProgressDialog progressDialog;
	String url_movies = "http://api.rottentomatoes.com/api/public/v1.0/lists/movies/";
	String url_is_fav = "http://cci-webdev.uncc.edu/~mshehab/api-rest/favorites/isFavorite.php";
	int number_of_fav_movies, counter = 0, item_position, delete_position,
			REQ_CODE = 1001;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_movies);

		movies = new ArrayList<Movie>();
		list_view = (ListView) findViewById(R.id.listView1);
		item_position = getIntent().getExtras().getInt(Item_Position);

		progressstart("Loading Movies...");
		switch (item_position) {
		case 0:
			new AsyncFavoritesGet(this)
					.execute("http://cci-webdev.uncc.edu/~mshehab/api-rest/favorites/getAllFavorites.php");
			break;
		case 1:
			new AsyncMoviesGet(this)
					.execute(url_movies
							+ "box_office.json?apikey=m7fpdqktbwjbwwakxt594brj&limit=50");
			break;
		case 2:
			new AsyncMoviesGet(this)
					.execute(url_movies
							+ "in_theaters.json?apikey=m7fpdqktbwjbwwakxt594brj&page_limit=50");
			break;
		case 3:
			new AsyncMoviesGet(this).execute(url_movies
					+ "opening.json?apikey=m7fpdqktbwjbwwakxt594brj&limit=50");
			break;
		case 4:
			new AsyncMoviesGet(this)
					.execute(url_movies
							+ "upcoming.json?apikey=m7fpdqktbwjbwwakxt594brj&page_limit=50");
			break;

		default:
			break;
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == REQ_CODE) {
			if (resultCode == RESULT_OK) {
				final_result.remove(delete_position);
				getResult(final_result);
				Toast.makeText(MoviesActivity.this,
						"Updated My Favorite Movies List", Toast.LENGTH_LONG)
						.show();

			}
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.exit) {
			finish();
		} else if (item.getItemId() == R.id.clear_fav) {
			new AsyncFavoritesGet(this)
					.execute("http://cci-webdev.uncc.edu/~mshehab/api-rest/favorites/deleteFavorite.php");
		}
		return false;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.movies, menu);
		return true;
	}

	@Override
	public void getResult(ArrayList<Movie> result) {
		progressdismiss();
		this.final_result = result;
		movies_list_adapter = new ItemListBaseAdapter(this, result);
		list_view.setAdapter(movies_list_adapter);

		list_view.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				delete_position = position;
				Intent i = new Intent(getBaseContext(), MovieActivity.class);
				i.putExtra(Item_Position, final_result.get(position));
				i.putExtra(Menu_Item_Poistion, item_position);
				startActivityForResult(i, REQ_CODE);
			}
		});

		list_view.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (item_position == 0) {
					delete_position = position;
					item_id_delete = final_result.get(position).id;
					String url_del_favorite = "http://cci-webdev.uncc.edu/~mshehab/api-rest/favorites/deleteFavorite.php";
					new AsyncFavoritesMovieIdGet(MoviesActivity.this,
							item_id_delete).execute(url_del_favorite);
					progressstart("Updating Favorites...");
				}
				return false;
			}
		});
	}

	@Override
	public void result_toast(Response result) {
		progressdismiss();
		if (Integer.parseInt(result.error_id) < 0) {
			Toast.makeText(MoviesActivity.this, result.error_msg,
					Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void resultIsFavorite(Response result) {
		if (Integer.parseInt(result.error_id) < 0) {
			Toast.makeText(MoviesActivity.this, result.error_msg,
					Toast.LENGTH_SHORT).show();
		} else {
			final_result.remove(delete_position);
			getResult(final_result);
		}
	}

	public void progressstart(String str) {
		progressDialog = ProgressDialog.show(this, null, str);
		progressDialog.setCancelable(false);
	}

	public void progressdismiss() {
		progressDialog.dismiss();
	}

	@Override
	public void result_get_all_fav(Response result) {
		if (Integer.parseInt(result.error_id) < 0) {
			Toast.makeText(MoviesActivity.this, result.error_msg,
					Toast.LENGTH_SHORT).show();
		} else {
			String[] resultingTokens = result.movie_ids.split(",");
			number_of_fav_movies = resultingTokens.length;
			for (int i = 0; i < resultingTokens.length; i++) {
				new AsyncSingleMovieGet(this)
						.execute("http://api.rottentomatoes.com/api/public/v1.0/movies/"
								+ resultingTokens[i]
								+ ".json?apikey=m7fpdqktbwjbwwakxt594brj");
			}
		}
	}

	@Override
	public void getResult(Movie result) {
		if (counter < number_of_fav_movies-1) {
			counter++;
			movies.add(result);
		} else {
			movies.add(result);
			getResult(movies);
			
		}
	}

	@Override
	public void getResult_Genre(String result) {
		// Nothing to do here
	}
}
