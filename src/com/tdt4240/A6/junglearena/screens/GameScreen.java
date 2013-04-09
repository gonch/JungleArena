package com.tdt4240.A6.junglearena.screens;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;
import aurelienribon.tweenengine.equations.Bounce;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.tdt4240.A6.junglearena.controller.MapController;
import com.tdt4240.A6.junglearena.controller.WorldController;
import com.tdt4240.A6.junglearena.model.JungleWorld;
import com.tdt4240.A6.junglearena.model.gameControls.ControlsLayer;
import com.tdt4240.A6.junglearena.model.gameControls.GameButton;
import com.tdt4240.A6.junglearena.model.gameControls.TargetAccessor;
import com.tdt4240.A6.junglearena.view.ControlsRenderer;
import com.tdt4240.A6.junglearena.view.GameInfoRenderer;
import com.tdt4240.A6.junglearena.view.MapRenderer;
import com.tdt4240.A6.junglearena.view.WorldRenderer;

public class GameScreen implements Screen, GestureListener, InputProcessor {

	private JungleWorld world;
	private WorldController worldController;
	private WorldRenderer worldRenderer;
	private Game game;
	private MapController mapController;
	private MapRenderer mapRenderer;
	private GameInfoRenderer gameInfoRenderer;
	private ControlsLayer controls;
	private ControlsRenderer controlsRenderer;
	private TweenManager tweenManager;
	private int width, height;

	public GameScreen(Game game) {
		this.game = game;
	}

	@Override
	public void show() {
		this.world = new JungleWorld();
		this.mapController = new MapController("desert");
		this.mapController.generateMap();
		this.world.setMap(this.mapController.getMap());
		this.worldController = new WorldController(this.world);
		this.worldRenderer = new WorldRenderer(this.world);
		this.mapRenderer = new MapRenderer(this.mapController.getMap());
		this.worldController.setCharacterStartingPositions();
		this.worldController.generateBox2DWorld();
		this.gameInfoRenderer = new GameInfoRenderer(world);
		Gdx.input.setInputProcessor(new GestureDetector(this));
		this.controls = this.worldController.getControls();
		this.controlsRenderer = new ControlsRenderer(controls);

		/* For the tween engin */
		Tween.registerAccessor(GameButton.class, new TargetAccessor());

		this.tweenManager = new TweenManager();
		// Tween.to(this.controls.getTarget(), TargetAccessor.POSITION_XY,
		// 10.0f)
		// .target(100, 200)
		// .start(tweenManager);

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
		// Gdx.gl.glClearColor(0.56f, 0.165f, 0.1f, 1);// clear the screen with
		// black
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		// this.mapRenderer.render();
		this.tweenManager.update(dt);
		this.worldController.update(dt);
		this.worldRenderer.render();
		this.gameInfoRenderer.render();
		this.controlsRenderer.render();
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
		// this.worldController.screenTouched(x,y);
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		this.worldController.screenTouched(x, Gdx.graphics.getHeight() - y);
		// this.worldController.angleTouched(x,Gdx.graphics.getHeight()-y);

		Tween.to(this.controls.getTarget(), TargetAccessor.POSITION_XY, 1.0f).target(x, Gdx.graphics.getHeight() - y)
				.start(tweenManager);

		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		// this.worldController.screenWithFling(velocityX,velocityY);
		// this.worldController.angleTouched(velocityX,Gdx.graphics.getHeight()-velocityY);
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
		return false;
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		this.worldController.screenTouched(screenX, screenY);
		// Tween.to(this.controls.getTarget(), TargetAccessor.POSITION_XY, 1.0f)
		// .target(screenX, Gdx.graphics.getHeight() -screenY)
		// .start(tweenManager);
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// Tween.to(this.controls.getTarget(), TargetAccessor.POSITION_XY, 1.0f)
		// .target(screenX, Gdx.graphics.getHeight() - screenY)
		// .start(tweenManager);
		// Tween.to(this, GameObject.XY, 1200, Bounce.OUT).target( screenX,
		// screenY ).delay(100).addToManager(this.tweenManager);
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		this.worldController.angleTouched(screenX, Gdx.graphics.getHeight() - screenY);
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// Tween.to(this.controls.getTarget(), TargetAccessor.POSITION_XY, 1.0f)
		// .target(screenX, Gdx.graphics.getHeight() - screenY)
		// .start(tweenManager);
		this.worldController.angleTouched(screenX, Gdx.graphics.getHeight() - screenY);
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
