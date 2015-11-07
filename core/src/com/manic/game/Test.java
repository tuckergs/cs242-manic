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

public class Test extends GameState {
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
	Body p2Bod;
	
	public Test(GameStateManager gsm) {
		super(gsm);
		
		world = new World(new Vector2(GRAVITY_X, GRAVITY_Y), true);
		contactListener = new MyContactListener();
		world.setContactListener(contactListener);
		
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
		
		
		//create player
		Player p = new Player(100, 100);
		
		Player p2 = new Player(200, 200);
		
		
		playerBody = world.createBody(p.getBod());
		playerBody.createFixture(p.getFix()).setUserData("player");
		//hitbox
		playerBody.createFixture(p.getSens()).setUserData("player foot");
		
		p2Bod = world.createBody(p2.getBod());
		p2Bod.createFixture(p2.getFix()).setUserData("player");
		//hitbox
		p2Bod.createFixture(p2.getSens()).setUserData("player foot");
		
		
		//setup box2DCamera
		box2DCamera = new OrthographicCamera();
		box2DCamera.setToOrtho(false, Manic.V_WIDTH/PPM, Manic.V_HEIGHT/PPM);
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
		
		
		if (InputHandler.isPressed(InputHandler.KEY_UP))
		{
			if (contactListener.isOnGround()) {
				//in newtons. player weighs 1kg, -9.78 gravity
				//apply upward force when on the ground
				p2Bod.applyForceToCenter(0, JUMP_FORCE_NEWTONS, true);
			}
		}
		
		if (InputHandler.isPressed(InputHandler.KEY_DOWN))
		{
			if (!contactListener.isOnGround()) {
				//apply downward force when airborne
				p2Bod.applyForceToCenter(0, -JUMP_FORCE_NEWTONS, true);
			}
		}
		
		if (InputHandler.isDown(InputHandler.KEY_RIGHT))
		{
			//playerBody.applyForce(new Vector2(3f, 0), playerBody.getPosition(), true);
			p2Bod.applyForceToCenter(MOVEMENT_SPEED_NEWTONS, 0, true);
		}
		
		if (InputHandler.isDown(InputHandler.KEY_LEFT))
		{
			p2Bod.applyForceToCenter(-MOVEMENT_SPEED_NEWTONS, 0, true);
		}
	}
	
	public void update(float dt)
	{
		handleInput();
		
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
