package com.manic.game;

import java.lang.reflect.Array;

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



public class ObjectTimeline<T> {

	T[] objs;
	int cur_obj;
	
	float time;
	float delay;
	
	
	@SuppressWarnings("unchecked")
	public ObjectTimeline ( T[] arr , float dly )
	{
		
		objs = (T[]) new Object [ arr.length ];

		for ( int i = 0 ; i < arr.length ; ++i )
		{
			
			objs [ i ] = (T) new Object ();
			
		}
		
		cur_obj = 0;
		
		time = 0;
		
		delay = dly;
		
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
		
		++cur_obj;
	
		if ( cur_obj == objs.length )
		{
			
			cur_obj = 0;
			
		}
		
	}
	
	
	public T getCurrentObj ()
	{
		
		return objs [ cur_obj ];
		
	}
	
	private void setCurrentIndex ( int index )
	{
		
		cur_obj = index;
		
	}
	
	
}
