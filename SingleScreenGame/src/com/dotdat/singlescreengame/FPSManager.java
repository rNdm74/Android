package com.dotdat.singlescreengame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Typeface;

public class FPSManager {

	private Paint font;
	private int maxFrames, frames;
	private long totalTime;
	private float scale;

	public FPSManager() {
		font = new Paint();
		font.setColor(Color.rgb(51, 181, 229));
		font.setTypeface(Typeface.DEFAULT_BOLD);
		font.setTextSize(50);

		maxFrames = 0;
		frames = 0;
		totalTime = 0;
		scale = 0;
	}

	public void draw(Canvas canvas) {
		canvas.drawText("FPS: " + maxFrames, 20f, 50f, font);
		//canvas.drawText("SCALE: " + scale, 20f, 100f, font);
	}

	public void calculateFPS(long lastTime) {
		long now = System.nanoTime();
		long passed = now - lastTime;
		lastTime = now;
		totalTime += passed;

		if (totalTime >= 1000000000) {
			maxFrames = frames;
			totalTime = 0;
			frames = 0;
		}

		frames++;
	}

	public void drawScale(float scale2) {
		scale = scale2;
	}
}
