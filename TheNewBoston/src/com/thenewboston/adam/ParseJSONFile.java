package com.thenewboston.adam;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ParseJSONFile extends Activity implements OnClickListener{
	
	//JSON Node Names 
    private static final String TAG_FRAMES = "frames";
    private static final String TAG_BEAU = "BeauBattle";
    private static final String TAG_FRAME = "frame";
    private static final String TAG_ROTATED = "rotated";
    private static final String TAG_TRIMMED = "trimmed";
    private static final String TAG_SPRITE_SOURCE_SIZE = "spriteSourceSize";
    private static final String TAG_SOURCE_SIZE = "sourceSize";
    private WeakReference<ImageView> imageViewReference;
    
	Context context;
	TextView loading;	
	TextView jsonData;	
	
	JSONObject jsonFile;
	JSONArray user = null;
	
	ImageView sprite;
	Bitmap spriteSheet;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.parsesjson);

		loading = (TextView) findViewById(R.id.tvWaitingJson);
		jsonData = (TextView) findViewById(R.id.tvJsonData);
		Button next = (Button) findViewById(R.id.bNextObject);
		Button back = (Button) findViewById(R.id.bBackObject);
		
		sprite = (ImageView) findViewById(R.id.ivSprite);
		
		loadBitmap(R.drawable.mm, sprite);
		
		//sprite.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.player, 158, 202));
		
		//spriteSheet = BitmapFactory.decodeResource(getResources(), R.drawable.player);
		
		//Bitmap b = Bitmap.createBitmap(spriteSheet, 5611, 6029, 158, 202);
		
		//sprite.setImageBitmap(b);
		
		next.setOnClickListener(this);
		back.setOnClickListener(this);

		context = getBaseContext();
		
		new Read().execute("megamansprites.json");
		
		
	}
	
	public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
    // Raw height and width of image
    final int height = options.outHeight;
    final int width = options.outWidth;
    int inSampleSize = 1;

    if (height > reqHeight || width > reqWidth) {

        final int halfHeight = height / 2;
        final int halfWidth = width / 2;

        // Calculate the largest inSampleSize value that is a power of 2 and keeps both
        // height and width larger than the requested height and width.
        while ((halfHeight / inSampleSize) > reqHeight
                && (halfWidth / inSampleSize) > reqWidth) {
            inSampleSize *= 2;
        }
    }

    return inSampleSize;
}
	
	public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
	        int reqWidth, int reqHeight) {

	    // First decode with inJustDecodeBounds=true to check dimensions
	    final BitmapFactory.Options options = new BitmapFactory.Options();
	    options.inJustDecodeBounds = true;
	    BitmapFactory.decodeResource(res, resId, options);

	    // Calculate inSampleSize
	    options.inSampleSize = 1;
	    options.inScaled = true;

	    // Decode bitmap with inSampleSize set
	    options.inJustDecodeBounds = false;
	    return BitmapFactory.decodeResource(res, resId, options);
	}
	
	public void loadBitmap(int resId, ImageView imageView) {
	    BitmapWorkerTask task = new BitmapWorkerTask(imageView);
	    task.execute(resId);
	}
	
	class BitmapWorkerTask extends AsyncTask<Integer, Void, Bitmap> {
	    
	    private int data = 0;

	    public BitmapWorkerTask(ImageView imageView) {
	        // Use a WeakReference to ensure the ImageView can be garbage collected
	        imageViewReference = new WeakReference<ImageView>(imageView);
	    }

	    // Decode image in background.
	    @Override
	    protected Bitmap doInBackground(Integer... params) {
	        data = params[0];
	        return decodeSampledBitmapFromResource(getResources(), data, 0, 0);
	    }

	    // Once complete, see if ImageView is still around and set bitmap.
	    @Override
	    protected void onPostExecute(Bitmap bitmap) {
	    	if(bitmap != null)
	    		spriteSheet = bitmap;
	    }
	}
	
	private class Read extends AsyncTask<String, Integer, JSONObject> {

		private InputStream stream;
		private ProgressDialog pDialog;
				
		private JSONObject jObj = null;
		private String jsonString = "";
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			 pDialog = new ProgressDialog(ParseJSONFile.this);
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
				stream = context.getAssets().open("megamansprites.json");
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
	 
	        // return JSON String
	        return jObj;
		}
		
		

		@Override
		protected void onPostExecute(JSONObject json) {
			// TODO Auto-generated method stub
			super.onPostExecute(json);
				
			pDialog.dismiss();			
			
			jsonFile = json;
			
			String finished = "Finished";
			loading.setText(finished);
		
		}
	}
	
	
	private void showFrames(){
		try {
			JSONArray root = jsonFile.getJSONArray("sprites");
			
			//JSONObject sprites = root.toJSONObject(root);
			
			StringBuilder name = new StringBuilder();
			
			
			for (int i = 0; i < root.length(); i++) {
				JSONObject post = (JSONObject) root.getJSONObject(i);				
				name.append(post.getString("name") + "\n");
				
				JSONArray attachments = post.getJSONArray("frames");
				ArrayList<Rect> rects = new ArrayList<Rect>();
				for (int j = 0; j < attachments.length(); j++) {
					JSONObject frame = (JSONObject) attachments.getJSONObject(j);
					
					JSONObject pos = frame.getJSONObject("pos");
					JSONObject size = frame.getJSONObject("size");
					
					rects.add(new Rect(pos.getInt("x"), pos.getInt("y"), size.getInt("w"), size.getInt("h")));
					name.append(rects.get(j) + "\n");
//					name.append();
//					name.append();
//					name.append();
				}
				
				name.append("\n");
			}
			
			jsonData.setText(name);
			
//			JSONArray frames = sprites.getJSONArray("frames");
//			
//			
//			
//			for (int i = 0; i < frames.length(); i++) {
//				JSONObject frame = (JSONObject) frames.get(i);
//				
//				JSONObject pos = frame.getJSONObject("pos");
//				JSONObject size = frame.getJSONObject("size");
//				
//				rects.add(new Rect());
//				
//				
//			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	private void showData(int index){
		try {
			JSONObject root = jsonFile.getJSONObject(TAG_FRAMES);	
			
			String item = String.format("%04d", index);
			
			JSONObject beau = root.getJSONObject(TAG_BEAU + item);			
			
			loading.setText(TAG_BEAU + item);
			
			JSONObject frame = beau.getJSONObject(TAG_FRAME);
			JSONObject spriteSourceSize = beau.getJSONObject(TAG_SPRITE_SOURCE_SIZE);
			JSONObject sourceSize = beau.getJSONObject(TAG_SOURCE_SIZE);
			
			// Sprite Rotated
			boolean spriteRotated = beau.getBoolean(TAG_ROTATED);
			boolean spriteTrimmed = beau.getBoolean(TAG_TRIMMED);
			
			//Sprite Frame
			Rect spriteFrame = new Rect
			(
					frame.getInt("x"), 
					frame.getInt("y"), 
					frame.getInt("w"), 
					frame.getInt("h")
			);
			
			
			// Sprite source size
			Rect spriteSourceSizeRect = new Rect
			(
					spriteSourceSize.getInt("x"), 
					spriteSourceSize.getInt("y"), 
					spriteSourceSize.getInt("w"), 
					spriteSourceSize.getInt("h")
			);
			
			// Source Dimensions
			Point sourceDimensions = new Point
			(
					sourceSize.getInt("w"), 
					sourceSize.getInt("h")
			);
			
			jsonData.setText
			(
					"frame: " + spriteFrame.toString() + "\n" + 
					"rotated: " + spriteRotated + "\n" + 
					"trimmed: " + spriteTrimmed + "\n" + 
					"spriteSourceSize: " + spriteSourceSizeRect.toString() + "\n" +
					"sourceSize: " + sourceDimensions.toString()
			);
            			
			if (imageViewReference != null && spriteSheet != null) {
	            final ImageView imageView = imageViewReference.get();
	            
	            if (imageView != null) {
	            	
//	            	Bitmap b = Bitmap.createBitmap(spriteSheet, frame.getInt("x")/2, frame.getInt("y")/2, frame.getInt("w")/2, frame.getInt("h")/2);	
//	                imageView.setImageBitmap(Bitmap.createScaledBitmap(b, frame.getInt("w"), frame.getInt("h"), true));
//	                b.recycle();
	            }
			}
            
			} catch (JSONException e) { e.printStackTrace(); }
	}
	
	public static int index;
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bNextObject:
			showFrames();
//			if(index <= 499){
//				showData(index);
//				index++;
//			}
			break;

		case R.id.bBackObject:
			showFrames();
//			if(index > 0){
//				showData(index); 
//				index--;
//			} 
			break;
		}
	}
}
