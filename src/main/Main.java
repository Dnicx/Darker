package main;

import java.io.File;
import java.nio.charset.MalformedInputException;
import java.util.Scanner;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import logic.GameLogic;
import logic.InputUtility;
import render.Renderable;
import render.RenderableHolder;
import scene.ConfigScreen;
import scene.GameOverScreen;
import scene.GameScreen;
import scene.MenuScreen;
import scene.WinScreen;

public class Main extends Application {

	public static Main instance;
	// private Scene mainMenu;
	public static final String menu = "menuScene";
	public static final String game = "gameScene";
	public static final String gameOver = "gameOverScene";
	public static final String win = "winScene";
	public static final String loading = "loadingscene";
	private Scene gameScene;
	private Scene menuScene;
	private Scene gameOverScene;
	private Scene winScene;
	private GameScreen gameScreen;
	private ConfigScreen configScreen;
	private MenuScreen menuScreen;
	private GameOverScreen gameOverScreen;
	private WinScreen winScreen;
	private Stage mainStage;

	private String level = "./level/test-stage";// file root directory
	private static final String levelFile = "collideBox.txt"; // file name

	private final String GameTitle = "Darker";

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		instance = this;
		mainStage = stage;
		menuScreen = new MenuScreen();
		menuScene = new Scene(menuScreen);
		gameOverScreen = new GameOverScreen();
		gameOverScene = new Scene(gameOverScreen);
		winScreen = new WinScreen();
		winScene = new Scene(winScreen);
		// gameScreen = new
		// GameScreen(ConfigurableOption.getInstance().getScreenWidth(),
		// ConfigurableOption.getInstance().getScreenHeight(),
		// level+"/"+levelFile);
		// gameScene = new Scene(gameScreen);

		// stage.setScene(gameScene);
		stage.setScene(menuScene);
		stage.setTitle(GameTitle);
		stage.setResizable(false);
		stage.setWidth(ConfigurableOption.getInstance().getScreenWidth());
		stage.setHeight(ConfigurableOption.getInstance().getScreenHeight());
		stage.show();
		// System.out.println(RenderableHolder.getInstance().getEntity());

		/*
		 * new AnimationTimer() { long start = 1;
		 * 
		 * @Override public void handle(long now) { // TODO Auto-generated
		 * method stub if (start == 1) start = now; long dif = now - start; if
		 * (dif >= 30000000) { start = now; for (Renderable r :
		 * RenderableHolder.getInstance().getEntity()) {
		 * r.render(gameScreen.getGraphicContext()); }
		 * gameScreen.gameLogic.updateLogic(); InputUtility.update(); } }
		 * }.start();
		 */
		addListener();

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
					// System.out.println(key.getCode());
				}
				else if(mainStage.getScene() == menuScene){
					if(key.getCode()==KeyCode.ENTER||key.getCode()==KeyCode.K){
						if(menuScreen.getSelectStartBtn()){
							menuScreen.setPushStartBtn(true);
							menuScreen.updatemenu();
						}
						else if(menuScreen.getSelectExitBtn()){
							menuScreen.setPushExitBtn(true);
							menuScreen.updatemenu();
						}
					}
					else if(key.getCode()==KeyCode.W||key.getCode()==KeyCode.UP){
						if(!menuScreen.getSelectExitBtn()){
							menuScreen.setSelectStartBtn(false);
							menuScreen.setSelectExitBtn(true);
							menuScreen.updatemenu();
						}
						else{
							menuScreen.setSelectExitBtn(false);
							menuScreen.setSelectStartBtn(true);
							menuScreen.updatemenu();
							
						}
					}
					else if(key.getCode()==KeyCode.S||key.getCode()==KeyCode.DOWN){
						if(!menuScreen.getSelectStartBtn()){
							menuScreen.setSelectStartBtn(true);
							menuScreen.setSelectExitBtn(false);
							menuScreen.updatemenu();
						}
						else{
							menuScreen.setSelectExitBtn(true);
							menuScreen.setSelectStartBtn(false);
							menuScreen.updatemenu();
							
						}
					}
				}
				else if(mainStage.getScene() == gameOverScene&&key.getCode()==KeyCode.ENTER){
					gameOverScreen.reset=false;
					gameOverScreen.update();
					
				}
				else if(mainStage.getScene() == winScene){
					if(key.getCode()==KeyCode.ENTER||key.getCode()==KeyCode.K){
						if(winScreen.getSelectrtmBtn()){
							winScreen.setPushrtmBtn(true);
							winScreen.updatemenu();
						}
						else if(winScreen.getSelectExitBtn()){
							winScreen.setPushExitBtn(true);
							winScreen.updatemenu();
						}
					}
					else if(key.getCode()==KeyCode.A||key.getCode()==KeyCode.LEFT){
						if(!winScreen.getSelectExitBtn()){
							winScreen.setSelectrtmBtn(false);
							winScreen.setSelectExitBtn(true);
							winScreen.updatemenu();
						}
						else{
							winScreen.setSelectExitBtn(false);
							winScreen.setSelectrtmBtn(true);
							winScreen.updatemenu();
							
						}
					}
					else if(key.getCode()==KeyCode.D||key.getCode()==KeyCode.RIGHT){
						if(!winScreen.getSelectrtmBtn()){
							winScreen.setSelectrtmBtn(true);
							winScreen.setSelectExitBtn(false);
							winScreen.updatemenu();
						}
						else{
							winScreen.setSelectExitBtn(true);
							winScreen.setSelectrtmBtn(false);
							winScreen.updatemenu();
							
						}
					}
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
				else if(mainStage.getScene() == gameOverScene&&!gameOverScreen.reset){
					toggleScene(menu);
					gameOverScreen.reset();
				}
				else if(mainStage.getScene() == menuScene){
					if(menuScreen.getPushExitBtn()){
						System.exit(0);
					}
					else if (menuScreen.getPushStartBtn()){
						toggleScene(game);
						menuScreen.reset();
					}
				}
				else if(mainStage.getScene() == winScene){
					if(winScreen.getPushExitBtn()){
						System.exit(0);
					}
					else if (winScreen.getPushrtmBtn()){
						toggleScene(menu);
						winScreen.reset();
					}
				}
			}
		});
		mainStage.requestFocus();
	}

	public void toggleScene(String nextScene) {
		if (nextScene.equals(menu)) {
			mainStage.setScene(menuScene);
		} else if (nextScene.equals(game)) {
			gameScreen = new GameScreen(ConfigurableOption.getInstance().getScreenWidth(),
					ConfigurableOption.getInstance().getScreenHeight(), level + "/" + levelFile);
			gameScene = new Scene(gameScreen);
			mainStage.setScene(gameScene);
		} else if (nextScene.equals(gameOver)) {
			mainStage.setScene(gameOverScene);
		}
		addListener();
		mainStage.requestFocus();
	}

	public GameScreen getGameScreen() {
		return gameScreen;
	}
	/*
	 * public void resizeStage(){ configScreen.applyResize();
	 * menuScreen.applyResize(); //gameScreen.applyResize();
	 * mainStage.sizeToScene(); }
	 */
}
