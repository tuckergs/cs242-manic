package com.manic.game;

//doxygen example

import static com.manic.game.Settings.PPM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

//class documenation example
/**
 * @brief This class is really great.
 *
 * This class is meant as an example.  It is not useful by itself
 * rather its usefulness is only a function of how much it helps
 * the reader. It is in a sense defined by the person who reads it
 * and otherwise does not exist in any real form. 
 *
 * @note This documentation does not mean anything
 *
 * @author Stephen Lorenz
 *
 * @version 0.1
 *
 * @date 11/4/15
 */

public class Player extends Entity { //extends Entity
	
	private float currentHealth = 0;
	private float damageModifier = 0; //damage done, increases future damage done
	private float movementSpeed = 5;
	private Character fighter;
	
	/**
	 * @brief Brief description.
	 * 
	 * A longer description goes here.
	 * 
	 * @param character What a character is.
	 * @param texturePath Why it needs a texturePath.
	 * @param batch What batch is used for.
	 * @param x what this represents.
	 * @param y how this affects other classes.
	 * @param h why this is necessary for the player.
	 * @param w etc
	 */
	
	public Player(int loc1, int loc2)
	{
		super(BodyType.DynamicBody, new PolygonShape(), 5, 5, 60, 60);
		
		fixtureDef.density = 75.0f;
		fixtureDef.restitution = 0.2f;
		fixtureDef.filter.categoryBits = Settings.BIT_PLAYER;
		fixtureDef.filter.maskBits = Settings.BIT_PLATFORM | Settings.BIT_BALL | Settings.BIT_PLAYER;

	}

	
	//example function documentation
	/**
	 * @brief Used to determine whether Player is able to send input in a given frame.
	 * 
	 * A longer description goes here.
	 * 
	 * @param frame Description of what the parameter does.
	 * 
	 * @return Description of what the function is returning.
	 */
	
	public boolean isControllable(int frame)
	{
		return true;
	}

	//misc
	public String toString() {
		return "Extends Entity, implements Controllable and Hitbox";
	}
}