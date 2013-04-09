package com.tdt4240.A6.junglearena.model.gameControls;

import com.badlogic.gdx.math.Vector2;

public class GameButton {

	private Vector2 position;
	private Vector2 size;
	private String buttonName;

	public GameButton(String buttonName, Vector2 position, Vector2 size) {
		super();
		this.setButtonName(buttonName);
		this.position = position;
		this.size = size;
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
}
