package com.manic.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.manic.game.InputHandler;
import com.manic.game.Manic;
import com.manic.game.MyContactListener;
import com.manic.game.Settings;
import com.manic.game.entities.Entity;
import com.manic.game.entities.GameEntity;
import com.manic.game.entities.Player;
import com.manic.game.entities.Character;

import box2dLight.PointLight;
import box2dLight.RayHandler;

import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

//pixels per meter
import static com.manic.game.Settings.PPM;
import static com.manic.game.Settings.SCALE_PPM;

public class Start extends GameState {
	private World world;
	
	private final float GRAVITY_X = 0;
	private final float GRAVITY_Y = -2.81f;
	//private final float JUMP_FORCE_NEWTONS = 125;
	private final float JUMP_FORCE_NEWTONS = 180;
	private final float MOVEMENT_SPEED_NEWTONS = 3f;
	private Box2DDebugRenderer debugRenderer;
	private OrthographicCamera box2DCamera;
	private MyContactListener contactListener;
	private Body playerBody;
	private RayHandler handler;
	private static Skin skin;
	private Stage stage = new Stage();
	
	private Player p;
	private Character sagat;	
	//score tracker
	public static int points = 0;
	private CharSequence charSeq;
	private Label scoreBoard;
	
	public Start(GameStateManager gsm) {
		super(gsm);
	
		//Create Skin
		createSkin();
		//Point Board
        charSeq = "SCORE: "+ points;
        scoreBoard = new Label(charSeq, skin);
        scoreBoard.setPosition((float) (Gdx.graphics.getWidth()*.5 - Gdx.graphics.getWidth()*.125) , (float) (Gdx.graphics.getHeight()*.90));
        
		
		//Create world and all its inhabitants
		world = new World(new Vector2(GRAVITY_X, GRAVITY_Y), true);
		contactListener = new MyContactListener();
		world.setContactListener(contactListener);
		
		debugRenderer = new Box2DDebugRenderer();
		debugRenderer.setDrawBodies(true);;
		BodyDef bodyDef= new BodyDef();
		FixtureDef fixtureDef = new FixtureDef();
		
		//create platform
		bodyDef.position.set(160/PPM, 120/PPM);
		bodyDef.type = BodyType.StaticBody; //unaffected by gravity
		Body body = world.createBody(bodyDef);

		
		PolygonShape box = new PolygonShape();
		
		
		
		box.setAsBox(50/PPM, 5/PPM); //100x10
		
		
		//TESTER
		fixtureDef.shape = box;
		fixtureDef.filter.categoryBits = Settings.BIT_PLATFORM;
		fixtureDef.filter.maskBits = Settings.BIT_PLAYER | Settings.BIT_BALL; //it can collide with both the player and ball
		body.createFixture(fixtureDef).setUserData("platform");

		
		
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//test platforms
		//USES DIMENSIONS OF V_WIDTH, V_HEIGHT
		//bottom plat
		bodyDef.position.set(0/PPM, 0/PPM);
		body = world.createBody(bodyDef);
		box.setAsBox(320/PPM, 2/PPM); //100x10
		body.createFixture(fixtureDef).setUserData("platform");
		
		//left side plat
		bodyDef.position.set(0/PPM, 0/PPM);
		body = world.createBody(bodyDef);
		box.setAsBox(2/PPM, 240/PPM); //100x10
		body.createFixture(fixtureDef).setUserData("platform");
		
		//right side plat
		bodyDef.position.set(320/PPM, 0/PPM);
		body = world.createBody(bodyDef);
		box.setAsBox(2/PPM, 240/PPM); //100x10
		body.createFixture(fixtureDef).setUserData("platform");
		
		bodyDef.position.set(0/PPM, 240/PPM);
		body = world.createBody(bodyDef);
		box.setAsBox(320/PPM, 2/PPM); //100x10
		body.createFixture(fixtureDef).setUserData("platform");
		
		//shaqs badass practice hoop of true greatness. this is how he practices in real life
		
		bodyDef.position.set(300/PPM, 0/PPM);
		body = world.createBody(bodyDef);
		box.setAsBox(4/PPM, 155/PPM); //100x10
		body.createFixture(fixtureDef).setUserData("platform");
		
		fixtureDef.filter.maskBits = Settings.BIT_PLAYER;
		bodyDef.position.set(300/PPM, 180/PPM);
		body = world.createBody(bodyDef);
		box.setAsBox(25/PPM, 25/PPM); //100x10
		body.createFixture(fixtureDef).setUserData("platform");
		
		bodyDef.position.set(300/PPM, 180/PPM);
		body = world.createBody(bodyDef);
		box.setAsBox(25/PPM, 25/PPM); //100x10
		body.createFixture(fixtureDef).setUserData("platform");

		bodyDef.position.set(300/PPM, 165/PPM);
		body = world.createBody(bodyDef);
		box.setAsBox(8/PPM, 8/PPM); //100x10
		body.createFixture(fixtureDef).setUserData("hoop");
		
		//loads of balls
		CircleShape circle = new CircleShape();
		circle.setRadius(5/PPM);
		fixtureDef.shape = circle;
		fixtureDef.density = 75.0f;
		fixtureDef.restitution = 1.0f; //max bounce
		fixtureDef.filter.categoryBits = Settings.BIT_BALL; //it is a type ball
		fixtureDef.filter.maskBits = Settings.BIT_PLATFORM | Settings.BIT_PLAYER | Settings.BIT_BALL; //can collide with ground
		bodyDef.position.set(153/PPM, 220/PPM);
		bodyDef.type = BodyType.DynamicBody;
		body = world.createBody(bodyDef);
		bodyDef.position.set(10/PPM, 100/PPM);
		
		System.out.println("0.5f" + body.getMass());
		body = world.createBody(bodyDef);
		body.createFixture(fixtureDef).setUserData("ball");
		
		bodyDef.position.set(20/PPM, 100/PPM);
		body = world.createBody(bodyDef);
		body.createFixture(fixtureDef).setUserData("ball");
		
		bodyDef.position.set(40/PPM, 100/PPM);
		body = world.createBody(bodyDef);
		body.createFixture(fixtureDef).setUserData("ball");
		
		bodyDef.position.set(60/PPM, 100/PPM);
		body = world.createBody(bodyDef);
		body.createFixture(fixtureDef).setUserData("ball");
		
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		//create ball guy
		bodyDef.position.set(153/PPM, 220/PPM);
		bodyDef.type = BodyType.DynamicBody;
		body = world.createBody(bodyDef);
		
		//CircleShape circle = new CircleShape();
		circle.setRadius(5/PPM);
		fixtureDef.shape = circle;
		fixtureDef.restitution = 1.0f; //max bounce
		fixtureDef.filter.categoryBits = Settings.BIT_BALL; //it is a type ball
		fixtureDef.filter.maskBits = Settings.BIT_PLATFORM | Settings.BIT_PLAYER; //can collide with ground
		body.createFixture(fixtureDef).setUserData("ball");
		
		
		//create player
		sagat = new Character
				( world , new Vector2 ( 200 , 200 ) , new Vector2 ( 44  , 104 ),
				new SpriteBatch() , "sagat");
		
		playerBody = sagat.getBody();
		
		
		
		
		//setup box2DCamera
		box2DCamera = new OrthographicCamera();
		box2DCamera.setToOrtho(false, Manic.V_WIDTH/PPM, Manic.V_HEIGHT/PPM);
		
		handler = new RayHandler(world);
		handler.setAmbientLight(.5f);
		handler.setCombinedMatrix(camera.combined);
		handler.setShadows(true);
		
		PointLight light = new PointLight(handler, 200, Color.MAGENTA, 150f, 0, 240);
		PointLight light2 = new PointLight(handler, 200, Color.CYAN, 150f, 160, 240);
		PointLight light3 = new PointLight(handler, 200, Color.MAGENTA, 150f, 320, 240);
		
		light.setSoftnessLength(100f);
		light2.setSoftnessLength(100f);
		light3.setSoftnessLength(100f);
		stage.addActor(scoreBoard);
	}
	
