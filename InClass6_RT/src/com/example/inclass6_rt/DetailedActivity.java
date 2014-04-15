/*a. Assignment #.   In Class 6
b. File Name.      DetailedActivity.java
c. Full name of all students in your group.
Udaykumar Bhupendra Kumar
Mrinali Lothey
 */

package com.example.inclass6_rt;



import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailedActivity extends Activity {
	String Movie_Title = "name";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detailed);
		TextView mTitle = (TextView) findViewById(R.id.textView1);
		TextView mRuntime = (TextView) findViewById(R.id.textView2);
		TextView mSysnopsis = (TextView) findViewById(R.id.textView3);
		Button back = (Button) findViewById(R.id.button1);
		Movie mObject = (Movie) getIntent().getSerializableExtra(Movie_Title);
		
		
		mTitle.setText(mObject.title);
		mRuntime.setText(mObject.runtime + "");
		mSysnopsis.setText(mObject.desc);
		
		back.setOnClickListener(new View.OnClickListener() {
			
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
		getMenuInflater().inflate(R.menu.detailed, menu);
		return true;
	}

}
