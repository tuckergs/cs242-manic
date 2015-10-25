package com.manic.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

//pixels per meter
import static com.manic.game.Settings.PPM;

public class Start extends GameState {
	private World world;
	private final float X_GRAVITY = 0;
	private final float Y_GRAVITY = -2.81f;
	private Box2DDebugRenderer debugRenderer;
	private OrthographicCamera box2DCamera;
	
	public Start(GameStateManager gsm) {
		super(gsm);
		
		world = new World(new Vector2(X_GRAVITY, Y_GRAVITY), true);
		world.setContactListener(new MyContactListener());
		
		debugRenderer = new Box2DDebugRenderer();
		BodyDef bodyDef= new BodyDef();
		FixtureDef fixtureDef = new FixtureDef();
		
		//create platform
		bodyDef.position.set(160/PPM, 120/PPM);
		bodyDef.type = BodyType.StaticBody; //unaffected by gravity
		Body body = world.createBody(bodyDef);
		
		PolygonShape box = new PolygonShape();
		box.setAsBox(50/PPM, 5/PPM); //100x10

		fixtureDef.shape = box;
		fixtureDef.filter.categoryBits = Settings.BIT_PLATFORM;
		fixtureDef.filter.maskBits = Settings.BIT_PLAYER | Settings.BIT_BALL; //it can colide with both the player and ball
		body.createFixture(fixtureDef).setUserData("Platform");
		
		//create box guy
		bodyDef.position.set(160/PPM, 200/PPM);
		bodyDef.type = BodyType.DynamicBody;
		body = world.createBody(bodyDef);
		
		box.setAsBox(5/PPM, 5/PPM); //10x10
		fixtureDef.shape = box;
		fixtureDef.filter.categoryBits = Settings.BIT_PLAYER;
		fixtureDef.filter.maskBits = Settings.BIT_PLATFORM;
		body.createFixture(fixtureDef).setUserData("Player");;
		
		//create ball guy
		bodyDef.position.set(153/PPM, 220/PPM);
		body = world.createBody(bodyDef);
		
		CircleShape circle = new CircleShape();
		circle.setRadius(5/PPM);
		fixtureDef.shape = circle;
		fixtureDef.restitution = 1.0f;
		fixtureDef.filter.categoryBits = Settings.BIT_BALL; //it is a type ball
		fixtureDef.filter.maskBits = Settings.BIT_PLATFORM; //can collide with ground
		body.createFixture(fixtureDef).setUserData("Ball");;
		
		//setup box2DCamera
		box2DCamera = new OrthographicCamera();
		box2DCamera.setToOrtho(false, Manic.V_WIDTH/PPM, Manic.V_HEIGHT/PPM);
	}
	
	public void handleInput() {}
	
	public void update(float dt)
	{
		world.step(dt, 6, 2);
	}
	public void render() {
		//clear
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		//draw
		debugRenderer.render(world, box2DCamera.combined);
	}
	
	public void dispose() {}
}
