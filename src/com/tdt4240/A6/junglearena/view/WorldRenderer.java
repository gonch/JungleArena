package com.tdt4240.A6.junglearena.view;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.tdt4240.A6.junglearena.model.Context;
import com.tdt4240.A6.junglearena.model.JungleWorld;
import com.tdt4240.A6.junglearena.model.Player;
import com.tdt4240.A6.junglearena.model.Weapons.Weapon;
import com.tdt4240.A6.junglearena.model.characters.GameCharacter;

public class WorldRenderer {

	// TODO be
	private static final float CAMERA_WIDTH = Gdx.graphics.getWidth();
	private static final float CAMERA_HEIGHT = Gdx.graphics.getHeight();

	private JungleWorld jungleWorld;
	private OrthographicCamera cam;

	private ShapeRenderer shapeRenderer;
	private Box2DDebugRenderer debugRenderer;

	/** Textures **/
	private Texture texturePlayerOne;
	private Texture texturePlayerTwo;
	private Texture targetTexture;
	private Map<String, Texture> weaponName2textures;

	private SpriteBatch spriteBatch;
	private int width;
	private int height;
	private float ppuX; // pixels per unit on the X axis
	private float ppuY; // pixels per unit on the Y axis

	public WorldRenderer(JungleWorld world, Context context) {
		this.jungleWorld = world;
		this.cam = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
		this.cam.position.set(CAMERA_WIDTH / 2f, CAMERA_HEIGHT / 2f, 0);
		this.cam.update();
		this.ppuX = 10 / CAMERA_WIDTH;
		this.ppuY = 10 / CAMERA_WIDTH;
		spriteBatch = new SpriteBatch();
		this.shapeRenderer = new ShapeRenderer();
		loadTextures(context);
		this.debugRenderer = new Box2DDebugRenderer();
	}

	/*
	 * Converts from world coordinates to screen coordinates *
	 */
	public void setSize(int w, int h) {
		this.width = w;
		this.height = h;
		ppuX = width / CAMERA_WIDTH;
		ppuY = height / CAMERA_HEIGHT;
	}

	private void loadTextures(Context context) {
		texturePlayerOne = new Texture(Gdx.files.internal("models/" + context.getNameChar1().toLowerCase() + ".png"));
		texturePlayerTwo = new Texture(Gdx.files.internal("models/" + context.getNameChar2().toLowerCase() + ".png"));
		this.targetTexture = new Texture(Gdx.files.internal("crosshair.png"));
		this.weaponName2textures = new HashMap<String, Texture>();
		for (String weaponName : this.jungleWorld.getAllAvailableWeaponNames()) {
			String textureLocation = "weapons/" + weaponName.toLowerCase() + ".png";
			Texture weaponTexture = new Texture(Gdx.files.internal(textureLocation));
			this.weaponName2textures.put(weaponName, weaponTexture);
		}
	}

	public void render() {
//		debugRenderer.render(jungleWorld.getWorld(), cam.combined);//uncomment if you want to debug box2d objects
		spriteBatch.begin();
		drawTanks();
		drawWeapon();
		spriteBatch.end();
	}

	private void drawTanks() {
		Player player1 = jungleWorld.getPlayer1();
		Player player2 = jungleWorld.getPlayer2();
		GameCharacter ch1 = player1.getCharacter();
		GameCharacter ch2 = player2.getCharacter();

		Sprite rightprite = new Sprite(texturePlayerTwo);
		rightprite.flip(true, false);// now the second tank faces to the left
		spriteBatch.draw(texturePlayerOne, ch1.getPosition().x, ch1.getPosition().y, ch1.getSize().x, ch1.getSize().y);
		spriteBatch.draw(rightprite, ch2.getPosition().x, ch2.getPosition().y, ch2.getSize().x, ch2.getSize().y);
	}

	private void drawWeapon() {
		Weapon currentWeapon = this.jungleWorld.getCurrentWeapon();
		if (currentWeapon != null && currentWeapon.getBody() != null) {
			Texture weaponTexture = this.weaponName2textures.get(currentWeapon.getName());	
			spriteBatch.draw(weaponTexture, currentWeapon.getX(), currentWeapon.getY(), 30, 30);
		}
	}
}
