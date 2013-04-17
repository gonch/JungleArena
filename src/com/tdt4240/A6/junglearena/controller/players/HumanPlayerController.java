package com.tdt4240.A6.junglearena.controller.players;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.tdt4240.A6.junglearena.controller.WorldController;
import com.tdt4240.A6.junglearena.model.Player;
import com.tdt4240.A6.junglearena.model.characters.GameCharacter;
import com.tdt4240.A6.junglearena.model.gameControls.ControlsLayer;
import com.tdt4240.A6.junglearena.model.gameControls.GameButton;
import com.tdt4240.A6.junglearena.model.gameControls.WeaponButton;

public class HumanPlayerController extends PlayerController implements InputProcessor {

	private WorldController worldController;
	private String weaponSelected;

	public HumanPlayerController(Player player, WorldController worldController) {
		super(player);
		Gdx.input.setInputProcessor(this);
		this.worldController = worldController;
		this.weaponSelected = "Gun";
	}

	public WorldController getWorldController() {
		return this.worldController;
	}

	public void setWorldController(WorldController worldController) {
		this.worldController = worldController;
	}

	@Override
	public void chooseShootingParameters(float dt) {
//		if (this.isMyTurn()) {
//			this.worldController.initializeControls();
//		}
		// the game waits until a touch input arrives
	}

	public void readyToshot() {
		ControlsLayer controls = this.worldController.getControls();

		float power = controls.getPowerBar().getPower();
		GameCharacter character = this.getPlayer().getCharacter();
		Vector2 charCentre = character.getCentre();
		Vector2 targetCentre = controls.getTarget().getCentre();
		Vector2 charPosition = character.getPosition();
		Vector2 charSize = character.getSize();
		double angle = MathUtils.atan2((charCentre.y - targetCentre.y), (charCentre.x - targetCentre.x));
		angle = Math.atan((charCentre.y - targetCentre.y) / (charCentre.x - targetCentre.x));

		this.worldController.shot(power, angle, this.weaponSelected);
	}

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
			System.out.println(this.worldController.toString());
			ControlsLayer controls = this.worldController.getControls();
			GameButton target = controls.getTarget();
			if (target.checkSelected(screenX, Gdx.graphics.getHeight() - screenY)) {
				target.setSelected(true);
				target.setReleased(false);
			}
			GameButton fireButton = controls.getFireButton();
			if (fireButton.checkSelected(screenX, Gdx.graphics.getHeight() - screenY)) {
				fireButton.setSelected(true);
				fireButton.setReleased(false);
			}
			for (WeaponButton weaponButton : controls.getWeaponButtons()) {
				if (weaponButton.checkSelected(screenX, Gdx.graphics.getHeight() - screenY)) {
					this.weaponSelected = weaponButton.getButtonName();
					weaponButton.setSelected(true);
				}
			}
		}
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (this.isMyTurn()) {
			if (this.worldController.getControls().getTarget().isSelected()) {
				this.worldController.getControls().getTarget().setSelected(false);
			}
			if (this.worldController.getControls().getFireButton().isSelected()) {
				this.worldController.getControls().getFireButton().setSelected(false);
				this.worldController.getControls().getPowerBar().setSelected(false);
				this.readyToshot();
				// this.setMyTurn(false);
			}
		}
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		if (this.isMyTurn()) {
			if (this.worldController.getControls().getTarget().isSelected()) {
				this.worldController.angleTouched(screenX, Gdx.graphics.getHeight() - screenY);
			}
			if (this.worldController.getControls().getFireButton().isSelected()) {
				this.worldController.getControls().getPowerBar().setSelected(true);
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
