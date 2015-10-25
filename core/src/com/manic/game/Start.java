package com.manic.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

//pixels per meter
import static com.manic.game.Settings.PPM;

public class Start extends GameState {
	private World world;
	private final float X_GRAVITY = 0;
	private final float Y_GRAVITY = -9.81f;
	private Box2DDebugRenderer debugRenderer;
	private OrthographicCamera box2DCamera;
	
	public Start(GameStateManager gsm) {
		super(gsm);
		
		world = new World(new Vector2(X_GRAVITY, Y_GRAVITY), true);
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
		body.createFixture(fixtureDef);
		
		//create guy
		bodyDef.position.set(160/PPM, 200/PPM);
		bodyDef.type = BodyType.DynamicBody;
		body = world.createBody(bodyDef);
		
		box.setAsBox(5/PPM, 5/PPM); //10x10
		fixtureDef.shape = box;
		body.createFixture(fixtureDef);
		
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
