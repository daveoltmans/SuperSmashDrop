package com.epicfalldown.FallDownGame;

//import java.util.Timer;
//import java.util.TimerTask;

import java.util.Timer;
import java.util.TimerTask;

import android.R;
import android.R.bool;
import android.content.Intent;
import android.util.Log;

import com.epicfalldown.objects.Ball;
import com.epicfalldown.objects.Balk;
import com.epicfalldown.objects.Spike;
import com.epicfalldown.objects.Gat;
import com.example.epicfalldown.DoodMenu;
import com.example.epicfalldown.Game;
import com.example.epicfalldown.GameBoard;
import com.example.epicfalldown.GameObject;
import com.example.epicfalldown.MainActivity;
import com.example.epicfalldown.GameObject.Type;
import com.example.epicfalldown.StartMenu;

public class GameFallDown extends Game {
	/** Tag used for log messages */
	public static final String TAG = "EpicSuperFallDown";

	/** Reference to the main activity, so some labels can be updated. */
	private static MainActivity activity;

	/** The number of leafs eaten. */
	private static int score;
	private int timerCount;
	private Timer t;
	private boolean running;
	private static TimerTask tTask;
	private static TimerTask tTask2;
	private static Intent intent;
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

		// initialize game variables
		String mode = StartMenu.getSelectedMode();
		double t1 = 3000;
		double t2 = 1000;
		double t3 = 1500;
		double t4 = 500;
		// setting given GameMode
		if (mode.equals("Easy")) {
			Log.d("DEBUG", mode);
		} else if (mode.equals("Medium")) {
			Log.d("DEBUG", mode);
			t1 = t1 * 0.75;
			t2 = t2 * 0.75;
			t3 = t3 * 0.75;
			t4 = t4 * 0.75;

			
		} else if (mode.equals("Hard")) {
			Log.d("DEBUG", mode);
			t1 = t1 * 0.5;
			t2 = t2 * 0.5;
			t3 = t3 * 0.5;
			t4 = t4 * 0.5;

		} else {
			Log.d("DEBUG", "Mode gaat niet goed");
		}
		// Set the score and update the label
		score = 0;
		timerCount = 0;

