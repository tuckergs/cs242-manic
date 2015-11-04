package com.manic.game;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class MyContactListener implements ContactListener {
	
	private boolean isOnGround;
	
	public void beginContact(Contact c)
	{
		Fixture fixtureA = c.getFixtureA();
		Fixture fixtureB = c.getFixtureB();
		
		if (fixtureA.getUserData() != null && fixtureA.getUserData().equals("player foot")) {
			isOnGround = true;
		}
		
		if (fixtureB.getUserData() != null && fixtureB.getUserData().equals("player foot")) {
			isOnGround = true;
		}
		
		System.out.println(fixtureA.getUserData() + ", " + fixtureB.getUserData());
	}
	
	public void endContact(Contact c)
	{
		Fixture fixtureA = c.getFixtureA();
		Fixture fixtureB = c.getFixtureB();
		
		if (fixtureA.getUserData() != null && fixtureA.getUserData().equals("player foot")) {
			isOnGround = false;
		}
		
		if (fixtureB.getUserData() != null && fixtureB.getUserData().equals("player foot")) {
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
