package com.manic.game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class Start extends GameState {
	
	private BitmapFont font = new BitmapFont();
	
	public Start(GameStateManager gsm) {
		super(gsm);
	}
	
	public void handleInput() {}
	public void update(float dt) {}
	public void render() {
		sb.setProjectionMatrix(camera.combined);
		sb.begin();
		font.draw(sb, "play state", 100, 100);
		sb.end();
	}
	
	public void dispose() {}
}
