package com.dotdat.singlescreengame;

import java.io.ByteArrayOutputStream;
import java.lang.ref.WeakReference;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.DisplayMetrics;

public class BitmapManager extends AsyncTask<Integer, Void, Bitmap[]>  {
	
	private Resources rManager;
	
	public BitmapManager(Resources r) {
		rManager = r;
	}
	
	@Override
	protected Bitmap[] doInBackground(Integer... params) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		DisplayMetrics metrics = rManager.getDisplayMetrics();
		options.inJustDecodeBounds = true;
		options.inScreenDensity = metrics.densityDpi;
		options.inTargetDensity =  metrics.densityDpi;
		options.inDensity = metrics.densityDpi;
		options.inJustDecodeBounds = false;
		
		Bitmap[] bitmaps = new Bitmap[]{
			BitmapFactory.decodeResource(rManager, R.drawable.player_one_spritesheet, options),
			BitmapFactory.decodeResource(rManager, R.drawable.background, options),
			BitmapFactory.decodeResource(rManager, R.drawable.midground, options),
			BitmapFactory.decodeResource(rManager, R.drawable.foreground, options)	
		};
				
		return bitmaps;
	}
	
	@Override
    protected void onPostExecute(Bitmap[] bitmap) {
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		bitmap[0].compress(Bitmap.CompressFormat.PNG, 50, out);
		bitmap[1].compress(Bitmap.CompressFormat.PNG, 50, out);
		bitmap[2].compress(Bitmap.CompressFormat.PNG, 50, out);
		bitmap[3].compress(Bitmap.CompressFormat.PNG, 50, out);
		
		Constants.playerBitmap = bitmap[0];
		Constants.background = bitmap[1];
		Constants.midground = bitmap[2];
		Constants.foreground = bitmap[3];
    }
	
//	public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {
//
//	    // First decode with inJustDecodeBounds=true to check dimensions
//	    final BitmapFactory.Options options = new BitmapFactory.Options();
//	    options.inJustDecodeBounds = true;
//	    BitmapFactory.decodeResource(res, resId, options);
//
//	    // Calculate inSampleSize
//	    options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
//
//	    // Decode bitmap with inSampleSize set
//	    options.inJustDecodeBounds = false;
//	    return BitmapFactory.decodeResource(res, resId, options);
//	}
//	
//	public static int calculateInSampleSize(
//            BitmapFactory.Options options, int reqWidth, int reqHeight) {
//    // Raw height and width of image
//    final int height = options.outHeight;
//    final int width = options.outWidth;
//    int inSampleSize = 1;
//
//    if (height > reqHeight || width > reqWidth) {
//
//        final int halfHeight = height / 2;
//        final int halfWidth = width / 2;
//
//        // Calculate the largest inSampleSize value that is a power of 2 and keeps both
//        // height and width larger than the requested height and width.
//        while ((halfHeight / inSampleSize) > reqHeight
//                && (halfWidth / inSampleSize) > reqWidth) {
//            inSampleSize *= 2;
//        }
//    }
//
//    return inSampleSize;
//}
}
