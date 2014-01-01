package com.dotdat.singlescreengame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

public class TileMap {
		
	private int[][] tileMap;
	private TileList tiles;
	private Paint paint;
		
	public TileMap(Resources startRManager, int[][] startTileMap){
		tiles = new TileList(startRManager);
		tileMap = startTileMap;	
		
		paint = new Paint();
		
		paint.setColor(Color.WHITE);
		paint.setAlpha(0);
	}
	Paint p = new Paint();	
	public void draw(Canvas canvas)
	{
		// Draw tile bitmap to canvas //(REDUNDANT HANDLED BY THE VIEWPORT)
		for(int col = 0; col < tileMap.length ; col++)
		{
			for(int row = 0; row < tileMap[0].length; row++)
			{				
				int x = (int) (col * 70);
				int y = (int) (row * 70);

				if(tileMap[col][row] != 0){
					canvas.drawBitmap(tiles.getTileBitmap(tileMap[col][row]), x, y, null);
				}
				
				p.setStyle(Paint.Style.STROKE);
				p.setColor(Color.MAGENTA);
				canvas.drawRect(x, y, x + 70,y + 70,  p);
				
//				if(tileMap[col][row] = 0){
//					canvas.drawBitmap(tiles.getTileBitmap(tileMap[col][row]), x, y, paint);
//				}
//				else{
//					canvas.drawBitmap(tiles.getTileBitmap(tileMap[col][row]), x, y, null);
//				}
				 				
			}
		}
	}
	
	public void setMapValue(int col, int row, int tileValue)
	{
		// Return bitmap of a indexed tile
		tileMap[col][row] = tileValue;
	}

	public int getMapValue(int col, int row)
	{
		// Return bitmap of a indexed tile
		// the row and col are reversed because of the way
		// the tile map is read into the game
		return tileMap[row][col]; 
	}
	
	public boolean isSolid(int col, int row) {
		return tiles.isSolid(tileMap[col][row]);
	}
	
	public boolean isWalkable(int col, int row) {
		return tiles.isWalkable(tileMap[col][row]);
	}
	
	public boolean isClimbable(int col, int row) {
		return tiles.isClimbable(tileMap[col][row]);
	}
		
	public Bitmap getTileBitmap(int col, int row) 	{ return tiles.getTileBitmap(tileMap[col][row]); }	
	public Point getTileMapBounds() 				{ return new Point(tileMap.length * 70, tileMap[0].length * 70); }

}
