package com.manic.game.moves;

//import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.manic.game.ObjectTimeline;
import com.manic.game.entities.Character;

/**
 * 
 * @class Move
 * 
 * @brief This represents an action a Character can do
 * 
 * This contains an ObjectTimeline of CodeSnippets,
 * so the timeline returns code based on what the current frame
 * of the move is.
 * 
 * @author gabe
 * 
 * @contact gst06@roadrunner.com
 *
 */
public class Move implements Cloneable{

	private ObjectTimeline< CodeSnippet > snippets;
	
	
	public Move ( ObjectTimeline<CodeSnippet> snips )
	{
		
		snippets = snips.clone();
		
	}
	
	public void update ( float dt )
	{
		
		snippets.update(dt);
		
	}
	
	public void execute ( Character ch ){
		
		CodeSnippet code = snippets.getCurrentObj();
		if (code != null)
			code.run(ch);
		
	}
	
	public Move clone()
	{
		
		return new Move ( snippets.clone() );
		
	}
	
}
