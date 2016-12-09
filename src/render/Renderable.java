package render;

import javafx.scene.canvas.GraphicsContext;

public interface Renderable {
	public int getz();
	public boolean isVisible();
	public void render(GraphicsContext gc);
}
