package logic;

import java.io.File;

import character.Hero;
import javafx.scene.input.KeyCode;
import render.Background;
import scene.GameScreen;

public class GameLogic {
	
	private Ground ground = null;
	private int heroPositionX, heroPositionY;
	private GameScreen gameScreen;
	private Hero hero;
	private Background bg;
	
	public GameLogic(GameScreen gs) {
		this.gameScreen = gs;
		this.hero = gs.getHero();
		this.bg = gs.background;
		try {
			ground = new Ground(new File(gs.file));
		} catch (NullPointerException e) {
			System.out.println("cant find file : " + e);
		}
		ground.createGround();
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
		if (InputUtility.isKeyPressed(KeyCode.RIGHT)) {
			//hero.setPosition(hero.getX() + hero.speed, hero.getY());
			bg.setNearPosition(bg.getNearPositionX() + hero.speed, bg.getNearPositionY());
			bg.setFarPosition(bg.getFarPositionX() + hero.speed/2, bg.getFarPositionY());
			if (InputUtility.isKeyTriggered(KeyCode.RIGHT)) {
				hero.setState(hero.walk);
				hero.setDirection(hero.FACE_RIGHT);
			}
		}
		else if (InputUtility.isKeyPressed(KeyCode.LEFT)) {
			//hero.setPosition(hero.getX() - hero.speed, hero.getY());
			bg.setNearPosition(bg.getNearPositionX() - hero.speed , bg.getNearPositionY());
			bg.setFarPosition(bg.getFarPositionX() - hero.speed/2, bg.getFarPositionY());
			if (InputUtility.isKeyTriggered(KeyCode.LEFT)) {
				hero.setState(hero.walk);
				hero.setDirection(hero.FACE_LEFT);
			}
		}
		else {
			if (hero.getCurrentState() != hero.idle) hero.setState(hero.idle);
		}
		
		
		
	}
	
}
