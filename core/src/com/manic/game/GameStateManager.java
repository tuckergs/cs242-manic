package com.manic.game;

import java.util.Stack;

public class GameStateManager {
	private Manic manic;
	private Stack<GameState> gameStates;
	private enum States
	{
		PLAY, ROUND, EXIT
	}
	
	public GameStateManager(Manic manic)
	{
		this.manic = manic;
		gameStates = new Stack<GameState>();
		
		addState(States.PLAY);
	}
	
	public Manic getManic()
	{
		return manic;
	}
	
	private GameState getState(States state)
	{
 		if (state == States.PLAY)
 		{
 			return new Start(this);
 		}
 		else
 		{
 			return null;
 		}
	}
	
	public void setState(States state)
	{
		deleteState();
		addState(state);
		
	}
	
	//add to top of stack
	public void addState(States state)
	{
		gameStates.push(getState(state));
	}
	
	//remove top of stack
	public void deleteState() 
	{
		GameState state = gameStates.pop();
		state.dispose();
	}
	
	public void update(float dt)
	{
		gameStates.peek().update(dt);
	}
	
	public void render()
	{
		gameStates.peek().render();
	}
}
