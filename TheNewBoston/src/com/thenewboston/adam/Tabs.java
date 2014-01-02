package com.thenewboston.adam;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class Tabs extends Activity implements OnClickListener{
	TabHost th;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(
				WindowManager.LayoutParams.FLAG_FULLSCREEN, // Flags
				WindowManager.LayoutParams.FLAG_FULLSCREEN  // Mask
		);
		
		
		setContentView(R.layout.tabs);
		
		Button addTab = (Button) findViewById(R.id.bAddTab);
		addTab.setOnClickListener(this);
		
		th = (TabHost) findViewById(R.id.tabhost);
		
		th.setup();
		
		TabSpec specs = th.newTabSpec("tag1");
		specs.setContent(R.id.tab1);
		specs.setIndicator("StopWatch");
		th.addTab(specs);
		specs = th.newTabSpec("tag2");
		specs.setContent(R.id.tab2);
		specs.setIndicator("Tab 2");
		th.addTab(specs);		
		specs = th.newTabSpec("tag3");
		specs.setContent(R.id.tab3);
		specs.setIndicator("Add a Tab");
		th.addTab(specs);
		
	}

	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.bAddTab)
		{
			TabSpec ourSpec = th.newTabSpec("tag1");
			ourSpec.setContent(new TabHost.TabContentFactory() {
				
				@Override
				public View createTabContent(String tag) {					
					TextView text = new TextView(Tabs.this);
					text.setText("This is a new tab!");
					return text;
				}
			});		
			
			ourSpec.setIndicator("New");
			
			th.addTab(ourSpec);
		}		
	}
}
