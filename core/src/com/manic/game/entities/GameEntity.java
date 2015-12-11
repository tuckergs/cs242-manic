package com.manic.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.World;
import com.manic.game.BodyDestroyer;
//import com.manic.game.Manic;
import com.manic.game.Settings;

/**
 * 
 * @author Stephen Lorenz, Gabe Tucker
 * 
 * @brief Simplest form of any physics effected object in the game.
 *
 * Expands upon Entity, enabling it to experience physics within the 
 * game world. This is done by giving it a reference to the gdx Body
 * class. This class enforces that the moment any GameEntity is
 * initialized, it must be attached to the world.
 */

//root of all game objects effected by physics
public class GameEntity extends Entity {
	
    
    protected Body body;
    protected World world;
    
    
    protected Vector2 renderLocation;
    
	
    
    //the coordinates variable here is basically unused
	
    public GameEntity(BodyDef bdef, World world , Vector2 coordinates, SpriteBatch batch, String spriteID )
    {
    	
    	super(coordinates, batch , spriteID); 
    	
    	bdef.position.set(coordinates.scl(1/Settings.PPM));
    	
    	this.world = world;
    	body = world.createBody(bdef);
    	
    }
    
    /**
     * @brief Modify's Entity's coordinates to work with Box2D.
     * 
     * Because Box2D uses different dimension scale, it is 
     * necessary to make GameEntity comply by using SCALE_PPM
     * to adjust the size and location.
     */
    @Override
    public void render(){
    	
    	TextureRegion currentFrame = anim.getCurrentObj();
    	
    	if ( currentFrame == null )
    		return;
    	
    	batch.begin();
    	
    	if ( renderLocation == null )
    		batch.draw( currentFrame ,
    				body.getPosition().x * Settings.SCALE_PPM  
    						- (currentFrame.getRegionWidth() / 2) ,
    				body.getPosition().y * Settings.SCALE_PPM  
    						- (currentFrame.getRegionHeight() / 2) );
    	else
    		batch.draw( currentFrame , 
    				body.getPosition().x * Settings.SCALE_PPM
    					+ renderLocation.x, 
    				body.getPosition().y * Settings.SCALE_PPM
    					+ renderLocation.y);
    	
    	batch.end();
    	
    }

    //Getters
    public Body getBody(){
    	return body;
    }
    
    public World getWorld(){
    	return world;
    }
    
    public Vector2 getRenderLocation ()
    {
    	
    	if ( renderLocation == null ) 
    		return null;
    	else
    		return new Vector2 ( renderLocation.x , 
    							renderLocation.y );
    	
    }
    
    //Setters
    public void setRenderLocation( float rX , float rY )
    {
    	
    	if ( renderLocation == null )
    		renderLocation = new Vector2();
    	
    	renderLocation.x = rX;
    	renderLocation.y = rY;
    	
    }
    
    
    
    //Dispose
    public void dispose( BodyDestroyer bd )
    {
    	
    	super.dispose();
    	
    	bd.add(body);
    	
    }
    
    
    @Override
    public String toString()
    {
    	return "";
    }
}
