package logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.annotation.processing.Filer;

import character.Fireball;
import character.Hero;
import javafx.scene.input.KeyCode;
import main.ConfigurableOption;
import render.Background;
import scene.GameScreen;

public class GameLogic {
	
	private Ground ground = null;
	private int heroPositionX, heroPositionY; // position in logic block
	private int heroOnScreenX, heroOnScreenY;
	private int backgroundScreenX, backgroundScreenY; // background position show on screen
	private GameScreen gameScreen;
	private Hero hero;
	private Background bg;
	private Scanner fileRead;
	private int gravity = 2;
	
	public GameLogic(GameScreen gs) {
		new Fireball(100, 100);
		this.gameScreen = gs;
		this.hero = gs.getHero();
		//heroPositionX = hero.getX();
		//heroPositionY = hero.getY();
		this.bg = gs.background;
		try {
			fileRead = new Scanner(new File(gs.file));
			ground = new Ground(fileRead);
		} catch (FileNotFoundException e) {
			System.out.println("cant find file : " + e);
		} catch (NullPointerException e) {
			System.out.println("error load fiel : " + e);
		}
		try {
			heroPositionX = fileRead.nextInt();
			heroPositionY = fileRead.nextInt();
		} catch (Exception e) {
			System.out.println("file corrupt : " + e);
		}
		ground.createGround();
		backgroundScreenX = bg.getNearPositionX();
		backgroundScreenY = bg.getNearPositionY();
		heroOnScreenX = heroPositionX;
		heroOnScreenY = heroPositionY;
	}
	
	public void pressKey(KeyCode key) {
		//System.out.println(key);
		InputUtility.setKeyPress(key, true);
	}
	
	public void releaseKey(KeyCode key) {
		//System.out.println(key);
		InputUtility.setKeyPress(key, false);
	}
	
