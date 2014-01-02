package com.thenewboston.adam;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class Receive extends Activity implements OnClickListener,
		OnCheckedChangeListener {

	RadioGroup answers;

	RadioButton aCrazy;
	RadioButton aSuper;
	RadioButton aCool;

	Button submit;
	TextView question;
	TextView text;
		
	String receivedString, sendData;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.receive);
		initialize();
		
		// Access preferences
		SharedPreferences getApplicationPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		
		String editText = getApplicationPreferences.getString("name", "I am....");
		String values = getApplicationPreferences.getString("list", "4");
		
		if(values.contentEquals("1")) {
			question.setText(editText);
		}
		
//		receiveBundle();
	}

	private void initialize() {
		answers = (RadioGroup) findViewById(R.id.rgAnswers);
		answers.setOnCheckedChangeListener(this);

		aCrazy = (RadioButton) findViewById(R.id.rCrazy);
		aSuper = (RadioButton) findViewById(R.id.rSuper);
		aCool = (RadioButton) findViewById(R.id.rCool);

		question = (TextView) findViewById(R.id.tvQuestion);
		text = (TextView) findViewById(R.id.tvText);

		submit = (Button) findViewById(R.id.bSubmit);
		submit.setOnClickListener(this);
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		switch (checkedId) {
		case R.id.rCrazy:
			sendData = "Correct";
			break;
		case R.id.rSuper:
			sendData = "Incorrect";
			break;
		case R.id.rCool:
			sendData = "Incorrect";
			break;
		}
		
		// Debug to make sure radio buttons are working
		text.setText(sendData);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bSubmit:
			submit();
			break;
		}
	}

	private void submit() {		
		// Setup bundle to pass to other class
		Bundle bundle = new Bundle();
		bundle.putString("receiveStringKey", sendData);
				
		// Starts new screen "Receive"
		Intent intent = new Intent();		
		intent.putExtras(bundle);
		
		// Send data back to "Send" class
		setResult(RESULT_OK, intent);
		
		// Close receive class
		finish();
	}
	
	private void receiveBundle() {
		Bundle bundle = getIntent().getExtras();
		
		// User key from the previous class
		receivedString = bundle.getString("sendStringKey");
		
		question.setText(receivedString);
	}
}
