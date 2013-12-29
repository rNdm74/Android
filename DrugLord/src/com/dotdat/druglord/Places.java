package com.dotdat.druglord;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TabHost;

public class Places extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.places);
		
		TabHost tabs = (TabHost) findViewById(R.id.tabhostplaces); 
		tabs.setup(); 
		
		TabHost.TabSpec spec = tabs.newTabSpec("tag1"); 
		spec.setContent(R.id.Finances); 
		spec.setIndicator("Finances"); 
		tabs.addTab(spec); 
		
		spec = tabs.newTabSpec("tag2"); 
		spec.setContent(R.id.Shopping); 
		spec.setIndicator("Shopping"); 
		tabs.addTab(spec); 
		
		spec = tabs.newTabSpec("tag3"); 
		spec.setContent(R.id.Hospital); 
		spec.setIndicator("Hospital"); 
		tabs.addTab(spec);
		
		spec = tabs.newTabSpec("tag4"); 
		spec.setContent(R.id.Vault); 
		spec.setIndicator("Vault"); 
		tabs.addTab(spec);
		
		spec = tabs.newTabSpec("tag5"); 
		spec.setContent(R.id.Shipping); 
		spec.setIndicator("Shipping"); 
		tabs.addTab(spec);
		
		tabs.setCurrentTab(0);
	}

	@Override
	public void onClick(View v) {
	}
}
