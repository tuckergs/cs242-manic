package com.manic.game.resource_management;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
//import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.manic.game.Manic;
import com.manic.game.ObjectTimeline;
import com.manic.game.Settings;
import com.manic.game.moves.CodeSnippet;
//import com.manic.game.moves.Hitbox;
//import com.manic.game.moves.HitboxGroup;
import com.manic.game.moves.HitboxType;
import com.manic.game.moves.Move;
import com.manic.game.entities.Character;
import com.manic.game.entities.HitboxEntity;

/**
 * 
 * @author gabe
 *
 * All of the moves are hardcoded for now
 * Later, we'll read moves from XML :)
 * and we'll make this class extend ResourceManager
 *
 */
public class Moves {

	private HashMap < String , Move > moves;


	private static final String SAGATSTAND = "sagatstand";


	public Moves()
	{

		moves = new HashMap < String , Move >();

	}


	public Move get ( String name )
	{

		return moves.get(name);

	}
	
	
	
	
	
	

	public void init()
	{

		moves = new HashMap < String , Move >();

		createSagatStand();
		
		createTigerShotMove();
		
		createSagatAerial();
		
		createSagatGroundKick();
		
		createSagatHitstun();

	}


	
	
	
	
	
	
	
	


	//Creators
	@SuppressWarnings("unused")
	private void createNothing()
	{

		HashMap < Integer , CodeSnippet > hsh 
		= new HashMap < Integer , CodeSnippet >();

		int length = 0;
		float delay = Manic.STEP;


		ObjectTimeline< CodeSnippet > code
		= new ObjectTimeline< CodeSnippet > (
				hsh , length , delay );

		Move move = new Move ( code );

		moves.put ( "nothing" , move );


	}

	private void createSagatStand( )
	{

		HashMap < Integer , CodeSnippet > hsh 
		= new HashMap< Integer , CodeSnippet >();

		int length = 8;
		float delay = Manic.STEP;
		boolean loop = false;
		boolean get_on_no_keyframe = false;


		//Create normal stand move
		hsh.put( 0 , new CodeSnippet(){

			public void run( Character ch )
			{

				//Set the animations
				createAnimationSagatStand ( ch );
				
				createRenderLocationSagatStand ( ch );


				//Create hitboxes
				createHitboxesSagatStand ( ch );


			}

		});


		ObjectTimeline< CodeSnippet > code
		= new ObjectTimeline < CodeSnippet >(
				hsh , length , delay , loop , get_on_no_keyframe );

		Move move = new Move ( code );

		moves.put( SAGATSTAND , move );







		//Create the turn portion, which just flips the hitboxes
		hsh = new HashMap < Integer , CodeSnippet >();
		hsh.put( 0 , new CodeSnippet(){

			public void run( Character ch )
			{

				//Create hitboxes
				createHitboxesSagatStand ( ch );


			}


		});


		code = new ObjectTimeline < CodeSnippet >(
				hsh , length , delay , loop , get_on_no_keyframe );

		move = new Move ( code );

		moves.put( SAGATSTAND + "turn" , move );

	}

	private void createAnimationSagatStand( Character ch )
	{

		ObjectTimeline<TextureRegion> a
		= Manic.res_animations.get( SAGATSTAND );

		ch.setAnimation( a );

	}
	
	private void createRenderLocationSagatStand ( Character ch )
	{
		
		ch.setRenderLocation( -22 , -52 );
		
	}

