package com.example.epicfalldown;

/**
 * Each object type in the game will inherit from this class. Objects can then
 * be placed on the game board and moved around.
 * 
 * The image representation on the GameBoardView is handled from here as well.
 * By implementing the method: public String getImageId(); you can control which
 * image will be drawn for this object next time(s) it will be redrawn. The
 * imageID you return is the one you used as 'imageID' in the call to loadTile-
 * method in GameBoardView.
 * 
 * You can respond to the user clicking on the object by implementing public
 * void onTouched(GameBoard gameBoard);
 * 
 * @author Jan Stroet
 * @author Paul de Groot
 */
public abstract class GameObject {
	/** X-coordinate of the tile this object is at on the game board. */
	private int positionX;

	/** Y-coordinate of the tile this object is at on the game board. */
	private int positionY;

	/**
	 * Collection of GameObject types
	 */
	public enum Type {
		/**
		 * The SuperSmashDrop ball
		 */
		Ball,
		/**
		 * Generic, non-killing obstacles (e.g. wooden bars)
		 */
		Obstacle,
		/**
		 * All obstacles that result in an immediate Game Over when touched by
		 * the Type.Ball
		 */
		KillingObstacle,
		/**
		 * Non-hindering objects that reward the player when touched by the
		 * Type.Ball
		 */
		PowerUp,
		/**
		 * Objects that penalise the player when touched by the Type.Ball (e.g.
		 * the Poison Mushrooms from the Super Mario series)
		 */
		PowerDown
	};

	public enum PowerType {

		TotalAnihilation,
		
		GoldOne,

	};

	/**
	 * Initializes GameObject. Its position is (0,0) by default, but you will
	 * probably specify a position when you add it to the game board.
	 */
	public GameObject() {
		this.positionX = 0;
		this.positionY = 0;
	}

	/**
	 * @return the positionX
	 */
	public int getPositionX() {
		return positionX;
	}

	/**
	 * @return the positionY
	 */
	public int getPositionY() {
		return positionY;
	}

	/**
	 * Gets the GameObject type for this object. This is useful in determining
	 * what kind of object (e.g. something that kills the player, something that
	 * helps the player, etcetera) this is.
	 * 
	 * @return The Type of this GameObject
	 */
	public abstract Type getObjectType();
	

	public abstract PowerType getPowerType();

	/**
	 * The ImageID (used in calling GameBoardView.loadTile) of the image to show
	 * for this game object. This can be depending on the state of the object.
	 * 
	 * @return The ID of the image to show.
	 */
	public abstract String getImageId();

	/**
	 * Called when the user touched this game object.
	 * 
	 * @param gameBoard
	 *            The game board this object is on. This is useful since you can
	 *            use it to lookup neighboring objects, etc.
	 */
	public abstract void onTouched(GameBoard gameBoard);

	/** Used by GameBoard.moveObject() */
	void setPosition(int positionX, int positionY) {
		this.positionX = positionX;
		this.positionY = positionY;
	}
}
