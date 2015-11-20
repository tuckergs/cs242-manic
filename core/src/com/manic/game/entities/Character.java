/* Defines what a character is, what they can do, and how they do it */
// every player has a character

package com.manic.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class Character extends Box {
	private String characterName;
	private float movementSpeed;
	private float health;
	private String placeholderPath;
	
	/* this class will give player all the information about their character */
	
	public Character(Vector2 coordinates, Vector2 dimensions, SpriteBatch batch, String spritePath)
	{
		super(BodyType.DynamicBody, coordinates, dimensions, batch, "");
		
		fixtureDef.density = 75.0f;
		fixtureDef.restitution = 0.2f;
	}
	
	//ABLE TO CREATE SENSOR

}
