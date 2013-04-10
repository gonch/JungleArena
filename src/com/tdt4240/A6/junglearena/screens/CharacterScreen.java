package com.tdt4240.A6.junglearena.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tdt4240.A6.junglearena.model.Context;
import com.tdt4240.A6.junglearena.screens.skins.mySkin;
import com.tdt4240.A6.junglearena.view.ScreenRenderer;

public class CharacterScreen implements Screen, InputProcessor {

	private ScreenRenderer screenRenderer;
	private Game game;
	private Stage stage;
	private TextButton buttonsP1[] = new TextButton[3];
	private TextButton buttonsP2[] = new TextButton[3];
	private int selected1, selected2;
	Context context;
	TextButton next;
	boolean enter = true, bchange = false;

	public CharacterScreen(final Game game, Context context) {
		this.game = game;

		this.context = context;
		next = new TextButton("NEXT", mySkin.getMediumButtonSkin());
		next.align(Align.right);// TODO why is the opposite

	}

	public Table generateTable() {
		Table table = new Table();
		table.setFillParent(true);
		table.setWidth(500);// TODO HARD CODED

		// if (bchange) {
		// table.add(button2);
		// } else {
		// table.add(button1);
		// }
		// table.add(button2);
		table.row();

		if (buttonsP1.length == buttonsP2.length)// always true TODO
			for (int i = 0; i < buttonsP1.length; i++) {

				table.add(buttonsP1[i]);
				table.add(buttonsP1[i]).align(Align.right);
				table.add(buttonsP2[i]);
				table.row();
			}

		table.align(Align.center);
		return table;
	}

	@Override
	public void show() {
		next.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				context.setNameChar1("");// TODO
				context.setNameChar2("");// TODO
				game.setScreen(new MapSelectionScreen(game, context));
			}
		});

		for (int i = 0; i < buttonsP1.length; i++) {

			if (i == 0) {
				buttonsP1[i] = new TextButton("P" + 0,
						mySkin.getHugeRedButtonSkin());
			} else {
				buttonsP1[i] = new TextButton("P" + i,
						mySkin.getHugeButtonSkin());
			}
			buttonsP1[i].addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {

					for (int j = 0; j < buttonsP1.length; j++) { // the button
																	// selected
						if (event.getListenerActor().equals(buttonsP1[j])) {
							selected1 = j;
							buttonsP1[j].setStyle(mySkin.getHugeRedButtonSkin()
									.get("default", TextButtonStyle.class));
						} else// is not the buttons !select
						{
							buttonsP1[j].setStyle(mySkin.getHugeButtonSkin()
									.get("default", TextButtonStyle.class));
						}
					}
				}
			});
		}

		for (int i = 0; i < buttonsP2.length; i++) {
			if (i == 0) {
				buttonsP2[i] = new TextButton("P" + 0,
						mySkin.getHugeRedButtonSkin());
			} else {
				buttonsP2[i] = new TextButton("P" + i,
						mySkin.getHugeButtonSkin());
			}
			buttonsP2[i].addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {

					for (int i = 0; i < buttonsP2.length; i++) {
						// the button selected
						if (event.getListenerActor().equals(buttonsP2[i])) {
							selected2 = i;
							buttonsP2[i].setStyle(mySkin.getHugeRedButtonSkin()
									.get("default", TextButtonStyle.class));
						} else// is not the buttons !select
						{
							buttonsP2[i].setStyle(mySkin.getHugeButtonSkin()
									.get("default", TextButtonStyle.class));
						}
					}
				}
			});
		}
		stage = new Stage();
		stage.addActor(generateTable());
		stage.addActor(next);
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
