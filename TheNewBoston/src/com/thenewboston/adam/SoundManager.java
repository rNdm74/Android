package com.thenewboston.adam;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;

public class SoundManager extends Activity implements OnTouchListener, OnLongClickListener{

	SoundPool sp;
	MediaPlayer mp;
	
	int collect = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		View view = new View(this);
		
		view.setOnTouchListener(this);
		view.setOnLongClickListener(this);
		
		setContentView(view);
		
		sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
		collect = sp.load(this, R.raw.hitsound, 1);
		
		mp = MediaPlayer.create(this, R.raw.gayremix_loop4);	
		
	}

	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {
		// TODO Auto-generated method stub
		if(collect != 0) sp.play(collect, 1, 1, 0, 0, 1);
		return false;
	}

	@Override
	public boolean onLongClick(View v) {
		// TODO Auto-generated method stub
		mp.start();
		return false;
	}
	
	

}
