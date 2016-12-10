package logic;

import character.Hero;
import javafx.scene.input.KeyCode;
import scene.GameScreen;

public class GameLogic {
	
	private Ground ground = null;
	private static final GameLogic instance = new GameLogic();
	
	
	public GameLogic() {
		
	}
	
	public static GameLogic getInstance() {
		return instance;
	}
	
	public void pressKey(KeyCode key) {
		//System.out.println(key);
		InputUtility.setKeyPress(key, true);
	}
	
	public void releaseKey(KeyCode key) {
		//System.out.println(key);
		InputUtility.setKeyPress(key, false);
	}
	
	public void updateLogic(GameScreen gameScreen) {
		Hero hero = gameScreen.getHero();
		if (InputUtility.isKeyPressed(KeyCode.RIGHT)) {
			hero.setPosition(hero.getX() + hero.speed, hero.getY());
			if (InputUtility.isKeyTriggered(KeyCode.RIGHT))
				hero.setState(hero.walk);
		}
		else if (InputUtility.isKeyPressed(KeyCode.LEFT)) {
			hero.setPosition(hero.getX() - hero.speed, hero.getY());
			if (InputUtility.isKeyTriggered(KeyCode.LEFT))
				hero.setState(hero.walk);
		}
		else {
			if (hero.getCurrentState() != hero.idle) hero.setState(hero.idle);
		}
	}
	
}
