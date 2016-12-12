package scene;

import java.io.File;
import java.util.Scanner;

import character.Fireball;
import character.Hero;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import logic.EnemyLogic;
import logic.GameLogic;
import logic.InputUtility;
import render.Background;
import render.Renderable;
import render.RenderableHolder;

public class GameScreen extends StackPane {

	private Canvas canvas;
	//private Hero knight;
	private GraphicsContext gc;
	public Background background;
	public GameLogic gameLogic;
	public String file;
	private Thread updateEnemy;


	public GameScreen(int width, int height, String file) {
		this.file = file;
		
		canvas = new Canvas(width, height);
		gc = canvas.getGraphicsContext2D();
		this.getChildren().add(canvas);
		RenderableHolder.getInstance().loadResource();
		//knight = new Hero(heroStartX, heroStartY);
		background = new Background(RenderableHolder.getInstance().testStageFar, RenderableHolder.getInstance().testStageNear);
		background.setFarPosition(0, 0);
		background.setNearPosition(0, 0);
		gameLogic = new GameLogic(this);
		updateEnemy = new EnemyLogic(this);
		
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
						r.render(gc);
					}
					gameLogic.updateLogic();
					InputUtility.update();
					//System.out.println(gameLogic.isGameOver());
					if (gameLogic.isGameOver()) {
						System.out.println("game over");
						this.stop();
						
					}
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
	
	/*public Hero getHero() {
		return knight;
	}*/
/*	public void applyResize(){
		this.setPrefSize(main.ConfigurableOption.getInstance().getScreenWidth(), main.ConfigurableOption.getInstance().getScreenHeight());
	}*/

}
