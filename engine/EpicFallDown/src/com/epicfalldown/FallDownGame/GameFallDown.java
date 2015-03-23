package com.epicfalldown.FallDownGame;

//import java.util.Timer;
//import java.util.TimerTask;

import java.util.Timer;
import java.util.TimerTask;

import android.util.Log;

import com.epicfalldown.objects.Ball;
import com.epicfalldown.objects.Balk;
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
	private int timerCount;

	boolean running = false;

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

		// Tell the game board view which game board to show
		FallDownGameBoardView gameView = activity.getGameBoardView();
		GameBoard gameBoard = getGameBoard();
		gameView.setGameBoard(gameBoard);

		// Set size of the view to that of the game board
		gameView.setFixedGridSize(gameBoard.getWidth(), gameBoard.getHeight());
		// gameView.setVariableGridSize(120);

		// Reset the game
		initNewGame();

	}

	public void initNewGame() {
		// Set the score and update the label
		score = 0;
		timerCount = 0;

		Timer t = new Timer();
		TimerTask tTask = new TimerTask() {

			@Override
			public void run() {

				activity.runOnUiThread(new Runnable() {
					public void run() {

						GameBoard board = getGameBoard();
						Log.d("UI thread", "I am the UI thread");

						Log.d(TAG, " Timer gaat af");

						Running();
						board.updateView();

					}
				});
			};
		};
		TimerTask tTask2 = new TimerTask() {

			@Override
			public void run() {

				activity.runOnUiThread(new Runnable() {
					public void run() {
						GameBoard board = getGameBoard();
						UpdateSpikes();
						board.updateView();

					}
				});
			}
		};

		GameBoard board = getGameBoard();
		board.removeAllObjects();

		board.addGameObject(new Ball(), 2, 8);

		board.updateView();

		// GAME LOOP

		if (!running) {

			timerCount++;
			Log.d(TAG, timerCount + "# TimerTask Created");
			t.scheduleAtFixedRate(tTask, 0, 1500);

			t.scheduleAtFixedRate(tTask2, 0, 500);

			running = true;
		} else {
			timerCount++;
			Log.d(TAG, timerCount + "#TimerTask renewed");

			t.scheduleAtFixedRate(tTask, 0, 1500);

			t.scheduleAtFixedRate(tTask2, 0, 500);
		}

	}

	/**
	 * Running() De methode om de addingSpikes methode aan te roepen, en om na
	 * elke verandering een update te geven aan de view.
	 */
	public void Running() {

		GameBoard board = getGameBoard();

		addingSpikes(randomNumber());
		Log.d(TAG, "SpikesAdded");
		board.updateView();

	}

	/**
	 * UpdateSpikes Verplaatst alle balken( heet nu nog spike) in de array, Als
	 * er boven een balk een ball staat dan gaat de ball 1 y in de Array omhoog.
	 * Als de bal aan het einde van het Array berijkt is de speler dood en word
	 * de ball verwijderd . Als een balk zelf aan het einde van de Array bereikt
	 * wordt deze verwijderd.
	 */
	public void UpdateSpikes() {
		GameBoard board = getGameBoard();
		// Update Methode
		for (int k = 0; k < 5; k++) {
			for (int j = 0; j < 13; j++) {
				String StringX = Integer.toString(k);
				String StringY = Integer.toString(j);
				Log.d(TAG, "updating");
				// Log.d(TAG, StringX);
				// Log.d(TAG, StringY);
				int x = k;
				int y = j;

				// methode om het hele array te checken voor object
				if (board.getObject(x, y) instanceof Ball
						&& board.getObject(x, (y + 1)) instanceof Balk) {
					board.moveObject(board.getObject(x, y), x, (y - 1));
					Log.d(TAG, "ball has moved");
				} else if (board.getObject(k, j) instanceof Balk) {
					board.moveObject(board.getObject(k, j), x, (y - 1));
					Log.d(TAG, "Object has moved");
				} else if (board.getObject(k, j) == null) {
					Log.d(TAG, "Geen object");
				}
			}
		}

		board.updateView();
		Log.d(TAG, "end of loop");
		increaseScore(1);
	}

	/**
	 * addingSpikes() De Methode om een gegeven nummer aan set van Spikes aan te
	 * roepen.
	 * 
	 * @param i
	 *            i is het nummer van de set dat wordt aangeroepen
	 */
	public void addingSpikes(int i) {
		Log.d(TAG, Integer.toString(i) + "# adding spikes");
		// 1. Spike, is een balk of kan als een spike gebruikt worden
		// 2. Leaf, is een power up(wordt nog toegevoegd, heeft nog geen funtie)
		if (i == 0) {

			GameBoard board = getGameBoard();
			board.addGameObject(new Balk(), 1, 12);
			board.addGameObject(new Balk(), 2, 12);
			board.addGameObject(new Balk(), 3, 12);
			board.addGameObject(new Balk(), 4, 12);
		} else if (i == 1) {

			GameBoard board = getGameBoard();
			board.addGameObject(new Balk(), 0, 12);
			board.addGameObject(new Balk(), 1, 12);
			board.addGameObject(new Balk(), 2, 12);
			board.addGameObject(new Balk(), 3, 12);
		}

		else if (i == 2) {

			GameBoard board = getGameBoard();
			board.addGameObject(new Balk(), 0, 12);
			board.addGameObject(new Balk(), 2, 12);
			board.addGameObject(new Balk(), 3, 12);
			board.addGameObject(new Balk(), 4, 12);
		}

		else if (i == 3) {

			GameBoard board = getGameBoard();
			board.addGameObject(new Balk(), 0, 12);
			board.addGameObject(new Balk(), 1, 12);
			board.addGameObject(new Balk(), 3, 12);
			board.addGameObject(new Balk(), 4, 12);
		}

		else {

			GameBoard board = getGameBoard();
			board.addGameObject(new Balk(), 0, 12);
			board.addGameObject(new Balk(), 1, 12);
			board.addGameObject(new Balk(), 2, 12);
			board.addGameObject(new Balk(), 4, 12);
		}
	}

	/**
	 * increaseScore() increased de score met 1
	 */
	public void increaseScore(int aantal) {
		score += aantal;
		activity.updateScoreLabel(score);
	}

	/**
	 * randomNumber() maakt een random nummber van 1 tot 5
	 * 
	 * @return return de nummer dat is gemaakt in de methode.
	 */
	public int randomNumber() {
		int number = (int) (Math.random() * 5);
		Log.d(TAG, (Integer.toString(number)));
		int i = 4;
		return number;
	}
}
// /
// Epicnoodlez11; <name> Jan-Willem </name>
//

