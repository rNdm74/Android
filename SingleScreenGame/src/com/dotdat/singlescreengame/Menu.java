package com.dotdat.singlescreengame;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.util.LruCache;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Menu extends Activity implements OnClickListener {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.menu);
		
		initialize();
	}
	
	private void initialize(){
		Button start = (Button) findViewById(R.id.start_button);
		Button options = (Button) findViewById(R.id.options_button);
		Button exit = (Button) findViewById(R.id.exit_button);
		
		start.setOnClickListener(this);
		options.setOnClickListener(this);
		exit.setOnClickListener(this);
	}
	

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		setContentView(R.layout.menu);
		initialize();
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		setContentView(R.layout.menu);
		initialize();
	}



	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
	        //code to reset view
	        return false;
	    }
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.start_button:
			// Create intent to open game activity
			Intent openGameActivity = new Intent("com.dotcom.singlescreengame.GAME");
			// Start new activity
			startActivity(openGameActivity);
			break;
		
		case R.id.options_button:
			// Create intent to open game activity
			Intent openOptionsActivity = new Intent("com.dotcom.singlescreengame.OPTIONS");
			// Start new activity
			startActivity(openOptionsActivity);
			break;
		
		case R.id.exit_button:
			// Create intent to open game activity
			finish();
			break;
		}
	}
}
