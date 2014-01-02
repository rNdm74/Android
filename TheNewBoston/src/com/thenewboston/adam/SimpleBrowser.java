package com.thenewboston.adam;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

public class SimpleBrowser extends Activity implements OnClickListener{

	WebView webview;
	AutoCompleteTextView address;
	Button go, forward, back, refresh, clear;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		// Make application full screen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(
				WindowManager.LayoutParams.FLAG_FULLSCREEN, // Flags
				WindowManager.LayoutParams.FLAG_FULLSCREEN  // Mask
		);
		
		setContentView(R.layout.simplebrowser);
		
		webview = (WebView) findViewById(R.id.webView);
		webview.setWebViewClient(new ViewClient());
		webview.getSettings().setJavaScriptEnabled(true);
		webview.getSettings().setLoadWithOverviewMode(true);
		webview.getSettings().setUseWideViewPort(true);
		
		try{
			webview.loadUrl("http://www.mybringback.com");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		address = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
		
		go = (Button) findViewById(R.id.bGo);		
		forward = (Button) findViewById(R.id.bForward);
		back = (Button) findViewById(R.id.bBack);
		refresh = (Button) findViewById(R.id.bRefresh);
		clear = (Button) findViewById(R.id.bClear);
		
		go.setOnClickListener(this);
		forward.setOnClickListener(this);
		back.setOnClickListener(this);
		refresh.setOnClickListener(this);
		clear.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
			case R.id.bGo:
				goButton();
				break;
			case R.id.bForward:
				forwardButton();
				break;
			case R.id.bBack:
				backButton();
				break;
			case R.id.bRefresh:
				refreshButton();
				break;
			case R.id.bClear:
				clearButton();
				break;
		}		
	}
	
	private void goButton(){
		// Setup for hiding keyboard
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		
		
		String url = address.getText().toString();	
		
		if(!url.contains("http://www."))
			url = "http://www." + url;
					
		try{
			webview.loadUrl(url);			
			address.setText(webview.getUrl());
			imm.hideSoftInputFromWindow(address.getWindowToken(), 0);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
			
	}
	private void forwardButton(){
		if(webview.canGoForward())
			webview.goForward();		
	}
	private void backButton(){
		if(webview.canGoBack())
			webview.goBack();		
	}
	private void refreshButton(){
		webview.reload();		
	}
	private void clearButton(){		
		webview.clearHistory();		
	}
	
	public class ViewClient extends WebViewClient{
		@Override
		public boolean shouldOverrideUrlLoading(WebView v, String url){
			
			v.loadUrl(url);
			
			return true;
		}
	}
}


