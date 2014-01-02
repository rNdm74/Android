package com.thenewboston.adam;

import android.app.ListActivity;
import android.app.WallpaperManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Menu extends ListActivity {

	String classes[] = { "MainActivity", "TextPlay", "Check", "Scroll",
			"Email", "Camera", "Send", "Receive", "GFX", "GFXSurface", 
			"SoundManager", "Slider", "Tabs", "SimpleBrowser", "Flipper", 
			"SharedPrefs", "InternalData", "ExternalStorage", "Sql", 
			"Accelerate", "HTTPExample", "ParseJSONFile", "ParseXMLFile"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		// Make application full screen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(
				WindowManager.LayoutParams.FLAG_FULLSCREEN, // Flags
				WindowManager.LayoutParams.FLAG_FULLSCREEN  // Mask
		);
		
		// Setup list view for string items
		setListAdapter(new ArrayAdapter<String>(Menu.this,
				android.R.layout.simple_list_item_1, classes));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);

		// Init string from string array
		String className = classes[position];

		try {

			// Creates class from name from classes array
			Class ourClass = Class
					.forName("com.thenewboston.adam." + className);

			// new intent for starting new class
			Intent newIntent = new Intent(Menu.this, ourClass);

			// Start class
			startActivity(newIntent);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);

		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.menu, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		Intent intent;
		
		switch (item.getItemId()) {
		case R.id.aboutUs:
			intent = new Intent("com.thenewboston.adam.ABOUT");
			startActivity(intent);
			break;
		case R.id.preferences:
			intent = new Intent("com.thenewboston.adam.PREFS");
			startActivity(intent);
			break;
		case R.id.exit:
			finish();
			break;
		}

		return false;
	}
}
