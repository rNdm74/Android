package com.thenewboston.adam;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Window;
import android.view.WindowManager;

public class Splash extends Activity{
	MediaPlayer media;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);		
		
		// Make application full screen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(
				WindowManager.LayoutParams.FLAG_FULLSCREEN, // Flags
				WindowManager.LayoutParams.FLAG_FULLSCREEN  // Mask
		);
		
		setContentView(R.layout.splash);
		media = MediaPlayer.create(Splash.this, R.raw.menu);
		// Access preferences
		SharedPreferences getApplicationPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		
		boolean musicOn = getApplicationPreferences.getBoolean("checkbox", true);	
		// Soundpool gunshots etc
		// Mediaplayer music		
		if(musicOn) {			
			media.start();			
		}
				
		// Create a new thread for splash animation
		Thread timer = new Thread(){
			public void run(){
				try{
					
					// Try code
					// Sleep for 5 seconds
					sleep(1000);
					
				}
				catch(InterruptedException e){
					
					// Exception Code
					// Debugging
					e.printStackTrace();
										
				}
				finally{
					
					// Do something after try
					// Intent from manifest
					Intent openMainActivity = new Intent("com.thenewboston.adam.MENU");
					
					// Start new activity
					startActivity(openMainActivity);
					
				}
			}			
		};
		
		// Start the thread
		timer.start();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		
		// Kill sound
		media.release();
		
		// Clean up splash
		finish();
	}  
}
