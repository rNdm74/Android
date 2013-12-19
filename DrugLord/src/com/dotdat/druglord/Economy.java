package com.dotdat.druglord;

import com.dotdat.druglord.Constants.Cities;

public class Economy {
	private City[] cities;

	public Economy() {
		int totalCities = Constants.CityNames.length;
		
		cities = new City[totalCities];
		
		for (int city = 0; city < totalCities; city++) {
			cities[city] = new City(Constants.CityNames[city]);
		}
	}
	
	public City getCity(int city){
		return cities[city];
	}
}
