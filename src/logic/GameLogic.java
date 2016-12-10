package logic;

import javafx.scene.input.KeyCode;

public class GameLogic {
	
	private Ground ground = null;
	private static final GameLogic instance = new GameLogic();
	
	public GameLogic() {
		
	}
	
	public static GameLogic getInstance() {
		return instance;
	}
	
	public void Press(KeyCode key) {
		System.out.println(key);
	}
	
}
