package com.dotdat.druglord;

import java.util.Random;

import com.dotdat.druglord.Constants.Cities;
import com.dotdat.druglord.Constants.Drugs;

public class Drug {
	private String name;
	private int qty, price;
	private Cities city;
	private Random rGen;

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public Cities getCity() {
		return city;
	}

	public Drug(int drug) {

		name = getDrugName(drug);
		
		rGen = new Random();

		qty = rGen.nextInt(20);

		price = rGen.nextInt(100000);
	}

	private String getDrugName(int drug) {
		return Constants.DrugNames[drug];
	}

	private int multiplier(Drugs d) {
		switch (d) {
		case COCAINE:
			return 100;
		case CRACK:
			break;
		case ECSTACY:
			break;
		case HASHISH:
			break;
		case HEROIN:
			break;
		case ICE:
			break;
		case KAT:
			break;
		case LSD:
			break;
		case MDA:
			break;
		case MORPHINE:
			break;
		case MUSHROOMS:
			break;
		case OPIUM:
			break;
		case PCP:
			break;
		case PEYOTE:
			break;
		case POT:
			break;
		case SPECIAL_K:
			break;
		case SPEED:
			break;
		}

		return price;
	}

	private int maxValue(Drugs d) {
		return price;

	}
}
