/*a. Assignment #.   In Class 5
b. File Name.      In Class5a
c. Full name of all students in your group.
Udaykumar Bhupendra Kumar
Mrinali Lothey
 */

package com.example.inclass5a;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	HttpClient client = new DefaultHttpClient();
	String state;
	Handler handler;
	TextView t1, t2;
	ExecutorService taskPool;
	AutoCompleteTextView textView;
	private static final String[] COUNTRIES = new String[] { "AL", "AK", "AZ",
			"AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN",
			"IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO",
			"MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK",
			"OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA",
			"WV", "WI", "WY" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button b = (Button) findViewById(R.id.button1);

		t1 = (TextView) findViewById(R.id.population);
		t2 = (TextView) findViewById(R.id.state);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, COUNTRIES);
		textView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
		textView.setAdapter(adapter);

		taskPool = Executors.newFixedThreadPool(2);

		handler = new Handler(new Handler.Callback() {
			public boolean handleMessage(Message msg) {
				if (msg.getData().containsKey("ERROR")) {
					Toast.makeText(getBaseContext(),
							msg.getData().getString("ERROR"), Toast.LENGTH_LONG)
							.show();
				} else {
					String[] resultingTokens = msg.getData()
							.getString("RESULT").split(";");

					t1.setText(resultingTokens[1]);
					t2.setText(resultingTokens[0]);
				}
				return true;
			}
		});

		b.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String selectedState = textView.getText().toString();
				if (selectedState == null || selectedState.isEmpty()) {
					Toast.makeText(getBaseContext(), "Enter the state name",
							Toast.LENGTH_LONG).show();
				} else
					taskPool.execute(new GetData(selectedState));
			}
		});
	}

	private class GetData implements Runnable {
		String state;
		Bundle bundle = new Bundle();
		Message msg = new Message();

		public GetData(String state) {
			this.state = state;
		}

		@Override
		public void run() {

			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("method", "getStatePopulation"));
			params.add(new BasicNameValuePair("state", state));

			BufferedReader in = null;
			try {
				URI uri = URIUtils.createURI("http", "cci-webdev.uncc.edu", -1,
						"/~mshehab/api-rest/states/index.php",
						URLEncodedUtils.format(params, "UTF-8"), null);
				// URIUtils.createURI(scheme, host, port, path, query, fragment)
				HttpGet request = new HttpGet(uri);
				HttpResponse response = client.execute(request);
				if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					in = new BufferedReader(new InputStreamReader(response
							.getEntity().getContent()));
					StringBuffer sb = new StringBuffer("");
					String line = "";
					while ((line = in.readLine()) != null) {
						sb.append(line + "\n");
					}
					if (sb.toString().contains("not")) {
						bundle.putString("ERROR", sb.toString());
					} else {
						bundle.putString("RESULT", sb.toString());
					}
					Log.d("demo", sb.toString());
					in.close();
				} else {
					bundle.putString("ERROR", "Problem with Response");
				}
			} catch (Exception e) {
				Log.d("demo", e.toString());
			}
			msg.setData(bundle);
			handler.sendMessage(msg);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
