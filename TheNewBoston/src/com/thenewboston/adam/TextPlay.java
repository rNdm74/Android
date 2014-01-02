package com.thenewboston.adam;

import java.util.Random;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter.ViewBinder;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class TextPlay extends Activity implements View.OnClickListener {

	Button tryCommand;
	ToggleButton showText;
	EditText command;
	TextView results;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		// Change view to new class
		setContentView(R.layout.text);

		initVariables();

		// Every time the submit button is clicked
		showText.setOnClickListener(this);

		tryCommand.setOnClickListener(this);
	}

	private void initVariables() {
		// Variables
		tryCommand = (Button) findViewById(R.id.bCommand);
		showText = (ToggleButton) findViewById(R.id.tbShowText);
		command = (EditText) findViewById(R.id.etCommand);
		results = (TextView) findViewById(R.id.tvCommandCorrect);
	}

	private void togglePressed() {
		if (showText.isChecked()) {
			command.setInputType(InputType.TYPE_CLASS_TEXT
					| InputType.TYPE_TEXT_VARIATION_PASSWORD);
		} else {
			command.setInputType(InputType.TYPE_CLASS_TEXT);
		}
	}

	private void tryCommandPressed() {
		String commandCheck = command.getText().toString();
		results.setText(commandCheck);

		if (commandCheck.equalsIgnoreCase("left")) {
			results.setGravity(Gravity.LEFT);
		} else if (commandCheck.equalsIgnoreCase("center")) {
			results.setGravity(Gravity.CENTER);
		} else if (commandCheck.equalsIgnoreCase("right")) {
			results.setGravity(Gravity.RIGHT);
		} else if (commandCheck.equalsIgnoreCase("blue")) {
			results.setTextColor(Color.BLUE);
		} else if (commandCheck.equalsIgnoreCase("WTF")) {
			Random rGen = new Random();

			int textSize = rGen.nextInt(75);

			int r = rGen.nextInt(255);
			int g = rGen.nextInt(255);
			int b = rGen.nextInt(255);

			results.setTextSize(textSize);
			results.setTextColor(Color.rgb(r, g, b));
			results.setText("WTF!!");

			switch (rGen.nextInt(3)) {
			case 0:
				results.setGravity(Gravity.LEFT);
				break;
			case 1:
				results.setGravity(Gravity.CENTER);
				break;
			case 2:
				results.setGravity(Gravity.RIGHT);
				break;
			}
		} else {
			results.setTextColor(Color.DKGRAY);
			results.setText("Invalid");
			results.setGravity(Gravity.CENTER);
		}

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bCommand:
			tryCommandPressed();
			break;
		case R.id.tbShowText:
			togglePressed();
			break;
		}
	}
}
