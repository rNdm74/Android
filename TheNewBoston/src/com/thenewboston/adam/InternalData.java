package com.thenewboston.adam;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class InternalData extends Activity implements OnClickListener{
	
	EditText sharedData;
	TextView dataResults;
	FileOutputStream fos;
	
	
	public static String FILENAME = "InternalString";
	
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
				String data = sharedData.getText().toString();

// 				Saving data via File
//******************************************************************
//				File f = new File(filename);
//				try {
//					fos = new FileOutputStream(f);
//				} catch (FileNotFoundException e) {
//					e.printStackTrace();
//				}
				
			try {
				
				fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
				fos.write(data.getBytes());
				fos.close();
				
			} catch (FileNotFoundException e) { e.printStackTrace();
			} catch (IOException e) {			e.printStackTrace(); }								
				break;
			case R.id.bLoad:				
				new LoadSomeStuff().execute(FILENAME);				
				break;
		}		
	}
	
	private void setupVariables() {
		Button save = (Button) findViewById(R.id.bSave);
		Button load = (Button) findViewById(R.id.bLoad);
		
		sharedData = (EditText) findViewById(R.id.eFilename);
		dataResults = (TextView) findViewById(R.id.tvLoadData);
		
		save.setOnClickListener(this);
		load.setOnClickListener(this);
		
		try {
			fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
			fos.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public class LoadSomeStuff extends AsyncTask<String, Integer, String>{ // what we pass in, progress bar stuff, what are we returning

		// Four methods you can use with the Async task
		
		ProgressDialog dialog;
		
		protected void onPreExecute(){
			// Example of setting up variables
			dialog = new ProgressDialog(InternalData.this);
			dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			dialog.setMax(100);
			dialog.show();
		}
						
		@Override
		protected String doInBackground(String... params) {
				FileInputStream fis = null;
				String collected = null;
	
				for (int i = 0; i < 20; i++) {
					publishProgress(5);
					try {
						Thread.sleep(88);
					} catch (InterruptedException e) { e.printStackTrace(); }
				}
				
				dialog.dismiss();
				
				try {					
					fis = openFileInput(FILENAME);
					
					// Creates array that is the size of the string
					byte[] dataArray = new byte[fis.available()];
					
					while(fis.read(dataArray) != -1) { // -1 means that everything has been read
						collected = new String(dataArray);						
					}
					
				} catch (FileNotFoundException e) { e.printStackTrace(); } catch (IOException e) { e.printStackTrace(); 
				} finally{
					try {
						fis.close();
						return collected;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}					
				}
				
			return null;
		}
		
		protected void onProgressUpdate(Integer... progress){
			dialog.incrementProgressBy(progress[0]);			
		}
		
		protected void onPostExecute(String result){
			dataResults.setText(result);
		}
	}
}


