package com.tdt4240.A6.junglearena.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.tdt4240.A6.junglearena.view.TitleScreenRenderer;

public class TitleScreen implements Screen, InputProcessor{
	
	private TitleScreenRenderer titleScreenRenderer;
	private Game game;
	public TitleScreen(Game game){
		this.game = game;
	}
	
	@Override
	public void show() {
		this.titleScreenRenderer = new TitleScreenRenderer();
		Gdx.input.setInputProcessor(this);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);// clear the screen with black
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		this.titleScreenRenderer.render();
		
		if(Gdx.input.justTouched()){
			game.setScreen(new GameScreen(game));
		}
	}

	@Override
	public void resize(int width, int height) {
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
		this.titleScreenRenderer.dispose();
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
