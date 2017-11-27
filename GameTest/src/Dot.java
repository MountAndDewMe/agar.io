//Import the following libraries to be used in the game: 
import java.awt.Color;
import java.util.Random;

/**
 * Dot Class - "Food" for the Player and the Blobs. Increases mass of the Player and the Blobs when eaten
 * @author Simmy Javellana-Samonte
 * @see Rules in Game.java for the main properties of a Dot
 */
public class Dot {
	/** Maximum amount of Dots allowed on the game */
	public static int maxDots = 400;
	/** Fixed value for the radius of all the dots */
	public static final int DOT_RAD = 5;
	/** Value for the mass of all the dots */
	public static int mass = 20;

	/** A Dot object's x,y values*/
	public int x, y;
	/** r,g,b Color values for a Dot's EZCircle */
	private int r, g, b;
	/** Color of a Dot's EZCircle */
	private Color c;

	/** EZCircle element for a Dot object */
	private EZCircle dot;
	/** EZCirccle element for the outline border of a Dot object */
	private EZCircle dOutline;

	public Dot(int x, int y) { //Constructor
		this.x = x;
		this.y = y;

		//If the random generated r,g,b values are not in the white scale palette, draw a EZCircle element for a Dot object
		if(isRGBNotWhite()) { 
			drawDot();
		}
	}

	/** Draws a Dot object as an EZCircle */
	public void drawDot() {
		int border = 4; //Thickness value for the outline border for the Dot EZCircle
		dOutline = EZ.addCircle(x, y, DOT_RAD * 2 + 4, DOT_RAD * 2 + border, c.darker(), true); //The color of the outline is a darker version of the color of the dot
		dot = EZ.addCircle(x, y, DOT_RAD * 2, DOT_RAD * 2, c, true);
	}

	/**
	 * This is a method to make sure that the random generated r, g, b values do not generate a color that is "too white" because our background is white
	 * @return True if the random generated r, g, b values do not fall under a white scale palette
	 */
	private boolean isRGBNotWhite() {
		Random rand = new Random();

		r = rand.nextInt(255);
		g = rand.nextInt(255);
		b = rand.nextInt(255);
		while(r > 100 && g > 100 && b > 100) {
			r = rand.nextInt(255);
			g = rand.nextInt(255);
			b = rand.nextInt(255);
		}
		this.c = new Color(r, g, b);
		return true;
	}

	/** Returns the x-center coordinate of the EZCircle dot*/
	public int getDotXCenter() {
		return dot.getXCenter();
	}

	/** Returns the y-center coordinate of the EZCircle dot*/
	public int getDotYCenter() {
		return dot.getYCenter();
	}

	/** Removes the Dot EZCircle element */
	public void removeDot() {
		EZ.removeEZElement(dot);
		EZ.removeEZElement(dOutline);
	}
}
