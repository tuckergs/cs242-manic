package com.manic.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
	
//root of all things drawn to screen
abstract class Entity {
	protected Vector2 coordinates;
	protected SpriteBatch batch;
	
    public Entity(Vector2 coordinates, SpriteBatch batch)
    {	
    	this.coordinates = new Vector2(coordinates);
    	
        this.batch = batch;
    }
    
    abstract void create();
    
    abstract void render();
    
    abstract Vector2 getCoordinates();
    
    @Override
    public String toString()
    {
    	return "Root of anything that can be drawn to the screen";
    }
}