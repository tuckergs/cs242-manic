package com.manic.game.moves;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;



public class HitboxGroup {

	private HashMap< String , Hitbox > hitboxes;
	
	
	
	public HitboxGroup() {
		
		hitboxes = new HashMap < String , Hitbox >();

	}
	
	
	


	public void add ( String k , Hitbox h )
	{
		
		hitboxes.put( k , h );
		
	}
	
	public Hitbox get( String k )
	{
		return hitboxes.get(k);
	}
	
	
	public void removeAllHitboxes ()
	{
		
		Set<String> keys = hitboxes.keySet();
		
		for ( Iterator<String> itr = keys.iterator() ; itr.hasNext() ; )
		{
			
			String k = itr.next();
			
			removeHitbox ( k );
			
		}
		
	}
	
	
	public void removeHitbox ( String k )
	{
		
		hitboxes.get(k).destroy();
		
	}
	
}
