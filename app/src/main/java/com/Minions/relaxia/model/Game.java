package com.Minions.relaxia.model;

import com.Minions.relaxia.themes.Theme;

/**
 * This is instance of active playing game
 */
public class Game {

	/**
	 * The board configuration
	 */
	public BoardConfiguration boardConfiguration;

	/**
	 * The board arrangement
	 */
	public BoardArrangement boardArrangement;

	/**
	 * The selected theme
	 */
	public Theme theme;

	public GameState gameState;

}
