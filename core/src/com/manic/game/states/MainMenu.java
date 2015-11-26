package com.manic.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.manic.game.InputHandler;
import com.manic.game.InputProcessor;
import com.manic.game.Manic;

public class MainMenu extends GameState{
	
	private static final float buttonHeight = 50f;
	private static final float buttonWidth = 200f;
	private static final float buttonBuffer = 10f;
	private OrthographicCamera camera;
	private Body itemBody;
	private SpriteBatch batch;
    private BitmapFont font;
    private Stage stage;
    private Skin skin;
    private State PLAY;
    private World world;
	
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
	//	update (1f);
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    	gsm=super.gsm;
        Stage stage = new Stage();
        //Gdx.input.setInputProcessor(stage);// Make the stage consume events
        //Create Skin
        createBasicSkin();
        
        //Create Start Button
        TextButton startButton = new TextButton("Press Space to Start", skin); // Use the initialized skin
        startButton.setPosition(Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth()/8 , Gdx.graphics.getHeight()/2);
        
        
        /*Create Character Select Button
        TextButton characterSelectButton = new TextButton("PRESS SPACE TO START", skin); // Use the initialized skin
        characterSelectButton.setPosition(Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth()/8 , (Gdx.graphics.getHeight()/2)-startButton.getHeight()-buttonBuffer);
        */stage.addActor(startButton);
      //  stage.addActor(characterSelectButton); 
        stage.act();
        stage.draw();

	}

	@Override
	public void dispose() {
		
	}

	private void createBasicSkin(){
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
 
	}
}
