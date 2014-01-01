package com.dotdat.singlescreengame;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.preference.SwitchPreference;

import com.dotdat.singlescreengame.Constants.State;

public class Sprite {

	private static final int SPEED = 2;

	private Bitmap spriteSheet;
	private int width;
	private int height;

	private int xPos;
	private int yPos;
	private int xVel;
	private int yVel;

	private Rect[][] frames;
	private Rect[] walking;
	private Rect[] jumping;
	private Rect[] ducking;
	private Rect[] standing;
	private int frameWidth;
	private int frameHeight;
	private int nFrames;
	private int currentFrame;
	
	private final static int standTime = 1000;
	private int time;
	
	private int look = 1;
	
	private int startJumpYPos;
	private boolean isJumping;
	
	private Point[] direction;

	public State state;
	
	private Random rGen;
	
	private Matrix matrix;
	
	private TileMap tileMap;

	private boolean isClimbing;

	public Sprite(Bitmap startSpriteSheet, TileMap startTileMap, Point startPosition) {
		spriteSheet = startSpriteSheet;

		tileMap = startTileMap;
				
		rGen = new Random();
		
		matrix = new Matrix();

		walking = new Rect[] 
		{
				new Rect(0, 0, 72, 97),
				new Rect(73, 0, 72, 97),
				new Rect(146, 0, 72, 97), 
				new Rect(0, 98, 72, 97),
				new Rect(73, 98, 72, 97), 
				new Rect(146, 98, 72, 97),
				new Rect(219, 0, 72, 97), 
				new Rect(292, 0, 72, 97),
				new Rect(219, 98, 72, 97), 
				new Rect(365, 0, 72, 97),
				new Rect(292, 98, 72, 97) 
		};
		
		jumping = new Rect[] 
		{
				new Rect(438, 93, 67, 94),
				new Rect(438, 93, 67, 94),
				new Rect(438, 93, 67, 94), 
				new Rect(438, 93, 67, 94),
				new Rect(438, 93, 67, 94), 
				new Rect(438, 93, 67, 94),
				new Rect(438, 93, 67, 94), 
				new Rect(438, 93, 67, 94),
				new Rect(438, 93, 67, 94),
				new Rect(438, 93, 67, 94),
				new Rect(438, 93, 67, 94) 
		};
		
		ducking = new Rect[] 
		{
				new Rect(365, 98, 69, 71),
				new Rect(365, 98, 69, 71),
				new Rect(365, 98, 69, 71),
				new Rect(365, 98, 69, 71),
				new Rect(365, 98, 69, 71),
				new Rect(365, 98, 69, 71),
				new Rect(365, 98, 69, 71),
				new Rect(365, 98, 69, 71),
				new Rect(365, 98, 69, 71),
				new Rect(365, 98, 69, 71),
				new Rect(365, 98, 69, 71)
		};
		
		standing = new Rect[] 
		{
				new Rect(67, 196, 66, 92),
				new Rect(67, 196, 66, 92),
				new Rect(67, 196, 66, 92),
				new Rect(67, 196, 66, 92),
				new Rect(67, 196, 66, 92),
				new Rect(67, 196, 66, 92),
				new Rect(67, 196, 66, 92),
				new Rect(67, 196, 66, 92),
				new Rect(67, 196, 66, 92),
				new Rect(67, 196, 66, 92),
				new Rect(67, 196, 66, 92)
		};
		
		frames = new Rect[][]{
			jumping, 	// NORTH
			walking,	// EAST
			ducking,	// SOUTH
			walking,	// WEST
			standing,	// STAND
			standing,	// LOOKLEFT
			standing,	// LOOKRIGHT
			jumping
		};

		direction = new Point[] 
		{ 
			new Point(1, 0), // NORTH // use 1 on X value so the frame matrix wont crash
			new Point(1, 0), // EAST
			new Point(1, 0), // SOUTH
			new Point(-1, 0),
			new Point(1,0),
			new Point(-1,0),
			new Point(1,0),
			new Point(1,0)
		};

		state = State.STANDING;
		
		currentFrame = 0;
		frameWidth = frames[state.ordinal()][currentFrame].right;
		frameHeight = frames[state.ordinal()][currentFrame].bottom;
		nFrames = frames[0].length;

		xPos = 0;
		
		
		yPos = (10 * 70) - frameHeight; // Converts row back to pixels
		

		xVel = 2;		
		yVel = -15;
	}

