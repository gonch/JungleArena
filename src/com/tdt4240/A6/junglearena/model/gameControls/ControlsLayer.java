package com.tdt4240.A6.junglearena.model.gameControls;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector2;


public class ControlsLayer {

	private List<GameButton> buttons;
	private GameButton target;
	private GameButton powerBar;
	private GameButton fireButton;
	
	public ControlsLayer() {
		super();
		this.buttons = new ArrayList<GameButton>();
		this.target = new GameButton();
		this.powerBar = new GameButton();
		this.fireButton = new GameButton();
//		this.target = new GameButton("target", new Vector2(50,50), new Vector2(30,30));
	}

	public GameButton getFireButton() {
		return fireButton;
	}

	public void setFireButton(GameButton fireButton) {
		this.fireButton = fireButton;
	}

	public GameButton getPowerBar() {
		return powerBar;
	}

	public void setPowerBar(GameButton powerBar) {
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
