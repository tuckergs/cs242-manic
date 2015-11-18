package com.manic.game.xml;

import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.manic.game.exceptions.InvalidXMLException;

public abstract class XMLParser {

	protected XmlReader document;
	protected Element cur; //The current node
	
	
	public XMLParser(){
		
		document = new XmlReader();
		
	}
	
	
	public abstract void parse( String xml ) throws InvalidXMLException;
	
	
	
}
