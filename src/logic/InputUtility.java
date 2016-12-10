package logic;

import java.util.ArrayList;

import javafx.scene.input.KeyCode;

public class InputUtility {
	
	private static ArrayList<KeyCode> keyTriggered = new ArrayList<>();
	private static ArrayList<KeyCode> keyPressed = new ArrayList<>();
	
	public static boolean isKeyPressed(KeyCode key) {
		return keyPressed.contains(key);
	}
	
	public static boolean isKeyTriggered(KeyCode key) {
		return keyTriggered.contains(key);
	}
	
	public static void setKeyPress(KeyCode key, boolean press) {
		if (press && !keyPressed.contains(key)) {
			keyPressed.add(key);
			keyTriggered.add(key);
		}
		if (!press && keyPressed.contains(key)) { 
			keyPressed.remove(keyPressed.indexOf(key));
		}
	}
	
	public static void update() {
		for (int i=0; i<keyTriggered.size(); i++) {
			keyTriggered.remove(i);
		}
	}

}
