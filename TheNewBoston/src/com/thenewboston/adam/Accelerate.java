package com.thenewboston.adam;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Accelerate extends Activity implements SensorEventListener{
	SensorManager sensorManager;
	Bitmap square;
	float x, y, sensorX, sensorY;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(new MySurface(this));	
		
		sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
				
		if(sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER).size() != 0){
			Sensor sensor = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
			sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
		}
		
		square = BitmapFactory.decodeResource(getResources(), R.drawable.square);
		
		x = y = sensorX = sensorY = 0;
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
		sensorManager.unregisterListener(this);
	}



	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		try {
			Thread.sleep(16);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sensorX = event.values[0];
		sensorY = event.values[1];		
	}
	
public class MySurface extends SurfaceView implements Runnable{
	
		private final static int MAX_FPS = 60;
		//maximum number of drawn frames to be skipped if drawing took too long last cycle
		private final static int MAX_FRAME_SKIPS = 5;
		//ideal time taken to update & draw
		private final static int CYCLE_PERIOD = 1000 / MAX_FPS;
		
		private int sleepTime = 0; // milliseconds to sleep (<0 if drawing behind schedule)  
	    private int framesSkipped = 0; // number of render frames skipped
		
		SurfaceHolder ourHolder;
		Thread clock = null;
		
		boolean isRunning = true;
		
		long startTime;
		long endTime;
		int fps;		
				
		Paint text;
		
		public MySurface(Context context) {
			super(context);
			
			text = new Paint();
			text.setColor(Color.BLACK);
			text.setTextAlign(Align.CENTER);
			text.setTextSize(25);			
									
			ourHolder = getHolder();
						
			// Setup clock
			clock = new Thread(this);
			clock.start();
		}

		public void pause() {
			isRunning = false;	
			
			// Run thread thill finished
			while(true){
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
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
//			startTime = System.currentTimeMillis();
						
			int maxFrames = 0;
	        int frames = 0;
	        long lastTime = System.nanoTime();
	        long totalTime = 0;
	        
	        
			
			while(isRunning) {				
				// Checks if surface is valid then run rest of the code
				if(!ourHolder.getSurface().isValid()) continue;	
				
				// get Time before anything is drawn to the screen				
				long now = System.nanoTime();
				framesSkipped = 0;
				
				Canvas canvas = ourHolder.lockCanvas();	            
	            canvas.drawColor(Color.WHITE);		
	            
	            float centerX = canvas.getWidth() / 2;
	            float centerY = canvas.getHeight() / 2;
	            
	            
				canvas.drawBitmap(square, centerX + sensorX * 20, centerY + sensorY * 20, null);	
				
				canvas.drawText("FPS: " + maxFrames, 150f, 200f, text);	
				
				ourHolder.unlockCanvasAndPost(canvas);
				
	            long passed = now - lastTime;	            
	            lastTime = now;
	            totalTime += passed;
	            	            
	            if(totalTime >= 1000000000){
	            	maxFrames = frames;
	                totalTime = 0;
	                frames = 0;
	            }
	            		            
	            //calculate potential sleep time
	            sleepTime = (int)(CYCLE_PERIOD - (passed / 1000000000));
	            
	            //sleep for remaining cycle
                if (sleepTime > 0){
                    try{
                        Thread.sleep(sleepTime); //saves battery! :)
                    } catch (InterruptedException e){}
                }
                
                //if sleepTime negative then we're running behind
                while (sleepTime < 0 && framesSkipped < MAX_FRAME_SKIPS){                    
                    //skip as many frame renders as needed to get back into
                    //positive sleepTime and continue as normal
                    sleepTime += CYCLE_PERIOD;
                    framesSkipped++;
                }
                
                frames++;
			}		
		}		
	}
}