	private void createHitboxesSagatStand ( Character ch )
	{

//		Body body = ch.getBody();


		ch.removeAllHitboxes(null);



		
		//Create big hitbox
		ch.addHitbox ( 
						new Vector2 ( 0f , 0f ) ,
						new Vector2 ( 44f , 104f ) ,
						HitboxType.CHARACTER ,
						"hurtbox" , 
						0 , 0
					);
		 




		/*
		float flipX = ch.getFlipFactor();

		//Create top box
		ch.addHitbox (
				new Vector2 ( 3f * flipX , 31.5f ) ,
				new Vector2 ( 34f , 23f ) ,
				HitboxType.CHARACTER ,
				"top" , 
				0 , 0 
				);
		
		

		//Create hip box
		ch.addHitbox (
				new Vector2 ( -2 * flipX , -2 ) ,
				new Vector2 ( 28 , 34 ) ,
				HitboxType.CHARACTER ,
				"hip" ,
				0 , 0
				);

		//Create leg box
		ch.addHitbox (
				new Vector2 ( -4 * flipX , -35.5f ) ,
				new Vector2 ( 34 , 29 ) ,
				HitboxType.CHARACTER ,
				"leg" ,
				0 , 0
				);
		 */


	}

	
	
	
	
	
	
	
	
	
	

