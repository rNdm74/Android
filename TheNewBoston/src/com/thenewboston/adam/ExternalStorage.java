package com.thenewboston.adam;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ExternalStorage extends Activity implements OnClickListener, OnItemSelectedListener{

	private TextView canWrite, canRead;
	private boolean canR, canW;
	private String state;
	
	private Spinner spinner;
	private String[] paths = {"Music","Pictures","Downloads"};
	private File path = null;
	private File file = null;
	private EditText saveFile;
	private Button confirm, save;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.externaldata);
		
		initialize();
		
		
	}

	private void writeData() {
		try {
			InputStream is = getResources().openRawResource(R.drawable.background1);
			OutputStream os = new FileOutputStream(file);
			
			byte[] data = new byte[is.available()];
			
			is.read(data);			
			os.write(data);
			
			is.close();
			os.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	private void initialize() {
		canWrite = (TextView) findViewById(R.id.tvCanWrite);
		canRead = (TextView) findViewById(R.id.tvCanRead);
		
		saveFile = (EditText) findViewById(R.id.etSaveAs);
		confirm = (Button) findViewById(R.id.bConfirm);		
		save = (Button) findViewById(R.id.bSaveAs);
		
		confirm.setOnClickListener(this);
		save.setOnClickListener(this);
				
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, paths);
		spinner = (Spinner) findViewById(R.id.spinner1);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(this);		
	}

	private boolean canWrite() {
		state = Environment.getExternalStorageState();
		
		path.mkdirs();	
		
		if(state.equals(Environment.MEDIA_MOUNTED)) {
			canWrite.setText("true");
			canRead.setText("true");
			
			canR = canW = true;			
			
			return true;
		}
		else if(state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)){
			canWrite.setText("false");
			canRead.setText("true");
			
			canR = true;
			canW = false;
			
			return false;
		}
		else {
			canWrite.setText("false");
			canRead.setText("false");
			
			canR = false;
			canW = false;
			
			return false;
		}
		
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		
		int position = spinner.getSelectedItemPosition();
		
		switch(position){
			case 0:
				path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
				break;
			case 1:
				path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
				break;
			case 2:
				path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
				break;			
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.bConfirm:
			save.setVisibility(View.VISIBLE);			
			break;
		case R.id.bSaveAs:
			String f = saveFile.getText().toString();
			file = new File(path, f + ".png"); // directory, filename
			
			// Check and write to storage
			if(canWrite()) writeData();	
			
			// Display confirmation message
			Toast t = Toast.makeText(ExternalStorage.this, "File has been saved", Toast.LENGTH_LONG);
			t.show();
			
			// Update files for the user to use
			MediaScannerConnection.scanFile
			(
					ExternalStorage.this, 
					new String[] {file.toString()}, 
					null, 
					new MediaScannerConnection.OnScanCompletedListener() {
						
						@Override
						public void onScanCompleted(String path, Uri uri) {
							Toast t = Toast.makeText(ExternalStorage.this, "Scan Complete", Toast.LENGTH_LONG);
							t.show();							
						}
					}
			);
			break;
		}		
	}
}
