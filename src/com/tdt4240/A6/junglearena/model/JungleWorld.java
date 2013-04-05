package com.tdt4240.A6.junglearena.model;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;


public class JungleWorld{
	private Player player1, player2;
	private List<Player> players;
	private GameMap map;
	private World world;
	private Weapon currentWeapon; //TODO: for collision testing, may be removed after
	
	public JungleWorld() {
		Character lion = new Character(100, "lion", new Vector2(10, 10), "lion");
		Character lion2 = new Character(100, "lion", new Vector2(300, 300), "lion");
		this.player1 = new Player("alessio", 1, lion);
		this.player2 = new Player("gonzalo", 2, lion2);
		this.players = new ArrayList<Player>();
		this.players.add(player1);
		this.players.add(player2);
		this.map = null;
		this.setWorld(new World(new Vector2(0,-10), true));		
		this.currentWeapon = null;
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

	public void setMap(GameMap map) {
		this.map = map;
	}

	/**
	 * This method is used to swap players. In fact the player who is going to
	 * play the round is the one at position 0
	 * */
	public List<Player> swapPlayers() {
		Player tmp = this.players.get(0);
		this.players.remove(0);
		this.players.add(tmp);
		return this.players;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public Weapon getCurrentWeapon() {
		return currentWeapon;
	}

	public void setCurrentWeapon(Weapon currentWeapon) {
		this.currentWeapon = currentWeapon;
	}
}
