package logic;

import java.util.Scanner;

import character.Enemy;
import character.EnemyHolder;
import character.Hero;
import javafx.scene.input.KeyCode;
import main.ConfigurableOption;
import main.Main;
import render.AudioUtility;
import render.Background;
import render.SequenceAnimation;
import scene.GameScreen;

public class GameLogic {

	private Ground ground = null;
	protected int heroPositionX, heroPositionY; // position in logic block
	protected int heroOnScreenX, heroOnScreenY;
	private int backgroundScreenX, backgroundScreenY; // background position
														// show on screen
	// private GameScreen gameScreen;
	protected Hero hero;
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
		EnemyHolder.getInstance().getEnemyPack().clear();
		try {
			EnemyHolder.getInstance().loadMob();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		this.bg = gs.background;
		this.fileRead = gs.fileRead;
		try {
			ground = new Ground(fileRead);
		} catch (NullPointerException e) {
			System.out.println("error load fiel : " + e);
		}
		try {
			heroPositionX = fileRead.nextInt();
			heroPositionY = fileRead.nextInt();
			if (fileRead.hasNext()) {
				this.hero = new Hero(heroPositionX, heroPositionY, fileRead.nextInt(), fileRead.nextInt(), fileRead.nextInt());
			} else {
				this.hero = new Hero(heroPositionX, heroPositionY);
			}
		} catch (Exception e) {
			System.out.println("file corrupt : " + e);
		}
		ground.createGround();
		AudioUtility.getinstance().loadResource();
		backgroundScreenX = bg.getNearPositionX();
		backgroundScreenY = bg.getNearPositionY();
		heroOnScreenX = heroPositionX;
		heroOnScreenY = heroPositionY;
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

	public int getHeroHealth() {
		return hero.HP;
	}

	public int getHeroFullHealth() {
		return hero.fullHP;
	}

	/**
	 * 
	 * @param c1
	 *            : collideBox of attacker
	 * @param c2
	 *            : collideBox of damage taker
	 * @return true if the attack hits
	 */
	public static boolean isHit(CollideBox c1, CollideBox c2) {
		// System.out.println("top x = : " + c1.getLeft() + "top y = : " +
		// c1.getTop());
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
	 * handle player's logic such as move, jump, attack etc. player's input will
	 * be process here
	 */
	public synchronized void updateLogic() {
		// ########################## INPUT HANDLE ZONE
		// #############################
		// System.out.println(InputUtility.getPressed());

		if (hero.getCurrentState() instanceof SequenceAnimation
				&& !((SequenceAnimation) hero.getCurrentState()).isFinished()) {
			heroAttack(4);
		} else {
			if (InputUtility.isKeyTriggered(KeyCode.K)) {
				if (isTouchingGround(heroPositionX, heroPositionY, Hero.width, Hero.height)) {
					hero.fall_speed = -hero.jumpStrength;
				}
			} else if (InputUtility.isKeyTriggered(KeyCode.J)
					&& isTouchingGround(heroPositionX, heroPositionY, Hero.width, Hero.height)) {
				if (hero.getDirection() == Hero.FACE_RIGHT) {
					heroAttackRight(4);
				}
				if (hero.getDirection() == Hero.FACE_LEFT) {
					heroAttackLeft(4);
				}
			} else if (InputUtility.isKeyPressed(KeyCode.D)) {
				heroWalkRight();
			} else if (InputUtility.isKeyPressed(KeyCode.A)) {
				heroWalkLeft();
			} else {
				if (hero.getCurrentState() != hero.idleLeft && hero.getCurrentState() != hero.idleRight
						&& hero.getCurrentState() != hero.fallLeft && hero.getCurrentState() != hero.fallRight) {
					if (hero.getDirection() == Hero.FACE_LEFT)
						hero.setState(hero.idleLeft);
					if (hero.getDirection() == Hero.FACE_RIGHT)
						hero.setState(hero.idleRight);
				}
			}
		}

		// System.out.println(isTouchingGround(heroOnScreenX, heroPositionY,
		// hero.width, hero.height));

		// ################# FALLING ZONE ##########################//

		try {
			if (!isTouchingGround(heroPositionX, heroPositionY + hero.fall_speed, Hero.width, Hero.height)) {
				hero.fall_speed += gravity;
				heroFall();
			} else {
				if (hero.getCurrentState() == hero.fallLeft || hero.getCurrentState() == hero.fallRight) {
					if (hero.getDirection() == Hero.FACE_LEFT)
						hero.setState(hero.idleLeft);
					else
						hero.setState(hero.idleRight);
				}
				hero.fall_speed = 0;
				while (!isTouchingGround(heroPositionX, heroPositionY, Hero.width, Hero.height)) {
					heroPositionY += 1;
					heroOnScreenY += 1;
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("out of bound");
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
		if (hero.getlogicalY() >= ConfigurableOption.getInstance().getScreenHeight())
			heroPositionY = ConfigurableOption.getInstance().getScreenHeight();

		// ############## set hero position from calculation above ############
		hero.setOnScreenPosition(heroOnScreenX, heroOnScreenY);
		hero.setLogicalPosition(heroPositionX, heroPositionY);

		// System.out.println(canGoForward(heroPositionX, heroPositionY,
		// hero.width, hero.height, hero.getDirection()));
		// System.out.println("X : " + heroPositionX + "| Y : " + heroPositionY
		// + "- hs x : " + heroOnScreenX + "| hs y : " + heroOnScreenY + "-
		// screenx : " + backgroundScreenX +
		// "| screeny : " + backgroundScreenY);

		if (!isHeroAlive()) {
			setGameOver();
		}
		if (EnemyHolder.getInstance().getEnemyPack().isEmpty() && isHeroAlive()) {
			Main.instance.toggleScene(Main.win);
		}

	}

	public void heroFall() {
		if (hero.getCurrentState() != hero.fallRight && hero.getDirection() == Hero.FACE_RIGHT) {
			hero.setState(hero.fallRight);
		}
		if (hero.getCurrentState() != hero.fallLeft && hero.getDirection() == Hero.FACE_LEFT) {
			hero.setState(hero.fallLeft);
		}
	}

	public void heroAttackRight(int damageFrame) {
		boolean missed = true;
		if (hero.getCurrentState() != hero.attackRight) {
			hero.setState(hero.attackRight);
		}
		if (damageFrame != hero.getCurrentState().getCurrentFrame()) {
			return;
		}
		if (hero.getCurrentState().isFrameTriggered()) {
			// System.out.println(missed);
			for (Enemy e : EnemyHolder.getInstance().getEnemyPack()) {
				if (isHit(
						new CollideBox(heroPositionX + Hero.width, heroPositionY,
								heroPositionX + Hero.width + Hero.attackRange, heroPositionY + Hero.height),
						new CollideBox(e.getLogicalX(), e.getLogicalY(), e.getLogicalX() + e.getWidth(),
								e.getLogicalY() + e.getheight()))) {
					// System.out.println("hit");
					e.hitted(hero.damage);
					AudioUtility.playSound(AudioUtility.swordSlashSound);
					missed = false;
				}
			}
			if (missed)
				AudioUtility.playSound(AudioUtility.swordSlashWindSound);
		}
	}

	public void heroAttackLeft(int damageFrame) {
		boolean missed = true;
		if (hero.getCurrentState() != hero.attackLeft) {
			hero.setState(hero.attackLeft);
		}
		if (damageFrame != hero.getCurrentState().getCurrentFrame()) {
			return;
		}
		if (hero.getCurrentState().isFrameTriggered()) {
			// System.out.println(missed);
			for (Enemy e : EnemyHolder.getInstance().getEnemyPack()) {
				if (isHit(
						new CollideBox(heroPositionX - Hero.attackRange, heroPositionY, heroPositionX,
								heroPositionY + Hero.height),
						new CollideBox(e.getLogicalX(), e.getLogicalY(), e.getLogicalX() + e.getWidth(),
								e.getLogicalY() + e.getheight()))) {
					e.hitted(hero.damage);
					AudioUtility.playSound(AudioUtility.swordSlashSound);
					missed = false;
				}
			}
			if (missed)
				AudioUtility.playSound(AudioUtility.swordSlashWindSound);
		}
	}

	public void heroAttack(int damageFrame) {
		if (hero.getDirection() == Hero.FACE_RIGHT) {
			heroAttackRight(damageFrame);
		}
		if (hero.getDirection() == Hero.FACE_LEFT) {
			heroAttackLeft(damageFrame);
		}
	}

	/**
	 * move hero character to right with walking right-direction animation if
	 * cannot go forward the remain at same position but animate walking
	 * animation
	 */
	public void heroWalkRight() {
		hero.setDirection(Hero.FACE_RIGHT);
		if (canGoForward(heroPositionX, heroPositionY, Hero.width, Hero.height, hero.getDirection())) {
			heroPositionX += hero.speed * hero.getDirection();
			if (heroPositionX > ConfigurableOption.getInstance().getScreenWidth() / 2
					&& heroPositionX < bg.getWidth() - (ConfigurableOption.getInstance().getScreenWidth() / 2)) {
				// backgroundScreenX += hero.speed * hero.getDirection();
				backgroundScreenX = heroPositionX - ConfigurableOption.getInstance().getScreenWidth() / 2;
			} else {
				if (heroPositionX < ConfigurableOption.getInstance().getScreenWidth() / 2)
					backgroundScreenX = 0;
				if (heroPositionX > bg.getWidth() - (ConfigurableOption.getInstance().getScreenWidth() / 2))
					backgroundScreenX = (int) (bg.getWidth() - ConfigurableOption.getInstance().getScreenWidth());
			}
			if (bg.isBorderX(backgroundScreenX)) {
				// System.out.println("rrr");
				if (backgroundScreenX == 0)
					heroOnScreenX = heroPositionX;
				if (backgroundScreenX == (int) (bg.getWidth() - ConfigurableOption.getInstance().getScreenWidth()))
					heroOnScreenX = heroPositionX
							- (ground.getWidth() - ConfigurableOption.getInstance().getScreenWidth());
				// heroOnScreenX += hero.speed * hero.getDirection();
				//
				// if (heroOnScreenX < 0)
				// heroOnScreenX = 0;
				// if (heroOnScreenX >
				// ConfigurableOption.getInstance().getScreenWidth() -
				// hero.width)
				// heroOnScreenX =
				// ConfigurableOption.getInstance().getScreenWidth() -
				// hero.width;
			} else {
				heroOnScreenX = ConfigurableOption.getInstance().getScreenWidth() / 2;
			}

		} // else {
		while (!canGoForward(heroPositionX, heroPositionY, Hero.width, Hero.height, hero.getDirection())) {
			heroPositionX -= 1;
			// heroOnScreenX -= 1;
		}
		heroPositionX += 1;
		// heroOnScreenX += 1;
		// }

		if (heroPositionX < 0) {
			heroPositionX = 0;
		}
		bg.setNearPosition(backgroundScreenX, backgroundScreenY);
		bg.setFarPosition(backgroundScreenX / 2, backgroundScreenY);
		if (hero.getCurrentState() != hero.walkRight && hero.getCurrentState() != hero.fallLeft
				&& hero.getCurrentState() != hero.fallRight) {
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
		if (canGoForward(heroPositionX, heroPositionY, Hero.width, Hero.height, hero.getDirection())) {
			heroPositionX += hero.speed * hero.getDirection();
			if (heroPositionX > ConfigurableOption.getInstance().getScreenWidth() / 2
					&& heroPositionX < bg.getWidth() - (ConfigurableOption.getInstance().getScreenWidth() / 2)) {
				// backgroundScreenX += hero.speed * hero.getDirection();
				backgroundScreenX = heroPositionX - ConfigurableOption.getInstance().getScreenWidth() / 2;
			} else {
				if (heroPositionX < ConfigurableOption.getInstance().getScreenWidth() / 2)
					backgroundScreenX = 0;
				if (heroPositionX > bg.getWidth() - (ConfigurableOption.getInstance().getScreenWidth() / 2))
					backgroundScreenX = (int) (bg.getWidth() - ConfigurableOption.getInstance().getScreenWidth());
			}
			if (bg.isBorderX(backgroundScreenX)) {
				// System.out.println("lllllllllllll");
				if (backgroundScreenX == 0)
					heroOnScreenX = heroPositionX;
				if (backgroundScreenX == (int) (bg.getWidth() - ConfigurableOption.getInstance().getScreenWidth()))
					heroOnScreenX = heroPositionX
							- (ground.getWidth() - ConfigurableOption.getInstance().getScreenWidth());
				// heroOnScreenX += hero.speed * hero.getDirection();
				//
				// if (heroOnScreenX < 0)
				// heroOnScreenX = 0;
				// if (heroOnScreenX >
				// ConfigurableOption.getInstance().getScreenWidth() -
				// hero.width)
				// heroOnScreenX =
				// ConfigurableOption.getInstance().getScreenWidth() -
				// hero.width;
			} else {
				heroOnScreenX = ConfigurableOption.getInstance().getScreenWidth() / 2;
			}

		} // else {
		while (!canGoForward(heroPositionX, heroPositionY, Hero.width, Hero.height, hero.getDirection())) {
			heroPositionX += 1;
			// heroOnScreenX += 1;
		}
		heroPositionX -= 1;
		// heroOnScreenX -= 1;
		// }
		if (heroPositionX < 0) {
			heroPositionX = 0;
		}
		bg.setNearPosition(backgroundScreenX, backgroundScreenY);
		bg.setFarPosition(backgroundScreenX / 2, backgroundScreenY);
		if (hero.getCurrentState() != hero.walkLeft && hero.getCurrentState() != hero.fallLeft
				&& hero.getCurrentState() != hero.fallRight) {
			hero.setState(hero.walkLeft);
		}
	}

	public String getHeroName() {
		return hero.heroName;
	}

}
