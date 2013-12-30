package com.dotdat.druglord;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Dump extends Activity implements OnClickListener {

	private TextView name;
	private TextView qty;
	private TextView price;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dump);

		name = (TextView) Constants.selectInventoryItem.findViewById(R.id.NAME);
		qty = (TextView) Constants.selectInventoryItem.findViewById(R.id.QTY);
		price = (TextView) Constants.selectInventoryItem
				.findViewById(R.id.PRICE);

		String sellMessage = "You have " + qty.getText().toString() + " units of " + name.getText().toString();
		
		TextView title = (TextView) findViewById(R.id.tDumpTitle);
		title.setText(title.getText().toString() + " "
				+ name.getText().toString());

		TextView message = (TextView) findViewById(R.id.tDumpMessage);
		message.setText(sellMessage);

		EditText sellQty = (EditText) findViewById(R.id.eDumpQuantity);
		sellQty.setText(qty.getText().toString());

		Button confirm = (Button) findViewById(R.id.bDumpConfirm);
		confirm.setOnClickListener(this);
		Button cancel = (Button) findViewById(R.id.bDumpCancel);
		cancel.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bDumpConfirm:
			finish();
			break;
		case R.id.bDumpCancel:
			finish();
			break;
		}
	}
}
