package com.tdt4240.A6.junglearena.screens;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.tdt4240.A6.junglearena.controller.MapController;
import com.tdt4240.A6.junglearena.controller.WorldController;
import com.tdt4240.A6.junglearena.model.Context;
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
	private Context context;
	private Music music;

	public static final int GAME_RUNNING = 0;
    public static final int GAME_PAUSED = 1;

    private int gamestatus;
    
	public GameScreen(Game game, Context context) {
		this.game = game;
		this.context = context;
	}

	@Override
	public void show() {
		music =  Gdx.audio.newMusic(Gdx.files.internal("music/gameMusic.mp3"));
		music.setLooping(true);
		music.play();
		//pauseGame(); //START PAUSED FOR TESTING PURPOSES

		this.world = new JungleWorld();
		this.mapController = new MapController("jungle");
		this.mapController.generateMap();
		this.world.setMap(this.mapController.getMap());
		this.worldController = new WorldController(this.world);
		this.worldRenderer = new WorldRenderer(this.world);
		this.mapRenderer = new MapRenderer(this.mapController.getMap());
		this.worldController.setCharacterStartingPositions();
		this.worldController.generateBox2DWorld();
		this.gameInfoRenderer = new GameInfoRenderer(world);
		Gdx.input.setInputProcessor(new GestureDetector(this));
		Gdx.input.setInputProcessor(this);
		this.controls = this.worldController.getControls();
		this.controlsRenderer = new ControlsRenderer(controls);

		/* For the tween engin */
		Tween.registerAccessor(GameButton.class, new TargetAccessor());
		this.tweenManager = new TweenManager();
	}

	@Override
	public void dispose() {
		music.dispose();
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
		pauseGame();
	}

	@Override
	public void render(float dt) {
		// Gdx.gl.glClearColor(0.56f, 0.165f, 0.1f, 1);// clear the screen with
		// black
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		this.mapRenderer.render();
		this.tweenManager.update(dt);
		this.worldController.update(dt);
		this.worldRenderer.render();
		this.gameInfoRenderer.render();
		this.controlsRenderer.render();
		//TODO this code has to be run when the pause button is pressed
//		if (pausebutton is pressed) {
//            pauseGame();
//        }

		/**
		 * 
		 * **/
        //if (gamestatus == GAME_PAUSED) {
//        if(gamestatus == GAME_PAUSED){    
//        	PauseScreen pause = new PauseScreen(game,this);
//            pause.render(dt);
//        }
	}
	
	public void pauseGame() {
		music.pause();
        gamestatus = GAME_PAUSED;
    }

	public void resumeGame() {
		music.play();
		gamestatus = GAME_RUNNING;
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
		return true;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		this.worldController.screenTouched(x, Gdx.graphics.getHeight() - y);
		// this.worldController.angleTouched(x,Gdx.graphics.getHeight()-y);

		// Tween.to(this.controls.getTarget(), TargetAccessor.POSITION_XY,
		// 1.0f).target(x, Gdx.graphics.getHeight() - y)
		// .start(tweenManager);

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
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		ControlsLayer controls = this.worldController.getControls();
		GameButton target = controls.getTarget();
		if (target.checkSelected(screenX, Gdx.graphics.getHeight() - screenY)) {
			target.setSelected(true);
			target.setReleased(false);
		}
		GameButton fireButton = this.controls.getFireButton();
		if(fireButton.checkSelected(screenX, Gdx.graphics.getHeight()-screenY)){
			fireButton.setSelected(true);
			fireButton.setReleased(false);
		}
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (this.worldController.getControls().getTarget().isSelected()) {
			this.worldController.getControls().getTarget().setSelected(false);
		}
		if(this.worldController.getControls().getFireButton().isSelected()){
			this.worldController.shot();
			this.worldController.getControls().getFireButton().setSelected(false);
			this.worldController.getControls().getPowerBar().setSelected(false);
		}
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		if (this.worldController.getControls().getTarget().isSelected()) {
			this.worldController.angleTouched(screenX, Gdx.graphics.getHeight() - screenY);
		}if(this.worldController.getControls().getFireButton().isSelected()){
			this.worldController.getControls().getPowerBar().setSelected(true);
		}
		return true;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		if (this.worldController.getControls().getTarget().isSelected()) {
			this.worldController.angleTouched(screenX, Gdx.graphics.getHeight() - screenY);
		}
		return true;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

}
