package com.tdt4240.A6.junglearena.controller.factories;

import java.util.ArrayList;
import java.util.List;

import com.tdt4240.A6.junglearena.controller.WorldController;
import com.tdt4240.A6.junglearena.controller.players.EasyAIPlayerController;
import com.tdt4240.A6.junglearena.controller.players.HardAIPlayerController;
import com.tdt4240.A6.junglearena.controller.players.HumanPlayerController;
import com.tdt4240.A6.junglearena.controller.players.PlayerController;
import com.tdt4240.A6.junglearena.model.Player;

public class PlayerControllerFactory {
	// Private constructor prevents instantiation from other classes
	private PlayerControllerFactory() {
	}

	/**
	 * SingletonHolder is loaded on the first execution of
	 * Singleton.getInstance() or the first access to SingletonHolder.INSTANCE,
	 * not before.
	 */
	private static class SingletonHolder {
		public static final PlayerControllerFactory INSTANCE = new PlayerControllerFactory();
	}

	public static PlayerControllerFactory getInstance() {
		return SingletonHolder.INSTANCE;
	}

	public List<String> getList() {
		List<String> players = new ArrayList<String>();
		players.add("easy");
		players.add("hard");
		return players;
	}

	public PlayerController createPlayerController(String playerMode, Player player, WorldController worldController) {
		PlayerController playerController = null;
		
		if (playerMode != null && playerMode.toLowerCase().equals("easy")) {
			playerController = new EasyAIPlayerController(player, worldController);
		}
		if (playerMode != null && playerMode.toLowerCase().equals("hard")) {
			playerController = new HardAIPlayerController(player, worldController);
		}
		if (playerMode != null && playerMode.toLowerCase().equals("human")) {
			playerController = new HumanPlayerController(player, worldController);
		}
		return playerController;
	}
}
