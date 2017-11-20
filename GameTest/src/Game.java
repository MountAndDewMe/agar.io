/*
 * Rules:
 * 1. When the Player eats a DOT, increase Player's mass
 * 2. When the Player collides with a BLOB:
 *  - If the Player's mass < a Blob's mass, then the Player gets eaten by that Blob (Game Over)
 *  - If the Player's mass > a Blob's mass, then the Player eats that Blob and increase Player's mass + that Blob's mass
 * 3. When a Virus collides with a:
 *   - Player:
 *     * If the Player's mass < Virus's mass, then the Virus does nothing
 *     * If the Player's mass > Virus's mass, then the Player's mass decreases
 *   - Blob:
 *     * If the Blob's mass < Virus's mass, then the Virus does nothing
 *     * If the Blob's mass > Virus's mass, then the Player's mass decreases
 * 4. When a BLOB eats a DOT, increase that BLOB's mass
 * 5. When a BLOB collides with another BLOB:
 *  - If the Blob's mass < the other Blob's mass, then the Blob gets eaten by the other Blob
 *  - If the Blob's mass > the other Blob's mass, then the Blob eats that other Blob and increase the Blob's mass + that other Blob's mass
 * 6.The game runs forever until:
 *  - LOSE GAME: When the Player gets eaten by a BLOB or a VIRUS
 *  - WIN GAME: When the Player has eaten all the other Blobs
 */

/*
 * TODO: Code a better method for how the player follows the cursor
 * TODO: Fix endGame() to properly restart the game
 * TODO: Create an algorithm for better spawning system for blobs, dots, and viruses
 * TODO: Create AI for Blob
 */
import java.awt.Color;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {
	//Screen Width and Height
	public GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	public final int SCREEN_WIDTH = gd.getDisplayMode().getWidth();
	public final int SCREEN_HEIGHT = gd.getDisplayMode().getHeight();

	//Objects
	private ArrayList<Blobs> blobsArrayList = new ArrayList<Blobs>();
	private ArrayList<Dot> dotsArrayList = new ArrayList<Dot>();
	private ArrayList<Virus> virusesArrayList = new ArrayList<Virus>();
	
	//Input Name
	private EZText namePos;
	private String playerName;
	
	//Game Loop Booleans
	private boolean running = true;
	private boolean gameLoop = true;
	private String status = "";

	//End Game Elements
	public EZRectangle endScreen, yesButton, noButton;
	public EZText tryAgain, yesText, noText;
	
	//Timer Variables
	private long startTime;
	private long time;
	
	public static void main(String[] args) {
		Game game = new Game();
		game.init();
	}

	private void init() {
		EZ.initialize(SCREEN_WIDTH, SCREEN_HEIGHT);
		Menu menu = new Menu(SCREEN_WIDTH, SCREEN_HEIGHT);
		menu.initialPhaseInteract();
		
		menu.drawFirstPhase();
		playerName = getInputName(menu);
		
		menu.initiateSecondPhase();
		menu.secondPhaseInteract();
		
		if(menu.thirdPhase) {
			run();
		}
	}

	private void run() {
