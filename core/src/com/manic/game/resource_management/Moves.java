package com.manic.game.resource_management;

import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.manic.game.Manic;
import com.manic.game.ObjectTimeline;
import com.manic.game.moves.CodeSnippet;
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
		
		createStandMove( "sagat" );
		
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
	
	private void createStandMove( String charID )
	{
		
		final String CHAR_ID_STAND = charID + "stand";
		
		HashMap < Integer , CodeSnippet > hsh 
				= new HashMap< Integer , CodeSnippet >();
		
		int length = 8;
		float delay = Manic.STEP;
		boolean loop = false;
		
		
		hsh.put( 0 , new CodeSnippet(){
			
			public void run( Character ch )
			{
				
				ObjectTimeline<TextureRegion> a
						= Manic.res_animations.get( CHAR_ID_STAND );
				
				ch.setAnimation( a );
				
			}
			
		});
		
		
		ObjectTimeline< CodeSnippet > code
				= new ObjectTimeline < CodeSnippet >(
						hsh , length , delay , loop );
		
		Move move = new Move ( code );
		
		moves.put( CHAR_ID_STAND , move );
		
	}
	
	
	public void createHighTigerShotMove ()
	{
		
		//TODO: Do stuff
		
	}
	
}
