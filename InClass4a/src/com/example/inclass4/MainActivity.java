/*a. Assignment #.   In Class 4
b. File Name.      In Class4a
c. Full name of all students in your group.
Udaykumar Bhupendra Kumar
Mrinali Lothey
*/
package com.example.inclass4;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView tx;
	Button but;
	ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tx = (TextView) findViewById(R.id.textView1);
		but = (Button) findViewById(R.id.button1);

		but.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				new SimulateTask().execute();

			}
		});
	}

	private class SimulateTask extends AsyncTask<Void, Integer, Double> {

		@Override
		protected Double doInBackground(Void... params) {
			// TODO Auto-generated method stub

			Double result = HeavyWork.getNumber();
			return result;
		}

		protected void onPreExecute() {
			progressDialog = ProgressDialog.show(MainActivity.this,
					"Doing Work", "Retriving the number");
		}

		protected void onProgressUpdate(Integer... progress) {

		}

		protected void onPostExecute(Double result) {
			tx.setText(result.toString());
			progressDialog.dismiss();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
