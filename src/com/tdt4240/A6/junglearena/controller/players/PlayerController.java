package com.tdt4240.A6.junglearena.controller.players;

import com.tdt4240.A6.junglearena.controller.WorldController;
import com.tdt4240.A6.junglearena.model.Player;

public abstract class PlayerController {

	private Player player;
	private boolean isMyTurn;
	
	public PlayerController(Player player){
//		System.out.println(player.getName()+" CHRISTIOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
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
		this.isMyTurn = isMyTurn;
	}	
	
	public boolean isMyTurn() {
//		System.out.println(this.player.getName()+"IT'S MY TUUUUUUUUUUUUUUUURN");
		return isMyTurn;
	}

	public abstract void chooseShootingParameters(float dt);
}
