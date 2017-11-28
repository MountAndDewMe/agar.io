import java.awt.Color;

public class Options {
	/** Screen Width */
	private final int SCREEN_WIDTH = Game.SCREEN_WIDTH;
	/** Screen Height */
	private final int SCREEN_HEIGHT = Game.SCREEN_HEIGHT;
	/** Half of the Screen Width */
	private final int HALF_SCREEN_WIDTH =  SCREEN_WIDTH / 2;
	/** Half of the Screen Height */
	private final int HALF_SCREEN_HEIGHT = SCREEN_HEIGHT / 2;

	private final int BORDER = 8;
	private Menu menu;
	public EZText defaultButton, backText;
	public EZRectangle backButton, backOutline;
	public EZRectangle optionsScreen, optionsScreenOutline;
	public EZText optionsScreenTitle;
	public EZText maxDots, maxBlobs, maxPlayerMass, maxBlobMass, dotMass;
	public EZImage up1, up2, up3, up4, up5, up6, down1, down2, down3, down4, down5, down6;
	public EZText maxDotsVal, maxBlobsVal, maxPlayerMassVal, maxBlobMassVal, dotMassVal;

	public Options(Menu menu) {
		this.menu = menu;
	}
	
	public void drawOptions() {
		
		//Options Screen
		optionsScreenOutline = EZ.addRectangle(HALF_SCREEN_WIDTH, HALF_SCREEN_HEIGHT, SCREEN_WIDTH - 800 + BORDER, SCREEN_HEIGHT - 150 + BORDER, new Color(0, 0, 0, 120), true);
		optionsScreen = EZ.addRectangle(HALF_SCREEN_WIDTH, HALF_SCREEN_HEIGHT, SCREEN_WIDTH - 800, SCREEN_HEIGHT - 150, new Color(Color.green.getRed(), Color.green.getGreen(), Color.green.getBlue(), 200), true);
		optionsScreenTitle = EZ.addText(optionsScreen.getXCenter(), optionsScreen.getYCenter() - 400, "Options", Color.black, 50);
		
		//Options EZTexts
		int verticalGap = 150; //Vertical gap in between each option
		int fs = 30; //Font size
		Color c = Color.black;
		maxDots = EZ.addText(optionsScreenTitle.getXCenter() - 300, optionsScreenTitle.getYCenter() + 100, "Maximum Dots", c, fs);
		maxBlobs = EZ.addText(maxDots.getXCenter(), maxDots.getYCenter() + verticalGap, "Maximum Blobs", c, fs);
		maxPlayerMass = EZ.addText(maxBlobs.getXCenter(), maxBlobs.getYCenter() + verticalGap, "Maximum Player Mass", c, fs);
		maxBlobMass = EZ.addText(maxPlayerMass.getXCenter(), maxPlayerMass.getYCenter() + verticalGap, "Maximum Blob Mass", c, fs);
		dotMass = EZ.addText(maxBlobMass.getXCenter(), maxBlobMass.getYCenter() + verticalGap, "Dot Mass", c, fs);
		

		//Options decrease/increase buttons
		int leftJustified = optionsScreenTitle.getXCenter() + 50; //Line up all the down buttons 
		int horizontalGap = 350; //Horizontal gap in between the down and up buttons
		down1 = EZ.addImage("resources/buttons/down1.png", leftJustified, optionsScreenTitle.getYCenter() + 100);
		up1 = EZ.addImage("resources/buttons/up1.png", leftJustified + horizontalGap, optionsScreenTitle.getYCenter() + 100);
		down2 = EZ.addImage("resources/buttons/down2.png", leftJustified, down1.getYCenter() + verticalGap);
		up2 = EZ.addImage("resources/buttons/up2.png", leftJustified + horizontalGap, up1.getYCenter() + verticalGap);
		down3 = EZ.addImage("resources/buttons/down2.png", leftJustified, down2.getYCenter() + verticalGap);
		up3 = EZ.addImage("resources/buttons/up2.png", leftJustified + horizontalGap, up2.getYCenter() + verticalGap);
		down4 = EZ.addImage("resources/buttons/down3.png", leftJustified, down3.getYCenter() + verticalGap);
		up4 = EZ.addImage("resources/buttons/up4.png", leftJustified + horizontalGap, up3.getYCenter() + verticalGap);
		down5 = EZ.addImage("resources/buttons/down5.png", leftJustified, down4.getYCenter() + verticalGap);
		up5 = EZ.addImage("resources/buttons/up5.png", leftJustified + horizontalGap, up4.getYCenter() + verticalGap);

		//Options EZText values 
		int between = (down1.getXCenter() + up1.getXCenter()) / 2;
		maxDotsVal = EZ.addText(between, down1.getYCenter(), Integer.toString(Dot.maxDots), c, fs);
		maxBlobsVal = EZ.addText(between, down2.getYCenter(), Integer.toString(Blob.maxBlobs), c, fs);
		maxPlayerMassVal = EZ.addText(between, down3.getYCenter(), Integer.toString(Player.maxMass), c, fs);
		maxBlobMassVal = EZ.addText(between, down4.getYCenter(), Integer.toString(Blob.maxMass), c, fs);
		dotMassVal = EZ.addText(between, down5.getYCenter(), Integer.toString(Dot.mass), c, fs);
		
		//Back Button
		backOutline = EZ.addRectangle(100, 100, 150 + BORDER, 100 + BORDER, Color.black, true);
		backButton = EZ.addRectangle(100, 100, 150, 100, Color.green, true);
		backText = EZ.addText(backButton.getXCenter(), backButton.getYCenter(), "Back", Color.black, 30);

		//Default Button
		defaultButton = EZ.addText(HALF_SCREEN_WIDTH + 400, HALF_SCREEN_HEIGHT - 400, "Default", Color.white, 30);

		interactOptions();
	}
	public void updateOptions() {
		maxDotsVal.setMsg(Integer.toString(Dot.maxDots));
		maxBlobsVal.setMsg(Integer.toString(Blob.maxBlobs));
		maxPlayerMassVal.setMsg(Integer.toString(Player.maxMass));
		maxBlobMassVal.setMsg(Integer.toString(Blob.maxMass));
		dotMassVal.setMsg(Integer.toString(Dot.mass));
	}

