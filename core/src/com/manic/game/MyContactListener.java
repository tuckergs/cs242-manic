package com.manic.game;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.manic.game.entities.HitboxEntity;
import com.manic.game.entities.Character;
import com.manic.game.moves.Hitbox;
import com.manic.game.moves.HitboxType;
import com.manic.game.states.GameState;
import com.manic.game.states.Start;


public class MyContactListener implements ContactListener {
	
	private boolean isOnGround;
	
	private Start state;
	private BodyDestroyer bodyDestroyer;
	private FixtureDestroyer fixtureDestroyer;
	
	
	public void bindState ( Start st )
	{
		
		state = st;
		
	}
	
	
	public void bindBodyDestroyer ( BodyDestroyer bd )
	{
		
		bodyDestroyer = bd;
		
	}
	
	public void bindFixtureDestroyer ( FixtureDestroyer fd )
	{
		
		fixtureDestroyer = fd;
		
	}
	
	
	public void beginContact(Contact c)
	{
		Fixture fixtureA = c.getFixtureA();
		Fixture fixtureB = c.getFixtureB();
		

		//Handle jumping
		if (fixtureA.getUserData() != null && fixtureA.getUserData().equals("player foot")) {
			isOnGround = true;
		}
		
		if (fixtureB.getUserData().equals("player foot")) {
			isOnGround = true;
		}

		
		//Handle hitbox collision
		if ( fixtureA.getUserData() instanceof HitboxFixtureUserData )
			handleHitboxCollision ( fixtureA , fixtureB );
		
		System.out.println(fixtureA.getUserData() + ", " + fixtureB.getUserData());
	}
	
	public void handleHitboxCollision(Fixture fixtureA , Fixture fixtureB)
	{
		
		
		
		//Get the hitboxes
		Hitbox hboxA = ((HitboxFixtureUserData) fixtureA.getUserData() ).getHitbox(state.hboxEntities);
		Hitbox hboxB = ((HitboxFixtureUserData) fixtureB.getUserData() ).getHitbox(state.hboxEntities);
		
		//Get the entities
		HitboxEntity entA = ((HitboxFixtureUserData) fixtureA.getUserData() ).getEntity(state.hboxEntities);
		HitboxEntity entB = ((HitboxFixtureUserData) fixtureB.getUserData() ).getEntity(state.hboxEntities);
		
		
		
		boolean entA_is_character = (entA instanceof Character);
		boolean entB_is_character = (entB instanceof Character);
		
		boolean hboxA_is_damaging = (hboxA.getType() == HitboxType.DAMAGING);
		boolean hboxB_is_damaging = (hboxB.getType() == HitboxType.DAMAGING);
		
		
		
		//Subtract damage if needed
		if (hboxA_is_damaging)
		{
			
			if ( entB_is_character )
			{
				
				((Character) entB).addHealth ( -hboxA.getDamage() );
				
				
				
			}
			
			hboxA.destroy( fixtureDestroyer );
			
		}
		
		if (hboxB_is_damaging)
		{
			
			if ( entA_is_character )
			{
				
				((Character) entA).addHealth ( -hboxB.getDamage() );
				
				
				
			}
			
			hboxB.destroy( fixtureDestroyer );
			
		}
		
		//Do we need to kill the bodies of the hitboxes?
		if ( !entA_is_character )
		{
			bodyDestroyer.add(entA.getBody());
			//Add destroy functions to entities
		}
		if ( !entB_is_character )
		{
			bodyDestroyer.add(entB.getBody());			
		}
		
		
		
	}
	
	
	public void endContact(Contact c)
	{
		Fixture fixtureA = c.getFixtureA();
		Fixture fixtureB = c.getFixtureB();
		
		if (fixtureA.getUserData().equals("player foot")) {
			isOnGround = false;
		}
		
		if (fixtureB.getUserData().equals("player foot")) {
			isOnGround = false;
		}
		
	}
	
	public boolean isOnGround()
	{
		return isOnGround;
	}
	
	public void preSolve(Contact c, Manifold m)
	{
		
	}
	
	public void postSolve(Contact c, ContactImpulse ci)
	{
		
	}
}
