package com.tdt4240.A6.junglearena.model.gameControls;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector2;


public class ControlsLayer {

	private List<GameButton> buttons;
	private GameButton target;
	private GameButton power;
	
	public ControlsLayer() {
		super();
		this.buttons = new ArrayList<GameButton>();
		this.target = new GameButton("target", new Vector2(50,50), new Vector2(30,30));
		this.power = new GameButton("power",new Vector2(10,10),new Vector2(30,30));
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
