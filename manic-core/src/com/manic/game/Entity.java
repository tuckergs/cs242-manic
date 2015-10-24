package com.manic.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Entity {
	private SpriteBatch spriteBatchHandle;
	private Texture texture;
	protected float xCoordinate = 0;
	protected float yCoordinate = 0;
	protected float height = 0;
	protected float width = 0;
	
	/* Description: To exist, you must be initialized with a sprite, dimension, and location */
	//TODO implement with SpriteHandler
	public Entity(String texturePath, SpriteBatch batch, float x, float y, float h, float w)
	{
		spriteBatchHandle = batch;
		texture = new Texture(texturePath);

		setCoordinateX(x);
		setCoordinateY(y);
		
		setHeight(h);
		setWidth(w);
	}
	
	// Draw the entity
	protected void draw()
	{
		spriteBatchHandle.draw(texture, xCoordinate, yCoordinate);
	}
	
	// Kill the entity
	protected void dispose()
	{
		spriteBatchHandle.dispose();
		xCoordinate = -1;
		yCoordinate = -1;
		height = 0;
		width = 0;
	}
	
	//#### Getters ####
	  //Location coordinates
	public float getCoordinateX()
	{
		return xCoordinate;
	}
	
	public float getCoordinateY()
	{
		return yCoordinate;
	}
	
	public float getHeight()
	{
		return height;
	}
	
	public float getWidth()
	{
		return width;
	}
	
	//#### Setters ####
	  //Location coordinates
	protected void setCoordinateX(float x)
	{
		if (x > -1) {
			xCoordinate = x;
		}
		else {
			xCoordinate = 0;
		}
	}
	
	protected void setCoordinateY(float y)
  	{
		if (y > -1) {
  			yCoordinate = y;
		}
		else {
			yCoordinate = 0;
		}
  	}
	
  	  //dimensions
  	protected void setHeight(float h)
	{
  		if (h > 0) {
			height = h;
  		}
  		else {
  			height = 1;
  		}
	}
  	
  	protected void setWidth(float w)
	{
		if (w > 0) {
			width = w;
		}
		else {
			width = 1;
		}
	}
	
	//misc
	public String toString() {
		return "Root class of all visible object. Contains a sprite, dimension, and location";
	}
}

