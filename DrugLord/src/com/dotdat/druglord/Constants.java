package com.dotdat.druglord;

import java.util.ArrayList;
import java.util.HashMap;

import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public final class Constants {
	public static Economy marketEconomy;
	public static City currentCity;
	
	//
	// MARKET
	//
	public static View selectMarketItem;
	public static ListView marketView;
	public static ArrayList<String> marketValues;
	public static ArrayList<HashMap<String, String>> marketList;
	public static HashMap<String, String> marketMap;

	//
	// INVENTORY
	//
	public static View selectInventoryItem;
	public static ListView inventoryView;
	public static ArrayList<String> inventoryValues;
	public static ArrayList<HashMap<String, String>> inventorylist;
	public static HashMap<String, String> inventoryMap;
	public static int selectedPosition;
	
	
	//
	// STATUS
	//
	public static TextView location;
	public static ProgressBar health;
	public static TextView day;
	public static TextView rank;
	public static TextView cash;
	public static TextView bank;
	public static TextView debt;
	
	public static int currentDay;
	public static String currentRank;
	public static int currentCash;
	public static int currentBank;
	public static int currentDebt;

	public static enum Rank {
		WANNABE, SMALL_TIME_OPERATOR, DEALER, BIG_TIME_DEALER, DISTRIBUTER, DRUG_LORD
	}
	
	public static String[] RankNames = new String[]{
		"Wannabe", "Small-time Operator","Dealer","Big-Time Dealer","Distributer","Drug Lord"		
	};
	
	//
	// ECONOMY
	//
	public static enum Drugs {
		COCAINE, CRACK, ECSTACY, HASHISH, HEROIN, ICE, KAT, LSD, MDA, MORPHINE, MUSHROOMS, OPIUM, PCP, PEYOTE, POT, SPECIAL_K, SPEED
	}

	public static String[] DrugNames = new String[] { "Cocaine", "Crack",
			"Ecstacy", "Hashish", "Heroin", "Ice", "Kat", "LSD", "MDA",
			"Morphine", "Mushrooms", "Opium", "PCP", "Peyote", "Pot",
			"Special K", "Speed" };

	public static enum Cities {
		AUSTIN, BEIJING, BOSTON, DETROIT, LONDON, LOS_ANGELES, MIAMI, MOSCOW, NEW_YORK, PARIS, SAN_FRANCISCO, ST_PETERSBURG, SYDNEY, TORONTO, VANCOUVER
	}

	public static String[] CityNames = new String[] { "Austin, USA",
			"Beijing, China", "Boston, USA", "Detroit, USA", "London, England",
			"Los Angeles, USA", "Miama, USA", "Moscow, Russia",
			"New York, USA", "Paris, France", "San Francisco, USA",
			"St Petersburg, Russia", "Sydney, Australia", "Toronto, Canada",
			"Vancouver, Canada" };

	//
	// Methods
	//
	public static void populateMarketList() {
		// Get new market prices
		currentCity = marketEconomy.getCity(Cities.AUSTIN.ordinal());

		for (int i = 0; i < DrugNames.length; i++) {

			Drug drug = currentCity.getDrug(i);

			marketMap = new HashMap<String, String>();

			marketMap.put("Name", drug.getName());
			marketMap.put("Qty", Integer.toString(drug.getQty()));
			marketMap.put("Price", Integer.toString(drug.getPrice()));
			marketList.add(marketMap);
		}
	}

	public static void updateMarketListBuy(String name, int qty) {
		marketList.clear();
		
		// Get new market prices
		currentCity = marketEconomy.getCity(Cities.AUSTIN.ordinal());
		
		for (int i = 0; i < DrugNames.length; i++) {

			Drug drug = currentCity.getDrug(i);

			marketMap = new HashMap<String, String>();

			marketMap.put("Name", drug.getName());
			
			if (drug.getName().equalsIgnoreCase(name)) {				
				//Update in economy
				drug.setQty(qty);
				
				//Update in list				
				marketMap.put("Qty", Integer.toString(qty));
			} else {
				//Update only list as no change to economy
				marketMap.put("Qty", Integer.toString(drug.getQty()));
			}

			marketMap.put("Price", Integer.toString(drug.getPrice()));
			marketList.add(marketMap);
		}
	}
	
	public static void updateMarketListSell(String name, int qty) {
		marketList.clear();
		
		// Get new market prices
		currentCity = marketEconomy.getCity(Cities.AUSTIN.ordinal());
		
		for (int i = 0; i < DrugNames.length; i++) {

			Drug drug = currentCity.getDrug(i);

			marketMap = new HashMap<String, String>();

			marketMap.put("Name", drug.getName());
			
			if (drug.getName().equalsIgnoreCase(name)) {
				int currentQty = drug.getQty();
				
				//Update in economy
				drug.setQty(currentQty + qty);
				//Update in list
				
				marketMap.put("Qty", Integer.toString(currentQty + qty));
			} else {
				//Update only list as no change to economy
				marketMap.put("Qty", Integer.toString(drug.getQty()));
			}

			marketMap.put("Price", Integer.toString(drug.getPrice()));
			marketList.add(marketMap);
		}
	}
}
