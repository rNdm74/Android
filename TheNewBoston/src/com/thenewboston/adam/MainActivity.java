package com.thenewboston.adam;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	// Variables
	int counter;
	Button add, sub;
	TextView display;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		counter = 0;

		add = (Button) findViewById(R.id.bAdd);
		sub = (Button) findViewById(R.id.bSub);
		display = (TextView) findViewById(R.id.tvDisplay);

		add.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// Add one to counter
				counter++;
				
				// Change text of Textview concat counter
				display.setText("Your total is " + counter);
			}
		});

		sub.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// Subtract one from counter
				counter--;
				
				// Change text of Textview concat counter
				display.setText("Your total is " + counter);
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
