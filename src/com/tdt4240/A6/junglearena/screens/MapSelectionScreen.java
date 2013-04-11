package com.tdt4240.A6.junglearena.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tdt4240.A6.junglearena.model.Context;
import com.tdt4240.A6.junglearena.screens.skins.MySkin;
import com.tdt4240.A6.junglearena.view.ScreenRenderer;

public class MapSelectionScreen implements Screen, InputProcessor {

	private ScreenRenderer screenRenderer;
	private Game game;
	private Stage stage;
	private Context context;
	private TextButton buttonMaps[];
	private String environments [];

	public MapSelectionScreen(final Game game, Context context) {
		this.game = game;
		this.context = context;
		buttonMaps = new TextButton[2];
		environments = new String []{"Jungle","Desert"};// TODO Hard coded, waiting for MARTIN
		buttonMaps[0] = new TextButton(environments[0], MySkin.getHugeButtonSkin());
		buttonMaps[1] = new TextButton(environments[1], MySkin.getHugeButtonSkin());
	}

	@Override
	public void show() {
		buttonMaps[0].addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				context.setEnvironment(((TextButton)event.getListenerActor()).getLabel().getText().toString());
				game.setScreen(new GameScreen(game, context));
			}
		});
		buttonMaps[1].addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				context.setEnvironment(((TextButton)event.getListenerActor()).getLabel().getText().toString());
				game.setScreen(new GameScreen(game, context));
			}
		});
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		Table table = new Table();
		table.setFillParent(true);

		buttonMaps[0].pad(25);
		buttonMaps[1].pad(25);
		table.add(buttonMaps[0]);
		table.row();
		table.add(buttonMaps[1]);
		table.row();
		stage.addActor(table);
		this.screenRenderer = new ScreenRenderer();
		Gdx.input.setInputProcessor(stage);
		// Gdx.input.setInputProcessor(this);
	}

	@Override
	public void render(float delta) {
		this.screenRenderer.render(stage);
		// if(Gdx.input.justTouched()){
		// game.setScreen(new GameScreen(game));
		// }
	}

	@Override
	public void resize(int width, int height) {
		stage.setViewport(width, height, true);
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		stage.dispose();
		this.screenRenderer.dispose();
	}

	/**
	 * Called when a finger went down on the screen or a mouse button was
	 * pressed. Reports the coordinates as well as the pointer index and mouse
	 * button (always Buttons.LEFT for touch screens). *
	 **/
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int botton) {
		// the pointer is the index of the finger who touched the screen
		return false;
	}

	/**
	 * Called when a finger is being dragged over the screen or the mouse is
	 * dragged while a button is pressed. Reports the coordinates and pointer
	 * index. The button is not reported as multiple buttons could be pressed
	 * while the mouse is being dragged. You can use Gdx.input.isButtonPressed()
	 * to check for a specific button.
	 * **/
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	/**
	 * Called when a finger was lifted from the screen or a mouse button was
	 * released. Reports the last known coordinates as well as the pointer index
	 * and mouse button (always Buttons.Left for touch screens).
	 * */
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int botton) {
		return false;
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

}
