package scene;

import java.io.File;
import java.util.Scanner;

import character.Hero;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import logic.GameLogic;
import render.Background;
import render.RenderableHolder;

public class GameScreen extends StackPane {

	private Canvas canvas;
	private Hero knight;
	private GraphicsContext gc;
	public Background background;
	public GameLogic gameLogic;
	public String file;

	public GameScreen(int width, int height, String file) {
		canvas = new Canvas(width, height);
		gc = canvas.getGraphicsContext2D();
		this.getChildren().add(canvas);
		RenderableHolder.getInstance().loadResource();
		knight = new Hero(450, 0);
		background = new Background(RenderableHolder.getInstance().testStageFar, RenderableHolder.getInstance().testStageNear);
		background.setFarPosition(0, 0);
		background.setNearPosition(0, 0);
		try {
			this.file = file;
		} catch (NullPointerException e) {
			System.out.println("can not find collide box file.");
		} catch (Exception e) {
			System.out.println("error load collide box : " + e);
		}
		gameLogic = new GameLogic(this);
		
	}

	public GraphicsContext getGraphicContext() {
		return gc;
	}
	
	public Hero getHero() {
		return knight;
	}
/*	public void applyResize(){
		this.setPrefSize(main.ConfigurableOption.getInstance().getScreenWidth(), main.ConfigurableOption.getInstance().getScreenHeight());
	}*/

}
