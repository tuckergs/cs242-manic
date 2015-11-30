package com.manic.game.moves;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.manic.game.Settings;

import static com.manic.game.Settings.SCALE_PPM;

public class Hitbox {

	
	private Fixture hboxFixture;
	
	private HitboxType type;
	
	private float damage;
	
	private float hitstun;
	
	
	//Constructor
	public Hitbox ( Body body , Vector2 coordinates , Vector2 dimensions , HitboxType type , 
					String userData , float damage , float hitstun ){
		
		//Create fixture
		FixtureDef fdef = new FixtureDef();
		
		PolygonShape box = new PolygonShape();
		
		box.setAsBox(dimensions.x / 2 / SCALE_PPM , dimensions.y / 2 / SCALE_PPM , 
				coordinates.scl(1 / SCALE_PPM) , 0 );
		
		fdef.shape = box;
		
		if ( type == HitboxType.DAMAGING )
		{
			
			fdef.filter.categoryBits = Settings.BIT_HITBOX_DAMAGING;
			fdef.filter.maskBits = Settings.BIT_HITBOX_CHARACTER;

		}
		else 
		{
			
			fdef.filter.categoryBits = Settings.BIT_HITBOX_CHARACTER;
			fdef.filter.maskBits = Settings.BIT_HITBOX_DAMAGING;
			
		}
			
		fdef.isSensor = true;
		
		fdef.density = 0f;
		
		hboxFixture = body.createFixture(fdef);
		
		hboxFixture.setUserData(userData);
		
		
		
		
		this.type = type;
		
		this.damage = damage;
		
		this.hitstun = hitstun;		
		
	}
	
	public Hitbox (Body body , Vector2 coordinates , Vector2 dimensions , HitboxType type , 
					String charData , String hitboxID , float damage , float hitstun)
	{
		
		this ( body , coordinates , dimensions , type , "" , damage , hitstun );
		
		//Make
		
	}
	
	public void destroy ()
	{
		
		hboxFixture.getBody().destroyFixture(hboxFixture);
		
	}
	
	
	
	
	
	//Getters
	public HitboxType getType()
	{
		return type;
	}
	
	public float getDamage ()
	{
		return damage;
	}
	
	public float getHitstun()
	{
		return hitstun;
	}
	
	
	
	
}
