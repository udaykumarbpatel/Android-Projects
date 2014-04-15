/*a. Assignment #.   In Class 4
b. File Name.      In Class4b
c. Full name of all students in your group.
Udaykumar Bhupendra Kumar
Mrinali Lothey
*/
package com.example.inclass4b;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.ProgressDialog;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	private Handler h;
	TextView tx;
	ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		h = new Handler();
		
		Button b = (Button) findViewById(R.id.button1);
		b.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				h.post(new Runnable() {
					
					@Override
					public void run() {
						Double result = HeavyWork.getNumber();
						tx.setText(result.toString());
					}
				}); 
			}
		});
	}


	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
