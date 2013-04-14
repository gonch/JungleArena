package com.tdt4240.A6.junglearena.controller.players;

import com.tdt4240.A6.junglearena.controller.WorldController;
import com.tdt4240.A6.junglearena.model.Player;

public class HardAIPlayerController extends PlayerController{

	private WorldController worldController;
	
	public HardAIPlayerController(Player player, WorldController worldController) {
		super(player);
	}
	
	
	public WorldController getWorldController() {
		return worldController;
	}

	public void setWorldController(WorldController worldController) {
		this.worldController = worldController;
	}

	@Override
	public void chooseShootingParameters(float dt) {
		
	}

}
