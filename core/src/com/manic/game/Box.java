package com.manic.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import static com.manic.game.Settings.PPM;

public class Box extends GameEntity {
	private float height = 0;
    private float width = 0;
    
    public Box(BodyType type, Vector2 v, PolygonShape box, float height, float width, SpriteBatch batch, String spritePath)
    {
    	super(type, v, batch, spritePath);
    	
    	setHeight(height);
     	setWidth(width);

     	bodyDef.position.set(v);
     	
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
    protected void setHeight(float h)
    {
        if (h > 0) {
            height = h/PPM;
        }
        else {
        	height = 1;
        }
    }
    
    protected void setWidth(float w)
    {
        if (w > 0) {
            width = w/PPM;
        }
        else {
        	width = 1;
        }
    }
    
    @Override
    public String toString()
    {
    	return "";
    }
}
