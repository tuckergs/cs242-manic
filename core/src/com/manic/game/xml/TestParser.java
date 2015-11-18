package com.manic.game.xml;

import com.badlogic.gdx.utils.XmlReader.Element;

public class TestParser extends XMLParser {

	@Override
	public void parse(String xml) {
		
		cur = document.parse(xml);
		printme ( cur , 0 );

	}

	protected void printme ( Element parent , int indent )
	{
		
		//Make the padding string
		StringBuilder tabstringbuild = new StringBuilder();
		
		for ( int i = 1 ; i <= indent ; ++i )
		{
			
			tabstringbuild.append("--");
			
		}
		
		String tabstring = new String ( tabstringbuild );
		
		
		System.out.println ( tabstring + parent.getName() );
		
		String txt = parent.getText();
		if ( txt != null )
			System.out.println( tabstring + ">" + txt );
		
		
		int childcount = parent.getChildCount();
		
		for ( int i = 0 ; i < childcount ; ++i )
		{
			
			printme ( parent.getChild(i) , indent + 1 );
			
		}
		
		
		
	}
	
}
