package com.dotdat.singlescreengame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

public class GameSurface extends SurfaceView implements Runnable {

	private SurfaceHolder surfaceHolder;

	private GameManager gManager;
	
	private Resources rManager;
	
	private Thread clock = null;

	private boolean isRunning = true;
	
	private float scale;

	public GameSurface(Context context) {
		super(context);

		surfaceHolder = getHolder();
		
		rManager = getResources();
		
		gManager = new GameManager(surfaceHolder, rManager);
		
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		
		Point screenSize = new Point();
		display.getSize(screenSize);
		
		scale = getPixelPerInch(rManager.getDisplayMetrics().densityDpi, rManager.getDisplayMetrics().density);
		
		gManager.initialize(screenSize);

		// Setup clock
		clock = new Thread(this);
		
		clock.start();
		
	}
	
	private float getPixelPerInch(int densityDpi, float scale){		
		
		switch(densityDpi){ 
		 	
			case DisplayMetrics.DENSITY_TV: 		return 70 * ((213 * scale));
		 	case DisplayMetrics.DENSITY_400: 		return 400 * scale;
       	 	case DisplayMetrics.DENSITY_LOW: 		return 120 * scale; 
       	 	case DisplayMetrics.DENSITY_DEFAULT: 	return 160 * scale; 
       	 	case DisplayMetrics.DENSITY_HIGH: 		return 240 * scale; 
       	 	case DisplayMetrics.DENSITY_XHIGH: 		return 320 * scale;
       	 	case DisplayMetrics.DENSITY_XXHIGH: 	return 480 * scale;
       	 	case DisplayMetrics.DENSITY_XXXHIGH: 	return 640 * scale;
		} 
		 
		return 0;
	}
	
	@Override
	public void run() {
		
		while (isRunning) {
			long lastTime = System.nanoTime();
			// Checks if surface is valid then run rest of the code
			if (!surfaceHolder.getSurface().isValid())
				continue;

			//gManager.check();
			//gManager.move();
			gManager.update();
			gManager.draw();
			gManager.calculateFPS(lastTime);
			//gManager.drawScale(scale);

		}
	}

	public void pause() {
		isRunning = false;

		// Run thread till finished
		while (true) {
			try {
				clock.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			break;
		}

		// destroys thread
		clock = null;
	}

	public void resume() {
		isRunning = true;

		clock = new Thread(this);
		clock.start();
	}
}
