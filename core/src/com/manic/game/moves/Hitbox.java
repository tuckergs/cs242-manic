package com.manic.game.moves;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.manic.game.FixtureDestroyer;
import com.manic.game.HitboxFixtureUserData;
import com.manic.game.Settings;

import static com.manic.game.Settings.SCALE_PPM;


/**
 * 
 * @class Hitbox
 * 
 * @brief A collision box
 * 
 * This is a box that you attach to an Entity
 * to give the entity precise collsion detection
 * 
 * Can either be a damaging hhitbox or a character hurtbox
 * 
 * A hitbox colliding with a character hurtbox causes the character
 * to be damaged and stunned
 * 
 * When two damagiing hitboxes hit, they should trade hits and cancel out
 * 
 * 
 * @author Gabe Tucker
 * 
 * @contact gst06@roadrunner.com
 *
 */
public class Hitbox {

	
	private Fixture hboxFixture;
	
	private String hboxID;
	
	private HitboxType type;
	
	private float damage;
	
	private int hitstun;
	
	private boolean is_destroyed;
	
	
	///Constructor
	public Hitbox ( Body body , Vector2 coordinates , Vector2 dimensions , HitboxType type , 
					String hboxUserData , float damage , int hitstun ){
		
		///Create fixture
		FixtureDef fdef = new FixtureDef();
	
		///Init shape
		PolygonShape box = new PolygonShape();
		box.setAsBox(dimensions.x / 2 / SCALE_PPM , dimensions.y / 2 / SCALE_PPM , 
				coordinates.scl(1 / SCALE_PPM) , 0 );
		
		fdef.shape = box;
		
		
		///Set appropriate collison bits 
		if ( type == HitboxType.DAMAGING )
		{
			
			fdef.filter.categoryBits = Settings.BIT_HITBOX_DAMAGING;
			fdef.filter.maskBits = Settings.BIT_HITBOX_CHARACTER | Settings.BIT_HITBOX_DAMAGING;
		
		}
		else 
		{
			
			fdef.filter.categoryBits = Settings.BIT_HITBOX_CHARACTER;
			fdef.filter.maskBits = Settings.BIT_HITBOX_DAMAGING;
			
		}
		
		///Is a sensor
		fdef.isSensor = true;
		
		///Has no mass
		fdef.density = 0f;
		
		///Create fixture
		hboxFixture = body.createFixture(fdef);
		
		///Set user data
		hboxFixture.setUserData( new HitboxFixtureUserData (
									body.getUserData().toString() , hboxUserData ));
		
		hboxID = hboxUserData;
		
		
		///Set other stuff
		this.type = type;
		this.damage = damage;
		this.hitstun = hitstun;		
		
		///Is not destroyed
		is_destroyed = false;
		
	}
	
	
	/*
	 * I think this, if implemented, would break the design of Hitbox in relation to user data
	public Hitbox (Body body , Vector2 coordinates , Vector2 dimensions , HitboxType type , 
					String charData , String hitboxID , float damage , float hitstun)
	{
		
		this ( body , coordinates , dimensions , type , "" , damage , hitstun );
		
		///Make user data
		
	}
	*/
	
	///This function gives you the option of not queuing a FixtureDestroyer
	public void destroy ( FixtureDestroyer fd )
	{
		
		if ( fd != null ) 
		{
			fd.add(hboxFixture);
		}
		else
		{
			hboxFixture.getBody().destroyFixture(hboxFixture);
		}
			
		is_destroyed = true;
		
	}
	
	
	
	
	///Getters
		
	public String getHitboxID()
	{
		return hboxID;
	}
	
		
	public HitboxType getType()
	{
		return type;
	}
	
	public float getDamage ()
	{
		return damage;
	}
	
	public int getHitstun()
	{
		return hitstun;
	}
	
	public boolean is_destroyed()
	{
		return is_destroyed;
	}
	
	
	
	
}
