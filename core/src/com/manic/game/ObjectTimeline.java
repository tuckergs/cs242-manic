package com.manic.game;


import java.util.Arrays;
import java.util.HashMap;

/**
 * @class ObjectTimeline
 *
 * @author Gabe Tucker
 *
 * @brief This represents a "generic animation".
 * 
 * A generic animation is a map of objects that can only return
 * objects based on what the generic animation's current
 * frame is.
 * 
 * It doesn't necessarily have an object mapped to every frame;
 * rather, it has keyframes, which are frames which
 * have objects attached to them.
 * 
 * You can tell it whether it returns the last object encountered
 * or whether it returns null when the current frame is not
 * a keyframe. 
 *
 * You can update it to change the current frame and to change 
 * what the current object is.
 *
 * You can specify if it loops after the end of the timeline has been
 * reached
 *
 * Look at Block Bunny's Animation (by Foreign Guy Mike) class for the 
 * basis of this code.
 *
 *
 * @version 1.0
 * 
 * @contact tuckergs@clarkson.edu
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
	
	
	
	
	private void init ( HashMap < Integer , T > hsh , int len , float dly , 
						boolean loop , boolean get_on_no_keyframe )
	{
		
		objs = (HashMap<Integer, T>) hsh;

		
		total_length = len;
		
		
		///This sets cur_obj to -1 because we assume update is called before getCurrentObj
		cur_obj = -1;
		cur_frame = -1;
		
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
	
		///Defaults to looping
		init ( hsh , len , dly , loop , true );
		
	}
	
	public ObjectTimeline ( HashMap < Integer , T > hsh , int len , float dly )
	{
		
		///Defaults to looping and returning the previous object on non-keyframes
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
	
	///An unused updater which also changes the current frame
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
		
		///We don't want to increment if the object
		///is non-looping and if the current frame
		///is the length
		///We do this to give the object the ability to return
		///a certain object if the "animation"
		///is done
		if ( is_looping || cur_frame != total_length )
			++cur_frame;
	
		///We don't want looping if we turned off looping
		if ( is_looping && cur_frame == total_length )
			cur_frame = 0;
		
		
		///Change the current object if the
		///current frame is a keyframe
		if ( objs.containsKey(cur_frame))
			cur_obj = cur_frame;
		
		
	}
	
	
	///This returns an object based on what the current frame is
	public T getCurrentObj ()
	{
		
		///We perform this check so that under certain conditions,
		///it doesn't return an object
		
		if ( total_length == 0 ) return null; 
		if ( !get_on_no_keyframe && cur_frame != cur_obj )  return null;
			
		return objs.get( cur_obj ) ;
		
		
	}
	
	///This function is unused since it's used by the unused updater
	private void setCurrentIndex ( int index )
	{
		
		cur_frame = index;
		
		update_cur_obj();
		
	}
	
	///This function is unused since it's used by the unused updater
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
	
	
	///Searches for the numbers that is just 
	///below the cur_frame
	///I had to write my own algorithm
	///This function is unused since it's used by the unused updater
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
		else /// >=
		{
			
			r = new int [ arr.length - halfway ];
			
			for ( int i = 0 ; i < r.length ; ++i )
			{
				
				r [ i ] = arr [ i + halfway ];
				
			}
			
			return searchForObjIndex ( r );
			
		}
		
	}

	
	///Cloner
	public ObjectTimeline<T> clone(){
		
		ObjectTimeline<T> cl = new ObjectTimeline<T>( objs , total_length , delay , is_looping , get_on_no_keyframe );
		
		return cl;
		
	}
	
	
}
	
	
	

