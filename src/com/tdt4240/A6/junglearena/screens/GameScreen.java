package com.tdt4240.A6.junglearena.screens;

import java.util.ArrayList;
import java.util.List;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.math.Vector2;
import com.tdt4240.A6.junglearena.controller.MapController;
import com.tdt4240.A6.junglearena.controller.WorldController;
import com.tdt4240.A6.junglearena.controller.factories.CharacterFactory;
import com.tdt4240.A6.junglearena.controller.factories.PlayerControllerFactory;
import com.tdt4240.A6.junglearena.controller.factories.WeaponFactory;
import com.tdt4240.A6.junglearena.controller.players.*;
import com.tdt4240.A6.junglearena.model.Context;
import com.tdt4240.A6.junglearena.model.JungleWorld;
import com.tdt4240.A6.junglearena.model.Player;
import com.tdt4240.A6.junglearena.model.characters.GameCharacter;
import com.tdt4240.A6.junglearena.model.gameControls.ControlsLayer;
import com.tdt4240.A6.junglearena.model.gameControls.GameButton;
import com.tdt4240.A6.junglearena.model.gameControls.TargetAccessor;
import com.tdt4240.A6.junglearena.view.ControlsRenderer;
import com.tdt4240.A6.junglearena.view.GameInfoRenderer;
import com.tdt4240.A6.junglearena.view.MapRenderer;
import com.tdt4240.A6.junglearena.view.WorldRenderer;

public class GameScreen implements Screen{

	private JungleWorld jungleWorld;
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
		//		music = Gdx.audio.newMusic(Gdx.files.internal("music/gameMusic.mp3"));
		//		music.setLooping(true);
		//		music.play();
		// pauseGame(); //START PAUSED FOR TESTING PURPOSES
		startNewGame();

		/* For the tween engin */
		Tween.registerAccessor(GameButton.class, new TargetAccessor());
		this.tweenManager = new TweenManager();
	}

	public void startNewGame() {
		// initialize players and characters
		List<PlayerController> playerControllers = new ArrayList<PlayerController>();

		CharacterFactory characterFactory = CharacterFactory.getInstance();
		GameCharacter gameCharacter1 = characterFactory.createCharacter(this.context.getNameChar1(), 100,
				this.context.getNameChar1(), new Vector2(0, 0), this.context.getNameChar1());
		Player player1 = new Player(this.context.getNamePlayer1(), 1, gameCharacter1);

		GameCharacter gameCharacter2 = characterFactory.createCharacter(this.context.getNameChar2(), 150,
				this.context.getNameChar2(), new Vector2(0, 0), this.context.getNameChar2());
		Player player2 = new Player(this.context.getNamePlayer2(), 2, gameCharacter2);

		// Create a new jungle world
		this.jungleWorld = new JungleWorld(player1, player2);
		this.jungleWorld.setAllAvailableWeaponNames(WeaponFactory.getInstance().getWeaponNames());
		//initialize world controller
		this.worldController = new WorldController(this.jungleWorld);



		//initialize the map

		this.mapController = new MapController(context.getEnvironment());// TODO hardcoded
		this.mapController.generateMap();
		this.jungleWorld.setMap(this.mapController.getMap());
		this.worldController.generateBox2DWorld();
		this.worldController.setCharacterStartingPositions();
		//initialize the renderers
		this.worldRenderer = new WorldRenderer(this.jungleWorld, this.context);
		this.mapRenderer = new MapRenderer(this.mapController.getMap());
		this.gameInfoRenderer = new GameInfoRenderer(jungleWorld);
		this.controls = this.worldController.getControls();
		this.controlsRenderer = new ControlsRenderer(controls);

		//initialize player controllers
		//player1 is always a human
		PlayerController playerController1 = PlayerControllerFactory.getInstance().createPlayerController("human",player1, worldController);
		playerController1.setMyTurn(true);//for default player1 starts
		//player2 depends on the user selection in the previous screens
		PlayerController playerController2 = PlayerControllerFactory.getInstance().createPlayerController(this.context.getDifficulty(),player2, worldController);
		playerController2.setMyTurn(false);
		playerControllers.add(playerController1);
		playerControllers.add(playerController2);
		this.worldController.setPlayerControllers(playerControllers);
		//		this.worldController.startNewTurn();
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
		if (this.worldController.isGameOver()) {
			// TODO: go to game over screen
		}
		if (this.worldController.isEndOfTurn()) {
			this.worldController.startNewTurn();
		}

		// TODO this code has to be run when the pause button is pressed
		if (Gdx.input.isTouched()) {
			GameButton pauseButton = this.worldController.getControls().getPauseButton();
			float screenX = Gdx.input.getX();
			float screenY = Gdx.graphics.getHeight() - Gdx.input.getY();
			if (pauseButton != null && pauseButton.checkSelected(screenX, screenY)) {
				pauseGame();
			}
		}

		/**
		 * 
		 * **/
		if (gamestatus == GAME_PAUSED) {
			if(gamestatus == GAME_PAUSED){
				PauseScreen pause = new PauseScreen(game,this);
				pause.render(dt);
			}
		}
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
}
