package com.manic.game.exceptions;

@SuppressWarnings("serial")
public class InvalidXMLException extends Exception{

	
	private static final int UNSPECIFIED = -1;
	private static final int INVALID_TAG = 0;
	private static final int MISSING_TAG = 1;
	private static final int XML_EMPTY = 2;
	
	
	private int err_no;
	
	String[] strings;
	
	
	
	
	//Constructors
	public InvalidXMLException ()
	{
		
		//TODO: Do stuff
		
		err_no = UNSPECIFIED;
		
	}
	
	
	
	
	
	
	public InvalidXMLException setXMLEmpty (){
	
		err_no = XML_EMPTY;
		
		return this;
		
		
	}
	
	public InvalidXMLException setMissingTag ( String expected )
	{
	
		//TODO: Do stuff
		
		err_no = MISSING_TAG;
		
		strings = new String[1];
		
		strings[0] = expected;
		
		
		
		return this;
		
		
	}
	
	public InvalidXMLException setInvalidTag ( String expected , String received )
	{
		
		//TODO: Do stuff
		
		err_no = INVALID_TAG;
		
		strings = new String[2];
		
		strings[0] = expected;
		
		strings[1] = received;
		
		
		return this;
		
	}
	
	
	@Override
	public String getMessage()
	{
		
		
		if ( err_no == INVALID_TAG )
		{
			
			return "The parser expected \"" + strings[0]
					+ "\" but received \"" + strings[1] + "\"";
			
		} 
		else if ( err_no == MISSING_TAG )
		{
			
			return "The parser expected at least one \"" + strings[0] + "\"";
					
		} 
		else if ( err_no == XML_EMPTY )
		{
			
			return "There doesn't seem to be anything to parse";
			
		} 
		else //UNSPECIFIED
		{
			
			return "InvalidXMLException: Unspecified error";
			
		}
		
	}
	
	
}
