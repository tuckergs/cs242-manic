package com.manic.game.entities;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;


//root of all game objects effected by physics
public class GameEntity extends Entity {
	protected BodyDef bodyDef;
    protected FixtureDef fixtureDef;
   
    
    
	protected String spritePath;
    protected Texture sprite;
	
	
    public GameEntity(BodyType type, Vector2 coordinates, SpriteBatch batch, String spriteID )
    {
    	
    	super(coordinates, batch , spriteID); 
    	
    	bodyDef = new BodyDef();
    	this.bodyDef.type = type;
    	
    	fixtureDef = new FixtureDef();
    	
    	
    }
   
   
    
    //Setters
    protected void setBodyDef(BodyDef bodyDef)
    {
        this.bodyDef = bodyDef;
    }
   
    protected void setFixtureDef(FixtureDef fixtureDef)
    {
    	this.fixtureDef = fixtureDef;
    }
    
    //Getters   
    public BodyDef getBodyDef()
    {
        return bodyDef;
    }
   
    public FixtureDef getFixtureDef()
    {
        return fixtureDef;
    }
    
    @Override
    public String toString()
    {
    	return "";
    }
}
