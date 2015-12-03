package com.manic.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.manic.game.InputHandler;
import com.manic.game.Manic;

public class MainMenu extends GameState{
	
    private static Skin skin;
	
	public MainMenu(GameStateManager gsm){
		super (gsm);
	}

	@Override
	public void handleInput() {
		
		if (InputHandler.isPressed(InputHandler.KEY_SPACE)){
			
			System.out.println("SPACE");
			
			Manic.changeStateLock = true;
			
			gsm.setState(GameStateManager.State.PLAY);
        
		}
		
	}

	@Override
	public void update(float dt) {
		handleInput();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    	gsm=super.gsm;
        Stage stage = new Stage();

        //Create Skin
        createSkin();
        
        //Create Title
        CharSequence charSeq;
        charSeq = "MANIC";
        Label title = new Label(charSeq, skin);
        title.setPosition((float) (Gdx.graphics.getWidth()*.49 - Gdx.graphics.getWidth()*.125) , (float) (Gdx.graphics.getHeight()*.70));
        title.setFontScale(4);
        
        
        //Create Start Button
        TextButton startButton = new TextButton("Press Space to Start", skin);
        startButton.setPosition(Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth()/8 , Gdx.graphics.getHeight()/3);
        
        stage.addActor(title);
        stage.addActor(startButton); 
        stage.act();
        stage.draw();

	}

	@Override
	public void dispose() {
		
	}

	public static void createSkin(){
		//Create a font
		BitmapFont font = new BitmapFont();
		skin = new Skin();
		skin.add("default", font);
 
		//Create a texture
		Pixmap pixmap = new Pixmap((int)Gdx.graphics.getWidth()/4,(int)Gdx.graphics.getHeight()/10, Pixmap.Format.RGB888);
		pixmap.setColor(Color.RED);
		pixmap.fill();
		skin.add("background",new Texture(pixmap));
 
		//Create a button style
		TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
  		textButtonStyle.up = skin.newDrawable("background", Color.GRAY);
  		textButtonStyle.down = skin.newDrawable("background", Color.DARK_GRAY);
  		textButtonStyle.checked = skin.newDrawable("background", Color.DARK_GRAY);
  		textButtonStyle.over = skin.newDrawable("background", Color.LIGHT_GRAY);
  		textButtonStyle.font = skin.getFont("default");
  		skin.add("default", textButtonStyle);
  		
  		//Create Title Style
  		Label.LabelStyle labelStyle = new Label.LabelStyle();
  		labelStyle.fontColor = Color.RED;
  		Pixmap titlePixmap = new Pixmap((int)Gdx.graphics.getWidth()/4,(int)Gdx.graphics.getHeight()/10, Pixmap.Format.RGB888);
		titlePixmap.setColor(Color.CLEAR);
		titlePixmap.fill();
		skin.add("titleBackground",new Texture(titlePixmap));
  		labelStyle.background = skin.newDrawable("titleBackground",Color.CLEAR);
  		labelStyle.font = skin.getFont("default");
  		font.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Nearest);
  		skin.add("default", labelStyle);
  		
	}
}
