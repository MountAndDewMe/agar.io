//Import the following libraries to be used in the game:
import java.awt.Color;
import java.util.Random;

/** Menu Class - The Main Menu screen that the user interacts with before the game officially starts 
 * @author Gian Calica
 */
public class Menu {
	/** Screen Width */
	private final int SCREEN_WIDTH = Game.SCREEN_WIDTH;
	/** Screen Height */
	private final int SCREEN_HEIGHT = Game.SCREEN_HEIGHT;
	/** Half of the Screen Width */
	private final int HALF_SCREEN_WIDTH =  SCREEN_WIDTH / 2;
	/** Half of the Screen Height */
	private final int HALF_SCREEN_HEIGHT = SCREEN_HEIGHT / 2;

	/*
	 * Initial Phase: Overview Screen
	 * First Phase: Telling the user to enter a user name
	 * Second Phase: Telling the user to pick a color
	 * Third Phase: Run the game
	 */

	////Initial Phase Elements////
	/** Thickness value for the border outline for any element that uses a border outline */
	private final int BORDER = 8;
	/** EZText Elements */
	private EZText backText, title, titleShadow, playButtonText, howToPlayButtonText, optionsText;
	/** EZRectangle Elements */
	private EZRectangle backButton, playButton, howToPlayButton, optionsButton;
	/** EZRectangle outline borders Elements */
	private EZRectangle backOutline, playButtonOutline, howToPlayButtonOutline, optionsOutline;
	/** Maximum amount of EZCIrcles drawn on the Menu background */
	private final int MAX_BACKGROUND_CIRCS = 40;
	/** Array that holds the EZCircles used as a background for Menu */
	private EZCircle[] backgroundCircs = new EZCircle[MAX_BACKGROUND_CIRCS];

	////Initial Phase Branches Booleans////
	//These booleans are true if the user has entered that branch of the Menu
	private boolean initialPhaseBranchHowToPlay = false;
	private boolean initialPhaseBranchOptions = false;

	////Initial Phase - How To Play Branch Elements////
	Tutorial tutorial;
	
	////Initial Phase - Options Branch Elements////
	Options options;

	////First Phase Elements////
	/** Prompts the user to enter a user name in the console */
	public EZText enterUsername, enterUsernameShadow;

	////Second Phase Elements////
	/** Click-able EZCircles that the user interacts with to choose a color for the Player */
	private EZCircle red, orange, yellow, green, blue, purple, pink;
	/** The color of the EZCircles */
	private Color cRed, cOrange, cYellow, cGreen, cBlue, cPurple, cPink;
	/** Prompts the user to choose a color for the Player */
	private EZText chooseColor;

	////Phase Booleans////
	//These booleans are true if the user has entered that phase. 
	public boolean firstPhase = false;
	public boolean secondPhase = false;
	public boolean thirdPhase = false;

	public Menu() { //Constructor 
		options = new Options();
		tutorial = new Tutorial();
		drawInitialPhase();
	}

	/** Draws the main overview of the Menu of the game */
	public void drawInitialPhase() {
		if(true) {
			Random rand = new Random();
			//EZCircles with random sizes and colors as the background to be used in Initial Phase, all the Initial Phase Branches, and up until first phase.
			for(int i = 0; i < MAX_BACKGROUND_CIRCS; i++) {
				int x = rand.nextInt(SCREEN_WIDTH);
				int y = rand.nextInt(SCREEN_HEIGHT);
				int rad = rand.ints(30, 125).findFirst().getAsInt();

				int r = rand.nextInt(255);
				int g = rand.nextInt(255);
				int b = rand.nextInt(255);
				backgroundCircs[i] = EZ.addCircle(x, y, rad * 2, rad * 2 , new Color(r, g, b), true);
			}

			//Title that shows the name of our game
			titleShadow = EZ.addText(HALF_SCREEN_WIDTH + 10, HALF_SCREEN_HEIGHT - 290, "", Color.black, 100);
			title = EZ.addText(HALF_SCREEN_WIDTH, HALF_SCREEN_HEIGHT - 300, "", Color.blue, 100);
			title.setFont("MarshyKate.ttf");
			titleShadow.setFont("MarshyKate.ttf");
			title.setMsg(Game.GAME_TITLE);
			titleShadow.setMsg(Game.GAME_TITLE);

			//Outline for the EZRectangle branches
			playButtonOutline =  EZ.addRectangle(200, SCREEN_HEIGHT - 600, 300 + BORDER, 100 + BORDER, Color.black, true);
			howToPlayButtonOutline = EZ.addRectangle(200, SCREEN_HEIGHT - 450, 300 + BORDER, 100 + BORDER, Color.black, true);
			optionsOutline = EZ.addRectangle(200, SCREEN_HEIGHT - 300, 300 + BORDER, 100 + BORDER, Color.black, true);

			//Click-able EZRectangles that the user interacts with to go through the different branches of initial phase
			playButton = EZ.addRectangle(200, SCREEN_HEIGHT - 600, 300, 100, Color.green, true);
			howToPlayButton = EZ.addRectangle(200, SCREEN_HEIGHT - 450, 300, 100, Color.green, true);
			optionsButton = EZ.addRectangle(200, SCREEN_HEIGHT - 300, 300, 100, Color.green, true);

			//EZText that tells the user of what each the EZRectangles do
			playButtonText = EZ.addText(playButton.getXCenter(), playButton.getYCenter(), "Play", Color.black, 20);
			howToPlayButtonText = EZ.addText(howToPlayButton.getXCenter(), howToPlayButton.getYCenter(), "How to Play", Color.black, 20);
			optionsText = EZ.addText(optionsButton.getXCenter(), optionsButton.getYCenter(), "Options", Color.black, 20);
		}
	}

