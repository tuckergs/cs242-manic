package com.manic.game.states;

import java.util.Stack;

import com.manic.game.Manic;

public class GameStateManager {
	protected Manic manic;
	protected Stack<GameState> gameStates;
	protected enum State{
		MAINMENU, PLAY, PAUSE, RESUME
	}
	
	public GameStateManager(Manic manic){
		this.manic = manic;
		gameStates = new Stack<GameState>();
		//addState(State.PLAY);
		addState(State.MAINMENU);
		
	}
	
	public Manic getManic(){
		return manic;
	}
	
	protected GameState getState(State state){
 		if (state == State.PLAY){
 			return new Start(this);
 		}else if( state == State.MAINMENU){
 			return new MainMenu(this);
		}else if(state == State.PAUSE){
			
		}
 		return new Start(this);
	}
	
	public void setState(State state){
		deleteState();
		addState(state);
		
	}
	
	//add to top of stack
	public void addState(State state){
		gameStates.push(getState(state));
	}
	
	//remove top of stack
	public void deleteState(){
		GameState state = gameStates.pop();
		state.dispose();
	}
	
	public void update(float dt){
		gameStates.peek().update(dt);
	}
	
	public void render(){
		gameStates.peek().render();
	}
}
