package project3;

import java.util.ArrayList;
import java.util.Random;

// Collin Wong Hostile Class
public class Hostile {
	private EZImage demon;
	private int x;
	private int y;
	private int speed;
	private int maxScreenX;
	private int maxScreenY;

	// constructor for hostiles
	public Hostile(String imageName, int maxX, int maxY) {
		demon = EZ.addImage(imageName, 0, 0);
		maxScreenX = maxX;
		maxScreenY = maxY;
		initialize();
	}

	// initializes a random number for the y position for the monster
	public void initialize() {
		Random randomGenerator = new Random();
		int mutiples = (int) maxScreenY / demon.getHeight();
		int ranY = randomGenerator.nextInt(mutiples) * demon.getHeight();
		setPosition(maxScreenX, ranY);
		int spd = randomGenerator.nextInt(4) + 1;
		speed = spd;
	}

	// member function to grab the x value
	public int getX() {
		return x;
	}

	// member function to grab the y value
	public int getY() {
		return y;
	}

	// member function to set the Hostile image position
	public void setHostilePicture(int posX, int posY) {
		demon.translateTo(posX, posY);
	}

	// member function to set the Hostile Position
	public void setPosition(int posX, int posY) {
		x = posX;
		y = posY;
		setHostilePicture(x, y);
	}

	// member function to move the demon
	public void move() {
		x = x - speed;
		setHostilePicture(x, y);
		// System.out.println(x + " " +y );
		if (x < 0) {
			initialize();
		}
	}

	
	public boolean touching(int posX, int posY) {
		return demon.isPointInElement(posX, posY);
	}
	// make a function of arrayList of bulletTouching
	//for loop to remove bullet
	public boolean bulletTouching(ArrayList<EZImage> bullets) {
		for (int i = 0; i < bullets.size(); i++) {
			if (demon.isPointInElement(bullets.get(i).getXCenter(), bullets.get(i).getYCenter())) {
				bullets.get(i).hide();
				bullets.remove(i);
				return true;
			}
		}
		return false;
	}
 //function that removes the demon and adds a blast image whenever it hits the demon
	public void hostileDead() {
		demon.hide();
		EZImage blast = EZ.addImage("blast.png", demon.getXCenter(), demon.getYCenter());
		boolean blastTime = false;
//		long blastDuration = System.currentTimeMillis();
//
//		while(System.currentTimeMillis() - blastDuration <= 1000) {
//			
//		}
//		blast.hide();

	}
}
