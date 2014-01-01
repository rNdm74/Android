package com.dotdat.singlescreengame;

import android.graphics.Rect;

public class JSONParser {

	private Rect[] frames;

	public JSONParser() {
		frames = new Rect[] 
		{
			new Rect(365, 98, 69, 71), 	// DUCK
			new Rect(0, 196, 66, 92), 	// FRONT
			new Rect(438, 0, 69, 92), 	// HURT
			new Rect(438, 93, 67, 94), 	// JUMP
			new Rect(67, 196, 66, 92) 	// STAND
		};
		
		frames = new Rect[]{
			new Rect(0, 0, 72, 97),
			new Rect(73, 0, 72, 97),
			new Rect(146, 0, 72, 97),
			new Rect(0, 98, 72, 97),
			new Rect(73, 98, 72, 97),
			new Rect(146, 98, 72, 97),
			new Rect( 219, 0, 72, 97),
			new Rect(292, 0, 72, 97),
			new Rect(219, 98, 72, 97),
			new Rect(365, 0, 72, 97),
			new Rect(292, 98, 72, 97)
		};
	}
}
