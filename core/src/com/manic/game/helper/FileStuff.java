package com.manic.game.helper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;



public class FileStuff {

	public static String fileToString ( String name ) throws IOException
	{
		
		BufferedReader fin
				= new BufferedReader ( new FileReader (name) );
		
		char c;
		StringBuilder outb = new StringBuilder();
		
		
		while ( ( c = (char)fin.read() ) != 0xFFFF )
			outb.append( c );
		
		fin.close();
		
		return new String ( outb );
		
	}
	
	
	public static void writeToFile ( String text , String ofile ) throws IOException
	{
		
		PrintWriter fout =
			new PrintWriter ( new BufferedWriter ( new FileWriter ( ofile )));
		
		fout.print ( text );
		
		fout.close();
		
	}
	
	

}
