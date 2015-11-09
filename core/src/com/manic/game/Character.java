/* Defines what a character is, what they can do, and how they do it */
// every player has a character

package com.manic.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class Character extends Box {
	private String characterName;
	private float movementSpeed;
	private float health;
	private String placeholderPath;
	
	/* this class will give player all the information about their character */
	
	public Character(Vector2 v, float height, float width, SpriteBatch batch, String spritePath)
	{
		super(BodyType.DynamicBody, v, 5, 5, batch, "");
	}

}
