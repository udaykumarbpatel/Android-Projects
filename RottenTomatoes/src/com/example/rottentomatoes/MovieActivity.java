/*
a. Midterm
b. MovieActivity.java
c. Udaykumar Bhupendra Kumar
 */

package com.example.rottentomatoes;

import com.example.rottentomatoes.AsyncFavoritesGet.Result_Favorite;
import com.example.rottentomatoes.AsyncFavoritesMovieIdGet.Result_MovieId;
import com.example.rottentomatoes.AsyncMovieGenreGet.GenrePassing;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MovieActivity extends Activity implements Result_MovieId,
		GenrePassing, Result_Favorite {
	String Item_Position = "item";
	String Menu_Item_Poistion = "menu_position";
	String RESULT_KEY = "result";
	int REQ_CODE = 1001;
	ProgressDialog progressDialog;
	Movie item;
	ImageButton my_favorite;
	TextView movie_genre;
	Response result;
	int menu_position;
	String url_is_fav = "http://cci-webdev.uncc.edu/~mshehab/api-rest/favorites/isFavorite.php";
	boolean fav_activate = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_movie);

		item = (Movie) getIntent().getSerializableExtra(Item_Position);
		menu_position = getIntent().getExtras().getInt(Menu_Item_Poistion);

		TextView m_title = (TextView) findViewById(R.id.title1);
		TextView r_date = (TextView) findViewById(R.id.release_date1);
		TextView mpaa = (TextView) findViewById(R.id.rating_mpaa1);
		TextView runtime = (TextView) findViewById(R.id.duration1);
		TextView a_rating = (TextView) findViewById(R.id.a_score1);
		TextView c_rating = (TextView) findViewById(R.id.c_score1);
		movie_genre = (TextView) findViewById(R.id.genre1);

		new AsyncMovieGenreGet(this)
				.execute("http://api.rottentomatoes.com/api/public/v1.0/movies/"
						+ item.id + ".json?apikey=m7fpdqktbwjbwwakxt594brj");
		progressstart("Loading Genre...");
		ImageView img_det = (ImageView) findViewById(R.id.detailed_image1);
		ImageView img_a = (ImageView) findViewById(R.id.a_rating_image1);
		ImageView img_c = (ImageView) findViewById(R.id.c_rating_image1);

		if (item.critics_rating.equals("Rotten")) {
			img_c.setImageResource(R.drawable.rotten);
		} else if (item.critics_rating.equals("Certified Fresh")) {
			img_c.setImageResource(R.drawable.certified_fresh);
		} else if (item.critics_rating.equals("Fresh")) {
			img_c.setImageResource(R.drawable.fresh);
		} else {
			img_c.setImageResource(R.drawable.notranked);
		}

		if (item.audience_rating.equals("Spilled")) {
			img_a.setImageResource(R.drawable.spilled);
		} else if (item.audience_rating.equals("Upright")) {
			img_a.setImageResource(R.drawable.upright);
		} else {
			img_a.setImageResource(R.drawable.notranked);
		}

		m_title.setText(item.title);

		String[] resultingTokens = item.release_date.split("-");
		r_date.setText(resultingTokens[1] + "/" + resultingTokens[2] + "/"
				+ resultingTokens[0]);
		mpaa.setText(item.mpaa_rating);

		if (item.runtime.equals("")) {
			runtime.setText("Not Available");
		} else {
			int hours = Integer.parseInt(item.runtime) / 60;
			int minutes = Integer.parseInt(item.runtime) % 60;
			runtime.setText(+hours + " hr " + minutes + "min");
		}

		a_rating.setText(item.audience_score + "%");
		c_rating.setText(item.critics_score + "%");
		img_det.setTag(item.url_poster_detailed.trim());

		my_favorite = (ImageButton) findViewById(R.id.favorite_button);
		ImageButton back_button = (ImageButton) findViewById(R.id.back_button);
		ImageButton internet_button = (ImageButton) findViewById(R.id.internet_button);

		new AsyncFavoritesMovieIdGet(MovieActivity.this, item.id)
				.execute(url_is_fav);

		new AsyncImageDownload().execute(img_det, item.url_poster_detailed);

		my_favorite.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				fromFavoritebutton(result);
				progressstart("Updating favorites...");
				if (!fav_activate) {
					fav_activate = true;
				} else {
					fav_activate = false;
				}
			}
		});

		back_button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});

		internet_button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse(item.url_alternate));
				startActivity(i);
			}
		});
	}

	@Override
	public void onBackPressed() {
		if (fav_activate && menu_position == 0) {
			Intent i = new Intent();
			i.putExtra(RESULT_KEY, "Hello World !!");
			setResult(RESULT_OK, i);
		}
		// if (fav_activate && menu_position == 0) {
		// Intent i = new Intent(getBaseContext(), MoviesActivity.class);
		// i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		// i.putExtra("item", 0);
		// startActivity(i);
		// }
		super.onBackPressed();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.exit) {
			onBackPressed();
		} else if (item.getItemId() == R.id.clear_fav) {
			new AsyncFavoritesGet(this)
					.execute("http://cci-webdev.uncc.edu/~mshehab/api-rest/favorites/deleteFavorite.php");
		}
		return false;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.movie, menu);
		return true;
	}

	@Override
	public void resultIsFavorite(Response result) {
		this.result = result;
		progressdismiss();
		if (Integer.parseInt(result.error_id) < 0) {
			Toast.makeText(MovieActivity.this, result.error_msg,
					Toast.LENGTH_SHORT).show();
		} else {
			if (result.isFavorite.equals("true")) {
				my_favorite.setImageResource(R.drawable.star);
			} else {
				my_favorite.setImageResource(R.drawable.unstar);
			}
		}
	}

	public void fromFavoritebutton(Response result1) {
		if (result1.isFavorite.equals("true")) {
			String url_del_favorite = "http://cci-webdev.uncc.edu/~mshehab/api-rest/favorites/deleteFavorite.php";
			new AsyncFavoritesMovieIdGet(MovieActivity.this, item.id)
					.execute(url_del_favorite);
			result1.isFavorite = "false";
		} else {
			String url_add_favorite = "http://cci-webdev.uncc.edu/~mshehab/api-rest/favorites/addToFavorites.php";
			new AsyncFavoritesMovieIdGet(MovieActivity.this, item.id)
					.execute(url_add_favorite);
			result1.isFavorite = "true";
		}
	}

	@Override
	public void result_toast(Response result) {
		if (Integer.parseInt(result.error_id) < 0) {
			Toast.makeText(MovieActivity.this, result.error_msg,
					Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void getResult_Genre(String result) {
		movie_genre.setText(result.substring(0, result.length() - 1));
		progressdismiss();
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
		// Do nothing
	}
}