	public void interactOptions() {
		while(true) {
			EZ.refreshScreen();
			int x = EZInteraction.getXMouse();
			int y = EZInteraction.getYMouse();

			if(EZInteraction.wasMouseLeftButtonPressed()) { //Back Button
				if(backButton.isPointInElement(x, y)) {
					menu.removeReturnToMenu();
					menu.removeInitialPhaseBranchElements();
					menu.showInitialPhase();
					break;
				} else if(defaultButton.isPointInElement(x, y)) { //Default Button
					Dot.maxDots = 400;
					Blob.maxBlobs = 3;
					Player.maxMass = 350;
					Blob.maxMass = 350;
					Dot.mass = 2;
					updateOptions();
				} else if(down1.isPointInElement(x, y)) { //Decrease maxDots
					if(Dot.maxDots > 50) {
						Dot.maxDots -= 50;
						updateOptions();
					}
				} else if(up1.isPointInElement(x, y)) { //Increase maxDots
					if(Dot.maxDots != 600) {
						Dot.maxDots += 50;
						updateOptions();
					}
				} else if(down2.isPointInElement(x, y)) { //Decrease maxBlobs
					if(Blob.maxBlobs > 1) {
						Blob.maxBlobs -= 1;
						updateOptions();
					}
				} else if(up2.isPointInElement(x, y)) { //Increase maxBlobs
					if(Blob.maxBlobs != 5) {
						Blob.maxBlobs += 1;
						updateOptions();
					}
				} else if(down3.isPointInElement(x, y)) { //Decrease Player's maxMass
					if(Player.maxMass > 200) {
						Player.maxMass -= 50;
						updateOptions();
					}
				} else if(up3.isPointInElement(x, y)) { //Increase Player's maxMass
					if(Player.maxMass != 550) {
						Player.maxMass += 50;
						updateOptions();
					}
				} else if(down4.isPointInElement(x, y)) { //Decrease Blob's maxMass
					if(Blob.maxMass > 200) {
						Blob.maxMass -= 50;
						updateOptions();
					}
				} else if(up4.isPointInElement(x, y)) { //Increase Blob's maxMass
					if(Blob.maxMass!= 550) {
						Blob.maxMass += 50;
						updateOptions();
					}
				} else if(down5.isPointInElement(x, y)) { //Decrease Dot's mass
					if(Dot.mass > 1) {
						Dot.mass -= 1;
						updateOptions();
					}
				} else if(up5.isPointInElement(x, y)) { //Increase Dot's mass
					if(Dot.mass != 15) {
						Dot.mass += 1;
						updateOptions();
					}
				}
			}
		}
	}
}
