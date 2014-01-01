package com.dotdat.singlescreengame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.SurfaceHolder;

import com.dotdat.singlescreengame.Constants.State;

public class GameManager {

	private SurfaceHolder surfaceHolder;
	private Resources rManager;
	private FPSManager fpsManager;

	private Sprite sprite;
	private TileMap tileMap;
	
	private float backgroundXPos = -150 ;
	private float midgroundXPos = -150;
	private float foregroundXPos = -150;
		
	private int width;
	private int height;
	
	private Paint paint;
	
		
	public GameManager(SurfaceHolder startSurfaceHolder, Resources startRManager) {

		surfaceHolder = startSurfaceHolder;

		rManager = startRManager;

		fpsManager = new FPSManager();
		
		paint = new Paint();
		paint.setColor(Color.rgb(8, 59, 114));
				
		int[][] map = new int[][]{
				{0,0,1,0,1,0,1,0,1,0,1},
				{0,0,1,0,1,0,1,0,1,0,1},
				{0,0,1,0,1,0,1,0,1,0,1},
				{0,0,1,0,1,0,1,0,1,0,1},
				{0,0,1,0,1,0,1,0,1,0,1},
				{0,0,1,0,1,0,1,0,1,0,1},
				{0,0,1,0,1,0,1,0,1,0,1},
				{0,0,1,0,1,0,1,0,1,0,1},
				{0,0,1,0,1,0,1,0,1,0,1},
				{0,0,3,2,2,2,2,2,2,2,1},
				{0,0,1,0,1,0,1,0,1,0,1},
				{0,0,1,0,1,0,1,0,1,0,1},
				{0,0,1,0,1,0,1,0,1,0,1},
				{0,0,1,0,1,0,1,0,1,0,1},
				{0,0,1,0,1,0,1,0,1,0,1},
				{0,0,1,0,1,0,1,0,1,0,1},
				{0,0,1,0,1,0,1,0,1,0,1},
				{0,0,1,0,1,0,1,0,1,0,1},
				{0,0,1,0,1,0,1,0,1,0,1}
		};
		
		tileMap = new TileMap(startRManager, map);
		
		//backgroundXPos = Constants.screenSize.x - (Constants.background.getWidth()*2);
	}

	public void initialize(Point screenSize) {
				
		for (int entity = 0; entity < 1; entity++) {
			sprite = new Sprite(Constants.playerBitmap, tileMap, new Point(100, tileMap.getTileMapBounds().y));
		}		
		
		width = screenSize.x;
		height = screenSize.y;
	}
				
	public void check() {
		sprite.check();		
	}

	public void move() {		
	}

	public void update() {		
		if(sprite.state == State.EAST) {
			backgroundXPos += 0.15f;
			midgroundXPos += 0.25f;
			foregroundXPos += 0.5f;
		}
		
		if(sprite.state == State.WEST) {
			backgroundXPos -= 0.15f;
			midgroundXPos -= 0.25f;
			foregroundXPos -= 0.5f;
		}
		
		sprite.update();				
	}

	public void drawScaled(Canvas canvas, Bitmap bitmap, float xPos, float yPos){
		int bWidth = bitmap.getWidth();
		int bHeight = bitmap.getHeight() / 2;
		
		int sWidth = bWidth * 2;
		int sHeight = bHeight * 2;
		
		Rect src = new Rect(0, 0, bWidth, bHeight);
		RectF dst = new RectF(xPos, yPos, xPos + sWidth, yPos + sHeight);
								
		canvas.drawBitmap(bitmap, src, dst, null);
	}
	public void draw() {

		Canvas canvas = surfaceHolder.lockCanvas();
		
		canvas.drawRGB(4, 52, 101);		
		drawScaled(canvas, Constants.background, backgroundXPos, 0);
		drawScaled(canvas, Constants.midground, midgroundXPos, Constants.background.getHeight() - (Constants.midground.getHeight() / 2.5f));
		canvas.drawRect(0, (Constants.background.getHeight() + Constants.midground.getHeight() + Constants.foreground.getHeight()), Constants.screenSize.x, Constants.screenSize.y, paint);
		drawScaled(canvas, Constants.foreground, foregroundXPos, (Constants.background.getHeight() + Constants.midground.getHeight() + Constants.foreground.getHeight()) - (Constants.foreground.getHeight()));
		
		tileMap.draw(canvas);

		sprite.draw(canvas);
		
		fpsManager.draw(canvas);

		surfaceHolder.unlockCanvasAndPost(canvas);
	}

	public void calculateFPS(long lastTime) {

		fpsManager.calculateFPS(lastTime);		
	}
	
	public void drawScale(float scale){
		fpsManager.drawScale(scale);
	}	
}