	/** Handles the mouse click interaction of the initial phase
	 * @see drawMenu() in Game.java
	 */
	public void initialPhaseInteract() {
		while(true) {
			//We have to call EZ.refreshScreen() or else it will not pick up the following cursor events
			EZ.refreshScreen(); 
			int x = EZInteraction.getXMouse();
			int y = EZInteraction.getYMouse();

			if(EZInteraction.wasMouseLeftButtonPressed()) {
				if(playButton.isPointInElement(x, y)) {
					removeInitialPhase();
					firstPhase = true; //We start the official game
					break;
				} else if(howToPlayButton.isPointInElement(x, y)) {
					initialPhaseBranchHowToPlay = true; //Indicates that the user has entered the How to Play branch of initial phase
					drawInitialPhaseBranchHowToPlay();
					break;
				} else if(optionsButton.isPointInElement(x, y)) {
					initialPhaseBranchOptions = true; //Indicates that the user has entered the Options branch of initial phase
					drawInitialPhaseBranchOptions();
					break;
				}
			}
		}
	}

	/** Draws the How to Play branch of the initial phase. Contains info on game mechanics*/
	public void drawInitialPhaseBranchHowToPlay() {
		if(initialPhaseBranchHowToPlay) {
			hideInitialPhase();
			tutorial.drawTutorial();
			drawReturnToMenu();
		}
	}

	/** Draws the Options branch of the initial phase. Allows the user to edit certain values of the game. 
	 * @see Options.java
	 */
	public void drawInitialPhaseBranchOptions() {
		if(initialPhaseBranchOptions) {
			hideInitialPhase();
			options.drawOptions();
			drawReturnToMenu();
		}
	}

	/** Hides the elements of initial phase. 
	 * Instead of removing the elements of the initial phase and having to re-add them back again when we go back to the initial phase 
	 * from any of the initial phase branches, we simply hide it */
	public void hideInitialPhase() {
		title.hide();
		playButton.hide();
		howToPlayButton.hide();
		optionsButton.hide();

		titleShadow.hide();
		playButtonOutline.hide();
		howToPlayButtonOutline.hide();
		optionsOutline.hide();

		playButtonText.hide();
		howToPlayButtonText.hide();
		optionsText.hide();
	}

	/** Shows the elements of initial phase
	 * @see hideInitialPhase()
	 */
	public void showInitialPhase() {
		title.show();
		playButton.show();
		howToPlayButton.show();
		optionsButton.show();

		titleShadow.show();
		playButtonOutline.show();
		howToPlayButtonOutline.show();
		optionsOutline.show();

		playButtonText.show();
		howToPlayButtonText.show();
		optionsText.show();

		initialPhaseInteract();
	}

	/** Removes the EZEelements of the initial phase */
	public void removeInitialPhase() {
		EZ.removeEZElement(title);
		EZ.removeEZElement(playButton);
		EZ.removeEZElement(howToPlayButton);
		EZ.removeEZElement(optionsButton);

		EZ.removeEZElement(titleShadow);
		EZ.removeEZElement(playButtonOutline);
		EZ.removeEZElement(howToPlayButtonOutline);
		EZ.removeEZElement(optionsOutline);

		EZ.removeEZElement(playButtonText);
		EZ.removeEZElement(howToPlayButtonText);
		EZ.removeEZElement(optionsText);
	}

	/** Removes the EZCircles in the background */
	public void removeInitialPhaseBackground() {
		for(int i = 0; i < MAX_BACKGROUND_CIRCS; i++) {
			EZ.removeEZElement(backgroundCircs[i]);
		}
	}

