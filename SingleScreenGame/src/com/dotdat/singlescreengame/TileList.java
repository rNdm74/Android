package com.dotdat.singlescreengame;

import java.io.ByteArrayOutputStream;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;

public class TileList {
	private Resources rManager;
	private Bitmap[] tileBitmaps;
	private Tile[] tiles;
	
	public TileList(Resources startRManager){
		rManager = startRManager;
		
		BitmapFactory.Options options = new BitmapFactory.Options();
		DisplayMetrics metrics = startRManager.getDisplayMetrics();
		options.inJustDecodeBounds = true;
		options.inScreenDensity = metrics.densityDpi;
		options.inTargetDensity =  metrics.densityDpi;
		options.inDensity = metrics.densityDpi;
		options.inJustDecodeBounds = false;
		
		Bitmap sky = BitmapFactory.decodeResource(rManager, R.drawable.clear, options);
		Bitmap skycloud = BitmapFactory.decodeResource(rManager, R.drawable.skycloud, options);
		Bitmap grass = BitmapFactory.decodeResource(rManager, R.drawable.stone, options);
		Bitmap ladder_mid = BitmapFactory.decodeResource(rManager, R.drawable.ladder_mid, options);
		Bitmap ladder_top = BitmapFactory.decodeResource(rManager, R.drawable.ladder_top, options);
		
		
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		sky.compress(Bitmap.CompressFormat.PNG, 50, out);
		skycloud.compress(Bitmap.CompressFormat.PNG, 50, out);
		grass.compress(Bitmap.CompressFormat.PNG, 50, out);
		ladder_mid.compress(Bitmap.CompressFormat.PNG, 50, out);
		ladder_top.compress(Bitmap.CompressFormat.PNG, 50, out);
		
		
		tileBitmaps = new Bitmap[]{ sky, grass, ladder_mid, ladder_top };
						
		tiles = new Tile[tileBitmaps.length];

		for(int i = 0; i < tiles.length; i++)
			tiles[i] = new Tile(tileBitmaps[i]);
		
		tiles[1].setSolid(true);
		tiles[2].setClimbable(true);
		tiles[3].setClimbable(true);
	}
	
	Bitmap getTileBitmap(int index)
	{		
		// Return bitmap of a indexed tile	
		return tiles[index].getTile();
	}
	
	public boolean isSolid(int index) {
		return tiles[index].isSolid();
	}
	
	public boolean isWalkable(int index) {
		return tiles[index].isWalkable();
	}
	
	public boolean isClimbable(int index) {
		return tiles[index].isClimbable();
	}
		
	public void setTile(int index, Tile newTile) { tiles[index] = newTile; }
}

