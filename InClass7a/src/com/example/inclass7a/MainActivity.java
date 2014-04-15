/*
a. Assignment #.   In Class Assignment 7
b. File Name.      MainActivity.java
c. Full name of all students in your group.
Udaykumar Bhupendra Kumar
Mrinali Lothey
*/

package com.example.inclass7a;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class MainActivity extends Activity {
	String personsUrl = "http://itunes.apple.com/us/rss/topgrossingapplications/limit=100/xml";
	ArrayList<EntryInfo> entryList;
	ListView l_view;
	String image = "image_url";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		l_view = (ListView) findViewById(R.id.listView1);
		new AsyncEntriesGet(this).execute(personsUrl);
	}

	public void gResult(final ArrayList<EntryInfo> result) {
		this.entryList = result;
		l_view.setAdapter(new ItemListBaseAdapter(this));
		l_view.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent i = new Intent(getBaseContext(), PreviewActivity.class);
				i.putExtra(image, entryList.get(position));
				startActivity(i);
			}
		});
	}

	public class ItemListBaseAdapter extends BaseAdapter {
		private LayoutInflater l_Inflater;

		public ItemListBaseAdapter(Context context) {
			l_Inflater = LayoutInflater.from(context);
		}

		public int getCount() {
			return entryList.size();
		}

		public Object getItem(int position) {
			return entryList.get(position);
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			TextView txt_itemName;
			TextView txt_itemDeveloperName;
			TextView txt_itemReleaseDate;
			TextView txt_itemPrice;
			TextView txt_itemCategory;
			ImageView itemImage;
			convertView = l_Inflater.inflate(R.layout.activity_list_view, null);
			txt_itemName = (TextView) convertView.findViewById(R.id.name);
			txt_itemDeveloperName = (TextView) convertView
					.findViewById(R.id.itemDescription);
			txt_itemReleaseDate = (TextView) convertView
					.findViewById(R.id.r_date);
			txt_itemPrice = (TextView) convertView.findViewById(R.id.price1);
			txt_itemCategory = (TextView) convertView
					.findViewById(R.id.category);
			itemImage = (ImageView) convertView.findViewById(R.id.photo);
			txt_itemName.setText(entryList.get(position).title);
			txt_itemDeveloperName.setText(entryList.get(position).a_name);
			txt_itemReleaseDate.setText(entryList.get(position).r_date);
			txt_itemPrice.setText(entryList.get(position).price);
			txt_itemCategory.setText(entryList.get(position).category);
			downloadFile(itemImage, entryList.get(position).url_image);
			return convertView;
		}
	}

	Bitmap bmImg;

	void downloadFile(final ImageView imageView, String fileUrl) {
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

			protected void onPostExecute(String unused) {
				imageView.setImageBitmap(bmImg);
			}
		};
		task.execute(fileUrl);
	}
}