	/** Removes all the elements of the branches in initial phase */
	public void removeInitialPhaseBranchElements() {
		//initialPhaseBranchHowToPlay Elements
		EZ.removeEZElement(tutorial.tutorialScreenOutline);
		EZ.removeEZElement(tutorial.tutorialScreen);
		EZ.removeEZElement(tutorial.tutorialScreenTitle);
		
		EZ.removeEZElement(tutorial.player);
		EZ.removeEZElement(tutorial.blobs);
		EZ.removeEZElement(tutorial.dots);
		EZ.removeEZElement(tutorial.virus);
		
		EZ.removeEZElement(tutorial.line1);
		EZ.removeEZElement(tutorial.line2);
		EZ.removeEZElement(tutorial.line3);
		EZ.removeEZElement(tutorial.line4);
		EZ.removeEZElement(tutorial.line5);
		EZ.removeEZElement(tutorial.line6);
		EZ.removeEZElement(tutorial.line7);
		
		//initialPhaseBranchOptions Elements
		EZ.removeEZElement(options.defaultButton);
		EZ.removeEZElement(options.optionsScreen);
		EZ.removeEZElement(options.optionsScreenOutline);
		EZ.removeEZElement(options.optionsScreenTitle);

		EZ.removeEZElement(options.maxDots);
		EZ.removeEZElement(options.maxBlobs);
		EZ.removeEZElement(options.maxViruses);
		EZ.removeEZElement(options.maxPlayerMass);
		EZ.removeEZElement(options.maxBlobMass);
		EZ.removeEZElement(options.dotMass);

		EZ.removeEZElement(options.maxDotsVal);
		EZ.removeEZElement(options.maxBlobsVal);
		EZ.removeEZElement(options.maxVirusesVal);
		EZ.removeEZElement(options.maxPlayerMassVal);
		EZ.removeEZElement(options.maxBlobMassVal);
		EZ.removeEZElement(options.dotMassVal);

		EZ.removeEZElement(options.down1);
		EZ.removeEZElement(options.down2);
		EZ.removeEZElement(options.down3);
		EZ.removeEZElement(options.down4);
		EZ.removeEZElement(options.down5);
		EZ.removeEZElement(options.down6);
		EZ.removeEZElement(options.up1);
		EZ.removeEZElement(options.up2);
		EZ.removeEZElement(options.up3);
		EZ.removeEZElement(options.up4);
		EZ.removeEZElement(options.up5);
		EZ.removeEZElement(options.up6);
	}

	/** Draws a "Back" button that allows the user to return back to the initial phase of the menu*/
	public void drawReturnToMenu() {
		backOutline = EZ.addRectangle(100, 100, 150 + BORDER, 100 + BORDER, Color.black, true);
		backButton = EZ.addRectangle(100, 100, 150, 100, Color.green, true);
		backText = EZ.addText(backButton.getXCenter(), backButton.getYCenter(), "Back", Color.black, 30);

		returnToMenuInteract();
	}

	/** Mouse interaction with the "Back Button
	 * @see drawReturnToMenu()
	 */
	public void returnToMenuInteract() {
		while(true) {
			EZ.refreshScreen();
			int x = EZInteraction.getXMouse();
			int y = EZInteraction.getYMouse();

			if(EZInteraction.wasMouseLeftButtonReleased()) {
				if(backButton.isPointInElement(x, y)) {
					removeReturnToMenu();
					removeInitialPhaseBranchElements();
					showInitialPhase();
					break;
				}
			}
		}
	}

	/** Removes the "Back" button 
	 * @see drawReturnToMenu()
	 */
	public void removeReturnToMenu() {
		EZ.removeEZElement(backOutline);
		EZ.removeEZElement(backButton);
		EZ.removeEZElement(backText);
	}

	/**
	 * Draws the first phase of Menu.
	 * Prompts the user to enter a user name in the console
	 * @See drawMenu() in Game.java
	 */
	public void drawFirstPhase() {
		if(firstPhase) {
			int shadowOffset = 4;
			enterUsernameShadow = EZ.addText(HALF_SCREEN_WIDTH + shadowOffset, HALF_SCREEN_HEIGHT + shadowOffset, "", new Color(0, 0, 0, 150), 60);
			enterUsernameShadow.setFont("MarshyKate.ttf");
			enterUsernameShadow.setMsg("ENTER A USER NAME IN THE CONSOLE!");

			enterUsername = EZ.addText(HALF_SCREEN_WIDTH, HALF_SCREEN_HEIGHT, "", Color.red, 60);
			enterUsername.setFont("MarshyKate.ttf");
			enterUsername.setMsg("ENTER A USER NAME IN THE CONSOLE!");
		}
	}

	/** Removes the elements of first phase */
	public void removeFirstPhase() {
		EZ.removeEZElement(enterUsername);
		EZ.removeEZElement(enterUsernameShadow);
		removeInitialPhaseBackground(); //We now remove the EZCircles in the background after this phase
	}

