package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import scene.GameScreen;

public class Main extends Application {
	
	private Scene mainMenu;
	private Scene gameScene;
	private GameScreen gameScreen;
	
	private final String GameTitle = "Darker";
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		gameScreen = new GameScreen(ConfigurableOption.getInstance().getScreenWidth(), ConfigurableOption.getInstance().getScreenHeight());
		gameScene = new Scene(gameScreen);
		
		stage.setScene(gameScene);
		stage.setTitle(GameTitle);
		stage.setResizable(false);
		stage.show();
		
	}

}
