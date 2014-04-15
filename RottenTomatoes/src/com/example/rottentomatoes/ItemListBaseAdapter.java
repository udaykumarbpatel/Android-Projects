/*
a. Midterm
b. ImageListBaseAdapter.java
c. Udaykumar Bhupendra Kumar
 */

package com.example.rottentomatoes;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemListBaseAdapter extends BaseAdapter {

	Context context;
	ArrayList<Movie> movieList;

	public ItemListBaseAdapter(Context context, ArrayList<Movie> result) {
		this.movieList = result;
		this.context = context;
	}

	public int getCount() {
		return movieList.size();
	}

	public Object getItem(int position) {
		return movieList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		TextView movie_name;
		TextView movie_year;
		TextView movie_mpaa_rating;
		ImageView movie_thumb;
		ImageView critic_image;
		ImageView audience_image;

		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.activity_list_view, parent,
					false);
		}

		movie_thumb = (ImageView) convertView.findViewById(R.id.movie_thumb);
		movie_thumb.setImageResource(R.drawable.no_preview);
		movie_thumb.setTag(movieList.get(position).url_poster_thumb.trim());
		new AsyncImageDownload().execute(movie_thumb,
				movieList.get(position).url_poster_thumb);
		
		movie_name = (TextView) convertView.findViewById(R.id.movie_title);
		movie_year = (TextView) convertView.findViewById(R.id.year);
		movie_mpaa_rating = (TextView) convertView
				.findViewById(R.id.mpaa_rating);
		critic_image = (ImageView) convertView.findViewById(R.id.critic_rating);
		audience_image = (ImageView) convertView
				.findViewById(R.id.audience_rating);

		movie_name.setText(movieList.get(position).title);
		movie_year.setText(movieList.get(position).year);
		movie_mpaa_rating.setText(movieList.get(position).mpaa_rating);	
		
		if (movieList.get(position).critics_rating.equals("Rotten")) {
			critic_image.setImageResource(R.drawable.rotten);
		} else if (movieList.get(position).critics_rating
				.equals("Certified Fresh")) {
			critic_image.setImageResource(R.drawable.certified_fresh);
		} else if (movieList.get(position).critics_rating.equals("Fresh")) {
			critic_image.setImageResource(R.drawable.fresh);
		}

		if (movieList.get(position).audience_rating.equals("Spilled")) {
			audience_image.setImageResource(R.drawable.spilled);
		} else if (movieList.get(position).audience_rating.equals("Upright")) {
			audience_image.setImageResource(R.drawable.upright);
		}
		return convertView;
	}
}
