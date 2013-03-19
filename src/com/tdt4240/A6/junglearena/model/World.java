package com.tdt4240.A6.junglearena.model;

import java.util.List;

import com.badlogic.gdx.math.Vector2;

public class World {
	Player player1,player2;
	List players;
	GameMap map;
	
	public World(){
		Character lion = new Character(100,"lion",new Vector2(10,10),"lion");
		Character lion2 = new Character(100,"lion",new Vector2(300,300),"lion");
		this.player1 = new Player("alessio", 1, lion);
		this.player2 = new Player("gonzalo", 2, lion2);
		this.map = null;
	}

	public Player getPlayer1() {
		return player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public GameMap getMap() {
		return this.map;
	}
	
	public void setMap(GameMap map){
		this.map = map;
	}
	
	
}
