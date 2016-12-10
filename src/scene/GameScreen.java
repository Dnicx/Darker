package scene;

import character.Hero;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import render.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import render.RenderableHolder;

public class GameScreen extends StackPane {
	
	private Canvas canvas;
	private Hero knight;
	private GraphicsContext gc;
	private Background bg;
	
	public GameScreen(int width, int height) {
		canvas = new Canvas(width, height);
		gc = canvas.getGraphicsContext2D();
		this.getChildren().add(canvas);
		RenderableHolder.getInstance().loadResource();
		knight = new Hero(500, 200);
		bg = new Background(RenderableHolder.getInstance().testStageFar, RenderableHolder.getInstance().testStageNear);
		bg.setFarPosition(0, 0);
		bg.setNearPosition(0, 0);
		RenderableHolder.getInstance().add(knight);

		bg.render(gc);
		knight.render(gc);
	}
	
	public GraphicsContext getGraphicContext() {
		return gc;
	}
	
}