	public void check() {			
	}
	
	private void makeDecision(){
		switch (rGen.nextInt(4)) {				// Make a choice
		case 0:	
			state = State.LOOK_LEFT;			// Do I want to look left
			break;
		case 1:		
			state = State.LOOK_RIGHT;			// Do I want to look right
			break;
		case 2:		
			// Keeps me facing the same direction I am standing
			direction[State.NORTH.ordinal()].x = direction[state.ordinal()].x; 
			startJumpYPos = yPos;
			state = State.NORTH;				// Do I want to jump
			break;
		case 3:		
			// Keeps me facing the same direction I am standing
			direction[State.SOUTH.ordinal()].x = direction[state.ordinal()].x;
			state = State.SOUTH;				// Do I want to crouch
			break;
		}
	}
	
	private void updateAction(){
		switch (state) {
		case EAST:
//			if(tileMap.isClimbable(xPos / 70, yPos / 70) && Constants.touchLocation.y < yPos){
//				Constants.touchLocation = null;
//				isClimbing = true;
//				state = State.CLIMB;
//			}
//			else 
			if(xPos > Constants.touchLocation.x){
				//xPos = (int) Constants.touchLocation.x;
				
				Constants.touchLocation = null;
				
				state = State.STANDING;
			}
			else if(Constants.touchLocation.x < xPos){
				state = State.WEST;
			}
//			if(rGen.nextInt(100)== 30){
//				direction[State.STANDING.ordinal()].x = direction[state.ordinal()].x;
//				state = State.STANDING;
//			}else if(xPos + frameWidth > Constants.screenSize.x){
//				state = State.WEST;
//			}
			
			break;
		case NORTH:
			if(isJumping == false){
				state = State.STANDING;
			}
			break;
		case SOUTH:
			if(Constants.touchLocation != null){
				if(Constants.touchLocation.x > xPos) state = State.EAST;
				if(Constants.touchLocation.x < xPos) state = State.WEST;
			}
			else if(time > 100){				
				state = State.STANDING;
				time = 0;
			}
			break;
		case WEST:
			if(xPos < Constants.touchLocation.x){
				//xPos = (int) Constants.touchLocation.x;	
				
				Constants.touchLocation = null;
				
				state = State.STANDING;				
			}
			else if(Constants.touchLocation.x > xPos){
				state = State.EAST;
			}
//			if(rGen.nextInt(100)== 30){					// While I am walking I might want to stop
//				direction[State.STANDING.ordinal()].x = direction[state.ordinal()].x; // Keeps me facing the same direction when I stop
//				state = State.STANDING;					// I am now standing
//			}else if(xPos < 0){							// I may hit a wall and I need to walk the opposite way
//				state = State.EAST;						// I am now walking the other way
//			}
			break;
		case STANDING:			
//			if(tileMap.isClimbable(xPos / 70, yPos / 70) && Constants.touchLocation.y < yPos){
//				Constants.touchLocation = null;
//				isClimbing = true;
//				state = State.CLIMB;
//			}
//			else 
			
			if(Constants.touchLocation != null){
				if(Constants.touchLocation.y < yPos) state = State.CLIMB;
				if(Constants.touchLocation.x > xPos) state = State.EAST;
				if(Constants.touchLocation.x < xPos) state = State.WEST;
			}
			else if(time > rGen.nextInt(200 - 50) + 50){		// Wait for a little bit	
				makeDecision();							// Make a decision	
				time = 0;								// reset wait time for next state
			}
			break;	
		case LOOK_LEFT:
			if(Constants.touchLocation != null){
				if(Constants.touchLocation.x > xPos) state = State.EAST;
				if(Constants.touchLocation.x < xPos) state = State.WEST;
			}
			else if(time > rGen.nextInt(200 - 100) + 100){	// Wait for a little bit
				//state = State.WEST;						// Lets start walking
				time = 0;								// resets wait time for next state
			}
			else if(rGen.nextInt(100) == 0){			// While i am waiting I might want to look another direction
				state = State.LOOK_RIGHT;				// I want to look right
				time = 0;								// resets wait time for next state
			}			
			break;
		case LOOK_RIGHT:
			if(Constants.touchLocation != null){
				if(Constants.touchLocation.x > xPos) state = State.EAST;
				if(Constants.touchLocation.x < xPos) state = State.WEST;
			}
			else if(time > rGen.nextInt(200 - 100) + 100){	// Wait for a little bit
				//state = State.EAST;						// Lets start walking
				time = 0;								// resets wait time for next state
			}
			else if(rGen.nextInt(100) == 0){			// While i am waiting I might want to look another direction
				state = State.LOOK_LEFT;				// I want to look right
				time = 0;								// resets wait time for next state
			}
			break;
		case CLIMB:
			if(isClimbing == false || !tileMap.isClimbable(xPos / 70, yPos / 70) || yPos < Constants.touchLocation.y){
				state = State.STANDING;
			}
		}
	}
	int row = 0;
	private void performAction(){
		switch (state) {
		case EAST:
			move();
			break;
		case NORTH:
			jump();
			break;
		case SOUTH:
			//yPos = tileMap.getTileMapBounds().y - frames[state.ordinal()][currentFrame].bottom - 70;
			row = (yPos + (frameHeight-10)) / 70; 
			
			yPos = ((row+1) * 70) - frameHeight;
			
			time++;
			break;
		case WEST:
			move();
			break;
		case STANDING:	
			row = (yPos + (frameHeight-10)) / 70; 
			
			yPos = ((row+1) * 70) - frameHeight;
			//yPos = tileMap.getTileMapBounds().y - frames[state.ordinal()][currentFrame].bottom - 70;
			time++;
			break;	
		case LOOK_LEFT:
			time++;
			break;
		case LOOK_RIGHT:
			time++;
			break;	
		case CLIMB:
			yPos -= SPEED * 2;
		}		
	}

