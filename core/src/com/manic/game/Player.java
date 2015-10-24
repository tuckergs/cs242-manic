/*
 * Description: Handles how a player works
 */

package com.manic.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player extends Entity implements Hitbox, Controllable { //extends Entity
	
	private String playerName, characterName;
	private float totalHealth = 0;
	private float currentHealth = 0;
	private float damageModifier = 0; //damage done, increases future damage done
	private float movementSpeed = 5;
	
	public Player(String player, String character, String texturePath, SpriteBatch batch, float x, float y, float h, float w)
	{
		super(texturePath, batch, x, y, h, w);
		
		playerName = player;
		characterName = character;		
	}
	
	public boolean isControllable(int frame)
	{
		return true;
	}
	
	public void updatePosition()
	{	
		if (Gdx.input.isKeyPressed(Input.Keys.A)) 
		{
			if (xCoordinate > 0)
			{
				xCoordinate -= movementSpeed;
			}
		}
		
		else if ( Gdx.input.isKeyPressed(Input.Keys.D) ) 
		{
			if (xCoordinate < 500)
			{
				xCoordinate += movementSpeed;
			}
		}
		
		else if (Gdx.input.isKeyPressed(Input.Keys.W)) 
		{
			if (yCoordinate > 230)
			{
				yCoordinate += movementSpeed;
			}
		}
		
		else if (Gdx.input.isKeyPressed(Input.Keys.S)) 
		{
			if (yCoordinate < 0)
			{
				yCoordinate -= movementSpeed;
			}
		}
	}
	
	//misc
	public String toString() {
		return "Extends Entity, implements Controllable and Hitbox";
	}
}