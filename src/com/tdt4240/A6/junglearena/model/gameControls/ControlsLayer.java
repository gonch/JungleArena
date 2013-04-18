package com.tdt4240.A6.junglearena.model.gameControls;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector2;


public class ControlsLayer {

	private List<GameButton> buttons;
	private GameButton target;
	private PowerBar powerBar;
	private GameButton fireButton;
	private List<WeaponButton> weaponButtons;
	private GameButton pauseButton;
	
	public GameButton getPauseButton() {
		return pauseButton;
	}

	public void setPauseButton(GameButton pauseButton) {
		this.pauseButton = pauseButton;
	}

	public ControlsLayer() {
		super();
		this.buttons = new ArrayList<GameButton>();
		this.target = new GameButton();
		this.powerBar = new PowerBar();
		this.fireButton = new GameButton();
		this.weaponButtons = new ArrayList<WeaponButton>();
		this.pauseButton = new GameButton();
	}

	public List<WeaponButton> getWeaponButtons() {
		return weaponButtons;
	}

	public void setWeaponButtons(List<WeaponButton> weaponButtons) {
		this.weaponButtons = weaponButtons;
	}

	public GameButton getFireButton() {
		return fireButton;
	}

	public void setFireButton(GameButton fireButton) {
		this.fireButton = fireButton;
	}

	public PowerBar getPowerBar() {
		return powerBar;
	}

	public void setPowerBar(PowerBar powerBar) {
		this.powerBar = powerBar;
	}

	public List<GameButton> getButtons() {
		return buttons;
	}

	public void setButtons(List<GameButton> buttons) {
		this.buttons = buttons;
	}
	
	public void addButton(GameButton button){
		this.buttons.add(button);
	}
	
	public GameButton getButtonByName(String buttonName){
		GameButton requestedButton = null;
		for(GameButton button : this.buttons){
			if(button.getButtonName().equals(buttonName)){
				requestedButton = button;
			}
		}
		return requestedButton;
	}

	public GameButton getTarget() {
		return target;
	}

	public void setTarget(GameButton target) {
		this.target = target;
	}
}
