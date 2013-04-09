package com.tdt4240.A6.junglearena.controller;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.tdt4240.A6.junglearena.controller.factories.WeaponFactory;
import com.tdt4240.A6.junglearena.listeners.CollisionListener;
import com.tdt4240.A6.junglearena.model.characters.Character;
import com.tdt4240.A6.junglearena.model.JungleWorld;
import com.tdt4240.A6.junglearena.model.Weapons.Weapon;

public class WorldController {
	private JungleWorld jungleWorld;

	public WorldController(JungleWorld world) {
		this.jungleWorld = world;
	}

	public void screenTouched(float x, float y) {
		Character tank1 = this.jungleWorld.getPlayer1().getCharacter();
		// tank1.setPosition(new Vector2(x, y));
		setCharacterStartingPositions();
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
		World world = new World(new Vector2(0, -100), true);
		world.setContactListener(new CollisionListener()); //for collision detection
		
		// Create a body from the defintion and add it to the world
//		List<PolygonShape> polygons = this.jungleWorld.getMap().getPolygons();
//		int i =0;
//		for (PolygonShape groundBox : polygons) {
//			// Create our body definition
//			BodyDef groundBodyDef = new BodyDef();
//			// Set its world position
//			groundBodyDef.position.set(new Vector2(i+=3,0)); // TODO hardcoded
//			// Create a polygon shape
//			Body groundBody = world.createBody(groundBodyDef);
//			// Create a fixture from our polygon shape and add it to our ground
//			// body
//			groundBody.createFixture(groundBox, 0.0f);
//		}
		
		ChainShape chainShape = this.jungleWorld.getMap().getChainShape();
		
		BodyDef mapBodyDef = new BodyDef();
		mapBodyDef.position.set(0, 0);
		Body mapBody = world.createBody(mapBodyDef);
		mapBody.createFixture(chainShape, 0.0f);
		this.jungleWorld.getMap().setBody(mapBody);
		
		chainShape.dispose();
		
		// First we create a body definition
		BodyDef bodyDef = new BodyDef();
		// We set our body to dynamic, for something like ground which doesnt
		// move we would set it to StaticBody
		bodyDef.type = BodyType.DynamicBody;
		// Set our body's starting position in the world
		bodyDef.position.set(101, 301);

		// Create our body in the world using our body definition
		Body body = world.createBody(bodyDef);
		WeaponFactory weaponFactory= new WeaponFactory();
		Weapon currentWeapon = weaponFactory.createWeapon("bomb",10,"The Bombz","bombskin",1);
		currentWeapon.setBody(body);		
		this.jungleWorld.setCurrentWeapon(currentWeapon);
		body.setUserData(currentWeapon);
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
		// Remember to dispose of any shapes after you're done with them!
		// BodyDef and FixtureDef don't need disposing, but shapes do.
		circle.dispose();
		this.jungleWorld.setWorld(world);
		return world;
	}

	public void update(float dt) {
		World world = this.jungleWorld.getWorld();
		handleWeaponExplosion(world,dt);
		world.step(1 / 60f, 6, 2);
	}
	
	private void handleWeaponExplosion(World world, float dt){
		Weapon currentWeapon = this.jungleWorld.getCurrentWeapon();
		currentWeapon.update(dt);
		if(currentWeapon.isExploded()){
			world.destroyBody(currentWeapon.getBody());
		}
	}
}
