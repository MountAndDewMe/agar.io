//Import the following libraries to be used in the game:
import java.awt.Color;

/**
 * Player Class - The Player object that the user moves around to play the game. 
 * @author Gian Calica
 * @see Rules in Game.java for the main properties of the Player
 */
public class Player {
	/** Maximum mass allowed for the Player */
	public static int maxMass = 350;
	/** Thickness value for the outline border of the EZCircle player */
	private static final int BORDER = 8;
	/** Starting value for the radius of the Player at the beginning of the game */
	private static final int PLAYER_RAD = 5;

	/** The Player's x,y values */
	public static int x, y;
	/** The Player's destination x,y values. The x,y coordinates of the cursor mouse that the Player follows */
	public static int desX, desY;
	/** The Player's mass*/
	public static int mass;
	/** Color of the Player's EZCircle*/
	public static Color c;

	/** EZCircle element for the Player */
	private static EZCircle player;
	/** EZCircle element for the outline border of the Player object*/
	private static EZCircle pOutline;

	public Player(int x, int y) { //Constructor
		Player.x = x;
		Player.y = y;

		mass = PLAYER_RAD * 2;

		drawPlayer();
	}

	/** Draws the Player object as an EZCircle */
	private void drawPlayer() {
		player = EZ.addCircle(x, y, PLAYER_RAD * 2,  PLAYER_RAD * 2,  c, true);
		pOutline = EZ.addCircle(x, y, PLAYER_RAD * 2 + BORDER, PLAYER_RAD * 2 + BORDER, new Color(0, 0, 0, 0), true);
		pOutline.pullToFront();
		player.pullToFront();
	}

	/** Method to move the Player. It follows behind the position of the mouse cursor. */
	public void move() {
		Player.desX = EZInteraction.getXMouse();
		Player.desY = EZInteraction.getYMouse();

		Player.x = player.getXCenter();
		Player.y = player.getYCenter();

		long moveX = (long)(desX - x) / (long)mass;
		long moveY = (long)(desY - y) / (long)mass;

		/*
		 * desX and desY are values from EZInteraction.getXMouse() and EZInteraction.getYMouse(), respectively
		 * Right now, when your mouse is outside the window screen, desX and desY return -1, which means that the Player is moving to -1, -1 when your mouse is outside the window screen
		 * As a workaround fix, we only move the Player circle if desX and desY are not equal to -1
		 */
		if(Player.desX != -1 && Player.desY != -1) {
			pOutline.translateBy(moveX, moveY);
			player.setColor(c);
			player.translateBy(moveX, moveY);
		}

	}

	/** Returns the radius of Player with respect to the width / 2 of pOutline */
	public static int getPlayerRad() {
		return pOutline.getWidth() / 2;
	}

	/**
	 * Checks if the x,y coordinate are within the EZCircle player 
	 * @param x coordinate to check
	 * @param y coordinate to check
	 * @return True if the specified x,y coordinates are within the EZCircle player
	 */
	public static boolean isPlayerPointInElement(int x, int y) {
		return player.isPointInElement(x, y);
	}

	/** Returns the x-center coordinate of the EZCircle player*/
	public static int getPlayerXCenter() {
		return player.getXCenter();
	}

	/** Returns the x-center coordinate of the EZCircle player*/
	public static int getPlayerYCenter() {
		return player.getYCenter();
	}

	/**
	 * Increases the mass of the Player
	 * @param val is the value to increase the mass by
	 */
	public static void increaseMass(int val) {
		Player.mass += val;
	}

	/**
	 * Decreases the mass of the Player
	 * @param val is the value to increase the mass by
	 */
	public static void decreaseMass(int val) {
		Player.mass -= val;
	}

	/** Updates the size of the Player's and its outline's EZCircles relative to the mass of the blob */
	public static void updatePlayer() {
		pOutline.setColor(c.darker()); //Sets the color of the outline to be a darker color of the Player

		//Set a maximum cap to the mass of the Player
		if(mass > maxMass) {
			mass = maxMass;
		}

		player.setWidth((int)mass);
		player.setHeight((int)mass);

		pOutline.setWidth((int)mass + BORDER);
		pOutline.setHeight((int)mass + BORDER);

		pOutline.pullToFront();
		player.pullToFront();
	}


	/** Removes the Player and its outline EZCircle elements */
	public static void remove() {
		EZ.removeEZElement(player);
		EZ.removeEZElement(pOutline);
	}
}
