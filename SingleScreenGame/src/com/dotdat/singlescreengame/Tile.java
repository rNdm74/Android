package com.dotdat.singlescreengame;

import android.graphics.Bitmap;

public class Tile {
	
	Bitmap tile;
		
	boolean solid;
	boolean walkable;
	boolean climbable;
	
	public Tile(Bitmap startTileBitmap){
		tile = startTileBitmap;
	}
	
	public boolean isSolid() {
		return solid;
	}

	public void setSolid(boolean solid) {
		this.solid = solid;
	}

	public boolean isWalkable() {
		return walkable;
	}

	public void setWalkable(boolean walkable) {
		this.walkable = walkable;
	}

	public boolean isClimbable() {
		return climbable;
	}
	
	public void setClimbable(boolean climbable) {
		this.climbable = climbable;		
	}
	
	public Bitmap getTile() {
		return tile;
	}	
}