		t = new Timer();
		tTask = new TimerTask() {

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
		tTask2 = new TimerTask() {

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
			t.scheduleAtFixedRate(tTask, 0, (int) t1);
			t.scheduleAtFixedRate(tTask2, 0, (int) t2);

			running = true;
		} else {
			timerCount++;
			Log.d(TAG, timerCount + "#TimerTask renewed");

			t.scheduleAtFixedRate(tTask, 0, (int) t3);

			t.scheduleAtFixedRate(tTask2, 0, (int) t4);
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
	 * Moves the SuperSmashDrop ball in the direction provided. This method has
	 * built-in checks to prevent the ball from moving into an already occupied
	 * space.
	 * 
	 * @param swipeDirection
	 *            The direction in which the ball should move (e.g.
	 *            SwipeGestureFilter.SWIPE_LEFT)
	 */
	public void swipeBall(int swipeDirection) {
		GameBoard board = getGameBoard();
		int x = 0, xNew = 0, y = 0, yNew = 0;

		// Nested loop for determining the current position of the ball on the
		// board
		for (int w = 0; w < board.getWidth(); w++) {
			for (int h = 0; h < board.getHeight(); h++) {
				if (board.getObject(w, h) instanceof Ball) {
					x = w;
					y = h;
				}
			}
		}

		// Checks if the user swiped left, right, or down
		if (swipeDirection == SwipeGestureFilter.SWIPE_LEFT)
			xNew = x - 1;
		else if (swipeDirection == SwipeGestureFilter.SWIPE_RIGHT)
			xNew = x + 1;
		else {

			int xOld = x;
			int yOld = y;

			yNew = y + 1;
			
			if (yNew > 10) {
				yNew = 10;
			}
			
			if (board.getObject(xOld, yNew) instanceof Balk) {
				return;
			}

			if (board.getObject(xOld, yNew) instanceof Gat) {
				board.removeObject(board.getObject(xOld, (yNew)));
				increaseScore(1);
				board.moveObject(board.getObject(xOld, yOld), xOld, yNew);
				board.updateView();
				return;
			}

			if (board.getObject(xOld, yNew) instanceof Spike) {
				board.removeObject(board.getObject(xOld, (yNew)));
				board.moveObject(board.getObject(xOld, yOld), xOld, yNew);
				board.updateView();
				endCurrentGame(true);
				return;
			}
			// Move the ball and give the signal to redraw the board
			board.moveObject(board.getObject(xOld, yOld), xOld, yNew);
			board.updateView();
			return;

		}

		// If new position is over the edge of the board, do nothing
		if (xNew >= board.getWidth())
			xNew = 0;
		if (xNew < 0)
			xNew = board.getWidth() - 1;

		// Takes a cheeky peek at the new position on the board
		GameObject objectAtNewPos = board.getObject(xNew, y);

		// Checks whether the next position of the ball is empty or not
		if (objectAtNewPos != null) {
			// The ball can't move through bars :(
			if (objectAtNewPos.getObjectType() == Type.Obstacle) {
				board.updateView();
				return;
			}

			// Lucky! The ball just hit a Power-Up!
			if (objectAtNewPos.getObjectType() == Type.PowerUp) {
				board.removeObject(objectAtNewPos);
				((GameFallDown) board.getGame()).increaseScore(1);
			}

			// Not so lucky, the ball just hit an obstacle which insta-kills!
			if (objectAtNewPos.getObjectType() == Type.KillingObstacle) {
				board.removeObject(board.getObject(x, y));
				endCurrentGame(true);
			}
		} else {
			// Move the ball and give the signal to redraw the board
			board.moveObject(board.getObject(x, y), xNew, y);
			board.updateView();
		}
	}

	/**
	 * Ends the current game, either because the player quit, or because they
	 * lost.
	 * 
	 * @param wasKilled
	 *            Determines if the player simply quit, or was killed.
	 */
	public static void endCurrentGame(boolean wasKilled) {
		if (wasKilled) {

			tTask.cancel();
			tTask2.cancel();
			intent = new Intent(activity, DoodMenu.class);
			if (null != intent) {
				activity.startActivity(intent);

			}
		}
		if (!wasKilled) {

			tTask.cancel();
			tTask2.cancel();
			intent = new Intent(activity, StartMenu.class);
			if (null != intent) {
				activity.startActivity(intent);

			}
		}

	}

	/**
	 * UpdateSpikes Verplaatst alle balken( heet nu nog spike) in de array, Als
	 * er boven een balk een ball staat dan gaat de ball 1 y in de Array omhoog.
	 * Als de bal aan het einde van het Array bereikt is de speler dood en word
	 * de ball verwijderd . Als een balk zelf aan het einde van de Array bereikt
	 * wordt deze verwijderd.
	 */
	public void UpdateSpikes() {
		GameBoard board = getGameBoard();
		// Update Methode
		for (int k = 0; k < board.getWidth(); k++) {
			for (int j = 0; j < board.getHeight(); j++) {
				Log.d(TAG, "updating");
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

					endCurrentGame(true);
					Log.d(TAG, "Player is kill");
				} else if ((board.getObject(x, y) instanceof Ball)
						&& ((board.getObject(x, (y + 1)) instanceof Gat))) {
					board.removeObject(board.getObject(x, y + 1));
					increaseScore(1);
					Log.d(TAG, "BAL IN DOEL");
				} else if ((board.getObject(x, y) instanceof Balk)
						|| (board.getObject(x, y) instanceof Spike)
						|| (board.getObject(x, y) instanceof Gat)) {
					board.moveObject(board.getObject(x, y), x, (y - 1));
					Log.d(TAG, "Object (obstacle) has moved");
				} else if (board.getObject(x, y) == null) {
					Log.d(TAG, "Geen object");
				}
			}
		}

		board.updateView();
		Log.d(TAG, "end of loop");
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

			board.addGameObject(new Gat(), 0, 12);
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
			board.addGameObject(new Gat(), 4, 12);
		}

		else if (i == 2) {

			GameBoard board = getGameBoard();

			board.addGameObject(returnRandomObject(), 0, 12);
			board.addGameObject(new Gat(), 1, 12);
			board.addGameObject(returnRandomObject(), 2, 12);
			board.addGameObject(returnRandomObject(), 3, 12);
			board.addGameObject(returnRandomObject(), 4, 12);
		}

		else if (i == 3) {

			GameBoard board = getGameBoard();

			board.addGameObject(returnRandomObject(), 0, 12);
			board.addGameObject(returnRandomObject(), 1, 12);
			board.addGameObject(new Gat(), 2, 12);
			board.addGameObject(returnRandomObject(), 3, 12);
			board.addGameObject(returnRandomObject(), 4, 12);
		}

		else {

			GameBoard board = getGameBoard();

			board.addGameObject(returnRandomObject(), 0, 12);
			board.addGameObject(returnRandomObject(), 1, 12);
			board.addGameObject(returnRandomObject(), 2, 12);
			board.addGameObject(new Gat(), 3, 12);
			board.addGameObject(returnRandomObject(), 4, 12);
		}
	}

	/**
	 * Has a one-in-twenty chance to return a spike object instead of a bar
	 * 
	 * @return 
	 */
	public GameObject returnRandomObject() {
		int number = (int) (Math.random() * 15);
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

	public static int getScore() {
		return score;
	}

}
// /
// Epicnoodlez11; <name> Jan-Willem </name>
//

