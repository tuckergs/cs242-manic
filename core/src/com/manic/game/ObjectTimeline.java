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

	
	private HashMap < Integer , T > objs;
	private int cur_obj;
	private int cur_frame;
	private int total_length;
	
	private float time;
	private float delay;
	
	private boolean is_looping;
	private boolean get_on_no_keyframe;
	
	
	
	@SuppressWarnings("unchecked")
	private void init ( HashMap < Integer , T > hsh , int len , float dly , 
						boolean loop , boolean get_on_no_keyframe )
	{
		
		objs = (HashMap<Integer, T>) hsh.clone();

		
		total_length = len;
		
		
		//This is set to the length so when it updates before it does stuff, the first
		//frame of "animation" will be hit
		cur_obj = total_length;
		
		time = 0;
		
		delay = dly;
		
		
		is_looping = loop;
		
		this.get_on_no_keyframe = get_on_no_keyframe;
		
	}
	
	public ObjectTimeline ( HashMap < Integer , T > hsh , int len , float dly , 
							boolean loop , boolean get_on_no_keyframe )
	{
	
		init ( hsh , len , dly , loop , get_on_no_keyframe );
		
	}
	
	public ObjectTimeline ( HashMap < Integer , T > hsh , int len , float dly , 
							boolean loop)
	{
	
		init ( hsh , len , dly , loop , true );
		
	}
	
	public ObjectTimeline ( HashMap < Integer , T > hsh , int len , float dly )
	{
		
		//Defaults to looping
		init ( hsh , len , dly , true , true );
		
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
		
		//We don't want to increment if the object
		//is non-looping and if the current frame
		//is the length
		//We do this to give the object the ability to return
		//a certain something if the "animation"
		//is done
		if ( is_looping || cur_frame != total_length )
			++cur_frame;
	
		//We don't want looping if we turned off looping
		if ( is_looping && cur_frame == total_length )
			cur_frame = 0;
		
		
		//Change the current object if the
		//current frame is a keyframe
		//A keyframe is a key in the hashmap
		if ( objs.containsKey(cur_frame))
			cur_obj = cur_frame;
		
		
	}
	
	
	public T getCurrentObj ()
	{
		
		//The reason why I  check it here is because
		//the calling function doesn't need to know what went "wrong"
		//I use "wrong" in quotation marks because often,
		//there's nothing wrong
		//Also, the calling function wouldn't have 
		//to check this condition every time
		
		if ( total_length != 0 
				&& ( get_on_no_keyframe || cur_frame == cur_obj ) )
			return objs.get( cur_obj ) ;
		else
			return null;
		
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
	
	
	

