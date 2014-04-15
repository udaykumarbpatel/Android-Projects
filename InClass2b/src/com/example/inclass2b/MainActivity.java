/*a. Assignment #.   In Class 2
b. File Name.      In Class 2b
c. Full name of all students in your group.
Udaykumar Bhupendra Kumar
Mrinali Lothey
*/

package com.example.inclass2b;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final LinearLayout l = (LinearLayout) findViewById(R.id.LinearLayout1);
		
		final TextView tv = (TextView) findViewById(R.id.textView1);
		
		final SeekBar sBar = (SeekBar) findViewById(R.id.seekBar1);

		final RadioGroup rGroup = (RadioGroup) findViewById(R.id.radioGroup1);

		final RadioButton r1 = (RadioButton) findViewById(R.id.radioButton1);
		final RadioButton r2 = (RadioButton) findViewById(R.id.radioButton2);
		final RadioButton r3 = (RadioButton) findViewById(R.id.radioButton3);
		final RadioButton r4 = (RadioButton) findViewById(R.id.radioButton4);
		final RadioButton r5 = (RadioButton) findViewById(R.id.radioButton5);

		l.setAlpha(0.9f);

		sBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			int BColor = 0;

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				int idx = rGroup.getCheckedRadioButtonId();

				switch (idx) {
				case R.id.radioButton1:
					BColor = Color.BLACK;
					break;
				case R.id.radioButton2:
					BColor = Color.WHITE;
					break;
				case R.id.radioButton3:
					BColor = Color.RED;
					break;
				case R.id.radioButton4:
					BColor = Color.GREEN;
					break;
				case R.id.radioButton5:
					BColor = Color.BLUE;
					break;
				}
				l.setBackgroundColor(BColor);
				l.setAlpha((float) progress / 100);
				rGroup.setAlpha(1);
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {

			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {

			}
		});

		
		r1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				l.setBackgroundColor(Color.BLACK);
				r1.setTextColor(Color.WHITE);
				r2.setTextColor(Color.WHITE);
				r3.setTextColor(Color.WHITE);
				r4.setTextColor(Color.WHITE);
				r5.setTextColor(Color.WHITE);
				tv.setTextColor(Color.WHITE);
			}

		});

		r2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				l.setBackgroundColor(Color.WHITE);
				r1.setTextColor(Color.BLACK);
				r2.setTextColor(Color.BLACK);
				r3.setTextColor(Color.BLACK);
				r4.setTextColor(Color.BLACK);
				r5.setTextColor(Color.BLACK);
				tv.setTextColor(Color.BLACK);
			}
		});
		r3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				l.setBackgroundColor(Color.RED);
				r1.setTextColor(Color.BLACK);
				r2.setTextColor(Color.BLACK);
				r3.setTextColor(Color.BLACK);
				r4.setTextColor(Color.BLACK);
				r5.setTextColor(Color.BLACK);
				tv.setTextColor(Color.BLACK);
			}
		});
		r4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				l.setBackgroundColor(Color.GREEN);
				r1.setTextColor(Color.BLACK);
				r2.setTextColor(Color.BLACK);
				r3.setTextColor(Color.BLACK);
				r4.setTextColor(Color.BLACK);
				r5.setTextColor(Color.BLACK);
				tv.setTextColor(Color.BLACK);
			}
		});
		r5.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				l.setBackgroundColor(Color.BLUE);
				r1.setTextColor(Color.BLACK);
				r2.setTextColor(Color.BLACK);
				r3.setTextColor(Color.BLACK);
				r4.setTextColor(Color.BLACK);
				r5.setTextColor(Color.BLACK);
				tv.setTextColor(Color.BLACK);
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