package com.thenewboston.adam;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.Paint.Align;
import android.view.View;

public class MyBringBack extends View{
	View v;
	Bitmap t;
	Rect rect;	
	Paint paint, text;
	float xPos, yPos;
	
	Typeface font;
	
	public MyBringBack(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		t = BitmapFactory.decodeResource(getResources(), R.drawable.square);
		
		font = Typeface.createFromAsset(context.getAssets(), "COMICATE.TTF");
		
		rect = new Rect();
		
		text = new Paint();
		text.setARGB(255, 255, 255, 255);
		text.setTextAlign(Align.CENTER);
		text.setTextSize(50);
		text.setTypeface(font);
				
		paint = new Paint();
		paint.setColor(Color.DKGRAY);
		
		xPos = 0;
		yPos = 0;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);	
		
		if(xPos < (canvas.getWidth() - t.getWidth())){
			xPos++;
		}
		
		if(yPos < canvas.getHeight()){
			
		}
		
		// Draw Background
		canvas.drawColor(Color.WHITE);
		
		//
		rect.set(0, canvas.getHeight() - 300, canvas.getWidth(), canvas.getHeight());
		canvas.drawRect(rect, paint);
		
		//
		canvas.drawText("RNDM", canvas.getWidth() / 2, canvas.getHeight() - 200, text);
		
		//
		canvas.drawBitmap(t, xPos, yPos, null);
		
		invalidate();
	}
}
