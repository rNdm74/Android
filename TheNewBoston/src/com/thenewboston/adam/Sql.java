package com.thenewboston.adam;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class Sql extends Activity implements OnClickListener, OnSeekBarChangeListener{
	
	EditText name;
	SeekBar rating;
	TextView ratingProgress;
	EditText age;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sql);
		
		initialize();
	}

	private void initialize() {
		name = (EditText) findViewById(R.id.etName);
		rating = (SeekBar) findViewById(R.id.sbRating);
		ratingProgress = (TextView) findViewById(R.id.tvRatingProgress);
		age = (EditText) findViewById(R.id.etAge);
		
		Button update = (Button) findViewById(R.id.bUpdateDatabase);
		Button view = (Button) findViewById(R.id.bViewDatabase);
		
		rating.setMax(10);	
		
		rating.setOnSeekBarChangeListener(this);		
		update.setOnClickListener(this);
		view.setOnClickListener(this);
	}


	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.bUpdateDatabase:
			updateDatabase();
			break;
		case R.id.bViewDatabase:
			viewDatabase();
			break;
		}		
	}


	private void viewDatabase() {
		Intent intent = new Intent("com.thenewboston.adam.SQLVIEW");
		startActivity(intent);		
	}


	private void updateDatabase() {
		boolean success = true;
		
		try
		{
			String sqlName = name.getText().toString();
			int sqlRating = rating.getProgress();
			int sqlAge = Integer.parseInt(age.getText().toString());
			
			Database entry = new Database(Sql.this);
			
			entry.open();
			entry.createEntry(sqlName, sqlRating, sqlAge);
			entry.close();
		}
		catch(Exception e){
			success = false;	
			
			// Get the error
			String error = e.toString();
			
			TextView textView = new TextView(this);
			Dialog dialog = new Dialog(this);
			
			dialog.setTitle("Error");	
			textView.setText(error);
			dialog.setContentView(textView);
			
			dialog.show();	
		}
		finally{			
			if(success) {	
				TextView textView = new TextView(this);
				Dialog dialog = new Dialog(this);
				dialog.setTitle("Success");	
				textView.setText("You have successfully updated the database");
				dialog.setContentView(textView);
				dialog.show();				
			}
		}
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		if(seekBar.getId() == R.id.sbRating){
			String value = Integer.toString(seekBar.getProgress());
			ratingProgress.setText(value);
		}					
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		//ratingProgress.setText(seekBar.getProgress());	
		
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		//ratingProgress.setText(seekBar.getProgress());			
	}
}
