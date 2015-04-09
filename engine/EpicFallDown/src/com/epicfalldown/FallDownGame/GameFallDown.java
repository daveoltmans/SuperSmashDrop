package com.epicfalldown.FallDownGame;

import java.util.Timer;
import java.util.TimerTask;

import android.R;
import android.R.bool;
import android.content.Intent;
import android.util.Log;

import com.epicfalldown.objects.Ball;
import com.epicfalldown.objects.Balk;
import com.epicfalldown.objects.Clear;
import com.epicfalldown.objects.PowerUp;
import com.epicfalldown.objects.Spike;
import com.epicfalldown.objects.Gat;
import com.example.epicfalldown.DoodMenu;
import com.example.epicfalldown.Game;
import com.example.epicfalldown.GameBoard;
import com.example.epicfalldown.GameObject;
import com.example.epicfalldown.GameObject.PowerType;
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
	private static TimerTask tTask3;

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
		double t2 = 750;
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

		tTask3 = new TimerTask() {

			@Override
			public void run() {

				activity.runOnUiThread(new Runnable() {

					public void run() {
						GameBoard board = getGameBoard();

						updatePowerUp();
						board.updateView();
					}
				});
			}
		};

		GameBoard board = getGameBoard();
		board.removeAllObjects();

		board.addGameObject(new Ball(), 2, 8);

		// GAME LOOP

		if (!running) {

			timerCount++;
			Log.d(TAG, timerCount + "# TimerTask Created");
			t.scheduleAtFixedRate(tTask, 0, (int) t1);
			t.scheduleAtFixedRate(tTask2, 0, (int) t2);
			t.scheduleAtFixedRate(tTask3, 0, (int) t2);

			running = true;
			return;
		} else {

			Log.d(TAG, timerCount + "# TimerTask Renewed");
			t.scheduleAtFixedRate(tTask, 0, (int) t3);
			t.scheduleAtFixedRate(tTask2, 0, (int) t4);
			t.scheduleAtFixedRate(tTask3, 0, (int) t4);
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
		int x = 0, xNew = 0, y = 0;

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
			// Niek, hier komt jouw methode te staan. Vergeet niet dat je een
			// Boolean (true) moet teruggeven als de bal
			// een balk raakt. De 'null' achter return vervang je door jouw
			// methode.

			// return null;

			pogingBalValt(x, y);
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
			if (objectAtNewPos.getPowerType() == PowerType.GoldOne) {
				board.removeObject(objectAtNewPos);
				((GameFallDown) board.getGame()).increaseScore(1);
			}

			// Not so lucky, the ball just hit an obstacle which insta-kills!
			if (objectAtNewPos.getObjectType() == Type.KillingObstacle) {
				board.removeObject(board.getObject(x, y));
				endCurrentGame(true);

			}
			if (objectAtNewPos.getPowerType() == PowerType.TotalAnihilation) {
				board.removeAllObjects();
				board.addGameObject(new Ball(), xNew, y);
				increaseScore(2);
				board.updateView();
				return;
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
					board.removeObject(board.getObject(x, y + 1));
					endCurrentGame(true);
					Log.d(TAG, "Player is kill");

				} else if ((board.getObject(x, y) instanceof Ball)
						&& ((board.getObject(x, (y + 1)) instanceof Gat))) {
					board.removeObject(board.getObject(x, y + 1));
					increaseScore(1);
					Log.d(TAG, "BAL IN DOEL");

				} else if ((board.getObject(x, y) instanceof Ball)
						&& ((board.getObject(x, (y + 1)) instanceof Clear))) {
					board.removeAllObjects();
					board.addGameObject(new Ball(), x, y);
					increaseScore(2);
					Log.d(TAG, "CLEAR");

				} else if ((board.getObject(x, y) instanceof Balk)
						|| (board.getObject(x, y) instanceof Spike)
						|| (board.getObject(x, y) instanceof Gat || (board
								.getObject(x, y) instanceof Clear))) {
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

			board.addGameObject(new Clear(), 2, 12);
		}

		else if (i == 4) {

			GameBoard board = getGameBoard();

			board.addGameObject(returnRandomObject(), 0, 12);
			board.addGameObject(returnRandomObject(), 1, 12);
			board.addGameObject(returnRandomObject(), 2, 12);
			board.addGameObject(new Gat(), 3, 12);
			board.addGameObject(returnRandomObject(), 4, 12);
		} else if (i == 5) {
			GameBoard board = getGameBoard();

			board.addGameObject(returnRandomObject(), 0, 12);
			board.addGameObject(new Gat(), 1, 12);
			board.addGameObject(returnRandomObject(), 2, 12);
			board.addGameObject(new Gat(), 3, 12);
			board.addGameObject(returnRandomObject(), 4, 12);

		} else if (i == 6) {
			GameBoard board = getGameBoard();

			board.addGameObject(new Gat(), 0, 12);
			board.addGameObject(returnRandomObject(), 1, 12);
			board.addGameObject(new Gat(), 2, 12);
			board.addGameObject(returnRandomObject(), 3, 12);
			board.addGameObject(new Gat(), 4, 12);
		} else if (i == 7) {

			GameBoard board = getGameBoard();

			board.addGameObject(new Gat(), 0, 12);
			board.addGameObject(returnRandomObject(), 1, 12);
			board.addGameObject(new Gat(), 2, 12);
			board.addGameObject(returnRandomObject(), 3, 12);
			board.addGameObject(new Gat(), 4, 12);

		} else {

			GameBoard board = getGameBoard();

			board.addGameObject(returnRandomObject(), 0, 12);
			board.addGameObject(returnRandomObject(), 1, 12);
			board.addGameObject(new Gat(), 2, 12);
			board.addGameObject(returnRandomObject(), 3, 12);
			board.addGameObject(returnRandomObject(), 4, 12);
		}
	}

	/**
	 * Has a one-in-twenty chance to return a spike object instead of a bar
	 * 
	 * @return
	 */
	public GameObject returnRandomObject() {
		int number = (int) (Math.random() * 9);
		if (number == 5) {
			return new Spike();
		} else {
			return new Balk();
		}
	}

	/**
	 * increaseScore() increased de score met het gegeven aantal
	 */
	public void increaseScore(int aantal) {
		score += aantal;
	}

	/**
	 * randomNumber() maakt een random nummber van 1 tot 8
	 * 
	 * @return return de nummer dat is gemaakt in de methode.
	 */
	public int randomNumber() {
		int number = (int) (Math.random() * 8);
		Log.d(TAG, (Integer.toString(number)));
		return number;
	}
	/**
	 * Een methode om de int score terug te geven
	 * @return int score
	 */
	public static int getScore() {
		return score;
	}

	/**
	 * Checkt welk object onder het Ball-object zit. Deze methode returned als het object
	 * een spike it. Voert endCurrentGame(true) uit als het object een spike is. Voert 
	 * IncreaseScore als het object een gat is. En Removed alle objecten en voegt de
	 * Player, het Ball-bject, toe aan de array
	 * 
	 * @param x 			De x positie van de Bal
	 * @param y				De y positie van de Bal
	 */
	public void pogingBalValt(int x, int y) {

		GameBoard board = getGameBoard();
		// check of je niet te laag komt
		if (y > 9) {
			return;
		}

		// check of er geen Balk is die je tegenhoudt
		if (board.getObject(x, y + 1) instanceof Balk) {
			return;
		}

		// check of je niet jezelf in een Spike gooit zo ja dan sterf je muhaha
		// :(
		if (board.getObject(x, y + 1) instanceof Spike) {
			board.removeObject(board.getObject(x, y));
			board.updateView();
			endCurrentGame(true);
			return;
		}
		// checkt of er een gat in de balken lijn zit zo ja dan increase score
		// met 1
		if (board.getObject(x, y + 1) instanceof Gat) {
			board.removeObject(board.getObject(x, y + 1));
			increaseScore(1);
		}
		// checkt of er een clear-powerup onder je zit zo ja clear het board.
		if (board.getObject(x, y + 1) instanceof Clear) {
			board.removeObject(board.getObject(x, y + 1));
			board.removeAllObjects();
			board.addGameObject(new Ball(), x, y + 1);
			board.moveObject(board.getObject(x, y), x, y + 1);
			increaseScore(2);
			return;
		}

		// check of er een powerup onder de bal zit en pakt de powerup dan
		// if (board.getObject(x, y+1) instanceof Powerup) {
		// pakt de powerup methode uhm moet wachten tot Powerup class af is
		// }

		// verplaatst de bal
		board.moveObject(board.getObject(x, y), x, y + 1);
		board.updateView();
		return;
	}

	/**
	 * checks if the the PowerUp can move. If the powerUp hits the left wall
	 * it will be placed on the right side of the screen. if the powerUp hits
	 * the player while updating delete all items on the board if the powerUp
	 * is a Clear-Powerup.	
	 */
	public void updatePowerUp() {

		GameBoard board = getGameBoard();

		for (int c = 0; c < board.getWidth(); c++) {
			for (int d = 0; d < board.getHeight(); d++) {
				Log.d(TAG, "update PowerUps");

				int xOld = c;
				int yOld = d;

				xOld = xOld - 1;

				if (xOld < 0) {
					xOld = 4;
				} else if (xOld > 4) {
					xOld = 0;
				}

				if ((board.getObject(c, d) instanceof Clear)
						&& (board.getObject(xOld, (d)) instanceof Ball)) {
					board.removeObject(board.getObject(c, d));
					board.removeAllObjects();
					board.addGameObject(new Ball(), xOld, d);
					increaseScore(2);
					board.updateView();
				}
				
				if ((board.getObject(0, d) instanceof Clear) 
						&& (board.getObject(4, d-1)instanceof Ball)){

					board.removeObject(board.getObject(0, d));
					board.removeAllObjects();
					board.addGameObject(new Ball(), 4, d);
					increaseScore(2);
					board.updateView();
				}
				if (board.getObject(c, d) instanceof Clear) {
					board.moveObject(board.getObject(c, d), (xOld), d);
					board.updateView();
					return;
				}
			}
			board.updateView();
		}
	}

}
