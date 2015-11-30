package com.manic.game;



import java.util.LinkedList;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public class BodyDestroyer {

	LinkedList<Body> bodies;
	
	
	public BodyDestroyer()
	{
		
		bodies = new LinkedList<Body>();
		
		
	}
	
	
	public void add ( Body b )
	{
		
		bodies.addLast(b);
		
	}
	
	public void destroyAll( World world )
	{
		
		Body p;
		
		while ( !bodies.isEmpty() )
		{
			
			p = bodies.removeFirst();
			
			//TODO: Do destructiony things
			
			
		}
		
	}
	
	
}
