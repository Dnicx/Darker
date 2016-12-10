package main;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import logic.GameLogic;
import render.Renderable;
import render.RenderableHolder;
import scene.GameScreen;

public class Main extends Application {
	
	private Scene mainMenu;
	private Scene gameScene;
	private GameScreen gameScreen;
	private Stage mainStage;
	
	private GameLogic gameLogic;
	
	private final String GameTitle = "Darker";
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		mainStage = stage;
		gameScreen = new GameScreen(ConfigurableOption.getInstance().getScreenWidth(), ConfigurableOption.getInstance().getScreenHeight());
		gameScene = new Scene(gameScreen);
		
		gameLogic = GameLogic.getInstance();
		
		stage.setScene(gameScene);
		stage.setTitle(GameTitle);
		stage.setResizable(false);
		stage.setWidth(ConfigurableOption.getInstance().getScreenWidth());
		stage.setHeight(ConfigurableOption.getInstance().getScreenHeight());
		stage.show();
		//System.out.println(RenderableHolder.getInstance().getEntity());
		new AnimationTimer() {
			long start = 1;
			@Override
			public void handle(long now) {
				// TODO Auto-generated method stub
				if (start == 1) start = now;
				long dif = now - start;
				if (dif >= 30000000) {
					start = now;
					for (Renderable r : RenderableHolder.getInstance().getEntity()) {
						r.render(gameScreen.getGraphicContext());
					}
				}
			}
		}.start();
		stage.requestFocus();
		addListener();
	}
	
	public Stage getMainStage() {
		return mainStage;
	}
	
	public void addListener() {
		mainStage.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent key) {
				// TODO Auto-generated method stub
				gameLogic.Press(key.getCode());
			}
		});
	}

}
