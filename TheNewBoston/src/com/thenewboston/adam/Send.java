package com.thenewboston.adam;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Send extends Activity implements View.OnClickListener {

	Button startActivity;
	Button startActivityForResult;
	TextView gotMessage;
	EditText sendET;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.send);
		initialize();
	}

	private void initialize() {

		gotMessage = (TextView) findViewById(R.id.tvGOT);
		sendET = (EditText) findViewById(R.id.etSend);

		startActivity = (Button) findViewById(R.id.bSA);
		startActivity.setOnClickListener(this);

		startActivityForResult = (Button) findViewById(R.id.bSAFR);
		startActivityForResult.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bSA:
			startActivity();
			break;
		case R.id.bSAFR:
			Intent intent = new Intent(Send.this, Receive.class);
			startActivityForResult(intent, 0);
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		if(resultCode == RESULT_OK) {
			// Put data in new bundle
			Bundle bundle = data.getExtras();
			
			// loads string from bundle
			String receiveString = bundle.getString("receiveStringKey");
			
			// sets text for send
			gotMessage.setText(receiveString);
		}
	}

	private void startActivity() {
		// Pass string data to next activity that starts up
		String sendETString = sendET.getText().toString();

		// Setup bundle to pass to other class
		Bundle bundle = new Bundle();
		bundle.putString("sendStringKey", sendETString);

		// Starts new screen "Receive"
		Intent intent = new Intent(Send.this, Receive.class);
		intent.putExtras(bundle);
		startActivity(intent);
	}
}
