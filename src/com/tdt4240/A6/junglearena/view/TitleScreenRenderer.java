package com.tdt4240.A6.junglearena.view;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TitleScreenRenderer {
	private SpriteBatch spriteBatch;
	private BitmapFont font;
	private CharSequence welcomeText;

	public TitleScreenRenderer() {
		this.spriteBatch = new SpriteBatch();
		this.font = new BitmapFont();
		this.welcomeText = "Welcome to Jungle Arena";
	}

	public void render() {
		spriteBatch.begin();
		font.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		font.setScale(3);
		font.draw(spriteBatch, this.welcomeText, 25, 160);
		spriteBatch.end();
	}

}
