package com.Minions.relaxia.model;

public class BoardConfiguration {

	private static final int _6 = 6;
	private static final int _8 = 8;
	private static final int _10 = 10;
	private static final int _12 = 12;
	private static final int _16 = 16;
	private static final int _20 = 20;

	public final int difficulty;
	public final int numTiles;
	public final int numTilesInRow;
	public final int numRows;
	public final int time;

	public BoardConfiguration(int difficulty) {
		this.difficulty = difficulty;
		switch (difficulty) {
		case 1:
			numTiles = _6;
			numTilesInRow = 3;
			numRows = 2;
			time = 60;
			break;
		case 2:
			numTiles = _8;
			numTilesInRow = 4;
			numRows = 2;
			time = 90;
			break;
		case 3:
			numTiles = _10;
			numTilesInRow = 5;
			numRows = 2;
			time = 120;
			break;
		case 4:
			numTiles = _12;
			numTilesInRow = 4;
			numRows = 3;
			time = 150;
			break;
		case 5:
			numTiles = _16;
			numTilesInRow = 4;
			numRows = 4;
			time = 180;
			break;
		case 6:
			numTiles = _20;
			numTilesInRow = 5;
			numRows = 4;
			time = 210;
			break;	
		default:
			throw new IllegalArgumentException("Select one of predefined sizes");
		}
	}

}
