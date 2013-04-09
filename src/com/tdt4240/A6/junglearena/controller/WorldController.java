package com.tdt4240.A6.junglearena.controller;

import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.tdt4240.A6.junglearena.listeners.CollisionListener;
import com.tdt4240.A6.junglearena.model.Character;
import com.tdt4240.A6.junglearena.model.JungleWorld;
import com.tdt4240.A6.junglearena.model.Weapon;

public class WorldController {
	private JungleWorld jungleWorld;

	public WorldController(JungleWorld world) {
		this.jungleWorld = world;
	}

	public void screenTouched(float x, float y) {
		Character tank1 = this.jungleWorld.getPlayer1().getCharacter();
		// tank1.setPosition(new Vector2(x, y));
		// setCharacterStartingPositions();
		World world = this.jungleWorld.getWorld();
		// First we create a body definition
		BodyDef bodyDef = new BodyDef();
		// We set our body to dynamic, for something like ground which doesnt
		// move we would set it to StaticBody
		bodyDef.type = BodyType.DynamicBody;
		// Set our body's starting position in the world
		bodyDef.position.set(tank1.getPosition().x + tank1.getSize().x, tank1.getPosition().y + tank1.getSize().y);
		bodyDef.position.set(0, 100);
//		bodyDef.linearVelocity.x = 10000000;
//		bodyDef.linearVelocity.y = 10000000;
		// Create our body in the world using our body definition
		Body body = world.createBody(bodyDef);

		Weapon currentWeapon = new Weapon(1, "", "", 1);
		currentWeapon.setBody(body);
		this.jungleWorld.setCurrentWeapon(currentWeapon);
		body.setUserData(currentWeapon);
//		 body.applyForceToCenter(10000000000000000.0f, 10000000000000000.0f);
		// (65,65) is the max
		float power = 40f;
//		power = 10f;
		double angle = Math.PI/4;
		float vx = (float) (power * Math.cos((float)angle)) * 100f;
		float vy = (float) (power * Math.sin((float)angle)) * 100f;
		System.out.println("Vx " + vx + ",Vy " + vy);
		 body.applyLinearImpulse(new
		 Vector2((vx/65),vy/65),body.getLocalCenter());

//		body.setAwake(true);
//		 body.setLinearVelocity( vx,vy);
//		 body.linearVelocity.y = vy;
//		 body.angle = (float)angle;

		// Create a circle shape and set its radius to 6
		CircleShape circle = new CircleShape();
		circle.setRadius(6f);

		// Create a fixture definition to apply our shape to
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = circle;
		fixtureDef.density = 0.5f;
		fixtureDef.friction = 0.4f;
		fixtureDef.restitution = 1f; // Make it bounce a little bit

		// Create our fixture and attach it to the body
		Fixture fixture = body.createFixture(fixtureDef);
		// Remember to dispose of any shapes after you're done with them!2
		// BodyDef and FixtureDef don't need disposing, but shapes do.
		circle.dispose();
	}

	/**
	 * The first character will be positioned on the first 1/3, the second in
	 * the last 1/3
	 * */
	public void setCharacterStartingPositions() {
		// TODO: center the images
		Character leftChar = this.jungleWorld.getPlayer1().getCharacter();
		Character rigthChar = this.jungleWorld.getPlayer2().getCharacter();
		Random random = new Random();
		int randomX = random.nextInt(Gdx.graphics.getWidth() / 3);
		float y = this.jungleWorld.getMap().getMapY()[randomX];
		leftChar.setPosition(new Vector2(randomX, y));
		randomX = random.nextInt(Gdx.graphics.getWidth() / 3) + Gdx.graphics.getWidth() * 2 / 3;
		y = this.jungleWorld.getMap().getMapY()[randomX];
		rigthChar.setPosition(new Vector2(randomX, y));		
	}

	public void screenWithFling(float velocityX, float velocityY) {

	}

	public World generateBox2DWorld() {
		World world = new World(new Vector2(0, -10f), true);
		world.setContactListener(new CollisionListener()); // for collision
															// detection
		/**
		 * Map
		 * */
		ChainShape chainShape = this.jungleWorld.getMap().getChainShape();

		BodyDef mapBodyDef = new BodyDef();
		mapBodyDef.position.set(0, 0);
		Body mapBody = world.createBody(mapBodyDef);
		mapBody.createFixture(chainShape, 0.0f);
		this.jungleWorld.getMap().setBody(mapBody);

		chainShape.dispose();
		
		
		this.jungleWorld.setWorld(world);
		return world;
	}

	public void update(float dt) {
		World world = this.jungleWorld.getWorld();
		handleWeaponExplosion(world, dt);
		// world.step(1 / 60f, 6, 2);
//		Weapon weapon = this.jungleWorld.getCurrentWeapon();
//		if (weapon != null) {
//			updateWeapon(weapon, dt);
//		}
//		updateWeapon(weapon, dt);		
		world.step(Gdx.graphics.getDeltaTime(), 5, 5);
		world.clearForces();
	}

	private void handleWeaponExplosion(World world, float dt) {
		Weapon currentWeapon = this.jungleWorld.getCurrentWeapon();
		if (currentWeapon != null) {
			currentWeapon.update(dt);
			if (currentWeapon.isExploded()) {
				world.destroyBody(currentWeapon.getBody());
			}
		}
	}
}
