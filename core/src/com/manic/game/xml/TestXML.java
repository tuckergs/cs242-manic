package com.manic.game.xml;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.manic.game.exceptions.InvalidXMLException;

public class TestXML {

	public static void main(String[] args) {
		
		BufferedReader fin;
		
		try {
		
			XMLParser p = new TestParser();
			
			
			//We want a file :)
			fin = new BufferedReader (
					new FileReader ("src\\com\\manic\\game\\xml\\test.xml"));
		
			
			//Read from file into xml
			StringBuilder xmlBuilder = new StringBuilder();
			
			char c;
			
			while ( (c = (char) fin.read()) != 0xFFFF )
				xmlBuilder.append(c);
			
			fin.close();
			
			String xml = new String ( xmlBuilder );
			
			
			
			//Parse
			p.parse(xml);
			
			
			
			
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
		
			e.printStackTrace();
	
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidXMLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
