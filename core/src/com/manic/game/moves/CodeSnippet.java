package com.manic.game.moves;

import com.manic.game.entities.Character;

/**
 * 
 * @interface CodeSnippet
 * 
 * @brief A CodeSnippet with a function that takes a Character as argument
 * 
 * This allows you to hardcode moves in Moves 
 * 
 * @author Gabe Tucker
 * 
 * @contact gst06@roadrunner.com
 *
 */
public interface CodeSnippet {

	public void run ( Character ch );
	
}
