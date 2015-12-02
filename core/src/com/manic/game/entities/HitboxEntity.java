package com.manic.game.entities;

import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.manic.game.moves.Hitbox;
import com.manic.game.moves.HitboxGroup;
import com.manic.game.moves.HitboxType;

public class HitboxEntity extends GameEntity {

	
	
	HitboxGroup hboxes;
	
	
	
	public HitboxEntity(BodyDef bdef, World world, Vector2 coordinates, SpriteBatch batch, 
						String spriteID , String entityID , 
						HashMap < String , HitboxEntity > hsh ) {
	
		
		super(bdef, world, coordinates, batch, spriteID);
		
		body.setUserData(entityID);
		
		hboxes = new HitboxGroup ();
		
		
		putToEntityTable ( hsh );
		
	}

	
	public HitboxGroup getHitboxes()
	{
		
		return hboxes;
		
	}
	
	
	
	public void addHitbox ( Vector2 coordinates , Vector2 dimensions , HitboxType type , 
					String hboxUserData , float damage , float hitstun )
	{
		
		hboxes.add ( new Hitbox ( body , coordinates , dimensions , 
					type , hboxUserData , damage , hitstun ));
		
	}
	
	
	
	protected void putToEntityTable ( HashMap < String , HitboxEntity > hsh )
	{
		
		hsh.put( body.getUserData().toString() , this );
		
	}
	
	
}
