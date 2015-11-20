package com.manic.game;


import java.util.Arrays;
import java.util.HashMap;

/**
 * 
 * @author gabe
 *
 * @param <T>
 * 
 * What this does is makes a generic "animation"
 * so I could make a string of sprites and a string of hitboxes
 * 
 * 
 * 
 */



public class ObjectTimeline<T> implements Cloneable{

	//T[] objs;
	//int cur_obj;
	
	private HashMap < Integer , T > objs;
	private int cur_obj;
	private int cur_frame;
	private int total_length;
	
	private float time;
	private float delay;
	
	
	
	@SuppressWarnings("unchecked")
	public ObjectTimeline ( HashMap < Integer , T > hsh , int len , float dly )
	{
		
		objs = (HashMap<Integer, T>) hsh.clone();

		
		//This will cause bugs if 
		cur_obj = 0;
		
		time = 0;
		
		delay = dly;
		
		total_length = len;
		
	}
	
	public void update ( Float increment )
	{
		
		if ( delay <= 0 )
			return;
		
		time += increment;
		
		while ( time >= delay )
		{
			
			time -= delay;
			
			step();
			
		}
		
		
	}
	
	
	public void update ( Float increment , int index )
	{
		
		if ( delay <= 0 )
			return;
		
		time += increment;
		
		while ( time >= delay )
		{
			
			time -= delay;
			
			setCurrentIndex ( index );
			
		}
		
	}
	
	
	
	private void step ()
	{
		
		++cur_frame;
	
		if ( cur_frame == total_length )
		{
			
			cur_frame = 0;
			
		}
		
		
		if ( objs.containsKey(cur_frame))
			cur_obj = cur_frame;
		
		
	}
	
	
	public T getCurrentObj ()
	{
		
		return objs.get( cur_obj ) ;
		
	}
	
	private void setCurrentIndex ( int index )
	{
		
		cur_frame = index;
		
		update_cur_obj();
		
	}
	
	
	private void update_cur_obj ()
	{
		
		Object[] temparr;
		
		int keyset[];
		
		temparr = objs.keySet().toArray();
		
		
		keyset = new int [ temparr.length ];
		
		
		
		for ( int i = 0 ; i < temparr.length ; ++i )
			keyset [ i ] = (Integer) temparr [ i ];
		
		
		Arrays.sort ( keyset );
		
		
		cur_obj = searchForObjIndex ( keyset );
		
		
		
	}
	
	
	//Searches for the numbers that is just 
	//below the cur_frame
	//I had to write my own algorithm
	private int searchForObjIndex ( int [] arr )
	{
		
		
		int [] l , r;
		
		
		
		if ( arr.length == 1 )
			return arr [ 0 ];
		
		
		int halfway = arr.length / 2;
		
		int halfwayVal = arr [ halfway ];
		
		
		if ( cur_frame < halfwayVal )
		{
			
			l = new int [ halfway ];
			
			for ( int i = 0 ; i < l.length ; ++i )
			{
				
				l [ i ] = arr [ i ];
				
				
				
			}
			
			return searchForObjIndex ( l );
			
			
		}
		else // >=
		{
			
			r = new int [ arr.length - halfway ];
			
			for ( int i = 0 ; i < r.length ; ++i )
			{
				
				r [ i ] = arr [ i + halfway ];
				
			}
			
			return searchForObjIndex ( r );
			
		}
		
	}

	
	//Cloner
	public ObjectTimeline<T> clone(){
		
		ObjectTimeline<T> cl = new ObjectTimeline<T>( objs , total_length , delay);
		
		return cl;
		
	}
	
	
}
	
	
	

