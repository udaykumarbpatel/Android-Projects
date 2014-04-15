package com.example.inclass10;

import java.util.List;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

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
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity {

	IntentIntegrator integrator;
	private LocationManager locationMngr;
	GoogleMap gMap;
	int count = 0;
	double pre_lat, pre_long, next_lat, next_long, first_lan, first_long;
	PolylineOptions rectOptions;
	Polyline polyline;
	List<LatLng> points;
	boolean flag = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		locationMngr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		gMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();
		gMap.setMyLocationEnabled(true);
		gMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
	}

	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		IntentResult scanResult = IntentIntegrator.parseActivityResult(
				requestCode, resultCode, intent);
		if (scanResult != null) {

			String[] resultingTokens = scanResult.toString().split(",");
			for (int i = 0; i < resultingTokens.length; i++) {
				Log.d("demo", i + "  " + resultingTokens[i]);
			}

			String[] resultingToken = resultingTokens[0].toString().split(":");
			Log.d("demo", "End of 1");
			for (int i = 0; i < resultingToken.length; i++) {
				Log.d("demo", i + "  " + resultingToken[i]);
			}

			String[] resultingToken1 = resultingTokens[2].toString().split(";");
			Log.d("demo", "End of 1");
			for (int i = 0; i < resultingToken1.length; i++) {
				Log.d("demo", i + "  " + resultingToken1[i]);
			}
			pre_lat = Double.parseDouble(resultingTokens[1]);
			pre_long = Double.parseDouble(resultingTokens[2].substring(0, 17));

			if (count < 1) {
				count++;
				first_lan = pre_lat;
				first_long = pre_long;
				next_lat = Double.parseDouble(resultingTokens[3]);
				next_long = Double.parseDouble(resultingTokens[4].substring(0,
						17));
				gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
						new LatLng(pre_lat, pre_long), 15));
				gMap.addMarker(new MarkerOptions().position(
						new LatLng(pre_lat, pre_long)).title(resultingToken[2]));

				gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
						new LatLng(next_lat, next_long), 15));
				gMap.addMarker(new MarkerOptions().position(
						new LatLng(next_lat, next_long)).title(
						resultingToken1[1]));
			} else if (next_lat == pre_lat && next_long == pre_long
					&& pre_lat != first_lan && pre_long != first_long) {
				count++;
				next_lat = Double.parseDouble(resultingTokens[3]);
				next_long = Double.parseDouble(resultingTokens[4].substring(0,
						17));
				gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
						new LatLng(pre_lat, pre_long), 19));
				gMap.addMarker(new MarkerOptions().position(
						new LatLng(pre_lat, pre_long)).title(resultingToken[2]));

				gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
						new LatLng(next_lat, next_long), 19));
				gMap.addMarker(new MarkerOptions().position(
						new LatLng(next_lat, next_long)).title(
						resultingToken[1]));
			} else if (pre_lat == first_lan && pre_long == first_long) {
				Toast.makeText(MainActivity.this,
						"The game is successfully completed",
						Toast.LENGTH_SHORT).show();
				locationMngr.removeUpdates(simpleLocationListener);
				gMap.clear();
			} else {
				Toast.makeText(MainActivity.this,
						"This is not the correct Target Location",
						Toast.LENGTH_SHORT).show();
			}
		} else {
			Log.d("demo", "ERROR");
		}
	}

	protected void onResume() {
		super.onResume();
		if (!locationMngr.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Network not enabled")
					.setMessage("Would like to enable the Network settings")
					.setCancelable(true)
					.setPositiveButton("Yes",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									Intent i = new Intent(
											Settings.ACTION_LOCATION_SOURCE_SETTINGS);
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
			locationMngr.requestLocationUpdates(LocationManager.GPS_PROVIDER,
					0, 0, simpleLocationListener);
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
			if (count >= 1) {
				if (flag) {
					rectOptions = new PolylineOptions()
							.add(new LatLng(location.getLatitude(), location
									.getLongitude())).geodesic(true)
							.color(Color.BLUE);
					polyline = gMap.addPolyline(rectOptions);
					points = polyline.getPoints();
					flag = false;
				}
				Log.d("demo", points.toString());
				points.add(new LatLng(location.getLatitude(), location
						.getLongitude()));
				polyline.setPoints(points);
			}
		}
		
		
	};

	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.result_hunt) {
			Intent i = new Intent(getBaseContext(), MainActivity.class);
			i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(i);
		} else if (item.getItemId() == R.id.scan_barcode) {
			integrator = new IntentIntegrator(this);
			integrator.initiateScan();
		}
		return false;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
