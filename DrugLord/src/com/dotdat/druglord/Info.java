package com.dotdat.druglord;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TabHost;

public class Info extends Activity implements OnClickListener{

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.info);
		
		TabHost tabs = (TabHost) findViewById(R.id.tabhostinfo); 
		tabs.setup(); 
		
		TabHost.TabSpec spec = tabs.newTabSpec("tag6"); 
		spec.setContent(R.id.Vaults); 
		spec.setIndicator("Vaults"); 
		tabs.addTab(spec); 
		
		spec = tabs.newTabSpec("tag7"); 
		spec.setContent(R.id.WorldDrugPrices); 
		spec.setIndicator("World Drug Prices"); 
		tabs.addTab(spec); 
		
		spec = tabs.newTabSpec("tag8"); 
		spec.setContent(R.id.WorldCities); 
		spec.setIndicator("World Cities"); 
		tabs.addTab(spec);
		
		spec = tabs.newTabSpec("tag9"); 
		spec.setContent(R.id.ShipmentStatus); 
		spec.setIndicator("Shipment Status"); 
		tabs.addTab(spec);
		
		spec = tabs.newTabSpec("tag10"); 
		spec.setContent(R.id.History); 
		spec.setIndicator("History"); 
		tabs.addTab(spec);
		
		tabs.setCurrentTab(0);
		
		//getWindow().setLayout (LayoutParams.WRAP_CONTENT /* width */ , LayoutParams.FILL_PARENT /* height */);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

}
