package com.tdt4240.A6.junglearena.model.gameControls;

import com.badlogic.gdx.math.Vector2;

public class PowerBar extends GameButton {
	private float power;

	public float getPower() {
		return power;
	}

	public void setPower(float power) {
		this.power = power;
	}

	public PowerBar() {
		super();
	}

	public PowerBar(String string, Vector2 vector2, Vector2 vector22) {
		super(string, vector2, vector22);
	}

	@Override
	public void update(float dt) {
		super.update(dt);
		if (this.isSelected()) {
			float previousScaleX = this.getScaleX();
			if (previousScaleX <= this.getSize().x) {
				this.setScale(previousScaleX + 0.5f, this.getSize().y);// TODO
																		// hardcode
				this.power = this.getScaleX() / this.getSize().x;
			}
		}
	}

}
