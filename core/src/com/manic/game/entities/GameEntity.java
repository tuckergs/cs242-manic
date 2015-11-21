package com.manic.game.entities;





import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.manic.game.Settings;


//root of all game objects effected by physics
public class GameEntity extends Entity {
	
    
    protected Body body;
    protected World world;
	
    
    //the coordinates variable here is basically unused
	
    public GameEntity(BodyDef bdef, World world , Vector2 coordinates, SpriteBatch batch, String spriteID )
    {
    	
    	super(coordinates, batch , spriteID); 
    	
    	bdef.position.set(coordinates);
    	
    	this.world = world;
    	body = world.createBody(bdef);
    	
    }
    
    
    @Override
    public void render(){
    	
    	TextureRegion currentFrame = anim.getCurrentObj();
    	
    	batch.begin();
    	
    	batch.draw( currentFrame ,
    				body.getPosition().x * Settings.PPM  - 11 ,
    				body.getPosition().y * Settings.PPM  - 26 );
    	
    	batch.end();
    	
    }

    //Getters
    public Body getBody(){
    	return body;
    }
    
    public World getWorld(){
    	return world;
    }
    
    
    
    @Override
    public String toString()
    {
    	return "";
    }
}
