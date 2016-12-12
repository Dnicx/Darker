package logic;

import character.Enemy;
import character.EnemyHolder;
import main.ConfigurableOption;
import main.Main;
import scene.GameScreen;

public class EnemyLogic extends Thread{

	private EnemyHolder enemys;
	private int gravity = 2;
	private GameLogic gameLogic;
	private int logicalX, logicalY, onScreenX, onScreenY;
	
	/**
	 * must be initiate before adding enemy
	 */
	public EnemyLogic(GameScreen gs) {
		enemys = EnemyHolder.getInstance();
		//enemys.clear();
		gameLogic = gs.getGameLogic();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (!gameLogic.isGameOver()) {
			for (Enemy e : EnemyHolder.getInstance().getEnemyPack()) {
				logicalX = e.getLogicalX();
				logicalY = e.getLogicalY();
				onScreenX = e.getOnScreenX();
				onScreenY = e.getOnScreenY();
				//System.out.println("x = " + logicalX + "| y = " + logicalY + "- screenX = " + onScreenX + "| screenY = " + onScreenY);
				try {
					if (!gameLogic.isTouchGround(logicalX, logicalY+e.fall_speed, e.width, e.height)) {
						e.fall_speed += gravity;
					} else {
						e.fall_speed = 0;
						while (!gameLogic.isTouchGround(logicalX, logicalY, e.width, e.height)) {
							logicalY += 1;
							onScreenY += 1;
							System.out.println("loop");
						}
					}
				} catch (ArrayIndexOutOfBoundsException exception) {
					if (logicalY < 0) {
						logicalY = 0;
						onScreenY = 0;
						if (e.fall_speed < 0) e.fall_speed = 0;
					}
					if (logicalY+e.height >= gameLogic.getGround().getHeight()) {
						e.hitted(10000);
					}
				}
				
				if (logicalY < 0) {
					logicalY = 0;
					onScreenY = 0;
					if (e.fall_speed < 0) e.fall_speed = 0;
				}
				if (logicalY >= gameLogic.getGround().getHeight()) {
					e.hitted(10000);
					//System.out.println("hitt");
				}
				
				logicalY += e.fall_speed;
				onScreenY += e.fall_speed;
				if (logicalY >= ConfigurableOption.getInstance().getScreenHeight())
					logicalY = ConfigurableOption.getInstance().getScreenHeight();
				e.setOnScreenPosition(onScreenX, onScreenY);
				e.setLogicalPosition(logicalX, logicalY);
			}
		}
	}
	
	
	
}
