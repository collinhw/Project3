package project3;

import java.util.ArrayList;

public class Hero {

	//JinHao Lin did the Hero class
	//member variables
	EZImage hero;
	EZImage[] heart;
	EZImage heroShoot;
	String heartImage;
	EZImage portal = EZ.addImage("portal.png", 1200, 500);
	ArrayList<EZImage> bullets;
	int x;
	int y;
	int liveCount;
	EZSound bulletSound = EZ.addSound("bulletSound.wav");
	
	//constructor
	public Hero(String imageName, int posX, int posY) {
		hero = EZ.addImage(imageName, posX, posY);
		x = posX;
		y = posY;
		liveCount = 4;
		heartImage = "heart.png";
		heart = new EZImage[4];
		heart[0] = EZ.addImage(heartImage, 50, 50);
		heart[1] = EZ.addImage(heartImage, 200, 50);
		heart[2] = EZ.addImage(heartImage, 350, 50);
		heart[3] = EZ.addImage(heartImage, 500, 50);
		for (int i = 0; i < 4; i++) {
			heart[i].scaleTo(0.3);
		}
		//Arraylist for bullets
		bullets = new ArrayList<EZImage>();
	}
	//getting the x and y function of the hero
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	//Set the hero position
	public void setPosition(int posX, int posY) {
		x = posX;
		y = posY;
		setHeroPosition(x, y);
	}

	public void setHeroPosition(int posX, int posY) {
		hero.translateTo(posX, posY);
	}

	//Sets the movements of the hero
	public void moveLeft(int step) {
		x = x - step;
		setHeroPosition(x, y);
	}

	public void moveRight(int step) {
		x = x + step;
		setHeroPosition(x, y);
	}

	public void moveUp(int step) {
		y = y - step;
		setHeroPosition(x, y);
	}

	public void moveDown(int step) {
		y = y + step;
		setHeroPosition(x, y);
	}

	// movement keys that allows hero to move 
	public void movementCommands() {
		if (EZInteraction.isKeyDown('w')) {
			moveUp(2);
		}
		if (EZInteraction.isKeyDown('a')) {
			moveLeft(2);
		}
		if (EZInteraction.isKeyDown('d')) {
			moveRight(2);
		}
		if (EZInteraction.isKeyDown('s')) {
			moveDown(2);
		}
	}
	// shoot function to get the X and Y position of hero for the bullet to come
	public void shoot(int x, int y, int maxScreenLong) {
		int posX = hero.getXCenter();
		int posY = hero.getYCenter();
		int duration = 0;
	
		
		// if key pressed was p
		//add bullets picture at point
		//get bullet size and scale it
		if (EZInteraction.wasKeyPressed('p')) {
			bullets.add(EZ.addImage("bullet.png", posX+100, posY));
			// monster.isPointInElement(posX, posY)
			bulletSound.play();
			bullets.get(bullets.size() - 1).scaleTo(0.4);
		}
	}
	
	// member function for updateBullets
	// get back bullet updates
	//if the bullet is empty then it is false
	// bullet moves forward
	public void updateBullets(int maxX) {
		if (bullets.isEmpty() == false) {
			for (int i = 0; i < bullets.size(); i++) {
				bullets.get(i).moveForward(4);
				if(bullets.get(i).getXCenter() > maxX){
					bullets.get(i).hide();
					bullets.remove(i);
				}
			}
		}
	}
	// function that hide hearts whenever being hit by demon
	public void heartCount(){
		heart[liveCount-1].hide();
		if(liveCount > 0){
			liveCount--;
		
		}
	}
	
	
	}

