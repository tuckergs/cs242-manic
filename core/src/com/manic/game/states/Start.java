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
import com.manic.game.BodyDestroyer;
import com.manic.game.FixtureDestroyer;
import com.manic.game.InputHandler;
import com.manic.game.Manic;
import com.manic.game.MyContactListener;
import com.manic.game.Settings;
import com.manic.game.entities.Entity;
import com.manic.game.entities.GameEntity;
import com.manic.game.entities.HitboxEntity;
import com.manic.game.entities.Player;
import com.manic.game.moves.Hitbox;
import com.manic.game.moves.HitboxType;
import com.manic.game.entities.Character;

import box2dLight.PointLight;
import box2dLight.RayHandler;

import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

//pixels per meter
import static com.manic.game.Settings.PPM;
import static com.manic.game.Settings.SCALE_PPM;

import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;

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
	private Body player2Body;


	private BodyDestroyer bodyDestroyer;
	private FixtureDestroyer fixtureDestroyer;

	private GameEntity moderatePlatform;
	private GameEntity liberalPlatform;
	private GameEntity conservativePlatform;

	private RayHandler handler;
	private static Skin skin;
	private Stage stage = new Stage();

	private Entity backgroundOfMeow;

	private Character sagat;
	private Character fluffy;

	private static float maxHealth=20f;
	//	private static float healthPoints1 = maxHealth;
	//	private static float healthPoints2 = maxHealth;
	private CharSequence p1HealthCharSeq;
	private CharSequence p2HealthCharSeq;
	private Label p1Health;
	private Label p2Health;
	public static int p1Wins=0;
	public static int p2Wins=0;
	private CharSequence roundChars;
	private Label roundWins;


	public HashMap < String , HitboxEntity > hboxEntities;



	public Start(GameStateManager gsm) {
		super(gsm);


		hboxEntities = new HashMap < String , HitboxEntity >();


		//Create cat background
		backgroundOfMeow = new Entity
				( new Vector2 ( 0 , 0 ) , sb , "catscatscatscatscats" );

		//Update it once and only once
		backgroundOfMeow.update(Manic.STEP);



		//Create body destroyer and fixture destroyer
		bodyDestroyer = new BodyDestroyer();
		fixtureDestroyer = new FixtureDestroyer();


		//Create world and all its inhabitants
		world = new World(new Vector2(GRAVITY_X, GRAVITY_Y), true);


		//Create contact listener, the collision detection thing
		contactListener = new MyContactListener();
		contactListener.bindState(this);
		contactListener.bindBodyDestroyer(bodyDestroyer);
		contactListener.bindFixtureDestroyer(fixtureDestroyer);
		world.setContactListener(contactListener);

		//Create debug render
		debugRenderer = new Box2DDebugRenderer();
		debugRenderer.setDrawBodies(true);;
		BodyDef bodyDef= new BodyDef();
		FixtureDef fixtureDef = new FixtureDef();


		bodyDef.type = BodyType.StaticBody; //unaffected by gravity
		Body body;


		PolygonShape box = new PolygonShape();

		//Set fixture stuff
		fixtureDef.shape = box;
		fixtureDef.filter.categoryBits = Settings.BIT_PLATFORM;
		fixtureDef.filter.maskBits = Settings.BIT_PLAYER | Settings.BIT_BALL;//it can collide with the player and ball

		box.setAsBox(35/SCALE_PPM, 10/SCALE_PPM);
		liberalPlatform = new GameEntity ( bodyDef , world , new Vector2 ( 30 , 100 ) , sb , "platform" );
		liberalPlatform.getBody().createFixture(fixtureDef).setUserData("platform");
		liberalPlatform.update(Manic.STEP);

		box.setAsBox(58/SCALE_PPM, 10/SCALE_PPM); //100x10
		fixtureDef.shape = box;
		moderatePlatform = new GameEntity ( bodyDef , world , new Vector2 ( 160 , 160 ) , sb , "platform2" );
		moderatePlatform.getBody().createFixture(fixtureDef).setUserData("platform");
		moderatePlatform.update(Manic.STEP);

		box.setAsBox(35/SCALE_PPM, 10/SCALE_PPM); //100x10
		fixtureDef.shape = box;
		conservativePlatform = new GameEntity ( bodyDef , world , new Vector2 ( 290 , 100 ) , sb , "platform" );
		conservativePlatform.getBody().createFixture(fixtureDef).setUserData("platform");
		conservativePlatform.update(Manic.STEP);


		bodyDef.position.set(0/PPM, 25/PPM);
		body = world.createBody(bodyDef);
		box.setAsBox(320/PPM, 0/PPM); //100x10
		body.createFixture(fixtureDef).setUserData("platform");

		//left side plat
		bodyDef.position.set(0/PPM, 0/PPM);
		body = world.createBody(bodyDef);
		box.setAsBox(2/PPM, 240/PPM); //100x10
		body.createFixture(fixtureDef).setUserData("platform");

		//left side collector
		bodyDef.type = BodyType.KinematicBody;
		bodyDef.position.set( -10/PPM , (Manic.V_HEIGHT / 2)/PPM );
		body = world.createBody(bodyDef);
		box.setAsBox( -7f/PPM , (Manic.V_HEIGHT / 2)/PPM);
		body.createFixture(fixtureDef).setUserData("obCollector");

		//right side plat
		bodyDef.type = BodyType.StaticBody;
		bodyDef.position.set(320/PPM, 0/PPM);
		body = world.createBody(bodyDef);
		box.setAsBox(2/PPM, 240/PPM); //100x10
		body.createFixture(fixtureDef).setUserData("platform");


		//up plat
		bodyDef.position.set(0/PPM, 240/PPM);
		body = world.createBody(bodyDef);
		box.setAsBox(320/PPM, 2/PPM); //100x10
		body.createFixture(fixtureDef).setUserData("platform");


		/*
		bodyDef.position.set(153/PPM, 220/PPM);
		bodyDef.type = BodyType.DynamicBody;
		body = world.createBody(bodyDef);
		bodyDef.position.set(10/PPM, 100/PPM);
		 */

	//	Vector2 coordinates = new Vector2 ( 0 , 0 );
		//Vector2 dimensions =  new Vector2 ( 10f , 10f );


		//System.out.println("0.5f" + body.getMass());


		/*
		body = world.createBody(bodyDef);
		body.createFixture(fixtureDef).setUserData("ball");
		new Hitbox ( body , coordinates , dimensions , HitboxType.DAMAGING , "ballHbox" , 5 , 0 );
		 */

		bodyDef.type = BodyType.DynamicBody;

		//create player
		sagat = new Character( world , new Vector2 ( 275 , 200 ) , new Vector2 ( 44  , 104 ),sb , "sagat" , "c1" , hboxEntities );
		sagat.set_is_flipped(true);
		fluffy = new Character(world, new Vector2(25, 200), new Vector2(44, 104), sb, "sagat", "c2", hboxEntities);
		fluffy.set_is_flipped(false);

		//We need this assignment for input
		playerBody = sagat.getBody();
		player2Body = fluffy.getBody();

		//set health to max, which is global
		sagat.setHealth(maxHealth);
		fluffy.setHealth(maxHealth);

		//setup box2DCamera
		box2DCamera = new OrthographicCamera();
		box2DCamera.setToOrtho(false, Manic.V_WIDTH/PPM, Manic.V_HEIGHT/PPM);

		//Light stuff
		handler = new RayHandler(world);
		handler.setAmbientLight(.50f);
		handler.setCombinedMatrix(camera.combined);
		handler.setShadows(true);
		
		//Create the lights
		PointLight light = new PointLight(handler, 200, Color.BLACK, 175f, -5, 225);
		PointLight light2 = new PointLight(handler, 200, Color.BLACK, 100f, 160, 245);
		PointLight light3 = new PointLight(handler, 200, Color.BLACK, 175f, 320, 210);
		PointLight light6 = new PointLight(handler, 200, Color.SALMON, 175f, -5, 0);
		PointLight light4 = new PointLight(handler, 200, Color.LIME, 50f, 197/2, 155);
		PointLight light5 = new PointLight(handler, 200, Color.LIME, 50f, 324/2, 156);


		//Set some of the lights 
		light.setSoftnessLength(100f);
		light2.setSoftnessLength(100f);
		light3.setSoftnessLength(100f);
	}

	public void handleInput(){
		//Jump up
		if (InputHandler.isPressed(InputHandler.KEY_SPACE)){handleUpInput ( sagat );}

		if (InputHandler.isPressed(InputHandler.KEY_UP)){handleUpInput ( fluffy );}

		//boy, can't sagat shoot things! :3
		if ( InputHandler.isPressed( InputHandler.KEY_Q )){
			if (sagat.isOnGround() && sagat.canInput()) {
				sagat.setMove("sagattigershot");
			}
		}

		//Fluffy shooting
		if ( InputHandler.isPressed( InputHandler.KEY_U )){
			if (fluffy.isOnGround() && fluffy.canInput()) {
				fluffy.setMove("sagattigershot");
			}
		}
		//Quick drop
		if (InputHandler.isPressed(InputHandler.KEY_S)){handleDownInput ( sagat );}

		if (InputHandler.isPressed(InputHandler.KEY_DOWN)){handleDownInput ( fluffy );}
		//Go right
		if (InputHandler.isDown(InputHandler.KEY_D)){handleRightInput ( sagat );}

		if (InputHandler.isDown(InputHandler.KEY_RIGHT)){handleRightInput ( fluffy );}
		//Go left
		if (InputHandler.isDown(InputHandler.KEY_LEFT)){handleLeftInput ( fluffy );}

		if (InputHandler.isDown(InputHandler.KEY_A)){handleLeftInput ( sagat );}

		//Restart button
		if(InputHandler.isPressed(InputHandler.KEY_P)){
			System.out.println("RESTART");
			Manic.changeStateLock = true;
			gsm.setState(GameStateManager.State.RESTART);        
		}
		
		//If Sagat dies, Fluffy wins a point
		if (sagat.getHealth()==0){
			//Round win sound please 
			p2Wins++;
			sagat.setHealth(maxHealth);
			fluffy.setHealth(maxHealth);
			Manic.changeStateLock=true;
			gsm.setState(GameStateManager.State.RESTART);
		}
		
		//If Fluffy dies, Sagat gets a point
		if (fluffy.getHealth()==0){
			//Round win sound please 
			p1Wins++;
			sagat.setHealth(maxHealth);
			fluffy.setHealth(maxHealth);
			Manic.changeStateLock=true;
			gsm.setState(GameStateManager.State.RESTART);
		}
		//If anyone gets two points, go to victory screen
		if (p1Wins==2 || p2Wins==2){
			//Game win sound please 
			gsm.setState(GameStateManager.State.VICTORY);	
		}

	}

	private void handleRightInput ( Character ch )
	{

		if ( ch.canInput() )
		{
			//playerBody.applyForce(new Vector2(3f, 0), playerBody.getPosition(), true);
			ch.getBody().applyForceToCenter(MOVEMENT_SPEED_NEWTONS, 0, true);

			if ( sagat.is_flipped() )
			{

				sagat.set_is_flipped ( false );
				sagat.setMove( "sagatstandturn" );

			}

		}


	}

	private void handleLeftInput ( Character ch )
	{

		if ( ch.canInput() )
		{
			ch.getBody().applyForceToCenter(-MOVEMENT_SPEED_NEWTONS, 0, true);
			if ( !sagat.is_flipped() )
			{

				sagat.set_is_flipped ( true );
				sagat.setMove( "sagatstandturn" );

			}
		}

	}

	private void handleUpInput ( Character ch )
	{

		if ( ch.isOnGround() && ch.canInput() ) {
			//in newtons. player weighs 1kg, -9.78 gravity
			//apply upward force when on the ground
			ch.getBody().applyForceToCenter(0, JUMP_FORCE_NEWTONS, true);

			//sagat.getHealth()--;

		}

	}

	private void handleDownInput ( Character ch )
	{

		if ( !ch.isOnGround() && ch.canInput() ) {
			//apply downward force when airborne
			ch.getBody().applyForceToCenter(0, -JUMP_FORCE_NEWTONS, true);
		}

	}
	/**The skin is a libgdx tool that stores information about stage objects, and can store all your information or just specific actors,
	such as buttons or labels.*/
	public void createSkin(){
		//Create a font
		BitmapFont font = new BitmapFont();
		//initialize skin
		skin = new Skin();
		/**you add things to the skin when you make them, this is just the default font that comes with libgdx but we can add our own,
		later if we want*/
		skin.add("default", font);

		//Create a texture
		Pixmap pixmap = new Pixmap((int)Gdx.graphics.getWidth()/4,(int)Gdx.graphics.getHeight()/10, Pixmap.Format.RGB888);
		//button color
		pixmap.setColor(Color.LIME);
		pixmap.fill();
		skin.add("background",new Texture(pixmap));
		Label.LabelStyle labelStyle = new Label.LabelStyle();
		//label text color
		labelStyle.fontColor = Color.RED;
		Pixmap titlePixmap = new Pixmap((int)Gdx.graphics.getWidth()/4,(int)Gdx.graphics.getHeight()/10, Pixmap.Format.RGB888);
		//label background color
		titlePixmap.setColor(Color.CLEAR);
		titlePixmap.fill();
		skin.add("titleBackground",new Texture(titlePixmap));
		labelStyle.background = skin.newDrawable("titleBackground",Color.CLEAR);
		labelStyle.font = skin.getFont("default");
		skin.add("default", labelStyle);
	}
	public void update(float dt){
		handleInput();

		//Update hitbox entities
		TreeSet < String > keys = new TreeSet < String > ();
		keys.addAll(hboxEntities.keySet());
		for ( Iterator<String> itr = keys.iterator() ; itr.hasNext() ; )
			hboxEntities.get(itr.next()).update(dt);

		world.step(dt, 6, 2);

		//Clean up body stuff
		fixtureDestroyer.destroyAll();
		bodyDestroyer.destroyAll(world);

	}
		 
	public void render() {
		//clear
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.clear();

		//Create Skin
		createSkin();

		//Player 1 health
		p1HealthCharSeq = "Health: "+ sagat.getHealth();
		p1Health = new Label(p1HealthCharSeq, skin);
		p1Health.setPosition((float) (Gdx.graphics.getWidth()*.25 - Gdx.graphics.getWidth()*.125) , (float) (Gdx.graphics.getHeight()*.90));
		stage.addActor(p1Health);

		//Player 2 health
		p2HealthCharSeq = "Health: "+ fluffy.getHealth();
		p2Health = new Label(p2HealthCharSeq, skin);
		p2Health.setPosition((float) (Gdx.graphics.getWidth()*.85 - Gdx.graphics.getWidth()*.12) , (float) (Gdx.graphics.getHeight()*.90));
		stage.addActor(p2Health);

		//Round score
		roundChars = p1Wins + " : " + p2Wins;
		roundWins = new Label(roundChars, skin);
		roundWins.setPosition((float) (Gdx.graphics.getWidth()*.5 - Gdx.graphics.getWidth()*.04) , (float) (Gdx.graphics.getHeight()*.90));
		stage.addActor(roundWins);

		//Draw or render everything
		backgroundOfMeow.render();
		stage.act();
		stage.draw();
		moderatePlatform.render();
		liberalPlatform.render();
		conservativePlatform.render();

		//Render hitbox entities
		TreeSet < String > keys = new TreeSet < String > ();
		keys.addAll(hboxEntities.keySet());
		for ( Iterator <String> itr = keys.iterator() ; itr.hasNext() ; )
			hboxEntities.get( itr.next() ).render();

		sagat.render();
		fluffy.render();


		debugRenderer.render(world, box2DCamera.combined);
		handler.updateAndRender();
	}
	
	//we don't need to dispose much
	public void dispose() {
		stage.dispose();
		System.gc();
	}
}