	public void createTigerShotMove ()
	{

		//TODO: Do stuff
		
		HashMap < Integer , CodeSnippet > hsh
			= new HashMap < Integer , CodeSnippet >();
		
		int length = 50;
		float delay = Manic.STEP;
		boolean loop = false;
		boolean get_on_no_keyframe = false;
		
		
		hsh.put( 0 , new CodeSnippet(){
			
			public void run ( Character ch )
			{
				
				createAnimation ( ch );
				
				playAttackSound();
				
				updateRenderLocation ( ch );
				
				createHitboxes ( ch );
				
				ch.denyInput();
				
			}
			
			private void createAnimation ( Character ch )
			{
				
				ObjectTimeline < TextureRegion > a
					= Manic.res_animations.get("sagattigershot");
				
				ch.setAnimation(a);
				
			}
			
			private void updateRenderLocation( Character ch )
			{
				
				if ( ch.is_flipped() )
					ch.setRenderLocation( -62 , -52 );
				else
					ch.setRenderLocation( -29, -52 ); //The foot is seven pixels away from the edge
				
			}
			
			
			private void createHitboxes ( Character ch )
			{
				
				ch.removeAllHitboxes(null);
				
				//For now, keep it simple
				ch.addHitbox( 
						new Vector2 ( 0 , 0 ), 
						new Vector2 ( 44 , 108 ), 
						HitboxType.CHARACTER, 
						"body" , 
						0 , 0 
						);
				
			}
			
			
		});
		
		hsh.put( 4 , new CodeSnippet(){

			
			public void run(Character ch) {
				
				updateRenderLocation ( ch );
				
				createHitboxes ( ch );
				
			}
			
			private void updateRenderLocation ( Character ch )
			{
				
				if ( ch.is_flipped() )
					ch.setRenderLocation( -69 , -52 );
				else
					ch.setRenderLocation( -22 , -52 );
				
			}
			
			private void createHitboxes ( Character ch )
			{
				
				ch.removeAllHitboxes(null);
				
				float flipX = ch.getFlipFactor();
				
				//We can't go simple here
				//Body box
				ch.addHitbox(
						new Vector2 ( 12.5f * flipX , 8.5f ), 
						new Vector2 ( 31 , 45 ), 
						HitboxType.CHARACTER,
						"body", 
						0 , 0
						);
				//Right leg
				ch.addHitbox(
						new Vector2 ( 12f * flipX , -33f ) ,
						new Vector2 ( 12f , 34f ) ,
						HitboxType.CHARACTER,
						"rightleg",
						0 , 0
						);
				//Left leg bottom
				ch.addHitbox(
						new Vector2 ( -17f * flipX , -43f ) ,
						new Vector2 ( 10f , 18f ) ,
						HitboxType.CHARACTER,
						"leftlegD",
						0 , 0
						);
				//Left leg top
				ch.addHitbox(
						new Vector2 ( -5.5f * flipX , 35f ) ,
						new Vector2 ( 11f , 16f ) ,
						HitboxType.CHARACTER,
						"leftlegU",
						0 , 0
						);
				
				
			}
			
		});
		
		hsh.put( 8 , new CodeSnippet(){
			
			public void run( Character ch )
			{
				
				createCharacterHitboxes ( ch );
				
			}
			
			private void createCharacterHitboxes ( Character ch )
			{
				
				ch.removeAllHitboxes(null);
				
				float flipX = ch.getFlipFactor();
				
				//More fun
				
				//Top hitbox
				ch.addHitbox(
						new Vector2 ( 41f * flipX , 18f ) ,
						new Vector2 ( 54f , 12f ) ,
						HitboxType.CHARACTER ,
						"top" ,
						0 , 0
						);
				//Mid hitbox
				ch.addHitbox(
						new Vector2 ( 7f * flipX , -5f ) ,
						new Vector2 ( 20f , 28f ) ,
						HitboxType.CHARACTER,
						"mid" ,
						0 , 0
						);
				//Right leg
				ch.addHitbox(
						new Vector2 ( 23f * flipX , -31f ),
						new Vector2 ( 10f  , 42f ),
						HitboxType.CHARACTER,
						"rightleg",
						0 , 0
						);
				//TODO: Left leg
			
				
				
			}			
			
		});
		
		
		//This snippet creates the tiger shot
		hsh.put( 10 , new CodeSnippet(){
			
			public void run ( Character ch )
			{
				
				createTigerShot ( ch );
				
			}
			
			private void createTigerShot ( Character ch )
			{
				
				boolean ch_is_flipped = ch.is_flipped();
				
				float flipX = ch.getFlipFactor();
								
				Body chBody = ch.getBody();
				
				World w = ch.getWorld();
				
				HashMap < String , HitboxEntity > hboxEntityMap
							= ch.getHboxEntityMap();
				
				BodyDef bodyDef = new BodyDef();
				
				bodyDef.type = BodyType.KinematicBody;
				
				
				
				
				String entityID = "ts" + (Manic.counter++);
				
				
				//Create hitbox entity
				HitboxEntity tigershot = new HitboxEntity (
						bodyDef , w ,
						new Vector2 ( chBody.getPosition().x * Settings.PPM 
											+ ( 76f / Manic.SCALE * flipX ), 
									 chBody.getPosition().y * Settings.PPM 
									 + ( 15 / Manic.SCALE ) ) ,
						ch.getSpriteBatch() , 
						"tigershot" , 
						entityID ,
						hboxEntityMap 
						);
				
//				//Set platform bit on all HitboxEntity fixtures
//				for ( Fixture f : tigershot.getBody().getFixtureList())
//					f.getFilterData().maskBits |= Settings.BIT_PLATFORM;
				
				
				
				//Set orientation
				tigershot.set_is_flipped( ch_is_flipped );
				tigershot.setRenderLocation(-11, -14);
				
				//Create hitbox
				tigershot.addHitbox(
						new Vector2 ( 7.5f * flipX , 0f ) ,
						new Vector2 ( 5f , 14f ) ,
						HitboxType.DAMAGING ,
						"hitbox" ,
						5f , 24 
						);
				
				//Set linear velocity
				tigershot.getBody().setLinearVelocity( 300 * flipX / Settings.PPM , 0 );
						
				
				
			}
			
		});
		
		
		hsh.put( 50 , new CodeSnippet(){
			
			public void run( Character ch )
			{
				
				ch.acceptInput();
				
				ch.setMove("sagatstand");
				
			}
			
		});
		
		
		ObjectTimeline< CodeSnippet > code
			= new ObjectTimeline< CodeSnippet >(
					hsh , length , delay , loop , get_on_no_keyframe );
		
		Move move = new Move ( code );
		
		moves.put( "sagattigershot", move );
		
		

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@SuppressWarnings("unused") //He'll get to it later I believe
	private void createSagatAerial()
	{
		
		HashMap < Integer , CodeSnippet > hsh
			= new HashMap < Integer , CodeSnippet >();
		
		int length = 24;
		float delay = Manic.STEP;
		boolean loop = false;
		boolean get_on_no_keyframe = false;
		
		
		hsh.put( 0 , new CodeSnippet(){
			
			public void run ( Character ch )
			{
				
				createAnimation ( ch );
				
				playAttackSound();
				
				updateRenderLocationSagatKick ( ch );
				
				createHitboxesSagatKick1 ( ch );
				
				ch.denyInput();
				
			}
			
			private void createAnimation ( Character ch )
			{
				
				ObjectTimeline <TextureRegion> a
					= Manic.res_animations.get("sagatairkick");
				
				ch.setAnimation(a);
				
				
			}
			
		});
		
		hsh.put( 4 , new CodeSnippet(){
			
			public void run ( Character ch )
			{
				
				createHitboxesSagatKick2 ( ch );
				
			}
			
			
			
		});
		
		hsh.put( 8 , new CodeSnippet(){
			
			public void run ( Character ch )
			{
				
				createHitboxesSagatKick3 ( ch , 7f , 30);
				
			}
			
		});
		
		hsh.put( 16 , new CodeSnippet(){
			
			public void run ( Character ch )
			{
				
				createHitboxesSagatKick2 ( ch );
				
			}
			
		});
		
		hsh.put( 20 , new CodeSnippet(){
			
			public void run ( Character ch )
			{
				
				createHitboxesSagatKick1 ( ch );
				
			}
			
		});
		
		hsh.put(  24 ,  new CodeSnippet(){
			
			public void run ( Character ch )
			{
				
				ch.acceptInput();
				
				ch.setMove("sagatstand");
				
			}
			
		});
		
		ObjectTimeline < CodeSnippet > code
			= new ObjectTimeline < CodeSnippet > (
					hsh , length , delay , loop , get_on_no_keyframe );
		
		Move move = new Move ( code );
		
		moves.put( "sagatairkick" ,  move );
		
		
		
	}
	
	private void createSagatGroundKick ()
	{
		
		HashMap < Integer , CodeSnippet > hsh
			= new HashMap < Integer , CodeSnippet >();
		
		int length = 36;
		float delay = Manic.STEP;
		
		boolean loop = false;
		boolean get_on_no_keyframe = false;
		
		
		hsh.put( 0 , new CodeSnippet(){
			
			public void run ( Character ch )
			{
			
				createAnimation ( ch );
				
				playAttackSound ();
				
				updateRenderLocationSagatKick ( ch );
				
				createHitboxesSagatKick1 ( ch );
				
				ch.denyInput();
				
			}
			
			private void createAnimation ( Character ch )
			{
				
				ObjectTimeline < TextureRegion > a
					= Manic.res_animations.get( "sagatgroundkick" );
				
				ch.setAnimation(a);
				
			}
			
			
			
		});
		
		hsh.put ( 6 , new CodeSnippet(){
			
			public void run ( Character ch )
			{
				
				createHitboxesSagatKick2 ( ch );
				
			}
			
		});
		
		hsh.put( 12 , new CodeSnippet(){
			
			public void run ( Character ch )
			{
				
				createHitboxesSagatKick3 ( ch , 10f , 45 );
				
			}
			
		});
		
		hsh.put( 24 , new CodeSnippet(){
			
			public void run ( Character ch )
			{
				
				createHitboxesSagatKick2 ( ch );
				
			}
			
		});
		
		hsh.put( 30 , new CodeSnippet(){
			
			public void run ( Character ch )
			{
				
				createHitboxesSagatKick1 ( ch );
				
			}
			
		});
		
		hsh.put( 36 , new CodeSnippet(){
			
			public void run ( Character ch )
			{
				
				ch.acceptInput();
				
				ch.setMove("sagatstand");
				
			}
			
		});
		
		ObjectTimeline < CodeSnippet > code
			= new ObjectTimeline < CodeSnippet > (
					hsh , length ,  delay , loop , get_on_no_keyframe 
					);
		
		Move move = new Move ( code );
		
		moves.put( "sagatgroundkick" , move  );
		
	}
	
	private void updateRenderLocationSagatKick( Character ch )
	{
		
		if ( !ch.is_flipped())
			ch.setRenderLocation(  -28f , -68f );
		else
			ch.setRenderLocation(-52f, -68f);
		
	}
	
	private void createHitboxesSagatKick1 ( Character ch )
	{
		
		float flipX = ch.getFlipFactor();
		
		ch.removeAllHitboxes(null);
		
		ch.addHitbox (
				new Vector2 ( -2.5f * flipX , -25f ) ,
				new Vector2 ( 27f , 60f ) ,
				HitboxType.CHARACTER ,
				"hurtbox" ,
				0 , 0
				);
		
	}
	
	private void createHitboxesSagatKick2 ( Character ch )
	{
		
		float flipX = ch.getFlipFactor();
		
		ch.removeAllHitboxes ( null );
		
		ch.addHitbox(
				new Vector2 ( -4f * flipX , -18f ) ,
				new Vector2 ( 26f , 46f) ,
				HitboxType.CHARACTER,
				"top" ,
				0 , 0 
				);
		
		ch.addHitbox(
				new Vector2 ( 15.5f * flipX , -47.5f) ,
				new Vector2 ( 11f , 37f ) ,
				HitboxType.CHARACTER,
				"rightLeg" ,
				0 , 0
				);
		
	}
	
	//This creates the hitbox of the attack
	private void createHitboxesSagatKick3 ( Character ch , float damage , int hitstun )
	{
		
		float flipX = ch.getFlipFactor();
		
		ch.removeAllHitboxes(null);
		
		//Create hurtbox
		ch.addHitbox( 
				new Vector2 ( -4f * flipX , -19.5f ) ,
				new Vector2 ( 28 , 35 ) ,
				HitboxType.CHARACTER ,
				"top" ,
				0 , 0
				);
		
		//Create hitbox
		ch.addHitbox(
				new Vector2 (  33f * flipX , -46f ) ,
				new Vector2 (  32f , 22f ) ,
				HitboxType.DAMAGING ,
				"leg" ,
				damage , hitstun
				);
				
		
	}
	
	private void playAttackSound()
	{
		
		Sound attackSound = Gdx.audio.newSound( Gdx.files.internal("../resources/sounds/attack.wav"));
		
		attackSound.play();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public void createSagatHitstun ()
	{
	
		HashMap < Integer , CodeSnippet > hsh
			= new HashMap < Integer , CodeSnippet >();
		
		int length = 1;
		float delay = Manic.STEP;
		
		boolean loop = false;
		boolean get_on_no_keyframe = false;
		
		hsh.put( 0 , new CodeSnippet(){
			
			public void run ( Character ch )
			{
			
				createAnimation ( ch );
			
				updateRenderLocation ( ch );
			
				createHitboxes ( ch );
			
				ch.denyInput();
				
			}
			
			private void createAnimation ( Character ch )
			{
				
				ObjectTimeline < TextureRegion > a 
					= Manic.res_animations.get("sagathit");
				
				ch.setAnimation(a);
				
			}
			
			private void updateRenderLocation ( Character ch )
			{
				if ( ch.is_flipped() )
				{
					ch.setRenderLocation( -28f , -52f );
				}
				else
				{
					ch.setRenderLocation ( -22f , -52f );
				}
			}
			
			private void createHitboxes ( Character ch )
			{
				
				ch.removeAllHitboxes( null );
				
				ch.addHitbox(
						new Vector2 ( 0 , 0 ) ,
						new Vector2 ( 44 , 104 ) ,
						HitboxType.CHARACTER , 
						"hurtbox" ,
						0 , 0
						);
				
			}
			
		});
		
		hsh.put( 1 , new CodeSnippet(){
			
			public void run ( Character ch )
			{
				
				ch.incrementHitstun();
				
				if ( ch.outOfHitstun() )
				{
					//TODO: Do stuff
					
					ch.acceptInput();
					
					ch.setMove("sagatstand");
					
				}
				
			}
			
		});
		
		ObjectTimeline < CodeSnippet > code 
			= new ObjectTimeline < CodeSnippet >(
					hsh , length , delay , loop , get_on_no_keyframe 
					);
		
		Move move = new Move ( code );
		
		moves.put("sagathit", move);
		
		
	}	
	
	
	
}