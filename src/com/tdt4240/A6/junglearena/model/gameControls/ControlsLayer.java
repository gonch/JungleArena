package com.tdt4240.A6.junglearena.model.gameControls;

import java.util.ArrayList;
import java.util.List;


public class ControlsLayer {

	private List<GameButton> buttons;
	private GameButton target;
	
	public ControlsLayer() {
		super();
		this.buttons = new ArrayList<GameButton>();
		this.target = null;
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
