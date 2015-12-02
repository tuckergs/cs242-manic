package com.manic.game.resource_management;

import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.manic.game.Manic;
import com.manic.game.ObjectTimeline;
import com.manic.game.moves.CodeSnippet;
import com.manic.game.moves.Hitbox;
import com.manic.game.moves.HitboxGroup;
import com.manic.game.moves.HitboxType;
import com.manic.game.moves.Move;
import com.manic.game.entities.Character;

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

	}




	//Creators
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

	private void createHitboxesSagatStand ( Character ch )
	{

		Body body = ch.getBody();


		ch.removeAllHitboxes(null);



		/*
		//Create big hitbox
		ch.addHitbox ( 
						new Vector2 ( 0f , 0f ) ,
						new Vector2 ( 44f , 104f ) ,
						HitboxType.CHARACTER ,
						"hurtbox" , 
						0 , 0
					);
		 */





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

	}


	public void createHighTigerShotMove ()
	{

		//TODO: Do stuff

	}

}
