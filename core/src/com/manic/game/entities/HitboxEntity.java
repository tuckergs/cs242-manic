package com.manic.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.manic.game.moves.HitboxGroup;

public class HitboxEntity extends GameEntity {

	
	
	HitboxGroup hboxes;
	
	
	
	public HitboxEntity(BodyDef bdef, World world, Vector2 coordinates, SpriteBatch batch, String spriteID) {
	
		
		super(bdef, world, coordinates, batch, spriteID);
		
		hboxes = new HitboxGroup ();
		
	}

	
	public HitboxGroup getHitboxes()
	{
		
		return hboxes;
		
	}
	
	
}
