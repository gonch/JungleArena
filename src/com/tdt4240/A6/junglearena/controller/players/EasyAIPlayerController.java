package com.tdt4240.A6.junglearena.controller.players;

import com.badlogic.gdx.math.MathUtils;
import com.tdt4240.A6.junglearena.controller.WorldController;
import com.tdt4240.A6.junglearena.controller.factories.WeaponFactory;
import com.tdt4240.A6.junglearena.model.Player;
import com.tdt4240.A6.junglearena.model.Weapons.Weapon;
import com.tdt4240.A6.junglearena.utils.Constants;

public class EasyAIPlayerController extends PlayerController {

	private float thinkingTime;
	private WorldController worldController;

	public EasyAIPlayerController(Player player, WorldController worldController) {
		super(player);
		this.thinkingTime = 0f;
		this.worldController = worldController;
	}
	
	
	public WorldController getWorldController() {
		return worldController;
	}

	public void setWorldController(WorldController worldController) {
		this.worldController = worldController;
	}

	@Override
	public void chooseShootingParameters(float dt) {
		// before the shot, waits a couple of seconds for simulate the thinking
		this.thinkingTime += dt;
		if (thinkingTime >= Constants.AIThinkingTime) {
			// choose random values for power
			float power = MathUtils.random(1000000000000000f, 100000000000000000f);
			double angle = MathUtils.random(0, 360);
			angle = Math.PI;
			// shot
			this.getWorldController().shot(power, angle, "Rocket");
			// reset thinking time value
			this.thinkingTime = 0f;
		}
	}

}
