package com.example.inclass9;

import java.util.ArrayList;

import com.example.inclass9.AsyncMapGet.ResultPassing;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;

public class PlacesActivity extends Activity implements ResultPassing {

	private LocationManager locationMngr;
	GoogleMap gMap;
	ArrayList<Place> data;
	int position;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_places);
		locationMngr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		position = getIntent().getExtras().getInt("item");
		Log.d("demo", position + "");

		gMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();
		gMap.setMyLocationEnabled(true);
		gMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.places, menu);
		return true;
	}

	protected void onResume() {
		super.onResume();
		if (!locationMngr.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Network not enabled")
					.setMessage("Would like to enable the Network settings")
					.setCancelable(true)
					.setPositiveButton("Yes",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									Intent i = new Intent(
											Settings.ACTION_WIFI_SETTINGS);
									startActivity(i);
								}
							})
					.setNegativeButton("No",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									dialog.cancel();
									finish();
								}
							});
			AlertDialog alert = builder.create();
			alert.show();
		} else {
			locationMngr.requestLocationUpdates(
					LocationManager.NETWORK_PROVIDER, 0, 0,
					simpleLocationListener);

		}
	}

	LocationListener simpleLocationListener = new LocationListener() {

		public void onStatusChanged(String provider, int status, Bundle extras) {
		}

		public void onProviderEnabled(String provider) {
		}

		public void onProviderDisabled(String provider) {
		}

		public void onLocationChanged(Location location) {
			gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(
					location.getLatitude(), location.getLongitude()), 12));

			switch (position) {
			case 0:
				new AsyncMapGet(PlacesActivity.this)
				.execute("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="
						+ location.getLatitude()
						+ ","
						+ location.getLongitude()
						+ "&radius=5000&types=pharmacy&sensor=false&key=AIzaSyB10frYe19hYihFWwRepuA-kwwOVCTjznE");
				break;
			case 1:
				new AsyncMapGet(PlacesActivity.this)
				.execute("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="
						+ location.getLatitude()
						+ ","
						+ location.getLongitude()
						+ "&radius=5000&types=bank&sensor=false&key=AIzaSyB10frYe19hYihFWwRepuA-kwwOVCTjznE");
				break;
			case 2:
				new AsyncMapGet(PlacesActivity.this)
				.execute("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="
						+ location.getLatitude()
						+ ","
						+ location.getLongitude()
						+ "&radius=5000&types=bar&sensor=false&key=AIzaSyB10frYe19hYihFWwRepuA-kwwOVCTjznE");
				break;
			case 3:
				new AsyncMapGet(PlacesActivity.this)
				.execute("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="
						+ location.getLatitude()
						+ ","
						+ location.getLongitude()
						+ "&radius=5000&types=gym&sensor=false&key=AIzaSyB10frYe19hYihFWwRepuA-kwwOVCTjznE");
				break;
			case 4:
				new AsyncMapGet(PlacesActivity.this)
				.execute("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="
						+ location.getLatitude()
						+ ","
						+ location.getLongitude()
						+ "&radius=5000&types=university&sensor=false&key=AIzaSyB10frYe19hYihFWwRepuA-kwwOVCTjznE");
				break;
			}
		}
	};

	@Override
	public void getResult(ArrayList<Place> result) {
		// TODO Auto-generated method stub
		this.data = result;
		Log.d("demo", result.toString());

		for (int i = 0; i < data.size(); i++) {
			Log.d("demo", "Added");
			gMap.addMarker(new MarkerOptions().position(
					new LatLng(data.get(i).lat, data.get(i).lng)).title(
					data.get(i).name));
		}
	}

}
