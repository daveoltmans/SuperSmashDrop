package com.epicfalldown.FallDownGame;

//import java.util.Timer;
//import java.util.TimerTask;

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

		//board.addGameObject(new Ball(), 2, 8);
		Timer t = new Timer();
		
		//t.scheduleAtFixedRate(new TimerTask(){
			
			//@Override
			//public void run() {

				//Log.d(TAG, " Timer gaat af");
				// TODO Auto-generated method stub

				Running();
				
			//}
		//}, 0, 300000);
	
	}
	/** 
	 * Running()		De methode om de addingSpikes methode aan te roepen, en
	 * 					om na elke verandering een update te geven aan de view.
	 */
	public void Running() {

		GameBoard board = getGameBoard();

		Log.d(TAG, "Adding Spike");
		addingSpikes(randomNumber());
		Log.d(TAG, "Added Spike");
		board.updateView();
		UpdateSpikes();
		board.updateView();
		UpdateSpikes();
		board.updateView();
		
		Log.d(TAG, "Adding Spike");
		addingSpikes(randomNumber());
		Log.d(TAG, "Added Spike");
		board.updateView();
		UpdateSpikes();
		board.updateView();
		UpdateSpikes();
		board.updateView();
		
		Log.d(TAG, "Adding Spike");
		addingSpikes(randomNumber());
		Log.d(TAG, "Added Spike");
		board.updateView();
		UpdateSpikes();
		board.updateView();
		UpdateSpikes();
		board.updateView();
		
		Log.d(TAG, "Adding Spike");
		addingSpikes(randomNumber());
		Log.d(TAG, "Added Spike");
		board.updateView();
		UpdateSpikes();
		board.updateView();
		UpdateSpikes();
		board.updateView();
		
		Log.d(TAG, "Adding Spike");
		addingSpikes(randomNumber());
		Log.d(TAG, "Added Spike");
		board.updateView();
		UpdateSpikes();
		board.updateView();
		UpdateSpikes();
		board.updateView();
		
		Log.d(TAG, "Adding Spike");
		addingSpikes(randomNumber());
		Log.d(TAG, "Added Spike");
		board.updateView();
		UpdateSpikes();
		board.updateView();
		UpdateSpikes();
		board.updateView();
		
		Log.d(TAG, "Adding Spike");
		addingSpikes(randomNumber());
		Log.d(TAG, "Added Spike");
		board.updateView();
		UpdateSpikes();
		board.updateView();
		UpdateSpikes();
		board.updateView();
		
		Log.d(TAG, "Adding Spike");
		addingSpikes(randomNumber());
		Log.d(TAG, "Added Spike");
		board.updateView();
		UpdateSpikes();
		board.updateView();
		UpdateSpikes();
		board.updateView();
		
		

	}

	/**
	 * UpdateSpikes 	Verplaatst alle balken( heet nu nog spike) in de array, Als
	 * 					er boven een balk een ball staat dan gaat de ball 1 y in de Array omhoog.
	 * 					Als de bal aan het einde van het Array berijkt is de speler dood en word
	 * 					de ball verwijderd . Als een balk zelf aan het einde van de Array bereikt
	 * 					wordt deze verwijderd.
	 */
	public void UpdateSpikes() {
		GameBoard board = getGameBoard();
		// Update Methode
		for (int k = 0; k < 5; k++) {
			for (int j = 0; j < 18; j++) {
				String StringX = Integer.toString(k);
				String StringY = Integer.toString(j);
				Log.d(TAG, "updating");
				Log.d(TAG, StringX);
				Log.d(TAG, StringY);
				int x = k;
				int y = j;
				
				// methode om het hele array te checken voor object.Spike
				if (board.getObject(k, j) instanceof Spike) {
					board.moveObject(board.getObject(k, j), x, (y - 1));
					Log.d(TAG, "Object has moved");
				} else if (board.getObject(k, j) == null) {
					Log.d(TAG, "Geen object");
				}
			}
		}
		Log.d(TAG, "end of loop");
	}

	/** 
	 * addingSpikes()		De Methode om een gegeven nummer aan set van Spikes aan te roepen.
	 * @param i				i is het nummer van de set dat wordt aangeroepen
	 */
	public void addingSpikes(int i) {
		Log.d(TAG, Integer.toString(i) + "# adding spikes");
		//1. Spike, is een balk of kan als een spike gebruikt worden
		//2. Leaf, is een power up(wordt nog toegevoegd, heeft nog geen funtie)
		if (i == 0) {

			GameBoard board = getGameBoard();
			board.addGameObject(new Spike(), 1, 17);
			board.addGameObject(new Spike(), 2, 17);
			board.addGameObject(new Spike(), 3, 17);
			board.addGameObject(new Spike(), 4, 17);
		}
		else if (i == 1) {

			GameBoard board = getGameBoard();
			board.addGameObject(new Spike(), 0, 17);
			board.addGameObject(new Spike(), 1, 17);
			board.addGameObject(new Spike(), 2, 17);
			board.addGameObject(new Spike(), 3, 17);
		}

		else if (i == 2) {

			GameBoard board = getGameBoard();
			board.addGameObject(new Spike(), 0, 17);
			board.addGameObject(new Spike(), 2, 17);
			board.addGameObject(new Spike(), 3, 17);
			board.addGameObject(new Spike(), 4, 17);
		}

		else if (i == 3) {

			GameBoard board = getGameBoard();
			board.addGameObject(new Spike(), 0, 17);
			board.addGameObject(new Spike(), 1, 17);
			board.addGameObject(new Spike(), 3, 17);
			board.addGameObject(new Spike(), 4, 17);
		}

		else   {

			GameBoard board = getGameBoard();
			board.addGameObject(new Spike(), 0, 17);
			board.addGameObject(new Spike(), 1, 17);
			board.addGameObject(new Spike(), 2, 17);
			board.addGameObject(new Spike(), 4, 17);
		}
	}

	/**
	 * increaseScore()		inscreased de score
	 */
	public void increaseScore() {
		score++;

	}
	/**
	 * randomNumber()		maakt een random nummber van 1 tot 5
	 * @return				return de nummer dat is gemaakt in de methode.
	 */
	public int randomNumber(){
		int number = (int) ( Math.random() * 5);
		Log.d(TAG,(Integer.toString(number)));
		int i = 4;
		return number;
	}
}

///////////////////Prullebak//////////////////////////////////
// if (board.getObject(x, y - 1) instanceof Ball) {
// if ((y-2) == 0){
// board.removeObject(board.getObject(x, y - 1));
// Log.d(TAG, "ball removed");
// }
// else {board.moveObject(board.getObject(x, (y - 1)), x,
// (y - 2));
// }
// }
//

///
//Epicnoodlez11; <name> Jan-Willem </name> 
//