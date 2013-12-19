package com.dotdat.druglord;

import java.util.Random;

public class City {
	private Drug[] drugMarket;
	private int travelPrice;
	private String name;
	private Random rGen;

	public int getTravelPrice() {
		return travelPrice;
	}

	public void setTravelPrice(int travelPrice) {
		this.travelPrice = travelPrice;
	}

	public String getName() {
		return name;
	}

	public City(String cityName) {
		name = cityName;

		rGen = new Random();
		
		travelPrice = rGen.nextInt(1000);

		int totalDrugs = Constants.DrugNames.length;

		drugMarket = new Drug[totalDrugs];

		for (int drug = 0; drug < totalDrugs; drug++) {
			drugMarket[drug] = new Drug(drug);
		}
	}

	public int length(){
		return Constants.DrugNames.length;
	}
	
	public Drug getDrug(int drug) {
		return drugMarket[drug];
	}
}
