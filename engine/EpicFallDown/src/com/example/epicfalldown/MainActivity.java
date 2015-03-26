package com.example.epicfalldown;

import com.epicfalldown.FallDownGame.FallDownGameBoardView;
import com.epicfalldown.FallDownGame.GameFallDown;
import com.epicfalldown.FallDownGame.SwipeGestureFilter;
import com.epicfalldown.FallDownGame.SwipeGestureFilter.SimpleGestureListener;
import com.example.epicfalldown.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * The main activity.
 * 
 * @author Paul de Groot
 * @author Jan Stroet
 */
public class MainActivity extends Activity implements SimpleGestureListener{
	private SwipeGestureFilter detector;
	private GameFallDown game;
	private FallDownGameBoardView gameView;
	private TextView scoreLabel;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// Load main.xml
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		// Detect touched area 
        detector = new SwipeGestureFilter(this,this);

		// Find some of the user interface elements
		gameView = (FallDownGameBoardView) findViewById(R.id.game);
		scoreLabel = (TextView) findViewById(R.id.scoreTextView);

		// Create the game object. This contains all data and functionality
		// belonging to the game
		game = new GameFallDown(this);

		// Do something when user clicks new game
		registerNewGameButton();

		// Tell user to start the game
		Toast.makeText(getApplicationContext(), "Let's start",
				Toast.LENGTH_SHORT).show();
	}

	/**
	 * Set a new score on the score label
	 * 
	 * @param newScore  The new score.
	 */

	public void updateScoreLabel() {
		
	}

	/**
	 * Returns the view on the game board.
	 */
	public FallDownGameBoardView getGameBoardView() {
		return gameView;
	}

	/**
	 * Install a listener to the 'New game'-button so that it starts a new
	 * game when clicked.
	 */
	private void registerNewGameButton() {
		// Find the 'New Game'-button in the activity
		final Button button1 = (Button) findViewById(R.id.newGameButton);
		
		// Add a click listener to the button that calls initNewGame()
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				game.initNewGame();
			}
		});
	}
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent me){
	    // Call onTouchEvent of SimpleGestureFilter class
	     this.detector.onTouchEvent(me);
	   return super.dispatchTouchEvent(me);
	}
	
	/**
	 * 'Simulates' swiping left and right through the D-pad left/right buttons (also, physical keyboard!)
	 */
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event)
	{
		switch (keyCode)
		{
			// Simulates left swipe
			case KeyEvent.KEYCODE_DPAD_LEFT:
				game.swipeBall(SwipeGestureFilter.SWIPE_LEFT);
				return true;
			// Simulates right swipe
			case KeyEvent.KEYCODE_DPAD_RIGHT:
				game.swipeBall(SwipeGestureFilter.SWIPE_RIGHT);
				return true;
			// Not left/right arrow, so let's send it back to the original method
			default:
				return super.onKeyUp(keyCode, event);
		}
	}
	
	/**
	 * Catches the onSwipe event, and calls the GameFallDown.swipeBall() method in response
	 */
	@Override
	public void onSwipe(int direction)
	{
		game.swipeBall(direction);
	}
	  
	 @Override
	 public void onDoubleTap(){}

}
