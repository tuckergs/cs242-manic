/* Defines what a character is, what they can do, and how they do it */
// every player has a character

package com.manic.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Character extends GameEntity {
	private String characterName;
	private float movementSpeed;
	private float health;
	private String placeholderPath;
	
	/* this class will give player all the information about their character */
	
	public Character( BodyDef bdef , World world , Vector2 coordinates, Vector2 dimensions, SpriteBatch batch, String spriteID)
	{
		
		super( bdef , world , coordinates, batch, "" );
		
		FixtureDef fixtureDef = new FixtureDef();
		
		fixtureDef.density = 75.0f;
		fixtureDef.restitution = 0.2f;
		
	}
	
	//ABLE TO CREATE SENSOR

}
