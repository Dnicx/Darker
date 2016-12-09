package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import scene.GameScreen;

public class Main extends Application {
	
	private Scene mainMenu;
	private Scene gameScene;
	private GameScreen gameScreen;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		gameScreen = new GameScreen();
		gameScene = new Scene(gameScreen);
	}

}
