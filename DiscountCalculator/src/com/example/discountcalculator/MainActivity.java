/*
a. Assignment #.   Home Work Assignment 2
b. File Name.      Discount Calculator
c. Full name of all students in your group.
Udaykumar Bhupendra Kumar
Mrinali Lothey
*/

package com.example.discountcalculator;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MainActivity extends Activity {

	SeekBar sBar;
	TextView customProgressValue, youPay, youSave;
	Button exitButton;
	EditText eText;
	RadioButton r1, r2, r3, r0;
	RadioGroup rGroup;
	int prog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		sBar = (SeekBar) findViewById(R.id.seekBar1);
		customProgressValue = (TextView) findViewById(R.id.textView4);
		exitButton = (Button) findViewById(R.id.button1);
		eText = (EditText) findViewById(R.id.editText1);
		r0 = (RadioButton) findViewById(R.id.radio0);
		r1 = (RadioButton) findViewById(R.id.radio1);
		r2 = (RadioButton) findViewById(R.id.radio2);
	 	r3 = (RadioButton) findViewById(R.id.radio3);
		rGroup = (RadioGroup) findViewById(R.id.radioGroup1);
		youSave = (TextView) findViewById(R.id.textView6);
		youPay = (TextView) findViewById(R.id.textView8);

		sBar.setProgress(25);
		prog = 25;
		customProgressValue.setText("25%");
		
		eText.setOnKeyListener(new OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				Calculate();
				return false;
			}
		});

		rGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				Calculate();
			}
		});

		sBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				String pro = progress + "%";
				prog = progress;
				customProgressValue.setText(pro);
				if (r3.isChecked()) {
					Calculate();
				}
			}
		});

		exitButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void Calculate() {
		String readString;
		Float totalamount, youP, youS;
		int checkedId = rGroup.getCheckedRadioButtonId();
		switch (checkedId) {
		case R.id.radio0:
			if (eText.getText().length() < 1)
				eText.setError("Enter List Price");
			else {
				readString = eText.getText().toString();
				totalamount = Float.parseFloat(readString);
				youS = (float) (totalamount * 0.1);
				youP = totalamount - youS;
				youSave.setText(" $ " + youS.toString());
				youPay.setText(" $ " + youP.toString());
			}
			break;
		case R.id.radio1:
			if (eText.getText().length() < 1)
				eText.setError("Enter List Price");
			else {
				readString = eText.getText().toString();
				totalamount = Float.parseFloat(readString);
				youS = (float) (totalamount * 0.25);
				youP = totalamount - youS;
				youSave.setText(" $ " + youS.toString());
				youPay.setText(" $ " + youP.toString());
			}
			break;
		case R.id.radio2:
			if (eText.getText().length() < 1)
				eText.setError("Enter List Price");
			else {
				readString = eText.getText().toString();
				totalamount = Float.parseFloat(readString);
				youS = (float) (totalamount * 0.5);
				youP = totalamount - youS;
				youSave.setText(" $ " + youS.toString());
				youPay.setText(" $ " + youP.toString());
			}
			break;
		case R.id.radio3:
			if (eText.getText().length() < 1)
				eText.setError("Enter List Price");
			else {
				readString = eText.getText().toString();
				totalamount = Float.parseFloat(readString);
				float per = (float) prog / 100;
				Log.d("Demo", Float.toString(per));
				youS = (float) (totalamount * per);
				youP = totalamount - youS;
				youSave.setText(" $ " + youS.toString());
				youPay.setText(" $ " + youP.toString());
			}
			break;
		}
	}
}
