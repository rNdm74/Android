package com.thenewboston.adam;

import android.app.Activity;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.View;

public class GFX extends Activity{

	MyBringBack ourView;
	WakeLock wl;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		// Wake-Lock
		PowerManager pm = (PowerManager) getSystemService(POWER_SERVICE);
		wl = pm.newWakeLock(View.KEEP_SCREEN_ON, "");
		
		super.onCreate(savedInstanceState);		
		wl.acquire();
		ourView = new MyBringBack(this);		
		setContentView(ourView);
	}

	
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		wl.acquire();
	}



	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		wl.release();
	}
	
	
}
