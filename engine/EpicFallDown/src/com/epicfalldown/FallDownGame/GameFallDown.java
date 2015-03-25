package com.epicfalldown.FallDownGame;

//import java.util.Timer;
//import java.util.TimerTask;

import java.util.Timer;
import java.util.TimerTask;

import android.R.bool;
import android.util.Log;

import com.epicfalldown.objects.Ball;
import com.epicfalldown.objects.Balk;
import com.epicfalldown.objects.Spike;
import com.example.epicfalldown.Game;
import com.example.epicfalldown.GameBoard;
import com.example.epicfalldown.GameObject;
import com.example.epicfalldown.MainActivity;
import com.example.epicfalldown.GameObject.Type;

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
			t.scheduleAtFixedRate(tTask, 0, 3000);

			t.scheduleAtFixedRate(tTask2, 0, 1000);

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
	 * Moves the SuperSmashDrop ball in the direction provided. 
	 * This method has built-in checks to prevent the ball from moving into an already occupied space.
	 * 
	 * @param swipeDirection 	The direction in which the ball should move (e.g. SwipeGestureFilter.SWIPE_LEFT)
	 */
	public void swipeBall(int swipeDirection)
	{
		GameBoard board = getGameBoard();
		int x = 0, xNew = 0, y = 0;
		
		// Nested loop for determining the current position of the ball on the board
		for (int w = 0; w < board.getWidth(); w++)
		{
			for (int h = 0; h < board.getHeight(); h++)
			{
				if (board.getObject(w, h) instanceof Ball)
				{
					x = w;
					y = h;
				}
			}
		}
		
		// Checks if the user swiped left or right
		if (swipeDirection == SwipeGestureFilter.SWIPE_LEFT) xNew = x - 1;
		else xNew = x + 1;
		
		// If new position is over the edge of the board, do nothing
		if (xNew >= board.getWidth()) xNew = 0;
		if (xNew < 0) xNew = board.getWidth() - 1;
		
		// Takes a cheeky peek at the new position on the board
		GameObject objectAtNewPos = board.getObject(xNew, y);
		
		// Checks whether the next position of the ball is empty or not
		if (objectAtNewPos != null)
		{
			// The ball can't move through bars :(
			if (objectAtNewPos.getObjectType() == Type.Obstacle)
			{
				board.updateView();
				return;
			}

			// Lucky! The ball just hit a Power-Up!
			if (objectAtNewPos.getObjectType() == Type.PowerUp)
			{
				board.removeObject(objectAtNewPos);
				((GameFallDown) board.getGame()).increaseScore(1);
			}
			
			// Not so lucky, the ball just hit an obstacle which insta-kills!
			if (objectAtNewPos.getObjectType() == Type.KillingObstacle)
			{
				board.removeObject(board.getObject(x, y));
			}
		}
		else
		{
			// Move the ball and give the signal to redraw the board
			board.moveObject(board.getObject(x, y), xNew, y);
			board.updateView();
		}
	}
	
	/**
	 * Ends the current game, either because the player quit, or because they lost.
	 * @param wasKilled 	Determines if the player simply quit, or was killed.
	 */
	public void endCurrentGame(Boolean wasKilled)
	{
		// INSERT MAGIC HERE
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
		for (int k = 0; k < board.getWidth(); k++) {
			for (int j = 0; j < board.getHeight(); j++) {
				String StringX = Integer.toString(k);
				String StringY = Integer.toString(j);
				Log.d(TAG, "updating");
				// Log.d(TAG, StringX);
				// Log.d(TAG, StringY);
				int x = k;
				int y = j;

				// methode om het hele array te checken voor object
				if ((board.getObject(x, y) instanceof Ball)
						&& ((board.getObject(x, (y + 1)) instanceof Balk))) {
					board.moveObject(board.getObject(x, y), x, (y - 1));
					Log.d(TAG, "Object (Ball) has moved");
				} else if ((board.getObject(x, y) instanceof Ball)
						&& ((board.getObject(x, (y + 1)) instanceof Spike))) {
					board.removeObject(board.getObject(x, y));
					Log.d(TAG, "Player is kill");
				} else if ((board.getObject(x, y) instanceof Balk)
						|| (board.getObject(x, y) instanceof Spike)) {
					board.moveObject(board.getObject(x, y), x, (y - 1));
					Log.d(TAG, "Object (obstacle) has moved");
				}

				else if (board.getObject(x, y) == null) {
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
			board.addGameObject(returnRandomObject(), 1, 12);
			board.addGameObject(returnRandomObject(), 2, 12);
			board.addGameObject(returnRandomObject(), 3, 12);
			board.addGameObject(returnRandomObject(), 4, 12);
		} else if (i == 1) {

			GameBoard board = getGameBoard();
			board.addGameObject(returnRandomObject(), 0, 12);
			board.addGameObject(returnRandomObject(), 1, 12);
			board.addGameObject(returnRandomObject(), 2, 12);
			board.addGameObject(returnRandomObject(), 3, 12);
		}

		else if (i == 2) {

			GameBoard board = getGameBoard();
			board.addGameObject(returnRandomObject(), 0, 12);
			board.addGameObject(returnRandomObject(), 2, 12);
			board.addGameObject(returnRandomObject(), 3, 12);
			board.addGameObject(returnRandomObject(), 4, 12);
		}

		else if (i == 3) {

			GameBoard board = getGameBoard();
			board.addGameObject(returnRandomObject(), 0, 12);
			board.addGameObject(returnRandomObject(), 1, 12);
			board.addGameObject(returnRandomObject(), 3, 12);
			board.addGameObject(returnRandomObject(), 4, 12);
		}

		else {

			GameBoard board = getGameBoard();
			board.addGameObject(returnRandomObject(), 0, 12);
			board.addGameObject(returnRandomObject(), 1, 12);
			board.addGameObject(returnRandomObject(), 2, 12);
			board.addGameObject(returnRandomObject(), 4, 12);
		}
	}

	/**
	 * Has a one-in-twenty chance to return a spike object instead of a bar
	 * 
	 * @return
	 */
	public GameObject returnRandomObject() {
		int number = (int) (Math.random() * 20);
		if (number == 10) {
			return new Spike();
		} else {
			return new Balk();
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
		return number;
	}
}
// /
// Epicnoodlez11; <name> Jan-Willem </name>
//

