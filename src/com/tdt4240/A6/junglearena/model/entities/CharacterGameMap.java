package com.tdt4240.A6.junglearena.model.entities;

import com.tdt4240.A6.junglearena.model.GameMap;
import com.tdt4240.A6.junglearena.model.characters.GameCharacter;

public class CharacterGameMap implements CoupleOfEntities {
	private GameCharacter character;
	private GameMap map;

	public CharacterGameMap(GameCharacter character, GameMap map) {
		super();
		this.character = character;
		this.map = map;
	}

	public GameCharacter getCharacter() {
		return character;
	}

	public void setCharacter(GameCharacter character) {
		this.character = character;
	}

	public GameMap getMap() {
		return map;
	}

	public void setMap(GameMap map) {
		this.map = map;
	}

	@Override
	public void collisionHappened() {
		//at the moment no interaction between char and map
		//may be used in the future for implementing the movement
	}

}