	/** Initializes second phase by setting the color variables and drawing second phase 
	 * @see drawMenu() in Game.java
	 */
	public void initiateSecondPhase() {
		if(secondPhase) {
			EZ.removeEZElement(enterUsername);
			setColor();
			drawSecondPhase();
		}
	}

	/** Set the colors variables to their respective colors */
	public void setColor() {
		cRed = new Color(255, 0, 0);
		cOrange = new Color(255, 50, 0);
		cYellow = new Color(255, 255, 0);
		cGreen = new Color(0, 200, 0);
		cBlue = new Color(0, 100, 200);
		cPurple = new Color(100, 0, 200);
		cPink = new Color(255, 50, 200);
	}

	/** Draws the second phase. Click-able colored EZCircles that the user picks for what color they want for their Player */
	public void drawSecondPhase() {
		int horizontalGap = 100; //Horizontal gap between the Circles
		int halfHorizontalGap = horizontalGap / 2;
		int circDiameter = 200; //Diameter of the EZCircles
		int doubleCircDiameter = circDiameter * 2;

		chooseColor = EZ.addText(HALF_SCREEN_WIDTH, 100, "Choose a color: ", Color.black, 170);

		red = EZ.addCircle(HALF_SCREEN_WIDTH - doubleCircDiameter - horizontalGap, SCREEN_HEIGHT / 3, circDiameter, circDiameter, cRed, true);
		orange = EZ.addCircle(HALF_SCREEN_WIDTH - circDiameter, SCREEN_HEIGHT / 3, circDiameter, circDiameter, cOrange, true);
		yellow = EZ.addCircle(HALF_SCREEN_WIDTH + circDiameter, SCREEN_HEIGHT / 3, circDiameter, circDiameter, cYellow, true);
		green = EZ.addCircle(HALF_SCREEN_WIDTH + doubleCircDiameter + horizontalGap, SCREEN_HEIGHT / 3, circDiameter, circDiameter, cGreen, true);

		blue = EZ.addCircle(HALF_SCREEN_WIDTH - (doubleCircDiameter - halfHorizontalGap), HALF_SCREEN_HEIGHT, circDiameter, circDiameter, cBlue, true);
		purple = EZ.addCircle(HALF_SCREEN_WIDTH, HALF_SCREEN_HEIGHT, circDiameter, circDiameter, cPurple, true);
		pink = EZ.addCircle(HALF_SCREEN_WIDTH + (doubleCircDiameter - halfHorizontalGap), HALF_SCREEN_HEIGHT, circDiameter, circDiameter, cPink, true);

	}

	/** 
	 * Mouse click interaction of the second phase. Clicking a particular EZCircle sets the color of the PLayer of that EZCircle
	 * @see drawSecondPhase() and setColor() 
	 * @see drawMenu() in Game.java 
	 */
	public void secondPhaseInteract() {
		while(true) {
			int x = EZInteraction.getXMouse();
			int y = EZInteraction.getYMouse();

			if(EZInteraction.wasMouseLeftButtonPressed()) {
				if(red.isPointInElement(x, y)) {
					Player.c = cRed;
					thirdPhase = true;
					removeSecondPhaseElements();
					break;
				} else if(orange.isPointInElement(x, y)) {
					Player.c = cOrange;
					thirdPhase = true;
					removeSecondPhaseElements();
					break;
				} else if(yellow.isPointInElement(x, y)) {
					Player.c = cYellow;
					thirdPhase = true;
					removeSecondPhaseElements();
					break;
				} else if(green.isPointInElement(x, y)) {
					Player.c = cGreen;
					thirdPhase = true;
					removeSecondPhaseElements();
					break;
				} else if(blue.isPointInElement(x, y)) {
					Player.c = cBlue;
					thirdPhase = true;
					removeSecondPhaseElements();
					break;
				} else if(purple.isPointInElement(x, y)) {
					Player.c = cPurple;
					thirdPhase = true;
					removeSecondPhaseElements();
					break;
				} else if(pink.isPointInElement(x, y)) {
					Player.c = cPink;
					thirdPhase = true;
					removeSecondPhaseElements();
					break;
				}
			}
			EZ.refreshScreen();
		}
	}

	/** Removes the elements of the second phase */
	public void removeSecondPhaseElements() {
		EZ.removeEZElement(chooseColor);
		EZ.removeEZElement(red);
		EZ.removeEZElement(orange);
		EZ.removeEZElement(yellow);
		EZ.removeEZElement(green);
		EZ.removeEZElement(blue);
		EZ.removeEZElement(purple);
		EZ.removeEZElement(pink);
	}
}
