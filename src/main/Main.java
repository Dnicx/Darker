package main;

import java.io.File;
import java.util.Scanner;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import logic.GameLogic;
import logic.InputUtility;
import render.Renderable;
import render.RenderableHolder;
import scene.ConfigScreen;
import scene.GameScreen;
import scene.MenuScreen;

public class Main extends Application {
	
	public static Main instance;
	//private Scene mainMenu;
	private Scene gameScene;
	private Scene configScene;
	private Scene menuScene;
	private GameScreen gameScreen;
	private ConfigScreen configScreen;
	private MenuScreen menuScreen;
	private Stage mainStage;
	
	private String level =  "./level/test-stage";// file root directory
	private static final String levelFile = "collideBox.txt"; // file name
	
	private final String GameTitle = "Darker";
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		instance=this;
		mainStage = stage;
		menuScreen=new MenuScreen();
		menuScene=new Scene(menuScreen);
		configScreen = new ConfigScreen();
		configScene= new Scene(configScreen);
		//gameScreen = new GameScreen(ConfigurableOption.getInstance().getScreenWidth(), ConfigurableOption.getInstance().getScreenHeight(), level+"/"+levelFile);
		//gameScene = new Scene(gameScreen);
		
		//stage.setScene(gameScene);
		stage.setScene(menuScene);
		stage.setTitle(GameTitle);
		stage.setResizable(false);
		stage.setWidth(ConfigurableOption.getInstance().getScreenWidth());
		stage.setHeight(ConfigurableOption.getInstance().getScreenHeight());
		stage.show();
		//System.out.println(RenderableHolder.getInstance().getEntity());
		
		/*new AnimationTimer() {
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
					gameScreen.gameLogic.updateLogic();
					InputUtility.update();	
				}
			}
		}.start(); */
		//addListener();
		
	}
	
	@Override
	public void stop() {
		try {
			gameScreen.getGameLogic().setGameOver();
		} catch (NullPointerException e) {
		}
	}
	
	public Stage getMainStage() {
		return mainStage;
	}
	
	public void addListener() {
		mainStage.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent key) {
				// TODO Auto-generated method stub
				if (mainStage.getScene() == gameScene) {
					gameScreen.gameLogic.pressKey(key.getCode());
					//System.out.println(key.getCode());
				}
			}
		});
		
		mainStage.getScene().setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent key) {
				// TODO Auto-generated method stub
				if (mainStage.getScene() == gameScene) {
					gameScreen.gameLogic.releaseKey(key.getCode());
				}
			}
		});
		mainStage.requestFocus();
	}
	public void toggleScene(String nextScene){
		if(nextScene=="menuScene"){
			mainStage.setScene(menuScene);
		}
		else{
			gameScreen = new GameScreen(ConfigurableOption.getInstance().getScreenWidth(), ConfigurableOption.getInstance().getScreenHeight(), level+"/"+levelFile);
			gameScene = new Scene(gameScreen);
			mainStage.setScene(gameScene);
		}
		addListener();
		mainStage.requestFocus();
	}
	
	public GameScreen getGameScreen() {
		return gameScreen;
	}
	/*public void resizeStage(){
		configScreen.applyResize();
		menuScreen.applyResize();
		//gameScreen.applyResize();
		mainStage.sizeToScene();
	}*/
}
