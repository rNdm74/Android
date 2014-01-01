package com.dotdat.singlescreengame;

import android.app.Activity;
import android.graphics.Point;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class Game extends Activity implements OnTouchListener{

	private GameSurface gameSurface;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		gameSurface = new GameSurface(this);
		gameSurface.setOnTouchListener(this);
		
		setContentView(gameSurface);
		
		
	}
			
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();		
		gameSurface.pause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		gameSurface.resume();
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		Constants.touchLocation = new PointF(event.getX(), event.getY());
		return false;
	}
}
