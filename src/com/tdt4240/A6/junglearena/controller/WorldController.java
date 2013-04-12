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
import com.tdt4240.A6.junglearena.model.JungleWorld;
import com.tdt4240.A6.junglearena.model.Player;
import com.tdt4240.A6.junglearena.model.Weapons.Weapon;
import com.tdt4240.A6.junglearena.model.characters.Character;
import com.tdt4240.A6.junglearena.model.gameControls.ControlsLayer;
import com.tdt4240.A6.junglearena.model.gameControls.GameButton;
import com.tdt4240.A6.junglearena.model.gameControls.PowerBar;
import com.tdt4240.A6.junglearena.utils.MathPhysicsUtils;

public class WorldController {
	private JungleWorld jungleWorld;
	private ControlsLayer controls;

	public WorldController(JungleWorld world) {
		this.jungleWorld = world;
		this.controls = new ControlsLayer();
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
		bodyDef.position.set(tank1.getPosition().x + tank1.getSize().x,
				tank1.getPosition().y + tank1.getSize().y);
		bodyDef.position.set(0, 100);
		// Create our body in the world using our body definition
		Body body = world.createBody(bodyDef);

		Weapon currentWeapon = new Weapon(1, "", "", 1);
		currentWeapon.setBody(body);
		this.jungleWorld.setCurrentWeapon(currentWeapon);
		body.setUserData(currentWeapon);
		float power = 40f;
		double angle = Math.PI / 4;
		float vx = (float) (power * Math.cos((float) angle)) * 100f;
		float vy = (float) (power * Math.sin((float) angle)) * 100f;
		body.applyLinearImpulse(new Vector2((vx / 65), vy / 65),
				body.getLocalCenter());

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

		setCharacterStartingPositions();
	}

	public void angleTouched(float screenX, float screenY) {
		Vector2 origin = this.jungleWorld.getCurrentPlayer().getCharacter()
				.getCentre();
		float radius = 100; // hardcoded
		// float targetCircleX =
		// MathPhysicsUtils.calculateXCircleInterpolationGivenY(origin.x,
		// origin.y, screenY, radius, screenX).x;
		Vector2 pos = MathPhysicsUtils.calculateXCircleInterpolation(origin.x,
				origin.y, screenX, screenY, radius);

		// if(origin.x >= screenX){
		// targetCircleX *= -1;
		// }
		this.controls.getTarget().setPosition(pos);
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
		int randomX = random.nextInt(Gdx.graphics.getWidth() / 3 + 150);// 50
																		// for
																		// not
																		// having
																		// it
																		// close
																		// to
																		// the
																		// borders
		float y = this.jungleWorld.getMap().getMapY()[randomX];
		leftChar.setPosition(new Vector2(randomX, y));
		randomX = random.nextInt(Gdx.graphics.getWidth() / 3)
				+ Gdx.graphics.getWidth() * 2 / 3 - 50;
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

		// First we create a body definition
		BodyDef bodyDef = new BodyDef();
		// We set our body to dynamic, for something like ground which doesnt
		// move we would set it to StaticBody
		bodyDef.type = BodyType.DynamicBody;
		// Set our body's starting position in the world
		bodyDef.position.set(101, 301);

		// Create our body in the world using our body definition
		Body body = world.createBody(bodyDef);
		WeaponFactory weaponFactory = new WeaponFactory();
		Weapon currentWeapon = weaponFactory.createWeapon("bomb", 10,
				"The Bombz", "bombskin", 1);
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

		initializeControls();

		return world;
	}

	public void update(float dt) {
		World world = this.jungleWorld.getWorld();
		handleWeaponExplosion(world, dt);
		// world.step(1 / 60f, 6, 2);
		// Weapon weapon = this.jungleWorld.getCurrentWeapon();
		// if (weapon != null) {
		// updateWeapon(weapon, dt);
		// }
		// updateWeapon(weapon, dt);
		this.controls.getTarget().update(dt);
		this.controls.getPowerBar().update(dt);

		// world.step(Gdx.graphics.getDeltaTime(), 5, 5);
		world.step(1 / 10f, 5, 5);
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

	private void initializeControls() {
		Player currentPlayer = this.jungleWorld.getCurrentPlayer();
		Character currentCharacter = currentPlayer.getCharacter();
		Vector2 characterCentre = currentCharacter.getCentre();
		GameButton target = new GameButton("target", characterCentre.add(0, 0),
				new Vector2(70, 70));
		this.controls.addButton(target);
		this.controls.setTarget(target);

		GameButton powerBar = new PowerBar("powerBar", new Vector2(10, 10),
				new Vector2(200, 100));
		powerBar.setScale(0, 0);
		this.controls.setPowerBar(powerBar);
		this.controls.addButton(powerBar);
		
		GameButton fireButton = new GameButton("fireButton", new Vector2(Gdx.graphics.getWidth()-50,50), new Vector2(50,50));
		this.controls.setFireButton(fireButton);
		this.controls.addButton(fireButton);
	}

	public ControlsLayer getControls() {
		return this.controls;
	}
}
