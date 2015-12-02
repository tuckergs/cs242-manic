package com.manic.game.moves;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.manic.game.FixtureDestroyer;



public class HitboxGroup {

	private HashMap< String , Hitbox > hitboxes;
	
	
	
	public HitboxGroup() {
		
		hitboxes = new HashMap < String , Hitbox >();

	}
	
	
	public void add ( Hitbox hbox )
	{
		
		hitboxes.put( hbox.getHitboxID(), hbox );
		
	}

	/*
	 * This is commented out as it can cause some problems with bodies
	public void add ( Body body , Vector2 coordinates , Vector2 dimensions , HitboxType type , 
						String hboxUserData , float damage , float hitstun )
	{
		
		hitboxes.put( hboxUserData , new Hitbox ( body , coordinates , dimensions , type ,
													hboxUserData , damage , hitstun));
		
	}
	*/
	
	public Hitbox get( String k )
	{
		return hitboxes.get(k);
	}
	
	
	public void removeAllHitboxes ( FixtureDestroyer fd )
	{
		
		Set<String> keys = hitboxes.keySet();
		
		for ( Iterator<String> itr = keys.iterator() ; itr.hasNext() ; )
		{
			
			String k = itr.next();
			
			removeHitbox ( k , fd);
			
		}
		
	}
	
	
	public void removeHitbox ( String k , FixtureDestroyer fd )
	{
		
		hitboxes.get(k).destroy( fd );
		
	}
	
}
