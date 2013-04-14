package com.tdt4240.A6.junglearena.controller;

import java.util.ArrayList;
import java.util.List;
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
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.tdt4240.A6.junglearena.controller.factories.WeaponFactory;
import com.tdt4240.A6.junglearena.listeners.CollisionListener;
import com.tdt4240.A6.junglearena.model.JungleWorld;
import com.tdt4240.A6.junglearena.model.Player;
import com.tdt4240.A6.junglearena.model.Weapons.Weapon;
import com.tdt4240.A6.junglearena.model.characters.GameCharacter;
import com.tdt4240.A6.junglearena.model.gameControls.ControlsLayer;
import com.tdt4240.A6.junglearena.model.gameControls.GameButton;
import com.tdt4240.A6.junglearena.model.gameControls.PowerBar;
import com.tdt4240.A6.junglearena.utils.Constants;
import com.tdt4240.A6.junglearena.utils.MathPhysicsUtils;

public class WorldController {
	private JungleWorld jungleWorld;
	private ControlsLayer controls;
	private List<PlayerController> playerControllers;

	public WorldController(JungleWorld world) {
		this.jungleWorld = world;
		this.controls = new ControlsLayer();
		this.playerControllers = new ArrayList<PlayerController>();
	}
	
	public void update(float dt) {
		World world = this.jungleWorld.getWorld();
		handleWeaponExplosion(world, dt);
		// Weapon weapon = this.jungleWorld.getCurrentWeapon();
		// if (weapon != null) {
		// updateWeapon(weapon, dt);
		// }
		// updateWeapon(weapon, dt);
		this.controls.getTarget().update(dt);
		this.controls.getPowerBar().update(dt);
		playerControllers.get(0).chooseShootingParameters(); //current Player
		world.step(Gdx.graphics.getDeltaTime(), 5, 5);
//		this.jungleWorld.getPlayer1().getCharacter().update(dt);
//		this.jungleWorld.getPlayer2().getCharacter().update(dt);
		world.clearForces();
	}

	public void startNewTurn() {
		this.jungleWorld.swapPlayers();
		this.swapPlayerControllers();
		this.jungleWorld.setCurrentWeapon(new Weapon());
		this.initializeControls();
	}

	public void screenTouched(float x, float y) {
	
	}
	
	/**
	 * This method is used to swap players. In fact the player who is going to
	 * play the round is the one at position 0
	 * */
	public List<PlayerController> swapPlayerControllers() {
		PlayerController tmp = this.playerControllers.get(0);
		tmp.setIsMyTurn(false);
		this.playerControllers.remove(0);
		this.playerControllers.add(tmp);
		this.playerControllers.get(0).setIsMyTurn(true);
		return this.playerControllers;
	}


	public void angleTouched(float screenX, float screenY) {
		Vector2 origin = this.jungleWorld.getCurrentPlayer().getCharacter()
				.getCentre();
		float radius = Constants.distanceFromTarget;
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
		GameCharacter leftChar = this.jungleWorld.getPlayer1().getCharacter();
		GameCharacter rightChar = this.jungleWorld.getPlayer2().getCharacter();
		Random random = new Random();
		int randomX = random.nextInt(Gdx.graphics.getWidth() / 3 + 50);// 50
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
		rightChar.setPosition(new Vector2(randomX, y));

		generateBox2DCharacter(leftChar);
		generateBox2DCharacter(rightChar);
		
		initializeControls();// TODO to be called from a separate method

	}

	/**
	 * Called once per game
	 * */
	private void generateBox2DCharacter(GameCharacter character) {
		World world = this.jungleWorld.getWorld();
		// First we create a body definition
		BodyDef bodyDef = new BodyDef();
		// We set our body to static
		bodyDef.type = BodyType.StaticBody;
		bodyDef.gravityScale = 0;		// Set our body's starting position in the world

		bodyDef.position.set(character.getCentre().x,
				character.getCentre().y);

		// Create our body in the world using our body definition
		Body body = world.createBody(bodyDef);
		body.setUserData(character);
		character.setBody(body);

		// Define the shape
		PolygonShape polygonShape = new PolygonShape();
		// polygonShape.setAsBox(hx, hy, center, angle)
//		Vector2[] vertices = new Vector2[4];
//		vertices[0] = new Vector2(character.getPosition().x,
//				character.getPosition().y);
//		vertices[1] = new Vector2(character.getPosition().x,
//				character.getPosition().y + character.getSize().y);
//		vertices[2] = new Vector2(character.getPosition().x
//				+ character.getSize().x, character.getPosition().y
//				+ character.getSize().y);
//		vertices[3] = new Vector2(character.getPosition().x
//				+ character.getSize().x, character.getPosition().y);
		// polygonShape.set(vertices );
		polygonShape.setAsBox(character.getSize().x/2 ,
				character.getSize().y/2 );

		// Fixture fixture = body.createFixture(polygonShape, 0);
		CircleShape circleShape = new CircleShape();
		circleShape.setPosition(character.getCentre());
		circleShape.setRadius(character.getSize().x / 2);
		FixtureDef fixDef = new FixtureDef();
		fixDef.shape = polygonShape;
		fixDef.friction = 1;
		fixDef.restitution = 0;
		fixDef.density = 0;
		Fixture fixture = body.createFixture(fixDef);
		polygonShape.dispose();
	}

