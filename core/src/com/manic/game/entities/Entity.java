package com.manic.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.manic.game.ObjectTimeline;
	
//root of all things drawn to screen
abstract class Entity {
	
	protected Vector2 coordinates;
	
	
	protected SpriteBatch batch;
	
	protected ObjectTimeline<TextureRegion> anim;
    protected boolean is_animated;
	
    public Entity(Vector2 coordinates, SpriteBatch batch)
    {	
    	this.coordinates = new Vector2(coordinates); ///scale down vector
    	
        this.batch = batch;
    }
    
    abstract void create();
    
    abstract void render();
    
    abstract void update();
    
    abstract void dispose();
    
    abstract Vector2 getCoordinates();
    
    @Override
    public String toString()
    {
    	return "Root of anything that can be drawn to the screen";
    }
}