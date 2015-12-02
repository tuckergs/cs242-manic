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
		
		final String SAGATSTAND = "sagatstand";
		
		HashMap < Integer , CodeSnippet > hsh 
				= new HashMap< Integer , CodeSnippet >();
		
		int length = 8;
		float delay = Manic.STEP;
		boolean loop = false;
		boolean get_on_no_keyframe = false;
		
		
		hsh.put( 0 , new CodeSnippet(){
			
			public void run( Character ch )
			{
				
				//Set the animations
				createAnimation ( ch );
			
				
				//Create hitboxes
				createHitboxes ( ch );
				
				
			}
			
			
			public void createAnimation( Character ch )
			{
				
				ObjectTimeline<TextureRegion> a
						= Manic.res_animations.get( SAGATSTAND );
		
				ch.setAnimation( a );
				
			}
			
			public void createHitboxes( Character ch )
			{
				
				Body body = ch.getBody();
				
				
				
				
				
				/*
				//Create big hitbox
				group.add( "hi", 
						new Hitbox (
								body ,
								new Vector2 ( 0f , 0f ) ,
								new Vector2 ( 44f , 104f ) ,
								HitboxType.CHARACTER ,
								"hurtbox" , 
								0 , 0
								));
				*/
				
				
				
				
				
				//Create top box
				ch.addHitbox (
								new Vector2 ( 3f , 31.5f ) ,
								new Vector2 ( 34f , 23f ) ,
								HitboxType.CHARACTER ,
								"hurtbox" , 
								0 , 0 
							);
				
				//Create hip box
				ch.addHitbox (
								new Vector2 ( -2 , -2 ) ,
								new Vector2 ( 28 , 34) ,
								HitboxType.CHARACTER ,
								"hurtbox" ,
								0 , 0
							);
				
				//Create leg box
				ch.addHitbox (
								new Vector2 ( -4 , -35.5f ) ,
								new Vector2 ( 34 , 29 ) ,
								HitboxType.CHARACTER ,
								"hurtbox" ,
								0 , 0
							);
							
				
				
				
			}
			
			
		});
		
		
		ObjectTimeline< CodeSnippet > code
				= new ObjectTimeline < CodeSnippet >(
						hsh , length , delay , loop , get_on_no_keyframe );
		
		Move move = new Move ( code );
		
		moves.put( SAGATSTAND , move );
		
	}
	
	
	public void createHighTigerShotMove ()
	{
		
		//TODO: Do stuff
		
	}
	
}