	public void handleInput()
	{
		//player can jump
		if (InputHandler.isPressed(InputHandler.KEY_SPACE))
		{
			if (contactListener.isOnGround()) {
				//in newtons. player weighs 1kg, -9.78 gravity
				//apply upward force when on the ground
				playerBody.applyForceToCenter(0, JUMP_FORCE_NEWTONS, true);
			}
			points ++;
		}
		
		if (InputHandler.isPressed(InputHandler.KEY_S))
		{
			if (!contactListener.isOnGround()) {
				//apply downward force when airborne
				playerBody.applyForceToCenter(0, -JUMP_FORCE_NEWTONS, true);
			}
		}
		
		if (InputHandler.isDown(InputHandler.KEY_D))
		{
			//playerBody.applyForce(new Vector2(3f, 0), playerBody.getPosition(), true);
			playerBody.applyForceToCenter(MOVEMENT_SPEED_NEWTONS, 0, true);
		}
		
		if (InputHandler.isDown(InputHandler.KEY_A))
		{
			playerBody.applyForceToCenter(-MOVEMENT_SPEED_NEWTONS, 0, true);
		}
		if(InputHandler.isPressed(InputHandler.KEY_P)){
			System.out.println("PAUSE");
			
			Manic.changeStateLock = true;
			
			gsm.setState(GameStateManager.State.PAUSE);        
		}
	
	}
	
	public void createSkin(){
		//Create a font
				BitmapFont font = new BitmapFont();
				skin = new Skin();
				skin.add("default", font);
		 
				//Create a texture
				Pixmap pixmap = new Pixmap((int)Gdx.graphics.getWidth()/4,(int)Gdx.graphics.getHeight()/10, Pixmap.Format.RGB888);
				pixmap.setColor(Color.LIME);
				pixmap.fill();
				skin.add("background",new Texture(pixmap));
				Label.LabelStyle labelStyle = new Label.LabelStyle();
		  		labelStyle.fontColor = Color.RED;
		  		Pixmap titlePixmap = new Pixmap((int)Gdx.graphics.getWidth()/4,(int)Gdx.graphics.getHeight()/10, Pixmap.Format.RGB888);
				titlePixmap.setColor(Color.CLEAR);
				titlePixmap.fill();
				skin.add("titleBackground",new Texture(titlePixmap));
		  		labelStyle.background = skin.newDrawable("titleBackground",Color.CLEAR);
		  		labelStyle.font = skin.getFont("default");
		  		skin.add("default", labelStyle);
	}
	public void update(float dt)
	{
		handleInput();
		
		sagat.update(dt);
		
		world.step(dt, 6, 2);
	}
	public void render() {
		//clear
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act();
        stage.draw();
		sagat.render();
		debugRenderer.render(world, box2DCamera.combined);
		handler.updateAndRender();
	}
	
	public void dispose() {}
}
