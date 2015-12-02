package com.manic.game.entities;

import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.manic.game.moves.Hitbox;
import com.manic.game.moves.HitboxGroup;
import com.manic.game.moves.HitboxType;
import com.manic.game.FixtureDestroyer;
import com.manic.game.Settings;

public class HitboxEntity extends GameEntity {

	
	protected boolean is_flipped;
	
	
	HitboxGroup hboxes;
	
	
	
	public HitboxEntity(BodyDef bdef, World world, Vector2 coordinates, SpriteBatch batch, 
						String spriteID , String entityID , 
						HashMap < String , HitboxEntity > hsh ) {
	
		
		super(bdef, world, coordinates, batch, spriteID);
		
		body.setUserData(entityID);
		
		hboxes = new HitboxGroup ();
		
		
		putToEntityTable ( hsh );
		
		
		is_flipped = false;
		
	}

	//Getters
	/*
	public HitboxGroup getHitboxes()
	{
		
		return hboxes;
		
	}
	*/
	
	public boolean is_flipped()
	{
		return is_flipped;
	}
	
	
	//Returns 1 if is flipped
	//and -1 if not
	public float getFlipFactor()
	{
		return ( is_flipped ? -1 : 1 );
	}
	
	
	public Hitbox getHitbox ( String k )
	{
		
		return hboxes.get(k);
		
	}
	
	public void addHitbox ( Vector2 coordinates , Vector2 dimensions , HitboxType type , 
					String hboxUserData , float damage , float hitstun )
	{
		
		hboxes.add ( new Hitbox ( body , coordinates , dimensions , 
					type , hboxUserData , damage , hitstun ));
		
	}
	
	public void removeHitbox ( String hboxID , FixtureDestroyer fd )
	{
		
		hboxes.removeHitbox( hboxID , fd );
		
	}
	
	public void removeAllHitboxes ( FixtureDestroyer fd )
	{
		
		hboxes.removeAllHitboxes(fd);
		
	}
	
	
	
	protected void putToEntityTable ( HashMap < String , HitboxEntity > hsh )
	{
		
		hsh.put( body.getUserData().toString() , this );
		
	}
	
	
	@Override
	public void render()
	{

		TextureRegion currentFrame = anim.getCurrentObj();
		
		currentFrame.flip(is_flipped, false);

		if ( currentFrame == null )
			return;

		batch.begin();

		batch.draw( currentFrame ,
				body.getPosition().x * Settings.SCALE_PPM  
				- (currentFrame.getRegionWidth() / 2) ,
				body.getPosition().y * Settings.SCALE_PPM  
				- (currentFrame.getRegionHeight() / 2) );

		batch.end();
		
		currentFrame.flip(is_flipped , false);
		
	}
	
	
	
}
