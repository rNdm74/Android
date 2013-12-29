package com.dotdat.druglord;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class FlyAway extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.flyaway);

		initializeFlyAway();
	}

	private void initializeFlyAway() {
		Constants.flyAwayView = (ListView) findViewById(R.id.lFlyAway);
		Constants.flyAwayValues = new ArrayList<String>();
		Constants.flyAwaylist = new ArrayList<HashMap<String, String>>();
		Constants.flyAwayMap = new HashMap<String, String>();

		// Populate
		for (int i = 0; i < Constants.CityNames.length; i++) {

			String city = Constants.CityNames[i];

			Constants.flyAwayMap = new HashMap<String, String>();

			Constants.flyAwayMap.put("Name", city);
			Constants.flyAwayMap.put("Qty", "");
			Constants.flyAwayMap.put("Price", "");
			Constants.flyAwaylist.add(Constants.flyAwayMap);
		}

		SimpleAdapter mSchedule = new SimpleAdapter(this, Constants.flyAwaylist,
				R.layout.row, new String[] { "Name", "", "Price" }, new int[] {
						R.id.NAME, R.id.QTY, R.id.PRICE });

		Constants.flyAwayView.setAdapter(mSchedule);

		Constants.flyAwayView.setSelector(R.drawable.marketlist_selector);

		// ListView Item Click Listener
		Constants.marketView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				if (view.equals(Constants.selectMarketItem)) {
					Constants.selectMarketItem = null;
					Constants.marketView.clearChoices();
					Constants.marketView.requestLayout();
				} else {
					Constants.selectMarketItem = view;
				}
			}
		});
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

}
