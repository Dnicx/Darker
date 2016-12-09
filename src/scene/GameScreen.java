package scene;

import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;

public class GameScreen extends StackPane {
	
	private Canvas canvas;
	
	public GameScreen(int width, int height) {
		this.getChildren().add(canvas);
		canvas.setHeight(height);
		canvas.setWidth(width);
	}
}
