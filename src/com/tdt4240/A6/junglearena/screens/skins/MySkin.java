package com.tdt4240.A6.junglearena.screens.skins;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class MySkin {

	public static Skin getHugeButtonSkin() {
		Skin skin = new Skin();
		// Generate a 1x1 white texture and store it in the skin named "white".
		Pixmap pixmap = new Pixmap(1, 1, Format.RGBA8888);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		skin.add("white", new Texture(pixmap));

		BitmapFont bitmap = new BitmapFont(
				Gdx.files.internal("fonts/cracked-94.fnt"), false);
		skin.add("default", bitmap);
		skin.add("default", createTextButtonStyle(skin));
		return skin;

	}

	public static Skin getHugeRedButtonSkin() {
		Skin skin = new Skin();
		// Generate a 1x1 white texture and store it in the skin named "white".
		Pixmap pixmap = new Pixmap(1, 1, Format.RGBA8888);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		skin.add("white", new Texture(pixmap));

		BitmapFont bitmap = new BitmapFont(
				Gdx.files.internal("fonts/cracked-red-94.fnt"), false);
		skin.add("default", bitmap);

		// Configure a TextButtonStyle and name it "default". Skin resources are
		// stored by type, so this doesn't overwrite the font.
		skin.add("default", createTextButtonStyle(skin));
		return skin;
	}
	
	public static Skin getBigRedButtonSkin() {
		Skin skin = new Skin();
		// Generate a 1x1 white texture and store it in the skin named "white".
		Pixmap pixmap = new Pixmap(1, 1, Format.RGBA8888);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		skin.add("white", new Texture(pixmap));

		BitmapFont bitmap = new BitmapFont(
				Gdx.files.internal("fonts/cracked-red-72.fnt"), false);
		skin.add("default", bitmap);

		// Configure a TextButtonStyle and name it "default". Skin resources are
		// stored by type, so this doesn't overwrite the font.
		skin.add("default", createTextButtonStyle(skin));
		return skin;
	}
	
	public static Skin getMediumRedButtonSkin() {
		Skin skin = new Skin();
		// Generate a 1x1 white texture and store it in the skin named "white".
		Pixmap pixmap = new Pixmap(1, 1, Format.RGBA8888);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		skin.add("white", new Texture(pixmap));

		BitmapFont bitmap = new BitmapFont(
				Gdx.files.internal("fonts/cracked-red-50.fnt"), false);
		skin.add("default", bitmap);

		// Configure a TextButtonStyle and name it "default". Skin resources are
		// stored by type, so this doesn't overwrite the font.
		skin.add("default", createTextButtonStyle(skin));
		return skin;
	}

	public static Skin getSmallRedButtonSkin() {
		Skin skin = new Skin();
		// Generate a 1x1 white texture and store it in the skin named "white".
		Pixmap pixmap = new Pixmap(1, 1, Format.RGBA8888);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		skin.add("white", new Texture(pixmap));

		BitmapFont bitmap = new BitmapFont(
				Gdx.files.internal("fonts/cracked-red-32.fnt"), false);
		skin.add("default", bitmap);

		// Configure a TextButtonStyle and name it "default". Skin resources are
		// stored by type, so this doesn't overwrite the font.
		skin.add("default", createTextButtonStyle(skin));
		return skin;
	}
	
	public static Skin getBigButtonSkin() {
		Skin skin = new Skin();

		// Generate a 1x1 white texture and store it in the skin named "white".
		Pixmap pixmap = new Pixmap(1, 1, Format.RGBA8888);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		skin.add("white", new Texture(pixmap));

		BitmapFont bitmap = new BitmapFont(
				Gdx.files.internal("fonts/cracked-72.fnt"), false);
		skin.add("default", bitmap);

		// Configure a TextButtonStyle and name it "default". Skin resources are
		// stored by type, so this doesn't overwrite the font.
		
		skin.add("default", createTextButtonStyle(skin));
		return skin;
	}

	private static TextButtonStyle createTextButtonStyle(Skin skin)
	{
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = skin.newDrawable("white", Color.CLEAR);
		textButtonStyle.down = skin.newDrawable("white", Color.CLEAR);
		textButtonStyle.checked = skin.newDrawable("white", Color.CLEAR);
		textButtonStyle.font = skin.getFont("default");
		return textButtonStyle;
	}
	
	public static Skin getMediumButtonSkin() {
		Skin skin = new Skin();

		// Generate a 1x1 white texture and store it in the skin named "white".
		Pixmap pixmap = new Pixmap(1, 1, Format.RGBA8888);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		skin.add("white", new Texture(pixmap));

		BitmapFont bitmap = new BitmapFont(
				Gdx.files.internal("fonts/cracked-50.fnt"), false);
		skin.add("default", bitmap);

		skin.add("default", createTextButtonStyle(skin));
		return skin;

	}

	public static Skin getSmallButtonSkin() {
		Skin skin = new Skin();

		// Generate a 1x1 white texture and store it in the skin named "white".
		Pixmap pixmap = new Pixmap(1, 1, Format.RGBA8888);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		skin.add("white", new Texture(pixmap));

		BitmapFont bitmap = new BitmapFont(
				Gdx.files.internal("fonts/cracked-32.fnt"), false);
		skin.add("default", bitmap);

		skin.add("default", createTextButtonStyle(skin));
		return skin;
	}

}
