package com.manic.game;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Input.Keys;

public class InputProcessor extends InputAdapter
{
	
	public boolean keyDown(int key)
	{
		if (key == Keys.Z) {
			
			InputHandler.setKey(InputHandler.BUTTON1, true);
		}
		else if (key == Keys.X) {
			
			InputHandler.setKey(InputHandler.BUTTON2, true); 
		}
		
		return true;
	}
	
	public boolean keyUp(int key)
	{
		if (key == Keys.Z) {
			
			InputHandler.setKey(InputHandler.BUTTON1, false);
		}
		else if (key == Keys.X) {
			
			InputHandler.setKey(InputHandler.BUTTON2, false); 
		}
		
		return true;
	}
}
