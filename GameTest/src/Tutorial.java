import java.awt.Color;

public class Tutorial {
	/** Screen Width */
	private final int SCREEN_WIDTH = Game.SCREEN_WIDTH;
	/** Screen Height */
	private final int SCREEN_HEIGHT = Game.SCREEN_HEIGHT;
	/** Half of the Screen Width */
	private final int HALF_SCREEN_WIDTH =  SCREEN_WIDTH / 2;
	/** Half of the Screen Height */
	private final int HALF_SCREEN_HEIGHT = SCREEN_HEIGHT / 2;
	/** Thickness for the border outline of the tutorial screen */
	private final int BORDER = 8;
	
	/** EZImages to be used in the Tutorial Screen */
	public EZImage player, blobs, dots, virus;
	public EZRectangle tutorialScreen, tutorialScreenOutline;
	public EZText tutorialScreenTitle;
	public EZText line1, line2, line3, line4, line5, line6, line7;
	
	public Tutorial() {} //Default constructor
	
	public void drawTutorial() {
		tutorialScreenOutline = EZ.addRectangle(HALF_SCREEN_WIDTH, HALF_SCREEN_HEIGHT, SCREEN_WIDTH - 200 + BORDER, SCREEN_HEIGHT - 350 + BORDER, new Color(0, 0, 0, 120), true);
		tutorialScreen = EZ.addRectangle(HALF_SCREEN_WIDTH, HALF_SCREEN_HEIGHT, SCREEN_WIDTH - 200, SCREEN_HEIGHT - 350, new Color(Color.green.getRed(), Color.green.getGreen(), Color.green.getBlue(), 200), true);
		tutorialScreenTitle = EZ.addText(tutorialScreen.getXCenter(), tutorialScreen.getYCenter() - 330, "How to Play", Color.black, 50);
		
		int align = tutorialScreen.getXCenter();
		int verticalGap = 20;
		int fs = 15;
		Color c = Color.black;
		
		player = EZ.addImage("resources/tutorial/Player.png", align - 200, tutorialScreenTitle.getYCenter() + 120);
		blobs = EZ.addImage("resources/tutorial/Blobs.png", align + 200, tutorialScreenTitle.getYCenter() + 120);
		line1 = EZ.addText(align, player.getYCenter() + player.getHeight() / 2 + verticalGap, Game.GAME_TITLE + " " + "is a top-down strategy game inspired and similar to the popular web-based game Agar.io. You control the Player by moving the mouse around ", c, fs);
		line2 = EZ.addText(align, line1.getYCenter() + verticalGap, "and the Player will follow your cursor. In order to win the game, you must eat all the other enemy blobs moving around the map. You may only eat them", c, fs);
		line3 = EZ.addText(align, line2.getYCenter() + verticalGap, "when your mass is bigger than theirs, otherwise they can eat you, making you lose the game. ", c, fs);
		
		dots = EZ.addImage("resources/tutorial/Dots.png", align, line3.getYCenter() + 100);
		line4 = EZ.addText(align, dots.getYCenter() + dots.getHeight() / 2 + verticalGap, "These little circles are called Dots. They are the food for both the Player and the Blob", c, fs);
		line5 = EZ.addText(align, line4.getYCenter() + verticalGap, "They continously spawn around the world where the Player or a Blob can eat them to gain mass.", c, fs);
		
		virus = EZ.addImage("resources/tutorial/Virus.png", align, line5.getYCenter() + 90);
		virus.scaleTo(0.7f);
		line6 = EZ.addText(align, virus.getYCenter() + verticalGap + 60, "These green polygons are Viruses. If the mass of the Player or a Blob is bigger than a Virus when they collide with it,", c, fs);
		line7 = EZ.addText(align, line6.getYCenter() + verticalGap, "the Virus decreases their mass until the Virus and the Player or the Blob have the same mass if they keep colliding all the way through", c, fs);
	}
}
