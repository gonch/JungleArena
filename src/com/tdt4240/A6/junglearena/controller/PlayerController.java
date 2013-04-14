package com.tdt4240.A6.junglearena.controller;

import com.tdt4240.A6.junglearena.model.Player;

public abstract class PlayerController {
	
	public boolean isMyTurn() {
		return isMyTurn;
	}

	public void setIsMyTurn(boolean isActive) {
		this.isMyTurn = isActive;
	}

	private Player player;
	private boolean isMyTurn;
	
	public PlayerController(Player player){
		this.isMyTurn = true;
		this.player = player;
	}
	
	public abstract void chooseShootingParameters();
}