	private void move() {
		xPos += (SPEED * xVel) * direction[state.ordinal()].x;
		
		// clamp yPos
		row = (yPos + (frameHeight-10)) / 70; 
		
		yPos = ((row+1) * 70) - frameHeight;
		//yPos = tileMap.getTileMapBounds().y - frames[state.ordinal()][currentFrame].bottom - 70;
	}

	
	
	private void jump(){			
				
		isJumping = true;
				 		
		yPos += yVel;

		yVel += 4;	
						
		if(yPos > startJumpYPos){
						
			yPos = startJumpYPos;
			
			startJumpYPos = 0;
			
			yVel = -15;
			
			isJumping = false;
		}
	}
	
	public void update() {
		currentFrame = ++currentFrame % nFrames;
		
		// AI
		updateAction();
		performAction();
	}
	Paint p = new Paint();
	private Bitmap spriteBitmap() {
		int srcX = frames[state.ordinal()][currentFrame].left;
		int srcY = frames[state.ordinal()][currentFrame].top;
		
		frameWidth = frames[state.ordinal()][currentFrame].right;
		frameHeight = frames[state.ordinal()][currentFrame].bottom;
				
		matrix.setScale(direction[state.ordinal()].x, 1); // X, Y
				
		return Bitmap.createBitmap(spriteSheet, srcX, srcY,  frameWidth, frameHeight, matrix, false);
	}

	public void draw(Canvas canvas) {
		
		canvas.drawBitmap(spriteBitmap(), xPos, yPos, null);
		
		p.setStyle(Paint.Style.STROKE);
		p.setColor(Color.CYAN);
		p.setTextSize(50);
		
		
		canvas.drawText(Integer.toString(row), 300, 50, p);
		canvas.drawText(Integer.toString(startJumpYPos), 400, 50, p);
		
		int y = (yPos + (frameHeight - 10));// / 70) * 70;
		canvas.drawRect(xPos, y, xPos + 2, y + 2, p);
		//canvas.drawRect(xPos, (yPos + frameHeight) - 70, xPos + frameWidth, ((yPos+frameHeight)-70) + 70, p);

	}
}