	public synchronized void updateLogic() {
		
		//########################## INPUT HANDLE ZONE #############################3
		//System.out.println(InputUtility.getPressed());
		if (InputUtility.isKeyPressed(KeyCode.D)) {
			hero.setDirection(Hero.FACE_RIGHT);
			if (canGoForward(heroPositionX, heroPositionY, hero.width, hero.height, hero.getDirection())) {
				heroPositionX += hero.speed*hero.getDirection();
				backgroundScreenX += hero.speed*hero.getDirection();
				if (bg.isBorder(backgroundScreenX, backgroundScreenY)) {
					heroOnScreenX += hero.speed*hero.getDirection();
					if (heroOnScreenX < 0) heroOnScreenX = 0;
					if (heroOnScreenX > ConfigurableOption.getInstance().getScreenWidth()-hero.width) 
						heroOnScreenX = ConfigurableOption.getInstance().getScreenWidth()-hero.width;
				}
			} else {
				while (!canGoForward(heroPositionX, heroPositionY, hero.width, hero.height, hero.getDirection())) {
					heroPositionX -= 1;
					//heroOnScreenX -= 1;
				}
				heroPositionX += 1;
				//heroOnScreenX += 1;
			}
			
			if (heroPositionX < 0) {heroPositionX = 0;}
			bg.setNearPosition(backgroundScreenX, backgroundScreenY);
			bg.setFarPosition(backgroundScreenX/2, backgroundScreenY);
			if (hero.getCurrentState() != hero.walkRight) {
				hero.setState(hero.walkRight);
			}
		}
		else if (InputUtility.isKeyPressed(KeyCode.A)) {
			hero.setDirection(Hero.FACE_LEFT);
			if (canGoForward(heroPositionX, heroPositionY, hero.width, hero.height, hero.getDirection())) {
				heroPositionX += hero.speed*hero.getDirection();
				backgroundScreenX += hero.speed*hero.getDirection();
				if (bg.isBorder(backgroundScreenX, backgroundScreenY)) {
					heroOnScreenX += hero.speed*hero.getDirection();
					if (heroOnScreenX < 0) heroOnScreenX = 0;
					if (heroOnScreenX > ConfigurableOption.getInstance().getScreenWidth()-hero.width) 
						heroOnScreenX = ConfigurableOption.getInstance().getScreenWidth()-hero.width;
				}
			} else {
				while (!canGoForward(heroPositionX, heroPositionY, hero.width, hero.height, hero.getDirection())) {
					heroPositionX += 1;
					//heroOnScreenX += 1;
				}
				heroPositionX -= 1;
				//heroOnScreenX -= 1;
			}
			if (heroPositionX < 0) {heroPositionX = 0;}
			bg.setNearPosition(backgroundScreenX, backgroundScreenY);
			bg.setFarPosition(backgroundScreenX/2, backgroundScreenY);
			if (hero.getCurrentState() != hero.walkLeft) {
				hero.setState(hero.walkLeft);
			}
		}
		else {
			if (hero.getCurrentState() != hero.idleLeft && hero.getCurrentState() != hero.idleRight) {
				if (hero.getDirection() == Hero.FACE_LEFT) hero.setState(hero.idleLeft);
				if (hero.getDirection() == Hero.FACE_RIGHT) hero.setState(hero.idleRight);
			}
		}
		
		if (InputUtility.isKeyTriggered(KeyCode.K)) {
			if (isTouchGround(heroPositionX, heroPositionY, hero.width, hero.height)) {
				hero.fall_speed = -hero.jumpStrength;
			}
		}
		
		//################# FALLING ZONE ##########################//
		
		try {
			if (!isTouchGround(heroPositionX, heroPositionY+hero.fall_speed, hero.width, hero.height)) {
				hero.fall_speed += gravity;
			} else {
				hero.fall_speed = 0;
				while (!isTouchGround(heroPositionX, heroPositionY, hero.width, hero.height)) {
					heroPositionY += 1;
					heroOnScreenY += 1;
				}
				/*while (isTouchGround(heroPositionX, heroPositionY-1, hero.width)) {
					heroPositionY -= 1;
					heroOnScreenY -= 1;
				}*/
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			if (heroPositionY < 0) {
				heroPositionY = 0;
				heroOnScreenY = 0;
				if (hero.fall_speed < 0) hero.fall_speed = 0;
			}
			if (heroPositionY > ground.getHeight()) {
				hero.hitted(10000);
			}
		}
		
		
		heroPositionY += hero.fall_speed;
		heroOnScreenY += hero.fall_speed;
		if (hero.getY() >= ConfigurableOption.getInstance().getScreenHeight())
			heroPositionY = ConfigurableOption.getInstance().getScreenHeight();
		
		//############## set hero position from calculation above ############
		hero.setPosition(heroOnScreenX, heroOnScreenY);
		
		//System.out.println(canGoForward(heroPositionX, heroPositionY, hero.width, hero.height, hero.getDirection()));
		//System.out.println("X : " + heroPositionX + "| Y : " + heroPositionY + "- screen x : " + heroOnScreenX + "| screen y : " + heroOnScreenY);
	}
	
	
	/**
	 * 
	 * @param x : current X position of character
	 * @param y : current Y position of character
	 * @param width : width of character
	 * @param height : height of character
	 * @return true if character is touching ground
	 */
	public boolean isTouchGround(int x, int y, int width, int height) {
		if (ground.getBlock()[x][y+height+1] == 1 || ground.getBlock()[x+width][y+height+1] == 1) return true;
		return false;
	}
	
	/**
	 * 
	 * @param x : x position of character
	 * @param y : y position of character
	 * @param width : width of character
	 * @param direction : direction character heading
	 * @return true if character can go forward
	 */
	public boolean canGoForward(int x, int y, int width, int height, int direction) {
		//System.out.println( x + width*((direction+1)/2) + direction);
		//System.out.println(x + width*((direction+1)/2) + direction < 0 || x + width*((direction+1)/2) + direction > ground.getWidth());
		if (x + width*((direction+1)/2) + direction < 0 || x + width*((direction+1)/2) + direction > ground.getWidth()) return false;
		if (ground.getBlock()[x + width*((direction+1)/2) + direction][y+height] == 0) return true;
		return false;
	}
	
	
}
