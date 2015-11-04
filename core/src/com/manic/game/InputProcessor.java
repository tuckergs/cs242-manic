package com.manic.game;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Input.Keys;

public class InputProcessor extends InputAdapter
{
	
	public boolean keyDown(int key)
	{
		if (key == Keys.SPACE) {
			
			InputHandler.setKey(InputHandler.KEY_SPACE, true);
		}
		
		if (key == Keys.A) {
			
			InputHandler.setKey(InputHandler.KEY_A, true); 
		}
		
		if (key == Keys.D) {
			
			InputHandler.setKey(InputHandler.KEY_D, true); 
		}
		
		if (key == Keys.S) {
			
			InputHandler.setKey(InputHandler.KEY_S, true); 
		}
		
		return true;
	}
	
	public boolean keyUp(int key)
	{
		if (key == Keys.SPACE) {
			
			InputHandler.setKey(InputHandler.KEY_SPACE, false);
		}
		
		if (key == Keys.A) {
			
			InputHandler.setKey(InputHandler.KEY_A, false); 
		}
		
		if (key == Keys.D) {
		
			InputHandler.setKey(InputHandler.KEY_D, false); 
		}
		
		if (key == Keys.S) {
			
			InputHandler.setKey(InputHandler.KEY_S, false); 
		}
		
		return true;
	}
}
