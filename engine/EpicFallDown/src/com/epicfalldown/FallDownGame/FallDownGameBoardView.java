package com.epicfalldown.FallDownGame;

import com.epicfalldown.View.GameBoardView;
import com.epicfalldown.View.SpriteCache;
import com.epicfalldown.objects.Ball;
import com.epicfalldown.objects.Balk;
import com.epicfalldown.objects.Clear;
import com.epicfalldown.objects.Doom;
import com.epicfalldown.objects.Leaf;
import com.epicfalldown.objects.Random;
import com.epicfalldown.objects.Spike;
import com.example.epicfalldown.R;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

/**
 * A view on the CoolGame game board.
 * 
 * @author Jan Stroet
 * @author Paul de Groot
 */
public class FallDownGameBoardView extends GameBoardView {
	private static final String TAG = "GameView";

	/**
	 * Constructor.
	 */
	public FallDownGameBoardView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initGameView();
	}
	
	/**
	 * Constructor.
	 */
	public FallDownGameBoardView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initGameView();
	}
	
	/**
	 * Loads all images that will be used for the game.
	 */
	private void initGameView() {
		Log.d(TAG, "Loading all images");

		SpriteCache spriteCache = SpriteCache.getInstance(); 
		spriteCache.setContext(this.getContext());
		
		
		// Load the 'empty' cell bitmap and tell the tile view that this is the
		// image to use for cells without GameObject
		//spriteCache.loadTile("empty", R.drawable.cell);
		spriteCache.loadTile(Ball.BALL_IMAGE, R.drawable.ball0);
		spriteCache.loadTile(Balk.BALK_IMAGE, R.drawable.balk);
		spriteCache.loadTile(Spike.SPIKE_IMAGE, R.drawable.spike);
		spriteCache.loadTile(Clear.CLEAR_IMAGE, R.drawable.fireball);
		spriteCache.loadTile(Doom.GHOST_IMAGE, R.drawable.ghost);
		spriteCache.loadTile(Random.QUESTION_IMAGE, R.drawable.questionblock);
		setEmptyTile("empty");
		
		// Load the images for the GameObjects
	}
}
