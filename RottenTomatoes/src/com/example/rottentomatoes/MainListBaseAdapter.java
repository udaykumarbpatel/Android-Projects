/*
a. Midterm
b. MainListBaseAdapter.java
c. Udaykumar Bhupendra Kumar
 */
package com.example.rottentomatoes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MainListBaseAdapter extends ArrayAdapter<String> {
	Context context;
	String[] menu_items;

	public MainListBaseAdapter(Context context, String[] menu_items) {
		super(context, R.layout.main_list_items, menu_items);
		this.context = context;
		this.menu_items = menu_items;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View itemRowView = inflater.inflate(R.layout.main_list_items, parent,
				false);
		TextView item = (TextView) itemRowView.findViewById(R.id.txtTitle);
		item.setText(menu_items[position]);
		return itemRowView;
	}
}
