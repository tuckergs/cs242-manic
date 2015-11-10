package com.manic.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import static com.manic.game.Settings.PPM;

public class Box extends GameEntity {
	protected float height = 0;
    protected float width = 0;
    
    public Box(BodyType type, Vector2 coordinates, PolygonShape box, float height, float width, SpriteBatch batch, String spritePath)
    {
    	super(type, coordinates, batch, spritePath);
    	
    	setHeight(height);
     	setWidth(width);

     	bodyDef.position.set(coordinates);
     	
    	box.setAsBox(this.width, this.height);
    	fixtureDef.shape = box;
    }    
    
    //Getters
    //Dimensions
    public float getHeight()
    {
        return height;
    }

    public float getWidth()
    {
        return width;
    }
    
    //Setters
    //Dimensions
    protected void setHeight(float height)
    {
        if (height > 0) {
            this.height = height/PPM;
        }
        else {
        	this.height = 1;
        }
    }
    
    protected void setWidth(float width)
    {
        if (width > 0) {
            this.width = width/PPM;
        }
        else {
        	this.width = 1;
        }
    }
    
    @Override
    public String toString()
    {
    	return "";
    }
}
