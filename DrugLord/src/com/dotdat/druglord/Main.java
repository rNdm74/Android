package com.dotdat.druglord;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.dotdat.druglord.Constants.Cities;
import com.dotdat.druglord.Constants.Rank;

public class Main extends Activity implements OnClickListener {

	private NumberFormat formatter;
	private String moneyString;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		Constants.marketView = (ListView) findViewById(R.id.lMarket);
		Constants.inventoryView = (ListView) findViewById(R.id.lInventory);

		
		Constants.marketEconomy = new Economy();
		
		
		
		
		initializeStayHereButton();
		
		initializeStatus();

		initializeMarket();

		initializeInventory();

		initializeBuyButton();

		initializeSellButton();

	}

	private void initializeStayHereButton() {
		Button stayHere = (Button) findViewById(R.id.bStayHere);
		stayHere.setOnClickListener(this);
	}

	private void initializeStatus() {
		
		
		Constants.location = (TextView) findViewById(R.id.tCurrentLocation);
		Constants.currentCity = Constants.marketEconomy.getCity(Cities.AUSTIN.ordinal());
		Constants.location.setText(Constants.currentCity.getName());
				
		Constants.health = (ProgressBar) findViewById(R.id.pbHealth);		
		Constants.health.setMax(100);
		Constants.health.setProgress(100);
		
		Constants.day = (TextView) findViewById(R.id.tCurrentDay);
		Constants.day.setText(Constants.currentDay + "/30");
		
		Constants.rank = (TextView) findViewById(R.id.tCurrentRank);
		Constants.currentRank = Constants.RankNames[Rank.WANNABE.ordinal()];
		Constants.rank.setTag(Constants.currentRank);
		
		formatter = NumberFormat.getCurrencyInstance();
		
		
		Constants.cash = (TextView) findViewById(R.id.tCurrentCash);
		Constants.currentCash = 1990;
		moneyString = formatter.format(Constants.currentCash);
		Constants.cash.setText(moneyString);
		
		Constants.bank = (TextView) findViewById(R.id.tCurrentBank);
		Constants.currentBank = 0;
		moneyString = formatter.format(Constants.currentBank);
		Constants.bank.setText(moneyString);
		
		Constants.debt = (TextView) findViewById(R.id.tCurrentDebt);
		Constants.currentDebt = 1150;
		moneyString = formatter.format(Constants.currentDebt);
		Constants.debt.setText(moneyString);
	}

	private void initializeSellButton() {
		Button sell = (Button) findViewById(R.id.bSell);

		sell.setText("<< Sell");

		sell.setOnClickListener(this);
	}

	private void initializeBuyButton() {
		Button buy = (Button) findViewById(R.id.bBuy);

		buy.setText("Buy >>");

		buy.setOnClickListener(this);
	}

	private void startActivity(String classAddress) {
		try {
			// Creates class from name from classes array
			Class ourClass = Class.forName(classAddress);

			// new intent for starting new class
			Intent newIntent = new Intent(Main.this, ourClass);

			// Start class
			startActivity(newIntent);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void redundant() {
		// places = (Spinner) findViewById(R.id.sPlaces);
		// info = (Spinner) findViewById(R.id.sInfo);
		// flyAway = (Spinner) findViewById(R.id.sFlyAway);
		//
		// ArrayAdapter<CharSequence> placesAdapter = ArrayAdapter
		// .createFromResource(this, R.array.places,
		// android.R.layout.simple_spinner_item);
		//
		// ArrayAdapter<CharSequence> infoAdapter = ArrayAdapter
		// .createFromResource(this, R.array.info,
		// android.R.layout.simple_spinner_item);
		//
		// ArrayAdapter<CharSequence> flyawayAdapter = ArrayAdapter
		// .createFromResource(this, R.array.flyaway,
		// android.R.layout.simple_spinner_item);
		//
		// placesAdapter
		// .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// infoAdapter
		// .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// flyawayAdapter
		// .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		//
		// places.setOnItemSelectedListener(new OnItemSelectedListener() {
		// @Override
		// public void onItemSelected(AdapterView<?> arg0, View arg1,
		// int position, long id) {
		// // items[0] = "One";
		// // selectedItem = items[position];
		// }
		//
		// @Override
		// public void onNothingSelected(AdapterView<?> arg0) {
		// }
		// });
		// places.setAdapter(placesAdapter);
		// info.setAdapter(infoAdapter);
		// flyAway.setAdapter(flyawayAdapter);
	}

	private void initializeInventory() {
		Constants.inventoryValues = new ArrayList<String>();
		Constants.inventorylist = new ArrayList<HashMap<String, String>>();
		Constants.inventoryMap = new HashMap<String, String>();

		SimpleAdapter mSchedule = new SimpleAdapter(this,
				Constants.inventorylist, R.layout.row, new String[] { "Name",
						"Qty", "Price" }, new int[] { R.id.NAME, R.id.QTY,
						R.id.PRICE });

		Constants.inventoryView.setAdapter(mSchedule);

		Constants.inventoryView.setSelector(R.drawable.inventorylist_selector);

		// ListView Item Click Listener
		Constants.inventoryView
				.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {

						if (view.equals(Constants.selectInventoryItem)) {
							Constants.selectedPosition = -1;
							Constants.selectInventoryItem = null;
							Constants.inventoryView.clearChoices();
							Constants.inventoryView.requestLayout();
						} else {
							Constants.selectedPosition = position;
							Constants.selectInventoryItem = view;
						}

					}

				});
	}

	private void initializeMarket() {
		Constants.marketEconomy = new Economy();
		Constants.marketValues = new ArrayList<String>();
		Constants.marketList = new ArrayList<HashMap<String, String>>();
		Constants.marketMap = new HashMap<String, String>();

		Constants.populateMarketList();

		SimpleAdapter mSchedule = new SimpleAdapter(this, Constants.marketList,
				R.layout.row, new String[] { "Name", "Qty", "Price" },
				new int[] { R.id.NAME, R.id.QTY, R.id.PRICE });

		Constants.marketView.setAdapter(mSchedule);

		Constants.marketView.setSelector(R.drawable.marketlist_selector);
		
		

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
		switch (v.getId()) {
		case R.id.bBuy:
			if (Constants.selectMarketItem == null) {
				// Show Alert
				Toast.makeText(getApplicationContext(),
						"You must select something to buy", Toast.LENGTH_SHORT)
						.show();
			} else {
				startActivity("com.dotdat.druglord.Buy");
			}
			break;
		case R.id.bSell:
			if (Constants.selectInventoryItem == null) {
				// Show Alert
				Toast.makeText(getApplicationContext(),
						"You must select something to sell", Toast.LENGTH_SHORT)
						.show();
			} else {
				startActivity("com.dotdat.druglord.Sell");
			}
			break;
		case R.id.bStayHere:
			Constants.day.setText(++Constants.currentDay + "/30");
			Constants.currentDebt += Constants.currentDebt * 0.5;					
			moneyString = formatter.format(Constants.currentDebt);
			Constants.debt.setText(moneyString);
			initializeMarket();
			Constants.marketView.invalidateViews();
			break;
		}

	}
}
