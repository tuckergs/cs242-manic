/* Defines what a character is, what they can do, and how they do it */
// every player has a character

package com.manic.game.entities;

//import static com.manic.game.Settings.PPM;
import static com.manic.game.Settings.SCALE_PPM;

import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
//import com.badlogic.gdx.utils.Array;
import com.manic.game.Manic;
import com.manic.game.ObjectTimeline;
import com.manic.game.Settings;
//import com.manic.game.moves.Hitbox;
//import com.manic.game.moves.HitboxGroup;
import com.manic.game.moves.Move;
//import com.manic.game.resource_management.Moves;

public class Character extends HitboxEntity 
{

	private String characterName;
//	private float movementSpeed;
	private float health;
//	private String placeholderPath;
	
	private int currentHitstunFrame;
	private int lastHitstunFrame;

//	private int playerID;

	private Move currentMove;

	private Fixture physicsBox;
	private Fixture footBox;

	private boolean canInput;
	private boolean isOnGround;


	private boolean move_changed;

	private HashMap < String , HitboxEntity > boundHboxEntityMap;



	/* this class will give player all the information about their character */

	//The constructor
	public Character( World world , Vector2 coordinates, Vector2 dimensions , 
			SpriteBatch batch, String charID , String entityID ,
			HashMap < String , HitboxEntity> hsh )
	{

		super ( new BodyDef() , world , coordinates , batch , "" , entityID , hsh );

		//Now get rid of that body that we just created... -_-
		//And all its connections
		world.destroyBody(body);
		hsh.remove(entityID);



		BodyDef bodyDef = new BodyDef();
		FixtureDef fixtureDef = new FixtureDef();

		//create player
		bodyDef.position.set(coordinates);
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.fixedRotation = true;


		body = world.createBody(bodyDef);
		body.setUserData( entityID );




		PolygonShape box = new PolygonShape();
		box.setAsBox(dimensions.x /2 /SCALE_PPM, dimensions.y /2 /SCALE_PPM); //10x10
		fixtureDef.shape = box;
		fixtureDef.density = 7.5f;
		System.out.println("1.0f" + body.getMass());
		fixtureDef.restitution = 0f;
		fixtureDef.filter.categoryBits = Settings.BIT_PLAYER;
		fixtureDef.filter.maskBits = Settings.BIT_PLATFORM | Settings.BIT_BALL | Settings.BIT_PLAYER;
		physicsBox = body.createFixture(fixtureDef);
		physicsBox.setUserData("player");


		//create foot sensor
		box.setAsBox( (dimensions.x/2) /2/SCALE_PPM, 2/SCALE_PPM, new Vector2( 0, (-dimensions.y /2 + 2) /SCALE_PPM ), 0);
		fixtureDef.shape = box;
		fixtureDef.filter.categoryBits = Settings.BIT_PLAYER;
		fixtureDef.filter.maskBits = Settings.BIT_PLATFORM;
		fixtureDef.isSensor = true;
		footBox = body.createFixture(fixtureDef);
		footBox.setUserData("player foot");


		//Character can use input
		canInput = true;

		//Set characterName
		characterName = charID;

		//Init move
		setMove ( characterName + "stand" );


		//Bind hitbox entity map
		boundHboxEntityMap = hsh;

		//Put body user data to entity table
		putToEntityTable ( boundHboxEntityMap );


	}

	//Updater
	@Override
	public void update ( float dt )
	{

		//This loop exists so that when the move is changed,
		//It goes straight to the first frame of stuff
		//This is because I plan to put move changing code 
		//directly after the last frame
		do {

			move_changed = false;
			currentMove.update( dt );
			currentMove.execute(this);

		} while ( move_changed );
		anim.update(dt);

	}




	//Setters
	public void setAnimation ( ObjectTimeline<TextureRegion> anim )
	{

		this.anim = anim.clone();

	}

	public void setMove ( String moveID )
	{

		currentMove = Manic.res_moves.get(moveID).clone();
		move_changed = true;

	}

	public void setHealth ( float h )
	{

		health = h;

		System.out.println ( characterName + ": " + health );

	}

	public void addHealth ( float r )
	{

		health += r;

		System.out.println( characterName + ": " + health );

	}

	public void acceptInput()
	{
		canInput = true;
	}

	public void denyInput()
	{
		canInput = false;
	}

	public void onGround()
	{
		isOnGround = true;
	}

	public void offGround()
	{
		isOnGround = false;
	}



	//Getters
	public float getHealth ()
	{
		return health;
	}

	public HashMap < String , HitboxEntity > getHboxEntityMap()
	{
		return boundHboxEntityMap;
	}

	public boolean canInput()
	{
		return canInput;
	}

	public boolean isOnGround()
	{
		return isOnGround;
	}
	
	//Hitstun stuff
	
	public void setHitstun ( int totalHitstun )
	{
		
		currentHitstunFrame = 0;
		lastHitstunFrame = totalHitstun;
		
	}

	public void incrementHitstun ()
	{
		
		++currentHitstunFrame;
		
	}
	
	public boolean outOfHitstun ()
	{
		return ( currentHitstunFrame >= lastHitstunFrame );
	}
	

	//ABLE TO CREATE SENSOR

}
