package com.tdt4240.A6.junglearena.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tdt4240.A6.junglearena.model.Player;
import com.tdt4240.A6.junglearena.model.World;

public class GameInfoRenderer {
	private SpriteBatch spriteBatch;
	private BitmapFont font;
	private Texture backgroundTexture;
	private World world;

	public GameInfoRenderer(World world) {
		this.spriteBatch = new SpriteBatch();
		this.font = new BitmapFont();
		this.world = world;
	}

	public void render() {
		spriteBatch.begin();
		font.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		Player leftPlayer = this.world.getPlayer1();
		Player rightPlayer = this.world.getPlayer2();

		CharSequence leftPlayerInfo = leftPlayer.getName();
		font.draw(spriteBatch, leftPlayerInfo, 25, Gdx.graphics.getHeight() - 30);
		leftPlayerInfo = "Health: " + leftPlayer.getCharacter().getHealth();
		font.draw(spriteBatch, leftPlayerInfo, 25, Gdx.graphics.getHeight() - 50);

		CharSequence rightPlayerInfo = rightPlayer.getName();
		font.draw(spriteBatch, rightPlayerInfo, Gdx.graphics.getWidth() - 100, Gdx.graphics.getHeight() - 30);

		rightPlayerInfo = "Health: " + rightPlayer.getCharacter().getHealth();
		font.draw(spriteBatch, rightPlayerInfo, Gdx.graphics.getWidth() - 100, Gdx.graphics.getHeight() - 50);

		spriteBatch.end();
	}

	public void dispose() {
		backgroundTexture.dispose();
	}

}
