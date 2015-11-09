package com.manic.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;

public class GameEntity extends Entity {
	protected BodyDef bodyDef;
    protected FixtureDef fixtureDef;
    
    public GameEntity(Vector2 v, SpriteBatch batch, String spritePath)
    {
    	super(v, batch, spritePath);
    	
    	bodyDef = new BodyDef();
    	fixtureDef = new FixtureDef();
    }    
    
    protected void setCoordinates(Vector2 v)
    {
    	if (v.x > 0 && v.y > 0) {
    		v.set(coordinates);
    	}
    }
    
    public BodyDef getBody()
    {
        return bodyDef;
    }
   
    //Fixtures
    public FixtureDef getFixture()
    {
        return fixtureDef;
    }

    
    public String toString() {
        return "";
    }
}
