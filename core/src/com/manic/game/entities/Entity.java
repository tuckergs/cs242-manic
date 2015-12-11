package com.manic.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.manic.game.ObjectTimeline;

import com.manic.game.Manic;

/**
 * @class Entity
 *
 * @author Stephen Lorenz, Gabe Tucker
 * 
 * @brief Abstract outline of all objects within the game world.
 *
 * Stores location coordinates within the Vector2, has the ability
 * to draw staticly and dynamically. Can communicate directly to 
 * the object timeline of texture regions.
 * 
 * @version 1.0
 * 
 * @contact lorenzsj@clarkson.edu, tuckergs@clarkson.edu
 *
 */
///root of all things drawn to screen
public class Entity {
    protected Vector2 coordinates;
    protected SpriteBatch batch;    
    protected ObjectTimeline<TextureRegion> anim;
    protected boolean is_animated; 
    String spriteName;	
    
    public Entity(Vector2 coordinates, SpriteBatch batch , String spriteID)
    {	
      this.coordinates = new Vector2(coordinates); //This assumes you'll scale
      
      this.batch = batch;
        
      this.spriteName = spriteID;
      
      create();
    }
    
    private void create()
    {
    	anim = Manic.res_animations.get(spriteName).clone();
    }
    
    public void render()
    {	
    	batch.begin();
    	batch.draw(anim.getCurrentObj(), coordinates.x, coordinates.y );
    	batch.end();
    }
    
    public void update(float dt)
    {	
    	anim.update(dt);	
    }

    ///TODO: Add disposing code
    public void dispose()      	
    {
    	anim = null;
    }
    
    public Vector2 getCoordinates()
    {
    	return coordinates;	
    }
    
    public SpriteBatch getSpriteBatch()
    {
    	return batch;	
    }
    
    public void setCoordinates( Vector2 c )
    {
       	coordinates = c; 	
    }
    
    @Override
    public String toString()
    {
    	return "Root of anything that can be drawn to the screen";
    }
}
