package com.thenewboston.adam;

import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.format.Time;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

public class GFXSurface extends Activity implements OnTouchListener{

	float fXPos, fYPos;
	float t2XPos, t2YPos;
	float t3XPos, t3YPos;
	
	MyBringSurface ourSurfaceView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		// Init x,y pos
		fXPos = 0;
		fYPos = 0;
		
		ourSurfaceView = new MyBringSurface(this);
		ourSurfaceView.setOnTouchListener(this);
		
		setContentView(ourSurfaceView);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();		
		ourSurfaceView.pause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		ourSurfaceView.resume();
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		fXPos = event.getX();
		fYPos = event.getY();
		
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				t2XPos = event.getX();
				t2YPos = event.getY();
				break;

			case MotionEvent.ACTION_UP:
				t3XPos = event.getX();
				t3YPos = event.getY();
				break;
		}
		
		return true;
	}	
	
	
	public class MyBringSurface extends SurfaceView implements Runnable{
		
		SurfaceHolder ourHolder;
		Thread clock = null;
		
		boolean isRunning = false;
		
		
		int xDirection = 1;
		int yDirection = 1;
		
		float t1XPos, t1YPos, xVel, yVel;
		
		long startTime;
		long endTime;
		int fps;
		
		private int sleepTime = 0; // milliseconds to sleep (<0 if drawing behind schedule) 
	    private int framesSkipped = 0; // number of render frames skipped
		
		Bitmap[] images;
		Point[] imagePoints;
		
		Paint text;
		
		private final static int MAX_FPS = 60;
	    //maximum number of drawn frames to be skipped if drawing took too long last cycle
	    private final static int MAX_FRAME_SKIPS = 5;
	    //ideal time taken to update & draw
	    private final static int CYCLE_PERIOD = 1000 / MAX_FPS;
		
		public MyBringSurface(Context context) {
			super(context);
			
			text = new Paint();
			text.setColor(Color.BLACK);
			text.setTextAlign(Align.CENTER);
			text.setTextSize(25);
			
			xVel = 2;
			yVel = 2;
			
			images = new Bitmap[10];
			imagePoints = new Point[images.length];
			
			Random rGen = new Random();
									
			ourHolder = getHolder();
			
			for (int i = 0; i < images.length; i++) {
				images[i] = BitmapFactory.decodeResource(getResources(), R.drawable.square);	
				imagePoints[i] = new Point(rGen.nextInt(300), rGen.nextInt(200));
			}
			
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
				
				long now = System.nanoTime();
				framesSkipped = 0;
				
				Canvas canvas = ourHolder.lockCanvas();
	            
	            canvas.drawColor(Color.WHITE);
					
	            for (int i = 0; i < images.length; i++) {
					Bitmap b = images[i];
					
					int x = imagePoints[i].x;
					int y = imagePoints[i].y;
					
					if(x < 0 || x > (canvas.getWidth() - b.getWidth())){
						xDirection *= -1;
					}
					
					if(y < 0 || y > (canvas.getHeight() - b.getHeight())){
						yDirection *= -1;
					}
						
					imagePoints[i].x += xVel * xDirection;
					imagePoints[i].y += yVel * yDirection;
						
					canvas.drawBitmap(b, imagePoints[i].x, imagePoints[i].y, null);					
				}
				
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


