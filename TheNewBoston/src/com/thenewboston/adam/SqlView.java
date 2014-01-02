package com.thenewboston.adam;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;


public class SqlView extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sqlview);
		
		TextView sqlInfo = (TextView) findViewById(R.id.tvSQLInfo);
		Database info = new Database(this);
		info.open();
		String data = info.getData();
		sqlInfo.setText(data);
	}
}
