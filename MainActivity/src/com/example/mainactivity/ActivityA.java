/*a. Assignment #.   In Class 3
b. File Name.      ActivityA
c. Full name of all students in your group.
Udaykumar Bhupendra Kumar
Mrinali Lothey
*/

package com.example.mainactivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActivityA extends Activity {
	final static String NAME_KEY = "name";
	TextView tx;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity);
		
		tx = (TextView) findViewById(R.id.textView1);
		tx.setText(getIntent().getExtras().getString(MainActivity.NAME_KEY));
		
		Button b1 = (Button) findViewById(R.id.button1);
		
		Button b2 = (Button) findViewById(R.id.button2);
		
		b1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getBaseContext(),ActivityB.class);
				String value = tx.getText().toString();
				Integer it = Integer.parseInt(value);
				it = it*2;
				i.putExtra(NAME_KEY, it.toString());
				Log.d("demo", "Inside");
				startActivity(i);
			}
		});
		
		b2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity, menu);
		return true;
	}

}
