package com.manic.game;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Input.Keys;

/**
 * 
 * @author Stephen Lorenz
 * 
 * @brief InputProcessor tells InputHandler if a key is activated.
 * 
 * Uses the pre-existing gdx input/key recognition to send boolean 
 * information to the InputHandler's arrays. 
 * 
 */


public class InputProcessor extends InputAdapter
{
	
	public boolean keyDown(int key)
	{
		if (key == Keys.SPACE) {InputHandler.setKey(InputHandler.KEY_SPACE, true);}
		
		if (key == Keys.A) {InputHandler.setKey(InputHandler.KEY_A, true);}
		
		if (key == Keys.D) {InputHandler.setKey(InputHandler.KEY_D, true);}
		
		if (key == Keys.S) {InputHandler.setKey(InputHandler.KEY_S, true);}
		
		if (key == Keys.UP) {InputHandler.setKey(InputHandler.KEY_UP, true);}
		
		if (key == Keys.LEFT) {InputHandler.setKey(InputHandler.KEY_LEFT, true);}
		
		if (key == Keys.RIGHT) {InputHandler.setKey(InputHandler.KEY_RIGHT, true);}
		
		if (key == Keys.DOWN) {InputHandler.setKey(InputHandler.KEY_DOWN, true);}
		
		if (key == Keys.P){InputHandler.setKey(InputHandler.KEY_P, true);}
		
		if (key == Keys.Q){ InputHandler.setKey(InputHandler.KEY_Q, true);}
		
		if (key == Keys.E){InputHandler.setKey(InputHandler.KEY_E, true);}
		
		if (key == Keys.U){ InputHandler.setKey(InputHandler.KEY_U, true);}
		
		if (key == Keys.O){InputHandler.setKey(InputHandler.KEY_O, true);}
		return true;
	}
	
	public boolean keyUp(int key)
	{
		if (key == Keys.SPACE) {InputHandler.setKey(InputHandler.KEY_SPACE, false);}
		
		if (key == Keys.A) {InputHandler.setKey(InputHandler.KEY_A, false);}
		
		if (key == Keys.D) {InputHandler.setKey(InputHandler.KEY_D, false);}
		
		if (key == Keys.S) {InputHandler.setKey(InputHandler.KEY_S, false);}
		
		if (key == Keys.UP) {InputHandler.setKey(InputHandler.KEY_UP, false);}
		
		if (key == Keys.LEFT) {InputHandler.setKey(InputHandler.KEY_LEFT, false);}
		
		if (key == Keys.RIGHT) {InputHandler.setKey(InputHandler.KEY_RIGHT, false);}
		
		if (key == Keys.DOWN) {InputHandler.setKey(InputHandler.KEY_DOWN, false);}
		
		if (key == Keys.P) {InputHandler.setKey(InputHandler.KEY_P, false);}
		
		if (key == Keys.Q){InputHandler.setKey(InputHandler.KEY_Q, false);}
		
		if (key == Keys.E){InputHandler.setKey(InputHandler.KEY_E, false);}

		if (key == Keys.U){InputHandler.setKey(InputHandler.KEY_U, false);}
		
		if (key == Keys.O){InputHandler.setKey(InputHandler.KEY_O, false);}
		
		return true;
	}
}