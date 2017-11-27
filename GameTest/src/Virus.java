//Import the following libraries to be used in the game:
import java.awt.Color;

/**
 * Virus Class - "Obstacle" in the game. Decreases mass of a Blob or the Player if their mass is bigger than the Virus
 * @author Devin Seals
 * @see Rules in Game.java for the main properties of a Virus
 */
public class Virus {
	/** Maximum amount of Viruses allowed on the game */
	public static int maxViruses = 7;

	/** A Virus object's x,y values */
	public int x, y;
	/** A Virus object's radius */
	public int rad;
	/** A Virus object's mass */
	public int mass;

	/** Array that contains the x,y points of the Virus polygon*/
	private int[] xp, yp;
	/** Array that contains the x,y points of the shadow for the Virus polygon */
	private int[] xpShadow, ypShadow;
	/** Turn degrees by which the Virus polygon rotates by */
	private double turn = 0.0;

	/** EZPolygon element for a Virus object */
	private EZPolygon virus;
	/** EZPolygon element for the shadow of a Virus object*/
	private EZPolygon vShadow;

	public Virus(int x, int y, int rad) { //Constructor
		this.x = x;
		this.y = y;
		this.rad = rad;

		mass = rad * 2;

		drawVirus();
	}

	/** Draws a Virus object as an EZPolygon */
	public void drawVirus() {
		xp = new int[maxViruses];
		yp = new int[maxViruses];

		xpShadow = new int[maxViruses];
		ypShadow = new int[maxViruses];

		/*
		 * The Math to find the xp[i], yp[i] points for the Pentagon Polygon and its shadow is credited to this post:
		 * http://forum.codecall.net/topic/49508-polygon-tutorial/ 
		 */
		double angle = 2 * Math.PI / maxViruses;

		for(int i = 0; i <  maxViruses; i++) {
			double v = i * angle - turn;

			xp[i] = x + (int)Math.round(rad * Math.cos(v));
			yp[i] = y + (int)Math.round(rad * Math.sin(v));

			int offset = 6; //Offset value for the shadow EZPolygon
			xpShadow[i] = x + offset + (int)Math.round(rad * Math.cos(v));
			ypShadow[i] = y + offset + (int)Math.round(rad * Math.sin(v));
		}

		vShadow = EZ.addPolygon(xpShadow, ypShadow, new Color(0, 0, 0, 85), true);
		virus = EZ.addPolygon(xp, yp, Color.green, true);
		EZText vText = EZ.addText(virus.getXCenter(), virus.getYCenter(), "Virus", Color.black); //EZText that says "Virus" that is centered on the Virus polygons
		vText.setFontSize(mass / 5); //Scale the font size of vText by the mass / 5
	}

	/*
	 * The math to determine the degrees(turn) of rotateBy is credited to the same post:
	 * http://forum.codecall.net/topic/49508-polygon-tutorial/ 
	 */
	public void rotate() {
		double dv = 5 * 2 * Math.PI/360;

		turn = turn + dv;

		if(turn > 2 * Math.PI) {
			turn -= 2 * Math.PI;
		}

		virus.rotateBy(turn);
		vShadow.rotateBy(turn);
	}
}
