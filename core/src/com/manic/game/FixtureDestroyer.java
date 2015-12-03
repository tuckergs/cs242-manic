package com.manic.game;



import java.util.LinkedList;

//import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
//import com.badlogic.gdx.physics.box2d.World;

public class FixtureDestroyer {

	LinkedList<Fixture> fixtures;
	
	
	public FixtureDestroyer()
	{
		
		fixtures = new LinkedList<Fixture>();
		
		
	}
	
	
	public void add ( Fixture f )
	{
		
		fixtures.addLast(f);
		
	}
	
	public void destroyAll()
	{
		
		Fixture f;
		
		while ( !fixtures.isEmpty() )
		{
			
			f = fixtures.removeFirst();
			
			//TODO: Do destructiony things
			
			f.getBody().destroyFixture(f);
			
			
		}
		
	}
	
	
}
