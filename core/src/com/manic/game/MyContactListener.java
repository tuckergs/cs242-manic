package com.manic.game;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import com.manic.game.states.GameState;
import com.manic.game.states.Start;


public class MyContactListener implements ContactListener {
	
	private boolean isOnGround;
	private Start state;
	
	
	
	public void bindState ( Start st )
	{
		
		state = st;
		
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
		
		//TODO: Finish me
		
		
		
		
		
		
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
