package com.dotdat.druglord;

import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Buy extends Activity implements OnClickListener {

	private EditText quantity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.buy);

		TextView name = (TextView) Constants.selectMarketItem
				.findViewById(R.id.NAME);
		TextView qty = (TextView) Constants.selectMarketItem
				.findViewById(R.id.QTY);
		TextView price = (TextView) Constants.selectMarketItem
				.findViewById(R.id.PRICE);

		String buyMessage = name.getText().toString()
				+ " is currently selling for $" + price.getText().toString()
				+ " per unit.  With your available funds, you can buy a unit.";

		TextView title = (TextView) findViewById(R.id.tBuyTitle);
		title.setText(title.getText().toString() + " " + name.getText().toString());

		TextView message = (TextView) findViewById(R.id.tBuyMessage);
		message.setText(buyMessage);

		quantity = (EditText) findViewById(R.id.eBuyQuantity);
		quantity.setText(qty.getText().toString());

		Button confirm = (Button) findViewById(R.id.bBuyConfirm);
		confirm.setOnClickListener(this);
		Button cancel = (Button) findViewById(R.id.bBuyCancel);
		cancel.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bBuyConfirm:
			confirmBuy();
			Constants.marketView.clearChoices();
			Constants.marketView.requestLayout();
			finish();
			break;
		case R.id.bBuyCancel:
			finish();
			break;
		}
	}

	private void confirmBuy() {
		TextView name = (TextView) Constants.selectMarketItem
				.findViewById(R.id.NAME);
		TextView qty = (TextView) Constants.selectMarketItem
				.findViewById(R.id.QTY);
		TextView price = (TextView) Constants.selectMarketItem
				.findViewById(R.id.PRICE);

		Constants.inventoryMap = new HashMap<String, String>();
		Constants.inventoryMap.put("Name", name.getText().toString());
		Constants.inventoryMap.put("Qty", qty.getText().toString());
		Constants.inventoryMap.put("Price", price.getText().toString());
		Constants.inventorylist.add(Constants.inventoryMap);

		int qtyValue = Integer.parseInt(qty.getText().toString());
		int quantityValue = Integer.parseInt(quantity.getText().toString());
		
		// Checks if you made a profit or loss from the items you bought
		int result = qtyValue - quantityValue;
		// qty.setText(Integer.toString(result));
		
		Constants.currentCash += result;

		Constants.updateMarketListBuy(name.getText().toString(), result);
		Constants.selectMarketItem = null;

		Constants.marketView.invalidateViews();
		Constants.inventoryView.invalidateViews();
	}
}
