package com.manic.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.manic.game.ObjectTimeline;

//root of all game objects effected by physics
public class GameEntity extends Entity {
	protected BodyDef bodyDef;
    protected FixtureDef fixtureDef;
   
    
    
	protected String spritePath;
    protected Texture sprite;
	
	
    public GameEntity(BodyType type, Vector2 coordinates, SpriteBatch batch, String spriteID )
    {
    	//TODO: Is this PPM?
    	super(coordinates.scl(0.01f), batch); //divide coordinates by 100
    	
    	bodyDef = new BodyDef();
    	this.bodyDef.type = type;
    	
    	fixtureDef = new FixtureDef();
    	
    	this.spritePath = spritePath;
    }
    
    protected void create() {
        sprite = new Texture(Gdx.files.internal(spritePath));
        
    	//TODO: Put in stuff to anim
    	
    }

    protected void render() {
        batch.begin();
        batch.draw(sprite, 100, 100);
        batch.end();
    }
    
    protected void update() {}
    
    protected void dispose() {}
    
    //Setters
    protected void setCoordinates(Vector2 v)
    {
    	if (v.x > 0 && v.y > 0) {
    		v.set(coordinates);
    	}
    }
    protected void setBodyDef(BodyDef bodyDef)
    {
        this.bodyDef = bodyDef;
    }
   
    protected void setFixtureDef(FixtureDef fixtureDef)
    {
    	this.fixtureDef = fixtureDef;
    }
    
    //Getters
    protected Vector2 getCoordinates()
    {
    	return coordinates;
    }
    
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
