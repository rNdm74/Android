package com.dotdat.singlescreengame;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.PointF;

public final class Constants {
	
	public enum State{
		NORTH,
		EAST,
		SOUTH,
		WEST,
		STANDING,
		LOOK_LEFT,
		LOOK_RIGHT, 
		CLIMB
	}
	
	public static final int SPEED = 1;
	public static float SCALE;
	
	public static Bitmap playerBitmap = null;
	public static Bitmap background = null;
	public static Bitmap midground = null;
	public static Bitmap foreground = null;
	
	public static Point screenSize = null;
	
	public static PointF touchLocation = null;
}
