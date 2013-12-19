package com.dotdat.druglord;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Sell extends Activity implements OnClickListener {

	private TextView name;
	private TextView qty;
	private TextView price;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sell);

		name = (TextView) Constants.selectInventoryItem.findViewById(R.id.NAME);
		qty = (TextView) Constants.selectInventoryItem.findViewById(R.id.QTY);
		price = (TextView) Constants.selectInventoryItem
				.findViewById(R.id.PRICE);
		
		int profit_loss = 0;
		int newDrugPrice = 0;
		int myPrice = Integer.parseInt(price.getText().toString());
		
		for (int i = 0; i < Constants.currentCity.length(); i++) {
			Drug drug = Constants.currentCity.getDrug(i);
			
			if(drug.getName().equalsIgnoreCase(name.getText().toString())){
				newDrugPrice = drug.getPrice();				
				profit_loss = newDrugPrice - myPrice;				
				break;
			}
		}
		
		
		

		String sellMessage = name.getText().toString()
				+ " is currently being bought for $" + Integer.toString(newDrugPrice)
				+ " per unit.  You have " + qty.getText().toString()
				+ " units to sell for a "+ ((profit_loss > 0) ? "profit" : "loss") +" of $"+ profit_loss +" per unit.";

		TextView title = (TextView) findViewById(R.id.tSellTitle);
		title.setText(title.getText().toString() + " " + name.getText().toString());

		TextView message = (TextView) findViewById(R.id.tSellMessage);
		message.setText(sellMessage);

		EditText sellQty = (EditText) findViewById(R.id.eSellQuantity);
		sellQty.setText(qty.getText().toString());

		Button confirm = (Button) findViewById(R.id.bSellConfirm);
		confirm.setOnClickListener(this);
		Button cancel = (Button) findViewById(R.id.bSellCancel);
		cancel.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bSellConfirm:
			confirmSell();			
			finish();
			break;
		case R.id.bSellCancel:
			finish();
			break;
		}
	}

	private void confirmSell() {

		Constants.updateMarketListSell(name.getText().toString(), Integer.parseInt(qty.getText().toString()));

		Constants.inventorylist.remove(Constants.selectedPosition);
		Constants.selectedPosition = -1;
		Constants.selectInventoryItem = null;

		Constants.marketView.invalidateViews();
		Constants.inventoryView.invalidateViews();
		Constants.inventoryView.clearChoices();
		Constants.inventoryView.requestLayout();
	}
}