	/**
	 * Called once per game
	 * */
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
		mapBody.setUserData(this.jungleWorld.getMap());
		mapBody.createFixture(chainShape, 0.0f);
		this.jungleWorld.getMap().setBody(mapBody);
		this.jungleWorld.setWorld(world);
		chainShape.dispose();
		return world;
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

	/**
	 * Called each turn
	 * */
	private void initializeControls() {
		Player currentPlayer = this.jungleWorld.getCurrentPlayer();
		GameCharacter currentCharacter = currentPlayer.getCharacter();
		Vector2 characterCentre = currentCharacter.getCentre();
		GameButton target = new GameButton("target", characterCentre.add(Constants.distanceFromTarget,0),
				new Vector2(70, 70));
		this.controls.addButton(target);
		this.controls.setTarget(target);

		PowerBar powerBar = new PowerBar("powerBar", new Vector2(10, 10),
				new Vector2(200, 100));
		powerBar.setScale(0, 0);
		this.controls.setPowerBar(powerBar);
		this.controls.addButton(powerBar);

		GameButton fireButton = new GameButton("fireButton", new Vector2(
				Gdx.graphics.getWidth() - 50, 50), new Vector2(50, 50));
		this.controls.setFireButton(fireButton);
		this.controls.addButton(fireButton);
	}

	public JungleWorld getJungleWorld() {
		return jungleWorld;
	}

	public void setJungleWorld(JungleWorld jungleWorld) {
		this.jungleWorld = jungleWorld;
	}

	public void setControls(ControlsLayer controls) {
		this.controls = controls;
	}

	public ControlsLayer getControls() {
		return this.controls;
	}

	/**
	 * CREATES A NEW WEAPON AND SHOTS IT
	 * */
	public void shot() {
		float power = this.controls.getPowerBar().getPower();
		GameCharacter character = this.jungleWorld.getCurrentPlayer()
				.getCharacter();
		Vector2 charCentre = character.getCentre();
		Vector2 targetCentre = this.controls.getTarget().getCentre();
		Vector2 charPosition = character.getPosition();
		Vector2 charSize = character.getSize();
		double angle = Math.atan((charCentre.y - targetCentre.y)
				/ (charCentre.x - targetCentre.x));

		World world = this.jungleWorld.getWorld();
		// world = this.box2Dworld;

		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		// Set our body's starting position in the world according
		if (charCentre.x < targetCentre.x) {
			//the second add is done in order to avoid collision before the shot
			bodyDef.position.set(charPosition.cpy().add(charSize).add(5f,5f));
		} else {
			bodyDef.position.set(charPosition.cpy().add(charSize).add(5f,5f));

			// bodyDef.position.set(charPosition.cpy().add(charSize));
		}
		// Create our body in the world using our body definition
		Body body = world.createBody(bodyDef);
		WeaponFactory weaponFactory = new WeaponFactory();
		Weapon currentWeapon = weaponFactory.createWeapon("bomb", 10,
				"The Bombz", "bombskin", 1);
		currentWeapon.setBody(body);
		this.jungleWorld.setCurrentWeapon(currentWeapon);
		body.setUserData(currentWeapon);
		// angle = 3*Math.PI/4;
		float scaleFactor = 100000000000000000000f;
		float vx = (float) (power * Math.cos((float) angle)) * scaleFactor;
		float vy = (float) (power * Math.sin((float) angle)) * scaleFactor;
		body.applyLinearImpulse(new Vector2((vx), vy), body.getLocalCenter());

		// Create a circle shape and set its radius to 6
		CircleShape circle = new CircleShape();
		circle.setRadius(6f);

		// Create a fixture definition to apply our shape to
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = circle;
		fixtureDef.density = 0.5f;
		fixtureDef.friction = 0.4f;
		fixtureDef.restitution = 0.3f; // Make it bounce a little bit

		// Create our fixture and attach it to the body
		Fixture fixture = body.createFixture(fixtureDef);
		// Remember to dispose of any shapes after you're done with them!
		// BodyDef and FixtureDef don't need disposing, but shapes do.
		circle.dispose();
		this.jungleWorld.setWorld(world);
	}
	
	public boolean isGameOver(){
		return this.jungleWorld.isGameOver();
	}
	
	public boolean isEndOfTurn(){
		return this.jungleWorld.isEndOfTurn();
	}
	
	public List<PlayerController> getPlayerControllers() {
		return playerControllers;
	}

	public void setPlayerControllers(List<PlayerController> playerControllers) {
		this.playerControllers = playerControllers;
	}
}
