package com.tdt4240.A6.junglearena.model;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.tdt4240.A6.junglearena.controller.factories.CharacterFactory;
import com.tdt4240.A6.junglearena.model.Weapons.Weapon;
import com.tdt4240.A6.junglearena.model.characters.GameCharacter;

public class JungleWorld {
	private Player player1, player2;
	private List<Player> players;
	private GameMap map;
	private World world;
	private Weapon currentWeapon;
	private List<String> allAvailableWeaponNames;

	public JungleWorld(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
		this.players = new ArrayList<Player>();
		this.players.add(player1);
		this.players.add(player2);
		this.map = null;
		this.setWorld(new World(new Vector2(0, -10), true));
		this.currentWeapon = new Weapon();
		this.allAvailableWeaponNames = new ArrayList<String>();
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

	public Player getCurrentPlayer() {
		return this.players.get(0);
	}

	public boolean isGameOver() {
		return this.player1.getCharacter().getHealth() <= 0 || this.player2.getCharacter().getHealth() <= 0;
	}

	public boolean isEndOfTurn() {
		// boolean outOfBoundscondition = false;
		// if(this.currentWeapon!=null&&this.currentWeapon.getBody()!=null){
		// outOfBoundscondition = this.currentWeapon.getCentre().x < 0
		// || this.currentWeapon.getCentre().x > Gdx.graphics.getWidth();
		// }
		// return this.currentWeapon.isExploded() || outOfBoundscondition;
		return this.currentWeapon.isExploded();
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

	public List<String> getAllAvailableWeaponNames() {
		return allAvailableWeaponNames;
	}

	public void setAllAvailableWeaponNames(List<String> allAvailableWeaponNames) {
		this.allAvailableWeaponNames = allAvailableWeaponNames;
	}

}
