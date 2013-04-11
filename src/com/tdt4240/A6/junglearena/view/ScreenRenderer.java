package com.tdt4240.A6.junglearena.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class ScreenRenderer {
	private SpriteBatch spriteBatch;
	private BitmapFont font;
	private CharSequence welcomeText;
	private Texture backgroundTexture;

	public ScreenRenderer() {
		this.spriteBatch = new SpriteBatch();
		this.font = new BitmapFont();
		this.welcomeText = "Welcome to Jungle Arena";
		backgroundTexture = new Texture(Gdx.files.internal("desert.jpeg"));
	}

	public void render(Stage stage) {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        Table.drawDebug(stage); // This is optional, but enables debug lines for tables.
	}
	
	public void dispose(){
		backgroundTexture.dispose();
	}

}
