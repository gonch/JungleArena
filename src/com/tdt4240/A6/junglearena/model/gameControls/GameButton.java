package com.tdt4240.A6.junglearena.model.gameControls;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class GameButton extends Sprite{

	private Vector2 position;
	private Vector2 size;
	private String buttonName;
	private boolean selected;

	public GameButton(String buttonName, Vector2 position, Vector2 size) {
		super();
		this.setButtonName(buttonName);
		this.position = position;
		this.size = size;
		this.setSelected(false);
		this.setBounds(position.x, position.y, size.x, size.y);
	}

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public Vector2 getSize() {
		return size;
	}

	public void setSize(Vector2 size) {
		this.size = size;
	}

	public String getButtonName() {
		return buttonName;
	}

	public void setButtonName(String buttonName) {
		this.buttonName = buttonName;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	public void update(float dt){
		this.setBounds(position.x, position.y, size.x, size.y);
	}
	
	/**
	 * Checks if those points are inside the bounding box of the button and, therefore,
	 * if it is selected
	 * */
	public boolean checkSelected(float screenX, float screenY){
		return this.getBoundingRectangle().contains(screenX, screenY);
	}
}
