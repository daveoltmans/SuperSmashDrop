package com.epicfalldown.FallDownGame;

import java.util.Timer;
import java.util.TimerTask;

import android.util.Log;

import com.epicfalldown.objects.Ball;
import com.epicfalldown.objects.Spike;
import com.example.epicfalldown.Game;
import com.example.epicfalldown.GameBoard;
import com.example.epicfalldown.MainActivity;

public class GameFallDown extends Game {
	/** Tag used for log messages */
	public static final String TAG = "EpicSuperFallDown";

	/** Reference to the main activity, so some labels can be updated. */
	private MainActivity activity;

	/** The number of leafs eaten. */
	private int score;

	boolean running;
	long timer;

	/**
	 * Constructor.
	 * 
	 * @param activity
	 *            The main activity
	 */
	public GameFallDown(MainActivity activity) {
		// Create a new game board and couple it to this game
		super(new GameFalldownBoard());

		// Store reference to the main activity
		this.activity = activity;

		// Reset the game
		initNewGame();

		// Tell the game board view which game board to show
		FallDownGameBoardView gameView = activity.getGameBoardView();
		GameBoard gameBoard = getGameBoard();
		gameView.setGameBoard(gameBoard);

		// Set size of the view to that of the game board
		gameView.setFixedGridSize(gameBoard.getWidth(), gameBoard.getHeight());

	}

	public void initNewGame() {
		// Set the score and update the label
		score = 0;
		running = true;

		GameBoard board = getGameBoard();
		board.removeAllObjects();

		board.addGameObject(new Ball(), 2, 8);
		//Timer timer = new Timer();
		
		//timer.schedule(new TimerTask(){
			
			//public void run(){
				Running();
			//}
		//}, 30000);
		
	}

	public void Running() {

		GameBoard board = getGameBoard();
		Log.d(TAG, "Adding Spike");
		addingSpikes();
		Log.d(TAG, "Added Spike");
		board.updateView();
		UpdateSpikes();
		board.updateView();
		UpdateSpikes();
		board.updateView();
	}

	
	/**
	 * UpdateSpikes			Verplaatst alle balken( heet nu nog spike)  in de array,
	 * 						Als er boven een balk een ball staat dan gaat de ball 1
	 * 						y in de Array omhoog. Als de bal aan het einde van het 
	 * 						Array berijkt is de speler dood en word de ball verwijderd 
	 * 						. Als een balk zelf aan 
	 * 						het einde van de Array bereikt wordt deze verwijderd.
	 */
	public void UpdateSpikes() {
		GameBoard board = getGameBoard();
		// Update Methode
		for (int x = 0; x < 5; x++) {
			for (int y = 0; y < 18; y++) {
				String StringX = Integer.toString(x);
				String StringY = Integer.toString(y);
				Log.d(TAG, "updating");
				Log.d(TAG, StringX);
				Log.d(TAG, StringY);

				//methode om het hele array door te gaan 
				if (board.getObject(x, y) instanceof Spike) {
					if (board.getObject(x, y - 1) instanceof Ball) {
						if ((y-2) == 0){
							board.removeObject(board.getObject(x, y - 1));
							Log.d(TAG, "ball removed");
						}
						else {board.moveObject(board.getObject(x, (y - 1)), x,
								(y - 2));
						}
					}
					board.moveObject(board.getObject(x, y), x, (y - 1));
					Log.d(TAG, "Object has moved");
				} else if (board.getObject(x, y) == null) {
					Log.d(TAG, "Geen object");
				}
			}
		}
	}

	public void addingSpikes() {
		GameBoard board = getGameBoard();
		int i = (4 * (int) Math.random());

		if (i == 0) {
			board.addGameObject(new Spike(), 1, 17);
			board.addGameObject(new Spike(), 2, 17);
			board.addGameObject(new Spike(), 3, 17);
			board.addGameObject(new Spike(), 4, 17);
		}
		if (i == 1) {
			board.addGameObject(new Spike(), 0, 17);
			board.addGameObject(new Spike(), 1, 17);
			board.addGameObject(new Spike(), 2, 17);
			board.addGameObject(new Spike(), 3, 17);
		}

		if (i == 2) {
			board.addGameObject(new Spike(), 0, 17);
			board.addGameObject(new Spike(), 2, 17);
			board.addGameObject(new Spike(), 3, 17);
			board.addGameObject(new Spike(), 4, 17);
		}

		if (i == 3) {
			board.addGameObject(new Spike(), 0, 17);
			board.addGameObject(new Spike(), 1, 17);
			board.addGameObject(new Spike(), 3, 17);
			board.addGameObject(new Spike(), 4, 17);
		}

		if (i == 4) {
			board.addGameObject(new Spike(), 0, 17);
			board.addGameObject(new Spike(), 1, 17);
			board.addGameObject(new Spike(), 2, 17);
			board.addGameObject(new Spike(), 4, 17);
		}

	}

	public void increaseScore() {
		score++;

	}
}