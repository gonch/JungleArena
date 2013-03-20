package com.tdt4240.A6.junglearena.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TitleScreenRenderer {
	private SpriteBatch spriteBatch;
	private BitmapFont font;
	private CharSequence welcomeText;
	private Texture backgroundTexture;

	public TitleScreenRenderer() {
		this.spriteBatch = new SpriteBatch();
		this.font = new BitmapFont();
		this.welcomeText = "Welcome to Jungle Arena";
		backgroundTexture = new Texture(Gdx.files.internal("desert.jpeg"));
	}

	public void render() {
		spriteBatch.begin();
		font.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		font.setScale(3);
		
		
	
		spriteBatch.draw(backgroundTexture, 0, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
//		spriteBatch.draw(backgroundTexture, 0, 0);

		font.draw(spriteBatch, this.welcomeText, 25, 160);
		spriteBatch.end();
	}
	
	public void dispose(){
		backgroundTexture.dispose();
	}

}
