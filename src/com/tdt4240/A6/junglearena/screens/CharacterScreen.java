package com.tdt4240.A6.junglearena.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tdt4240.A6.junglearena.screens.skins.mySkin;
import com.tdt4240.A6.junglearena.view.ScreenRenderer;

public class CharacterScreen implements Screen, InputProcessor{
	
	private ScreenRenderer screenRenderer;
	private Game game;
	private Stage stage;
	
	public CharacterScreen(final Game game){
		this.game = game;
		stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.setFillParent(true);
        Skin skin = mySkin.getHugeButtonSkin();
        Skin mediumSkin = mySkin.getMediumButtonSkin();
		
		// Create a button with the "default" TextButtonStyle. A 3rd parameter can be used to specify a name other than "default".
		final TextButton singlePlayerButton = new TextButton("P1", skin);
		final TextButton twoPlayerButton = new TextButton("P2", skin);
		final TextButton char11 = new TextButton("CHAR 1", skin);
		final TextButton char12 = new TextButton("CHAR 2", skin);
		final TextButton char13 = new TextButton("CHaR 3", skin);
		final TextButton char21 = new TextButton("CHAR 1", skin);
		final TextButton char22 = new TextButton("CHAR 2", skin);
		final TextButton char23 = new TextButton("CHaR 3", skin);
		
		final TextButton next = new TextButton("NEXT", mediumSkin);
		
		singlePlayerButton.pad(25);
		twoPlayerButton.pad(25);
		//labels
		table.add(singlePlayerButton);
		table.add(twoPlayerButton);
		table.row();
		//animal 1
		table.add(char11);
		table.add(char21);
		table.row();
		//animal 2
		table.add(char12);
		table.add(char22);
		table.row();
		//animal 3
		table.add(char13);
		table.add(char23);
		table.row();
		//next button
		table.align(Align.center);
		table.add(next);
		table.row();
		
		stage.addActor(table);
        
		char11.addListener(new ClickListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,int pointer, int button)
			{
				return true;
				
				
			}
		});
		twoPlayerButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				//game.setScreen(new CharacterScreen(game));
			}
		});
		next.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.setScreen(new MapSelectionScreen(game));
			}
		});
	}
	
	@Override
	public void show() {
		this.screenRenderer = new ScreenRenderer();
		Gdx.input.setInputProcessor(stage);
//		Gdx.input.setInputProcessor(this);
	}
	
	@Override
	public void render(float delta) {
		this.screenRenderer.render(stage);
//		if(Gdx.input.justTouched()){
//			game.setScreen(new GameScreen(game));
//		}
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
