package com.thenewboston.adam;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class HandleXML extends DefaultHandler {

	XMLDataCollected info = new XMLDataCollected();
	
	
	
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		
		if(localName.equalsIgnoreCase("dir")){
			String name = attributes.getValue("name");
			
			
		}
	}
}
