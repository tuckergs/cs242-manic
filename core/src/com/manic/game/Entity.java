/* 
 *  TRYING TO MIGRATE GAMEOBJECT INTO ENTITY
 * 
 */

package com.manic.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Entity {
	private SpriteBatch spriteBatchHandle;
	private Texture texture;
	private float xCoord = 0;
	private float yCoord = 0;
	
	//private float height = 0;
	//private float width = 0;
	
	/* Description: To exist, you must be initialized with a sprite and location */
	//TODO implement with SpriteHandler
	public Entity(String texturePath, SpriteBatch batch, float x, float y)
	{
		spriteBatchHandle = batch;
		texture = new Texture(texturePath);
		
		xCoord = x;
		yCoord = y;
	}
	
	// Draw the entity
	protected void render()
	{
		spriteBatchHandle.draw(texture, xCoord, yCoord);
	}
	
	// Kill the entity
	protected void dispose()
	{
		spriteBatchHandle.draw(texture, xCoord, yCoord);
	}
	
	//#### Getters ####
	  //Location coordinates
	public float getXCoordinate()
	{
		return xCoord;
	}
	
	public float getYCoordinate()
	{
		return yCoord;
	}
	
	//#### Setters ####
	  //Location coordinates
	protected void setXCoordinate(float x)
	{
		xCoord = x;
	}
	
	protected void setYCoordinate(float y)
	{
		yCoord = y;
	}
	
}

