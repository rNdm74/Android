package com.thenewboston.adam;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SharedPrefs extends Activity implements OnClickListener{

	EditText sharedData;
	TextView dataResults;
	SharedPreferences data;
	
	public static String filename = "MySharedString";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sharedpreferences);
		setupVariables();		
	}

	@Override
	public void onClick(View v) {
		switch(v.getId())
		{
			case R.id.bSave:				
				String stringData = sharedData.getText().toString();
				
				if(stringData == "")
					stringData = "NO DATA SAVED";
				
				SharedPreferences.Editor editor = data.edit(); // Allow to edit variable
				editor.putString("sharedString", stringData);  // Key and value for saving
				editor.commit();
				break;
			case R.id.bLoad:
				data = getSharedPreferences(filename, 0);
				String dataReturned = data.getString("sharedString", "data not found");
				dataResults.setText(dataReturned);
				break;
		}		
	}
	
	private void setupVariables(){
		Button save = (Button) findViewById(R.id.bSave);
		Button load = (Button) findViewById(R.id.bLoad);
		
		sharedData = (EditText) findViewById(R.id.eFilename);
		dataResults = (TextView) findViewById(R.id.tvLoadData);
		
		save.setOnClickListener(this);
		load.setOnClickListener(this);
		
		data = getSharedPreferences(filename, 0); // 0 means private
	}
}
