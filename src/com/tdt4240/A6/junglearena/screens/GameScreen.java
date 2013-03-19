package com.tdt4240.A6.junglearena.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.tdt4240.A6.junglearena.controller.MapController;
import com.tdt4240.A6.junglearena.controller.WorldController;
import com.tdt4240.A6.junglearena.model.World;
import com.tdt4240.A6.junglearena.view.GameInfoRenderer;
import com.tdt4240.A6.junglearena.view.MapRenderer;
import com.tdt4240.A6.junglearena.view.WorldRenderer;

public class GameScreen implements Screen, GestureListener {

	private World world;
	private WorldController worldController;
	private WorldRenderer worldRenderer;
	private Game game;
	private MapController mapController;
	private MapRenderer mapRenderer;
	private GameInfoRenderer gameInfoRenderer;
	
	private int width, height;

	public GameScreen(Game game){
		this.game = game;
	}
	
	@Override
	public void show() {
		this.world = new World();
		this.mapController = new MapController("desert");
		this.mapController.generateMap();
		this.world.setMap(this.mapController.getMap());
		this.worldController = new WorldController(this.world);
		this.worldRenderer = new WorldRenderer(this.world);
		this.mapRenderer = new MapRenderer(this.mapController.getMap());
		this.worldController.setCharacterStartingPositions();
		this.gameInfoRenderer = new GameInfoRenderer(world);
		Gdx.input.setInputProcessor(new GestureDetector(this));
	}

	@Override
	public void dispose() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void render(float dt) {
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);// clear the screen with black
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		this.mapRenderer.render();
		this.worldRenderer.render();
		this.gameInfoRenderer.render();
	}

	/*
	 * Is called each time to resize the screen *
	 */
	@Override
	public void resize(int width, int height) {
		worldRenderer.setSize(width, height);
		this.width = width;
		this.height = height;
	}

	@Override
	public void resume() {
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		return true;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		this.worldController.screenTouched(x,y);
		return true;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
		return false;
	}


}
