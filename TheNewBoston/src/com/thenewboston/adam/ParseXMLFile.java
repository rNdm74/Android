package com.thenewboston.adam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;

public class ParseXMLFile extends Activity implements OnClickListener{
	
	class SpriteState
	{
		public String state;
		public ArrayList<Rect> spriteFrames;
		
		public SpriteState(){
			state = "";
			spriteFrames = new ArrayList<Rect>();
		}		
	}
		
	//private TextView data;
	MyBringSurface ourSurfaceView;
	JSONObject jsonFile;
	String jsonString = "";
	
	public static ArrayList<SpriteState> states;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		states = new ArrayList<SpriteState>();
		
		new Read().execute("megamansprites.json");
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ourSurfaceView = new MyBringSurface(this);
		
		setContentView(ourSurfaceView);		
	}
	
	private ArrayList<SpriteState> getSpriteStates(JSONObject file){
		
		ArrayList<SpriteState> states = new ArrayList<SpriteState>();
		

		
		return states;
	}
	
	public ArrayList<SpriteState> xmlPullParser(){
		XmlPullParserFactory pullParserFactory;
		try {
			pullParserFactory = XmlPullParserFactory.newInstance();
			XmlPullParser parser = pullParserFactory.newPullParser();

			    InputStream in_s = getApplicationContext().getAssets().open("birds.xml");
		        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
	            parser.setInput(in_s, null);

	            return parseXML(parser);

		} catch (XmlPullParserException e) {

			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	
	public ArrayList<SpriteState> parseXML(XmlPullParser parser) throws XmlPullParserException,IOException
	{
		ArrayList<SpriteState> spriteStates = null;
		SpriteState currentSpriteState = null;
		
        int eventType = parser.getEventType();
                
        while (eventType != XmlPullParser.END_DOCUMENT){
        	
            String name = null;
            
            switch (eventType){
            
                case XmlPullParser.START_DOCUMENT:
                	spriteStates = new ArrayList<SpriteState>();               	
                    break;
                    
                case XmlPullParser.START_TAG:
                	
                    name = parser.getName().trim();
                                        
                    if (name.equalsIgnoreCase("dir")){
                    	currentSpriteState = new SpriteState();
                    	currentSpriteState.spriteFrames = new ArrayList<Rect>(); 
                    	
                    	String n = parser.getAttributeValue(null, "name");
                    	
                    	currentSpriteState.state = n;
                    }
                    
                    if(name.equalsIgnoreCase("spr")){
                    	
                		int x = Integer.parseInt(parser.getAttributeValue(null, "x"));
                		int y = Integer.parseInt(parser.getAttributeValue(null, "y"));
                		int w = Integer.parseInt(parser.getAttributeValue(null, "w"));
                		int h = Integer.parseInt(parser.getAttributeValue(null, "h"));
                    	
                    	Rect r = new Rect(x, y, w, h); 
                    	
                    	currentSpriteState.spriteFrames.add((Rect)r);
                    }
                    break;
                    
                case XmlPullParser.END_TAG:
                    name = parser.getName();
                    
                    if (name.equalsIgnoreCase("dir")){                    	
                    	spriteStates.add(currentSpriteState);
                    }
            }
            
            eventType = parser.next();
        }
        
        return spriteStates;
	}

	public void displayArrayList(ArrayList<SpriteState> spriteStates){
		StringBuilder sb = new StringBuilder();		
		
		for (SpriteState item : spriteStates) {
			
			sb.append("State=" + item.state + "\n");
			
			ArrayList<Rect> rects = item.spriteFrames;
			
			for (Rect rect : rects) {
				sb.append("Frame=" + rect + "\n");				
			}			
		}
		
		//data.setText(sb.toString());
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

	private class Read extends AsyncTask<String, Integer, JSONObject> {

		private InputStream stream;
		private ProgressDialog pDialog;
				
		private JSONObject jObj = null;
		
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			 pDialog = new ProgressDialog(ParseXMLFile.this);
	         pDialog.setMessage("Getting Data ...");
	         pDialog.setIndeterminate(false);
	         pDialog.setCancelable(true);
	         pDialog.show();
		}

//		@Override
//		protected void onProgressUpdate(Integer... values) {
//			// TODO Auto-generated method stub
//			super.onProgressUpdate(values);
//		}

		@Override
		protected JSONObject doInBackground(String... params) {
			
			
			try {
				stream = getApplicationContext().getAssets().open("megamansprites.json");
	            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "iso-8859-1"), 1000);
	            StringBuilder sb = new StringBuilder();
	            String line = null;
	            while ((line = reader.readLine()) != null) {	            	
	                sb.append(line + "\n");
	            }
	            stream.close();
	            jsonString = sb.toString();
	            
	            //jsonData.setText(jsonString);
	        } catch (Exception e) {
	            Log.e("Buffer Error", "Error converting result " + e.toString());
	        }
			
			// try parse the string to a JSON object
	        try {
	            jObj = new JSONObject(jsonString);
	        } catch (JSONException e) {
	            Log.e("JSON Parser", "Error parsing data " + e.toString());
	        }
	 
	        jsonString = "returnedobject";
	        // return JSON String
	        return jObj;
		}
		
		

		@Override
		protected void onPostExecute(JSONObject json) {
			// TODO Auto-generated method stub
			super.onPostExecute(json);
						
			try {			
				SpriteState currentSpriteState = null;
				
				JSONArray root = json.getJSONArray("sprites");
				
				for (int i = 0; i < root.length(); i++) {
					
					currentSpriteState = new SpriteState();
					
					JSONObject post = (JSONObject) root.getJSONObject(i);
					
					currentSpriteState.state = post.getString("name");
					
					JSONArray attachments = post.getJSONArray("frames");
									
					for (int j = 0; j < attachments.length(); j++) {
						JSONObject frame = (JSONObject) attachments.getJSONObject(j);
						
						JSONObject pos = frame.getJSONObject("pos");
						JSONObject size = frame.getJSONObject("size");
						
						currentSpriteState.spriteFrames.add(new Rect(pos.getInt("x"), pos.getInt("y"), size.getInt("w"), size.getInt("h")));					
					}
							
					states.add(currentSpriteState);
				}
				
				jsonString = Integer.toString(states.get(0).spriteFrames.get(0).right);//Integer.toString(states.size());
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			pDialog.dismiss();		
		}
	}
		
	
	public class MyBringSurface extends SurfaceView implements Runnable{
		private final static int MAX_FPS = 30;
	    //maximum number of drawn frames to be skipped if drawing took too long last cycle
	    private final static int MAX_FRAME_SKIPS = 5;
	    //ideal time taken to update & draw
	    private final static int CYCLE_PERIOD = 1000 / MAX_FPS;
	    // milliseconds to sleep (<0 if drawing behind schedule)
	    private int sleepTime = 0;  
	    // number of render frames skipped
	    private int framesSkipped = 0; 
	    long startTime;
	    long endTime;
	    int fps;
		
		
	    
		SurfaceHolder ourHolder;
		Thread clock = null;
				
		boolean isRunning = true;				
				
		float t1XPos, t1YPos, xVel, yVel;
		
		Bitmap spriteSheet;
		
		int currentFrame;
		int nFrames;
		int frameWidth;
		int frameHeight;
		
		int xPos;
		int yPos;
		
		Random rGen;
					
		Paint font;
		
		public MyBringSurface(Context context) {
			super(context);
			
			font = new Paint();
			font.setColor(Color.BLACK);
			font.setTextAlign(Align.CENTER);
			font.setTextSize(25);
			
			xPos = 200;
			yPos = 200;
			xVel = 2;
			yVel = 2;
			
			rGen = new Random();
			
			spriteSheet = BitmapFactory.decodeResource(getResources(), R.drawable.mm);
			
			nFrames = spriteSheet.getWidth() / spriteSheet.getHeight();
			frameWidth = spriteSheet.getWidth() / nFrames;
			frameHeight = spriteSheet.getHeight();
			currentFrame = 1;
			
			// ERROR !!!
			jsonString = states.get(0).spriteFrames.get(0).toString();
				
//			nFrames = states.get(0).spriteFrames.size();
//			frameWidth = states.get(0).spriteFrames.get(0).right;
//            frameHeight = states.get(0).spriteFrames.get(0).bottom;
//			currentFrame = 0;
			
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
					
	            //xPos += 1;
	            
	            currentFrame %= nFrames;
	            
	            //canvas.drawBitmap(spriteSheet, xPos, yPos, null);
	            
//	            frameWidth = states.get(0).spriteFrames.get(currentFrame).right;
//	            frameHeight = states.get(0).spriteFrames.get(currentFrame).bottom;
	            
	            int srcX = currentFrame * frameWidth;
	            int srcY = 0;
	            
	            Rect src = new Rect(srcX, srcY,  srcX + frameWidth, frameHeight);	            
	            Rect dst = new Rect(xPos, yPos, xPos + (frameWidth), yPos + (frameHeight));	
	            
	            canvas.drawBitmap(spriteSheet, src, dst, null);
	            
	            currentFrame++;
	            
	            canvas.drawText("\t" + jsonString, 50f, 100f, font);
				canvas.drawText("\tFPS: \t" + maxFrames, 50f, 50f, font);					
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
