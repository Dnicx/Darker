package logic;

import character.Enemy;
import character.EnemyHolder;
import character.Hero;
import main.ConfigurableOption;
import render.Animation;
import render.RenderableHolder;
import render.SequenceAnimation;
import scene.GameScreen;

public class EnemyLogic extends Thread {

	private int gravity = 2;
	private GameLogic gameLogic;
	private int logicalX, logicalY;

	/**
	 * (must be initiate before adding enemy)
	 * thread that update enemy pattern and status. For handle with enemy behavior without
	 * interfere with user input logic
	 */
	public EnemyLogic(GameScreen gs) {
		// enemys.clear();
		gameLogic = gs.getGameLogic();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (!gameLogic.isGameOver()) {
			try {
				Thread.sleep(33);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// System.out.println(enemys.getEnemyPack().size());
			for (int i = 0; i < EnemyHolder.getInstance().getEnemyPack().size(); i++) {

				Animation currentState;
				Enemy e = EnemyHolder.getInstance().getEnemyPack().get(i);
				if (!e.isAlive()) {
					e.setVisible(false);
					gameLogic.addScore(e.getScore());
					EnemyHolder.getInstance().remove(i);
					RenderableHolder.getInstance().remove(e);
					continue;
				}
				logicalX = e.getLogicalX();
				logicalY = e.getLogicalY();
				// onScreenX = e.getOnScreenX();
				// onScreenY = e.getOnScreenY();
				// System.out.println("x = " + logicalX + "| y = " + logicalY +
				// "- screenX = " + onScreenX + "| screenY = " + onScreenY);
				// System.out.println("x = " + logicalX + " y = " + logicalY);
				try {
					if (!gameLogic.isTouchingGround(logicalX, logicalY + e.fall_speed, e.getWidth(), e.getheight())) {
						e.fall_speed += gravity;
					} else {
						e.fall_speed = 0;
						while (!gameLogic.isTouchingGround(logicalX, logicalY, e.getWidth(), e.getheight())) {
							logicalY += 1;
							// onScreenY += 1;
						}
					}
				} catch (ArrayIndexOutOfBoundsException exception) {
					//
				}

				if (logicalY < 0) {
					logicalY = 0;
					// onScreenY = 0;
					if (e.fall_speed < 0)
						e.fall_speed = 0;
				}
				if (logicalY >= gameLogic.getGround().getHeight()) {
					e.hitted(10000);
				}

				logicalY += e.fall_speed;
				// onScreenY += e.fall_speed;
				if (logicalY >= ConfigurableOption.getInstance().getScreenHeight())
					logicalY = ConfigurableOption.getInstance().getScreenHeight();

				e.getCurrentPattern().patternUpdate();

				if (e.getCurrentPattern().isEnd()) {
					if (e.getCurrentPattern() != e.walkPattern)
						e.setCurrentPattern(e.walkPattern);
					e.getCurrentPattern().playPattern();
				}
				// if (e.getCurrentPattern().isEnd() && e.getCurrentPattern() !=
				// e.walkPattern) {
				// e.setCurrentPattern(e.walkPattern);
				// System.out.println("end");
				//// e.getCurrentPattern().playPattern();
				// }
				attempAttack(e);
				currentState = e.getCurrentPattern().getCurrentState();
				if (currentState == e.idleLeft) {
					// System.out.println("1");
					idleLeft(e);
				}
				if (currentState == e.idleRight) {
					// System.out.println("2");
					idleRight(e);
				}
				if (currentState == e.walkLeft) {
					// System.out.println("3");
					walkLeft(e);
				}
				if (currentState == e.walkRight) {
					// System.out.println("4");
					walkRight(e);
				}
				if (currentState == e.attackLeft) {
					// System.out.println("atl");
					attackLeft(e);
				}
				if (currentState == e.attackRight) {
					// System.out.println("atr");
					attackRight(e);
				}

				// System.out.println(e.getAttackRange());

				e.setOnScreenPosition(logicalX - gameLogic.getBackgroundX(), logicalY - gameLogic.getBackgroundY());
				e.setLogicalPosition(logicalX, logicalY);
			}
		}
	}

	/**
	 * if enemy e can go left, make it go left.
	 * @param e
	 */
	public void walkLeft(Enemy e) {
		if (e.getCurrentState() != e.walkLeft) {
			e.setState(e.walkLeft);
		}
		if (gameLogic.canGoForward(logicalX, logicalY, e.getWidth(), e.getheight(), Enemy.FACE_LEFT))
			logicalX -= e.speed;
	}
	/**
	 * if enemy e can go right, make it go right.
	 * @param e
	 */
	public void walkRight(Enemy e) {
		if (e.getCurrentState() != e.walkRight) {
			e.setState(e.walkRight);
		}
		if (gameLogic.canGoForward(logicalX, logicalY, e.getWidth(), e.getheight(), Enemy.FACE_RIGHT))
			logicalX += e.speed;
	}
	/**
	 * do nothing , facing left.
	 * @param e
	 */
	public void idleLeft(Enemy e) {
		if (e.getCurrentState() != e.idleLeft) {
			e.setState(e.idleLeft);
		}
	}
	/**
	 * do nothing, facing right.
	 * @param e
	 */
	public void idleRight(Enemy e) {
		if (e.getCurrentState() != e.idleRight) {
			e.setState(e.idleRight);
		}
	}

	/**
	 * check if player in e's range or not, if yes then it will start attack pattern
	 * @param e
	 */
	private void attempAttack(Enemy e) {
		// System.out.println("attack?");
		if (e.getLogicalX() - e.getAttackRange() < gameLogic.heroPositionX + Hero.width
				&& e.getLogicalX() + e.getWidth() > gameLogic.heroPositionX + Hero.width) {
			e.setCurrentPattern(e.attackLeftPattern);
			e.getCurrentPattern().playPattern();
			// System.out.println("attack!!!!!!!!!!");
		}
		if (e.getLogicalX() + e.getWidth() + e.getAttackRange() > gameLogic.heroPositionX
				&& e.getLogicalX() < gameLogic.heroPositionX) {
			e.setCurrentPattern(e.attackRightPattern);
			e.getCurrentPattern().playPattern();
			// System.out.println("!!!!!!!!!!!!!!!!!!!!!1attack");
		}
	}

	/**
	 * e attack to left and only do damage only one time.
	 * @param e
	 */
	public void attackLeft(Enemy e) {
		if (e.getCurrentState() != e.attackLeft) {
			e.setState(e.attackLeft);
		}
		if (((SequenceAnimation) e.getCurrentState()).isFinished()) {
			e.getCurrentPattern().skipState();
		}
		if (e.getCurrentState().getCurrentFrame() == e.getAtttackFrame() && e.getCurrentState().isFrameTriggered()) {
			doDamage(e);
		}
	}

	/**
	 * e attack to right and only do damage only one time.
	 * @param e
	 */
	public void attackRight(Enemy e) {
		if (e.getCurrentState() != e.attackRight) {
			e.setState(e.attackRight);
		}
		if (((SequenceAnimation) e.getCurrentState()).isFinished()) {
			e.getCurrentPattern().skipState();
		}
		if (e.getCurrentState().getCurrentFrame() == e.getAtttackFrame() && e.getCurrentState().isFrameTriggered()) {
			doDamage(e);
		}
	}

	/**
	 * if e hits hero, hero will get hurt.
	 * @param e
	 */
	private void doDamage(Enemy e) {
		Hero hero = gameLogic.hero;
		// System.out.println(hero.toString() + " w " + Hero.width + " h " +
		// Hero.height);
		// System.out.println(e.toString() + " w " + e.getWidth() + " h " +
		// e.getheight());
		if (GameLogic.isHit(
				new CollideBox(e.getLogicalX() - e.getAttackRange(), e.getLogicalY(),
						e.getLogicalX() + e.getWidth() + e.getAttackRange(), e.getLogicalY() + e.getheight()),
				new CollideBox(hero.getLogicalX(), hero.getlogicalY(), hero.getLogicalX() + Hero.width,
						hero.getlogicalY() + Hero.height))) {
			hero.hitted(e.damage);
			// System.out.println(hero.HP);
			// System.out.println("hitted");
		}
	}

}
