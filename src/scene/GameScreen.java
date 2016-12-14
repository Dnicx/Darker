package scene;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import logic.EnemyLogic;
import logic.GameLogic;
import logic.InputUtility;
import main.ConfigurableOption;
import main.Main;
import render.AudioUtility;
import render.Background;
import render.Renderable;
import render.RenderableHolder;

public class GameScreen extends StackPane {

	private Canvas canvas;
	// private Hero knight;
	private GraphicsContext gc;
	public Background background;
	public GameLogic gameLogic;
	public String file;
	public Scanner fileRead;
	private Thread updateEnemy;
	private GameScreen instance;

	public GameScreen(int width, int height, String file) {
		instance = this;
		this.file = file;
		canvas = new Canvas(width, height);
		gc = canvas.getGraphicsContext2D();
		this.getChildren().add(canvas);
		// RenderableHolder.getInstance().loadResource();
		// knight = new Hero(heroStartX, heroStartY);

		try {
			fileRead = new Scanner(new File(file));
			RenderableHolder.getInstance()
					.setNearBackgroundSrc(RenderableHolder.levelDir + Main.stage + "/" + fileRead.next());
			RenderableHolder.getInstance()
					.setFarBackgroundSrc(RenderableHolder.levelDir + Main.stage + "/" + fileRead.next());
		} catch (FileNotFoundException e) {
		}
		RenderableHolder.getInstance().loadResource();
		background = new Background(RenderableHolder.getInstance().StageFar, RenderableHolder.getInstance().StageNear);
		background.setFarPosition(0, 0);
		background.setNearPosition(0, 0);
		gameLogic = new GameLogic(instance);
		updateEnemy = new EnemyLogic(instance);
		InputUtility.clear();

		gc.setFont(RenderableHolder.font);

		AudioUtility.backgroundMusic.play();
		new AnimationTimer() {
			long start = 1;

			@Override
			public void handle(long now) {
				// TODO Auto-generated method stub
				if (start == 1)
					start = now;
				long dif = now - start;
				if (dif >= 30000000) {
					start = now;
					gameLogic.updateLogic();
					InputUtility.update();
					Platform.runLater(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							for (Renderable r : RenderableHolder.getInstance().getEntity()) {
								if (r.isVisible())
									r.render(gc);
							}
							drawScore();
							drawHealthBar();
						}
					});
					if (gameLogic.isGameOver()) {
						System.out.println("game over");
						Main.instance.toggleScene(Main.gameOver);
						this.stop();
						AudioUtility.backgroundMusic.stop();
					}
					if (gameLogic.isWin()) {
						Main.instance.toggleScene(Main.win);
						this.stop();
						AudioUtility.backgroundMusic.stop();
					}
					System.out.println("loop");
				}
			}
		}.start();
		updateEnemy.start();

	}

	public GameLogic getGameLogic() {
		return gameLogic;
	}

	public GraphicsContext getGraphicContext() {
		return gc;
	}

	private void drawScore() {
		String score = "Score : " + gameLogic.getScore();
		gc.setFill(Color.WHITE);
		gc.fillText(score, ConfigurableOption.getInstance().getScreenWidth() - (score.length()) * 20, 50);
	}

	private void drawHealthBar() {
		gc.setStroke(Color.WHITE);
		gc.setLineWidth(2);
		gc.setFill(Color.GREEN);
		gc.fillText(gameLogic.getHeroName(), 140 - gameLogic.getHeroName().length() * 20, 65);
		gc.strokeRect(100, 50, 200, 20);
		gc.fillRect(100, 50, 200 * (double)((double)gameLogic.getHeroHealth() / (double)gameLogic.getHeroFullHealth()), 20);
	}

	/*
	 * public Hero getHero() { return knight; }
	 */
	/*
	 * public void applyResize(){
	 * this.setPrefSize(main.ConfigurableOption.getInstance().getScreenWidth(),
	 * main.ConfigurableOption.getInstance().getScreenHeight()); }
	 */

}
