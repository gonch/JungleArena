package com.tdt4240.A6.junglearena.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.tdt4240.A6.junglearena.model.Player;
import com.tdt4240.A6.junglearena.model.gameControls.ControlsLayer;
import com.tdt4240.A6.junglearena.model.gameControls.GameButton;

public class HumanPlayerController extends PlayerController implements
		InputProcessor {

	private WorldController worldController;

	public HumanPlayerController(Player player) {
		super(player);
		Gdx.input.setInputProcessor(this);
	}

	public HumanPlayerController(Player player, WorldController worldController) {
		super(player);
		this.worldController = worldController;
		Gdx.input.setInputProcessor(this);
	}

	public WorldController getWorldController() {
		return worldController;
	}

	public void setWorldController(WorldController worldController) {
		this.worldController = worldController;
	}

	@Override
	public void chooseShootingParameters() {
		if (this.isMyTurn()) {

		}
	}
//
//	public void shot() {
//		ControlsLayer controls = this.worldController.getControls();
//		JungleWorld jungleWorld = this.worldController.getJungleWorld();
//		float power = controls.getPowerBar().getPower();
//		GameCharacter character = jungleWorld.getCurrentPlayer().getCharacter();
//		Vector2 charCentre = character.getCentre();
//		Vector2 targetCentre = controls.getTarget().getCentre();
//		Vector2 charPosition = character.getPosition();
//		Vector2 charSize = character.getSize();
//		double angle = Math.atan((charCentre.y - targetCentre.y)
//				/ (charCentre.x - targetCentre.x));
//
//		World world = jungleWorld.getWorld();
//		// world = this.box2Dworld;
//
//		BodyDef bodyDef = new BodyDef();
//		bodyDef.type = BodyType.DynamicBody;
//		// Set our body's starting position in the world according
//		if (charCentre.x < targetCentre.x) {
//			// the second add is done in order to avoid collision before the
//			// shot
//			bodyDef.position.set(charPosition.cpy().add(charSize).add(5f, 5f));
//		} else {
//			bodyDef.position.set(charPosition.cpy().add(charSize).add(5f, 5f));
//
//			// bodyDef.position.set(charPosition.cpy().add(charSize));
//		}
//		// Create our body in the world using our body definition
//		Body body = world.createBody(bodyDef);
//		WeaponFactory weaponFactory = new WeaponFactory();
//		Weapon currentWeapon = weaponFactory.createWeapon("bomb", 10,
//				"The Bombz", "bombskin", 1);
//		currentWeapon.setBody(body);
//		jungleWorld.setCurrentWeapon(currentWeapon);
//		body.setUserData(currentWeapon);
//		// angle = 3*Math.PI/4;
//		float scaleFactor = 100000000000000000000f;
//		float vx = (float) (power * Math.cos((float) angle)) * scaleFactor;
//		float vy = (float) (power * Math.sin((float) angle)) * scaleFactor;
//		body.applyLinearImpulse(new Vector2((vx), vy), body.getLocalCenter());
//
//		// Create a circle shape and set its radius to 6
//		CircleShape circle = new CircleShape();
//		circle.setRadius(6f);
//
//		// Create a fixture definition to apply our shape to
//		FixtureDef fixtureDef = new FixtureDef();
//		fixtureDef.shape = circle;
//		fixtureDef.density = 0.5f;
//		fixtureDef.friction = 0.4f;
//		fixtureDef.restitution = 0.3f; // Make it bounce a little bit
//
//		// Create our fixture and attach it to the body
//		Fixture fixture = body.createFixture(fixtureDef);
//		// Remember to dispose of any shapes after you're done with them!
//		// BodyDef and FixtureDef don't need disposing, but shapes do.
//		circle.dispose();
//		jungleWorld.setWorld(world);
//	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if (this.isMyTurn()) {
			ControlsLayer controls = this.worldController.getControls();
			GameButton target = controls.getTarget();
			if (target.checkSelected(screenX, Gdx.graphics.getHeight()
					- screenY)) {
				target.setSelected(true);
				target.setReleased(false);
			}
			GameButton fireButton = controls.getFireButton();
			if (fireButton.checkSelected(screenX, Gdx.graphics.getHeight()
					- screenY)) {
				fireButton.setSelected(true);
				fireButton.setReleased(false);
			}
		}
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (this.isMyTurn()) {
			if (this.worldController.getControls().getTarget().isSelected()) {
				this.worldController.getControls().getTarget()
						.setSelected(false);
			}
			if (this.worldController.getControls().getFireButton().isSelected()) {
				this.worldController.shot();
				this.worldController.getControls().getFireButton()
						.setSelected(false);
				this.worldController.getControls().getPowerBar()
						.setSelected(false);
			}
		}
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		if (this.isMyTurn()) {
			if (this.worldController.getControls().getTarget().isSelected()) {
				this.worldController.angleTouched(screenX,
						Gdx.graphics.getHeight() - screenY);
			}
			if (this.worldController.getControls().getFireButton().isSelected()) {
				this.worldController.getControls().getPowerBar()
						.setSelected(true);
			}
		}
		return true;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

}
