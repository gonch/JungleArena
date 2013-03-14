package com.tdt4240.A6.junglearena.activities;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.tdt4240.A6.junglearena.JungleArena;

public class JungleArenaActivity extends AndroidApplication {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useAccelerometer = false;
		config.useCompass = false;
		config.useWakelock = true;
		config.useGL20 = true;
		initialize(new JungleArena(), config);		
	}

}
