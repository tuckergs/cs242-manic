package com.manic.game;



import java.util.LinkedList;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;


/**
 * 
 * @class BodyDestroyer
 * 
 * @brief An object that destroys a bunch of bodies at a later time
 * 
 * This lets you queue bodies to be destroyed
 * This is used in MyContactListener, since you can't destroy bodies
 * when collision stuff is still being calculated
 * 
 * @author Gabe Tucker
 * 
 * @contact gst06@roadrunner.com
 *
 */
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
		
		Body b;
		
		while ( !bodies.isEmpty() )
		{
			
			b = bodies.removeFirst();
			
			
			//Destroy fixtures
			for (Fixture f : b.getFixtureList()) {
				
				b.destroyFixture(f);
				
			}
			
			//Destroy bodies
			world.destroyBody(b);
			
			
		}
		
	}
	
	
}
