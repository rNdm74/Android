package com.dotdat.singlescreengame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;

public class Splash extends Activity{
	MediaPlayer media;
	//Resources resources;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Make application full screen
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		// getWindow().setFlags(
		// WindowManager.LayoutParams.FLAG_FULLSCREEN, // Flags
		// WindowManager.LayoutParams.FLAG_FULLSCREEN // Mask
		// );
		WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();		
		Constants.screenSize = new Point();
		display.getSize(Constants.screenSize);

		setContentView(R.layout.splash);
		
		new BitmapManager(getResources()).execute();
		

		// Setup sounds to be played
		// media = MediaPlayer.create(Splash.this, R.raw.menu);

		// Access preferences
		// SharedPreferences getApplicationPreferences =
		// PreferenceManager.getDefaultSharedPreferences(getBaseContext());

		// boolean musicOn = getApplicationPreferences.getBoolean("checkbox",
		// true);
		// Soundpool gunshots etc
		// Mediaplayer music
		// if(musicOn) {
		// //media.start();
		// }

		// Create a new thread for splash animation
		Thread timer = new Thread() {
			public void run() {
				try {

					// Try code
					// Sleep for 5 seconds
					
					sleep(1000);
					

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {

					// Do something after try
					// Intent from manifest
					Intent openMainActivity = new Intent(
							"com.dotcom.singlescreengame.MENU");

					// Start new activity
					startActivity(openMainActivity);

				}
			}
		};

		// Start the thread
		timer.start();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();

		// Kill sound
		// media.release();

		// Clean up splash
		finish();

	}
}
