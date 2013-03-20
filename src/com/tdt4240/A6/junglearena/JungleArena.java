package com.tdt4240.A6.junglearena;

import com.badlogic.gdx.Game;
import com.tdt4240.A6.junglearena.screens.TitleScreen;


public class JungleArena extends Game{

	@Override
	public void create() {
		setScreen(new TitleScreen(this)); 
	}

}
