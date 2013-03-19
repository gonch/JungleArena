package com.tdt4240.A6.junglearena.controller;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.tdt4240.A6.junglearena.model.Character;
import com.tdt4240.A6.junglearena.model.World;

public class WorldController {
	private World world;

	public WorldController(World world) {
		this.world = world;
	}

	public void screenTouched(float x, float y) {
		Character tank1 = this.world.getPlayer1().getCharacter();
//		tank1.setPosition(new Vector2(x, y));
		setCharacterStartingPositions();
	}

	/**
	 * The first character will be positioned on the first 1/3, the second in
	 * the last 1/3
	 * */
	public void setCharacterStartingPositions() {
		//TODO: center the images
		Character leftChar = this.world.getPlayer1().getCharacter();
		Character rigthChar = this.world.getPlayer2().getCharacter();
		Random random = new Random();
		int randomX = random.nextInt(Gdx.graphics.getWidth() / 3);
		float y = this.world.getMap().getMapY()[randomX];
		leftChar.setPosition(new Vector2(randomX, y));
		randomX = random.nextInt(Gdx.graphics.getWidth() / 3) + Gdx.graphics.getWidth() * 2 / 3 ;
		y = this.world.getMap().getMapY()[randomX];
		rigthChar.setPosition(new Vector2(randomX, y));
	}
}
