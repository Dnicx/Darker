package logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.annotation.processing.Filer;

import character.Enemy;
import character.EnemyHolder;
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
	private int backgroundScreenX, backgroundScreenY; // background position
														// show on screen
	// private GameScreen gameScreen;
	private Hero hero;
	private Background bg;
	private Scanner fileRead;
	private int gravity = 2;
	private boolean gameOver;

	public int heroStartX = 450;
	public int heroStartY = 0;
	
	public int score;

	public GameLogic(GameScreen gs) {
		// this.gameScreen = gs;
		// this.hero = new Hero(heroStartX, heroStartY);
		score = 0;
		gameOver = false;
		new Fireball(510, 0);
		new Fireball(500, 0);
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
		this.hero = new Hero(heroPositionX, heroPositionY);
	}

	public void pressKey(KeyCode key) {
		// System.out.println(key);
		InputUtility.setKeyPress(key, true);
	}

	public void releaseKey(KeyCode key) {
		// System.out.println(key);
		InputUtility.setKeyPress(key, false);
	}

	public boolean isHeroAlive() {
		return hero.isAlive();
	}

	public void setGameOver() {
		gameOver = true;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public Ground getGround() {
		return ground;
	}

	public int getBackgroundX() {
		return backgroundScreenX;
	}

	public int getBackgroundY() {
		return backgroundScreenY;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public synchronized void addScore(int score) {
		this.score += score;
	}

	/**
	 * 
	 * @param c1
	 *            : collideBox of attacker
	 * @param c2
	 *            : collideBox of damage taker
	 * @return true if the attack hits
	 */
	public static boolean hit(CollideBox c1, CollideBox c2) {
		//System.out.println("top x = : " + c1.getLeft() + "top y = : " + c1.getTop());
		return c1.isCollide(c2);
	}

	/**
	 * 
	 * @param x
	 *            : current X position of character
	 * @param y
	 *            : current Y position of character
	 * @param width
	 *            : width of character
	 * @param height
	 *            : height of character
	 * @return true if character is touching ground
	 */
	public boolean isTouchingGround(int x, int y, int width, int height) {
		if (ground.getBlock()[x][y + height + 1] == 1 || ground.getBlock()[x + width][y + height + 1] == 1)
			return true;
		return false;
	}

	/**
	 * 
	 * @param x
	 *            : x position of character
	 * @param y
	 *            : y position of character
	 * @param width
	 *            : width of character
	 * @param direction
	 *            : direction character heading
	 * @return true if character can go forward
	 */
	public boolean canGoForward(int x, int y, int width, int height, int direction) {
		// System.out.println( x + width*((direction+1)/2) + direction);
		// System.out.println(x + width*((direction+1)/2) + direction < 0 || x +
		// width*((direction+1)/2) + direction > ground.getWidth());
		if (x + width * ((direction + 1) / 2) + direction < 0
				|| x + width * ((direction + 1) / 2) + direction > ground.getWidth())
			return false;
		if (ground.getBlock()[x + width * ((direction + 1) / 2) + direction][y + height - 1] == 0)
			return true;
		return false;
	}

	/**
	 * handle player's logic such as move, jump, attack etc.
	 */
	public synchronized void updateLogic() {
		// ########################## INPUT HANDLE ZONE
		// #############################3
		// System.out.println(InputUtility.getPressed());
		if (!hero.isAttacking()) {
			if (InputUtility.isKeyPressed(KeyCode.D)) {
				heroWalkRight();
			} else if (InputUtility.isKeyPressed(KeyCode.A)) {
				heroWalkLeft();
			} else {
				if (hero.getCurrentState() != hero.idleLeft && hero.getCurrentState() != hero.idleRight) {
					if (hero.getDirection() == Hero.FACE_LEFT)
						hero.setState(hero.idleLeft);
					if (hero.getDirection() == Hero.FACE_RIGHT)
						hero.setState(hero.idleRight);
				}
			}
		} else {
			
		}

		if (InputUtility.isKeyTriggered(KeyCode.K)) {
			if (isTouchingGround(heroPositionX, heroPositionY, hero.width, hero.height)) {
				hero.fall_speed = -hero.jumpStrength;
			}
		}

		if (InputUtility.isKeyTriggered(KeyCode.J)) {
			if (hero.getDirection() == Hero.FACE_RIGHT) {
				heroAttackRight(3);
			}
			if (hero.getDirection() == Hero.FACE_LEFT) {
				heroAttackLeft(3);
			}
		}

		// ################# FALLING ZONE ##########################//

		try {
			if (!isTouchingGround(heroPositionX, heroPositionY + hero.fall_speed, hero.width, hero.height)) {
				hero.fall_speed += gravity;
			} else {
				hero.fall_speed = 0;
				while (!isTouchingGround(heroPositionX, heroPositionY, hero.width, hero.height)) {
					heroPositionY += 1;
					heroOnScreenY += 1;
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			if (heroPositionY < 0) {
				heroPositionY = 0;
				heroOnScreenY = 0;
				if (hero.fall_speed < 0)
					hero.fall_speed = 0;
			}
			if (heroPositionY + hero.height >= ground.getHeight()) {
				hero.hitted(10000);
			}
		}

		if (heroPositionY < 0) {
			heroPositionY = 0;
			heroOnScreenY = 0;
			if (hero.fall_speed < 0)
				hero.fall_speed = 0;
		}
		if (heroPositionY >= ground.getHeight()) {
			hero.hitted(10000);
			// System.out.println("hitt");
		}

		heroPositionY += hero.fall_speed;
		heroOnScreenY += hero.fall_speed;
		if (hero.getY() >= ConfigurableOption.getInstance().getScreenHeight())
			heroPositionY = ConfigurableOption.getInstance().getScreenHeight();

		// ############## set hero position from calculation above ############
		hero.setPosition(heroOnScreenX, heroOnScreenY);

		// System.out.println(canGoForward(heroPositionX, heroPositionY,
		// hero.width, hero.height, hero.getDirection()));
		// System.out.println("X : " + heroPositionX + "| Y : " + heroPositionY
		// + "- screen x : " + heroOnScreenX + "| screen y : " + heroOnScreenY);

		if (!isHeroAlive()) {
			setGameOver();
		}

	}

	public void heroAttackRight(int delayFrame) {
		if (hero.getCurrentState() != hero.attackRight) {
			hero.setState(hero.attackRight);
		}
		for (Enemy e : EnemyHolder.getInstance().getEnemyPack()) {
			if (hit(new CollideBox(heroPositionX + hero.width, heroPositionY,heroPositionX + hero.width + 40, heroPositionY + hero.height), 
					new CollideBox(e.getLogicalX(), e.getLogicalY(), e.getLogicalX() + e.getWidth(), e.getLogicalY() + e.getheight()))) {
				e.hitted(hero.damage);
			}
		}
	}

	public void heroAttackLeft(int delayFrame) {
		if (hero.getCurrentState() != hero.attackLeft) {
			hero.setState(hero.attackLeft);
		}
		for (Enemy e : EnemyHolder.getInstance().getEnemyPack()) {
			if (hit(new CollideBox(heroPositionX - 40, heroPositionY,heroPositionX, heroPositionY + hero.height), 
					new CollideBox(e.getLogicalX(), e.getLogicalY(), e.getLogicalX() + e.getWidth(), e.getLogicalY() + e.getheight()))) {
				e.hitted(hero.damage);
			}
		}
	}

	/**
	 * move hero character to right with walking right-direction animation if
	 * cannot go forward the remain at same position but animate walking
	 * animation
	 */
	public void heroWalkRight() {
		hero.setDirection(Hero.FACE_RIGHT);
		if (canGoForward(heroPositionX, heroPositionY, hero.width, hero.height, hero.getDirection())) {
			heroPositionX += hero.speed * hero.getDirection();
			backgroundScreenX += hero.speed * hero.getDirection();
			if (bg.isBorder(backgroundScreenX, backgroundScreenY)) {
				heroOnScreenX += hero.speed * hero.getDirection();
				if (heroOnScreenX < 0)
					heroOnScreenX = 0;
				if (heroOnScreenX > ConfigurableOption.getInstance().getScreenWidth() - hero.width)
					heroOnScreenX = ConfigurableOption.getInstance().getScreenWidth() - hero.width;
			}
		} else {
			while (!canGoForward(heroPositionX, heroPositionY, hero.width, hero.height, hero.getDirection())) {
				heroPositionX -= 1;
				// heroOnScreenX -= 1;
			}
			heroPositionX += 1;
			// heroOnScreenX += 1;
		}

		if (heroPositionX < 0) {
			heroPositionX = 0;
		}
		bg.setNearPosition(backgroundScreenX, backgroundScreenY);
		bg.setFarPosition(backgroundScreenX / 2, backgroundScreenY);
		if (hero.getCurrentState() != hero.walkRight) {
			hero.setState(hero.walkRight);
		}
	}

	/**
	 * move hero character to left with walking left-direction animation if
	 * cannot go forward the remain at same position but animate walking
	 * animation
	 */
	public void heroWalkLeft() {
		hero.setDirection(Hero.FACE_LEFT);
		if (canGoForward(heroPositionX, heroPositionY, hero.width, hero.height, hero.getDirection())) {
			heroPositionX += hero.speed * hero.getDirection();
			backgroundScreenX += hero.speed * hero.getDirection();
			if (bg.isBorder(backgroundScreenX, backgroundScreenY)) {
				heroOnScreenX += hero.speed * hero.getDirection();
				if (heroOnScreenX < 0)
					heroOnScreenX = 0;
				if (heroOnScreenX > ConfigurableOption.getInstance().getScreenWidth() - hero.width)
					heroOnScreenX = ConfigurableOption.getInstance().getScreenWidth() - hero.width;
			}
		} else {
			while (!canGoForward(heroPositionX, heroPositionY, hero.width, hero.height, hero.getDirection())) {
				heroPositionX += 1;
				// heroOnScreenX += 1;
			}
			heroPositionX -= 1;
			// heroOnScreenX -= 1;
		}
		if (heroPositionX < 0) {
			heroPositionX = 0;
		}
		bg.setNearPosition(backgroundScreenX, backgroundScreenY);
		bg.setFarPosition(backgroundScreenX / 2, backgroundScreenY);
		if (hero.getCurrentState() != hero.walkLeft) {
			hero.setState(hero.walkLeft);
		}
	}

}
