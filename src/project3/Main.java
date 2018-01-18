package project3;

import java.awt.Color;

// Collin Wong Main Class
public class Main {

	// static variables for the class
	static final int MAX_SCREEN_X = 1200;
	static final int MAX_SCREEN_Y = 650;
	static final int HERO_HEARTS = 4;
	static final int HOSTILE_HEARTS = 4;
	static final int MAX_HOSTILES = 10;
	static final int HERO_FIGHTING = 0;
	static final int HERO_DIE = 1;
	static final int HERO_DAMAGED = 0;
	static final int HERO_WIN = 2;
	static long immunityTime;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// initialize the screen
		EZ.initialize(MAX_SCREEN_X, MAX_SCREEN_Y);
		
		// calls the menu function from the menu class to start up the game with a mennu
		Menu menu = new Menu(MAX_SCREEN_X, MAX_SCREEN_Y);
		menu.menuStart();
		String heroImage = menu.menuClose();

		// variables for the hero
		int posX = 0;
		int posY = 0;
		boolean immunity = false;
		int heroState = HERO_FIGHTING;

		// sounds for the game
		EZSound backgroundMusic = EZ.addSound("backgroundMusic.wav");
		EZSound defeatSound = EZ.addSound("defeatSound.wav");
		EZSound victorySound = EZ.addSound("victorysound.wav");
		EZSound heroHurt = EZ.addSound("damaged.wav");

		// background image for the game
		EZImage road = EZ.addImage("road.png", MAX_SCREEN_X / 2, MAX_SCREEN_Y / 2);
		road.scaleTo(3.2);

		// set the hero as genji
		Hero myhero = new Hero(heroImage, 50, MAX_SCREEN_Y / 2);

		// making a certain number amount of bulbusaurs
		Hostile demon[] = new Hostile[MAX_HOSTILES];
		for (int i = 0; i < MAX_HOSTILES; i++) {
			demon[i] = new Hostile("drag.gif", MAX_SCREEN_X, MAX_SCREEN_Y);
		}

		// loops the background music
		backgroundMusic.loop();

		// a while loop for the main function of the game
		while (heroState == HERO_FIGHTING) {
			myhero.movementCommands();
			myhero.shoot(posX, posY, 0);
			myhero.updateBullets(MAX_SCREEN_X);

			// a for loop that spawns the monsters and allow them to move and
			// die if a bullet touches them
			for (int i = 0; i < MAX_HOSTILES; i++) {
				if (demon[i] != null && demon[i].bulletTouching(myhero.bullets)) {
					demon[i].hostileDead();
					demon[i] = null;
				}
				if (demon[i] == null) {
					continue;
				}
				// moves the demon
				demon[i].move();

				// a checker if the demon is touching the hero
				// if it touches the hero then the hero loses a heart
				if ((demon[i].touching(myhero.getX() - 10, myhero.getY() - 10))
						|| (demon[i].touching(myhero.getX() + 10, myhero.getY() - 10))
						|| (demon[i].touching(myhero.getX() - 10, myhero.getY() + 10))
						|| (demon[i].touching(myhero.getX() + 10, myhero.getY() + 10))) {

					// a 2 sec immunity for the hero if it gets hit by a monster
					// if the hero gets hit by a monster then it loses one heart
					if (immunity == false) {
						immunityTime = System.currentTimeMillis();
						myhero.heartCount();
						heroHurt.play();
						immunity = true;
						// when the 2 secs are up, the hero has no more immunity
					} else {
						if (System.currentTimeMillis() - immunityTime >= 2000) {
							immunity = false;
						}
						// if the hero's heart count reaches to 0, the hero dies
						if (myhero.liveCount == 0) {
							heroState = HERO_DIE;
							defeatSound.play();
						}
					}
				}

			}
			// a victory sound when the hero gets to the other side of the
			// screen
			if (myhero.getX() > MAX_SCREEN_X - 50) {
				heroState = HERO_WIN;
				victorySound.play();

			}
			
			// Messages that appear when the hero dies or loses
			if (heroState == HERO_DIE) {
				Color c = new Color(200, 50, 150);
				int fontsize = 100;
				EZText defeat = EZ.addText(MAX_SCREEN_X / 2, MAX_SCREEN_Y / 2, "GAME OVER", c, fontsize);
				defeat.setFont("Xephyr Italic.ttf");
			}
			if (heroState == HERO_WIN) {
				Color c = new Color(100, 150, 100);
				int fontsize = 100;
				EZText win = EZ.addText(MAX_SCREEN_X / 2, MAX_SCREEN_Y / 2, "You're Decent...", c, fontsize);
				win.setFont("Xephyr Italic.ttf");
				//menu.restartGame();
				
				
			}
			EZ.refreshScreen();
		}
		
	}
}
