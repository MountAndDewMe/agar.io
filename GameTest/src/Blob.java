//Import the following libraries to be used in the game:
import java.awt.Color;
import java.util.Random;

/**
 * Bob Class - Main "enemy" of the Player. Can eat other blobs.
 * @author Andrew Yagin
 * @see Rules in Game.java for the main properties of a Blob
 */
public class Blob {
	/** Maximum amount of Blobs allowed on the game */
	public static int maxBlobs = 3;
	/** Maximum mass allowed for Blobs */
	public static int maxMass = 300;

	/** A Blob object's x,y values */
	public int x, y;
	/** A Blob's destination x,y values. */
	public int desX, desY;
	/** A Blob object's radius */
	public int rad;
	/** A Blob object's mass*/
	public int mass;
	/** r,g,b Color values for a Blob's EZCircle */
	private int r, g, b;
	/** Color of a Blob's EZCircle */
	private Color c;

	/** EZCircle element for a Blob object*/
	private EZCircle blob;

	public Blob(int x, int y, int rad) { //Constructor
		this.x = x;
		this.y = y;
		this.rad = rad;

		mass = rad * 2;

		//If the random generated r,g,b values are not in the white scale palette, draw a EZCircle element for a Blob object
		if(isRGBNotWhite()) { 
			drawBlob();
		}
	}

	/** Draws a Blob object as an EZCircle */
	private void drawBlob() {
		blob = EZ.addCircle(x, y, rad * 2, rad * 2, c, true);
	}

	/**
	 * This is a method to make sure that the random generated r, g, b values do not generate a color that is "too white" because our background is white
	 * @return True if the random generated r, g, b values do not fall under the white scale palette
	 */
	private boolean isRGBNotWhite() {
		Random rand = new Random();

		r = rand.nextInt(255);
		g = rand.nextInt(255);
		b = rand.nextInt(255);

		//As long as all the r,g,b values are greater than 220, it is considered a white scale color. Therefore, regenerate random numbers again
		while(r > 220 && g > 220 && b > 220) {
			r = rand.nextInt(255);
			g = rand.nextInt(255);
			b = rand.nextInt(255);
		}

		this.c = new Color(r, g, b);
		return true;
	}
	
	/** Returns the radius of Player with respect to the width / 2 of pOutline */
	public int getBlobRad() {
		return blob.getWidth() / 2;
	}

	/** Returns the x-center coordinate of the EZCircle blob*/
	public int getBlobXCenter() {
		return blob.getXCenter();
	}

	/** Returns the y-center coordinate of the EZCircle blob*/
	public int getBlobYCenter() {
		return blob.getYCenter();
	}
	
	public void move() {
		
		x = blob.getXCenter();
		y = blob.getYCenter();
		long moveX = (long)(desX - x) / (long) mass;
		long moveY = (long)(desY - y) / (long) mass;
		blob.translateBy(moveX, moveY);
	}
	
	/**
	 * Increases the mass of the Blob 
	 * @param val is the value to increase the mass by
	 */
	public void increaseMass(int val) {
		this.mass += val;
	}

	/**
	 * Decreases the mass of the Blob 
	 * @param val is the value to decrease the mass by
	 */
	public void decreaseMass(int val) {
		this.mass -= val;
	}

	/** Updates the size of the Blob's EZCircle relative to the mass of the blob */
	public void updateBlob() {
		//Set a maximum cap to the mass of the Blob
		if(mass > maxMass) { 
			mass = maxMass;
		}

		blob.setWidth((int)mass);
		blob.setHeight((int)mass);
	}

	/** Removes the Blob EZCircle element */
	public void removeBlob() {
		EZ.removeEZElement(blob);
	}
}
