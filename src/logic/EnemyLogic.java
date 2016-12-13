package logic;

import character.Enemy;
import character.EnemyHolder;
import character.Fireball;
import main.ConfigurableOption;
import main.Main;
import render.RenderableHolder;
import scene.GameScreen;
import sun.misc.GC;

public class EnemyLogic extends Thread {

	private EnemyHolder enemys;
	private int gravity = 2;
	private GameLogic gameLogic;
	private int logicalX, logicalY, onScreenX, onScreenY;

	/**
	 * must be initiate before adding enemy
	 */
	public EnemyLogic(GameScreen gs) {
		enemys = EnemyHolder.getInstance();
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
			for (int i = 0; i < EnemyHolder.getInstance().getEnemyPack().size() ; i++) {
				
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
				//onScreenX = e.getOnScreenX();
				//onScreenY = e.getOnScreenY();
				// System.out.println("x = " + logicalX + "| y = " + logicalY +
				// "- screenX = " + onScreenX + "| screenY = " + onScreenY);
				//System.out.println("x = " + logicalX + " y = " + logicalY);
				try {
					if (!gameLogic.isTouchingGround(logicalX, logicalY + e.fall_speed, e.getWidth(), e.getheight())) {
						e.fall_speed += gravity;
					} else {
						e.fall_speed = 0;
						while (!gameLogic.isTouchingGround(logicalX, logicalY, e.getWidth(), e.getheight())) {
							logicalY += 1;
							onScreenY += 1;
							// System.out.println("loop");
						}
					}
				} catch (ArrayIndexOutOfBoundsException exception) {
					if (logicalY < 0) {
						logicalY = 0;
						onScreenY = 0;
						if (e.fall_speed < 0)
							e.fall_speed = 0;
					}
					if (logicalY + e.getheight() >= gameLogic.getGround().getHeight()) {
						e.hitted(10000);
					}
				}

				if (logicalY < 0) {
					logicalY = 0;
					onScreenY = 0;
					if (e.fall_speed < 0)
						e.fall_speed = 0;
				}
				if (logicalY >= gameLogic.getGround().getHeight()) {
					e.hitted(10000);
					// System.out.println("hitt");
				}

				logicalY += e.fall_speed;
				onScreenY += e.fall_speed;
				if (logicalY >= ConfigurableOption.getInstance().getScreenHeight())
					logicalY = ConfigurableOption.getInstance().getScreenHeight();
				e.setOnScreenPosition(logicalX - gameLogic.getBackgroundX(), logicalY - gameLogic.getBackgroundY());
				e.setLogicalPosition(logicalX, logicalY);
			}
		}
	}

}
