package com.thenewboston.adam;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class HTTPExample extends Activity{

	TextView http;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.httpexample);
		
		http = (TextView) findViewById(R.id.tvHttp);
		
		GetHTTPData httpData = new GetHTTPData();
		
		try {
			String returnedData = httpData.getData();
			http.setText(returnedData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}	
}
