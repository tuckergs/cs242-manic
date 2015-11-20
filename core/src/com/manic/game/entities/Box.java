package com.manic.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import static com.manic.game.Settings.PPM;

public class Box extends GameEntity {
	
	protected Vector2 dimensions;
    
    public Box(BodyType type, Vector2 coordinates, Vector2 dimensions, SpriteBatch batch, String spritePath)
    {
    	super(type, coordinates, batch, spritePath);
    	
    	setDimensions ( dimensions );

     	bodyDef.position.set(coordinates);
     	
     	PolygonShape box = new PolygonShape();
    	box.setAsBox( dimensions.x, dimensions.y );
    	fixtureDef.shape = box;
    }    
    
    //Getters
    //Dimensions
    public Vector2 getDimensions(){
    	return dimensions;
    }
    
    //Setters
    //Dimensions
    protected void setDimensions ( Vector2 d )
    {
    	
    	this.dimensions = d;
    	
    }
    
    @Override
    public String toString()
    {
    	return "";
    }
}
