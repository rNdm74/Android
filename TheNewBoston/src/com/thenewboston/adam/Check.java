package com.thenewboston.adam;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class Check extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		// Change view to new class
		setContentView(R.layout.check);

		// Variables
		Button submit = (Button) findViewById(R.id.bResults);
		final ToggleButton passTgl = (ToggleButton) findViewById(R.id.tbResults);
		final EditText username = (EditText) findViewById(R.id.etUsername);
		final EditText password = (EditText) findViewById(R.id.etPassword);
		final TextView results = (TextView) findViewById(R.id.tvResults);

		// Every time the submit button is clicked
		passTgl.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (passTgl.isChecked()) {
					password.setInputType(InputType.TYPE_CLASS_TEXT
							| InputType.TYPE_TEXT_VARIATION_PASSWORD);
				} else {
					password.setInputType(InputType.TYPE_CLASS_TEXT);
				}
			}
		});

		submit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String usernameCheck = username.getText().toString();
				String passwordCheck = password.getText().toString();
				String resultString = "";

				if (usernameCheck.equalsIgnoreCase("rndm")) {
					resultString += "Correct Username";
				} else {
					resultString += "Invalid Username";
				}

				if (passwordCheck.equalsIgnoreCase("rndm")) {
					resultString += " Correct Password";
				} else {
					resultString += " Invalid Password";
				}

				results.setText(resultString);
			}
		});
	}
	
	

}
