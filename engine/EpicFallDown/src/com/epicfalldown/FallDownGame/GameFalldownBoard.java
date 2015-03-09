package com.epicfalldown.FallDownGame;

import com.example.epicfalldown.GameBoard;

public class GameFalldownBoard extends GameBoard {
	private static final int GAMEBOARD_WIDTH = 5;
	private static final int GAMEBOARD_HEIGHT = 18;

	/**
	 * Create a new game board.
	 */
	public GameFalldownBoard() {
		super(GAMEBOARD_WIDTH, GAMEBOARD_HEIGHT);
	}

	public void onEmptyTileClicked(int x, int y) {
		// Nothing to do in this game.
	}

	public String getBackgroundImg(int mx, int my) {
		return null;
	}

}
