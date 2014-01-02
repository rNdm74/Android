package com.thenewboston.adam;

import android.app.Activity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SlidingDrawer;

@SuppressWarnings("deprecation")
public class Slider extends Activity implements OnClickListener, OnCheckedChangeListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.slider);	
		
		// Bridge to XML
		Button handle = (Button) findViewById(R.id.handle);
		handle.setOnClickListener(this);
		
		// Bridge to XML
		CheckBox checkBox = (CheckBox) findViewById(R.id.cbSlider);
		checkBox.setOnCheckedChangeListener(this);	
		
		@SuppressWarnings("deprecation")
		SlidingDrawer sd = (SlidingDrawer) findViewById(R.id.slidingDrawer1);
		
	}

	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.slidingDrawer1);
				
	}

	@Override
	public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				
	}
}
