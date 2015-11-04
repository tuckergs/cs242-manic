package com.manic.game;

public interface Controllable {
	
	public void updatePosition();

	//checks if a player is currently able to be controlled
	//useful for determing if they are stunned or mid-air
	abstract boolean isControllable(int frame);
	
	//abstract boolean isJumping(boolean onGround)
	
	
}
