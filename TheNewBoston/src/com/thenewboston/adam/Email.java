package com.thenewboston.adam;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Email extends Activity implements View.OnClickListener {

	EditText address, subject, message;
	String emailAddress, emailSubject, emailMessage;
	Button sendEmail;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.email);
		initializeVars();
		sendEmail.setOnClickListener(this);
	}

	private void initializeVars() {
		// TODO Auto-generated method stub
		address = (EditText) findViewById(R.id.etEmailAddress);
		subject = (EditText) findViewById(R.id.etSubject);
		message = (EditText) findViewById(R.id.etMessage);		
		sendEmail = (Button) findViewById(R.id.bSend);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		convertEditTextVarsIntoStrings();
		
		String emailaddress[] = { emailAddress };
		String emailsubject = emailSubject;
		String emailmessage = emailMessage;
		
		Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
		
		// To email address
		emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, emailaddress);
		
		// Email subject line
		emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, emailsubject);
		
		// Sets text type for email
		emailIntent.setType("plain/text");
		
		// Email body
		emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, emailmessage);
		
		// Send the email		
		startActivity(emailIntent);
	}

	private void convertEditTextVarsIntoStrings() {
		// TODO Auto-generated method stub
		emailAddress = address.getText().toString();
		emailSubject = subject.getText().toString();
		emailMessage = message.getText().toString();		
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
}