package com.manic.game;

import java.util.HashMap;

public class MyTest {

	
	public static void main ( String[] args )
	{
		
		
		HashMap < Integer , String > hsh = new HashMap <Integer , String>();
		
		
		for ( int i = 0 ; i < args.length ; ++i )
		{
			
			int n = Integer.parseInt( args [ i ] );
			hsh.put ( n , "" );
			
		}
		
		
		ObjectTimeline<String> objs
			= new ObjectTimeline<String> ( hsh , 841 , 0 );
		
		
		int printme = 0;
		//printme = objs.test(50);
		
		
		System.out.println ( printme );
		
		
		
	}
	
	
}
