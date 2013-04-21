package com.tdt4240.A6.junglearena.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tdt4240.A6.junglearena.model.JungleWorld;
import com.tdt4240.A6.junglearena.model.Player;

public class GameInfoRenderer {
	private SpriteBatch spriteBatch;
	private BitmapFont font;
	private Texture backgroundTexture;
	private JungleWorld world;

	public GameInfoRenderer(JungleWorld world) {
		this.spriteBatch = new SpriteBatch();
		this.font = new BitmapFont(Gdx.files.internal("fonts/32_white_game_screen_outline.fnt"), false);
		this.world = world;
	}

	public void render() {
		spriteBatch.begin();
		Player leftPlayer = this.world.getPlayer1();
		Player rightPlayer = this.world.getPlayer2();

		CharSequence leftPlayerInfo = leftPlayer.getName();
		
		font.draw(spriteBatch, leftPlayerInfo, 25, Gdx.graphics.getHeight() - 30);
		leftPlayerInfo = "Health: " + leftPlayer.getCharacter().getHealth();
		font.draw(spriteBatch, leftPlayerInfo, 25, Gdx.graphics.getHeight() - 60);

		CharSequence rightPlayerInfo = rightPlayer.getName();
		font.draw(spriteBatch, rightPlayerInfo, Gdx.graphics.getWidth() - 135, Gdx.graphics.getHeight() - 30);

		rightPlayerInfo = "Health: " + rightPlayer.getCharacter().getHealth();
		font.draw(spriteBatch, rightPlayerInfo, Gdx.graphics.getWidth() - 135, Gdx.graphics.getHeight() - 60);

		spriteBatch.end();
	}

	public void dispose() {
		backgroundTexture.dispose();
	}

}
