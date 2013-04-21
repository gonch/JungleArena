package com.tdt4240.A6.junglearena.controller.players;

import com.tdt4240.A6.junglearena.controller.WorldController;
import com.tdt4240.A6.junglearena.model.Player;

public abstract class PlayerController {

	protected Player player;
	private boolean isMyTurn;
	
	public PlayerController(Player player){
		this.isMyTurn = true;
		this.player = player;
	}
	
	public Player getPlayer() {
		return player;
	}


	public void setPlayer(Player player) {
		this.player = player;
	}


	public void setMyTurn(boolean isMyTurn) {
		if(!isMyTurn)System.out.println(player.getName()+": My turn is "+isMyTurn);
		this.isMyTurn = isMyTurn;
	}	
	
	public boolean isMyTurn() {
		return isMyTurn;
	}

	public abstract void chooseShootingParameters(float dt);
}
