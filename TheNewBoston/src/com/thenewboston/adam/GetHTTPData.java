package com.thenewboston.adam;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class GetHTTPData {
	
	public String getData() throws Exception{
		BufferedReader reader = null;
		
		String data = null;
		
		try{
			HttpClient client = new DefaultHttpClient();	
			
			// Specify address you want to get the data
			URI website = new URI("http://www.google.com");	
			
			HttpGet request = new HttpGet();
			request.setURI(website);
			
			// Starts above and puts all information in response variable
			HttpResponse response = client.execute(request);
			
			// Puts the data from the website so we can work with it
			reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			
			StringBuffer buffer = new StringBuffer("");
			
			String line = "";
			
			String newLine = System.getProperty("line.seperator");
			
			while((line = reader.readLine()) != null){
				buffer.append(line + newLine);				
			}
			
			reader.close();
			
			data = buffer.toString();
			
			return data;
			
		}
		finally{
			if(reader != null){
				try {
					reader.close();	
					return data;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
