package project3;

import java.awt.Color;

// Collin Wong did the menu class
public class Menu {

	boolean gameStart = false;
	private EZImage menuBackground;
	private EZText chooseHero;
	private int maxScreenX;
	private int maxScreenY;
	private String characterCheck = "";
	private EZImage soldier76;
	private EZImage hanzo;
	private EZImage paraoh;

	// constructor for Menu
	public Menu(int posX, int posY) {
		// hero = EZ.addImage(imageName, 0, 0);
		maxScreenX = posX;
		maxScreenY = posY;
		// background = EZ.addImage(imageName, maxScreenX, maxScreenY);
		Color c = new Color(100, 150, 100);
		int fontsize = 150;
		// images for the menu Background
		menuBackground = EZ.addImage("menuBackground.jpg", maxScreenX / 2, maxScreenY / 2);
		menuBackground.scaleTo(3);
		chooseHero = EZ.addText(maxScreenX / 2, 70, "CHOOSE HERO", c, fontsize);
		soldier76 = EZ.addImage("S76.png", maxScreenX / 4, maxScreenY / 2);
		hanzo = EZ.addImage("hanzo.png", maxScreenX / 4 * 2, maxScreenY / 2);
		paraoh = EZ.addImage("paraoh.png", maxScreenX / 4 * 3, maxScreenY / 2);

	}
	// member function to start the Menu
	public void menuStart() {

		int clickX = 0;
		int clickY = 0;
		// while loop to choose which character you want to play
		// when you click on that character the game will start with that character
		while (gameStart == false) {
			clickX = EZInteraction.getXMouse();
			clickY = EZInteraction.getYMouse();
			System.out.println(EZInteraction.isMouseLeftButtonDown());
			// if you clicked agent21, you will play agent21
			if (EZInteraction.isMouseLeftButtonDown()) {
				if (soldier76.isPointInElement(clickX, clickY)) {
					characterCheck = "S76.png";
					gameStart = true;
				}
				// if you clicked agent 22, you will play agent 22
				if (hanzo.isPointInElement(clickX, clickY)) {
					characterCheck = "hanzo.png";
					gameStart = true;
				}
				// if you clicked genji, you will play genji
				if (paraoh.isPointInElement(clickX, clickY)) {
					characterCheck = "paraoh.png";
					gameStart = true;
				}
			}

		}

	}
	// member function to hide all the menu options
	// hides the menu background, characters
	public String menuClose() {
		menuBackground.hide();
		soldier76.hide();
		hanzo.hide();
		paraoh.hide();
		chooseHero.hide();
		return characterCheck;
	}


}
